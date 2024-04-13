package com.Busybox.rewards.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.OrderMasterRepository;
import com.Busybox.rewards.application.model.Items;
import com.Busybox.rewards.application.model.orderMaster;

@Service
public class OrderMasterService {

    private final OrderMasterRepository OrderMasterRepository;

    @Autowired ItemsService itemService;
    @Autowired AddressImpl addImpl;
    
    @Autowired
    public OrderMasterService(OrderMasterRepository OrderMasterRepository) {
        this.OrderMasterRepository = OrderMasterRepository;
    }

    public List<orderMaster> getAllOrders() {
        return OrderMasterRepository.findAll();
    }

    public Optional<orderMaster> getOrderById(Long id) {
        return OrderMasterRepository.findById(id);
    }

    public orderMaster createOrder(orderMaster orderMaster) {
    	String[] address = orderMaster.getShipping_address();
    	Double d =0.0;
    	List<Items> items = orderMaster.getItems();
    	for(Items item : items) {
    		itemService.createItem(item);
    		d = d+item.getAmount();
    	}
    	String formattedValue = String.format("%.2f", d);
    	orderMaster.setTotal_amount(Double.parseDouble(formattedValue));
        return OrderMasterRepository.save(orderMaster);
    }

    public orderMaster updateOrder(Long id, orderMaster neworderMasterData) {
        if (OrderMasterRepository.existsById(id)) {
            neworderMasterData.setId(id); // Ensure the ID is set to the correct value
            return OrderMasterRepository.save(neworderMasterData);
        }
        return null; // Handle order not found scenario
    }

    public boolean deleteOrder(Long id) {
        if (OrderMasterRepository.existsById(id)) {
            OrderMasterRepository.deleteById(id);
            return true;
        }
        return false; // Handle order not found scenario
    }
}