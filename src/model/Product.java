package model;

import service.Convert;

import java.util.Scanner;

public class Product implements Features {
    public static String productCodeStatic = "00000";
    private String productCode;
    private String productName;
    private ProductGroup productGroup;
    private int stock;
    private long buyPrice;

    public String addProductCode() {
        int newProductCode = Integer.parseInt(productCodeStatic);
        newProductCode++;
        productCodeStatic = Convert.convertIntToStringFiveNumber(newProductCode);

        return productCodeStatic;
    }

    @Override
    public void add() {
        this.productCode = addProductCode();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập tên mặt hàng: ");
        this.productName = scanner.nextLine();

        System.out.println("Nhập nhóm mặt hàng: ");
        System.out.println("\n 1:Nhóm hàng thời trang ");
        System.out.println("\n 2:Nhóm hàng gia dụng ");
        System.out.println("\n 3:Nhóm hàng điện máy");
        int inputProductGroup = scanner.nextInt();

        do {
            switch (inputProductGroup) {
                case 1:
                    this.productGroup = ProductGroup.FASHION;
                    break;
                case 2:
                    this.productGroup = ProductGroup.CONSUMER;
                    break;
                case 3:
                    this.productGroup = ProductGroup.MEDIA;
                    break;
                default: {
                    System.out.println(" Nhập nhóm mặt hàng: ");
                    System.out.println(" 1:Nhóm hàng thời trang ");
                    System.out.println(" 2:Nhóm hàng gia dụng ");
                    System.out.println(" 3:Nhóm hàng điện máy ");
                    scanner.nextLine();
                    inputProductGroup = scanner.nextInt();
                }
            }
        } while (inputProductGroup < 0 || inputProductGroup > 3 || Convert.isNumeric(String.valueOf(inputProductGroup)) == false);

        System.out.println("Nhập số lượng tồn kho: ");
        this.stock = scanner.nextInt();

        System.out.println("Nhập giá mặt hàng: ");
        this.buyPrice = scanner.nextInt();

    }

    @Override
    public void print() {
        System.out.println("Product code: " + this.productCode + "; Product name: " + this.productName + " ; Product group: " + this.productGroup + " ; " + this.stock +  " ; buy price: "+ this.buyPrice);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
