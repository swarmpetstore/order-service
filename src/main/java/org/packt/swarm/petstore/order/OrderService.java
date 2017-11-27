package org.packt.swarm.petstore.order;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OrderService {

    @PersistenceContext(unitName = "OrderPU")
    private EntityManager em;

    public Order findById(int id){
        return em.createNamedQuery("order.findById", Order.class).setParameter("id",id).getResultList().get(0);
    }

    @Transactional
    public Order createOrder(int customerId, HashMap<Integer, Integer> items){
        Order order = new Order(customerId, items);
        em.persist(order);
        return order;
    }

    @Transactional
    public Order changeState(int orderId, Order.OrderState state){
        Order order = findById(orderId);
        order.setState(state);
        em.persist(order);
        return order;
    }
}
