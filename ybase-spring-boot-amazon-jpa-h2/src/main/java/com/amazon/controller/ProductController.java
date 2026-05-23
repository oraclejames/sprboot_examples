package com.amazon.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.amazon.product.model.Product;
import com.amazon.service.ProductService;

@RestController
@CrossOrigin(origins = "*") // Crucial for your E-commerce project when connecting a frontend later
public class ProductController {

    private final ProductService productService;

    // Constructor Injection (Cleaner and easier to test than @Autowired on fields)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. GET all products
    @GetMapping("/products")
    @CrossOrigin("*")
    public List<Product> getAllProducts() {
        System.out.println("Fetching all products..."); 
        return productService.getAllProducts();
    }

    // 2. GET product by ID
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        System.out.println("Fetching product with ID: " + productId); 
        return productService.getProductByID(productId);
    }

    // 3. POST add a new product
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        System.out.println("Adding new product: " + product); 
        productService.addProduct(product);
    }
	
    // 4. PUT update an existing product
    @PutMapping("/products/{productId}")
    public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
        System.out.println("Updating product ID: " + productId);
        productService.updateProduct(productId, product);
    }

    // 5. DELETE a product
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        System.out.println("Deleting product ID: " + productId); 
        productService.deleteProduct(productId);
    }
}