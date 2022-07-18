package br.com.devmarco.controllers;


import br.com.devmarco.repositories.dtos.UserDTO;
import br.com.devmarco.services.UserService;
import br.com.devmarco.services.impl.UserServiceImpl;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserController {

    UserService userService;
    @Inject
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GET
    public Uni<List<UserDTO>> findAll(){
        return userService.findAll();
    }

    @GET
    @Path("/{nickname}")
    public Uni<UserDTO> findbyNickname(@PathParam("nickname") String nickname){

        return userService.findByNickname(nickname);
    }

    @POST
    public Uni<UserDTO> create(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }

    @DELETE
    @Path("/{nickname}")
    public Uni<UserDTO> remove(@PathParam("nickname") String nickname){
        return userService.delete(nickname);
    }

    @PUT
    @Path("/{nickname}")
    public Uni<UserDTO> updateUser(@PathParam("nickname") String nickname, @RequestBody UserDTO userDTO){
        return userService.update(userDTO);
    }
}
