package dev.edgaritzak.unit_testing_demo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.edgaritzak.unit_testing_demo.models.Product;
import dev.edgaritzak.unit_testing_demo.repository.ProductRepository;
import dev.edgaritzak.unit_testing_demo.services.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {
	
//	@Autowired
//	private ProductService productService;
//	
//	@MockitoBean
//	private ProductRepository productRepository;
//	
//	@Test
//	@DisplayName("FindById - SUCCESS")
//	void testFindByIdSuccess() {
//		Product mockProduct = new Product(1L,"Orange Juice", "100 percent orange juice 1gl", 3.50F);
//		when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
//		
//		Optional<Product> ProductResult =productService.findById(1L);
//		
//		Assertions.assertTrue(ProductResult.isPresent());
//		Assertions.assertSame(ProductResult.get(), mockProduct);
//	}
//	
//	@Test
//	@DisplayName("FindById - NOT FOUND")
//	void testFindByIdNotFound() {
//		
//		when(productRepository.findById(1L)).thenReturn(Optional.empty());
//		
//		Optional<Product> productResult =productService.findById(1L);
//		
//		Assertions.assertTrue(productResult.isEmpty());
//	}
//	
//	@Test
//	@DisplayName("FindAll -SUCCESS")
//	void testFindAll() {
//		
//		Product mockProduct1 = new Product();
//		Product mockProduct2 = new Product();
//		Product mockProduct3 = new Product();
//		
//		Mockito.when(productRepository.findAll()).thenReturn(List.of(mockProduct1, mockProduct2, mockProduct3));
//		
//		List<Product> resultList = productService.findAll();
//		
//		Assertions.assertEquals(resultList.size(), 3);
//	}
//	
//	@Test
//	@DisplayName("FindAll - NO RESULTS")
//	void testFindAllNoResults() {
//		Mockito.when(productRepository.findAll()).thenReturn(List.of());
//		
//		List<Product> resultList = productService.findAll();
//		
//		Assertions.assertEquals(resultList.size(), 0);
//	}
//	
//	
//	@Test
//	@DisplayName("SaveProduct - SUCCESS")
//	void testSaveProduct() {
//		
//		Product mockProduct = new Product();
//		mockProduct.setName("Vanilla Ice Cream");
//		mockProduct.setDescription("A sweet frozen dessert made from milk");
//		mockProduct.setPrice(1.10F);
//		
//		Product expectedProductResult = new Product(1L,"Vanilla Ice Cream","A sweet frozen dessert made from milk", 1.10F);
//		
//		Mockito.when(productRepository.createProduct(mockProduct)).thenReturn(expectedProductResult);
//		
//		Product resultProduct = productService.save(mockProduct);
//		
//		Assertions.assertEquals(resultProduct, expectedProductResult);
//		Assertions.assertNotNull(resultProduct);
//	}
//	
//	@Test
//	@DisplayName("UpdateProduct - SUCCESS")
//	void testUpdateProduct() {
//		
//		Product mockProduct = new Product(1L,"Vanilla Ice Cream","A sweet frozen dessert made from milk", 1.10F);
//		Mockito.when(productRepository.updateProduct(mockProduct)).thenReturn(1);
//		int rowsAffected = productService.update(mockProduct);
//		Assertions.assertEquals(rowsAffected, 1);
//	}
//	
//	@Test
//	@DisplayName("Delete Product - SUCCESS")
//	void testDelet() {
//		
//		Mockito.when(productRepository.delteProductById(any())).thenReturn(true);
//		
//		boolean result = productService.delete(1L);
//		
//		Assertions.assertTrue(result);
//	}
}
