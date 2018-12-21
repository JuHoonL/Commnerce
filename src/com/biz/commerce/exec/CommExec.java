package com.biz.commerce.exec;

import com.biz.commerce.service.CommService;

public class CommExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strInoutData = "src/com/biz/commerce/매입매출데이터.txt";
		String strProductData = "src/com/biz/commerce/상품정보.txt"; 
		String strCommData = "src/com/biz/commerce/매입매출정보.txt";
		
		CommService cs = new CommService(strInoutData, strProductData, strCommData);
		
		cs.readF();
		cs.matchingF();
//		cs.view();
		cs.printComm();
	}

}
