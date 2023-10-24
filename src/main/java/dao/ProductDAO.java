package dao;

import dao.abs.AbstractDAO;
import model.Product;

import java.util.Set;

public interface ProductDAO extends AbstractDAO<Product, String> {
    // more specific methods
    Set<Product> findAllByProductLine(String productLine);

}
