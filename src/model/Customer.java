package model;

import service.Convert;

import java.util.Scanner;

public class Customer implements Features {
    public static String cusCodeStatic = "00000";
    private String code;
    private String name;
    private String address;
    private String phoneNumber;
    private CustomerType customerType;

    public String addCustomerCode() {
        int newCusCode = Integer.parseInt(cusCodeStatic);
        newCusCode++;
        cusCodeStatic = Convert.convertIntToStringFiveNumber(newCusCode);

        return cusCodeStatic;
    }

    @Override
    public void print() {
        System.out.println(" code: " + this.code + " ; name: " + this.name + " ; address: " + this.address
                + " ; phone: " + this.phoneNumber + " ; type: " + this.customerType);
    }

    @Override
    public void add() {
        this.code = addCustomerCode();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Nhập tên khách hàng:  ");
        this.name = scanner.nextLine();

        System.out.println(" Nhập địa chỉ khách hàng:  ");
        this.address = scanner.nextLine();

        System.out.println(" Nhập số điện thoại:  ");
        this.phoneNumber = scanner.nextLine();

        System.out.println(" Nhập loại khách hàng:  ");
        System.out.println(" 1. Nếu chọn là khách hàng mua lẻ:  ");
        System.out.println(" 2. Nếu là khách mua buôn:  ");
        int cusType = scanner.nextInt();
        do {
            switch (cusType) {
                case 1:
                    this.customerType = CustomerType.RETAIL;
                    break;
                case 2:
                    this.customerType = CustomerType.WHOLESALE;
                    break;
                default: {
                    System.out.println(" Nhập loại khách hàng:  ");
                    System.out.println(" 1. Nếu chọn là khách hàng mua lẻ:  ");
                    System.out.println(" 2. Nếu là khách mua buôn:  ");
                }
            }
        } while (cusType < 1 || cusType > 2 || !Convert.isNumeric(String.valueOf(cusType)));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }


}
