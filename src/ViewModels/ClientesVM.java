/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Conexion.Consult;
import Libreria.*;
import Models.Cliente;
import Models.Reporte_cliente;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author sa_fi
 */
public class ClientesVM extends Consult {

    private String _accion = "insert";
    private String _mony;
    private final ArrayList<JLabel> _label;
    private final ArrayList<JTextField> _textField;
    private final JCheckBox _chqBoxCredito;
    private final JTable _tableCliente, _tablaReportes;
    private DefaultTableModel modelo, modelo2;
    private JSpinner _spinnerPaginas;
    private int _idCliente = 0;
    private int _reg_por_pagina = 10;
    private int _num_pagina = 1;
    public int seccion;
    private final FormatInteger _format;
    private Paginador<Cliente> _paginadorClientes;
    private Paginador<Reporte_cliente> _paginadorReportes;

    public ClientesVM(Object[] objects, ArrayList<JLabel> label, ArrayList<JTextField> textField) {
        _label = label;
        _textField = textField;
        _chqBoxCredito = (JCheckBox) objects[0];
        _tableCliente = (JTable) objects[1];
        _spinnerPaginas = (JSpinner) objects[2];
        _tablaReportes = (JTable) objects[3];
        _format = new FormatInteger();
        restablecer();
        restablecerReporte();
    }
// <editor-fold defaultstate="collapsed" desc="Codigo de registrar cliente"> 

