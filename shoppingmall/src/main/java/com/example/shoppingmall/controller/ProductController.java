package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.ProductRepository; // 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProductList(Model model) {
        model.addAttribute("products", productRepository.findAll()); // 상품 리스트 전달
        return "product-list";
    }

    @GetMapping("/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(Product product) {
        productRepository.save(product); // DB 저장
        return "redirect:/products"; // 저장 후 리스트로 이동
    }

}