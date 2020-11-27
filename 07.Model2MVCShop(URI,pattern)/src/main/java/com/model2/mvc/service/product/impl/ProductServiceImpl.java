package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	//Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;

	public ProductServiceImpl() {
		System.out.println("Product Service Impl Constructor");
		// TODO Auto-generated constructor stub
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Add Product Service Impl");
		productDao.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Product Service Impl");
		return productDao.getProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Product List Service Impl");
		
		return productDao.getProductList(search);
		
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update Product Service Impl");
		productDao.updateProduct(product);
	}

}
