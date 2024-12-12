package dev.edgaritzak.unit_testing_demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.edgaritzak.unit_testing_demo.models.Product;
import dev.edgaritzak.unit_testing_demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public Optional<Product> findById(Long id){
		return productRepo.findById(id);
	}
	
	public List<Product> findAll(){
		return productRepo.findAll();
	}
	
	public Product save(Product product) {
		return productRepo.createProduct(product);
	}

	public int update(Product product) {
		return productRepo.updateProduct(product);
	}
	
	public boolean delete(Long id) {
		return productRepo.delteProductById(id);
	}
}
