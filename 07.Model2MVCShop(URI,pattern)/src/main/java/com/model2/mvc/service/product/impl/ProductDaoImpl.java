package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;

	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("productDaoImpl Called");
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("Product Dao Impl Set Sql Session Called");
		this.sqlSession = sqlSession;
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Product Dao Impl Add Product");
		return sqlSession.insert("ProductMapper.addProduct",product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Product Dao Impl Get Product Start");
		return (Product)sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("Product Dao Impl Get Product List Start");
		int totalCount = sqlSession.selectOne("ProductMapper.getTotalCount",search);
		
		List<Object> list = sqlSession.selectList("ProductMapper.getProductList", search);
		map.put("totalCount", new Integer(totalCount));
		map.put("list",list);
		return map;
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Product Dao Impl Update Product Start");
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public int getTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}
