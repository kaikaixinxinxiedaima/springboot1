package com.test.dao;

import com.test.vo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface AccountDao extends JpaRepository<Account, Integer> {

}
