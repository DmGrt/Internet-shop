package internet.shop.service;

import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    ShoppingCart getByUserId(Long userId);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

}
