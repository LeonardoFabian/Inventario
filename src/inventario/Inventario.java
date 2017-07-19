/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import Conexion.Conectar;
import Login.Login;
/**
 *
 * @author leonardo
 */
public class Inventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       //Conectar conectar = new Conectar();
       //conectar.insertData("persona", "nombre, apellido", " 'Eddy','Fabian' ");
        
        Login login = new Login();
        login.setVisible(true);
    }
    
}
