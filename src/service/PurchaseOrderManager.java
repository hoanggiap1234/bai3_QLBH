package service;

import model.Customer;
import model.Product;

import java.util.Scanner;

public class PurchaseOrderManager {
    private Customer[] customers;
    private Product[] products;
    private PurchaseOrder[] purchaseOrders;


    public void inputListCustomer() {
        Scanner scanner = new Scanner(System.in);

//        try {
        int numberOfCus;
        do {
            System.out.println(" Nhập số lượng khách hàng:");
            numberOfCus = scanner.nextInt();
        } while (Convert.isNumeric(String.valueOf(numberOfCus)) == false);

        customers = new Customer[numberOfCus];
        for (int i = 0; i < customers.length; i++) {
            Customer newCustomer = new Customer();
            newCustomer.add();
            customers[i] = newCustomer;
        }
//        } catch (ArithmeticException armtx) {
//            System.out.println("lỗi: số khách hàng phải là kiểu số");
//        } catch (NullPointerException npx) {
//            System.out.println("Danh sách khách hàng đang rỗng");
//        }

    }

    public void printListCustomer() {
        try {
            System.out.println("Danh sách khách hàng bạn vừa nhập: ");
            for (int i = 0; i < customers.length; i++) {
                customers[i].print();
            }
        } catch (NullPointerException npx) {
            System.out.println("lỗi: mảng null, hoặc chưa khởi tạo");
        } catch (ArrayIndexOutOfBoundsException aiobx) {
            System.out.println("lỗi: vượt quá chiều dài mảng");
        }

    }

    public void inputListProduct() {
        Scanner scanner = new Scanner(System.in);

        int numberOfProduct;
        do {
            System.out.println(" Nhập số lượng mặt hàng :");

            numberOfProduct = scanner.nextInt();
        } while (Convert.isNumeric(String.valueOf(numberOfProduct)) == false);

        products = new Product[numberOfProduct];
        for (int i = 0; i < products.length; i++) {
            Product newProduct = new Product();
            newProduct.add();
            products[i] = newProduct;
        }
    }

    public void printListProduct() {
        for (int i = 0; i < products.length; i++) {
            products[i].print();
        }
    }

    public void createPurchaseOrderForCustomer() {
        Scanner scanner = new Scanner(System.in);
        // 1. tìm đến từng khách hàng vừa nhập để thực hiện tạo order
        // 2. Nhập số lượng mặt hàng cần nhập cho khách hàng đó
        // 3. kiểm tra số lượng <= 10;
        // 4. Nhập mã mặt hàng cho khách này
        // 5. Kiểm tra nếu mã mặt hàng đã nhập trong order này thì báo trùng
        // 6. Nếu không trùng thì thêm vào order
        // 7. Thêm vào list PurchaseOrder;
        purchaseOrders = new PurchaseOrder[customers.length];
        for (int i = 0; i < customers.length; i++) {
            System.out.println(" Tạo order cho khách hàng: " + customers[i].getName());
            int numberOfProducts;
            do {
                System.out.println(" Nhập số mặt hàng cần mua: ");
                numberOfProducts = scanner.nextInt();
            } while (numberOfProducts < 1 || numberOfProducts > 10 || Convert.isNumeric(String.valueOf(numberOfProducts)) == false);

            String productCodeTemp = "";
            Product[] orderProducts = new Product[numberOfProducts]; // lưu danh sách mặt hàng trong order
            int[] quantities = new int[numberOfProducts]; // lưu số lượng cho từng mặt hàng

            for (int j = 0; j < numberOfProducts; j++) {
                // 1. Nhập mã mặt hàng
                String productCode = scanner.nextLine();
                // 2. Check mã mặt hàng
                Product findedProduct = new Product();
                while (!checkProductCode(productCode)) {
                    System.out.println("Nhập mã mặt hàng");
                    productCode = scanner.nextLine();

                }

                // 3. check trùng product code
                while (productCodeTemp.equals(productCode)) {
                    System.out.println("Nhập mã mặt hàng");
                    productCode = scanner.nextLine();

                }
                findedProduct = findProductByProductCode(productCode);

                orderProducts[j] = findedProduct;
                // 4. Nhập số lượng cần mua
                System.out.println("Nhập số lượng mặt hàng: " + findedProduct.getProductName() + " cần mua: ");
                int quantity = scanner.nextInt();
                // 5. check tồn kho
                while (findProductByProductCode(productCode).getStock() < quantity || quantity > findedProduct.getStock()) {
                    System.out.println("Nhập số lượng mặt hàng: " + findedProduct.getProductName() + "cần mua: ");
                    quantity = scanner.nextInt();
                }
                quantities[j] = quantity;
                //6. Giảm tồn kho trong bảng product
                products[j].setStock(products[j].getStock() - quantity);
                productCodeTemp = productCode;
            }

            PurchaseOrder newPurchaseOrder = new PurchaseOrder(customers[i], orderProducts, quantities);
            purchaseOrders[i] = newPurchaseOrder;
        }
    }

