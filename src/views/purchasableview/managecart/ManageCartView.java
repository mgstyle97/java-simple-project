package views.purchasableview.managecart;

import product.Product;
import views.purchasableview.PurchasableView;

import java.util.List;

public class ManageCartView extends PurchasableView {

    @Override
    public void printPage() {
        try {
            final List<Product> userStoreProducts = productRepository.findStoreProductByUserId(user.getId());
            userStoreProducts.forEach(System.out::println);

            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("1. 구매\t 2. 삭제\t 3. 나가기");
            handleRequest(inputCommand());
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        state = ViewState.Logged_In;
    }

    @Override
    protected void handleRequest(Integer command) {
        switch (command) {
            case 1:
                purchaseProduct();
                break;
            case 2:
                removeStoreProduct();
                break;
            case 3:
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    private void removeStoreProduct() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("삭제할 상품의 이름을 입력해주새요.");
        System.out.print(">> ");

        final String productName = scanner.nextLine();
        final Product product = findByProductName(productName);

        productRepository.removeStoreProduct(user.getId(), product);
    }

    private Product findByProductName(final String productName) {
        return productRepository.findStoreProductByUserId(user.getId()).stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("장바구니에 존재하지 않는 상품입니다.")
                );
    }
}
