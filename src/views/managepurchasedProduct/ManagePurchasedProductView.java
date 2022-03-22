package views.managepurchasedProduct;

import product.Product;
import user.User;
import views.View;

import java.util.List;

public class ManagePurchasedProductView extends View {

    @Override
    public void printPage() {
        try {
            final List<Product> userPurchasedProduct = productRepository.findPurchaseProductByUserId(user.getId());
            userPurchasedProduct.forEach(System.out::println);

            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("상품을 환불하시겠습니까?");
            System.out.println("1. 예\t 2. 아니오");
            System.out.print(">> ");
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
                handleRefundRequest();
                break;
            case 2:
                state = ViewState.Logged_In;
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        state = ViewState.Logged_In;
    }

    private void handleRefundRequest() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("환불할 상품의 이름을 입력해주세요.");
        System.out.print(">> ");

        final String productName = scanner.nextLine();
        final Product purchasedProduct = findByProductName(productName);

        refundForPurchasedProduct(purchasedProduct);
    }

    private Product findByProductName(final String productName) {
        return productRepository.findPurchaseProductByUserId(user.getId()).stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("구매 이력이 없는 상품입니다.")
                );
    }

    private void refundForPurchasedProduct(final Product purchasedProduct) {
        final User seller = userRepository
                .findById(
                        productRepository.findSellerIdByProduct(purchasedProduct),
                        (userId, user) -> user.getId().equals(userId)
                ).orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
                );

        seller.minusBalance(purchasedProduct.getPrice());
        user.addBalance(purchasedProduct.getPrice());

        productRepository.removePurchaseProduct(user.getId(), purchasedProduct);
    }
}