    public void RegistrarCliente() {
        if (_textField.get(0).getText().equals("")) {
            _label.get(0).setText("Ingrese el Rut");
            _label.get(0).setForeground(Color.red);
            _textField.get(0).requestFocus();
        } else {
            if (_textField.get(1).getText().equals("")) {
                _label.get(1).setText("Ingrese el Nombre");
                _label.get(1).setForeground(Color.red);
                _textField.get(1).requestFocus();
            } else {
                if (_textField.get(2).getText().equals("")) {
                    _label.get(2).setText("Ingrese el Apellido");
                    _label.get(2).setForeground(Color.red);
                    _textField.get(2).requestFocus();
                } else {
                    if (_textField.get(3).getText().equals("")) {
                        _label.get(3).setText("Ingrese el Email");
                        _label.get(3).setForeground(Color.red);
                        _textField.get(3).requestFocus();
                    } else {
                        if (!Objetos.eventos.isEmail(_textField.get(3).getText())) {
                            _label.get(3).setText("Ingrese un Email valido");
                            _label.get(3).setForeground(Color.red);
                            _textField.get(3).requestFocus();
                        } else {
                            if (_textField.get(4).getText().equals("")) {
                                _label.get(4).setText("Ingrese el Telefono");
                                _label.get(4).setForeground(Color.red);
                                _textField.get(4).requestFocus();
                            } else {
                                if (_textField.get(5).getText().equals("")) {
                                    _label.get(5).setText("Ingrese la Dirección");
                                    _label.get(5).setForeground(Color.red);
                                    _textField.get(5).requestFocus();
                                } else {
                                    int count;
                                    List<Cliente> listEmail = clientes().stream()
                                            .filter(u -> u.getEmail().equals(_textField.get(3).getText()))
                                            .collect(Collectors.toList());
                                    count = listEmail.size();

                                    List<Cliente> listRut = clientes().stream()
                                            .filter(u -> u.getRut().equals(_textField.get(0).getText()))
                                            .collect(Collectors.toList());
                                    count += listRut.size();
                                    try {
                                        switch (_accion) {
                                            case "insert":

                                                if (count == 0) {
                                                    Insert();
                                                } else {
                                                    if (!listEmail.isEmpty()) {
                                                        _label.get(3).setText("El Email ya esta registrado");
                                                        _label.get(3).setForeground(Color.red);
                                                        _textField.get(3).requestFocus();
                                                    }
                                                    if (!listRut.isEmpty()) {
                                                        _label.get(0).setText("El Rut ya esta registrado");
                                                        _label.get(0).setForeground(Color.red);
                                                        _textField.get(0).requestFocus();
                                                    }
                                                }

                                                break;
                                            case "update":
                                                if (count == 2) {
                                                    if (listEmail.get(0).getIdCliente() == _idCliente
                                                            && listRut.get(0).getIdCliente() == _idCliente) {
                                                        Insert();
                                                    } else {
                                                        if (listRut.get(0).getIdCliente() != _idCliente) {
                                                            _label.get(0).setText("El Rut ya esta Registrado");
                                                            _label.get(0).setForeground(Color.red);
                                                            _textField.get(0).requestFocus();
                                                        }
                                                        if (listEmail.get(0).getIdCliente() != _idCliente) {
                                                            _label.get(3).setText("El Email ya esta Registrado");
                                                            _label.get(3).setForeground(Color.red);
                                                            _textField.get(3).requestFocus();
                                                        }
                                                    }
                                                } else {
                                                    if (count == 0) {
                                                        Insert();
                                                    } else {
                                                        if (!listRut.isEmpty()) {
                                                            if (listRut.get(0).getIdCliente() == _idCliente) {
                                                                Insert();
                                                            } else {
                                                                _label.get(0).setText("El Rut ya esta Registrado");
                                                                _label.get(0).setForeground(Color.red);
                                                                _textField.get(0).requestFocus();
                                                            }
                                                        }
                                                        if (!listEmail.isEmpty()) {
                                                            if (listEmail.get(0).getIdCliente() == _idCliente) {
                                                                Insert();
                                                            } else {
                                                                _label.get(3).setText("El Email ya esta Registrado");
                                                                _label.get(3).setForeground(Color.red);
                                                                _textField.get(3).requestFocus();
                                                            }
                                                        }
                                                    }
                                                }
                                                break;

                                        }
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null, ex);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void Insert() throws SQLException {
        try {
            final QueryRunner qr = new QueryRunner(true);
            getConn().setAutoCommit(false);
            byte[] image = UploadImage.getImageByte();
            if (image == null) {
                image = Objetos.uploadimage.getTransFoto(_label.get(6));
            }
            switch (_accion) {
                case "insert":
                    String sqlCliente1 = "INSERT INTO cliente(rut,nombre,apellido,email,telefono,direccion,credito,fecha,imagen)"
                            + " VALUES(?,?,?,?,?,?,?,?,?)";
                    Object[] dataCliente1 = {
                        _textField.get(0).getText(),
                        _textField.get(1).getText(),
                        _textField.get(2).getText(),
                        _textField.get(3).getText(),
                        _textField.get(4).getText(),
                        _textField.get(5).getText(),
                        _chqBoxCredito.isSelected(),
                        new Calendario().getFecha(),
                        image};
                    qr.insert(getConn(), sqlCliente1, new ColumnListHandler(), dataCliente1);
                    String sqlReport = "INSERT INTO reporte_cliente (deuda_actual, fecha_deuda, ultimo_pago, fecha_pago, ticket, fecha_limite, idCliente) VALUES (?,?,?,?,?,?,?)";
                    List<Cliente> cliente = clientes();
                    Object[] dataReport = {
                        0,
                        "--/--/--",
                        0,
                        "--/--/--",
                        "0000000000",
                        "--/--/--",
                        cliente.get(cliente.size() - 1).getIdCliente(),};
                    qr.insert(getConn(), sqlReport, new ColumnListHandler(), dataReport);
                    break;
                case "update":
                    Object[] dataCliente2 = {
                        _textField.get(0).getText(),
                        _textField.get(1).getText(),
                        _textField.get(2).getText(),
                        _textField.get(3).getText(),
                        _textField.get(4).getText(),
                        _textField.get(5).getText(),
                        _chqBoxCredito.isSelected(),
                        image};
                    String sqlCliente2 = "UPDATE cliente SET rut = ?,nombre = ?, apellido = ?,"
                            + "email = ?, telefono = ?, direccion = ?, credito = ?,"
                            + "imagen = ? WHERE idCliente  =" + _idCliente;
                    qr.update(getConn(), sqlCliente2, dataCliente2);
                    break;
            }

            getConn().commit();
            restablecer();
        } catch (SQLException ex) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void SearchCliente(String campo) {
        List<Cliente> clienteFilter;
        String[] titulos = {
            "IdCliente", "Rut", "Nombre", "Apellido", "Email", "Dirección", "Telefono", "Credito", "Imagen"
        };
        modelo = new DefaultTableModel(null, titulos);
        int inicio = (_num_pagina - 1) * _reg_por_pagina;
        if (campo.equals("")) {
            clienteFilter = clientes().stream()
                    .skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        } else {
            clienteFilter = clientes().stream()
                    .filter(C -> C.getRut().startsWith(campo) || C.getNombre().startsWith(campo)
                    || C.getApellido().startsWith(campo))
                    .skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        }
        if (!clienteFilter.isEmpty()) {
            clienteFilter.forEach(item -> {
                Object[] registros = {
                    item.getIdCliente(),
                    item.getRut(),
                    item.getNombre(),
                    item.getApellido(),
                    item.getEmail(),
                    item.getDireccion(),
                    item.getTelefono(),
                    item.isCredito(),
                    item.getImagen()
                };
                modelo.addRow(registros);
            });

        }
        _tableCliente.setModel(modelo);
        _tableCliente.setRowHeight(30);
        _tableCliente.getColumnModel().getColumn(0).setMaxWidth(0);
        _tableCliente.getColumnModel().getColumn(0).setMinWidth(0);
        _tableCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
        _tableCliente.getColumnModel().getColumn(8).setMaxWidth(0);
        _tableCliente.getColumnModel().getColumn(8).setMinWidth(0);
        _tableCliente.getColumnModel().getColumn(8).setPreferredWidth(0);
        _tableCliente.getColumnModel().getColumn(7).setCellRenderer(new Render_CheckBox());
    }

    public void GetCliente() {
        _accion = "update";
        int filas = _tableCliente.getSelectedRow();
        _idCliente = (Integer) modelo.getValueAt(filas, 0);
        _textField.get(0).setText((String) modelo.getValueAt(filas, 1));
        _textField.get(1).setText((String) modelo.getValueAt(filas, 2));
        _textField.get(2).setText((String) modelo.getValueAt(filas, 3));
        _textField.get(3).setText((String) modelo.getValueAt(filas, 4));
        _textField.get(4).setText((String) modelo.getValueAt(filas, 5));
        _textField.get(5).setText((String) modelo.getValueAt(filas, 6));
        _chqBoxCredito.setSelected((Boolean) modelo.getValueAt(filas, 7));
        Objetos.uploadimage.byteImage(_label.get(6), (byte[]) modelo.getValueAt(filas, 8));
    }

    public final void restablecer() {
        seccion = 1;
        _accion = "insert";
        _textField.get(0).setText("");
        _textField.get(1).setText("");
        _textField.get(2).setText("");
        _textField.get(3).setText("");
        _textField.get(4).setText("");
        _textField.get(5).setText("");
        _chqBoxCredito.setSelected(false);
        _chqBoxCredito.setForeground(new Color(102, 102, 102));

        _label.get(0).setText("Rut");
        _label.get(0).setForeground(new Color(102, 102, 102));
        _label.get(1).setText("Nombre");
        _label.get(1).setForeground(new Color(102, 102, 102));
        _label.get(2).setText("Apellido");
        _label.get(2).setForeground(new Color(102, 102, 102));
        _label.get(3).setText("Email");
        _label.get(3).setForeground(new Color(102, 102, 102));
        _label.get(4).setText("Telefono");
        _label.get(4).setForeground(new Color(102, 102, 102));
        _label.get(5).setText("Dirección");
        _label.get(5).setForeground(new Color(102, 102, 102));
        _label.get(6).setIcon(new ImageIcon(getClass().getClassLoader().getResource("Resources/1.1.png")));
        listClientes = clientes();
        if (!listClientes.isEmpty()) {
            _paginadorClientes = new Paginador<>(listClientes, _label.get(7), _reg_por_pagina);
        }
        SpinnerNumberModel model1 = new SpinnerNumberModel(
                new Integer(10), //Datos visualizados al inicio del spinner
                new Integer(1), //Limite inferior
                new Integer(100), //Limite Superior
                new Integer(1) // Incremento / decremento
        );
        _spinnerPaginas.setModel(model1);
        SearchCliente("");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Codigo de PAGOS Y REPORTE"> 
    public void SearchReportes(String valor) {

        List<Cliente> clienteFilter;
        String[] titulos = {
            "IdCliente", "Rut", "Nombre", "Apellido", "Email", "Dirección", "Telefono"
        };
        modelo2 = new DefaultTableModel(null, titulos);
        int inicio = (_num_pagina - 1) * _reg_por_pagina;
        if (valor.equals("")) {
            clienteFilter = clientes().stream()
                    .skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        } else {
            clienteFilter = clientes().stream()
                    .filter(C -> C.getRut().startsWith(valor) || C.getNombre().startsWith(valor)
                    || C.getApellido().startsWith(valor))
                    .skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        }
        if (!clienteFilter.isEmpty()) {
            clienteFilter.forEach(item -> {
                Object[] registros = {
                    item.getIdCliente(),
                    item.getRut(),
                    item.getNombre(),
                    item.getApellido(),
                    item.getEmail(),
                    item.getDireccion(),
                    item.getTelefono(),
                    
                };
                modelo2.addRow(registros);
            });
        }
        _tablaReportes.setModel(modelo2);
        _tablaReportes.setRowHeight(30);
        _tablaReportes.getColumnModel().getColumn(0).setMaxWidth(0);
        _tablaReportes.getColumnModel().getColumn(0).setMinWidth(0);
        _tablaReportes.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    public void GetReportCliente() {
        int filas = _tablaReportes.getSelectedRow();
        _idCliente = (Integer) modelo2.getValueAt(filas, 0);
        String nombre = (String) modelo2.getValueAt(filas, 2) + " " + modelo2.getValueAt(filas, 3);
        _label.get(8).setText(nombre);
        _label.get(9).setText(_mony+_format.integer((Integer)modelo2.getValueAt(filas, 4)));
        _label.get(10).setText((String) modelo2.getValueAt(filas, 5));
        _label.get(11).setText(_mony+_format.integer((Integer)modelo2.getValueAt(filas, 6)));
        _label.get(12).setText((String) modelo2.getValueAt(filas, 7));
        _label.get(13).setText((String) modelo2.getValueAt(filas, 8));
    }

    public final void restablecerReporte() {
        listReporte = reporteCliente();
        if (!listReporte.isEmpty()) {
            _paginadorReportes = new Paginador<>(listReporte, _label.get(7), _reg_por_pagina);
        }
        SearchReportes("");

    }
    // </editor-fold>
    private List<Cliente> listClientes;
    private List<Reporte_cliente> listReporte;

    public void Paginador(String metodo) {
        switch (metodo) {
            case "Primero":
                switch (seccion) {
                    case 1:
                        if (!listClientes.isEmpty()) {
                            _num_pagina = _paginadorClientes.primero();
                        }
                        break;
                    case 2:
                        if (!listReporte.isEmpty()) {
                            _num_pagina = _paginadorReportes.primero();
                        }
                        break;

                }
                break;
            case "Anterior":
                switch (seccion) {
                    case 1:
                        if (!listClientes.isEmpty()) {
                            _num_pagina = _paginadorClientes.anterior();
                        }
                        break;
                    case 2:
                        if (!listReporte.isEmpty()) {
                            _num_pagina = _paginadorReportes.anterior();
                        }
                        break;
                }
                break;
            case "Siguiente":
                switch (seccion) {
                    case 1:
                        if (!listClientes.isEmpty()) {
                            _num_pagina = _paginadorClientes.siguiente();
                        }
                        break;
                    case 2:
                        if (!listReporte.isEmpty()) {
                            _num_pagina = _paginadorReportes.siguiente();
                        }
                        break;
                }
                break;
            case "Ultimo":
                switch (seccion) {
                    case 1:
                        if (!listClientes.isEmpty()) {
                            _num_pagina = _paginadorClientes.ultimo();
                        }
                        break;
                    case 2:
                        if (!listReporte.isEmpty()) {
                            _num_pagina = _paginadorReportes.ultimo();
                        }
                        break;
                }
                break;
        }
        switch (seccion) {
            case 1:
                SearchCliente("");
                break;
            case 2:
                SearchReportes("");
                break;
        }
    }

    public void Registro_Paginas() {
        _num_pagina = 1;
        Number caja = (Number) _spinnerPaginas.getValue();
        _reg_por_pagina = caja.intValue();

        switch (seccion) {
            case 1:
                if (!listClientes.isEmpty()) {
                    _paginadorClientes = new Paginador<>(listClientes, _label.get(7), _reg_por_pagina);
                }
                SearchCliente("");
                break;
            case 2:
                if (!listReporte.isEmpty()) {
                    _paginadorReportes = new Paginador<>(listReporte, _label.get(7), _reg_por_pagina);
                }
                SearchReportes("");
                break;

        }

    }
}
