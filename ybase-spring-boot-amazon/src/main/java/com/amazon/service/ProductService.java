package com.amazon.service;

import java.util.Arrays;
import java.util.*;

import org.springframework.stereotype.Service;

import com.amazon.product.model.Product;

@Service
public class ProductService {
	/*
	 * List<Product> products = Arrays.asList( new Product(1, "Laptop", 999.99), new
	 * Product(2, "Smartphone", 499.99), new Product(3, "Headphones", 199.99) );
	 */
	
	// FIXED: Wrap Arrays.asList inside 'new ArrayList<>()' to make it fully mutable
    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", 999.99),
            new Product(2, "Smartphone", 499.99),
            new Product(3, "Headphones", 199.99)
    ));
	
	public List<Product> getAllProducts() {
		return products;
	}
	
	public  Product getProductByID(int productId) {

		return products.stream()
				.filter(p -> p.getProductId() == productId)
				.findFirst()
				.orElse(null);
	}
 //create  
	public void addProduct(Product product) {
		products.add(product);
	}
	// delete 
	 
	
	//update 
	public void updateProduct(int productId, Product updatedProduct) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductId() == productId) {
				products.set(i, updatedProduct);
				return;
			}
		}
	}

	/*
	 * public void deleteProduct(int productId) { // TODO Auto-generated method stub
	 * //products.removeIf(p -> p.getProductId() == productId); int indexToRemove =
	 * 0; for (int i = 0; i < products.size(); i++) { if
	 * (products.get(i).getProductId() == productId) { indexToRemove = i; } }
	 * products.remove(indexToRemove); }
	 */
	public void deleteProduct(int productId) {
	    products.removeIf(p -> p.getProductId() == productId);
	}
}
