package com.model2.mvc.view.purchase;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

//구매를 위한 화면요청
public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");
		System.out.println(userVO);
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductDAO prodcutDAO = new ProductDAO();
		ProductVO productVO = null;
		productVO = prodcutDAO.findProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}//class
