package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		purchaseDao=new PurchaseDao();
	}
	
	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.insertPurchase(purchaseVO);
	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return purchaseDao.findPurchase(tranNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO) throws Exception {
		return purchaseDao.getPurchaseList(searchVO);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		return purchaseDao.getSaleList(searchVO);
	}

	@Override
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		return purchaseDao.updatePurchase(purchaseVO);
	}

	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
	}
}