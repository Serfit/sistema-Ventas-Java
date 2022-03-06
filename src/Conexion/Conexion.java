/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sa_fi
 */
public class Conexion {
 private String db = "punto_de_venta";
    private String user = "root";
    private String password = "";
    private String urlMysql = "jdbc:mysql://localhost/" + db + "?SslMode=none";
    private Connection conn = null;
    
    public Conexion(){
         try {
             //obtenemos el driver de para mysql
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection(this.urlMysql, this.user, this.password);
            
             if (conn != null) {
                System.out.println("Conexión a la base de datos " + this.db + "...... Listo ");
            }
         } catch (ClassNotFoundException | SQLException ex) {
             System.out.println("Error : " + ex);
         }
    }

    public Connection getConn() {
        return conn;
    }
    
}
