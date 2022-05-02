package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
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
		PurchaseVO purchaseVO = new PurchaseVO();
		
		HttpSession session = request.getSession(true);
		UserVO userVO = (UserVO)session.getAttribute("user");
		
		ProductService prodService = new ProductServiceImpl();
		ProductVO productVO = prodService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		System.out.println(productVO);
		
		purchaseVO.setBuyer(userVO);//구매자아이디 
		purchaseVO.setPurchaseProd(productVO);//구매물품정보 가져오기
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));//구매자주소 -> divyAddr
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));//배송희망일 -> divyDate
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));//구매요청사항
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));//구매방법
		purchaseVO.setReceiverName(request.getParameter("receiverName"));//구매자이름
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));//구매자연락처
		purchaseVO.setTranCode("1");
		
		System.out.println(purchaseVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);//구매정보를 입력
		
		request.setAttribute("purchaseVO", purchaseVO);
		request.setAttribute("productVO", productVO);
		return "redirect:/listPurchase.do";
	}

}
