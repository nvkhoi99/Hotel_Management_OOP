/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class ReportCondition {

    private Date from;
    private Date to;
    private String addIn;

    public ReportCondition(String from, String to) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.from = new Date(formatter.parse(from).getTime());
            this.to = new Date(formatter.parse(to).getTime() + 1000 * 60 * 60 * 24);
        } catch (ParseException ex) {
            Logger.getLogger(ReportCondition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAddIn(String addIn) {
        this.addIn = addIn;
    }

    public String getAddIn() {
        return addIn;
    }

    public ReportCondition(String pattern) {
        Calendar calendar = Calendar.getInstance();
        if (pattern.length() == 4) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            try {
                calendar.setTime(formatter.parse(pattern));
            } catch (ParseException ex) {
                Logger.getLogger(ReportCondition.class.getName()).log(Level.SEVERE, null, ex);
            }
            from = new Date(calendar.getTime().getTime());
            calendar.add(Calendar.YEAR, 1);
            to = new Date(calendar.getTime().getTime());
        } else if (pattern.length() == 7) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
            try {
                calendar.setTime(formatter.parse(pattern));
            } catch (ParseException ex) {
                Logger.getLogger(ReportCondition.class.getName()).log(Level.SEVERE, null, ex);
            }
            from = new Date(calendar.getTime().getTime());
            calendar.add(Calendar.MONTH, 1);
            to = new Date(calendar.getTime().getTime());
        }
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

}
