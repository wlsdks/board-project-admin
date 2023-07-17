package com.fastcampus.boardprojectadmin.repository;

import com.fastcampus.boardprojectadmin.domain.UserAccount;
import com.fastcampus.boardprojectadmin.domain.constant.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Jpa 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest // 쿼리가 잘 동작하는지 보기위한 테스트 (Transactional이 포함되어있는 슬라이스 테스트다.)
class JpaRepositoryTest {

    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    void test1() {
        //given

        //when
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        //then
        assertThat(userAccounts)
                .isNotNull() // null이 아니냐 -> 값이 있냐
                .hasSize(5); // 크기는 몇개가 있는가 -> 가짜 데이터5개를 넣었으니 5개다.
    }

    @DisplayName("회원 정보 insert 테스트")
    @Test
    void test2() {
        //given
        long previousCount = userAccountRepository.count();
        UserAccount userAccount = UserAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), null, null, null);

        //when
        userAccountRepository.save(userAccount);

        //then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void test3() {
        //given
        UserAccount userAccount = userAccountRepository.findByUserId("wlsdks12");
        userAccount.addRoleType(RoleType.DEVELOPER);
        userAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
        userAccount.removeRoleType(RoleType.ADMIN);

        //when -> update를 보기위해 saveAndFlush를 해줌
        UserAccount updatedAccount = userAccountRepository.saveAndFlush(userAccount);

        //then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("userId", "wlsdks12") // 우리가 관찰하고싶은 value가 우리가 원했던대로 set이 되어있는가?
                .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));

    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void test4() {
        //given
        long previousCount = userAccountRepository.count();
        UserAccount userAccount = userAccountRepository.findByUserId("wlsdks12");

        //when
        userAccountRepository.delete(userAccount);

        //then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount - 1);

    }

    /** 테스트 전용 auditor 설정 */
    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("uno");
        }
    }


}