package ua.fantotsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.fantotsy.products.Product;

public class Sale {
    private List<SalesLineItem> salesLineItems;
    private Date date;

    public Sale() {
        salesLineItems = new ArrayList<>();
    }

    public List<SalesLineItem> getSalesLineItems() {
        return salesLineItems;
    }

    public void setSalesLineItem(List<SalesLineItem> salesLineItems) {
        this.salesLineItems = salesLineItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int total() {
        int result = 0;
        for (SalesLineItem item : salesLineItems) {
            result += item.subTotal();
        }
        return result;
    }

    public void makeLineItem(Product product, int quantity) {
        SalesLineItem item = new SalesLineItem(product, quantity);
        salesLineItems.add(item);
        date = new Date(System.currentTimeMillis());
    }

    public void cancelLastSalesLineItem() {
        salesLineItems.remove(salesLineItems.size() - 1);
    }

    public void printAllSales() {
        for (SalesLineItem item : salesLineItems) {
            System.out.println(item);
            System.out.println(date);
        }
        System.out.println("Total: " + total());
    }

    public void cancelLastOrder() {
        if (this.getSalesLineItems().size() > 0) {
            this.cancelLastSalesLineItem();
        }
    }

    public static Sale createSale() {
        return new Sale();
    }
}