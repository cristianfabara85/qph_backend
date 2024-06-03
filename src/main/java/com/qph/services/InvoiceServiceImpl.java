package com.qph.services;

import java.util.Date;
import java.util.List;

import com.qph.entities.Invoice;
import com.qph.entities.InvoiceDetail;
import com.qph.repository.InvoiceRepository;
import com.qph.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl {

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Autowired
    public StockRepository stockRepository;

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll(Sort.by(Direction.DESC, "id"));
    }

    @Transactional
    public Invoice create(Invoice invoice) {
        invoice.setInvoiceDate(new Date());
        invoice.setItems(invoice.getItems());

        Invoice response = invoiceRepository.save(invoice);

        if (response.getId() != null) {
            response.getItems().forEach(item->{
                stockRepository.updateStocksale(item.getProductId(),item.getQuantity());
                stockRepository.updateAvailableStock(item.getProductId());

            });
        }

        return response;
    }
}
