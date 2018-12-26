package com.biz.commerce.exec;

/*
 * 자바야 지금부터 CommExec01 이라는 클래스를 시작한다.
 */

import com.biz.commerce.service.CommService01;

public class CommExec01 {
	/*
	 * 만약 JVM에서 이 프로젝트를 실행할 때, CommExec01 클래스에서 진입점 메서드를 호출할 텐데 네가 응답하렴
	 */

	public static void main(String[] args) {

		//매입매출데이터의 경로를 strInoutData라는 String 변수로 받고, 상품정보의 경로를 strProductData라는 String 변수로 받고,
		//두 데이터를 결합하여 만든 매입매출정보.txt라는 파일을 저장할 경로를 strCommData로 받는다.
		String strInoutData = "src/com/biz/commerce/매입매출데이터.txt";
		String strProductData = "src/com/biz/commerce/상품정보.txt"; 
		String strCommData = "src/com/biz/commerce/매입매출정보01.txt";
		
		//CommService01클래스의 메서드들을 사용하기 위해서 CommService01클래스를 cs라는 변수로 지정하여 초기화 및 설정		
		CommService01 cs = new CommService01(strInoutData, strProductData, strCommData);
		
		//생성자 메서드로 받은 파일들을 읽어오는 과정
		cs.readF();
		
		//comList에 매칭하여 완성한 VO를 추가하는 과정의 메서드를 사용하는 과정
		cs.addcomList();
		
		//cs.view()를 실행해서 inout리스트를 확인
		//cs.view();
		
		//완성된 comList를 PrintWriter를 사용하여 txt형태로 출력하는 메서드를 사용하는 과정
		cs.printComm();
	}

}
