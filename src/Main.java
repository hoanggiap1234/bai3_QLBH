import service.PurchaseOrderManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        PurchaseOrderManager purchaseOrderManager = new PurchaseOrderManager();
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    purchaseOrderManager.inputListCustomer();
                    purchaseOrderManager.printListCustomer();
                    break;
                case 2:
                    purchaseOrderManager.inputListProduct();
                    purchaseOrderManager.printListProduct();
                    break;
                case 3:
                    purchaseOrderManager.createPurchaseOrderForCustomer();
                    purchaseOrderManager.printListPurchaseOrder();
                    break;
                case 4:
                    System.out.println("Danh mục tồn kho sau khi tạo order: ");
                    purchaseOrderManager.printListProduct();
                    break;
                case 5:
                    System.out.println("Sắp xếp danh sách mua hàng theo tên khách hàng");

                    break;
                case 6:
                    System.out.println("case 6 ");
                    break;
                case 7:
                    purchaseOrderManager.createInvoiceByCus();
                    break;
                case 8:
                    System.exit(0);
            }

        } while (true);
    }

    private static int functionChoice() {
        System.out.println("\n --------Quản lý bán hàng--------");

        System.out.println("1. Nhập danh sách khách hàng.  In ra danh sách khách hàng đã có");
        System.out.println("2. Nhập danh sách mặt hàng mới. In ra danh sách các mặt hàng đã có  ");
        System.out.println("3. Lập Bảng danh sách mua hàng cho từng khách hàng");
        System.out.println("4. Kiểm tra tồn kho danh mục mặt hàng");
        System.out.println("5. Sắp xếp danh sách Bảng danh sách mua hàng theo tên khách hàng");
        System.out.println("6. Sắp xếp danh sách Bảng danh sách mua hàng theo tên mặt hàng");
        System.out.println("7. Lập hóa đơn cho từng khách hàng");
        System.out.println("8. Thoát");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice >= 1 && functionChoice <= 7) {
                break;
            }
            System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return functionChoice;
    }
}
