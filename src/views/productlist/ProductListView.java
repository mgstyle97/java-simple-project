package views.productlist;

import product.Product;
import user.User;
import views.Purchasable;
import views.Storeable;
import views.View;
import views.loggedin.mapper.CommandCategoryMapper;
import views.purchasableview.PurchasableView;

import java.util.List;
import java.util.Optional;

public class ProductListView extends PurchasableView implements Storeable {

    @Override
    public void printPage() {
        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("1. 전자기기\t 2. 음식\t 3. 옷\t 4. 침구류");
            System.out.print(">> ");
            handleRequest(inputCommand());
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void handleRequest(Integer command) {
        List<Product> products = productRepository
                .findByCategory(
                        CommandCategoryMapper.map(command)
                );
        products.forEach(System.out::println);
        additionalRequest();

    }

    private void additionalRequest() {
        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("1. 구매\t 2. 장바구니\t 3. 나가기");
            System.out.print(">> ");
            handleAdditionalRequest(inputCommand());
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private void handleAdditionalRequest(final Integer command) {
        switch (command) {
            case 1:
                purchaseProduct();
                break;
            case 2:
                storeProduct();
                break;
            case 3:
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        state = ViewState.Logged_In;
    }

    private void storeProduct() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("장바구니에 담을 상품의 이름을 입력해주세요.");
        System.out.print(">> ");
        Optional<Product> purchaseProduct = productRepository
                .findById(scanner.nextLine(), (name, product) -> product.getName().equals(name));
        store(purchaseProduct.orElseThrow(
                () -> new IllegalArgumentException("해당 이름의 상품이 존재하지 않습니다.")
        ));
    }

    @Override
    public void store(Product product) {
        productRepository.saveStoreProduct(user.getId(), product);
    }

}
