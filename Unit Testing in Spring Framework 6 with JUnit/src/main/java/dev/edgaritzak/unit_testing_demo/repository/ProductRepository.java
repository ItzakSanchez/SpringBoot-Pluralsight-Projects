package dev.edgaritzak.unit_testing_demo.repository;

import java.util.List;
import java.util.Optional;

import dev.edgaritzak.unit_testing_demo.models.Product;

public interface ProductRepository {

	List<Product> findAll(); 
	Optional<Product> findById(Long id);
	
	Product createProduct(Product product);
	
	int updateProduct(Product product);
	
	boolean delteProductById(Long productId);
}
