package com.juniorok.juniorok.openapi;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.juniorok.juniorok.config.ApiConfig;
import com.juniorok.juniorok.openapi.dto.BusinessBaseInfo;
import com.juniorok.juniorok.openapi.dto.BusinessDetailInfo;
import com.juniorok.juniorok.openapi.dto.JoinLeaveInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class NationalPensionApi {

    private final ApiConfig apiConfig;
    private final String API_BASE_URL = "http://apis.data.go.kr/B552015/NpsBplcInfoInqireService";
    private final String BUSINESS_BASE_INFO_LIST_API_PATH = "/getBassInfoSearch";
    private final String BUSINESS_DETAIL_PER_MONTH_API_PATH = "/getDetailInfoSearch";
    private final String EMPLOYEE_JOIN_LEAVE_PER_MONTH_API_PATH = "/getPdAcctoSttusInfoSearch";
    private final String REQUEST_SIZE = "50";

    private BusinessDetailInfo businessDetailInfo;
    private JoinLeaveInfo joinLeaveInfo;
    private int recentSequenceNumber;
    @Getter
    private int recentYearAndMonth;

    public int getRecentEmployees(long businessNumber, String companyName) {
        requestBusinessInfo(businessNumber, companyName);
        return businessDetailInfo.getBody().getItem().getEmployees();
    }

    public String getAddress(long businessNumber, String companyName) {
        requestBusinessInfo(businessNumber, companyName);
        return businessDetailInfo.getBody().getItem().getAddress();
    }

    public long getRecentAverageSalary(long businessNumber, String companyName) {
        requestBusinessInfo(businessNumber, companyName);
        return calculateAverageSalary();
    }

    private long calculateAverageSalary() {
        int employees = businessDetailInfo.getBody().getItem().getEmployees();
        return (long) (businessDetailInfo.getBody().getItem().getMonthlyAmount() / employees / 0.09 * 12);
    }

    public int getJoinCount(long businessNumber, String companyName) {
        requestBusinessInfo(businessNumber, companyName);
        requestJoinLeaveInfo(recentSequenceNumber);
        return joinLeaveInfo.getBody().getItems().getItem().getJoin();
    }

    public int getLeaveCount(long businessNumber, String companyName) {
        requestBusinessInfo(businessNumber, companyName);
        requestJoinLeaveInfo(recentSequenceNumber);
        return joinLeaveInfo.getBody().getItems().getItem().getLeave();
    }

    private void requestBusinessInfo(long businessNumber, String companyName) {
        if (businessDetailInfo == null || !businessDetailInfo.getBody().getItem().getCompanyName().equals(companyName)) {
            recentSequenceNumber = requestRecentSequenceNumber(businessNumber, companyName);
            requestBusinessDetailInfo(recentSequenceNumber);
        }
    }

    private int requestRecentSequenceNumber(long businessNumber, String companyName) {
        String url = buildBusinessBaseInfoUrl(List.of(String.valueOf(businessNumber).substring(0, 6), companyName, "1", REQUEST_SIZE));
        var xmlMapper = new XmlMapper();
        JavaType type = xmlMapper.getTypeFactory().constructCollectionType(List.class, BusinessBaseInfo.class);
        try {
            List<BusinessBaseInfo> businessBaseInfoList = xmlMapper.readValue(new URL(url), type);

            // 국민연금 API는 사업자명 검색 시 쿼리 파라미터에 포함된 문자열이 포함된 모든 사업자 정보를 제공합니다.
            // 또한 같은 사업자명을 포함하면서 사업자번호가 앞 6자리는 같은 경우가 있어 바로 데이터를 적용하기에는 무리가 있습니다.
            // 프로젝트 발표 기간이 얼마 남지 않아 임시적으로 적용을 하지만, 데이터베이스 저장 후 필터링해야 합니다.
            if (businessBaseInfoList.get(0).getResultCode().equals("00")) {
                List<BusinessBaseInfo.Item> items =  businessBaseInfoList.get(1).getItems().stream()
                        .sorted(Comparator.comparing(BusinessBaseInfo.Item::getCreatedDate).reversed())
                        .toList();
                recentYearAndMonth = items.get(0).getCreatedDate();
                return items.get(0).getSequenceNumber();
            }
        } catch (IOException e) {
            return 0;
        }
        return 0;
    }

    private String buildBusinessBaseInfoUrl(List<String> queryParams) {
        return UriComponentsBuilder.fromHttpUrl(API_BASE_URL + BUSINESS_BASE_INFO_LIST_API_PATH)
                .queryParam("serviceKey", apiConfig.getDataGoKrServiceKey())
                .queryParam("bzowr_rgst_no", queryParams.get(0))
                .queryParam("wkpl_nm", URLEncoder.encode(queryParams.get(1), StandardCharsets.UTF_8))
                .queryParam("pageNo", queryParams.get(2))
                .queryParam("numOfRows", queryParams.get(3))
                .build().toString();
    }

    private void requestBusinessDetailInfo(int sequenceNumber) {
        String url = buildBusinessDetailInfoUrl(sequenceNumber);
        var xmlMapper = new XmlMapper();
        try {
            businessDetailInfo = xmlMapper.readValue(new URL(url), BusinessDetailInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildBusinessDetailInfoUrl(int sequenceNumber) {
        return UriComponentsBuilder.fromHttpUrl(API_BASE_URL + BUSINESS_DETAIL_PER_MONTH_API_PATH)
                .queryParam("serviceKey", apiConfig.getDataGoKrServiceKey())
                .queryParam("seq", sequenceNumber)
                .build().toString();
    }

    private void requestJoinLeaveInfo(int sequenceNumber) {
        String url = buildJoinLeaveInfoUrl(sequenceNumber);
        var xmlMapper = new XmlMapper();
        try {
            joinLeaveInfo = xmlMapper.readValue(new URL(url), JoinLeaveInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildJoinLeaveInfoUrl(int sequenceNumber) {
        return UriComponentsBuilder.fromHttpUrl(API_BASE_URL + EMPLOYEE_JOIN_LEAVE_PER_MONTH_API_PATH)
                .queryParam("serviceKey", apiConfig.getDataGoKrServiceKey())
                .queryParam("seq", sequenceNumber)
                .build().toString();
    }

}
