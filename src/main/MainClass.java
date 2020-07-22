package main;

import java.util.ArrayList;
import java.util.Scanner;

import db.DBClass;
import dto.Thedaldto;

public class MainClass {

	public static void main(String[] args) {
		NumberClass nb = new NumberClass();
		Scanner scan = new Scanner(System.in);
		TheDalClass tdc = new TheDalClass();

//		DBClass.conTest(); // test

//		 숫자 셋팅 함수 10번 처음 한번만 실행
		DBClass.numlist_delete();
		for (int i = 0; i < 10; i++) {
			Thedaldto tddto = nb.makenum();
			DBClass.insert(tddto.getNum1(), tddto.getNum2());
		}
		ArrayList<Thedaldto> list= DBClass.numlist_select(); //list에 db에서 가져온 숫자를 입력
		
		// 게임 결과 넣기
		for (;;) {
			
			System.out.println("1. 게임하기");
			System.out.println("2. 유저보기");
			System.out.print("3. 그만하기 >> ");
			int i = scan.nextInt();
			if(i==1) {
				int point=0;
				for(Thedaldto td : list) {
					point+=tdc.bikyo(tdc.add(td));
				}
				System.out.println("게임이 끝났습니다.");
				System.out.println("－－－－－－－－－－－－－－－－");
				// rank 기록
				System.out.print("이름을 입력해 주세요. >>");
				String name = scan.next();
				Thedaldto tddto2 = nb.userRog(name, point);
				DBClass.rank_insert(tddto2.getName(), tddto2.getPoint());
				
			}
			else if(i==2) {
				System.out.println("＋－－－－－－－－－－－－－－－－－－－－－－＋");
				DBClass.showrank();
				System.out.println("＋－－－－－－－－－－－－－－－－－－－－－－＋");
			}
			else {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}

}
