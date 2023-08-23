package com.juniorok.juniorok.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonRootName(value = "response")
public class BusinessDetailInfo {

    private Header header;

    @Getter
    public static class Header {
        private String resultCode;
        private String resultMsg;

        private String returnAuthMsg;
        private String returnReasonCode;
        private String errMsg;
    }

    private Body body;

    @Getter
    public static class Body {

        private Item item;

        @Getter
        public static class Item {
            @JsonProperty(value = "vldtVlKrnNm")
            String vldtVlKrnNm; // 사업장업종코드명(예. 응용 소프트웨어 개발 및 공급업)

            @JsonProperty(value = "adptDt")
            Date adptDt; // 사업장등록일

            @JsonProperty(value = "scsnDt")
            Date scsnDt; // 사업장탈퇴일

            @JsonProperty(value = "jnngpCnt")
            int employees; // 가입자수

            @JsonProperty(value = "crrmmNtcAmt")
            long monthlyAmount; // 당월고지금액

            @JsonProperty(value = "wkplNm")
            String companyName; // 사업장명

            @JsonProperty(value = "bzowrRgstNo")
            String bzowrRgstNo; // 사업자등록번호

            @JsonProperty(value = "wkplRoadNmDtlAddr")
            String address; // 사업장도로명상세주소

            @JsonProperty(value = "wkplJnngStcd")
            int joinPensionStatusCode; //사업장가입상태코드(1:등록, 2:탈퇴)

            @JsonProperty(value = "ldongAddrMgplDgCd")
            int stateCode; // 법정동주소광역시도코드

            @JsonProperty(value = "ldongAddrMgplSgguCd")
            int cityCode; // 법정동주소시군구코드

            @JsonProperty(value = "ldongAddrMgplSgguEmdCd")
            int dongCode; // 법정동주소읍면동코드

            @JsonProperty(value = "wkplStylDvcd")
            int companyType; // 사업장형태구분코드(1:법인, 2:개인)

            @JsonProperty(value = "wkplIntpCd")
            int wkplIntpCd; // 사업업종코드(국세청 업종코드 참조)
        }

    }

}
