package com.fastcampus.boardprojectadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

// 모든 설정으로부터 ConfigurationProperties가 있는지 scan한다.
@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardProjectAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardProjectAdminApplication.class, args);
    }

}
