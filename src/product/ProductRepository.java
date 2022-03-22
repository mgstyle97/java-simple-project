package product;

import global.data.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRepository extends Repository<Product> {

    private final Map<String, List<Product>> userPurchaseProductCollection;
    private final Map<String, List<Product>> userStoreProductCollection;
    private final Map<String, List<Product>> userRegisterProductCollection;

    public ProductRepository() {
        this.userPurchaseProductCollection = new HashMap<>();
        this.userStoreProductCollection = new HashMap<>();
        this.userRegisterProductCollection = new HashMap<>();
    }

    public List<Product> findByCategory(final Category category) {
        return this.collection.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void savePurchaseProduct(final String userId, final Product product) {
        if (isRegisterProduct(userId, product)) {
            throw new IllegalArgumentException("본인이 등록한 상품에 관해 해당 요청을 수행할 수 없습니다.");
        }

        List<Product> userPurchaseProducts = this.userPurchaseProductCollection.get(userId);
        if (userPurchaseProducts == null) {
            userPurchaseProducts = new ArrayList<>();
        }

        userPurchaseProducts.add(product);
        userPurchaseProductCollection.put(userId, userPurchaseProducts);
    }

    public void saveStoreProduct(final String userId, final Product product) {
        if (isRegisterProduct(userId, product)) {
            throw new IllegalArgumentException("본인이 등록한 상품에 관해 해당 요청을 수행할 수 없습니다.");
        }

        List<Product> userStoreProducts = this.userStoreProductCollection.get(userId);
        if (userStoreProducts == null) {
            userStoreProducts = new ArrayList<>();
        }

        userStoreProducts.add(product);
        userStoreProductCollection.put(userId, userStoreProducts);
    }

    public void saveRegisterProduct(final String userId, final Product product) {
        super.save(product);

        List<Product> userRegisterProducts = this.userRegisterProductCollection.get(userId);
        if (userRegisterProducts == null) {
            userRegisterProducts = new ArrayList<>();
        }

        userRegisterProducts.add(product);
        userRegisterProductCollection.put(userId, userRegisterProducts);
    }

    public String findSellerIdByProduct(final Product product) {
        return this.userRegisterProductCollection.keySet().stream()
                .filter(userId -> this.userRegisterProductCollection.get(userId).contains(product))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("해당 상품에 관한 판매자가 존재하지 않습니다.")
                );
    }

    public List<Product> findRegisterProductByUserId(final String userId) {
        final List<Product> userRegisterProducts = this.userRegisterProductCollection.get(userId);

        if (userRegisterProducts == null) {
            throw new IllegalArgumentException("등록한 상품에 대한 정보가 없습니다.");
        }

        return userRegisterProducts;
    }

    public List<Product> findPurchaseProductByUserId(final String userId) {
        final List<Product> userPurchaseProducts = this.userPurchaseProductCollection.get(userId);

        if (userPurchaseProducts == null) {
            throw new IllegalArgumentException("구매한 상품들에 대한 정보가 없습니다.");
        }

        return userPurchaseProducts;
    }

    public List<Product> findStoreProductByUserId(final String userId) {
        final List<Product> userStoreProducts = this.userStoreProductCollection.get(userId);

        if (userStoreProducts == null) {
            throw new IllegalArgumentException("장바구니에 담긴 상품이 없습니다.");
        }

        return userStoreProducts;
    }

    public void removeRegisterProduct(final String userId, final Product product) {
        final List<Product> userRegisterProducts = this.userRegisterProductCollection.get(userId);
        if (userRegisterProducts == null) {
            throw new IllegalArgumentException("회원이 등록한 상품이 없습니다.");
        }

        removeInStoreProduct(product);
        userRegisterProducts.remove(product);
        super.delete(product);
    }

    public void removePurchaseProduct(final String userId, final Product product) {
        final List<Product> userPurchaseProducts = this.userPurchaseProductCollection.get(userId);
        if (userPurchaseProducts == null) {
            throw new IllegalArgumentException("회원이 구매한 상품이 없습니다.");
        }

        userPurchaseProducts.remove(product);
    }

    public void removeStoreProduct(final String userId, final Product product) {
        final List<Product> userStoreProducts = this.userStoreProductCollection.get(userId);
        if (userStoreProducts == null) {
            throw new IllegalArgumentException("장바구니에 담긴 상품이 없습니다.");
        }

        userStoreProducts.remove(product);
    }

    private void removeInStoreProduct(final Product product) {
        this.userStoreProductCollection.values().stream()
                .filter(storeProductList -> storeProductList.contains(product))
                .forEach(list -> list.remove(product));
    }

    private boolean isRegisterProduct(final String userId, final Product product) {
        List<Product> userRegisterProducts = this.userRegisterProductCollection.get(userId);
        if (userRegisterProducts == null) {
            return false;
        }

        return userRegisterProducts.stream()
                .anyMatch(product1 -> product1.getUuid().equals(product.getUuid()));
    }

}
