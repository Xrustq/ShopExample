package ru.example;


import com.sun.org.apache.xpath.internal.SourceTree;
import ru.example.domain.Order;
import ru.example.domain.Product;
import ru.example.ejb.OrdersManagerBean;
import ru.example.ejb.ProductsManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private Order order;
    private String name;
    private int quantity;
    private Product product;

    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ProductsManagerBean productsManagerBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*
    * Метод для соданеия заказа.
    * */

    public void createOrder() {
        if (order == null) {
            order = ordersManagerBean.createOrder();
        }
    }

    /*
    * Метод для создания товара.
    * */

    public void createProduct() {
        productsManagerBean.createProduct(name, quantity);
    }

    /*
    * Метол возвращяющий список вещей.
    * */

    public List<Product> getProducts() {
        return productsManagerBean.getProducts();
    }

    /*
    * Метод для добавления товара в заказ.
    * */

    public void addProduct(Product product) {
        if (order == null) {
            return;
        }

        ordersManagerBean.addToOrder(product.getId(), order.getId(), 1);
    }

    /*
    * Метод возвращяющий список вещей в корзине.
    * */

    public List<Product> getProductsInOrder() {
        if (order == null) {
            return Collections.emptyList();
        }

        return ordersManagerBean.getProductsInOrder(order.getId());
    }

    /*
    * Метод для удаления продукта
    * */

    public void deleteProduct(Product product) {
        productsManagerBean.deleteProduct(product);

    }

    public String editProduct(Product product) {
        this.product = product;

        return "edit";
    }

    public String saveProduct() {
        productsManagerBean.updateProduct(product, name, quantity);

        return "index";
    }
}
