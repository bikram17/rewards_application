package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Busybox.rewards.application.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
