/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author sa_fi
 */
public class Calendario {
    private DateFormat dateFormat;
    private Date date = new Date();
    private Calendar c = new GregorianCalendar();
    private final String fecha;
    private final String dia;
    private final String mes;
    private final String year;
    private final String hora;
    private String am_pm;
    public Calendario(){
        switch (c.get(Calendar.AM_PM)) {
            case 0:
                am_pm = "am";
                break;
            case 1:
                am_pm = "pm";
                break;
            
        }
        dateFormat = new SimpleDateFormat("dd");
        dia = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("MM");
        mes = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("yyyy");
        year = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fecha = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("hh:mm:ss");
        hora = dateFormat.format(date) + " "+ am_pm;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getYear() {
        return year;
    }

    public String getHora() {
        return hora;
    }
    
}
