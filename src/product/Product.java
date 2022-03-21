package product;

import global.data.Entity;
import global.properties.Money;
import views.registerproduct.dto.UpdateProductDTO;

public class Product extends Entity {

    private String name;
    private String description;
    private Category category;
    private Money price;

    public Product(final String name, final String description,
                   final String category, final long price) {
        this(name, description, Category.of(category), Money.of(price));
    }

    public Product(final String name, final String description,
                   final Category category, final Money price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
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

    public void update(final UpdateProductDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.category = dto.getCategory();
        this.price = Money.of(dto.getPrice());
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



}
