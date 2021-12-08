package com.sharibekoff.rest;

import com.sharibekoff.entity.UserInSocialNetwork;
import com.sharibekoff.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    @Inject
    UserService userService;

    @Path("{id}")
    @GET
    public Response findById(@PathParam("id") Long id) {
        if (userService.findById(id) == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findById(id)).build();
    }

    @Path("list")
    @GET
    public Response findAll() {
        if (userService.findAll().isEmpty()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findAll()).build();
    }

    @Path("new")
    @POST
    public Response create(UserInSocialNetwork user) {
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.createUser(user)).build();
    }

    @Path("update")
    @PUT
    public Response update(UserInSocialNetwork user) {
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.updateUser(user)).build();
    }

    @Path("delete")
    @DELETE
    public Response delete(UserInSocialNetwork user) {
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.deleteUser(user)).build();
    }

    @Path("findByAge")
    @GET
    public Response findByAge(@QueryParam("age") int age) {
        if (age < 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findByAge(age)).build();
    }
}
