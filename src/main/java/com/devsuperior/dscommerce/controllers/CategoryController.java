package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.dto.CategoriaDTO;
import com.devsuperior.dscommerce.entities.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.dto.ProductMinDTO;
import com.devsuperior.dscommerce.services.CategoryService;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

}
