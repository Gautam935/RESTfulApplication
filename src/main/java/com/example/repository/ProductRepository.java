package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ProductDetails;

@Repository
public interface ProductRepository extends CrudRepository<ProductDetails, Long> {

	public ProductDetails save(ProductDetails productDetails);

	public ProductDetails findOne(Long id);

	public void delete(Long id);

	public void deleteAll();

	public List<ProductDetails> findAll();
}
