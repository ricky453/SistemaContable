/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable.frames;

import java.awt.event.KeyEvent;
import sistemacontable.SistemaContable;
import sistemacontable.frames.frmCuentas;

/**
 *
 * @author Ricky
 */
public class frmValor extends javax.swing.JFrame {
    
    
    public frmValor() {
        initComponents();
        txtValor.requestFocus();
    }

    public void agregarValor(){
        int rowc = frmCuentas.modeloCuentasSeleccionadas.getRowCount();
        String valor [] = new String[2];
        valor[0]= txtCuenta.getText();
        valor[1]= txtValor.getText();
        frmCuentas.modeloCuentasSeleccionadas.addRow(valor);
        frmCuentas.tblCuentas.setModel(frmCuentas.modeloCuentasSeleccionadas);
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCuenta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnAgregar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 165, 234));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 270));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setText("$");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 20, 50));

        txtCuenta.setEditable(false);
        txtCuenta.setBackground(new java.awt.Color(72, 165, 234));
        txtCuenta.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        txtCuenta.setForeground(new java.awt.Color(204, 204, 204));
        txtCuenta.setBorder(null);
        jPanel1.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 190, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(72, 165, 234));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 190, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de la Cuenta:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 140, 50));

        txtValor.setBackground(new java.awt.Color(72, 165, 234));
        txtValor.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        txtValor.setBorder(null);
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });
        jPanel1.add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 120, 30));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(72, 165, 234));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 120, 10));

        btnAgregar.setBackground(new java.awt.Color(57, 137, 186));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Valor");
        btnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 140, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Valor de Cuenta:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 50));

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
        jPanel1.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 20, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 250));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       agregarValor();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        this.dispose();

    }//GEN-LAST:event_lblCerrarMouseClicked

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        agregarValor();
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        char s;
        s = evt.getKeyChar();
        int p=(int) evt.getKeyChar();

        // double actualizarPrecio = ((((CantidadActual)*(PrecioActual))+((detalleCompra.get(i).getCantidad())*(detalleCompra.get(i).getCostoUnitario())))/((detalleCompra.get(i).getCantidad())+CantidadActual));
        if (!Character.isDigit(s) && s != KeyEvent.VK_PERIOD && s!=KeyEvent.VK_BACK_SPACE && s!= KeyEvent.VK_ENTER) {
            getToolkit().beep();
            evt.consume();
        }else{
            if (p==46) {
                String cadena=txtValor.getText();
                int tamanio=cadena.length();
                for (int i = 0; i <= tamanio; i++) {
                    if (cadena.contains(".")) {
                        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                        getToolkit().beep();
                        evt.consume();
                    }
                }
            }
        }      
    }//GEN-LAST:event_txtValorKeyTyped

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
            java.util.logging.Logger.getLogger(frmValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmValor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCerrar;
    public static javax.swing.JTextField txtCuenta;
    public static javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
