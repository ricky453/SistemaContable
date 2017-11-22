/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable.frames;

import java.awt.Color;
import sistemacontable.SistemaContable;

/**
 *
 * @author Ricky
 */
public class frmDatosRazones extends javax.swing.JFrame {

    /**
     * Creates new form frmDatosRazones
     */
    public frmDatosRazones() {
        initComponents();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblComprasAnuales = new javax.swing.JLabel();
        lblNAccionesComunes = new javax.swing.JLabel();
        lblPagosPrincipal = new javax.swing.JLabel();
        lblGastosArrendamiento = new javax.swing.JLabel();
        lblAccPref = new javax.swing.JLabel();
        txtComprasAnuales = new javax.swing.JTextField();
        txtPrecioMercado = new javax.swing.JTextField();
        txtNAccionesComunes = new javax.swing.JTextField();
        txtPagosPrincipal = new javax.swing.JTextField();
        txtGastosArrendamiento = new javax.swing.JTextField();
        txtAccPreferentes = new javax.swing.JTextField();
        lblPrecioMercado = new javax.swing.JLabel();
        btnSeguir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 165, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel1.setText("Completa los Campos faltantes:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));

        lblComprasAnuales.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblComprasAnuales.setForeground(new java.awt.Color(255, 255, 255));
        lblComprasAnuales.setText("Compras Anuales");
        jPanel1.add(lblComprasAnuales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 220, 30));

        lblNAccionesComunes.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblNAccionesComunes.setForeground(new java.awt.Color(255, 255, 255));
        lblNAccionesComunes.setText("Nº Acciones Comunes en Circul.:");
        jPanel1.add(lblNAccionesComunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 220, 30));

