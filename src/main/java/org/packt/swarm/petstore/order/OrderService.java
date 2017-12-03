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
    public Long createOrder(Order order){
        em.persist(order);
        return order.getId();
    }

}
