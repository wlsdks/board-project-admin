package com.fastcampus.boardprojectadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 최신 방법의 security 설정하는 filterChain 방식을 적용
 * spring security 6.1.x 기준 설정코드 작성
 */
//@EnableWebSecurity -> 이건 안넣어도 된다.
@Configuration
public class SecurityConfig {

    /**
     * Spring Security 등록코드 작성
     * 5.x부터 빈 등록과 filterChain방식을 권장한다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        /**
         * 람다식을 도입한 이유로는 그냥 체이닝 방식보다 indent의 일관된 규칙을 적용할수가 있다.(IDE가 자동으로 처리해 준다.)
         * 참고로 spring security6부터는 mvcMatchers가 아닌 requestMatchers를 사용한다.
         * PathRequest.toStaticResources().atCommonLocations() 이것으로 흔히 사용되는 공통 경로(img, css, js, favicon, icon)는 다 허용해준다.
         */
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }

}
