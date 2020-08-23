package br.com.codenation.paymentmethods;

public enum PaymentMethod {

    CASH(new CashStrategy()),
    DEBIT_CARD(new DebitCardStrategy()),
    CREDIT_CARD(new CreditCardStrategy()),
    TRANSFER(new TransferStrategy());

    private PriceStrategy priceStrategy;

    PaymentMethod(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPaymentStrategy() {
        return priceStrategy;
    }
}