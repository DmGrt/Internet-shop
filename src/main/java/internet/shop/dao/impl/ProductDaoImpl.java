package internet.shop.dao.impl;

import internet.shop.dao.interfaces.ProductDao;
import internet.shop.db.Storage;
import internet.shop.lib.Dao;
import internet.shop.models.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return Storage.products.stream()
                .filter(x -> x.getId().equals(productId))
                .findFirst();
    }

    @Override
    public Product update(Product product) {
        for (int i = 0; i < Storage.products.size(); i++) {
            if (Storage.products.get(i).getId().equals(product.getId())) {
                Product oldProduct = Storage.products.get(i);
                Storage.products.set(i, product);
                return oldProduct;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long productId) {
        return Storage.products.removeIf(x -> x.getId().equals(productId));
    }

    @Override
    public boolean delete(Product product) {
        return deleteById(product.getId());
    }

    @Override
    public List<Product> getAllProducts() {
        return Storage.products;
    }
}
