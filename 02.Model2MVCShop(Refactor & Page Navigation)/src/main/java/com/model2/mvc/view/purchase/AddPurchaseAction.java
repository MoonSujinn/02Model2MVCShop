package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;



public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		
		Product pvo=new Product();
		pvo.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		System.out.println(pvo);
		
		User uvo=new User();
		uvo.setUserId(request.getParameter("userId"));
		
		
		System.out.println(uvo);
		
		Purchase purchase=new Purchase();
		purchase.setPurchaseProd(pvo);
		purchase.setBuyer(uvo);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("divyAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));		
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setTranCode("1");
	
		
		System.out.println(purchase);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		
		return "forward:/purchase/addPurchase.jsp";
	}
}