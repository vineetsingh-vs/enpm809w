package com.umd.hrm.repositories;

import com.umd.hrm.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Account, Long> {

}

