package com.capgemini.product.service;


import java.util.List;

import com.capgemini.product.exceptions.ProductNotFoundException;
import com.capgemini.product.modal.Product;

public interface ProductService {

	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public Product findProductById(int productId) throws ProductNotFoundException;
	public void deleteProduct(Product product);
	public List<Product> findProductByName(String productName);
	public List<Product> findProductByCategory(String productCategory);
	public List<Product> findProductByCategoryAndPrice(int from,int to);
	
	}
