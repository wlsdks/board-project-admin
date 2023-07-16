package com.fastcampus.boardprojectadmin.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Spring Security와의 관계를 끊어주기 위해 직접 이렇게 RoleType을 추가했다.
 * "1, 2, 3" -> table_column에 넣기(저장은 문자열 but 꺼낼때는 리스트로)
 */
@RequiredArgsConstructor
public enum RoleType {

    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    DEVELOPER("ROLE_DEVELOPER"),
    ADMIN("ROLE_ADMIN")
    ;

    // enum에서 name이라는 filed명은 이미 쓰이고있으니 사용하지 말자
    @Getter private final String roleName;

}
