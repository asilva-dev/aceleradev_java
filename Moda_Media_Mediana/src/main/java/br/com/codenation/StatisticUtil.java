package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;

public class StatisticUtil {

	public static int average(int[] elements) {
		return (int ) Arrays.stream(elements).average().orElse(Double.NaN);
	}

	public static int mode(int[] elements) {
		HashMap<Integer, Integer> elementsCount = new HashMap<Integer, Integer>();
		int max = 1;
		int mode = 0;
		for (int i =0; i < elements.length; i++) {
			if (elementsCount.get(elements[i]) == null ) {
				elementsCount.put(elements[i], 1);
				if  (i == 0)
					mode = elements[i];
			} else {
				elementsCount.put(elements[i], elementsCount.get(elements[i]) + 1);
				if (elementsCount.get(elements[i]) > max) {
					max = elementsCount.get(elements[i]);
					mode = elements[i];
				}
			}
		}
		return mode;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int tamanho = elements.length;
		if (tamanho % 2 > 0)
			return elements[tamanho/2];
		return (elements[tamanho/2] + elements[tamanho/2-1]) /2;
	}
}