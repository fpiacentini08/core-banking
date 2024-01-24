package com.core.banking.domain.repository;

import com.core.banking.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>
{

	@Modifying
	@Query("update account a set a.balance = :balance where a.id = :id")
	void updateAccountBalance(String id, BigDecimal balance);

}
