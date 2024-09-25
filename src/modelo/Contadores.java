/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.UUID;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmContador;

/**
 *
 * @author Estudiante
 */
public class Contadores {
    //PARAMETROS DE LA TABLA DE LA BASE
    private String UUID_CONTADOR;
    private String NOMBRE_CONTADOR;
    private int EDAD_CONTADOR;
    private int PESO_CONTADOR;
    private String CORREO_CONTADOR;
    
    //GETTERS Y SETTERS

    public String getUUID_CONTADOR() {
        return UUID_CONTADOR;
    }

    public void setUUID_CONTADOR(String UUID_CONTADOR) {
        this.UUID_CONTADOR = UUID_CONTADOR;
    }

    public String getNOMBRE_CONTADOR() {
        return NOMBRE_CONTADOR;
    }

    public void setNOMBRE_CONTADOR(String NOMBRE_CONTADOR) {
        this.NOMBRE_CONTADOR = NOMBRE_CONTADOR;
    }

    public int getEDAD_CONTADOR() {
        return EDAD_CONTADOR;
    }

    public void setEDAD_CONTADOR(int EDAD_CONTADOR) {
        this.EDAD_CONTADOR = EDAD_CONTADOR;
    }
    
    public int getPESO_CONTADOR() {
        return PESO_CONTADOR;
    }

    public void setPESO_CONTADOR(int PESO_CONTADOR) {
        this.PESO_CONTADOR = PESO_CONTADOR;
    }

    public String getCORREO_CONTADOR() {
        return CORREO_CONTADOR;
    }

    public void setCORREO_CONTADOR(String CORREO_CONTADOR) {
        this.CORREO_CONTADOR = CORREO_CONTADOR;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addContador = conexion.prepareStatement("INSERT INTO tbContador(UUID_CONTADOR, NOMBRE_CONTADOR, EDAD_CONTADOR, PESO_CONTADOR, CORREO_CONTADOR) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addContador.setString(1, UUID.randomUUID().toString());
            addContador.setString(2, getNOMBRE_CONTADOR());
            addContador.setInt(3, getEDAD_CONTADOR());
            addContador.setInt(4, getPESO_CONTADOR());
            addContador.setString(5, getCORREO_CONTADOR());
            //Ejecutar la consulta
            addContador.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = claseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Contador", "Nombre", "Edad", "Peso", "Correo electrónico"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbContador");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_CONTADOR"), 
                    rs.getString("NOMBRE_CONTADOR"), 
                    rs.getInt("EDAD_CONTADOR"), 
                    rs.getInt("PESO_CONTADOR"),
                    rs.getString("CORREO_CONTADOR")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
      
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteContador = conexion.prepareStatement("delete from tbContador where UUID_CONTADOR = ?");
            deleteContador.setString(1, miId);
            deleteContador.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error metodo de eliminar" + e);
        }
    }
          
        public void cargarDatosTabla(frmContador vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbContadores.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbContadores.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = vista.jtbContadores.getValueAt(filaSeleccionada, 4).toString();
 
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTb);
        }
    }
        
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateContador = conexion.prepareStatement("update tbContador set NOMBRE_CONTADOR = ?, EDAD_CONTADOR  = ?, PESO_CONTADOR = ?, CORREO_CONTADOR = ? where UUID_CONTADOR = ?");

                updateContador.setString(1, getNOMBRE_CONTADOR());
                updateContador.setInt(2, getEDAD_CONTADOR());
                updateContador.setInt(3, getPESO_CONTADOR());
                updateContador.setString(4, getCORREO_CONTADOR());
                updateContador.setString(5, miUUId);
                updateContador.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Limpiar(frmContador vista){
        
            int filaSeleccionada = vista.jtbContadores.getSelectedRow();
    
            vista.txtNombre.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            vista.txtCorreo.setText("");
    }
    
    public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Contador", "Nombre", "Edad", "Peso", "Correo electrónico"});
        try {
            PreparedStatement searchContador = conexion.prepareStatement("SELECT * FROM tbContador WHERE NOMBRE_CONTADOR LIKE ? || '%'");
            searchContador.setString(1, txtBuscar.getText());
            ResultSet rs = searchContador.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_CONTADOR"), 
                    rs.getString("NOMBRE_CONTADOR"), 
                    rs.getInt("EDAD_CONTADOR"), 
                    rs.getInt("PESO_CONTADOR"),
                    rs.getString("CORREO_CONTADOR")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
          
}