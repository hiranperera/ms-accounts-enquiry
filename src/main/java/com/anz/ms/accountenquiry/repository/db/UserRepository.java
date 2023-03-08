package com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.repository.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserCode(String userCode);
}
