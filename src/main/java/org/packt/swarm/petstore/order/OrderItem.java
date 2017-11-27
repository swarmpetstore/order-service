package org.packt.swarm.petstore.order;

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
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @Column
    private int itemId;
    @Column
    private int quantity;

    public OrderItem(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

}