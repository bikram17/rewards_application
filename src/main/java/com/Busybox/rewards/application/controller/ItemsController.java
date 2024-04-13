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

import com.Busybox.rewards.application.impl.ItemsService;
import com.Busybox.rewards.application.model.Items;

@RestController
@RequestMapping("/items")
public class ItemsController {

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        List<Items> items = itemsService.getAllItems();
        return ResponseHandler.generateResponse(items, HttpStatus.OK, "Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Optional<Items> item = itemsService.getItemById(id);
        return item.map(value -> ResponseHandler.generateResponseNull(value,HttpStatus.OK))
                .orElseGet(() -> ResponseHandler.generateResponseNull("No Content",HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Items item) {
        Items newItem = itemsService.createItem(item);
        return ResponseHandler.generateResponseNull(newItem,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Items newItemData) {
        Items updatedItem = itemsService.updateItem(id, newItemData);
        return updatedItem != null
                ?  ResponseHandler.generateResponseNull(updatedItem,HttpStatus.OK)
                : ResponseHandler.generateResponseNull("No Content",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        boolean isDeleted = itemsService.deleteItem(id);
        return isDeleted
                ? ResponseHandler.generateResponseNull("No Content",HttpStatus.NO_CONTENT)
                : ResponseHandler.generateResponseNull("No Content",HttpStatus.NOT_FOUND);
    }
}
