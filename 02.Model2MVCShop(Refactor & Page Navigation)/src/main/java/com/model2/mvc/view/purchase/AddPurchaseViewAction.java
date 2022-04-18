package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.dao.ProductDAO;



public class AddPurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		//HttpSession session=request.getSession();
		//UserVO userVO=(UserVO)session.getAttribute("user");
			
		
		String prodNo=request.getParameter("prodNo");
		
		System.out.println("prodNo:"+prodNo);

		ProductDAO productDAO= new ProductDAO();
		
		Product product=productDAO.findProduct(prodNo);
		
		request.setAttribute("product", product);
		
		System.out.println("productÅ×½ºÆ®"+product);
		
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
}