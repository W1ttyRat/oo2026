package ee.tanel.veebipood.controller;

import ee.tanel.veebipood.entity.Product;
import ee.tanel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {

    // localhost:8080/products
    // application.properties server.port=8090

//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello World!";
//    }

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id); // kustutan
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        productRepository.save(product); //siin salvestab
        return productRepository.findAll(); // siin on uuenenud seis
    }
}
