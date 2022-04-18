package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseService;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO=new PurchaseDAO();
	}
	
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
	    return purchaseDAO.findPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		return purchaseDAO.getPurchaseList(search,userId);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		return purchaseDAO.getSaleList(search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
		
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
		
	}


}