package dev.edgaritzak.unit_testing_demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;

import dev.edgaritzak.unit_testing_demo.models.Product;
import dev.edgaritzak.unit_testing_demo.services.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@MockitoBean
	private ProductService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("GET - FIND BY ID - /product/{id} - FOUND")
	void testFindbyId () throws Exception {
		Product mockProduct = new Product(1L,"Orange Juice", "100 percent orange juice 1gl", 3.50F);
		doReturn(Optional.of(mockProduct)).when(service).findById(1L);
		
		mockMvc.perform(get("/product/{id}", 1))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Orange Juice")))
			.andExpect(jsonPath("$.description", is("100 percent orange juice 1gl")))
			.andExpect(jsonPath("$.price", is(3.50)))
		;
	}
	
	@Test
	@DisplayName("GET - /product/{id} - FIND BY ID - NOT FOUND")
	void testFailFindById() throws Exception {
		doReturn(Optional.empty()).when(service).findById(1L);
		
		mockMvc.perform(get("/product/{id}",1))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("POST - /product/ - NEW PRODUCT - CREATED")
	void testCreateProduct() throws Exception {
		
		Product postProduct = new Product();
		postProduct.setName("Frozen Pizza 14.3 oz");
		postProduct.setDescription("Jack's Pepperoni, Original Thin Crust Pizza");
		postProduct.setPrice(4.99F);
		Product mockProduct = new Product(1l, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 4.99F);
		
		doReturn(mockProduct).when(service).save(any());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(postProduct);
		
		mockMvc.perform(post("/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
		
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Frozen Pizza 14.3 oz")))
			.andExpect(jsonPath("$.description", is("Jack's Pepperoni, Original Thin Crust Pizza")))
			.andExpect(jsonPath("$.price", is(4.99)))
			;
	}
	
	@Test
	@DisplayName("PUT - /product UDPATE PRODUCT - SUCCESS")
	void testNewProductFail() throws Exception {
		
		
		Product putProduct = new Product(1L, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 6.66F);
		Product mockProduct = new Product(1L, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 4.99F);
		
		doReturn(Optional.of(mockProduct)).when(service).findById(1L);
		doReturn(1).when(service).update(any());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(putProduct);
		
		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON).content(json))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Frozen Pizza 14.3 oz")))
		.andExpect(jsonPath("$.description", is("Jack's Pepperoni, Original Thin Crust Pizza")))
		.andExpect(jsonPath("$.price", is(6.66)))
		;
	}
	
	@Test
	@DisplayName("PUT - /product UDPATE PRODUCT - NOT FOUND")
	void testUpdateNotFound() throws Exception {
		
		Product mockProduct = new Product(1L, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 4.99F);
		
		doReturn(Optional.empty()).when(service).findById(1L);
		doReturn(0).when(service).update(any());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(mockProduct);
		mockMvc.perform(put("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
					.andExpect(status().isNotFound())
					;
	}
	
	@Test
	@DisplayName("DELETE - /product UDPATE PRODUCT - SUCCESS")
	void testDeleteProduct() throws Exception{
		
		Product mockProduct = new Product(1L, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 4.99F);
		doReturn(Optional.of(mockProduct)).when(service).findById(any());
		doReturn(true).when(service).delete(1L);
		
		mockMvc.perform(delete("/product/{id}", 1))
			.andExpect(status().isOk())
			;
	}
	
	@Test
	@DisplayName("DELETE - /product UDPATE PRODUCT - NOT FOUND")
	void testDeleteNotFound() throws Exception{
		
		doReturn(Optional.empty()).when(service).findById(any());
		
		mockMvc.perform(delete("/product/{id}",1L))
			.andExpect(status().isNotFound())
			;
	}
	
	@Test
	@DisplayName("DELETE - /product UDPATE PRODUCT - INTERNAL SERVER ERROR")
	void testDeleteInternalServerError() throws Exception{
		
		Product mockProduct = new Product(1L, "Frozen Pizza 14.3 oz","Jack's Pepperoni, Original Thin Crust Pizza", 4.99F);

		doReturn(Optional.of(mockProduct)).when(service).findById(any());
		doReturn(false).when(service).delete(any());
		
		mockMvc.perform(delete("/product/{id}", 1))
			.andExpect(status().isInternalServerError())
			;
	}
}
