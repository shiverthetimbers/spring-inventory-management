package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService{
    private final OutsourcedPartRepository partRepository;

    @Autowired
    public OutsourcedPartServiceImpl(OutsourcedPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<OutsourcedPart> findAll() {
        return (List<OutsourcedPart>) partRepository.findAll();
    }

    @Override
    public OutsourcedPart findById(long id) {
        Optional<OutsourcedPart> result = partRepository.findById(id);

        OutsourcedPart part;
        if (result.isPresent()) {
            part = result.get();
        } else {
            throw new RuntimeException("Did not find part id - " + id);
        }

        return part;
    }

    @Override
    public void save(OutsourcedPart part) {
        partRepository.save(part);

    }

    @Override
    public void deleteById(long id) {
        partRepository.deleteById(id);
    }

}
