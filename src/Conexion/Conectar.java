/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

/**
 *
 * @author leonardo
 */
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Conectar {
    
    private java.sql.Connection Conector = null;
    
    private static final String driver = "com.mysql.jdbc.Driver";//driver que esta en libreria para hacer conexion
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String database = "curso_inventario";
    private static final String user = "root";
    private static final String password = "";
    
    
    public Conectar(){
        
        try{
            Class.forName(driver);
            Conector = DriverManager.getConnection(url+database, user, password);//conectarse a la base de datos
            if (Conector != null){
                System.out.println("Conectado");
                //JOptionPane.showMessageDialog(null, "Conectado");
            }
        }catch (ClassNotFoundException ex){
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexion " + ex);
            
        }catch(SQLException ex){
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para retornar la conexion
    public boolean actualizarDatos(String table_name, String campos, String where){
        try{
            //String Query = "DELETE FROM " + table_name + " WHERE ID = \" "+ ID + "\"";
            //update usuario set nombre='Leonardo', tipo = 'cajero' where id = '1'
            String Query = " UPDATE " + table_name + " SET " + campos + " WHERE " + where + "";
            System.out.println(Query);
            Statement st = this.Conector.createStatement();
            st.executeUpdate(Query);
            System.out.println("Datos actualizados correctamente");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al marcar como completada la reparacion");
            return false;
        }
    }
    
    //metodo para desconectarnos de la base de datos
    public void insertData(String table_name, String campos, String valores){
        try{
            String sqlInsert = " INSERT INTO " + table_name + "("+campos+") VALUES ("+valores+")";
            System.out.println("consulta insert "+sqlInsert);
            Statement st = this.Conector.createStatement();
            st.executeUpdate(sqlInsert);
            System.out.println("Datos insertados correctamente");
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
    }catch(SQLException ex){
        System.out.println(ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());
        JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
    public String optenerUltimoID(String table_name){
        String id = "1";
        try{
            String Query = " SELECT max(id) as id FROM "+ table_name;
            Statement st = this.Conector.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            if (resultSet.next()){
                id = resultSet.getString("id");
                System.out.println("ID: " + resultSet.getString("ID"));                
            }
                    
                    
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error en la adquisicion de datos getValues(String table_name)");
        }
        return id;
    }
    
    public java.sql.ResultSet obtenerDatosParaTabla(String table_name, String campos, String otros){
        java.sql.ResultSet resultSet = null;
        try{
            String Query = " SELECT "+campos+" FROM "+table_name+ " "+otros;
            System.out.println(Query);
            Statement st = this.Conector.createStatement();
            resultSet = st.executeQuery(Query);
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en la adquisicion de datos getValues(String table_name)");
        }
        return resultSet;
    }
    
}
