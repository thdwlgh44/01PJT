package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

//구매 상태코드 수정 요청
public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("updateTranCode");
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode(request.getParameter("tranCode"));
		
		PurchaseService service= new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		String menu = request.getParameter("menu");
		System.out.println(menu);
		
		if(request.getParameter("con").equals("user")) {
			return "/listPurchase.do";
		} else {
			return "redirect:/listProduct.do?menu="+menu;
		}
		
	}

}
