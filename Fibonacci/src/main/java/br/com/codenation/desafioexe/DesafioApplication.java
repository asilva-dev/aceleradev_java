package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {

		int num1 = 0;
		int num2 = 1;
		int numFibonacci = 0;
		List<Integer> list = new ArrayList<>();

		list.add(num1);
		list.add(num2);

		while (numFibonacci < 350){
			list.add(numFibonacci = num1 + num2);
			num1 = num2;
			num2 = numFibonacci;
		}
		return list;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}
}