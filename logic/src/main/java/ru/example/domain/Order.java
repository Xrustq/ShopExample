package ru.example.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zakaz")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<ProductInOrder> productInOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }
}