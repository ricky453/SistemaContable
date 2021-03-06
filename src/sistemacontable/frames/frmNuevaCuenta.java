package sistemacontable.frames;

import clases.ControladorCuenta;
import clases.Cuenta;
import clases.ErrorSistemaContable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sistemacontable.frames.frmCuentas.lblSeleccionarCuentas;
import static sistemacontable.frames.frmCuentas.tblCuentas;
import static sistemacontable.frames.frmCuentas.tblEstadoResultados;

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
    boolean find;
    //frmPrincipal prin = new frmPrincipal();
    
    public frmNuevaCuenta() throws ErrorSistemaContable {
        initComponents();
        CargarEstados();
        lblAdv.setVisible(false);
        txtCuenta.requestFocus();
       // lbl1.setVisible(false);
        //chkGasto.setVisible(false);
       
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
        }CargarTipos();
       
    }
    public void CargarTipos() throws ErrorSistemaContable{
    cmbTipo.removeAllItems();

    if(cmbEstado.getSelectedItem().equals("BALANCE GENERAL")){
        tipoCuenta = ControladorCuenta.ObtenerTipo(1,2,3);
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
        CargarSubTipos();
    }else if(cmbEstado.getSelectedItem().equals("ESTADO DE RESULTADOS")){
        tipoCuenta = ControladorCuenta.ObtenerTipo(4,4,4);
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
        CargarSubTipos();
    }else{
        
    }

    }
    public void CargarSubTipos() throws ErrorSistemaContable{
        cmbSubTipo.removeAllItems();
        subtipoCuenta.clear();
        if(cmbTipo.getSelectedItem().equals("ACTIVO")||cmbTipo.getSelectedItem().equals("PASIVO")||cmbTipo.getSelectedItem().equals("PATRIMONIO")){
        subtipoCuenta = ControladorCuenta.ObtenerSubTipo(1,2,3);
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
        }else if(cmbTipo.getSelectedItem().equals("GASTOS")){
        subtipoCuenta = ControladorCuenta.ObtenerSubTipo(4,5,6);
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
    }
    public void limpiar(){
        txtCuenta.setText("");
        cmbEstado.setSelectedIndex(0);
        cmbTipo.setSelectedIndex(0);
        cmbSubTipo.setSelectedIndex(0);
        txtCuenta.requestFocus();
    }
    
    public void agregar(){
        encontrado = false;
        if(frmCuentas.tblBalanceGeneral.getRowCount()>0){
            int i = 0;
            while(encontrado==false&&i<frmCuentas.tblBalanceGeneral.getRowCount()){
                encontrado = txtCuenta.getText().equals(frmCuentas.tblBalanceGeneral.getValueAt(i, 1));
                i++;
            }
        }
       if(frmCuentas.tblEstadoResultados.getRowCount()>0){
            int i = 0;
            while(encontrado==false&&i<frmCuentas.tblEstadoResultados.getRowCount()){
                encontrado = txtCuenta.getText().equals(frmCuentas.tblEstadoResultados.getValueAt(i, 0));
                i++;
            }
        }
        if(encontrado==false){        
        int idCuenta;
        Cuenta agregado = new Cuenta();
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
                     if(chkGasto.isSelected()){
                           agregado.setIdTipoCuenta(Tipo[cmbTipo.getSelectedIndex()][0].toString());
                           agregado.setIdTipoSubCuenta(SubTipo[cmbSubTipo.getSelectedIndex()][0].toString());
                     }else{
                         agregado.setIdTipoCuenta("0");
                     }
                }else{
                agregado.setIdTipoCuenta(Tipo[cmbTipo.getSelectedIndex()][0].toString());
                agregado.setIdTipoSubCuenta(SubTipo[cmbSubTipo.getSelectedIndex()][0].toString());
                }
                ControladorCuenta.AgregarCuentas(agregado);
                JOptionPane.showMessageDialog(rootPane, "¡Cuenta agregada exitosamente!");
                sistemacontable.frames.frmCuentas.actualizarTablaBalance();
                sistemacontable.frames.frmCuentas.actualizarTablaResultados();
                limpiar();
            } catch (ErrorSistemaContable ex) {
                Logger.getLogger(frmNuevaCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }else{
            JOptionPane.showMessageDialog(rootPane, "¡Ya existe esa cuenta!");
            txtCuenta.requestFocus();
            txtCuenta.selectAll();
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
        chkGasto = new javax.swing.JCheckBox();
        lbl1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(72, 165, 234));
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnNuevaCuenta.setBackground(new java.awt.Color(72, 165, 234));
        jpnNuevaCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpnNuevaCuentaFocusGained(evt);
            }
        });
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
        jpnNuevaCuenta.add(lblAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 170, 50));

        txtCuenta.setBackground(new java.awt.Color(72, 165, 234));
        txtCuenta.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        txtCuenta.setBorder(null);
        txtCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaKeyTyped(evt);
            }
        });
        jpnNuevaCuenta.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 400, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(72, 165, 234));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpnNuevaCuenta.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 400, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre de la Cuenta:");
        jpnNuevaCuenta.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 160, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Estado Financiero:");
        jpnNuevaCuenta.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 140, 50));

        cmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoItemStateChanged(evt);
            }
        });
        jpnNuevaCuenta.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 220, 30));

        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });
        jpnNuevaCuenta.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 120, 30));

        jpnNuevaCuenta.add(cmbSubTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 180, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo:");
        jpnNuevaCuenta.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, 50));

        chkGasto.setBackground(new java.awt.Color(72, 165, 234));
        chkGasto.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        chkGasto.setForeground(new java.awt.Color(255, 255, 255));
        chkGasto.setText("Es Gasto");
        chkGasto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkGastoItemStateChanged(evt);
            }
        });
        jpnNuevaCuenta.add(chkGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 120, 80, 30));
        jpnNuevaCuenta.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 60, 10));

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
            try {
                cmbTipo.setVisible(false);
                cmbSubTipo.setVisible(false);
                lblAdv.setVisible(true);
                lbl1.setVisible(true);
                chkGasto.setVisible(true);
                CargarTipos();
            } catch (ErrorSistemaContable ex) {
                Logger.getLogger(frmNuevaCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                cmbTipo.setVisible(true);
                cmbSubTipo.setVisible(true);
                lblAdv.setVisible(false);
                lbl1.setVisible(false);
                chkGasto.setVisible(false);
                chkGasto.setSelected(false);
                CargarTipos();
            } catch (ErrorSistemaContable ex) {
                Logger.getLogger(frmNuevaCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void txtCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyTyped
       char mayu=evt.getKeyChar();
        char c =  evt.getKeyChar();
        
        if( c < (char) 'a' || c > (char) 'z'){
            if( c < (char) 'A' || c > (char) 'Z'){
                if(c != (char) KeyEvent.VK_SPACE){
            if(c != (char) KeyEvent.VK_ENTER){
            evt.consume();
            }else{
                
            }
            }
        }
        }
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }

        
    }//GEN-LAST:event_txtCuentaKeyTyped

    private void jpnNuevaCuentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpnNuevaCuentaFocusGained

    }//GEN-LAST:event_jpnNuevaCuentaFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

    }//GEN-LAST:event_formWindowGainedFocus

    private void chkGastoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkGastoItemStateChanged
        if(chkGasto.isSelected()){
            lblAdv.setVisible(false);
            cmbTipo.setVisible(true);
            cmbSubTipo.setVisible(true);
        }else{
            lblAdv.setVisible(true);
            cmbTipo.setVisible(false);
            cmbSubTipo.setVisible(false);
        }
    }//GEN-LAST:event_chkGastoItemStateChanged

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
    private javax.swing.JCheckBox chkGasto;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbSubTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpnNuevaCuenta;
    private javax.swing.JSeparator lbl1;
    private javax.swing.JLabel lblAdv;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JTextField txtCuenta;
    // End of variables declaration//GEN-END:variables
}
