package org.packt.swarm.petstore.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name="Order.findById",
                query="SELECT o FROM Order o WHERE o.id = :id"),
})
public class Order {

    public enum OrderState {
        NEW, PAID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_id_seq")
    @JsonIgnore
    private int id;

    @Column
    private int customerId;

    @Column
    private OrderState state;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;


    public Order(int customerId, List<Integer> itemIds, List<Integer> quantities){
        this.customerId = customerId;
        state = OrderState.NEW;
        items = new ArrayList<>();

        Iterator<Integer> i = itemIds.iterator();
        Iterator<Integer> q = quantities.iterator();
        for(;i.hasNext();i.next(),q.next()){
            items.add(new OrderItem(i.next(), q.next()));
        }
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
