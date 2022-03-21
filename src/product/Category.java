package product;

public enum Category {
    DIGITAL_MACHINE, FOOD, CLOTHES, BEDDING;

    public static Category of(final String categoryString) {
        return Category.valueOf(categoryString.toUpperCase());
    }
}
