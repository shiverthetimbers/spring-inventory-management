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

    List<Part> listAll(String keyword);

    Part findById(long id);

    void save (Part part);

    boolean deleteIfUnused(long id);
}
