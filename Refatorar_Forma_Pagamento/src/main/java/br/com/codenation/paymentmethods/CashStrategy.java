package br.com.codenation.paymentmethods;

public class CashStrategy implements PriceStrategy {
    @Override
    public Double calculate(Double price) {
        return price * 0.9;
    }
}