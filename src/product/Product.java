package product;

import global.Entity;
import user.Money;

public class Product extends Entity {

    private String name;
    private String description;
    private Category category;
    private Money price;

    public Product(final String name, final String description,
                   final String category, final long price) {
        this.name = name;
        this.description = description;
        this.category = Category.of(category);
        this.price = Money.of(price);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCategory(final String category) {
        this.category = Category.of(category);
    }

    public Category getCategory() {
        return this.category;
    }

    public void setPrice(final long price) {
        this.price = Money.of(price);
    }

    public Money getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("-----------------------------------\n");
        builder.append("이름: ").append(name).append("\n")
                .append("설명: ").append(description).append("\n")
                .append("가격: ").append(price).append("\n")
                .append("-----------------------------------\n");

        return builder.toString();
    }

    enum Category {
        DIGITAL_MACHINE, FOOD, CLOTHES, BEDDING;

        static Category of(final String categoryString) {
            return Category.valueOf(categoryString.toUpperCase());
        }
    }

}
