/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author sa_fi
 */
public class FormatInteger {
    DecimalFormat formateador = new DecimalFormat("###,###,###");
    Number numero;
    
    public String integer(int formato){
        return formateador.format(formato);
    }
    public int reconstruir(String formato){
        try {
            numero = formateador.parse(formato.replace(" ", ""));
        } catch (ParseException ex) {
            System.out.println("Error " + ex);
        }
        return numero.intValue();
    }
}
