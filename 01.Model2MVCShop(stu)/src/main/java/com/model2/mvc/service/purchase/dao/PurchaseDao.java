package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDao {

	public PurchaseDao() {
	}
	
	//���Ÿ� ���� DBMS ����
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES (seq_transaction_tran_no.NEXTVAL,?,?,"
				+ "?,?,?,?,?,?,SYSDATE,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());//payment_option
		stmt.setString(4, purchaseVO.getReceiverName());//receiver_name
		stmt.setString(5, purchaseVO.getReceiverPhone());//receiver_phone
		stmt.setString(6, purchaseVO.getDivyAddr());//divy_addr
		stmt.setString(7, purchaseVO.getDivyRequest());//divy_request
		stmt.setString(8, purchaseVO.getTranCode());//tran_status_code
		stmt.setString(9, purchaseVO.getDivyDate());//divy_date
		
		stmt.executeUpdate();
		
		con.close();
	}//insertPurchase()
	
	//�������� �� ��ȸ�� ���� DBMS ����
	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		PurchaseVO purchaseVO = null;
		ProductVO productVO = null;
		UserVO userVO = null;
		
		String sql = "SELECT * FROM users u, product p, transaction t"
				+ " WHERE u.user_id=t.buyer_id AND p.prod_no=t.prod_no AND t.tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			userVO = new UserVO();
			userVO.setUserId(rs.getString("user_id"));
			userVO.setUserName(rs.getString("user_name"));
			userVO.setPassword(rs.getString("password"));
			userVO.setRole(rs.getString("role"));
			userVO.setSsn(rs.getString("ssn"));
			userVO.setPhone(rs.getString("cell_phone"));
			userVO.setAddr(rs.getString("addr"));
			userVO.setEmail(rs.getString("email"));
			userVO.setRegDate(rs.getDate("reg_date"));
			
			productVO = new ProductVO();
			productVO.setFileName(rs.getString("image_file"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setRegDate(rs.getDate("reg_date"));
			
			purchaseVO = new PurchaseVO();
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("dlvy_addr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setTranNo(tranNo);
			purchaseVO.setOrderDate(rs.getDate("order_date"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
		}
		
		con.close();
		return purchaseVO;
		
		
	}//findPurchase()
	
	//���Ÿ�� ���⸦ ���� DBMS ����
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction ";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE buyer_id='"+searchVO.getSearchKeyword()
					+"'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name='"+searchVO.getSearchKeyword()
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
			System.out.println("�ο��� ��:" + total);

			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("count", new Integer(total));

			//�� ResultSet ��ü�� ������ �� ��ȣ�� Ŀ���� �̵��մϴ�.
			rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			List<PurchaseVO> list = new ArrayList<PurchaseVO>();
			if (total > 0) {
				for (int i=0; i<searchVO.getPageUnit(); i++) {
					PurchaseVO purchaseVO = new PurchaseVO();
					ProductVO productVO = new ProductVO();
					UserVO userVO = new UserVO();
					
					productVO.setProdNo(rs.getInt("prod_no"));
					purchaseVO.setPurchaseProd(productVO);
					userVO.setUserId(rs.getString("buyer_id"));
					purchaseVO.setBuyer(userVO);
					
					purchaseVO.setDivyAddr(rs.getString("dlvy_addr"));
					purchaseVO.setDivyDate(rs.getString("dlvy_date"));
					purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
					purchaseVO.setPaymentOption(rs.getString("payment_option"));
					purchaseVO.setReceiverName(rs.getString("receiver_name"));
					purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
					purchaseVO.setTranCode("tran_status_code");
					purchaseVO.setTranNo(rs.getInt("tran_no"));
					
					list.add(purchaseVO);
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
	
	//�ǸŸ�� ���⸦ ���� DBMS ����
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
		System.out.println("�ο��� ��:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		//�� ResultSet ��ü�� ������ �� ��ȣ�� Ŀ���� �̵��մϴ�.
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
	
	//�������� ������ ���� DBMS ����
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET payment_option=?,receiver_name=?,"
				+"receiver_phone=?,dlvy_addr=?,dlvy_request=?,dlvy_date=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getPaymentOption());//���Ź��
		stmt.setString(2, purchaseVO.getReceiverName());//�������̸�
		stmt.setString(3, purchaseVO.getReceiverPhone());//�����ڿ���ó
		stmt.setString(4, purchaseVO.getDivyAddr());//�������ּ�
		stmt.setString(5, purchaseVO.getDivyRequest());//���ſ�û����
		stmt.setString(6, purchaseVO.getDivyDate());//����������
		stmt.setInt(7, purchaseVO.getTranNo());//���Ź�ȣ
		stmt.executeUpdate();
		
		con.close();
		return purchaseVO;
	}//updatePurchase()
	
	//���� ���� �ڵ� ������ ���� DBMS ����
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code=? "
				+"WHERE prod_no=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, purchaseVO.getTranCode());
		pstmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		
		pstmt.executeUpdate();
		
		con.close();
	}//updateTranCode()
}
