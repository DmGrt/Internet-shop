package internet.shop;

import internet.shop.db.Storage;
import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.OrderService;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        Product apple = new Product("Apple", 2_000_000_000);
        Product google = new Product("Google", 1_000_000_000);
        Product huawei = new Product("Huawei", 95_000_000);
        Product xiaomi = new Product("Xiaomi", 33_000_000);
        Product samsung = new Product("Samsung", 363_000_000);

        ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);
        productService.create(apple);
        productService.create(google);
        productService.create(huawei);
        productService.create(xiaomi);
        productService.create(samsung);
        printInfo(productService);

        System.out.println("!!Huawei leaves the market!!");
        productService.delete(huawei.getId());
        printInfo(productService);

        System.out.println("!!Xiaomi rebrands to Redmi!!");
        Product updatedProduct = new Product("Redmi", 35_000_000);
        updatedProduct.setId(xiaomi.getId());
        productService.update(updatedProduct);
        printInfo(productService);

        UserService userService = (UserService)
                injector.getInstance(UserService.class);
        User frank = new User("Frank", "gglogergg", "verySecurePassword");
        userService.create(frank);
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        ShoppingCart franksCart = new ShoppingCart(frank.getId());
        shoppingCartService.create(franksCart);

        shoppingCartService.addProduct(franksCart, productService.get(samsung.getId()));
        shoppingCartService.addProduct(franksCart, productService.get(xiaomi.getId()));
        System.out.println("Frank's shopping cart: created");
        System.out.println(shoppingCartService.getByUserId(frank.getId()));
        System.out.println("Frank's shopping cart: removed one product");
        shoppingCartService.deleteProduct(franksCart, productService.get(xiaomi.getId()));
        System.out.println(franksCart.toString());

        OrderService orderService = (OrderService)
                injector.getInstance(OrderService.class);
        System.out.println("Frank's order: created");
        orderService.completeOrder(shoppingCartService.getByUserId(frank.getId()));
        System.out.println(orderService.get(franksCart.getId()));
        System.out.println("Frank's order: deleted");
        orderService.delete(franksCart.getId());
        System.out.println(Storage.orders);

        User updFrank = new User("Frank", "FreakMan", "securedSecurePassword");
        updFrank.setId(frank.getId());
        System.out.println("Old Frank:");
        System.out.println(userService.get(frank.getId()));
        System.out.println("New Frank:");
        userService.update(updFrank);
        System.out.println(userService.get(frank.getId()));
    }

    private static void printInfo(ProductService productService) {
        System.out.println("Companies for sale:");
        productService.getAll().forEach(System.out::println);
        System.out.println("------------------\n");
    }
}
