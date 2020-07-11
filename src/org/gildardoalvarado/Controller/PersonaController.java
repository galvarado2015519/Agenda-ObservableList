
package org.gildardoalvarado.Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gildardoalvarado.DB.Conexion;
import org.gildardoalvarado.system.Principal;
import org.gildardoalvarado.bean.Persona;

public class PersonaController implements Initializable {

    private Principal escenarioPrincipal;
    private ObservableList<Persona> listaPersona;
    
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtTelefono;
    
    @FXML private TableView tblPersona;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colApellido;
    @FXML private TableColumn colTelefono;
    
    
    @Override
    
    public void initialize(URL location, ResourceBundle resources) {
        
            cargarDatos();
        
    }

    public void agregar(){
        Persona registro = new Persona();
        registro.setNombre(txtNombre.getText());
        registro.setApellido(txtApellido.getText());
        registro.setTelefono(txtTelefono.getText());
        
        try{
            PreparedStatement procedimiento = Conexion.obtener().prepareCall("{call sp_AgregarPersonas(?,?,?)}");
            procedimiento.setString(1,registro.getNombre());
            procedimiento.setString(2,registro.getApellido());
            procedimiento.setString(3,registro.getTelefono());
            procedimiento.execute();
                    cargarDatos();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public void cargarDatos(){
        tblPersona.setItems(getPersona());
        colNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Persona,String>("telefono"));
    }
    
    public ObservableList<Persona> getPersona(){
        ArrayList<Persona> lista = new ArrayList<Persona>();
        
        try{
            PreparedStatement procedimiento = Conexion.obtener().prepareCall("{call sp_ListarPersona()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Persona( resultado.getString("nombre"),
                                   resultado.getString("apellido"),
                                   resultado.getString("telefono")));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaPersona = FXCollections.observableList(lista);

    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    } 
}
