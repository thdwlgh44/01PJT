package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDao {

	public PurchaseDao() {
	}
	
	//구매를 위한 DBMS 수행
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES (seq_transaction_tran_no.NEXTVAL,?,?,"
				+ "?,?,?,?,?,?,SYSDATE,SYSDATE)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(1, purchaseVO.getBuyer().getUserId());
		stmt.setString(2, purchaseVO.getPaymentOption());//payment_option
		stmt.setString(3, purchaseVO.getReceiverName());//receiver_name
		stmt.setString(4, purchaseVO.getReceiverPhone());//receiver_phone
		stmt.setString(5, purchaseVO.getDivyAddr());//divy_addr
		stmt.setString(6, purchaseVO.getDivyRequest());//divy_request
		stmt.setString(7, purchaseVO.getTranCode());//tran_status_code
		
		stmt.executeUpdate();
		
		con.close();
	}//insertPurchase()
	
	//구매정보 상세 조회를 위한 DBMS 수행
	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		PurchaseVO purchaseVO = null;
		UserVO userVO = null;
		while(rs.next()) {
			purchaseVO = new PurchaseVO();
			purchaseVO.setBuyer((UserVO) rs.getObject("buyer_id"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setDivyAddr(rs.getString("dlvy_addr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setOrderDate(rs.getDate("order_date"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
		}
		
		con.close();
		
		return purchaseVO;
		
	}//findPurchase()
	
	//구매목록 보기를 위한 DBMS 수행
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction ";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no='"+searchVO.getSearchKeyword()
					+"'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name='"+searchVO.getSearchKeyword()
				+"'";
			} else {
				sql += " WHERE price='"+searchVO.getSearchKeyword()
				+"'";
			}
		}
		sql += " ORDER BY prod_no";
		
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();
		
			rs.last(); //Moves the cursor to the last row in this ResultSet object.
			int total = rs.getRow();
			System.out.println("로우의 수:" + total);

			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("count", new Integer(total));

			//이 ResultSet 개체의 지정된 행 번호로 커서를 이동합니다.
			rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			ArrayList<ProductVO> list = new ArrayList<ProductVO>();
			if (total > 0) {
				for (int i=0; i<searchVO.getPageUnit(); i++) {
					ProductVO vo = new ProductVO(); 
					vo.setProdNo(rs.getInt("PROD_NO"));
					vo.setProdName(rs.getString("PROD_NAME"));
					vo.setProdDetail(rs.getString("PROD_DETAIL"));
					vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
					vo.setPrice(rs.getInt("PRICE"));
					vo.setFileName(rs.getString("IMAGE_FILE"));
					vo.setRegDate(rs.getDate("REG_DATE"));
					
					list.add(vo);
					if(!rs.next())
						break;
				}
			}
				System.out.println("list.size() : "+ list.size());
				map.put("list", list);
				System.out.println("map().size() : "+ map.size());
				
			con.close();
			
			return map;
	}//getProductList()
	
	//판매목록 보기를 위한 DBMS 수행
	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction ";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no='"+searchVO.getSearchKeyword()
				+"'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name='"+searchVO.getSearchKeyword()
				+"'";
			} else {
				sql += " WHERE price='"+searchVO.getSearchKeyword()
				+"'";
			}
		}
		sql += " ORDER BY prod_no";
		
		PreparedStatement stmt = 
				con.prepareStatement(	sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();
		
		rs.last(); //Moves the cursor to the last row in this ResultSet object.
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		//이 ResultSet 개체의 지정된 행 번호로 커서를 이동합니다.
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i=0; i<searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO(); 
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				
				list.add(vo);
				if(!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
		
		con.close();
		
		return map;
	}//getSaleList()
	
	//구매정보 수정을 위한 DBMS 수행
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET buyer_id=?,payment_option=?,receiver_name=?,"
				+"receiver_phone=?,dlvy_addr=?,dlvy_request=?,dlvy_date=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getBuyer().getUserId());//구매자아이디
		stmt.setString(2, purchaseVO.getPaymentOption());//구매방법
		stmt.setString(3, purchaseVO.getReceiverName());//구매자이름
		stmt.setString(4, purchaseVO.getReceiverPhone());//구매자연락처
		stmt.setString(5, purchaseVO.getDivyAddr());//구매자주소
		stmt.setString(6, purchaseVO.getDivyRequest());//구매요청사항
		stmt.setString(7, purchaseVO.getDivyDate());//배송희망일자
		stmt.setInt(8, purchaseVO.getTranNo());//구매번호
		stmt.executeUpdate();
		
		con.close();
		return purchaseVO;
	}//updatePurchase()
	
	/*//구매 상태 코드 수정을 위한 DBMS 수행
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET prod_name=?,prod_detail=?,manufacture_day=to_char(to_date(?, 'yyyy-mm-dd'), 'yyyymmdd'),"
				+"price=?,image_file=? WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}//updateTranCode()*/
}
