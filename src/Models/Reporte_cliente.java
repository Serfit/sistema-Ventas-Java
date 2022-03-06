/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author sa_fi
 */
public class Reporte_cliente extends Cliente{
    private int idRegistro;
    private int deuda_actual;
    private String fecha_deuda;
    private int ultimo_pago;
    private String fecha_pago;
    private String ticket;
    private String fecha_limite;
    private int idCliente;

    public Reporte_cliente() {
    }
    
    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getDeuda_actual() {
        return deuda_actual;
    }

    public void setDeuda_actual(int deuda_actual) {
        this.deuda_actual = deuda_actual;
    }

    public String getFecha_deuda() {
        return fecha_deuda;
    }

    public void setFecha_deuda(String fecha_deuda) {
        this.fecha_deuda = fecha_deuda;
    }

    public int getUltimo_pago() {
        return ultimo_pago;
    }

    public void setUltimo_pago(int ultimo_pago) {
        this.ultimo_pago = ultimo_pago;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
