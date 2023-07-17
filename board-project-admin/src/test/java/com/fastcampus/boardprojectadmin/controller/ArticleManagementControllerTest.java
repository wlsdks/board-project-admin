package com.fastcampus.boardprojectadmin.controller;

import com.fastcampus.boardprojectadmin.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 1. 실제 config를 import한다. -> 스프링 시큐리티 인증을 통과하기 위함
 * 2. 컨트롤러 slice 테스트 어노테이션 @WebMvcTest 사용
 */
@DisplayName("View 컨트롤러 - 게시글 관리")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleManagementController.class)
class ArticleManagementControllerTest {

    // mvc 테스트에서 mockMvc를 많이 사용한다.
    private final MockMvc mvc;

    public ArticleManagementControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[View][GET] 게시글 관리 페이지 - 정상 호출")
    @Test
    void test1() throws Exception {
        //given

        /**
         * content() 검사 -> 특정 browser의 호환성에따라(예전에는) html뒤에 ;charset=utf-8을 붙일때도 있었다.
         * 그래서 contentType() 이거말고 contentTypeCompatibleWith() 이걸로 해주면 검사가 통과된다.
         */
        //when & then
        mvc.perform(get("/management/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("management/articles"));

    }
}