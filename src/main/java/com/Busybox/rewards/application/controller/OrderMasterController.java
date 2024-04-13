package com.Busybox.rewards.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.impl.OrderMasterService;
import com.Busybox.rewards.application.model.orderMaster;

@RestController
@RequestMapping("/orders")
public class OrderMasterController {

    private final OrderMasterService orderMasterService;

    public OrderMasterController(OrderMasterService orderMasterService) {
        this.orderMasterService = orderMasterService;
    }

    @GetMapping
    public ResponseEntity<List<orderMaster>> getAllOrders() {
        List<orderMaster> orders = orderMasterService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<orderMaster> getOrderById(@PathVariable Long id) {
        Optional<orderMaster> order = orderMasterService.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<orderMaster> createOrder(@RequestBody orderMaster orderMaster) {
    	orderMaster newOrder = orderMasterService.createOrder(orderMaster);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<orderMaster> updateOrder(@PathVariable Long id, @RequestBody orderMaster newOrderMasterData) {
    	orderMaster updatedOrder = orderMasterService.updateOrder(id, newOrderMasterData);
        return updatedOrder != null
                ? new ResponseEntity<>(updatedOrder, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderMasterService.deleteOrder(id);
        return isDeleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}