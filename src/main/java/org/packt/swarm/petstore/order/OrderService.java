package org.packt.swarm.petstore.order;

import org.packt.swarm.petstore.order.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @PersistenceContext(unitName = "OrderPU")
    private EntityManager em;

    public Order findById(int id){
        return em.createNamedQuery("order.findById", Order.class).setParameter("id",id).getResultList().get(0);
    }

    @Transactional
    public int createOrder(int customerId, List<Integer> itemIds, List<Integer> quantities){
        Order order = new Order(customerId, itemIds, quantities);
        em.persist(order);
        return order.getId();
    }

}
