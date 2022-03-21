package views.loggedin.mapper;

import product.Category;

public class CommandCategoryMapper {

    public static Category map(final Integer command) {
        switch (command) {
            case 1:
                return Category.DIGITAL_MACHINE;
            case 2:
                return Category.FOOD;
            case 3:
                return Category.CLOTHES;
            case 4:
                return Category.BEDDING;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

}
