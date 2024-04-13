package com.Busybox.rewards.application.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.model.Invoice;
@Service
public interface InvoiceService {
    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long id);

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Long id, Invoice invoice);

    boolean deleteInvoice(Long id);
}
