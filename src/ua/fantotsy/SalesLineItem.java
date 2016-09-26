package ua.fantotsy;

import ua.fantotsy.products.Product;

public class SalesLineItem {
    private Product product;
    private int quantity;

    public SalesLineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int subTotal() {
        return product.getPrice() * quantity;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(product.getClass().getSimpleName());
        result.append("(UAH " + product.getPrice() + "):");
        result.append(quantity + "x.\n");
        return result.toString();
    }
}