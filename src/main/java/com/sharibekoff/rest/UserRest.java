package com.sharibekoff.rest;

import com.sharibekoff.entity.UserInSocialNetwork;
import com.sharibekoff.security.JWTTokenNeeded;
import com.sharibekoff.service.UserService;
import com.sharibekoff.util.KeyGenerator;
import com.sharibekoff.util.PasswordUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    @Context
    UriInfo uriInfo;

    @Inject
    KeyGenerator keyGenerator;

    @Inject
    UserService userService;

    @PersistenceContext
    EntityManager entityManager;

    @Path("login")
    @POST
    public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {
        try {
            authenticate(login, password);
            String token = issueToken(login);
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String login, String password) throws Exception {
        TypedQuery<UserInSocialNetwork> query = entityManager.createNamedQuery(UserInSocialNetwork.FIND_BY_LOGIN_AND_PASSWORD, UserInSocialNetwork.class);
        query.setParameter("login", login);
        query.setParameter("password", PasswordUtils.digestPassword(password));
        UserInSocialNetwork user = query.getSingleResult();

        if (user == null)
            throw new SecurityException("Invalid user/password");
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;

    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Path("{id}")
    @GET
//    @JWTTokenNeeded
    public Response findById(@PathParam("id") Long id) {
        if (userService.findById(id) == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findById(id)).build();
    }

    @Path("list")
    @GET
    @JWTTokenNeeded
    public Response findAll() {
        if (userService.findAll().isEmpty()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findAll()).build();
    }

    @Path("new")
    @POST
//    @JWTTokenNeeded
    public Response create(UserInSocialNetwork user) {
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.createUser(user)).build();
    }

    @Path("update/{id}")
    @PUT
//    @JWTTokenNeeded
    public Response update(@PathParam("id") Long id) {
        UserInSocialNetwork user = userService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.updateUser(user)).build();
    }

    @Path("delete/{id}")
    @DELETE
//    @JWTTokenNeeded
    public Response delete(@PathParam("id") Long id) {
        UserInSocialNetwork user = userService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.deleteUser(user)).build();
    }

    @Path("findByAge")
    @GET
//    @JWTTokenNeeded
    public Response findByAge(@QueryParam("age") int age) {
        if (age < 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(userService.findByAge(age)).build();
    }
}
