package com.example.service;

import java.util.List;

import com.example.model.ProductDetails;

public interface ProductService {

	public ProductDetails saveProduct(ProductDetails productDetails);

	public ProductDetails searchProduct(Long id);

	public List<ProductDetails> searchAllProduct();

	public void deleteProduct(Long id);

	public void deleteAllProduct();

}
