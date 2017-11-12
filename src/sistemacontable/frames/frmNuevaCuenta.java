/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable.frames;

import clases.ControladorCuenta;
import clases.Cuenta;
import clases.ErrorSistemaContable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sistemacontable.frames.frmCuentas.lblSeleccionarCuentas;

/**
 *
 * @author Ricky
 */
public class frmNuevaCuenta extends javax.swing.JFrame {

    ArrayList<Cuenta> estadosfinancieros = new ArrayList();
    Object Estados[][];
    ArrayList<Cuenta> tipoCuenta = new ArrayList();
    Object Tipo[][];
    ArrayList<Cuenta> subtipoCuenta = new ArrayList();
    Object SubTipo[][];
    boolean encontrado;
    //frmPrincipal prin = new frmPrincipal();
    
    public frmNuevaCuenta() throws ErrorSistemaContable {
        initComponents();
        CargarEstados();
        CargarTipos();
        CargarSubTipos();
        lblAdv.setVisible(false);
        txtCuenta.requestFocus();
       
    }

    public void CargarEstados() throws ErrorSistemaContable{
        estadosfinancieros = ControladorCuenta.ObtenerEstados();
        Estados = new Object[estadosfinancieros.size()/2][2];
        
        int contador=0,fila=0;
        Iterator<Cuenta> iterador= estadosfinancieros.iterator();
        String temporal="";
        while (iterador.hasNext()){
            Estados[fila][0]=iterador.next();
            Estados[fila][1]=iterador.next();
            cmbEstado.addItem(""+Estados[fila][1]);
            fila++;
        }
       
    }
    public void CargarTipos() throws ErrorSistemaContable{
        tipoCuenta = ControladorCuenta.ObtenerTipo();
        Tipo = new Object[tipoCuenta.size()/2][2];
        
        int contador=0,fila=0;
        Iterator<Cuenta> iterador= tipoCuenta.iterator();
        String temporal="";
        while (iterador.hasNext()){
            Tipo[fila][0]=iterador.next();
            Tipo[fila][1]=iterador.next();
            cmbTipo.addItem(""+Tipo[fila][1]);
            fila++;
        }
    }
    public void CargarSubTipos() throws ErrorSistemaContable{
        subtipoCuenta = ControladorCuenta.ObtenerSubTipo();
        SubTipo = new Object[subtipoCuenta.size()/2][2];
        
        int contador=0,fila=0;
        Iterator<Cuenta> iterador= subtipoCuenta.iterator();
        String temporal="";
        while (iterador.hasNext()){
            SubTipo[fila][0]=iterador.next();
            SubTipo[fila][1]=iterador.next();
            cmbSubTipo.addItem(""+SubTipo[fila][1]);
            fila++;
        }
    }
    public void limpiar(){
        txtCuenta.setText("");
        cmbEstado.setSelectedIndex(0);
        cmbTipo.setSelectedIndex(0);
        cmbSubTipo.setSelectedIndex(0);
        txtCuenta.requestFocus();
    }
    
    public void agregar(){
        int idCuenta;
        Cuenta agregado = new Cuenta();
        encontrado = false;
        if(txtCuenta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba un nombre a la cuenta");
            txtCuenta.requestFocus();
        }else{
            try {
                idCuenta = ControladorCuenta.ObtenerID();
                agregado.setIdCuenta(idCuenta+1);
                agregado.setCuenta(txtCuenta.getText());
                agregado.setIdEstadoFinanciero(Integer.parseInt(Estados[cmbEstado.getSelectedIndex()][0].toString()));
                if(cmbEstado.getSelectedIndex()==1){

                }else{
                agregado.setIdTipoCuenta(Tipo[cmbTipo.getSelectedIndex()][0].toString());
                agregado.setIdTipoSubCuenta(SubTipo[cmbSubTipo.getSelectedIndex()][0].toString());
                }
                ControladorCuenta.AgregarCuentas(agregado);
                JOptionPane.showMessageDialog(rootPane, "¡Cuenta agregada exitosamente!");
                limpiar();
            } catch (ErrorSistemaContable ex) {
                Logger.getLogger(frmNuevaCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnNuevaCuenta = new javax.swing.JPanel();
        btnAgregar1 = new javax.swing.JButton();
        lblCerrar = new javax.swing.JLabel();
        lblAdv = new javax.swing.JLabel();
        txtCuenta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        cmbTipo = new javax.swing.JComboBox<>();
        cmbSubTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(72, 165, 234));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(700, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnNuevaCuenta.setBackground(new java.awt.Color(72, 165, 234));
        jpnNuevaCuenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar1.setBackground(new java.awt.Color(57, 137, 186));
        btnAgregar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setText("Agregar Cuenta");
        btnAgregar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnAgregar1.setContentAreaFilled(false);
        btnAgregar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar1.setFocusPainted(false);
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jpnNuevaCuenta.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 160, 40));

        lblCerrar.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrar.setText("x");
        lblCerrar.setToolTipText("Cerrar");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        jpnNuevaCuenta.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 20, -1));

        lblAdv.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblAdv.setText("No posee clasificación.");
        jpnNuevaCuenta.add(lblAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 140, 50));

        txtCuenta.setBackground(new java.awt.Color(72, 165, 234));
        txtCuenta.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        txtCuenta.setBorder(null);
        jpnNuevaCuenta.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 400, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(72, 165, 234));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpnNuevaCuenta.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 400, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre de la Cuenta:");
        jpnNuevaCuenta.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 140, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Estado Financiero:");
        jpnNuevaCuenta.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 140, 50));

        cmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoItemStateChanged(evt);
            }
        });
        jpnNuevaCuenta.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 190, 30));

        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });
        jpnNuevaCuenta.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 120, 30));

        jpnNuevaCuenta.add(cmbSubTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo:");
        jpnNuevaCuenta.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, 50));

        getContentPane().add(jpnNuevaCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        frmCuentas.lblSeleccionarCuentas.setForeground(java.awt.Color.black);
        frmCuentas.lblNuevaCuenta.setForeground(java.awt.Color.lightGray);
        
        this.dispose();
        
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void cmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoItemStateChanged
        if(cmbEstado.getSelectedIndex()==1){
            cmbTipo.setVisible(false);
            cmbSubTipo.setVisible(false);
            lblAdv.setVisible(true);
        }else{
            cmbTipo.setVisible(true);
            cmbSubTipo.setVisible(true);
            lblAdv.setVisible(false);
        }
    }//GEN-LAST:event_cmbEstadoItemStateChanged

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged
        if(cmbTipo.getSelectedIndex()==2){
            cmbSubTipo.setSelectedIndex(2);
            cmbSubTipo.setEnabled(false);            
        }else{
            cmbSubTipo.setEnabled(true);
        }            
    }//GEN-LAST:event_cmbTipoItemStateChanged

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmNuevaCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNuevaCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNuevaCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNuevaCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmNuevaCuenta().setVisible(true);
                } catch (ErrorSistemaContable ex) {
                    Logger.getLogger(frmNuevaCuenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbSubTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpnNuevaCuenta;
    private javax.swing.JLabel lblAdv;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JTextField txtCuenta;
    // End of variables declaration//GEN-END:variables
}
