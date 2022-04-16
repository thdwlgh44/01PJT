package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

//구매 요청
public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		UserVO userVO = new UserVO();
		ProductVO productVO = new ProductVO();
		PurchaseVO purchaseVO = new PurchaseVO();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		productVO.setProdNo(prodNo);//물품번호
		purchaseVO.setBuyer(userVO);//구매자아이디
		purchaseVO.setPaymentOption("paymentOption");//구매방법
		purchaseVO.setReceiverName("receiverName");//구매자이름
		purchaseVO.setReceiverPhone("receiverPhone");//구매자연락처
		purchaseVO.setDivyAddr("receiverAddr");//구매자주소
		purchaseVO.setDivyRequest("receiverRequest");//구매요청사항
		purchaseVO.setDivyDate("receiverDate");//배송희망일
		
		PurchaseService purService = new PurchaseServiceImpl();
		purService.addPurchase(purchaseVO);//구매정보를 입력
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
