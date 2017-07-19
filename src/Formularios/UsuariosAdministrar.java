/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author leonardo
 */
import Conexion.Conectar;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class UsuariosAdministrar extends javax.swing.JFrame {

    /**
     * Creates new form UsuarioAdministrar
     */
    
    private String usuarioID = "1";
    private String CurrentUsuarioID = "1";
    private String usuarioNombre = "admin";
    private Conectar conector = null;
    
    private String Buscador = "";
    
    public void setUsuarioID(String usuarioID){
        this.usuarioID = usuarioID;
    }
    
    public void setUsuarioNombre(String usuarioNombre){
        this.usuarioNombre = usuarioNombre;
    }
    
    public void setConector(Conectar conector){
        this.conector = conector;
    }
    
    public UsuariosAdministrar(Conectar conector,String usuarioID,String usuarioNombre) {
        initComponents();
        this.setConector(conector);
        this.setUsuarioID(usuarioID);
        this.setUsuarioNombre(usuarioNombre);
        this.cargarTabla();
        this.MostrarBotones(true, false, false);
    }
    
    public void BuscadorCliente(){
        this.Buscador = "";
        if(!(this.jTextFieldBuscador.getText().isEmpty())){
            this.Buscador = " and concat(nombre,'',tipo,'',c.id) like '%"+this.jTextFieldBuscador.getText()+"%'";
            //this.cargarTabla();
        }
        this.cargarTabla();
    }
    
    public void insert(){
        String usuario = jTextField1.getText();
        String clave = new String(this.jPasswordField1.getPassword());
        String tipo = this.jComboBox1.getSelectedItem().toString();
        
        if(usuario.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo usuario esta vacio");
        }else if(clave.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo clave esta vacio");
        }else if(clave.length() <= 5){
            JOptionPane.showMessageDialog(null, "La clave tiene que tener mas de 5 caracteres");
        }else{
            String table_name = "usuario";
            String campos = " * ";
            String otros = " where nombre = '"+usuario+"' ";
            java.sql.ResultSet resultSet = this.conector.obtenerDatosParaTabla(table_name, campos, otros);
            
            try{
                if(!(resultSet.first())){
                    String tablel = "usuario";
                    String camposl = "nombre, clave, tipo, usuario_id, fecha";
                    String valoresl = " '"+usuario+"','"+clave+"','"+tipo+"','"+this.usuarioID+"',now()";
                    
                    this.conector.insertData(tablel, camposl, valoresl);
                    JOptionPane.showMessageDialog(null,"Datos insertados");
                    
                    this.jTextField1.setText("");
                    this.jPasswordField1.setText("");
                    this.jComboBox1.setSelectedIndex(0);
                    this.cargarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "El usuario ya existe, favor ingrese otro usuario");
                }
            }catch(SQLException ex){
                Logger.getLogger(UsuariosAdministrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public void editar(){
        String usuario = this.jTextField1.getText();
        String clave = new String(this.jPasswordField1.getPassword());
        String tipo = this.jComboBox1.getSelectedItem().toString();
        
        if(usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo usuario esta vacio");
        }else if(clave.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo clave esta vacio");
        }else if(clave.length() <= 5){
            JOptionPane.showMessageDialog(null, "La clave tiene que tener mas de 5 caracteres");            
        }else{
            String table = "usuario";
            String campos = "nombre='"+usuario+"',clave='"+clave+"',tipo='"+tipo+"' ";
            String where = " id = '"+this.CurrentUsuarioID+"' ";
            
            this.conector.actualizarDatos(table, campos, where);
            JOptionPane.showMessageDialog(null,"Datos modificados correctamente");
            
            this.jTextField1.setText("");
            this.jPasswordField1.setText("");
            this.jComboBox1.setSelectedIndex(0);
            this.cargarTabla();
        }
        
    }
    
    
    public void limpiar(){
        this.jTextField1.setText("");
        this.jPasswordField1.setText("");
        this.jComboBox1.setSelectedIndex(0);
    }
    
    
    public void eliminar(){
        int index = this.jTable1.getSelectedRow();
        
        if(index >= 0){
            if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea "+ 
                "eliminar este usuario?","Eliminar usuario",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION){
                this.CurrentUsuarioID = this.jTable1.getValueAt(index, 0).toString();
                
                String table = "usuario";
                String campos = "visible=false";
                String where = " id = '"+this.CurrentUsuarioID+"' ";
                
                this.conector.actualizarDatos(table, campos, where);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
                this.limpiar();
                this.cargarTabla();            
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    
    public void MostrarDato(){
        int index = this.jTable1.getSelectedRow();
        
        if(index >= 0){
            String id = this.jTable1.getValueAt(index, 0).toString();
            //String nombre = this.jTable1.getValueAt(index, 0).toString();
            //String tipo = this.jTable1.getValueAt(index, 0).toString();
            //JOptionPane.showMessageDialog(null,nombre+""+id+""+tipo);
            
            try{
                String table_name = "usuario";
                String campos = " * ";
                String otros = " where id = '"+id+"' ";
                java.sql.ResultSet resultSet = this.conector.obtenerDatosParaTabla(table_name,campos,otros);
                
                if( resultSet.first() ){
                    this.CurrentUsuarioID = resultSet.getString("id");
                    this.jTextField1.setText(resultSet.getString("nombre"));
                    this.jPasswordField1.setText(resultSet.getString("clave"));
                    this.jComboBox1.setSelectedItem(resultSet.getString("tipo"));
                    
                    this.MostrarBotones(false,true,true);
                }else{
                    JOptionPane.showMessageDialog(null,"No hay registro");
                    //System.out.println("");
                }
                
            }catch (SQLException ex){
                System.out.println(ex.getCause().toString());
            }
        }else{
            JOptionPane.showMessageDialog(null,"No hay fila seleccionada");
        }
    }
    
    public void MostrarBotones(boolean b1, boolean b2, boolean b3){
        this.jButton1.setVisible(b1);
        this.jButton2.setVisible(b2);
        this.jButton3.setVisible(b3);
    }
    
    public void cargarTabla(){
        try{
            String table_name = "usuario";
            String campos = " * ";
            String otros = " where visible = true "+this.Buscador;
            java.sql.ResultSet resultSet = this.conector.obtenerDatosParaTabla(table_name, campos, otros);
            
            if( resultSet.first() ){
                int total = 0;
                do{
                    total++;                    
                }while(resultSet.next());
                resultSet.first();
                
                String[] titulos = {"ID","USUARIO","TIPO"};
                Object[][] fila = new Object[total][10];
                
                int c = 0;
                
                do{
                    fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("nombre");
                    fila[c][2] = resultSet.getString("tipo");
                    
                    c++;
                }while(resultSet.next());
                DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
                
                this.jTable1.setModel(modelo);
                this.MostrarBotones(true,false,false);
            }else{
                JOptionPane.showMessageDialog(null,"No hay registro");
                //System.out.println("");
            }
        }catch(SQLException ex){
            System.out.println(ex.getCause().toString());
        }
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldBuscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Tipo");

        jLabel2.setText("Usuario");

        jLabel3.setText("Clave");

        jButton1.setText("Editar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jButton3.setText("Agregar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        jLabel4.setText("Que desea buscar?");

        jTextFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1)
                        .addComponent(jLabel2)
                        .addComponent(jPasswordField1)
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                            .addComponent(jButton3)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap(369, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        this.MostrarDato();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        this.MostrarBotones(true, false, false);
        this.limpiar();
    }//GEN-LAST:event_jButton2MousePressed

    private void jTextFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorKeyReleased
        // TODO add your handling code here:
        this.BuscadorCliente();
    }//GEN-LAST:event_jTextFieldBuscadorKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdministrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdministrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdministrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdministrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new UsuariosAdministrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
