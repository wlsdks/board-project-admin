package com.fastcampus.boardprojectadmin.domain;

import com.fastcampus.boardprojectadmin.domain.constant.RoleType;
import com.fastcampus.boardprojectadmin.domain.converter.RoleTypesConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true) //auditing 필드도 toString 대상에 넣어준다.
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {

    // 이 엔티티만 id값을 Long을 안쓰고 String을 사용했다. -> reference를 빠르게 찾기 위함
    // String을 pk로 사용중이다.
    @Id
    @Column(length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    /**
     * 정석은 테이블을 따로 만들어서 연관관계를 만드는 것이지만 그럴수 없는 상황이라고 가정하고 접근해봤다.
     * roleTypes필드에 final달라는 오류가 보여도 달지말아라 jpa에서는 final붙이면 자동 update가 안되기 때문이다.
     */
    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Setter
    private String memo;

    protected UserAccount() {}

    // private 생성자 -> factory method를 만들기 위해 private로 선언한다.
    private UserAccount(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.roleTypes = roleTypes;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy; // 최초에는 생성자와 수정자가 같으므로 둘다 createdBy로 해준다.
    }

    // UserAccount를 만드는 factory 메소드1 - 인증정보가 없을때(최초 회원가입일때)
    public static UserAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        return new UserAccount(userId, userPassword, roleTypes, email, nickname, memo, createdBy);
    }

    // UserAccount를 만드는 factory 메소드2 - 인증정보가 이미 있을때(createdBy를 안넣어줘도됨)
    public static UserAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        // 이미 createdBy가 있는 상태일것이라 createdBy 자리에 null을 넣는다.
        return UserAccount.of(userId, userPassword, roleTypes, email, nickname, memo, null);
    }

    // RoleType를 세팅해주는 메소드
    public void addRoleType(RoleType roleType) {
        this.getRoleTypes().add(roleType);
    }

    // RoleType를 여러개 세팅해주는 메소드
    public void addRoleTypes(Collection<RoleType> roleTypes) {
        //addAll()은 컬렉션을 받아서 전부 더해준다.
        this.getRoleTypes().addAll(roleTypes);
    }

    // RoleType을 하나를 삭제하는 메소드
    public void removeRoleType(RoleType roleType) {
        this.getRoleTypes().remove(roleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }

}
