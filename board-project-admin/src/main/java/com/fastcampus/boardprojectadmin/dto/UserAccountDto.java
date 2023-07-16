package com.fastcampus.boardprojectadmin.dto;

import com.fastcampus.boardprojectadmin.domain.UserAccount;
import com.fastcampus.boardprojectadmin.domain.constant.RoleType;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.fastcampus.boardprojectadmin.dto}
 */
public record UserAccountDto(
        String userId,
        String userPassword,
        Set<RoleType> roleTypes, // 권한정보 추가
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    /** 생성할때 생성정보들을 null로 세팅하는 factory 메소드(Auditing에 의해 채워짐) */
    public static UserAccountDto of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return new UserAccountDto(userId, userPassword, roleTypes, email, nickname, memo, null, null, null, null);
    }

    /** 모든 값을 받아서 생성하는 factory 메소드 */
    public static UserAccountDto of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, roleTypes, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getRoleTypes(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                roleTypes,
                email,
                nickname,
                memo
        );
    }

}