package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Contadores;
import vista.frmContador;

public class ctrlContadores implements MouseListener, KeyListener{
    
    //LLAMAR CAPAS MODELO Y VISTA
    private Contadores modelo;
    private frmContador vista;
    
    //CREAR EL CONSTRUCTOR
    public ctrlContadores(Contadores modelo, frmContador vista){
    this.modelo = modelo;
    this.vista = vista;
    
    vista.btnAgregar.addMouseListener(this);
    modelo.Mostrar(vista.jtbContadores);
    
    vista.btnEliminar.addMouseListener(this);
    vista.jtbContadores.addMouseListener(this);
    vista.btnEditar.addMouseListener(this);
    vista.btnLimpiar.addMouseListener(this);
    vista.txtBuscar.addKeyListener(this);
    vista.btnBuscar.addMouseListener(this);
    
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()==vista.btnAgregar){
       
            modelo.setNOMBRE_CONTADOR(vista.txtNombre.getText());
            modelo.setEDAD_CONTADOR(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPESO_CONTADOR(Integer.parseInt(vista.txtPeso.getText()));
            modelo.setCORREO_CONTADOR(vista.txtCorreo.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbContadores);
        }
        
        if (e.getSource() == vista.btnEliminar) {
            
            modelo.Eliminar(vista.jtbContadores);
            modelo.Mostrar(vista.jtbContadores);
        }
        
        if (e.getSource() == vista.jtbContadores) {
            
            modelo.cargarDatosTabla(vista);
        }
        
        if (e.getSource() == vista.btnEditar) {
            
            modelo.setNOMBRE_CONTADOR(vista.txtNombre.getText());
            modelo.setEDAD_CONTADOR(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPESO_CONTADOR(Integer.parseInt(vista.txtPeso.getText()));
            modelo.setCORREO_CONTADOR(vista.txtCorreo.getText());
            
            modelo.Actualizar(vista.jtbContadores);
            modelo.Mostrar(vista.jtbContadores);
        }
        
        if (e.getSource() == vista.btnLimpiar) {
            modelo.Limpiar(vista);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.jtbContadores, vista.txtBuscar);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
