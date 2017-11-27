package org.packt.swarm.petstore.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "order")
@NamedQueries({
        @NamedQuery(name="Order.findById",
                query="SELECT o FROM Order o WHERE o.id = :id"),
})
public class Order {

    enum OrderState {
        NEW, PAID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_id_seq")
    @JsonIgnore
    private int id;

    @Column
    private int customerId;

    @Column
    private OrderState state;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;


    public Order(int customerId, Map<Integer, Integer> itemMap){
        this.customerId = customerId;
        state = OrderState.NEW;
        items = new ArrayList<>();
        for(Integer itemId: itemMap.keySet()){
            items.add(new OrderItem(itemId, itemMap.get(itemId)));
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
