/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable.frames;

import clases.ControladorCuenta;
import clases.Cuenta;
import clases.ErrorSistemaContable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import sistemacontable.ColorTabla;
import sistemacontable.SistemaContable;

/**
 *
 * @author Ricky
 */
public class frmCuentas extends javax.swing.JFrame {

    JTableHeader tHeadActivos;
    JTableHeader tHeadPasivos;
    JTableHeader tHeadCuentas;
    public static DefaultTableModel modeloBalance;
    public static DefaultTableModel modeloResultados;
    public static DefaultTableModel modeloCuentasSeleccionadas;
    public static DefaultTableModel modeloMisEstados;
    ColorTabla r = new ColorTabla();
    String cuenta;
    int IdCuenta,IdEmpresa, IdFE;
    frmValor va = new frmValor();
    boolean encontrado;
    Date date = new Date();
    double RebajasSobreVentas = 0;
    double DevolucionesSobreVentas = 0;
    double GastosCompras = 0;
    double RebajasSobreCompras = 0;
    double DevolucionesSobreCompras = 0;
    
    public frmCuentas() {
        initComponents();
        colorTablaActivos();
        colorTablaPasivos();
        colorTablaCuentas();
        modeloBalance = (DefaultTableModel) tblBalanceGeneral.getModel();
        modeloResultados = (DefaultTableModel) tblEstadoResultados.getModel();
        modeloCuentasSeleccionadas = (DefaultTableModel) tblCuentas.getModel();
        modeloMisEstados = (DefaultTableModel) tblMisEstados.getModel();
        actualizarTablaBalance();
        actualizarTablaResultados();
        
        lblEmpresa.setText(SistemaContable.empresa);
    }

