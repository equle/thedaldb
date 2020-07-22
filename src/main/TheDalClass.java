package main;

import java.util.ArrayList;
import java.util.Scanner;

import dto.Thedaldto;

public class TheDalClass {
	Thedaldto dto = new Thedaldto();
	Scanner sc = new Scanner(System.in);
	
	public int bikyo(int dab) {
		//입력
		int point=0;
		int i = sc.nextInt();
		
		//만약 문제의 값과 입력한 값을 비교하여 
		// 맞으면 정답 틀리면 오답
		
		if (dab == i) {
			System.out.println("정답입니다.");
			point++;
		} else {
			System.out.println("오답입니다.");
		}
		return point;
		
	}
	
	public int add(Thedaldto td) {
		System.out.print(td.getDbnum1() + " + " + td.getDbnum2() + " = ");
		return td.getDbnum1() + td.getDbnum2();
	}
}