    public void printListPurchaseOrder() {
        System.out.println(" Danh sách order vừa tạo");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < purchaseOrders.length; i++) {
            System.out.print("\n customer code: " + purchaseOrders[i].getCustomer().getCode() + " | ");
            for (int j = 0; j < purchaseOrders[i].getProducts().length; j++) {
                System.out.println("product code: " + purchaseOrders[i].getProducts()[j].getProductCode() + " | product name: " + purchaseOrders[i].getProducts()[j].getProductName() + " | quantity: " + purchaseOrders[i].getQuantities()[j]);
            }
        }
    }

    public void sortListPurchaseOrderWithCustomerName() {
        if (purchaseOrders == null) {
            System.out.println("Chưa có đơn hàng nào được mua");
            System.out.println("Quay lại mục 3 để tạo đơn hàng");
            return;
        }
        for (int i = 0; i < purchaseOrders.length; i++) {
            for (int j = i + 1; j < purchaseOrders.length; j++) {
                if (purchaseOrders[i].getCustomer().getName().compareTo(purchaseOrders[j].getCustomer().getName()) > 0) {
                    PurchaseOrder temp = purchaseOrders[i];
                    purchaseOrders[i] = purchaseOrders[j];
                    purchaseOrders[j] = temp;
                }
            }
        }
    }

//    public void sortListPurchaseOrderWithProductName() {
//        if (purchaseOrders == null) {
//            System.out.println("Chưa có đơn hàng nào được mua");
//            System.out.println("Quay lại mục 3 để tạo đơn hàng");
//            return;
//        }
//        for (int i = 0; i < purchaseOrders.length; i++) {
//            for (int j = i + 1; j < purchaseOrders.length; j++) {
//                if (purchaseOrders[i].getProducts().[i].getProductName()getName().compareTo(purchaseOrders[j].getCustomer().getName()) > 0) {
//                    PurchaseOrder temp = purchaseOrders[i];
//                    purchaseOrders[i] = purchaseOrders[j];
//                    purchaseOrders[j] = temp;
//                }
//            }
//        }
//    }

    public void createInvoiceByCus(){
        for( int i =0; i< purchaseOrders.length; i++){
            System.out.println("----------Hóa đơn mua hàng--------------");
            System.out.println("Họ tên: " + purchaseOrders[i].getCustomer().getName());
            System.out.println("Danh sách mua hàng:");
            int totalMoney = 0;
            for( int j = 0; j< purchaseOrders[i].getProducts().length; j++){
                long money =  purchaseOrders[i].getProducts()[j].getBuyPrice() * purchaseOrders[i].getQuantities()[j];
                System.out.println(" Mặt hàng: " + j + " : ");
                System.out.println(purchaseOrders[i].getProducts()[j].getProductName() + " | số lượng: " + purchaseOrders[i].getQuantities()[j] +
                        " | Giá: " + purchaseOrders[i].getProducts()[j].getBuyPrice()
                        + " | Thành tiền: " + money);
                totalMoney += money;

            }
            System.out.println("Tổng tiền: " + totalMoney );
            System.out.println("-----------------------------------------------------------");

        }
    }

    public boolean checkProductCode(String productCode) {
        Boolean check = false;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductCode().equals(productCode)) {
                check = true;
            }
        }
        return check;
    }

    public Product findProductByProductCode(String productCode) {
        Product product;
        int index = 0;
        boolean isProduct = false;
        while (index < products.length || !isProduct) {
            if (products[index].getProductCode().equals(productCode)) {
                isProduct = true;
                break;
            } else {
                index++;
            }
        }
        if (isProduct) {
            product = products[index];
        } else {
            product = null;
        }
        return product;
    }
}
