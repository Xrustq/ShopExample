package ru.example;


import ru.example.domain.Order;
import ru.example.domain.Thing;
import ru.example.ejb.OrdersManagerBean;
import ru.example.ejb.ThingsManagerBean;

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

    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ThingsManagerBean thingsManagerBean;

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

    public void createThing() {
        thingsManagerBean.createThing(name, quantity);
    }

    /*
    * Метол возвращяющий список вещей.
    * */

    public List<Thing> getThings() {
        return thingsManagerBean.getThings();
    }

    /*
    * Метод для добавления товара в заказ.
    * */

    public void addThing(Thing thing) {
        if (order == null) {
            return;
        }

        ordersManagerBean.addToOrder(thing.getId(), order.getId(), 1);
    }

    /*
    * Метод возвращяющий список вещей в корзине.
    * */

    public List<Thing> getThingsInOrder() {
        if (order == null) {
            return Collections.emptyList();
        }

        return ordersManagerBean.getThingsInOrder(order.getId());
    }
}
