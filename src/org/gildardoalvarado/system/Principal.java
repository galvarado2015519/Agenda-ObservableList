/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gildardoalvarado.system;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.gildardoalvarado.Controller.PersonaController;

/**
 *
 * @author programacion
 */
public class Principal extends Application{
    private final String PAQUETE_VISTA = "/org/gildardoalvarado/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
   
    public void initialize(URL location, ResourceBundle resources) {
    }
   

    @Override
    public void start(Stage escenarioPrincipal){
        this.escenarioPrincipal = escenarioPrincipal;
        escenarioPrincipal.setTitle("Persona");
        VentanaPersona();
        escenarioPrincipal.show();
    }
        
    public void VentanaPersona(){
        try{
            PersonaController personaController = (PersonaController) cambiarEscena("PersonaView.fxml",600 ,400);
            personaController.setEscenarioPrincipal(this);
        }catch(Exception e){
                e.printStackTrace();
        }
    }
     
     public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA +fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
