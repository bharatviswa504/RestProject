package com.bh.users;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bharatviswanadham on 6/17/17.
 */

@Path("/userservice")
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    //PUT is idempotent
    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Success createUser(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("addr") String addr) {
        User addUser = new User(id, name, addr);
        UserDao ud = new UserDao();
        int ret = ud.addUser(addUser);
        if(ret == 1) {
            return new Success("User Created");
        } else {
            return new Success("User Already Exists");
        }
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Success updateUser(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("addr") String addr) {
        User addUser = new User(id, name, addr);
        UserDao ud = new UserDao();
        int ret = ud.updateUser(addUser);
        if(ret == 1) {
            return new Success("User Updated");
        } else {
            return new Success("User Created");
        }
    }

    ///Users;id=<<>>
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(@DefaultValue("-1") @MatrixParam("id") int id) {
        UserDao ud = new UserDao();
        List<User> userList = ud.getAllUsers();
        if( id == -1) {
            log.info("Going with default value");
            return userList;
        } else {
            log.info("Going with user passed value");
            for(User user : userList) {
                if(user.getId() == id) {
                    List<User> userId = new LinkedList<>();
                    userId.add(user);
                    return userId;
                }
            }
        }
        return null;
    }

    @DELETE
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Success deleteUser(@QueryParam("id") int id) {
        UserDao ud = new UserDao();
       int ret = ud.deleteUser(id);
        if(ret == 1) {
            return new Success("User Deleted");
        } else {
            return new Success("No such User");
        }
    }

    @OPTIONS
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Success optionsUsers() {
        return new Success("Options Supported are: POST, DELETE, GET, PUT");
    }
}

