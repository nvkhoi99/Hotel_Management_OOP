/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

/**
 *
 * @author Yue
 */
public class LoadDriver {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Loading driver...");

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded!");

    }
}
