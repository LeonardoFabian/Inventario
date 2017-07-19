/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Conexion.Conectar;//MySql
import Formularios.Menu;//MainMenu
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.sun.awt.AWTUtilities;
import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author leonardo
 */

public class Login extends javax.swing.JFrame {
    
    private Conectar conector = null;
    private String usuarioID = "1";
    private String usuarioNombre = "";
    private String usuarioTipo = "";
    //private javax.swing.JButton Boton2;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
       // this.setSize(700,600);
        //this.setLocation(350,300);
        conector = new Conectar();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        /*
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        
        jTextField1.setText("jTextField1");

// Code adding the component to the parent container - not shown here
jTextField1.setOpaque(false);
jPasswordField1.setOpaque(false);
        */
    }
    
    
    
    public void loguear (){
        String usuario = this.jTextField1.getText();
        String clave = new String(this.jPasswordField1.getPassword());
        
        if (usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo usuario esta vacio");
        }else if(clave.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo clave esta vacio");
        }else{
            try{
                String table_name = "usuario";
                String campos = " * ";
                String otros = " where nombre = '"+usuario+"' and clave = '"+clave+"'";
                java.sql.ResultSet resultSet = this.conector.obtenerDatosParaTabla(table_name,campos,otros);
                if( resultSet.first()){
                    usuarioID = resultSet.getString("id");
                    usuarioNombre = resultSet.getString("nombre");
                    usuarioTipo = resultSet.getString("tipo");
                    Menu menu = new Menu(this.conector,this.usuarioID,this.usuarioNombre,this.usuarioTipo);
                    menu.setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o clave no correcta");
                    //System.out.println("");
                }
                
            }catch (SQLException ex){
                System.out.println(ex.getCause().toString());//ME QUEDE AHI
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/LOGO.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 720, 590));

        jTextField1.setBackground(new java.awt.Color(25, 82, 68));
        jTextField1.setFont(new java.awt.Font("Garuda", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(28, 154, 119));
        jTextField1.setBorder(null);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 340, 40));

        jPasswordField1.setBackground(new java.awt.Color(25, 82, 68));
        jPasswordField1.setFont(new java.awt.Font("Garuda", 0, 18)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(28, 154, 119));
        jPasswordField1.setBorder(null);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 540, 340, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/USER.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 810, 120));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/PWD.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 840, 230));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/LOGIN-OFF.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/LOGIN-ON.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 450, 450));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/EXIT-OFF.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/EXIT-ON.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 390, 430));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EXPORTAR/FORM.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 910, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1534, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        this.KeyEnter(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        this.loguear();
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3MousePressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        this.KeyEnter(evt);
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    public void KeyEnter(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            this.loguear();
        }
    }
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
    });
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
   
}