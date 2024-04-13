package com.Busybox.rewards.application.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface WalletBalanceDao extends JpaRepository<WalletBalanceTransactionRecord, Long> {

}
