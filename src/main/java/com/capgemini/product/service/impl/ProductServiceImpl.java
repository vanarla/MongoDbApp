package com.capgemini.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.product.exceptions.ProductNotFoundException;
import com.capgemini.product.modal.Product;
import com.capgemini.product.repository.ProductRepository;
import com.capgemini.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product is not found");

	}

	@Override
	public void deleteProduct(Product product) {

		productRepository.delete(product);

	}

	@Override
	public List<Product> findProductByName(String productName) {
		return productRepository.findProductByName(productName);
	}

	@Override
	public List<Product> findProductByCategory(String productCategory) {
		return productRepository.findProductByCategory(productCategory);
	}

	@Override
	public List<Product> findProductByCategoryAndPrice(int from,int to) {
		return productRepository.findProductByCategoryAndPrice(from,to);
	}

}
