package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
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
		
		UserVO userVO = new UserVO();
		ProductVO productVO = new ProductVO();
		PurchaseVO purchaseVO = new PurchaseVO();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		productVO.setProdNo(prodNo);//��ǰ��ȣ
		purchaseVO.setBuyer(userVO);//�����ھ��̵�
		purchaseVO.setPaymentOption("paymentOption");//���Ź��
		purchaseVO.setReceiverName("receiverName");//�������̸�
		purchaseVO.setReceiverPhone("receiverPhone");//�����ڿ���ó
		purchaseVO.setDivyAddr("receiverAddr");//�������ּ�
		purchaseVO.setDivyRequest("receiverRequest");//���ſ�û����
		purchaseVO.setDivyDate("receiverDate");//��������
		
		PurchaseService purService = new PurchaseServiceImpl();
		purService.addPurchase(purchaseVO);//���������� �Է�
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
