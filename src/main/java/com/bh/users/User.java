package com.bh.users;

import java.io.Serializable;

/**
 * Created by bharatviswanadham on 6/17/17.
 */
public class User implements Serializable {
    int id;
    String name;
    String addr;

    public User() {

    }

    public User(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
