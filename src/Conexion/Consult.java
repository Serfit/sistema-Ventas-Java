/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Models.Cliente;
import Models.Configuracion;
import Models.Reporte_cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author sa_fi
 */
public class Consult extends Conexion {

    private QueryRunner qr = new QueryRunner();

    public List<Cliente> clientes() {
        List<Cliente> cliente = new ArrayList<>();
        try {
            cliente = (List<Cliente>) qr.query(getConn(), "SELECT * FROM cliente", new BeanListHandler(Cliente.class));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
        }
        return cliente;
    }

    public List<Reporte_cliente> reporteCliente() {
        String where = "";
        List<Reporte_cliente> reportes = new ArrayList<>();
        String condicion = " cliente.idCliente = reporte_cliente.idCliente ";
        String campo = " cliente.idCliente,cliente.rut,cliente.nombre,cliente.apellido,"
                + "reporte_cliente.idRegistro,reporte_cliente.deuda_actual,"
                + "reporte_cliente.fecha_deuda,reporte_cliente.ultimo_pago,"
                + "reporte_cliente.fecha_pago,reporte_cliente.ticket,"
                + "reporte_cliente.fecha_limite";
        try {
            reportes = (List<Reporte_cliente>) qr.query(getConn(),
                    "SELECT " + campo + " FROM reporte_cliente Inner Join cliente ON"
                    + condicion + where, new BeanListHandler(Reporte_cliente.class));
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return reportes;
    }

    public List<Configuracion> config() {
        List<Configuracion> config = new ArrayList<>();
        try {
            config = (List<Configuracion>) qr.query(getConn(), "SELECT * FROM configuracion",
                    new BeanListHandler(Configuracion.class));
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return config;
    }
}
