/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class NewClass {

    public static void main(String[] args) {
        Timestamp t = Timestamp.valueOf("2020-04-05 18:54:00");
        long first = t.getTime();
        Timestamp t1 = new Timestamp(first + 6 * 1000 * 60 * 60);
        t1.setHours(12);
        t1.setMinutes(0);
        t1.setSeconds(0);
        t1.setNanos(0);

        System.out.println(t1);
    }
}
