package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

//구매목록 요청
public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			SearchVO searchVO = new SearchVO();
			
			HttpSession session = request.getSession();
			UserVO userVO = (UserVO)session.getAttribute("user");
			PurchaseVO vo = new PurchaseVO();
			
			int page=1;
			if(request.getParameter("page") != null)
				page=Integer.parseInt(request.getParameter("page"));
			
			searchVO.setPage(page);
			searchVO.setSearchCondition("0");
			searchVO.setSearchKeyword(userVO.getUserId());
			
			String pageUnit=getServletContext().getInitParameter("pageSize");
			searchVO.setPageUnit(Integer.parseInt(pageUnit));
			
			PurchaseService service = new PurchaseServiceImpl();
			Map<String, Object> map = service.getPurchaseList(searchVO);
			
			request.setAttribute("map", map);
			request.setAttribute("searchVO", searchVO);
			request.setAttribute("purchaseVO", vo);
		
			return "forward:/purchase/listPurchaseView.jsp";
	}

}
