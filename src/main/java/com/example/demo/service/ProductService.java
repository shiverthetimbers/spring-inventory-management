package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    void save (Product product);

    void deleteById(long id);

    List<Product> listAll(String keyword);

    boolean buyProduct(long id);
}
