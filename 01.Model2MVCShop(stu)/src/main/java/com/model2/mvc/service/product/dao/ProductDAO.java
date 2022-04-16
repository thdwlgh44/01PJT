package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {

	public ProductDAO() {
	}
	
	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO product VALUES (seq_product_prod_no.NEXTVAL,?,?,to_char(to_date(?, 'yyyy-mm-dd'), 'yyyymmdd'),?,?,SYSDATE)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();
		
		con.close();
	}//insertProduct()
	
	public ProductVO findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		ProductVO productVO = null;
		while(rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();
		
		return productVO;
		
	}//findUser()
	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product ";
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
	}//getProductList()
	
	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE PRODUCT SET prod_name=?,prod_detail=?,manufacture_day=to_char(to_date(?, 'yyyy-mm-dd'), 'yyyymmdd'),"
				+"price=?,image_file=? WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}//updateProduct()
	
}//ProductDAO