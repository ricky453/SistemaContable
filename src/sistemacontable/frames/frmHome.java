/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable.frames;

import clases.Conexion;
import clases.ControladorCuenta;
import clases.Cuenta;
import clases.ErrorSistemaContable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemacontable.SistemaContable;

/**
 *
 * @author Ricky
 */
public class frmHome extends javax.swing.JFrame {

    Object miFechas[][];
    ArrayList<Cuenta> fechas = new ArrayList();
    private static DefaultTableModel modeloMisEstados;
    boolean salir, salir2,salir3,salir4, salir5, salir6, salir7, salir8;
            
    public frmHome() {
        initComponents();
        pnlComparar.setVisible(false);
        pnlNuevos.setVisible(false);
        pnlVer.setVisible(false);
        lbl1.setVisible(false);
        lbl2.setVisible(false);
        lbl3.setVisible(false);
        pnlVerEstados.setVisible(false);
        lblEmpresa.setText(sistemacontable.SistemaContable.empresa);
        modeloMisEstados = (DefaultTableModel) tblMisEstados.getModel();
        
       
    }

    public void CargarFechas() throws ErrorSistemaContable{
        cmbFecha.removeAll();
        fechas = ControladorCuenta.ObtenerFechas(lblEmpresa.getText());
        miFechas = new Object[fechas.size()/1][1];
        
        int contador=0,fila=0;
        Iterator<Cuenta> iterador= fechas.iterator();
        String temporal="";
        while (iterador.hasNext()){
            miFechas[fila][0]=iterador.next();
            cmbFecha.addItem(""+miFechas[fila][0]);
            fila++;
        }
       
    }
    public void actualizarMisEstados(){
        try {           
            modeloMisEstados.setRowCount(0);
            ArrayList<Cuenta> listaMisEstados=new ArrayList();
            Object fila[]=new Object[2];
            listaMisEstados=ControladorCuenta.MisEstados(2, cmbFecha.getSelectedItem().toString(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaMisEstados.iterator();
            while(MisEstados.hasNext()){
                fila[0]= MisEstados.next();
                fila[1]= MisEstados.next();
                modeloMisEstados.addRow(fila);
                tblMisEstados.setModel(modeloMisEstados);
            }
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        public void ObtenerDatosEstado(){
        actualizarMisEstados();
        int i = 0;
        while(i<tblMisEstados.getRowCount()){
            
        //    V E N T A S    N E T A S    
        if(tblMisEstados.getValueAt(i, 0).equals("VENTAS NETAS")){
            SistemaContable.VentasNetas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());   
            ObtenerComprasTotales();
            i=tblMisEstados.getRowCount()-1;
            salir3=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(i, 0).equals("REBAJAS EN VENTAS")||tblMisEstados.getValueAt(i, 0).equals("REBAJAS SOBRE VENTAS")){
                SistemaContable.RebajasSobreVentas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                int a=0;
                while(a<tblMisEstados.getRowCount()){
                   if(tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES SOBRE VENTAS")||tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES EN VENTAS")){
                         SistemaContable.DevolucionesSobreVentas = Double.parseDouble(tblMisEstados.getValueAt(a, 1).toString());
                         int b=0;
                         while(b<tblMisEstados.getRowCount()){
                         if(tblMisEstados.getValueAt(b, 0).equals("VENTAS")||tblMisEstados.getValueAt(b, 0).equals("VENTAS TOTALES")){
                            SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                            ObtenerComprasTotales();
                            i=tblMisEstados.getRowCount()-1;
                            b=tblMisEstados.getRowCount()-1;
                            a=tblMisEstados.getRowCount()-1;
                            salir3=false;
                            break;
                            }b++;
                        }
                   }a++;
               }int b=0;
                while(b<tblMisEstados.getRowCount()){
                    if(tblMisEstados.getValueAt(b, 0).equals("VENTAS")||tblMisEstados.getValueAt(b, 0).equals("VENTAS TOTALES")){
                        SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                        System.out.println(SistemaContable.VentasNetas);
                        System.out.println("Rebajas de "+SistemaContable.RebajasSobreVentas);
                        System.out.println("Devoluciones de "+SistemaContable.DevolucionesSobreVentas);
                        ObtenerComprasTotales();
                        i=tblMisEstados.getRowCount()-1;
                        b=tblMisEstados.getRowCount()-1;
                        salir3=false;
                        break;
                    }b++;
                }                
            }else if(tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES SOBRE VENTAS")||tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES EN VENTAS")){
                SistemaContable.DevolucionesSobreVentas=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                int b=0;
                while(b<tblMisEstados.getRowCount()){
                    if(tblMisEstados.getValueAt(b, 0).equals("VENTAS")||tblMisEstados.getValueAt(b, 0).equals("VENTAS TOTALES")){
                        SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                        ObtenerComprasTotales();
                        i=tblMisEstados.getRowCount()-1;
                        b=tblMisEstados.getRowCount()-1;
                        salir3=false;
                        break;
                    }b++;
                }
            }else if(tblMisEstados.getValueAt(i, 0).equals("VENTAS")||tblMisEstados.getValueAt(i, 0).equals("VENTAS TOTALES")){
                    SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                    salir3=true;
                }
            }i++;
            if(salir3==true){
                ObtenerComprasTotales();
            }
        }
    }
    public void ObtenerComprasTotales(){
        //     C O M P R A S    T O T A L E S
        int a = 0;
        while(a<tblMisEstados.getRowCount()){
                  
        if(tblMisEstados.getValueAt(a, 0).equals("COMPRAS TOTALES")){
            SistemaContable.ComprasTotales=Double.parseDouble(tblMisEstados.getValueAt(a, 1).toString());
            ObtenerComprasNetas();
            a=tblMisEstados.getRowCount()-1;
            salir3=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(a, 0).equals("GASTOS EN COMPRAS")||tblMisEstados.getValueAt(a, 0).equals("GASTOS DE COMPRAS")||tblMisEstados.getValueAt(a, 0).equals("GASTOS DE COMPRA")){
                SistemaContable.GastosCompras=Double.parseDouble(tblMisEstados.getValueAt(a, 1).toString());
                int c=0;
                while(c<tblMisEstados.getRowCount()){
                if(tblMisEstados.getValueAt(c, 0).equals("COMPRAS")){
                    SistemaContable.ComprasTotales= (Double.parseDouble(tblMisEstados.getValueAt(c, 1).toString()))+SistemaContable.GastosCompras;
                    ObtenerComprasNetas();
                    System.out.println("Gastos de Compras de "+SistemaContable.GastosCompras);
                    System.out.println("Compras totales de "+SistemaContable.ComprasTotales);
                    a=tblMisEstados.getRowCount()-1;
                    c=tblMisEstados.getRowCount()-1;
                    salir3=false;
                    break;
                    }c++;   
                }
            }
            int c=0;
            while(c<tblMisEstados.getRowCount()){
                if(tblMisEstados.getValueAt(c, 0).equals("COMPRAS")){
                    SistemaContable.ComprasTotales= (Double.parseDouble(tblMisEstados.getValueAt(c, 1).toString()))+SistemaContable.GastosCompras;
                    salir3=true;
                    c=tblMisEstados.getRowCount()-1;
                    break;
                    }c++;   
                }
            }a++;
        }if(salir3==true){
            ObtenerComprasNetas();
        }
    }
    
    public void ObtenerComprasNetas(){
        int d = 0;
        while(d<tblMisEstados.getRowCount()){
        //       C O M P R A S    N E T A S
        if(tblMisEstados.getValueAt(d, 0).equals("COMPRAS NETAS")){
            SistemaContable.ComprasNetas = Double.parseDouble(tblMisEstados.getValueAt(d, 1).toString());  
            ObtenerDisponibilidad();
            d=tblMisEstados.getRowCount()-1;
            salir5=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(d, 0).equals("REBAJAS EN COMPRAS")||tblMisEstados.getValueAt(d, 0).equals("REBAJAS SOBRE COMPRAS")){
                SistemaContable.RebajasSobreCompras = Double.parseDouble(tblMisEstados.getValueAt(d, 1).toString());
                int a=0;
                while(a<tblMisEstados.getRowCount()){
                   if(tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES SOBRE COMPRAS")||tblMisEstados.getValueAt(a, 0).equals("DEVOLUCIONES EN COMPRAS")){
                        SistemaContable.DevolucionesSobreCompras = Double.parseDouble(tblMisEstados.getValueAt(a, 1).toString());
                        SistemaContable.ComprasNetas = SistemaContable.ComprasTotales - (SistemaContable.DevolucionesSobreCompras+SistemaContable.RebajasSobreCompras);  
                        ObtenerDisponibilidad();
                        d=tblMisEstados.getRowCount()-1;
                        a=tblMisEstados.getRowCount()-1;
                        salir5=false;
                        break;
                   }a++;
               }
               
            }else if(tblMisEstados.getValueAt(d, 0).equals("DEVOLUCIONES SOBRE COMPRAS")||tblMisEstados.getValueAt(d, 0).equals("DEVOLUCIONES EN COMPRAS")){
                SistemaContable.DevolucionesSobreCompras=Double.parseDouble(tblMisEstados.getValueAt(d, 1).toString());
                salir5=true;
                }                        
            }d++;
        }if(salir5==true){
            SistemaContable.ComprasNetas = SistemaContable.ComprasTotales - (SistemaContable.DevolucionesSobreCompras+SistemaContable.RebajasSobreCompras);   
            ObtenerDisponibilidad();
        }
    }
   
    public void ObtenerDisponibilidad(){
        int e = 0;
        salir6=true;
        while(e<tblMisEstados.getRowCount()){
        //   D I S P O N I B I L I D A D    D E    M E R C A N C I A S    P A R A     E L    P E R I O D O
        if(tblMisEstados.getValueAt(e, 0).equals("DISPONIBILIDAD DE MERCANCIAS PARA EL PERIODO")|| tblMisEstados.getValueAt(e, 0).equals("DISPONIBILIDAD DE MERCANCIAS")){
            SistemaContable.DisponibilidadMercanciasPeriodo=Double.parseDouble(tblMisEstados.getValueAt(e, 1).toString());
            ObtenerCostoVendido();
            e=tblMisEstados.getRowCount()-1;
            salir6=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(e, 0).equals("INVENTARIO INICIAL")){
                SistemaContable.DisponibilidadMercanciasPeriodo=(Double.parseDouble(tblMisEstados.getValueAt(e, 1).toString())+SistemaContable.ComprasNetas);
                ObtenerCostoVendido();
                e=tblMisEstados.getRowCount()-1;
                salir6=false;
                break;
                }
            }
            e++;   
        }if(salir6 == true){
            SistemaContable.DisponibilidadMercanciasPeriodo = SistemaContable.ComprasNetas;
            ObtenerCostoVendido();
        }
    }
    
    public void ObtenerCostoVendido(){
        int f = 0;
        salir7=false;
        while(f<tblMisEstados.getRowCount()){
        //    C O S T O   D E   L O   V E N D I D O
        if(tblMisEstados.getValueAt(f, 0).equals("COSTO DE LO VENDIDO")){
            SistemaContable.CostoVendido=Double.parseDouble(tblMisEstados.getValueAt(f, 1).toString());
            ObtenerUtilidadBruta();
            f=tblMisEstados.getRowCount()-1;
            salir7=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(f, 0).equals("INVENTARIO FINAL")){
                SistemaContable.CostoVendido=SistemaContable.DisponibilidadMercanciasPeriodo-(Double.parseDouble(tblMisEstados.getValueAt(f, 1).toString()));
                ObtenerUtilidadBruta();
                f=tblMisEstados.getRowCount()-1;
                salir7=false;
                break;
                }else{                
                      salir7=true;
                }
            }f++;   
        }if(salir7==true){
            SistemaContable.CostoVendido=SistemaContable.DisponibilidadMercanciasPeriodo;
            ObtenerUtilidadBruta();
        }
    }
    public void ObtenerUtilidadBruta(){
        int g = 0;
        while(g<tblMisEstados.getRowCount()){
        //    U T I L I D A D   B R U T A     
        if(tblMisEstados.getValueAt(g, 0).equals("UTILIDAD BRUTA")||tblMisEstados.getValueAt(g, 0).equals("MARGEN BRUTO EN VENTAS")||tblMisEstados.getValueAt(g, 0).equals("MARGEN BRUTO DE UTILIDAD")){
            SistemaContable.UtilidadBruta=Double.parseDouble(tblMisEstados.getValueAt(g, 1).toString());
            ObtenerUtilidadOperativa();
            g=tblMisEstados.getRowCount()-1;
            salir = false;
            break;
        }else{
            SistemaContable.UtilidadBruta = SistemaContable.VentasNetas-SistemaContable.CostoVendido;
            salir = true;
            }g++;
        }if(salir==true){
            ObtenerUtilidadOperativa();
        }
             
    }
    public void ObtenerUtilidadOperativa(){
        int h = 0;
        while(h<tblMisEstados.getRowCount()){
                    
        //    U T I L I D A D   O P E R A T I V A
        if(tblMisEstados.getValueAt(h, 0).equals("GASTO OPERATIVO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS OPERATIVOS")||tblMisEstados.getValueAt(h, 0).equals("GASTOS DE OPERACION")){
            SistemaContable.UtilidadOperativa=SistemaContable.UtilidadBruta-(Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString()));
            ObtenerUtilidadNeta();
            h=tblMisEstados.getRowCount()-1;
            salir2=false;
            break;
            
        }else{
            if(tblMisEstados.getValueAt(h, 0).equals("GASTO DE VENTAS")||tblMisEstados.getValueAt(h, 0).equals("GASTOS EN VENTAS")){
                SistemaContable.GastoVentas=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }else if(tblMisEstados.getValueAt(h, 0).equals("GASTO FINANCIERO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS FINANCIEROS")){
                SistemaContable.GastoFinanciero=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }else if(tblMisEstados.getValueAt(h, 0).equals("GASTO ADMINISTRATIVO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS ADMINISTRATIVOS")){
                SistemaContable.GastoAdministrativo=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }
            SistemaContable.UtilidadOperativa = SistemaContable.GastoVentas+SistemaContable.GastoFinanciero+SistemaContable.GastoAdministrativo;
            salir2=true;
        }   
        if(tblMisEstados.getValueAt(h, 0).equals("UTILIDAD OPERATIVA")){
            SistemaContable.UtilidadOperativa=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            ObtenerUtilidadNeta();
            salir2=false;
            }h++;
        }if(salir2==true){
            ObtenerUtilidadNeta();
        }
    }
    public void ObtenerUtilidadNeta(){
        int j = 0;
        while(j<tblMisEstados.getRowCount()){
        //   U T I L I D A D    N E T A
        if(tblMisEstados.getValueAt(j, 0).equals("UTILIDAD NETA")||tblMisEstados.getValueAt(j, 0).equals("UTILIDAD NETA ANTES DE IMPUESTOS")||tblMisEstados.getValueAt(j, 0).equals("UTILIDAD NETA ANTES DE IMPUESTO")){
            SistemaContable.UtilidadNeta=Double.parseDouble(tblMisEstados.getValueAt(j, 1).toString());
            ObtenerUtilidadDistribuir();
            j=tblMisEstados.getRowCount()-1;
            salir8=false;
            break;
        }else{
            if(tblMisEstados.getValueAt(j, 0).equals("OTROS GASTOS")){
                SistemaContable.OtrosGastos=Double.parseDouble(tblMisEstados.getValueAt(j, 1).toString());
            }else if(tblMisEstados.getValueAt(j, 0).equals("OTROS INGRESOS")||tblMisEstados.getValueAt(j, 0).equals("OTROS PRODUCTOS")){
                SistemaContable.OtrosIngresos=Double.parseDouble(tblMisEstados.getValueAt(j, 1).toString());
                }
                salir8=true;
                }j++;
            }if(salir8==true){
            SistemaContable.UtilidadNeta = (SistemaContable.UtilidadOperativa+SistemaContable.OtrosIngresos)-SistemaContable.OtrosGastos;
            ObtenerUtilidadDistribuir(); 
        }
    }        
    public void ObtenerUtilidadDistribuir(){
        int k = 0;
        while(k<tblMisEstados.getRowCount()){
        //   U T I L I D A D   P O R   D I S T R I B U I R    
        if(tblMisEstados.getValueAt(k, 0).equals("UTILIDAD DEL EJERCICIO")||tblMisEstados.getValueAt(k, 0).equals("UTILIDAD POR DISTRIBUIR")){
            SistemaContable.UtilidadPorDistribuir=Double.parseDouble(tblMisEstados.getValueAt(k, 1).toString());
            k=tblMisEstados.getRowCount()-1;
        }else{
            if(SistemaContable.UtilidadNeta<150000){
                SistemaContable.Impuestos=0.25;
                SistemaContable.ReservaLegal=SistemaContable.UtilidadNeta*0.07;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*0.07)+(SistemaContable.UtilidadNeta*SistemaContable.Impuestos));
            }else{
                SistemaContable.Impuestos=0.30;
                SistemaContable.ReservaLegal=SistemaContable.UtilidadNeta*0.07;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*0.07)+(SistemaContable.UtilidadNeta*SistemaContable.Impuestos));
                }
            }k++;
        }         
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tblMisEstados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlComparar = new javax.swing.JPanel();
        lblBienvenida15 = new javax.swing.JLabel();
        lblBienvenida16 = new javax.swing.JLabel();
        lblBienvenida17 = new javax.swing.JLabel();
        lblBienvenida18 = new javax.swing.JLabel();
        pnlVer = new javax.swing.JPanel();
        lblBienvenida7 = new javax.swing.JLabel();
        lblBienvenida8 = new javax.swing.JLabel();
        lblBienvenida9 = new javax.swing.JLabel();
        lblBienvenida10 = new javax.swing.JLabel();
        pnlNuevos = new javax.swing.JPanel();
        lblBienvenida3 = new javax.swing.JLabel();
        lblBienvenida4 = new javax.swing.JLabel();
        lblBienvenida5 = new javax.swing.JLabel();
        lblBienvenida6 = new javax.swing.JLabel();
        lblComparar = new javax.swing.JLabel();
        lblCrearEstados = new javax.swing.JLabel();
        lblVerEstados = new javax.swing.JLabel();
        pnlVerEstados = new javax.swing.JPanel();
        lblIndices = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        cmbFecha = new javax.swing.JComboBox<>();
        lblBalance = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblCerrar1 = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblBienvenida1 = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();

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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 165, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrar.setText("x");
        lblCerrar.setToolTipText("Cerrar Sesión");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        jPanel1.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 20, -1));

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 30, 20));

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 40, 20));

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 30, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 570, 30));

        pnlComparar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida15.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida15.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida15.setText("más eficientes.");
        pnlComparar.add(lblBienvenida15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida16.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida16.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida16.setText("Al tener 2 o más estados financieros en fechas distintas");
        pnlComparar.add(lblBienvenida16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida17.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida17.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida17.setText("de tu empresa, podrás hacer una comparación por medio");
        pnlComparar.add(lblBienvenida17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida18.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida18.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida18.setText("de los índices y así saber cuales períodos de fecha fueron");
        pnlComparar.add(lblBienvenida18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlComparar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 410, 90));

        pnlVer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida7.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida7.setText("para una fecha en específica.");
        pnlVer.add(lblBienvenida7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida8.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida8.setText("Aquí están guardados todos los estados financieros de tu");
        pnlVer.add(lblBienvenida8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida9.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida9.setText("empresa con la opción de seleccionarlos por una fecha.");
        pnlVer.add(lblBienvenida9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida10.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida10.setText("Verás el balance general, estado de resultados y los índices");
        pnlVer.add(lblBienvenida10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 410, 90));

        pnlNuevos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida3.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida3.setText("No es posible crear nuevos estados en un mismo año.");
        pnlNuevos.add(lblBienvenida3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida4.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida4.setText("Podrás crear nuevos estados financieros a partir de cuentas ");
        pnlNuevos.add(lblBienvenida4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida5.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida5.setText("y sus saldos. Cada vez que crees un estado financiero se ");
        pnlNuevos.add(lblBienvenida5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida6.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida6.setText("guardará para luego compararlo con fechas distintas. ");
        pnlNuevos.add(lblBienvenida6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlNuevos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 410, 90));

        lblComparar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblComparar.setForeground(new java.awt.Color(51, 51, 51));
        lblComparar.setText("Comparar Años");
        lblComparar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblComparar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCompararMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCompararMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCompararMouseExited(evt);
            }
        });
        jPanel1.add(lblComparar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, 40));

        lblCrearEstados.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblCrearEstados.setForeground(new java.awt.Color(51, 51, 51));
        lblCrearEstados.setText("Crear Estados Financieros");
        lblCrearEstados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCrearEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCrearEstadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCrearEstadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCrearEstadosMouseExited(evt);
            }
        });
        jPanel1.add(lblCrearEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 40));

        lblVerEstados.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVerEstados.setForeground(new java.awt.Color(51, 51, 51));
        lblVerEstados.setText("Ver mis Estados");
        lblVerEstados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVerEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseEntered(evt);
            }
        });
        jPanel1.add(lblVerEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, 40));

        pnlVerEstados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIndices.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblIndices.setForeground(new java.awt.Color(154, 154, 154));
        lblIndices.setText("Indices");
        lblIndices.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIndices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIndicesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIndicesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIndicesMouseEntered(evt);
            }
        });
        pnlVerEstados.add(lblIndices, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, 40));

        lblEstado.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(154, 154, 154));
        lblEstado.setText("Estado de Resultados");
        lblEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEstadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEstadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEstadoMouseExited(evt);
            }
        });
        pnlVerEstados.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, 40));

        pnlVerEstados.add(cmbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 190, 30));

        lblBalance.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblBalance.setForeground(new java.awt.Color(154, 154, 154));
        lblBalance.setText("Balance General");
        lblBalance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBalanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBalanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBalanceMouseExited(evt);
            }
        });
        pnlVerEstados.add(lblBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 40));

        jLabel1.setBackground(new java.awt.Color(72, 165, 234));
        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(72, 165, 234));
        jLabel1.setText("Selecciona el año que deseas ver:");
        pnlVerEstados.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 280, 30));

        lblCerrar1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar1.setText("x");
        lblCerrar1.setToolTipText("Cerrar");
        lblCerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrar1MouseClicked(evt);
            }
        });
        pnlVerEstados.add(lblCerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, -1));

        jPanel1.add(pnlVerEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 550, 290));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 570, 450));

        lblBienvenida.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("2017");
        getContentPane().add(lblBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 150, 60));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 360, 20));

        lblBienvenida1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblBienvenida1.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida1.setText("SISTEMA CONTABLE");
        getContentPane().add(lblBienvenida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 250, 50));

        lblEmpresa.setBackground(new java.awt.Color(72, 165, 234));
        lblEmpresa.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        lblEmpresa.setForeground(new java.awt.Color(72, 165, 234));
        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setText("CASA");
        getContentPane().add(lblEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 360, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        frmLogin lo = new frmLogin();
        lo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void lblCompararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCompararMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCompararMouseClicked

    private void lblCrearEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearEstadosMouseClicked
        frmCuentas cuentas = new frmCuentas();
        cuentas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblCrearEstadosMouseClicked

    private void lblVerEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerEstadosMouseClicked
        try {
            CargarFechas();
            pnlVerEstados.setVisible(true);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVerEstadosMouseClicked

    private void lblCrearEstadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearEstadosMouseEntered
        pnlNuevos.setVisible(true);
        lbl1.setVisible(true);
    }//GEN-LAST:event_lblCrearEstadosMouseEntered

    private void lblCrearEstadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearEstadosMouseExited
        pnlNuevos.setVisible(false);
        lbl1.setVisible(false);
    }//GEN-LAST:event_lblCrearEstadosMouseExited

    private void lblVerEstadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerEstadosMouseEntered
        pnlVer.setVisible(true);
        lbl2.setVisible(true);
    }//GEN-LAST:event_lblVerEstadosMouseEntered

    private void lblVerEstadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerEstadosMouseExited
        pnlVer.setVisible(false);
        lbl2.setVisible(false);
    }//GEN-LAST:event_lblVerEstadosMouseExited

    private void lblCompararMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCompararMouseEntered
        pnlComparar.setVisible(true);
        lbl3.setVisible(true);
    }//GEN-LAST:event_lblCompararMouseEntered

    private void lblCompararMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCompararMouseExited
        pnlComparar.setVisible(false);
        lbl3.setVisible(false);
    }//GEN-LAST:event_lblCompararMouseExited

    private void lblIndicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndicesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblIndicesMouseClicked

    private void lblEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseClicked
        actualizarMisEstados();
        ObtenerDatosEstado();
                        System.out.println("VENTAS NETAS "+SistemaContable.VentasNetas);
                        System.out.println("COMPRAS TOTALES "+ SistemaContable.ComprasTotales);
                        System.out.println("COMPRAS NETAS "+ SistemaContable.ComprasNetas);
                        System.out.println("DISPONIBILIDAD "+SistemaContable.DisponibilidadMercanciasPeriodo);
                        System.out.println("COSTO DE LO VENDIDO "+SistemaContable.CostoVendido);
                        System.out.println("UTILIDAD BRUTA "+SistemaContable.UtilidadBruta);
                        System.out.println("UTILIDAD OPERATIVA "+SistemaContable.UtilidadOperativa);
                        System.out.println("UTILIDAD NETA "+SistemaContable.UtilidadNeta);
                        System.out.println("UTILIDAD POR DISTRIBUIR "+SistemaContable.UtilidadPorDistribuir);
    }//GEN-LAST:event_lblEstadoMouseClicked

    private void lblBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalanceMouseClicked
        try {
            Map parametro = new HashMap();
            String archivo = getClass().getResource("/reporte/balanceGeneral.jasper").getPath();
            parametro.put("parameter1", cmbFecha.getSelectedItem().toString());
            Conexion cn = new Conexion();
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn.conexion);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.setTitle("BALANCE GENERAL");
        } catch (JRException ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblBalanceMouseClicked

    private void lblCerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrar1MouseClicked
        // TODO add your handling code here:
        pnlVerEstados.setVisible(false);
    }//GEN-LAST:event_lblCerrar1MouseClicked

    private void lblBalanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalanceMouseEntered
        lblBalance.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_lblBalanceMouseEntered

    private void lblBalanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalanceMouseExited
        lblBalance.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblBalanceMouseExited

    private void lblEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseEntered
        lblEstado.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_lblEstadoMouseEntered

    private void lblEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseExited
        lblEstado.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblEstadoMouseExited

    private void lblIndicesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndicesMouseEntered
        lblIndices.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_lblIndicesMouseEntered

    private void lblIndicesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndicesMouseExited
        lblIndices.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblIndicesMouseExited

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
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    public static javax.swing.JLabel lblBalance;
    public static javax.swing.JLabel lblBienvenida;
    public static javax.swing.JLabel lblBienvenida1;
    public static javax.swing.JLabel lblBienvenida10;
    public static javax.swing.JLabel lblBienvenida15;
    public static javax.swing.JLabel lblBienvenida16;
    public static javax.swing.JLabel lblBienvenida17;
    public static javax.swing.JLabel lblBienvenida18;
    public static javax.swing.JLabel lblBienvenida3;
    public static javax.swing.JLabel lblBienvenida4;
    public static javax.swing.JLabel lblBienvenida5;
    public static javax.swing.JLabel lblBienvenida6;
    public static javax.swing.JLabel lblBienvenida7;
    public static javax.swing.JLabel lblBienvenida8;
    public static javax.swing.JLabel lblBienvenida9;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblCerrar1;
    public static javax.swing.JLabel lblComparar;
    public static javax.swing.JLabel lblCrearEstados;
    public static javax.swing.JLabel lblEmpresa;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblIndices;
    public static javax.swing.JLabel lblVerEstados;
    private javax.swing.JPanel pnlComparar;
    private javax.swing.JPanel pnlNuevos;
    private javax.swing.JPanel pnlVer;
    private javax.swing.JPanel pnlVerEstados;
    private javax.swing.JTable tblMisEstados;
    // End of variables declaration//GEN-END:variables
}
