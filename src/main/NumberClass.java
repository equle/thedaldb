package main;

import java.util.ArrayList;
import java.util.Random;

import dto.Thedaldto;

public class NumberClass {
	private Random rd = new Random();
	Thedaldto dto = new Thedaldto();
	
	public Thedaldto makenum() {
		int num1 = rd.nextInt(40);
		int num2 = rd.nextInt(40);
		if (num1>=num2) {
			dto.setNum1(num1);
			dto.setNum2(num2);
		}else {
			dto.setNum1(num2);
			dto.setNum2(num1);
		}
		return dto;
	}
	
	public Thedaldto userRog(String name, int point) {
		dto.setName(name);
		dto.setPoint(point);
		return dto;
	}
	
	
}
