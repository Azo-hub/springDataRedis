package com.springDataRedisApi.Repository;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.springDataRedisApi.Entity.Product;

/**
 * @author Azo-hub
 * @github (https://github.com/Azo-hub)
 * @since 2020
 */
@Repository
public class ProductRepo {
	private static final String HASH_KEY = "product";
	private RedisTemplate template;
	
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		
		return product;
		
	}
	
	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Product findProductById(Long id) {
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}

	public String deleteProduct(Long id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "product removed!";
	}
}
