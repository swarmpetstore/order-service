package org.packt.swarm.petstore.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_id_seq")
    @JsonIgnore
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID")
    private Order order;

    @Column
    private int itemId;
    @Column
    private int quantity;

    public OrderItem(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}