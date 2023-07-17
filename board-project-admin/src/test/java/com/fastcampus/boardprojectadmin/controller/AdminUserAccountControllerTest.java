package com.fastcampus.boardprojectadmin.controller;

import com.fastcampus.boardprojectadmin.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 어드민 회원")
@Import(SecurityConfig.class)
@WebMvcTest(AdminUserAccountControllerTest.class)
class AdminUserAccountControllerTest {

    // mvc 테스트에서 mockMvc를 많이 사용한다.
    private final MockMvc mvc;

    public AdminUserAccountControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[View][GET] 어드민 회원 페이지 - 정상 호출")
    @Test
    void test1() throws Exception {
        //given

        /**
         * content() 검사 -> 특정 browser의 호환성에따라(예전에는) html뒤에 ;charset=utf-8을 붙일때도 있었다.
         * 그래서 contentType() 이거말고 contentTypeCompatibleWith() 이걸로 해주면 검사가 통과된다.
         */
        //when & then
        mvc.perform(get("/admin/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/members"));

    }

}