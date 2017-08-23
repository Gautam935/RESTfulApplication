package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.ProductDetails;
import com.example.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ProductDetails saveProduct(ProductDetails productDetails) {

		return productRepository.save(productDetails);
	}

	@Override
	public ProductDetails searchProduct(Long id) {

		return productRepository.findOne(id);
	}

	@Override
	public List<ProductDetails> searchAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.delete(id);

	}

	@Override
	public void deleteAllProduct() {
		productRepository.deleteAll();

	}

}
