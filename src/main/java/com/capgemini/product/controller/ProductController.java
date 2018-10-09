package com.capgemini.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.product.exceptions.ProductNotFoundException;
import com.capgemini.product.modal.Product;
import com.capgemini.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			Product productFromDb = productService.findProductById(product.getProductId());
			if (productFromDb != null)
				return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException p) {

		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) throws ProductNotFoundException {
		try {
			Product productFromDb = productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		} catch (ProductNotFoundException e) {

		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	

	@GetMapping("/products/category")
	public ResponseEntity<List<Product>> findProductByCategory(@RequestParam String productCategory) {
		
		return new ResponseEntity<List<Product>>(productService.findProductByCategory(productCategory),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products/name")
	public ResponseEntity<List<Product>> findProductByName(@RequestParam String productName) {
		
		return new ResponseEntity<List<Product>>(productService.findProductByCategory(productName),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products/name")
	public ResponseEntity<List<Product>> findProductByCategoryAndPrice(@RequestParam int from,@RequestParam int to) {
		
		return new ResponseEntity<List<Product>>(productService.findProductByCategoryAndPrice(from,to),HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			if (productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}

		} catch (ProductNotFoundException e) {

		}
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}
