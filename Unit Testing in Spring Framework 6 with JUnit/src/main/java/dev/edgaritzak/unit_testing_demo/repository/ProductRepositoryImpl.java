package dev.edgaritzak.unit_testing_demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.stereotype.Repository;

import dev.edgaritzak.unit_testing_demo.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	ProductRowMapper rowMapper = new ProductRowMapper();
	
	
	@Override
	public List<Product> findAll() {
		try {
			List<Product> products = jdbcTemplate.query("Select * from product;", rowMapper);
			return products;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Optional<Product> findById(Long id) {
		try {
			Product product = jdbcTemplate.queryForObject("Select * from product where id = ?;", rowMapper, id);
			return Optional.of(product);
		} catch (Exception ex) {
			return Optional.empty();
		}
	}

	@Override
	public Product createProduct(Product product) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setTableName("product");
		insert.usingGeneratedKeyColumns("id");
		
		MapSqlParameterSource source = new MapSqlParameterSource()
				.addValue("name", product.getName())
				.addValue("description", product.getDescription())
				.addValue("price", product.getPrice());
		
		try {
		Number key = insert.executeAndReturnKey(source);
		product.setId(key.longValue());
		return product;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int updateProduct(Product product) {
		String sql = "UPDATE product SET name=?, description=?, price=? WHERE id = ?;";
		try {
			return jdbcTemplate.update(sql,product.getName(),product.getDescription(),product.getPrice(), product.getId());
		} catch (Exception ex) {
			return -1;
		}
	}

	@Override
	public boolean delteProductById(Long productId) {
		String sql ="DELETE FROM product WHERE id = ?;";
		try {
			jdbcTemplate.update(sql,productId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
