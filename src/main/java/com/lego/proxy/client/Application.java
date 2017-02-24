/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lego.proxy.client;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edgar
 */
public class Application {

    public static void main(String[] args) {
        ProxyClient<Book> bookClient = new ProxyClient<>("book");

        //Lista todos los libros
        System.out.println("********Lista todos los libros*********");
        Book[] books = bookClient.executeGet(Book[].class);

        for (Book book : books) {
            System.out.println(book);
        }

        //Crea uno nuevo
        System.out.println("********Crea uno nuevo*********");
        Book book = new Book();
        book.setName("Oliver Twist");
        book.setAuthor("Charles Dickens");
        Book newBook = bookClient.executePost(book, Book.class);

        Map<String, Object> params = new HashMap<>();
        params.put("id", newBook.getId());

        //Obtiene nuevo registro
        System.out.println("********Obtiene nuevo registro*********");
        System.out.println(bookClient.executeGet(Book.class, params));
        
        Map<String, Object> params2 = new HashMap<>();
        
        //Actualiza libro nuevo
        System.out.println("********Actualiza libro nuevo*********");
        newBook.setName("Del amor y otros demonios");
        bookClient.executePut(newBook,params2);
        
        //Obtiene updated
        System.out.println("********Obtiene updated*********");
        Book bo = bookClient.executeGet(Book.class, params);
        System.out.println(bo);
        
        //Lista todos los libros
        System.out.println("********Lista todos los libros*********");
        books = bookClient.executeGet(Book[].class);

        for (Book b : books) {
            System.out.println(b);
        }
        
        //Elimina libro
        System.out.println("********Elimina libro "+ bo.getId() + "*********");
        bookClient.executeDelete(params);

        //Lista todos los libros
        System.out.println("********Lista todos los libros*********");
        books = bookClient.executeGet(Book[].class);

        for (Book bs : books) {
            System.out.println(bs);
        }

    }
}
