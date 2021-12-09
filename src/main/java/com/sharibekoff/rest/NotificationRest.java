package com.sharibekoff.rest;

import com.sharibekoff.jms.NotificationInSocialNetwork;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("message")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class NotificationRest {
    @Inject
    NotificationInSocialNetwork notificationQueue;

    @Path("sendNotification/{notification}")
    @GET
    public Response sendNotification(@PathParam("notification") String notification) throws JMSException {
        notificationQueue.sendMessage(notification);
        return Response.ok().entity("Notification " + notification).build();
    }

    @Path("receiveNotification")
    @GET
    public Response receiveNotification() throws JMSException {
        String notification = notificationQueue.receiveMessage();
        return Response.ok().entity("Notification " + notification).build();
    }
}
