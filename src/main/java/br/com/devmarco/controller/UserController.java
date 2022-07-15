package br.com.devmarco.controller;


import br.com.devmarco.repositories.dtos.UserDTO;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/user")
public class UserController {
    /*
    @Inject
    UserService userService;

     */

    @GET
    public Uni<List<UserDTO>> findAll(){
        return null;
    }

    @GET
    @Path("/{id}")
    public Uni<List<UserDTO>> findbyId(){
        return null;
    }

    @POST
    public Uni<UserDTO> create(){
        return null;
    }

    @DELETE
    @Path("/{id}")
    public Uni<UserDTO> remove(@PathParam("id") Long id){
        return null;
    }

    @PUT
    @Path("/{id}")
    public Uni<UserDTO> updateUserParameter(@PathParam("id") Long id){
        return null;
    }

    @PATCH
    @Path("/{id}")
    public Uni<UserDTO> updateUser(@PathParam("id") Long id){
        return null;
    }
}
