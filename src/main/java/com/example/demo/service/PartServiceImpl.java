package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
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
public class PartServiceImpl implements PartService{

    private final PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return (List<Part>) partRepository.findAll();
    }

    public List<Part> listAll(String keyword){
        if(keyword !=null){
            return partRepository.search(keyword);
        }
        return (List<Part>) partRepository.findAll();
    }

    @Override
    public Part findById(long id) {
        Optional<Part> result = partRepository.findById(id);

        Part part;
        if (result.isPresent()) {
            part = result.get();
        }
        else {
            // we didn't find the part id
            throw new RuntimeException("Did not find part id - " + id);
        }

        return part;
    }

    @Override
    public void save(Part part) {
        partRepository.save(part);
    }

    @Override
    public boolean deleteIfUnused(long id) {

        Part part = findById(id);

        if (!part.getAssocRecipeLines().isEmpty()) {
            return false;
        }

        partRepository.deleteById(id);
        return true;
    }
}
