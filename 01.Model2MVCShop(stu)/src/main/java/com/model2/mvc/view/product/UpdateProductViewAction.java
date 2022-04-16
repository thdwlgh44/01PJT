package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		
		ProductVO vo=service.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		request.setAttribute("vo", vo);
		
		System.out.println(vo);
		return "forward:/product/updateProductView.jsp";
		
//		int prodNo = Integer.parseInt(request.getParameter("prodNo")); 
//		
//		ProductService service = new ProductServiceImpl();
//		ProductVO productVO = service.getProduct(prodNo);
//		
//		request.setAttribute("productVO", productVO);
//		
//		return "forward:/product/updateProductView.jsp";
	}
}