        lblPagosPrincipal.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblPagosPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblPagosPrincipal.setText("Pagos del Principal:");
        jPanel1.add(lblPagosPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 220, 30));

        lblGastosArrendamiento.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblGastosArrendamiento.setForeground(new java.awt.Color(255, 255, 255));
        lblGastosArrendamiento.setText("Pagos/Gastos de Arrendamiento:");
        jPanel1.add(lblGastosArrendamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 220, 30));

        lblAccPref.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblAccPref.setForeground(new java.awt.Color(255, 255, 255));
        lblAccPref.setText("Dividendos de Acciones Preferentes:");
        jPanel1.add(lblAccPref, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        txtComprasAnuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComprasAnualesActionPerformed(evt);
            }
        });
        jPanel1.add(txtComprasAnuales, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 100, 30));

        txtPrecioMercado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioMercadoActionPerformed(evt);
            }
        });
        jPanel1.add(txtPrecioMercado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 100, 30));

        txtNAccionesComunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNAccionesComunesActionPerformed(evt);
            }
        });
        jPanel1.add(txtNAccionesComunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 100, 30));

        txtPagosPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagosPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(txtPagosPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 100, 30));

        txtGastosArrendamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGastosArrendamientoActionPerformed(evt);
            }
        });
        jPanel1.add(txtGastosArrendamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 100, 30));

        txtAccPreferentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccPreferentesActionPerformed(evt);
            }
        });
        jPanel1.add(txtAccPreferentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 100, 30));

        lblPrecioMercado.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblPrecioMercado.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioMercado.setText("Precio de Mercado por Acción:");
        jPanel1.add(lblPrecioMercado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 220, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 340));

        btnSeguir.setBackground(new java.awt.Color(57, 137, 186));
        btnSeguir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSeguir.setForeground(new java.awt.Color(255, 255, 255));
        btnSeguir.setText("¡Seguir!");
        btnSeguir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSeguir.setContentAreaFilled(false);
        btnSeguir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeguir.setFocusPainted(false);
        btnSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeguirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeguir, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 160, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAccPreferentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccPreferentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccPreferentesActionPerformed

    private void txtGastosArrendamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGastosArrendamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGastosArrendamientoActionPerformed

    private void txtPagosPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagosPrincipalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagosPrincipalActionPerformed

    private void txtNAccionesComunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNAccionesComunesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNAccionesComunesActionPerformed

    private void txtPrecioMercadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioMercadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioMercadoActionPerformed

    private void txtComprasAnualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComprasAnualesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprasAnualesActionPerformed

    private void btnSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeguirActionPerformed
        SistemaContable.GananciasAccionistas = SistemaContable.UtilidadPorDistribuir + Double.parseDouble(txtAccPreferentes.getText().toString());
        SistemaContable.GastosArrendamiento = Double.parseDouble(txtGastosArrendamiento.getText().toString());
        SistemaContable.PagoPrincipal = Double.parseDouble(txtPagosPrincipal.getText().toString());
        SistemaContable.NAccionesComunes = Double.parseDouble(txtNAccionesComunes.getText().toString());
        SistemaContable.PrecioMercado = Double.parseDouble(txtPrecioMercado.getText().toString());
        if(txtComprasAnuales.getText().equals("")||txtComprasAnuales.getText().equals("0")){
            SistemaContable.ComprasAnuales = (SistemaContable.CostoVendido * 0.70)/360;
        }else{
            SistemaContable.ComprasAnuales = (Double.parseDouble(txtComprasAnuales.getText().toString())/360);
        }
        frmHome.lblR1.setText(""+(SistemaContable.ActivosCorrientes/SistemaContable.PasivosCorrientes));
        frmHome.lblR2.setText(""+((SistemaContable.ActivosCorrientes-SistemaContable.Inventario)/SistemaContable.PasivosCorrientes));
        frmHome.lblR3.setText(""+(SistemaContable.ActivosCorrientes-SistemaContable.PasivosCorrientes));
        frmHome.lblR4.setText(""+(SistemaContable.CostoVendido/SistemaContable.Inventario));
        frmHome.lblR5.setText(""+(SistemaContable.CuentasXCobrar)/(SistemaContable.Ventas/360));

        frmHome.lblR7.setText(""+(SistemaContable.Ventas/SistemaContable.ActivosTotales));
        frmHome.lblR8.setText(""+(SistemaContable.PasivosTotales/SistemaContable.ActivosTotales));
        frmHome.lblR9.setText(""+(SistemaContable.UtilidadOperativa/SistemaContable.OtrosGastos));
        //lbR10.setText();
        frmHome.lblR11.setText(""+(SistemaContable.UtilidadBruta/SistemaContable.Ventas));
        frmHome.lblR12.setText(""+(SistemaContable.UtilidadOperativa/SistemaContable.Ventas));
        frmHome.lblR13.setText(""+(SistemaContable.UtilidadNeta/SistemaContable.Ventas));
        frmHome.lblR6.setText(""+SistemaContable.ComprasAnuales);
        frmHome.lblR14.setText(""+(SistemaContable.GananciasAccionistas/SistemaContable.NAccionesComunes));
        frmHome.lblR15.setText(""+(SistemaContable.GananciasAccionistas/SistemaContable.ActivosTotales));
        frmHome.lblR16.setText(""+(SistemaContable.GananciasAccionistas/SistemaContable.Capital));
        frmHome.lblR17.setText(""+(SistemaContable.PrecioMercado/(SistemaContable.GananciasAccionistas/SistemaContable.NAccionesComunes)));
        frmHome.lblR18.setText(""+(SistemaContable.PrecioMercado/(SistemaContable.Capital/SistemaContable.NAccionesComunes)));

    }//GEN-LAST:event_btnSeguirActionPerformed

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
            java.util.logging.Logger.getLogger(frmDatosRazones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDatosRazones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDatosRazones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDatosRazones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDatosRazones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeguir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAccPref;
    private javax.swing.JLabel lblComprasAnuales;
    private javax.swing.JLabel lblGastosArrendamiento;
    private javax.swing.JLabel lblNAccionesComunes;
    private javax.swing.JLabel lblPagosPrincipal;
    private javax.swing.JLabel lblPrecioMercado;
    private javax.swing.JTextField txtAccPreferentes;
    private javax.swing.JTextField txtComprasAnuales;
    private javax.swing.JTextField txtGastosArrendamiento;
    private javax.swing.JTextField txtNAccionesComunes;
    private javax.swing.JTextField txtPagosPrincipal;
    private javax.swing.JTextField txtPrecioMercado;
    // End of variables declaration//GEN-END:variables
}
