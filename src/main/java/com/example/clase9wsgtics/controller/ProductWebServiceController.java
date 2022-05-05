package com.example.clase9wsgtics.controller;

import com.example.clase9wsgtics.entity.Product;
import com.example.clase9wsgtics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductWebServiceController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = {"/product"})
    public List<Product> listarProductos() {
        return productRepository.findAll();
    }
}
