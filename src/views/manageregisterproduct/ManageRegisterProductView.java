package views.manageregisterproduct;

import product.Category;
import product.Product;
import views.View;
import views.loggedin.mapper.CommandCategoryMapper;
import views.registerproduct.dto.UpdateProductDTO;

import java.util.List;

public class ManageRegisterProductView extends View {

    @Override
    public void printPage() {
        try {
            final List<Product> userRegisterProduct = PRODUCT_REPOSITORY.findRegisterProductByUserId(user.getId());
            userRegisterProduct.forEach(System.out::println);

            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("1. 수정\t 2. 삭제\t 3. 나가기");
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
                updateProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    private void updateProduct() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("수정할 상품의 이름을 입력해주세요.");
        System.out.print(">> ");

        final String productName = scanner.nextLine();
        final Product product = findByProductName(productName);

        final UpdateProductDTO dto = generateUpdateProductDTO();
        if (dto != null) product.update(dto);

        PRODUCT_REPOSITORY.update(product);

    }

    private void removeProduct() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("삭제할 상품의 이름을 입력해주세요.");
        System.out.print(">> ");

        final String productName = scanner.nextLine();
        final Product product = findByProductName(productName);

        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("정말 상품을 삭제하시겠습니까?");
            System.out.println("1. 예\t 2. 아니오");
            System.out.print(">> ");
            handleRemoveProduct(inputCommand(), product);
        } catch (NumberFormatException e) {
            System.err.println("표시된 숫자를 입력해야 합니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private void handleRemoveProduct(final Integer command, final Product product) {
        switch (command) {
            case 1:
                removeProductOnRepository(product);
                break;
            case 2:
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    private void removeProductOnRepository(final Product product) {
        PRODUCT_REPOSITORY.removeRegisterProduct(user.getId(), product);
    }

    private Product findByProductName(final String productName) {
        return PRODUCT_REPOSITORY.findRegisterProductByUserId(user.getId())
                .stream()
                .filter(product1 -> product1.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    private UpdateProductDTO generateUpdateProductDTO() {
        try {
            System.out.println("1. 전자기기\t 2. 음식\t 3. 옷\t 4. 침구류");
            System.out.print(">> ");
            final Category category = CommandCategoryMapper.map(inputCommand());

            System.out.print("이름 >> ");
            final String name = scanner.nextLine();

            System.out.print("가격 >> ");
            final Long price = Long.valueOf(scanner.nextLine());

            System.out.print("소개 >> ");
            final String description = scanner.nextLine();

            return new UpdateProductDTO(
                    name, price, description, category
            );
        } catch (NumberFormatException e) {
            System.err.println("숫자가 아닙니다.");
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
