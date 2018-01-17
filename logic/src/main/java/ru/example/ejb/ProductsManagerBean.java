package ru.example.ejb;

import ru.example.domain.Product;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@LocalBean
public class ProductsManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    /*
    * Создание предмета
    * */

    public Product createProduct(String name, int quantity) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        entityManager.persist(product);

        return product;
    }

    /*
    * Возвращает список всех вещей которые есть в БД
    * */

    public List<Product> getProducts() {
        TypedQuery <Product> query = entityManager.createQuery("select c from Product c", Product.class);

        return query.getResultList();
    }

    public void deleteProduct(Product product) {
//      https://stackoverflow.com/questions/17027398/java-lang-illegalargumentexception-removing-a-detached-instance-com-test-user5
        entityManager.remove(entityManager.getReference(Product.class, product.getId()));
    }

    public Product updateProduct(Product product, String name, int quantity) {

        entityManager.merge(product);
        product.setName(name);
        product.setQuantity(quantity);

        return product;
    }

}
