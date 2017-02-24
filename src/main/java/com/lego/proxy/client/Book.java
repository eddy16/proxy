/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lego.proxy.client;



/**
 *
 * @author edgar
 */
public class Book{

    private String id;
    private String name;
    private String author;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{"
                + "\n  id: " + id
                + ",\n  name: " + name
                + ",\n  author: " + author
                + "\n}";
    }
}
