package br.com.codenation.calculadora;


public class 	CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {

		if (salarioBase < 1039.00) {
			return 0;
		}
		return Math.round(calcularIrrf(calcularInss(salarioBase)));
	}
	
	
	private double calcularInss(double salarioBase) {

		if (salarioBase <= 1500){
			salarioBase -= (0.08 * salarioBase);
		} else if(salarioBase <= 4000){
			salarioBase -= (0.09 * salarioBase);
		} else {
			salarioBase -= (0.11 * salarioBase);
		}
		return salarioBase;
	}

	private double calcularIrrf(double salarioBase) {
		if (salarioBase > 3000 && salarioBase <= 6000) {
			salarioBase -= (salarioBase * 0.075);
		} else if (salarioBase > 6000) {
			salarioBase -= (salarioBase * 0.15);
		}
		return salarioBase;
	}
}