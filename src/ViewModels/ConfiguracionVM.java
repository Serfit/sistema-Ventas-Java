/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Conexion.Consult;
import Models.Configuracion;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JRadioButton;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author sa_fi
 */
public class ConfiguracionVM extends Consult {

    public static String Mony;
    private static List<JRadioButton> _radio;

    public ConfiguracionVM() {
       
        TypeMoney();
    }

    public ConfiguracionVM(List<JRadioButton> radio) {
        this.sqlConfig = "INSERT INTO configuracion(tipo_moneda) VALUES(?)";
        _radio = radio;
        RadioEvent();
        TypeMoney();
    }

    private void RadioEvent() {
        _radio.get(0).addActionListener((ActionEvent e) -> {
            TypeMoney("L", _radio.get(0).isSelected());
        });
        _radio.get(1).addActionListener((ActionEvent e) -> {
            TypeMoney("$", _radio.get(1).isSelected());
        });
    }
    private String sqlConfig;

    private void TypeMoney() {
        List<Configuracion> config = config();
        final QueryRunner qr = new QueryRunner(true);

        if (config.isEmpty()) {
            Mony = "L";
            Object[] dataConfig = {Mony};
            try {
                qr.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);
            } catch (SQLException e) {

            }
        } else {
            Configuracion data = config.get(0);
            Mony = data.getTipo_moneda();
            switch (Mony) {
                case "L":
                    _radio.get(0).setSelected(true);
                    break;
                case "$":
                    _radio.get(1).setSelected(true);
                    break;
            }
        }
    }

    private void TypeMoney(String typeMoney, boolean valor) {
        final QueryRunner qr = new QueryRunner(true);
        if (valor) {
            try {
                List<Configuracion> config = config();
                if (config.isEmpty()) {
                    sqlConfig = "INSERT INTO configuracion(tipo_moneda) VALUES(?)";
                    Mony = typeMoney;
                    Object[] dataConfig = {Mony};
                    qr.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);
                } else {                    
                    Configuracion data = config.get(0);
                    sqlConfig = "UPDATE configuracion SET tipo_moneda = ? WHERE IdConfig ="+data.getIdConfig();
                    if (data.getTipo_moneda().equals(typeMoney)) {
                        Mony = typeMoney;
                    } else {
                        Mony = typeMoney;
                        Object[] dataConfig = {Mony};
                        qr.update(getConn(), sqlConfig, dataConfig);
                    }

                }
            }catch (SQLException e) {

            }
        }
    }
}
