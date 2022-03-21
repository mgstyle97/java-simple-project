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
    protected void handleRequest(final Integer command) {
        try {
            switch (command) {
                case 1: {
                    lookupProduct();
                    break;
                }
                case 2: {
                    registerProduct();
                    break;
                }
                case 3: {
                    manageRegisterProduct();
                    break;
                }
                case 4: {
                    managePurchasedProduct();
                    break;
                }
                case 5: {
                    manageCart();
                    break;
                }
                case 6: {
                    signOut();
                    break;
                }
                case 7:
                    exit();
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 요청입니다.");
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.out.println();
        }
    }

    private void lookupProduct() {}

    private void registerProduct() {}

    private void manageRegisterProduct() {}

    private void managePurchasedProduct() {}

    private void manageCart() {}

    private void signOut() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("로그아웃을 하시겠습니까?");
        System.out.println("1. 예\t 2. 아니오");
        System.out.print(">> ");
        handleSignOut(Integer.valueOf(scanner.nextLine()));

    }

    private void handleSignOut(final Integer signOutCommand) {
        switch (signOutCommand) {
            case 1:
                setNonLoggedInState();
                break;
            case 2:
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    private void setNonLoggedInState() {
        View.user = null;
        View.state = ViewState.Non_Logged_In;
    }

}
