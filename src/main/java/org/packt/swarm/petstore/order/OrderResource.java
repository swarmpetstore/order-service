package org.packt.swarm.petstore.order;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.packt.swarm.petstore.order.model.Order;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class OrderResource {

    @Inject
    private OrderService orderService;

    @GET
    @Path("order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order searchById(@PathParam("id") int id) {
        return orderService.findById(id);
    }

    @POST
    @Path("order")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        try {
            System.out.println("MAM TAKIEGO ORDERA "+ ToStringBuilder.reflectionToString(order));
            long orderId = orderService.createOrder(order);
            return Response.ok(orderId).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
