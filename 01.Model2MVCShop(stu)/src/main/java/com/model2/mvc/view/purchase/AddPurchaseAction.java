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

//���� ��û
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
		
		purchaseVO.setBuyer(userVO);//�����ھ��̵� 
		purchaseVO.setPurchaseProd(productVO);//���Ź�ǰ���� ��������
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));//�������ּ� -> divyAddr
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));//�������� -> divyDate
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));//���ſ�û����
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));//���Ź��
		purchaseVO.setReceiverName(request.getParameter("receiverName"));//�������̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));//�����ڿ���ó
		purchaseVO.setTranCode("1");
		
		System.out.println(purchaseVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);//���������� �Է�
		
		request.setAttribute("purchaseVO", purchaseVO);
		request.setAttribute("productVO", productVO);
		return "redirect:/listPurchase.do";
	}

}
