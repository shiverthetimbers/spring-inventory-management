package com.example.demo.service;

import com.example.demo.domain.Part;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface PartService {
    List<Part> findAll();
    Part findById(long id);
    void save (Part part);
    void deleteById(int id);

    List<Part> listAll(String keyword);
}
