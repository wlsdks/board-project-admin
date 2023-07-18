package com.fastcampus.boardprojectadmin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * thymeleaf 템플릿 전용 설정 추가
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        // 원래있던 decoupled 로직에 설정을 추가해준 코드다.
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.decoupledLogic());

        return defaultTemplateResolver;
    }

    // record를 사용하여 깔끔하게 처리했다.
    // thymeleaf3 로 yml 파일에서 설정을 시작한다는 의미이다.
    @ConfigurationProperties("spring.thymeleaf3")
    public record Thymeleaf3Properties(boolean decoupledLogic) {}

}
