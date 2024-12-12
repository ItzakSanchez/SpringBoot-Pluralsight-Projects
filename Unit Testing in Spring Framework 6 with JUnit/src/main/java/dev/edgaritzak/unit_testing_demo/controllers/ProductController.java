package dev.edgaritzak.unit_testing_demo.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.edgaritzak.unit_testing_demo.models.Product;
import dev.edgaritzak.unit_testing_demo.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(path = "/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public String home() {
		return "Home page";
	}
	/*
	 * FIND
	 */
	@RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
		
		return productService.findById(id)
				.map(product -> {
					try {
						return ResponseEntity
								.ok()
								.location(new URI("/product/"+product.getId()))
								.body(product);
					} catch (Exception e){
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				})
				.orElse(ResponseEntity.notFound().build());
	}
	/*
	 * FIND ALL
	 */
	@RequestMapping(path = "/product", method = RequestMethod.GET)
	public Iterable<Product> findAll(){
		return productService.findAll();
	}
	
	/*
	 * SAVE
	 */
	@RequestMapping(path ="/product", method = RequestMethod.POST)
	public ResponseEntity<?> saveProduct(@RequestBody Product product){
		Product newProduct = productService.save(product);
		
		try {
			return ResponseEntity
				.created(new URI("/product/"+newProduct.getId()))
				.location(new URI("/product/"+newProduct.getId()))
				.body(newProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	/*
	 * UPDATE
	 */
	@RequestMapping(path = "/product", method = RequestMethod.PUT)
	private ResponseEntity<?> updateProduct(@RequestBody Product product){
		
		Optional<Product> existingProduct = productService.findById(product.getId());
		
		return existingProduct.map(prod -> {
			prod.setName(product.getName());
			prod.setDescription(product.getDescription());
			prod.setPrice(product.getPrice());
			
			try {
				if(productService.update(prod) ==1) {
					return ResponseEntity
							.ok()
							.location(new URI("/product/"+prod.getId()))
							.body(prod);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	/*
	 * DELETE
	 */
	@RequestMapping(path = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id){
		Optional<Product> existingProduct = productService.findById(id);
		
		return existingProduct.map(prod->{
			if(productService.delete(prod.getId())) {
				return ResponseEntity
						.ok()
						.build();
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}
}
