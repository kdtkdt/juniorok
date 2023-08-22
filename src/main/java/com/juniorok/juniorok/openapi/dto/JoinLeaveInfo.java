package com.juniorok.juniorok.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonRootName(value = "response")
public class JoinLeaveInfo {

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

        private int numOfRows;
        private int pageNo;
        private int totalCount;

        private Items items;

        @Getter
        public static class Items {
            private Item item;

            @Getter
            public static class Item {
                @JsonProperty(value = "nwAcqzrCnt")
                int join ; // 월별취업자수

                @JsonProperty(value = "lssJnngpCnt")
                int leave; // 월별퇴사자수
            }
        }
    }
}
