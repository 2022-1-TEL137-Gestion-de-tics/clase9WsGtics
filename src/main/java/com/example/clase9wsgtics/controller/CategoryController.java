package com.example.clase9wsgtics.controller;

import com.example.clase9wsgtics.entity.Category;
import com.example.clase9wsgtics.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public List<Category> listarProductos() {
        return categoryRepository.findAll();
    }

}
