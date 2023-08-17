package com.juniorok.juniorok.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiConfig {

    @Value("${kakao.api.map.appkey}")
    String kakaoMapAppkey;

}
