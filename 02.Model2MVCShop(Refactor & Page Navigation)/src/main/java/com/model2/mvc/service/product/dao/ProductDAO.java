package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO product VALUES (seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)";
	      
	      PreparedStatement stmt = con.prepareStatement(sql);
	      stmt.setString(1, product.getProdName());
	      stmt.setString(2, product.getProdDetail());
	      stmt.setString(3, product.getManuDate().replace("-", ""));
	      stmt.setInt(4, product.getPrice());
	      stmt.setString(5, product.getFileName());
	      
	      stmt.executeUpdate();
		
		  con.close();
	}

	public Product findProduct(String prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return product;
	}

	public Map<String , Object> getProductList(Search search) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT \r\n"
				+ "p.prod_no, p.prod_name, p.price, p.reg_date, NVL(t.tran_status_code,0) \r\n"
				+ "FROM product p, transaction t \r\n"
				+ "WHERE t.prod_no(+) = p.prod_no";
		
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("")) {
				sql += " AND prod_no LIKE '%" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("1") &&  !search.getSearchKeyword().equals("")) {
				sql += " AND prod_name LIKE '%" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("2") &&  !search.getSearchKeyword().equals("")) {
				sql += " AND price LIKE '%" + search.getSearchKeyword()
				+ "%'";
			} 
		}
		sql += " order by PROD_NO";
		
		System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("UserDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage ???????? ?????? Query ????????
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			product.setProTranCode(rs.getString("NVL(t.tran_status_code,0)"));
			
			list.add(product);
		}
		
		//==> totalCount ???? ????
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage ?? ?????? ???? ???? List ????
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?, PRICE=?, IMAGE_File=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}
	
	// ?????? Page ?????? ???? ???? Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// ?????? currentPage Row ??  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}

}