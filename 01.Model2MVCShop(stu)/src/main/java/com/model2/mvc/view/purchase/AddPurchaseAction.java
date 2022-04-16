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
		
		//�������� ã��
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");
		
		ProductService prodService = new ProductServiceImpl();
		ProductVO productVO = prodService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO.setBuyer(userVO);//�����ھ��̵� 
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));//���Ź��
		purchaseVO.setReceiverName(request.getParameter("receiverName"));//�������̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));//�����ڿ���ó
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));//�������ּ�
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));//���ſ�û����
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));//��������
		purchaseVO.setTranCode("1");
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);//���������� �Է�
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
