package internet.shop;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Injector;
import internet.shop.model.Product;

public class Application {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private static ProductDao productDao =
            (ProductDao) injector.getInstance(ProductDao.class);

    public static void main(String[] args) {
        Product apple = new Product("Apple", 2_000_000_000);
        apple.setId(1L);
        Product google = new Product("Google", 1_000_000_000);
        google.setId(2L);
        Product huawei = new Product("Huawei", 95_000_000);
        huawei.setId(3L);
        Product xiaomi = new Product("Xiaomi", 33_000_000);
        xiaomi.setId(4L);
        Product samsung = new Product("Samsung", 363_000_000);
        samsung.setId(5L);
        Product updatedXiaomi = new Product("Redmi", 38_000_000);
        updatedXiaomi.setId(xiaomi.getId());

        productDao.create(apple);
        productDao.create(google);
        productDao.create(huawei);
        productDao.create(xiaomi);
        productDao.create(samsung);

        System.out.println(productDao.getAll());
        System.out.println(productDao.get(apple.getId()));
        System.out.println(productDao.update(updatedXiaomi));
        System.out.println(productDao.delete(samsung.getId()));
        System.out.println(productDao.get(samsung.getId()));
        System.out.println(productDao.getAll());
    }
}
