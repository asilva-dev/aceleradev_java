package br.com.codenation.paymentmethods;

public class CreditCardStrategy implements PriceStrategy{
    @Override
    public Double calculate(Double price) {
        return price * 0.98;
    }
}