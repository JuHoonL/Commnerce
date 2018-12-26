package com.biz.commerce.service;
/*
 * 매입매출 관련 처리를 위한 클래스를 선언
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommVO01;

public class CommService01 {
	
	//각 데이터를 관리할 List변수들을 선언
	List<CommVO01> comList;
	List<CommVO01> inoutList;
	List<CommVO01> pdList;
	
	//Text 파일관련 변수들을 선언
	String strInoutData;
	String strProductData;
	String strCommData;
	

	
	//IoService 클래스로 객체를 생성할 때 호출되는 생성자를 선언
	//이 클래스에서는 Text파일을 읽어서 매입매출 관련 업무를 수행 할 것이므로 생서앚에서는 Text파일의 경로정보를 매개변수로 받아서 변수에 저장하는 코드를 수행한다.
	public CommService01(String strInoutData, String strProductData, String strCommData) {

		//각 list들을 초기화해줌
		comList = new ArrayList();
		inoutList = new ArrayList();
		pdList = new ArrayList();
		
		//매개변수로 받은 Text관련 변수들을 이 클래스의 변수들에 저장 
		this.strInoutData = strInoutData;
		this.strProductData = strProductData;
		this.strCommData = strCommData;
	}
	
	
	
	public void readF() {
		//매입매출데이터.txt 파일에서 문자열을 읽어서 inoutlist에 저장하는 부분
		FileReader fr;
		BufferedReader br;
		
		try {
			//생성자에서 값이 할당된 ioFile의 내용을 참조하여 파일을 읽기 위하여 open하는코드
			//이코드는 작동되는 과정에서 불가항력적인 문제가 발생할 소지가 있으므로 반드시 try..catch문으로 감싸 주어야한다.
			fr = new FileReader(strInoutData);
			
			//FileReader로 open된 파일 정보를 Buffer에 연결하여 준다.
			//이 코드가 실행되면 BufferedReader는 일단 파일을 읽어서  메모리의 Buffer 영역에 저장해 둔다.
			br = new BufferedReader(fr);
			
			//무한반복분을 이용해서 Buffer에 저장된 파일내용에서 한줄씩 (문자열로) 읽어서 처리한다.
			while(true) {
				//Buffer에서 한줄을 읽어서 readerIn변수에 저장
				String readerIn = br.readLine();
				
				//만약 readerIn에 저장된 값이 null이면 모든 문자열을 다 읽었다는 것이므로 반복문을 종료한다.
				if(readerIn == null) break;
				
				//readerIn 문자열을 콜론(:)을 기준으로 분리해서 inoutListVO에 담고 inoutList에 추가하는 부분
				
				//String.split() 메서드를 사용하여 splitIn의 String배열에 담는 부분
				String[] splitIn = readerIn.split(":");
				
				//CommVO01을 초기화해서 vo라는 객체를 생성해줌
				CommVO01 vo = new CommVO01();
				
				//vo의 각 member 변수에 값을 저장(할당)
				vo.setStrDate(splitIn[0]);
				vo.setStrCcode(splitIn[1]);
				vo.setStrInOut(splitIn[2]);
				vo.setIntprice(Integer.valueOf(splitIn[3]));
				vo.setIntQuan(Integer.valueOf(splitIn[4]));
				
				//member변수가 setting된 vo를 iolist에 추가
				inoutList.add(vo);
				
			}
			
			//파일을 모두 사용(읽기)했으므로 안전하게 닫아준다.
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//상품정보.txt 파일에서 문자열을 읽어서 pdlist에 저장하는 부분		
		try {
			fr = new FileReader(strProductData);
			br = new BufferedReader(fr);
			
			while(true) {
				String readerIn = br.readLine();
				if(readerIn == null) break;
				String[] splitIn = readerIn.split(":");
				CommVO01 vo = new CommVO01();
				vo.setStrCcode(splitIn[0]);
				vo.setStrProduct(splitIn[1]);
				vo.setIntInPrice(Integer.valueOf(splitIn[3]));
				vo.setIntOutPrice(Integer.valueOf(splitIn[4]));
				
				pdList.add(vo);
				
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//매입매출데이터를 저장한 vo인 vio와 상품정보를 저장한 vo인 vp를 매개 변수로 받아 두 vo에 저장되어있는 상품코드가 일치하는지 여부를 확인하여 
	//일치하면 vo에 저장하여 vo를 리턴하고일치하지않으면 null값을 리턴하는 메서드
	public CommVO01 matchingF(CommVO01 vio, CommVO01 vp) {
		//매개변수로 받은  vio와 vp에서 각 상품코드를 비교해서 일치하면 vo에 저장
		if(vio.getStrCcode().equals(vp.getStrCcode())) {
			CommVO01 vo = new CommVO01();
			vo.setStrDate(vio.getStrDate());
			setInOut(vo, vio, vp);
			vo.setStrCcode(vio.getStrCcode());
			vo.setStrProduct(vp.getStrProduct());
			vo.setIntQuan(vio.getIntQuan());
			vo.setIntprice(vio.getIntprice());
			
			//각 member변수들에 값을 저장한 vo를 리턴하는 부분
			return vo;
			
		}
		//매개변수로 받은 vio와 vp의 상품코드가 일치하지않으면 null값을 리턴하는 부분
		return null;
	}
	
	
	//해당 member변수를 저장할 VO를 객체로 설정한 vo매개변수와 matching()메서드에서 메칭이 일치하였을 때의 
	//매입매출데이터를 저장한 vo인 vio와 상품정보를 저장한 vo인 vp를 매개 변수로 받아 매입 매출로 나눠서 매입일 경우 구분에 "매입"을 저장하고 매출일 경우 "매출"을 저장하는 메서드
	public void setInOut(CommVO01 vo, CommVO01 vio, CommVO01 vp) {
	
		//만약 매입매출데이터를 저장한 VO인 vio의 거래구분이 "1"인 경우 "매입"을 매개변수로 받은 vo의 strInOut변수에 저장하고
		//vio와 상품코드가 일치하는 vp의 VO의 intInPrice변수에서 매입가격을 불러오고 해당 vio에서 intQuan변수에서 수량을 불러와서 곱해서 매입총금액을 vo의 매입금액에 저장해준다. 
		if(vio.getStrInOut().equals("1")) {
			vo.setStrInOut("매입");
			vo.setIntInPrice(vio.getIntprice() * vio.getIntQuan());
			vo.setIntOutPrice(0);
			
		} else {
		//만약 매입매출데이터를 저장한 VO인 vio의 거래구분이 "1"인 아닌경우 "매출"을 매개변수로 받은 vo의 strInOut변수에 저장하고
		//vio와 상품코드가 일치하는 vp의 VO의 intOutPrice변수에서 매출가격을 불러오고 해당 vio에서 intQuan변수에서 수량을 불러와서 곱해서 매출총금액을 vo의 매출금액에 저장해준다.
			vo.setStrInOut("매출");
			vo.setIntInPrice(0);
			vo.setIntOutPrice(vio.getIntprice() * vio.getIntQuan());
		}
	}
	
	
	//상품코드가 일치하는 매입매출데이터를 저장한 vo인 vio와 상품정보를 저장한 vo인 vp를 찾아서 matchingF()메서드를 이용해서 vo를 리턴받아서 
	//리턴받은 vo가 null이 아니면 comList에 추가(저장)하는 메서드
	public void addcomList() {
		//inoutList에 저장되어있는 리스트의 개수만큼 for를 실행하는데 실행할 때에 CommVO01의 형태로 vo를 불러와서 vio라는 객체를 선언해서 사용
		for(CommVO01 vio : inoutList) {
			//pdList에 저장되어있는 리스트의 개수만큼 for를 실행하는데 실행할 때에 CommVO01의 형태로 vo를 불러와서 vp라는 객체를 선언해서 사용
			for(CommVO01 vp : pdList) {
				
				//매칭시켜 member변수들을 저장한 VO를 vo라는 객체로 저장
				CommVO01 vo = matchingF(vio, vp);
				
				//만약 리턴받은 vo가 null값이면 for를 다시 실행
				if(vo == null) continue;
				
				//vo가 null값이 아니면 vo를 comList에 추가(저장)
				comList.add(vo);
			}
		}
	}
	
	
	//inoutList에 저장되어 있는 매입매출정보 리스트를 console에 표시해서 잘 저장되어 있는지 검사하는 메서드 선언
	public void view() {
		//comList에 저장되어있는 리스트의 개수만큼 for를 실행하는데 실행할 때에 CommVO01의 형태로 vo를 불러와서 vo라는 객체를 선언해서 사용
		for(CommVO01 vo : comList) {
			System.out.println(vo);
		}
		//comList에 총 저장되어있는 리스트의 개수를 확인하기 위한 부분
		System.out.println(comList.size());
	}
	
	
	//완성된 comList를 strCommData라는 경로는 받아서 PrintWriter를 이용해서 txt파일 형식으로 출력(저장)하는 메서드
	public void printComm() {
		
		//PrintWriter를 pw라는 객체로 선언
		PrintWriter pw;
		
		try {
			//pw라고 선언했던 객체를 초기화를 진행하면서 매개변수로 strCommData를 받아서 해당경로를 지정해주는 부분
			pw = new PrintWriter(strCommData);
			
			//comList에 저장되어있는 리스트의 개수만큼 for를 실행하는데 실행할 때에 CommVO01의 형태로 vo를 불러와서 vo라는 객체를 선언해서 사용
			for(CommVO01 vo : comList) {
				//vo에 저장된 각 member변수들을 PrintWriter의 println을 사용하여 출력하는 부분 
				pw.println(
						vo.getStrDate() + ":" 
						+ vo.getStrInOut() + ":" 
						+ vo.getStrCcode() + ":"
						+ vo.getStrProduct() + ":"
						+ vo.getIntprice() + ":"
						+ vo.getIntQuan() + ":"
						+ vo.getIntInPrice() + ":"
						+ vo.getIntOutPrice());
			}
			
			//출력이 모두 끝나고 PrintWriter를 닫아줘야 함(닫지 않으면 계속해서 출력하기 때문에 오류가 남)
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//저장완료를 console에 출력하여 메서드가 완료되었음을 알 수 있게 함
		System.out.println("저장완료");
	}
}
