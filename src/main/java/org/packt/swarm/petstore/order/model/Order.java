package org.packt.swarm.petstore.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_id_seq")
    @JsonIgnore
    private Long id;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="state")
    private OrderState state;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

