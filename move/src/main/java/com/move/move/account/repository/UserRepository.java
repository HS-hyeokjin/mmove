package com.move.move.account.repository;

import com.move.move.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);

    boolean existsByUid(String uid);

}
