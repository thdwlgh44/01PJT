package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//상품 등록 요청
public class AddProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));

		ProductService service = new ProductServiceImpl();
		service.addProduct(productVO);
		
		return "redirect:/product/addProductView.jsp";
	}

}
