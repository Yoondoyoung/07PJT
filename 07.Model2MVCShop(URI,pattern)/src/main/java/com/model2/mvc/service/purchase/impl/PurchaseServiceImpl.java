package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	//Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		System.out.println("Add Pruchase Impl");
		System.out.println(purchase);
		purchaseDao.addPurchase(purchase);
		
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Purchase Impl");
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Purchase2 Impl");
		return purchaseDao.getPurchase2(ProdNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search searchVO, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Purchase List Impl");
		return purchaseDao.getPurchaseList(searchVO, buyerId);
	}

	@Override
	public Map<String, Object> getSaleList(Search searchVO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Get Sale List Impl");
		return purchaseDao.getSaleList(searchVO);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update Purchase Impl");
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update TranCode Impl");
		purchaseDao.updateTranCode(purchase);
	}

}
