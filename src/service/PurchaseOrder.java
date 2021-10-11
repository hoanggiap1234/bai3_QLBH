package service;

import model.Customer;
import model.Product;

import java.util.Arrays;

public class PurchaseOrder {
    private Customer customer;
    private Product[] products;
    private int[] quantities; // Không mua quá 10 mặt hàng

    public PurchaseOrder(Customer customer, Product[] products, int[] quantities) {
        this.customer = customer;
        this.products = products;
        this.quantities = quantities;
    }
    public PurchaseOrder() {}

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "customer=" + customer.getCode() + ", cus Name: " + customer.getName() +
                ", products=" + Arrays.toString(products) +
                ", quantities=" + Arrays.toString(quantities) +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setQuantitys(int[] quantitys) {
        this.quantities = quantitys;
    }
}