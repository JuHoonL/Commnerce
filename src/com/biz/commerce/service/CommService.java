package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommVO;

public class CommService {

	List<CommVO> comList;
	List<CommVO> inoutList;
	List<CommVO> pdList;
	
	String strInoutData;
	String strProductData;
	String strCommData;
	
	public CommService(String strInoutData, String strProductData, String strCommData) {
		comList = new ArrayList();
		inoutList = new ArrayList();
		pdList = new ArrayList();
		this.strInoutData = strInoutData;
		this.strProductData = strProductData;
		this.strCommData = strCommData;
	}
	
	
	public void readF() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(strInoutData);
			br = new BufferedReader(fr);
			
			while(true) {
				String readerIn = br.readLine();
				if(readerIn == null) break;
				String[] splitIn = readerIn.split(":");
				CommVO vo = new CommVO();
				vo.setStrDate(splitIn[0]);
				vo.setStrCcode(splitIn[1]);
				vo.setStrInOut(splitIn[2]);
				vo.setStrprice(splitIn[3]);
				vo.setStrQuan(splitIn[4]);
				
				inoutList.add(vo);
				
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			fr = new FileReader(strProductData);
			br = new BufferedReader(fr);
			
			while(true) {
				String readerIn = br.readLine();
				if(readerIn == null) break;
				String[] splitIn = readerIn.split(":");
				CommVO vo = new CommVO();
				vo.setStrCcode(splitIn[0]);
				vo.setStrProduct(splitIn[1]);
				vo.setIntInPrice(Integer.valueOf(splitIn[3]));
				vo.setIntOutPrice(Integer.valueOf(splitIn[4]));
				
				pdList.add(vo);
				
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void matchingF() {
		for(CommVO vio : inoutList) {
			for(CommVO vp : pdList) {
				if(vio.getStrCcode().equals(vp.getStrCcode())) {
					CommVO vo = new CommVO();
					vo.setStrDate(vio.getStrDate());
					
					if(vio.getStrInOut().equals("1")) {
						vo.setStrInOut("매입");
						vo.setIntInPrice(vp.getIntInPrice() * Integer.valueOf(vio.getStrQuan()));
					} else {
						vo.setStrInOut("매출");
						vo.setIntOutPrice(vp.getIntOutPrice() * Integer.valueOf(vio.getStrQuan()));
					}
					
					vo.setStrCcode(vio.getStrCcode());
					vo.setStrProduct(vp.getStrProduct());
					vo.setStrQuan(vio.getStrQuan());
					vo.setStrprice(vio.getStrprice());
					
					comList.add(vo);
				}
			}
		}
	}
	
	public void view() {
		for(CommVO vo : comList) {
			System.out.println(vo);
		}
		System.out.println(comList.size());
	}
	
	public void printComm() {
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(strCommData);
			
			for(CommVO vo : comList) {
				pw.println(
						vo.getStrDate() + ":" 
						+ vo.getStrInOut() + ":" 
						+ vo.getStrCcode() + ":"
						+ vo.getStrProduct() + ":"
						+ vo.getStrprice() + ":"
						+ vo.getStrQuan() + ":"
						+ vo.getIntInPrice() + ":"
						+ vo.getIntOutPrice());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("저장완료");
	}
}
