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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enrique
 */
public class frmModificarCuentas extends javax.swing.JFrame {

    int idEmpresa=0;
 
    public frmModificarCuentas() {
        initComponents();        

//        cargandoTabla("",cmbEstados.getSelectedIndex()+1,fechaAnio.getYear(),idEmpresa);
    }
    
    public void cargandoTabla(String cuenta,int idFinanciero,int anio,int idEmpresa){
        DefaultTableModel modeloTabla=new DefaultTableModel();   
        //modeloTabla = (DefaultTableModel) tblCuentas.getModel();
        
        ArrayList<Cuenta> misCuentas=new ArrayList<Cuenta>();
        Object [] fila=new Object[3];
        String[] campos = {"Id Cuenta", "Cuenta", "Valor"};
        try {
            modeloTabla.setColumnIdentifiers(campos);
            misCuentas=ControladorCuenta.FiltroCuentas(cuenta,idFinanciero,anio,idEmpresa);
            Iterator ite=misCuentas.iterator();
            while (ite.hasNext()) {
                fila[0]=ite.next();
                fila[1]=ite.next();
                fila[2]=ite.next();
                modeloTabla.addRow(fila);
            }
            tblCuentas.setModel(modeloTabla);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmModificarCuentas.class.getName()).log(Level.SEVERE, null, ex);
        
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
        lblNombreEmpresa = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        fechaAnio = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        txtCuenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtModificar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        cmbEstados = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(72, 165, 234));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 165, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 220, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EMPRESA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, -1, -1));

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cuenta", "Cuenta", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCuentas);
        if (tblCuentas.getColumnModel().getColumnCount() > 0) {
            tblCuentas.getColumnModel().getColumn(0).setResizable(false);
            tblCuentas.getColumnModel().getColumn(1).setResizable(false);
            tblCuentas.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 880, 190));
        jPanel1.add(fechaAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 80, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel2.setText("AÑO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, 30));

        txtCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaKeyTyped(evt);
            }
        });
        jPanel1.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 450, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel4.setText("BUSCAR CUENTA:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 30));

        txtModificar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txtModificar.setForeground(new java.awt.Color(255, 255, 255));
        txtModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtModificar.setText("Modificar");
        txtModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtModificarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtModificarMouseExited(evt);
            }
        });
        jPanel1.add(txtModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 180, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel5.setText("MODIFICAR CUENTAS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, 30));

        lblCerrar.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e4ad(0)_482.png"))); // NOI18N
        lblCerrar.setToolTipText("Cerrar");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 50));

        cmbEstados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadosItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 200, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel3.setText("ESTADOS FINANCIEROS: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyReleased
        String cuenta=txtCuenta.getText();
        int idFinanciero=cmbEstados.getSelectedIndex()+1;
        int anio=fechaAnio.getYear();
        
        
        try {
            idEmpresa=ControladorCuenta.ObtenerIDEmpresa(lblNombreEmpresa.getText());
            cargandoTabla(cuenta,idFinanciero,anio,idEmpresa);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmModificarCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_txtCuentaKeyReleased

    private void txtCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtCuentaKeyTyped

    private void cmbEstadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadosItemStateChanged

    }//GEN-LAST:event_cmbEstadosItemStateChanged

    private void txtModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtModificarMouseClicked
        if(tblCuentas.getSelectedRow()!=-1){
            int seleccion; 
            frmModificandoCuenta pm = new frmModificandoCuenta();
            pm.setVisible(true);
//            this.dispose();
            seleccion = tblCuentas.getSelectedRow();
            pm.txtId.setText(tblCuentas.getValueAt(seleccion, 0).toString());
            pm.txtNombre.setText((tblCuentas.getValueAt(seleccion, 1).toString()));
            pm.txtValor.setText((tblCuentas.getValueAt(seleccion, 2).toString()));
            pm.anio=fechaAnio.getYear();
            pm.id=idEmpresa;
            
            
            pm.txtNombre.requestFocus();
            pm.txtNombre.selectAll();
            pm.txtId.setEnabled(false);
            pm.setLocationRelativeTo(null);
        }else{
            JOptionPane.showMessageDialog(null, "Selecciones un dato de la tabla por favor" );
        }
    }//GEN-LAST:event_txtModificarMouseClicked

    private void txtModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtModificarMouseEntered
        txtModificar.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_txtModificarMouseEntered

    private void txtModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtModificarMouseExited
        txtModificar.setForeground(java.awt.Color.white);
    }//GEN-LAST:event_txtModificarMouseExited

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblCerrarMouseClicked

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
            java.util.logging.Logger.getLogger(frmModificarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmModificarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmModificarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmModificarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmModificarCuentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cmbEstados;
    public com.toedter.calendar.JYearChooser fechaAnio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCerrar;
    public javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JTable tblCuentas;
    public javax.swing.JTextField txtCuenta;
    public static javax.swing.JLabel txtModificar;
    // End of variables declaration//GEN-END:variables
}
