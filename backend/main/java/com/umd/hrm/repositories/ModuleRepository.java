package com.umd.hrm.repositories;

import com.umd.hrm.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Account, Long> {

}

