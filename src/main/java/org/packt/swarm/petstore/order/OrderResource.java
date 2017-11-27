package org.packt.swarm.petstore.order;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
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
    public Response createOrder(@QueryParam("customerId") int customerId, @QueryParam("items") HashMap<Integer, Integer> items) {
        try {
            Order order = orderService.createOrder(customerId, items);
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("order/{id}/payed")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response markAsPayed(@PathParam("id") int orderId) {
        try {
            Order order = orderService.changeState(orderId, Order.OrderState.PAID);
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
