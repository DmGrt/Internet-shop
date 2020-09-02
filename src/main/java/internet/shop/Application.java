package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.models.Product;
import internet.shop.services.interfaces.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);

        productService.create(new Product("Apple",2_000_000_000));
        productService.create(new Product("Google",1_000_000_000));
        productService.create(new Product("Huawei",95_000_000));
        productService.create(new Product("Xiaomi",33_000_000));
        productService.create(new Product("Samsung",363_000_000));
        printInfo(productService);

        System.out.println("!!Huawei leaves the market!!");
        productService.deleteById(3L);
        printInfo(productService);

        System.out.println("!!Xiaomi rebrands to Redmi!!");
        Product updatedProduct = new Product("Redmi", 35_000_000);
        updatedProduct.setId(4L);
        productService.update(updatedProduct);
        printInfo(productService);
    }

    private static void printInfo(ProductService productService) {
        System.out.println("Companies for sale:");
        productService.getAllProducts().forEach(System.out::println);
        System.out.println("------------------\n");
    }
}
