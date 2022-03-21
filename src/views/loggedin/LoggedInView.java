package views.loggedin;

import product.ProductRepository;
import views.View;

public class LoggedInView extends View {

    @Override
    public void printPage() {
        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("1. 상품 조회\t 2. 상품 등록\t 3. 등록 상품 관리\t 4. 구매 상품 관리");
            System.out.println("5. 장바구니 관리\t 6. 잔액 확인\t 7. 로그아웃\t 8. 종료");
            System.out.print(">> ");
            handleRequest(inputCommand());
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void handleRequest(final Integer command) {
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
                checkBalance();
                break;
            }
            case 7: {
                signOut();
                break;
            }
            case 8:
                exit();
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }

    }

    private void lookupProduct() {
        View.state = ViewState.Lookup_Product_List;
    }

    private void registerProduct() {
        View.state = ViewState.Register_Product;
    }

    private void manageRegisterProduct() {
        View.state = ViewState.Manage_Register_Product;
    }

    private void managePurchasedProduct() {
        View.state = ViewState.Manage_Purchased_Product;
    }

    private void manageCart() {
        View.state = ViewState.Manage_Cart;
    }

    private void checkBalance() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println(user.getBalance());
    }

    private void signOut() {
        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("로그아웃을 하시겠습니까?");
            System.out.println("1. 예\t 2. 아니오");
            System.out.print(">> ");
            handleSignOut(inputCommand());
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

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
