package com.fastcampus.boardprojectadmin.repository;

import com.fastcampus.boardprojectadmin.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUserId(String userId);
}
