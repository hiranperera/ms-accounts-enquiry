package com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(Long accountId);

    List<Account> findByUser(User user);
}
