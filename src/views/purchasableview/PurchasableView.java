package views.purchasableview;

import product.Product;
import user.User;
import views.View;

import java.util.Optional;

public abstract class PurchasableView extends View {

    protected void purchaseProduct() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("구매할 상품의 이름을 입력해주세요.");
        System.out.print(">> ");
        Optional<Product> purchaseProduct = PRODUCT_REPOSITORY
                .findById(scanner.nextLine(), (name, product) -> product.getName().equals(name));
        purchase(purchaseProduct.orElseThrow(
                () -> new IllegalArgumentException("해당 이름의 상품이 존재하지 않습니다.")
        ));
    }

    private void purchase(Product product) {
        payForProductPrice(product);
        PRODUCT_REPOSITORY.savePurchaseProduct(View.user.getId(), product);
    }

    private void payForProductPrice(final Product product) {
        final User seller = USER_REPOSITORY
                .findById(
                        PRODUCT_REPOSITORY.findSellerIdByProduct(product),
                        (userId, user) -> user.getId().equals(userId)
                ).orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
                );

        seller.addBalance(product.getPrice());
        user.minusBalance(product.getPrice());
    }

}
