package com.capgemini.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.product.modal.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

	@Query("{'productName' : ?0}")
	public List<Product> findProductByName(String productName);

	@Query("{'productCategory' : ?0}")
	public List<Product> findProductByCategory(String productCategory);
	
	@Query("{'productCategory':'laptop','productPrice':{'$gt':5000,'$lt':100000}}")
	public List<Product> findProductByCategoryAndPrice(int from,int to);

}
