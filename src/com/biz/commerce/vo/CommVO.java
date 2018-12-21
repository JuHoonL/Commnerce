package com.biz.commerce.vo;

public class CommVO {

	String strDate;
	String strCcode;
	String strProduct;
	String strInOut;
	String strprice;
	String strQuan;
	
	int intInPrice;
	int intOutPrice;

	
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrCcode() {
		return strCcode;
	}
	public void setStrCcode(String strCcode) {
		this.strCcode = strCcode;
	}
	public String getStrProduct() {
		return strProduct;
	}
	public void setStrProduct(String strProduct) {
		this.strProduct = strProduct;
	}
	public String getStrInOut() {
		return strInOut;
	}
	public void setStrInOut(String strInOut) {
		this.strInOut = strInOut;
	}
	public String getStrprice() {
		return strprice;
	}
	public void setStrprice(String strprice) {
		this.strprice = strprice;
	}
	public String getStrQuan() {
		return strQuan;
	}
	public void setStrQuan(String strQuan) {
		this.strQuan = strQuan;
	}
	public int getIntInPrice() {
		return intInPrice;
	}
	public void setIntInPrice(int intInPrice) {
		this.intInPrice = intInPrice;
	}
	public int getIntOutPrice() {
		return intOutPrice;
	}
	public void setIntOutPrice(int intOutPrice) {
		this.intOutPrice = intOutPrice;
	}

	
	public String toString() {
		return "CommVO [strDate=" + strDate + ", strCcode=" + strCcode + ", strProduct=" + strProduct + ", strInOut=" + strInOut + ", strprice="
				+ strprice + ", strQuan=" + strQuan + ", intInPrice=" + intInPrice + ", intOutPrice=" + intOutPrice
				+ "]";
	}

	
	
}
