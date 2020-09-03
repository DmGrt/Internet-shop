package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);

        Product apple = new Product("Apple", 2_000_000_000);
        Product google = new Product("Google", 1_000_000_000);
        Product huawei = new Product("Huawei", 95_000_000);
        Product xiaomi = new Product("Xiaomi", 33_000_000);
        Product samsung = new Product("Samsung", 363_000_000);

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
    }

    private static void printInfo(ProductService productService) {
        System.out.println("Companies for sale:");
        productService.getAll().forEach(System.out::println);
        System.out.println("------------------\n");
    }
}
