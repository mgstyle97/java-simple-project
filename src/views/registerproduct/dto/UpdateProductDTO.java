package views.registerproduct.dto;

import product.Category;

public class UpdateProductDTO {

    private String name;
    private Long price;
    private String description;
    private Category category;

    public UpdateProductDTO(final String name, final Long price,
                              final String description, final Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

}
