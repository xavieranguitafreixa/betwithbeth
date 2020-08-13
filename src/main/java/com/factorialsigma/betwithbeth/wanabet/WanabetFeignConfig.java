package com.factorialsigma.betwithbeth.wanabet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableFeignClients(basePackages = "com.factorialsigma.betwithbeth.wanabet")
public class WanabetFeignConfig {
}
