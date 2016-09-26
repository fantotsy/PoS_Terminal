package ua.fantotsy.products;

public abstract class Product {
    protected int price;

    public Product() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void askForPayment() {
        System.out.println("You have ordered " + this.getClass().getSimpleName());
        System.out.println("Pay: " + price);
    }
}