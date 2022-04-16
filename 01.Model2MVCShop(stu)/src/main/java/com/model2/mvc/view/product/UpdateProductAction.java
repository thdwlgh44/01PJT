package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

//상품 정보 수정 요청
public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ProductVO productVO = new ProductVO();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO); //상품정보를 업데이트 시킴
		
		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
}
