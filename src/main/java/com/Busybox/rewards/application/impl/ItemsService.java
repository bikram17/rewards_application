package com.Busybox.rewards.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.ItemsRepository;
import com.Busybox.rewards.application.model.Items;

@Service
public class ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    public Optional<Items> getItemById(Long id) {
        return itemsRepository.findById(id);
    }

    public Items createItem(Items item) {
        return itemsRepository.save(item);
    }

    public Items updateItem(Long id, Items newItemData) {
        if (itemsRepository.existsById(id)) {
            newItemData.setId(id); // Ensure the ID is set to the correct value
            return itemsRepository.save(newItemData);
        }
        return null; // Handle item not found scenario
    }

    public boolean deleteItem(Long id) {
        if (itemsRepository.existsById(id)) {
            itemsRepository.deleteById(id);
            return true;
        }
        return false; // Handle item not found scenario
    }
}