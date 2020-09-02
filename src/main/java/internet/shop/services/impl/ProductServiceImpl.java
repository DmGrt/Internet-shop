package internet.shop.services.impl;

import internet.shop.dao.interfaces.ProductDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.models.Product;
import internet.shop.services.interfaces.ProductService;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return productDao.getById(productId);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean deleteById(Long productId) {
        return productDao.deleteById(productId);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
