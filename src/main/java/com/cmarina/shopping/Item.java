package com.cmarina.shopping;

public class Item {

    public enum Deals {
        THREE_FOR_TWO, BUY_ONE_GET_ONE_FREE, NONE
    };

    private String name;
    private double price;
    private Deals deal;
    
    public Item(String name, double price) {
        super();
        this.name = name;
        this.price = price;
        this.deal = Deals.NONE;
    }

    public Item(String name, double price, Deals deal) {
        super();
        this.name = name;
        this.price = price;
        this.deal = deal;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Deals getDeal() {
        return deal;
    }

    public void setDeal(Deals deal) {
        this.deal = deal;
    }

    public double getPricePerQuantity(Long quantity) {
        switch (this.deal) {
            case BUY_ONE_GET_ONE_FREE:
                return (quantity/2 * price) + ((quantity % 2) * price) ;
            case THREE_FOR_TWO:
                return (quantity/3 * 2 * price) + ((quantity % 3) * price);
            default:
                return quantity * price;
        }
    }

}