    public void actualizarMisEstados(){
            modeloMisEstados.setRowCount(0);           
            ArrayList<Cuenta> listaMisEstados=new ArrayList();
            Object fila[]=new Object[2];
            try {
            listaMisEstados=ControladorCuenta.MisEstados(txtAnio.getText(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaMisEstados.iterator();
                while(MisEstados.hasNext()){
                    fila[0]= MisEstados.next();
                    fila[1]= MisEstados.next();
                    modeloMisEstados.addRow(fila);
                    tblMisEstados.setModel(modeloMisEstados);
                }
            }
         catch (ErrorSistemaContable ex) {
             Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);        
         }
    } 
    
    public void ObtenerDatosEstado(){
        actualizarMisEstados();
        int i = 0;
        while(i<tblMisEstados.getRowCount()){
            
            //    V E N T A S    N E T A S
            
        if(tblMisEstados.getValueAt(i, 0).equals("VENTAS NETAS")){
            SistemaContable.VentasNetas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("REBAJAS EN VENTAS")||tblMisEstados.getValueAt(i, 0).equals("REBAJAS SOBRE VENTAS")){
                RebajasSobreVentas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                int a=0;
                while(a<tblMisEstados.getRowCount()){
                   if(tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES SOBRE VENTAS")||tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES EN VENTAS")){
                         DevolucionesSobreVentas = Double.parseDouble(tblMisEstados.getValueAt(a, 1).toString());
                   }
               }                
            }else if(tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES SOBRE VENTAS")||tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES EN VENTAS")){
                DevolucionesSobreVentas=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }
            int b=0;
            while(b<tblMisEstados.getRowCount()){
                if(tblMisEstados.getValueAt(b, 0).equals("VENTAS")||tblMisEstados.getValueAt(b, 0).equals("VENTAS TOTALES")){
                    SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString()))-(RebajasSobreVentas+DevolucionesSobreVentas);
                }   
            }
        }  
        
              //     C O M P R A S    T O T A L E S
              
        if(tblMisEstados.getValueAt(i, 0).equals("COMPRAS TOTALES")){
            SistemaContable.ComprasTotales=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("GASTOS EN COMPRAS")||tblMisEstados.getValueAt(i, 0).equals("GASTOS DE COMPRAS")||tblMisEstados.getValueAt(i, 0).equals("GASTOS DE COMPRA")){
                GastosCompras=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }
            int c=0;
            while(c<tblMisEstados.getRowCount()){
                if(tblMisEstados.getValueAt(c, 0).equals("COMPRAS")){
                    SistemaContable.ComprasTotales= (Double.parseDouble(tblMisEstados.getValueAt(c, 1).toString()))+GastosCompras;
                }   
            }
        }      
        
              //       C O M P R A S    N E T A S
              
        if(tblMisEstados.getValueAt(i, 0).equals("COMPRAS NETAS")){
            SistemaContable.VentasNetas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("REBAJAS EN COMPRAS")||tblMisEstados.getValueAt(i, 0).equals("REBAJAS SOBRE COMPRAS")){
                RebajasSobreCompras = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                int d=0;
                while(d<tblMisEstados.getRowCount()){
                   if(tblMisEstados.getValueAt(d, 0).equals("DEVOLUCIONES SOBRE COMPRAS")||tblMisEstados.getValueAt(d, 0).equals("DEVOLUCIONES EN COMPRAS")){
                         DevolucionesSobreCompras = Double.parseDouble(tblMisEstados.getValueAt(d, 1).toString());
                   }
               }                
            }else if(tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES SOBRE COMPRAS")||tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES EN COMPRAS")){
                DevolucionesSobreCompras=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }
            int e=0;
            while(e<tblMisEstados.getRowCount()){
                if(tblMisEstados.getValueAt(e, 0).equals("COMPRAS TOTALES")){
                    SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(e, 1).toString()))-(RebajasSobreCompras+DevolucionesSobreCompras);
                }   
            }
        }  
        
             //   D I S P O N I B I L I D A D    D E    M E R C A N C I A S    P A R A     E L    P E R I O D O
        if(tblMisEstados.getValueAt(i, 0).equals("DISPONIBILIDAD DE MERCANCIAS PARA EL PERIODO")|| tblMisEstados.getValueAt(i, 0).equals("DISPONIBILIDAD DE MERCANCIAS")){
            SistemaContable.DisponibilidadMercanciasPeriodo=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("INVENTARIO INICIAL")){
                SistemaContable.DisponibilidadMercanciasPeriodo=(Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString())+SistemaContable.ComprasNetas);
            }
        }    
        
            //    C O S T O   D E   L O   V E N D I D O
        
        if(tblMisEstados.getValueAt(i, 0).equals("COSTO DE LO VENDIDO")){
            SistemaContable.CostoVendido=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("INVENTARIO FINAL")){
                SistemaContable.CostoVendido=SistemaContable.DisponibilidadMercanciasPeriodo-(Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString()));
            }
        }    
             
           //    U T I L I D A D   B R U T A 
            
        if(tblMisEstados.getValueAt(i, 0).equals("UTILIDAD BRUTA")||tblMisEstados.getValueAt(i, 0).equals("MARGEN BRUTO EN VENTAS")||tblMisEstados.getValueAt(i, 0).equals("MARGEN BRUTO DE UTILIDAD")){
            SistemaContable.UtilidadBruta=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            SistemaContable.UtilidadBruta = SistemaContable.VentasNetas-SistemaContable.CostoVendido;
        }
        
           //    U T I L I D A D   O P E R A T I V A
        
        if(tblMisEstados.getValueAt(i, 0).equals("GASTO OPERATIVO")||tblMisEstados.getValueAt(i, 0).equals("GASTOS OPERATIVOS")||tblMisEstados.getValueAt(i, 0).equals("GASTOS DE OPERACION")){
            SistemaContable.UtilidadOperativa=SistemaContable.UtilidadBruta-(Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString()));
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("GASTO DE VENTAS")||tblMisEstados.getValueAt(i, 0).equals("GASTOS EN VENTAS")){
                SistemaContable.GastoVentas=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }else if(tblMisEstados.getValueAt(i, 0).equals("GASTO FINANCIERO")||tblMisEstados.getValueAt(i, 0).equals("GASTOS FINANCIEROS")){
                SistemaContable.GastoFinanciero=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }else if(tblMisEstados.getValueAt(i, 0).equals("GASTO ADMINISTRATIVO")||tblMisEstados.getValueAt(i, 0).equals("GASTOS ADMINISTRATIVOS")){
                SistemaContable.GastoAdministrativo=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }
            SistemaContable.UtilidadOperativa = SistemaContable.GastoVentas+SistemaContable.GastoFinanciero+SistemaContable.GastoAdministrativo;
        }   
        if(tblMisEstados.getValueAt(i, 0).equals("UTILIDAD OPERATIVA")){
            SistemaContable.UtilidadOperativa=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }
        
            //   U T I L I D A D    N E T A
        if(tblMisEstados.getValueAt(i, 0).equals("UTILIDAD NETA")||tblMisEstados.getValueAt(i, 0).equals("UTILIDAD NETA ANTES DE IMPUESTOS")||tblMisEstados.getValueAt(i, 0).equals("UTILIDAD NETA ANTES DE IMPUESTO")){
            SistemaContable.UtilidadNeta=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("OTROS GASTOS")){
                SistemaContable.OtrosGastos=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }else if(tblMisEstados.getValueAt(i, 0).equals("OTROS INGRESOS")||tblMisEstados.getValueAt(i, 0).equals("OTROS PRODUCTOS")){
                SistemaContable.OtrosIngresos=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
            }
            SistemaContable.UtilidadNeta = (SistemaContable.UtilidadOperativa+SistemaContable.OtrosIngresos)-SistemaContable.OtrosGastos;
        }
        
           //   U T I L I D A D   P O R   D I S T R I B U I R 
           
        if(tblMisEstados.getValueAt(i, 0).equals("UTILIDAD DEL EJERCICIO")||tblMisEstados.getValueAt(i, 0).equals("UTILIDAD POR DISTRIBUIR")){
            SistemaContable.UtilidadPorDistribuir=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
        }else{
            if(SistemaContable.UtilidadNeta<150000){
                SistemaContable.Impuestos=0.25;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*SistemaContable.ReservaLegal)+(SistemaContable.UtilidadNeta*SistemaContable.Impuestos));
            }else{
                SistemaContable.Impuestos=0.30;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*SistemaContable.ReservaLegal)+(SistemaContable.UtilidadNeta*SistemaContable.Impuestos));
               }
            }
        }
    }
    
    public void colorTablaActivos(){
        tHeadActivos = tblBalanceGeneral.getTableHeader();
        Font fuente = new Font("Segoe UI Light", Font.BOLD, 12);
        tHeadActivos.setBackground(jpnSeleccion.getBackground());
        tHeadActivos.setForeground(Color.WHITE);
        tHeadActivos.setFont(fuente);
    }
    public void colorTablaPasivos(){
        tHeadPasivos = tblEstadoResultados.getTableHeader();
        Font fuente = new Font("Segoe UI Light", Font.BOLD, 12);
        tHeadPasivos.setBackground(jpnSeleccion.getBackground());
        tHeadPasivos.setForeground(Color.WHITE);
        tHeadPasivos.setFont(fuente);
    }
    public void colorTablaCuentas(){
        tHeadCuentas = tblCuentas.getTableHeader();
        Font fuente = new Font("Segoe UI Light", Font.BOLD, 12);
        tHeadCuentas.setBackground(Color.WHITE);
        tHeadCuentas.setForeground(jpnSeleccion.getBackground());
        tHeadCuentas.setFont(fuente);
    }
    public void actualizarTablaBalance(){
            modeloBalance.setRowCount(0);           
            ArrayList<Cuenta> listaBalance=new ArrayList();
            Object fila[]=new Object[2];
            try {
            listaBalance=ControladorCuenta.ObtenerCuentas(1);
            //String[] nombreProveedores = new String []{"Tipo","Cuenta","Seleccionar"};
            //modeloBalance.setColumnIdentifiers(nombreProveedores);
            Iterator<Cuenta> balance=listaBalance.iterator();
                while(balance.hasNext()){
                    fila[0]= balance.next();
                    fila[1]= balance.next();
                    //fila[2]= balance.next();
                    modeloBalance.addRow(fila);
                    tblBalanceGeneral.setModel(modeloBalance);
                }
            }
         catch (ErrorSistemaContable ex) {
             Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);        
         }
    } 
    public void actualizarTablaResultados(){
            modeloResultados.setRowCount(0);           
            ArrayList<Cuenta> listaResultados=new ArrayList();
            Object fila[]=new Object[1];
            try {
            listaResultados=ControladorCuenta.ObtenerCuentas2(2);
            //String[] nombreProveedores = new String []{"Tipo","Cuenta","Seleccionar"};
            //modeloBalance.setColumnIdentifiers(nombreProveedores);
            Iterator<Cuenta> resultados=listaResultados.iterator();
                while(resultados.hasNext()){
                    fila[0]= resultados.next();
                    //fila[2]= balance.next();
                    modeloResultados.addRow(fila);
                    tblEstadoResultados.setModel(modeloResultados);
                }
            }
         catch (ErrorSistemaContable ex) {
             Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);        
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

        jScrollPane4 = new javax.swing.JScrollPane();
        tblMisEstados = new javax.swing.JTable();
        jpnSeleccion = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblNuevaCuenta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstadoResultados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBalanceGeneral = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblSeleccionarCuentas = new javax.swing.JLabel();

        tblMisEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane4.setViewportView(tblMisEstados);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnSeleccion.setBackground(new java.awt.Color(72, 165, 234));
        jpnSeleccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jpnSeleccion.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 10, -1));

        lblEmpresa.setBackground(new java.awt.Color(72, 165, 234));
        lblEmpresa.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        lblEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setText("CASA");
        jpnSeleccion.add(lblEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, 30));

        jScrollPane3.setBackground(new java.awt.Color(72, 165, 234));

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuenta", "Valor $"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCuentas.setSelectionBackground(new java.awt.Color(72, 165, 234));
        tblCuentas.setShowHorizontalLines(false);
        tblCuentas.setShowVerticalLines(false);
        tblCuentas.getTableHeader().setReorderingAllowed(false);
        tblCuentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCuentasKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblCuentas);
        if (tblCuentas.getColumnModel().getColumnCount() > 0) {
            tblCuentas.getColumnModel().getColumn(0).setResizable(false);
            tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblCuentas.getColumnModel().getColumn(1).setResizable(false);
        }

        jpnSeleccion.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 310, 450));

        jLabel4.setBackground(new java.awt.Color(72, 165, 234));
        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 58, 115));
        jLabel4.setText("Cuentas seleccionadas:");
        jpnSeleccion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 30));

        btnGenerar.setBackground(new java.awt.Color(57, 137, 186));
        btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar Estados Financieros");
        btnGenerar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnGenerar.setContentAreaFilled(false);
        btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerar.setFocusPainted(false);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jpnSeleccion.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 640, 250, 40));

        jLabel5.setBackground(new java.awt.Color(72, 165, 234));
        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(49, 58, 115));
        jLabel5.setText("Fecha:");
        jpnSeleccion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

        jLabel6.setBackground(new java.awt.Color(72, 165, 234));
        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 58, 115));
        jLabel6.setText("EMPRESA:");
        jpnSeleccion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, 30));
        jpnSeleccion.add(txtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 140, 30));

        getContentPane().add(jpnSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 330, 700));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 670, 30));

        lblNuevaCuenta.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblNuevaCuenta.setForeground(new java.awt.Color(153, 153, 153));
        lblNuevaCuenta.setText("Nueva Cuenta");
        lblNuevaCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNuevaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaCuentaMouseClicked(evt);
            }
        });
        getContentPane().add(lblNuevaCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, 40));

        jLabel1.setBackground(new java.awt.Color(72, 165, 234));
        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(49, 58, 115));
        jLabel1.setText("Estado de Resultados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 210, 30));

        tblEstadoResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEstadoResultados.getTableHeader().setReorderingAllowed(false);
        tblEstadoResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstadoResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstadoResultados);
        if (tblEstadoResultados.getColumnModel().getColumnCount() > 0) {
            tblEstadoResultados.getColumnModel().getColumn(0).setResizable(false);
            tblEstadoResultados.getColumnModel().getColumn(0).setPreferredWidth(250);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 570, 190));

        tblBalanceGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBalanceGeneral.getTableHeader().setReorderingAllowed(false);
        tblBalanceGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBalanceGeneralMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBalanceGeneral);
        if (tblBalanceGeneral.getColumnModel().getColumnCount() > 0) {
            tblBalanceGeneral.getColumnModel().getColumn(0).setResizable(false);
            tblBalanceGeneral.getColumnModel().getColumn(1).setResizable(false);
            tblBalanceGeneral.getColumnModel().getColumn(1).setPreferredWidth(230);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 570, 200));

        jLabel3.setBackground(new java.awt.Color(72, 165, 234));
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 58, 115));
        jLabel3.setText("Balance General");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 30));

        lblSeleccionarCuentas.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblSeleccionarCuentas.setText("Seleccionar Cuentas");
        lblSeleccionarCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSeleccionarCuentasMouseClicked(evt);
            }
        });
        getContentPane().add(lblSeleccionarCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblNuevaCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaCuentaMouseClicked
        try {
            lblNuevaCuenta.setForeground(java.awt.Color.black);
            lblSeleccionarCuentas.setForeground(java.awt.Color.lightGray);
            frmNuevaCuenta nc = new frmNuevaCuenta();
            nc.setVisible(true);
            //this.setEnabled(false);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblNuevaCuentaMouseClicked

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        Cuenta agregado = new Cuenta();
        if(tblCuentas.getRowCount()!=0){
            try {
                IdEmpresa = ControladorCuenta.ObtenerIDEmpresa(lblEmpresa.getText());
                agregado.setFecha(txtAnio.getText());
                agregado.setIdEmpresa(IdEmpresa);
                for(int i=0;i<tblCuentas.getRowCount();i++){
                    try {
                        IdCuenta = ControladorCuenta.ObtenerIDCuenta(tblCuentas.getValueAt(i, 0).toString());
                        agregado.setIdCuenta(IdCuenta);
                        agregado.setValor(tblCuentas.getValueAt(i, 1).toString());
                        ControladorCuenta.Agregar(agregado);
                        JOptionPane.showMessageDialog(rootPane, "Se han generado las cuentas!");
                        ObtenerDatosEstado();
                        System.out.println(SistemaContable.VentasNetas);
                        System.out.println(SistemaContable.ComprasTotales);
                        System.out.println(SistemaContable.ComprasNetas);
                        System.out.println(SistemaContable.DisponibilidadMercanciasPeriodo);
                        System.out.println(SistemaContable.CostoVendido);
                        System.out.println(SistemaContable.UtilidadBruta);
                        System.out.println(SistemaContable.UtilidadOperativa);
                        System.out.println(SistemaContable.UtilidadNeta);
                        System.out.println(SistemaContable.UtilidadPorDistribuir);
                    } catch (ErrorSistemaContable ex) {
                        Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (ErrorSistemaContable ex) {
                Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{JOptionPane.showMessageDialog(rootPane, "Debe de agregar cuentas");}
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void lblSeleccionarCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSeleccionarCuentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSeleccionarCuentasMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        actualizarTablaBalance();
        actualizarTablaResultados();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

    }//GEN-LAST:event_formWindowLostFocus

    private void tblBalanceGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBalanceGeneralMouseClicked
        if(evt.getClickCount()==2){ 
            encontrado=false;
            if(modeloCuentasSeleccionadas.getRowCount()>0){
                int i = 0;
                while(encontrado==false&&i<tblCuentas.getRowCount()){
                    encontrado = tblCuentas.getValueAt(i,0).equals(tblBalanceGeneral.getValueAt(tblBalanceGeneral.getSelectedRow(), 1));
                    i++;
                }
            }
            if(encontrado==false){
                cuenta = modeloBalance.getValueAt(tblBalanceGeneral.getSelectedRow(), 1).toString();
                SistemaContable.posCuenta = tblBalanceGeneral.getSelectedRow();
                va.setVisible(true);
                va.txtCuenta.setText(cuenta);    
                va.txtValor.setText("");
                va.txtValor.requestFocus();
            }else{JOptionPane.showMessageDialog(rootPane, "¡Ya está en la lista!");}
        }
    }//GEN-LAST:event_tblBalanceGeneralMouseClicked

    private void tblCuentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCuentasKeyPressed
        char c = evt.getKeyChar();
        if(c == (char) KeyEvent.VK_DELETE){
            int rowAct = tblCuentas.getSelectedRow();
            modeloCuentasSeleccionadas.removeRow(rowAct);
            tblCuentas.setModel(modeloCuentasSeleccionadas);
        }
    }//GEN-LAST:event_tblCuentasKeyPressed

    private void tblEstadoResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstadoResultadosMouseClicked
        if(evt.getClickCount()==2){ 
            encontrado=false;
            if(modeloCuentasSeleccionadas.getRowCount()>0){
                int i = 0;
                while(encontrado==false&&i<tblCuentas.getRowCount()){
                    encontrado = tblCuentas.getValueAt(i,0).equals(tblEstadoResultados.getValueAt(tblEstadoResultados.getSelectedRow(), 0));
                    i++;
                }
            }
            if(encontrado==false){
                cuenta = modeloResultados.getValueAt(tblEstadoResultados.getSelectedRow(), 0).toString();
                SistemaContable.posCuenta = tblEstadoResultados.getSelectedRow();
                va.setVisible(true);
                va.txtCuenta.setText(cuenta);    
                va.txtValor.setText("");
                va.txtValor.requestFocus();
            }else{JOptionPane.showMessageDialog(rootPane, "¡Ya está en la lista!");}
        }
    }//GEN-LAST:event_tblEstadoResultadosMouseClicked

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
            java.util.logging.Logger.getLogger(frmCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCuentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpnSeleccion;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblEmpresa;
    public static javax.swing.JLabel lblNuevaCuenta;
    public static javax.swing.JLabel lblSeleccionarCuentas;
    public static javax.swing.JTable tblBalanceGeneral;
    public static javax.swing.JTable tblCuentas;
    public static javax.swing.JTable tblEstadoResultados;
    private javax.swing.JTable tblMisEstados;
    private javax.swing.JTextField txtAnio;
    // End of variables declaration//GEN-END:variables
}
