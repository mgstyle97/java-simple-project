package views.loggedin;

import views.View;

public class LoggedInView extends View {

    @Override
    public void printPage() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("1. 상품 조회\t 2. 상품 등록\t 3. 등록 상품 관리");
        System.out.println("4. 구매 상품 관리\t 5. 장바구니 관리\t 6. 로그아웃\t 7. 종료");
        System.out.print(">> ");
        handleRequest(Integer.valueOf(scanner.nextLine()));
    }

    @Override
    protected void handleRequest(Integer command) {

    }
}
