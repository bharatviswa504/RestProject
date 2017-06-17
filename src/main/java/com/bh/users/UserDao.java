package com.bh.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bharatviswanadham on 6/17/17.
 */
public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            File file = new File("Users.dat");
            if(!file.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (ois != null)
                userList = (List<User>) ois.readObject();
            if (userList == null) {
                log.info("userlist is null");
            }
            ois.close();
            // }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUser(int id) {
        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public int addUser(User pUser) {
        List<User> userList = getAllUsers();
        boolean userExists = false;

        if (userList == null) {
            log.info("first time user created");
            userList = new LinkedList<>();
            userList.add(pUser);
            saveUserList(userList);
            return 1;
        }
        for (User user : userList) {
            if (user.getId() == pUser.getId()) {
                userExists = true;
                break;
            }
        }
        if (!userExists) {
            log.info("adding user to an existing list");
            userList.add(pUser);
            saveUserList(userList);
            return 1;
        }
        return 0;
    }

    public int updateUser(User pUser) {
        List<User> userList = getAllUsers();

        for (User user : userList) {

            //if exists update it
            if (user.getId() == pUser.getId()) {
                log.info("already user exists, updating");
                int index = userList.indexOf(user);
                userList.set(index, pUser);
                saveUserList(userList);
                return 1;
            }
        }

        log.info("No such user, creating it");
        //If not exist create
        userList.add(pUser);
        saveUserList(userList);

        return 0;
    }

    public int deleteUser(int id) {
        List<User> userList = getAllUsers();

        for (User user : userList) {
            //log.info("user list not empty");
            //log.info("id:" + user.getId());
            if (user.getId() == id) {
                log.info("found user");
                int index = userList.indexOf(user);
                userList.remove(index);
                saveUserList(userList);
                return 1;
            }
        }
        log.info("not entered loop");
        return 0;
    }

    private void saveUserList(List<User> userList) {
        try {
            File file = new File("Users.dat");
            FileOutputStream fos;

            fos = new FileOutputStream(file);


            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}