package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.repository.SparePartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SparePartServiceImpl implements SparePartService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SparePartRepository sparePartRepository;

    @Override
    public void addToWareHouse(SparePart part, int amount) {

        if (sparePartRepository.existsById(part.getId())) {
            SparePart sparePart = sparePartRepository.getById(part.getId());
            sparePart.addStock(amount);
            sparePartRepository.save(sparePart);
        }
        part.setInStock(amount);
        sparePartRepository.save(part);
    }

    @Override
    public void delete(SparePart part) {
        if (sparePartRepository.existsById(part.getId())) {
            sparePartRepository.deleteById(part.getId());
        }
    }

    @Override
    public void addToOrder(SparePart sparePart) {

    }

    @Override
    public SparePart findById(Long id) {
        return sparePartRepository.findById(id).get();
    }

    @Override
    public SparePart findByCode(int code) {
        return sparePartRepository.findByCode(code);
    }

    @Override
    public boolean exists(int code) {
        if (sparePartRepository.findByCode(code) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void save(SparePart part) {
        if (sparePartRepository.existsById(part.getId())) {
            SparePart sparePart = sparePartRepository.getById(part.getId());
            sparePart.setCode(part.getCode());
            sparePart.setPrice(part.getPrice());
            sparePart.setName(part.getName());
        }
        sparePartRepository.save(part);
    }
}
