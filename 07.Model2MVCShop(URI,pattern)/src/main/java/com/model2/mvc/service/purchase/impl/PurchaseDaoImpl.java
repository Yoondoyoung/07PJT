package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public void setSession(SqlSession sqlSession) {
		System.out.println("Purchase Dao Impl Set Sql Session Called");
		this.sqlSession = sqlSession;
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Purchase Dao Impl Add Purchase");
		
		sqlSession.insert("PurchaseMapper.addPurchase",purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Purchase Dao Impl Add Purchase");
		
		return sqlSession.selectOne("PurchaseMapper.getPurchase",tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchase2", ProdNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search",search);
		map.put("buyer",buyerId);
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList",map);
		
		map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount", search));
		
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("search",search);
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getSaleList",map);
		
		map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount2", search));
		
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase",purchase);

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode",purchase);

	}

}
