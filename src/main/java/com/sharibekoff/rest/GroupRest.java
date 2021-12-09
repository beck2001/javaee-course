package com.sharibekoff.rest;

import com.sharibekoff.entity.GroupInSocialNetwork;
import com.sharibekoff.security.JWTTokenNeeded;
import com.sharibekoff.service.GroupService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("group")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupRest {

    @Inject
    GroupService groupService;

    @Path("{id}")
    @GET
    @JWTTokenNeeded
    public Response findById(@PathParam("id") Long id) {
        if (groupService.findById(id) == null) {
            // if group not found, server return 500 status code as response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(groupService.findById(id)).build();
    }

    @Path("list")
    @GET
    @JWTTokenNeeded
    public Response findAll() {
        if (groupService.findAll().isEmpty()) {
            // if there are no groups return 500 status code
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(groupService.findAll()).build();
    }

    @Path("new")
    @POST
    @JWTTokenNeeded
    public Response create(GroupInSocialNetwork group) {
        return Response.ok(groupService.createGroup(group)).build();
    }

    @Path("update")
    @PUT
    @JWTTokenNeeded
    public Response update(GroupInSocialNetwork group) {
        if (group == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(groupService.updateGroup(group)).build();
    }

    @Path("delete")
    @DELETE
    @JWTTokenNeeded
    public Response delete(GroupInSocialNetwork group) {
        if (group == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(groupService.deleteGroup(group)).build();
    }

    @Path("subscribers")
    @GET
    @JWTTokenNeeded
    public Response findBySubscribersAmount(@QueryParam("amount") int amount) {
        if (amount < 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(groupService.findByNumberOfSubscribers(amount)).build();
    }

}
