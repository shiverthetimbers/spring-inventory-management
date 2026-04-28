package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
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
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new RuntimeException("Did not find product id - " + id);
        }

        return product;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> listAll(String keyword){

        if (keyword != null) {
            return productRepository.search(keyword);
        }

        return (List<Product>) productRepository.findAll();
    }

    @Override
    public boolean buyProduct(long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()){
            return false;
        }

        Product product = optionalProduct.get();
        if (product.getInv() <= 0) {
            return false;
        }

        product.setInv(product.getInv() - 1);
        productRepository.save(product);

        return true;
    }
}
