package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		System.out.println("업데이트트랜코드 시작");
		
		
		Purchase pcvo=new Purchase();
		Product pvo=new Product();
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String tranCode=request.getParameter("tranCode");
		
		System.out.println("UpdateTranCode"+prodNo);
		System.out.println("UpdateTranCode"+tranCode);
			
	
		pvo.setProdNo(prodNo);	
		pcvo.setPurchaseProd(pvo);
		
		pcvo.setTranCode(request.getParameter("tranCode"));
		
		System.out.println("pcvo 보자보자"+pcvo);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(pcvo);	
		
		
		return "forward:/listProduct.do?menu=manage";
		
	}
}