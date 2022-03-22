package views.registerproduct;

import global.properties.Money;
import product.Category;
import product.Product;
import views.View;
import views.loggedin.mapper.CommandCategoryMapper;
import views.registerproduct.dto.RegisterProductDTO;

public class RegisterProductView extends View {

    @Override
    public void printPage() {
        try {
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("상품의 정보를 입력해주세요.");
            System.out.println("1. 전자기기\t 2. 음식\t 3. 옷\t 4. 침구류\t 5. 나가기");
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
        if (command.equals(5)) {
            return;
        }

        try {
            final Category category = CommandCategoryMapper.map(command);

            System.out.print("이름 >> ");
            final String name = scanner.nextLine();

            System.out.print("가격 >> ");
            final Long price = Long.valueOf(scanner.nextLine());

            System.out.print("소개 >> ");
            final String description = scanner.nextLine();

            final RegisterProductDTO dto = new RegisterProductDTO(
                    name, price, description, category
            );

            PRODUCT_REPOSITORY.saveRegisterProduct(user.getId(), new Product(
                    dto.getName(), dto.getDescription(),
                    dto.getCategory(), Money.of(dto.getPrice())
            ));
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력해야 합니다.");
        }
    }
}
