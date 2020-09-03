package internet.shop.service.impl;

import internet.shop.dao.daointerface.ProductDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.Product;
import internet.shop.service.serviceinterface.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
