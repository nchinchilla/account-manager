package com.tecso.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tecso.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAll();

}
