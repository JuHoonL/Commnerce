package com.biz.commerce.vo;

/*
 * 매입매출데이터.txt 파일로부터 데이터를 읽어서 List를 만들때 사용할 데이터클래스(VO) 선언
 */
public class CommVO01 {

	/*
	 * 거래일자:상품코드:거래구분:단가:수량 형식의 데이터를 읽어서 각 member 변수에 저장하고,
	 * 상품코드:상품명:과세구분:매입단가:매출단가:바코드 형식의 데이터를 읽어서 상품코드와 상품명을 member 변수에 저장 
	 */
	String strDate;			//거래일자
	String strCcode;		//상품코드
	String strProduct;		//상품명
	String strInOut;		//거래구분
	int intprice;		//단가
	int intQuan;			//수량
	
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
	public int getIntprice() {
		return intprice;
	}
	public void setIntprice(int intprice) {
		this.intprice = intprice;
	}
	public int getIntQuan() {
		return intQuan;
	}
	public void setIntQuan(int intQuan) {
		this.intQuan = intQuan;
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
		return "CommVO [strDate=" + strDate + ", strCcode=" + strCcode + ", strProduct=" + strProduct + ", strInOut=" + strInOut + ", intprice="
				+ intprice + ", intQuan=" + intQuan + ", intInPrice=" + intInPrice + ", intOutPrice=" + intOutPrice
				+ "]";
	}

	
	
}
