package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.SparePart;

public interface SparePartService {
    void addToWareHouse(SparePart part, int amount);
    void delete(SparePart part);
    void addToOrder(SparePart sparePart);
    SparePart findById(Long id);
    SparePart findByCode(int code);
    boolean exists(int code);
    void save(SparePart part);
}
