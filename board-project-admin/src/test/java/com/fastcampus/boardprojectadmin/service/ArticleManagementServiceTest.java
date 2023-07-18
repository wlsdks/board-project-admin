package com.fastcampus.boardprojectadmin.service;

import com.fastcampus.boardprojectadmin.domain.constant.RoleType;
import com.fastcampus.boardprojectadmin.dto.ArticleDto;
import com.fastcampus.boardprojectadmin.dto.UserAccountDto;
import com.fastcampus.boardprojectadmin.dto.properties.ProjectProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DisplayName("비즈니스 로직 - 게시글 관리")
class ArticleManagementServiceTest {

    // 중첩 테스트 생성
    @DisplayName("API Mocking 테스트")
    @EnableConfigurationProperties(ProjectProperties.class) // slice 테스트동안 이 프로퍼티를 사용하겠다는 의미이다.
    @AutoConfigureWebClient(registerRestTemplate = true)
    @RestClientTest(ArticleManagementService.class)
    @Nested
    class RestTemplateTest {

        private final ArticleManagementService sut;
        private final ProjectProperties projectProperties;
        private final MockRestServiceServer server;
        private final ObjectMapper mapper; // 직렬화 역직렬화에 사용

        @Autowired
        public RestTemplateTest(ArticleManagementService sut, ProjectProperties projectProperties, MockRestServiceServer server, ObjectMapper mapper) {
            this.sut = sut;
            this.projectProperties = projectProperties;
            this.server = server;
            this.mapper = mapper;
        }

        @DisplayName("게시글 목록 API를 호출하면, 게시글들을 가져온다.")
        @Test
        void test() {
            //given
            ArticleDto expectedArticle = createArticleDto("제목", "글");

            //when
            List<ArticleDto> result = sut.getArticles();

            //then
            assertThat(result).isNotNull();
        }

        private ArticleDto createArticleDto(String title, String content) {
            return ArticleDto.of(
                    1L,
                    createUserAccountDto(),
                    title,
                    content,
                    null,
                    LocalDateTime.now(),
                    "Uno",
                    LocalDateTime.now(),
                    "Uno"
            );
        }

        private UserAccountDto createUserAccountDto() {
            return UserAccountDto.of(
                    "unoTest",
                    "pw",
                    Set.of(RoleType.ADMIN),
                    "uno-test@email.com",
                    "uno-test",
                    "test memo"
            );
        }


    }

}