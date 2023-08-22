package com.juniorok.juniorok.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonRootName(value = "response")
public class BusinessBaseInfo {

    private String returnAuthMsg;
    private String returnReasonCode;
    private String errMsg;

    private String resultCode;
    private String resultMsg;

    private int numOfRows;
    private int pageNo;
    private int totalCount;

    @JsonProperty(value = "items")
    List<Item> items;

    @Getter
    public static class Item {
        @JsonProperty(value = "dataCrtYm")
        int createdDate; // YYYYMM

        @JsonProperty(value = "seq")
        int sequenceNumber; // 식별번호

        @JsonProperty(value = "wkplNm")
        String companyName; // 사업장명

        @JsonProperty(value = "bzowrRgstNo")
        String businessNumber; // 사업자등록번호

        @JsonProperty(value = "wkplRoadNmDtlAddr")
        String address; // 사업장도로명상세주소

        @JsonProperty(value = "wkplJnngStcd")
        int joinPensionStatusCode; //사업장가입상태코드(1:등록, 2:탈퇴)

        @JsonProperty(value = "wkplStylDvcd")
        int companyType; // 사업장형태구분코드(1:법인, 2:개인)

        @JsonProperty(value = "ldongAddrMgplDgCd")
        int stateCode; // 법정동주소광역시도코드

        @JsonProperty(value = "ldongAddrMgplSgguCd")
        int cityCode; // 법정동주소시군구코드

        @JsonProperty(value = "ldongAddrMgplSgguEmdCd")
        int dongCode; // 법정동주소읍면동코드
    }

}