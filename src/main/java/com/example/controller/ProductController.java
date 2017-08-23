package com.example.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDTO;
import com.example.model.ProductDetails;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	final static Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {

		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductName(productDTO.getProductName());
		productDetails.setProductDescription(productDTO.getProductDescription());
		productDetails.setProductPrice(productDTO.getProductPrice());

		productService.saveProduct(productDetails);
		logger.debug("Added::" + productDTO);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/getProduct/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDetails> getProduct(@PathVariable("id") Long id) {

		ProductDetails product = productService.searchProduct(id);
		logger.info("Found Employee::" + product);
		return new ResponseEntity<ProductDetails>(product, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDetails>> getAllProduct() {

		List<ProductDetails> productDetails = productService.searchAllProduct();
		return new ResponseEntity<List<ProductDetails>>(productDetails, HttpStatus.OK);

	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProduct(@RequestParam("id") Long id, @RequestBody ProductDTO productDTO) {

		ProductDetails currentUserId = productService.searchProduct(id);
		if (currentUserId == null) {
			logger.info("Unable to update. User with id " + id + " not found");
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
		currentUserId.setProductName(productDTO.getProductName());
		currentUserId.setProductDescription(productDTO.getProductDescription());
		currentUserId.setProductPrice(productDTO.getProductPrice());

		productService.saveProduct(currentUserId);
		return new ResponseEntity<String>("User Updated Successfully", HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@RequestParam("id") Long id) {

		ProductDetails avail_Id = productService.searchProduct(id);
		if (avail_Id == null) {
			logger.info("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.NOT_FOUND);
		}
		productService.deleteProduct(id);
		;

		return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.GONE);

	}

	@RequestMapping(value = "/deleteAllProduct", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllProduct() {
		productService.deleteAllProduct();
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.GONE);

	}
}
