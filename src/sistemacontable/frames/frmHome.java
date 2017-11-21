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
import clases.Estado;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    private static DefaultTableModel modeloBG;
    private static DefaultTableModel EstadosParaReporte;
    private static DefaultTableModel AnalisisVerticalERTableModel;
    private static DefaultTableModel AnalisisVerticalBGTableModel;
    DecimalFormat df = new DecimalFormat("#.00");
    boolean salir, salir2,salir3,salir4, salir5, salir6, salir7, salir8;
            
    public frmHome() {
        initComponents();
        pnlComparar.setVisible(false);
        pnlNuevos.setVisible(false);
        pnlVer.setVisible(false);
        pnlAnalisis.setVisible(false);
        pnlAnalisisVertical.setVisible(false);
        pnlIndices.setVisible(false);
        pnlIndices2.setVisible(false);
        pnlAnalisisVerticalBG.setVisible(false);
        pnlAnalisisVerticalER.setVisible(false);
        lbl1.setVisible(false);
        lbl2.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);
        pnlVerEstados.setVisible(false);
        lblEmpresa.setText(sistemacontable.SistemaContable.empresa);
        modeloMisEstados = (DefaultTableModel) tblMisEstados.getModel();
        modeloBG = (DefaultTableModel) tblBG.getModel();
        EstadosParaReporte = (DefaultTableModel) tblEstadosReporte.getModel();
        AnalisisVerticalERTableModel = (DefaultTableModel) tblAnalisisVerticalER.getModel();
        AnalisisVerticalBGTableModel = (DefaultTableModel) tblAnalisisVerticalBG.getModel();
    }

    public  void CargarFechas() throws ErrorSistemaContable{
        cmbFecha.removeAllItems();
        cmbFecha1.removeAllItems();
        fechas = ControladorCuenta.ObtenerFechas(lblEmpresa.getText());
        miFechas = new Object[fechas.size()/1][1];
        
        int contador=0,fila=0;
        Iterator<Cuenta> iterador= fechas.iterator();
        String temporal="";
        while (iterador.hasNext()){
            miFechas[fila][0]=iterador.next();
            cmbFecha.addItem(""+miFechas[fila][0]);
            cmbFecha1.addItem(""+miFechas[fila][0]);
            fila++;
        }
       
    }
    public void generarEstado(){
        Estado em;// Instaciamos la clase empleado
        List <Estado>lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for(int i=0; i<tblEstadosReporte.getRowCount(); i++){ // Iterena cada fila de la tabla
            em = new Estado(tblEstadosReporte.getValueAt(i, 0).toString(),tblEstadosReporte.getValueAt(i,1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
            tblEstadosReporte.getValueAt(i, 2).toString(),tblEstadosReporte.getValueAt(i, 3).toString(),tblEstadosReporte.getValueAt(i,4).toString(),
            tblEstadosReporte.getValueAt(i,5).toString(),tblEstadosReporte.getValueAt(i,6).toString(),tblEstadosReporte.getValueAt(i,7).toString(),
            tblEstadosReporte.getValueAt(i,8).toString(),tblEstadosReporte.getValueAt(i,9).toString(),tblEstadosReporte.getValueAt(i,10).toString(),
            tblEstadosReporte.getValueAt(i,11).toString(),tblEstadosReporte.getValueAt(i,12).toString(),tblEstadosReporte.getValueAt(i,13).toString(),
            tblEstadosReporte.getValueAt(i,14).toString(),tblEstadosReporte.getValueAt(i,15).toString(),tblEstadosReporte.getValueAt(i,16).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        JasperReport reporte; // Instaciamos el objeto reporte
        String path = getClass().getResource("/reporte/estadoResultados2.jasper").getPath(); //Ponemos la localizacion del reporte creado
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Se carga el reporte de su localizacion
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista)); //Agregamos los parametros para llenar el reporte
            JasperViewer viewer = new JasperViewer(jprint, false); //Se crea la vista del reportes
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true); //Se vizualiza el reporte
        } catch (JRException ex) {
           
        }
    }
    public void generarTablaReporteEstado(){      
            EstadosParaReporte.setRowCount(0);
            Object fila[]=new Object[17];
            fila[0]= SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas;
            fila[1]= SistemaContable.VentasNetas;
            fila[2]= SistemaContable.GastosCompras; 
            fila[3]= SistemaContable.ComprasTotales;
            fila[4]= SistemaContable.RebajasSobreCompras+SistemaContable.DevolucionesSobreCompras;
            fila[5]= SistemaContable.ComprasNetas;
            fila[6]= SistemaContable.DisponibilidadMercanciasPeriodo;
            fila[7]= SistemaContable.CostoVendido;
            fila[9]= SistemaContable.UtilidadBruta;
            fila[8]= SistemaContable.GastosOperativos;    
            fila[10]= SistemaContable.UtilidadOperativa; 
            fila[11]= SistemaContable.OtrosGastos;
            fila[12]= SistemaContable.OtrosIngresos;
            fila[13]= SistemaContable.UtilidadNeta;
            fila[14]= SistemaContable.ReservaLegal;
            fila[15]= SistemaContable.Impuestos;
            fila[16]= SistemaContable.UtilidadPorDistribuir;
            EstadosParaReporte.addRow(fila);
            tblEstadosReporte.setModel(EstadosParaReporte);
    }
    //OBTENER DATOS DEL BALANCE GENERAL
        public void actualizarMiBG(){
        try {           
            modeloBG.setRowCount(0);
            ArrayList<Cuenta> listaBG=new ArrayList();
            Object fila[]=new Object[4];
            listaBG=ControladorCuenta.ObtenerBG(cmbFecha.getSelectedItem().toString(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaBG.iterator();
            while(MisEstados.hasNext()){
                fila[0]= MisEstados.next();
                fila[1]= MisEstados.next();
                fila[2]= MisEstados.next();
                fila[3]= MisEstados.next();
                modeloBG.addRow(fila);
                tblMisEstados.setModel(modeloBG);
            }
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public void actualizarMiBG2(){
        try {           
            modeloBG.setRowCount(0);
            ArrayList<Cuenta> listaBG=new ArrayList();
            Object fila[]=new Object[4];
            listaBG=ControladorCuenta.ObtenerBG(cmbFecha1.getSelectedItem().toString(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaBG.iterator();
            while(MisEstados.hasNext()){
                fila[0]= MisEstados.next();
                fila[1]= MisEstados.next();
                fila[2]= MisEstados.next();
                fila[3]= MisEstados.next();
                modeloBG.addRow(fila);
//                tblMisEstados.setModel(modeloBG);
            }
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void ActivosCorrientes(){
        int i = 0;
        SistemaContable.ActivosCorrientes=0;
        while(i<tblBG.getRowCount()){
        if(tblBG.getValueAt(i, 1).equals("1") && tblBG.getValueAt(i, 2).equals("1")){
            SistemaContable.ActivosCorrientes=SistemaContable.ActivosCorrientes+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
    public void ActivosNoCorrientes(){
        int i = 0;
        SistemaContable.ActivosNoCorrientes=0;
        while(i<tblBG.getRowCount()){   
        if(tblBG.getValueAt(i, 1).equals("1") && tblBG.getValueAt(i, 2).equals("2")){
            SistemaContable.ActivosNoCorrientes=SistemaContable.ActivosNoCorrientes+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
    public void PasivosCorrientes(){
        int i = 0;
        SistemaContable.PasivosCorrientes=0;
        while(i<tblBG.getRowCount()){
        if(tblBG.getValueAt(i, 1).equals("2") && tblBG.getValueAt(i, 2).equals("1")){
            SistemaContable.PasivosCorrientes=SistemaContable.PasivosCorrientes+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
    public void PasivosNoCorrientes(){
        int i = 0;
        SistemaContable.PasivosNoCorrientes=0;
        while(i<tblBG.getRowCount()){    
        if(tblBG.getValueAt(i, 1).equals("2") && tblBG.getValueAt(i, 2).equals("2")){
            SistemaContable.PasivosNoCorrientes=SistemaContable.PasivosNoCorrientes+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
    public void Capital(){
        int i = 0;
        SistemaContable.Capital=0;
        while(i<tblBG.getRowCount()){
        if(tblBG.getValueAt(i, 1).equals("3") && tblBG.getValueAt(i, 2).equals("3")){
            SistemaContable.Capital=SistemaContable.Capital+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
    public void Inventario(){
        int i = 0;
        SistemaContable.Inventario=0;
        while(i<tblBG.getRowCount()){
        if(tblBG.getValueAt(i, 0).equals("INVENTARIO")){
            SistemaContable.Inventario=SistemaContable.Inventario+Double.parseDouble(tblBG.getValueAt(i, 3).toString());
            }
        i++;}        
    }
        
    public void generarBG(){
        actualizarMiBG();
        actualizarMiBG2();
        ActivosCorrientes();
        ActivosNoCorrientes();
        SistemaContable.ActivosTotales = SistemaContable.ActivosCorrientes+SistemaContable.ActivosNoCorrientes;
        PasivosCorrientes();
        PasivosNoCorrientes();
        SistemaContable.PasivosTotales = SistemaContable.PasivosCorrientes+SistemaContable.PasivosNoCorrientes;
        Capital();
        Inventario();
        //SistemaContable.ValorEnLibros = SistemaContable.Capital/;
                
    }
    //OBTENER DATOS DEL ESTADO DE RESULTADOS    
    public void actualizarMisEstados(){
        try {           
            modeloMisEstados.setRowCount(0);
            ArrayList<Cuenta> listaMisEstados=new ArrayList();
            Object fila[]=new Object[3];
            listaMisEstados=ControladorCuenta.MisEstados(2, cmbFecha.getSelectedItem().toString(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaMisEstados.iterator();
            while(MisEstados.hasNext()){
                fila[0]= MisEstados.next();
                fila[1]= MisEstados.next();
                fila[2]= MisEstados.next();
                modeloMisEstados.addRow(fila);
                tblMisEstados.setModel(modeloMisEstados);
            }
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarMisEstados2(){ 
        try {           
            modeloMisEstados.setRowCount(0);
            ArrayList<Cuenta> listaMisEstados2=new ArrayList();
            Object fila[]=new Object[3];
            listaMisEstados2=ControladorCuenta.MisEstados(2, cmbFecha1.getSelectedItem().toString(), SistemaContable.empresa);
            Iterator<Cuenta> MisEstados=listaMisEstados2.iterator();
            while(MisEstados.hasNext()){
                fila[0]= MisEstados.next();
                fila[1]= MisEstados.next();
                fila[2]= MisEstados.next();
                modeloMisEstados.addRow(fila);
               //tblAnalisisVerticalER.setModel(modeloMisEstados);
            }
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObtenerDatosEstado(){
        actualizarMisEstados();
        actualizarMisEstados2();
        int i = 0;
        SistemaContable.VentasNetas=0;
        SistemaContable.RebajasSobreVentas=0;
        SistemaContable.DevolucionesSobreVentas=0;
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
                            SistemaContable.Ventas = Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString());
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
                        SistemaContable.Ventas = Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString());
                        System.out.println("Rebajas de "+SistemaContable.RebajasSobreVentas);
                        System.out.println("Devoluciones de "+SistemaContable.DevolucionesSobreVentas);
                        ObtenerComprasTotales();
                        b=tblMisEstados.getRowCount()-1;
                        salir3=false;
                    }b++;
                }                
            }else if(tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES SOBRE VENTAS")||tblMisEstados.getValueAt(i, 0).equals("DEVOLUCIONES EN VENTAS")){
                SistemaContable.DevolucionesSobreVentas=Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                int b=0;
                while(b<tblMisEstados.getRowCount()){
                    if(tblMisEstados.getValueAt(b, 0).equals("VENTAS")||tblMisEstados.getValueAt(b, 0).equals("VENTAS TOTALES")){
                        SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                        ObtenerComprasTotales();
                        b=tblMisEstados.getRowCount()-1;
                        salir3=false;
                        SistemaContable.Ventas = Double.parseDouble(tblMisEstados.getValueAt(b, 1).toString());
                        
                    }b++;
                }
            }else if(tblMisEstados.getValueAt(i, 0).equals("VENTAS")||tblMisEstados.getValueAt(i, 0).equals("VENTAS TOTALES")){
                    SistemaContable.VentasNetas= (Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString()))-(SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas);
                    salir3=true;
                    SistemaContable.Ventas = Double.parseDouble(tblMisEstados.getValueAt(i, 1).toString());
                }
            }i++;
        }if(salir3==true){
             ObtenerComprasTotales();
        }
    }
    public void ObtenerComprasTotales(){
        //     C O M P R A S    T O T A L E S
        SistemaContable.ComprasTotales=0;
        SistemaContable.GastosCompras=0;
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
                    }c++;   
                }
            }a++;
        }if(salir3==true){
            ObtenerComprasNetas();
        }
    }
    
    public void ObtenerComprasNetas(){
        int d = 0;
        SistemaContable.ComprasNetas=0;
        SistemaContable.RebajasSobreCompras=0;
        SistemaContable.DevolucionesSobreCompras=0;
        salir5=true;
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
                salir5=true;
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
        SistemaContable.DisponibilidadMercanciasPeriodo=0;
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
        SistemaContable.CostoVendido=0;
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
        SistemaContable.UtilidadBruta=0;
        while(g<tblMisEstados.getRowCount()){
        //    U T I L I D A D   B R U T A     
        if(tblMisEstados.getValueAt(g, 0).equals("UTILIDAD BRUTA")||tblMisEstados.getValueAt(g, 0).equals("MARGEN BRUTO EN VENTAS")||tblMisEstados.getValueAt(g, 0).equals("MARGEN BRUTO DE UTILIDAD")){
            SistemaContable.UtilidadBruta=Double.parseDouble(tblMisEstados.getValueAt(g, 1).toString());
            ObtenerUtilidadOperativa();
            g=tblMisEstados.getRowCount()-1;
            salir = false;
            break;
        }else{
            salir = true;
            }g++;
        }if(salir==true){
            SistemaContable.UtilidadBruta = SistemaContable.VentasNetas-SistemaContable.CostoVendido;
            ObtenerUtilidadOperativa();
        }
             
    }
    public void ObtenerUtilidadOperativa(){
        int h = 0;
        SistemaContable.Gast1=0;
        SistemaContable.Gast2=0;
        SistemaContable.GastosOperativos=0;
        SistemaContable.UtilidadOperativa=0;
        SistemaContable.GastoAdministrativo=0;
        SistemaContable.GastoFinanciero=0;
        SistemaContable.GastoVentas=0;
        
        while(h<tblMisEstados.getRowCount()){
        //    U T I L I D A D   O P E R A T I V A
        if(tblMisEstados.getValueAt(h, 0).equals("GASTO OPERATIVO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS OPERATIVOS")||tblMisEstados.getValueAt(h, 0).equals("GASTOS DE OPERACION")){
            SistemaContable.GastosOperativos= Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            SistemaContable.UtilidadOperativa=SistemaContable.UtilidadBruta-(Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString()));
            ObtenerUtilidadNeta();
            h=tblMisEstados.getRowCount()-1;
            salir2=false;
            break;
            
        }
        
        else if(tblMisEstados.getValueAt(h, 2).toString().equals("4")){
            SistemaContable.Gast1 = SistemaContable.Gast1 + Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            System.out.println(tblMisEstados.getValueAt(h, 0).toString());
            salir2=true;            
        }else{
            if(tblMisEstados.getValueAt(h, 0).equals("GASTO DE VENTAS")||tblMisEstados.getValueAt(h, 0).equals("GASTOS EN VENTAS")){
                SistemaContable.GastoVentas=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }else if(tblMisEstados.getValueAt(h, 0).equals("GASTO FINANCIERO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS FINANCIEROS")){
                SistemaContable.GastoFinanciero=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }else if(tblMisEstados.getValueAt(h, 0).equals("GASTO ADMINISTRATIVO")||tblMisEstados.getValueAt(h, 0).equals("GASTOS ADMINISTRATIVOS")){
                SistemaContable.GastoAdministrativo=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            }
            SistemaContable.Gast2 = SistemaContable.GastoVentas+SistemaContable.GastoFinanciero+SistemaContable.GastoAdministrativo;
            salir2=true;
        }   
        if(tblMisEstados.getValueAt(h, 0).equals("UTILIDAD OPERATIVA")){
            SistemaContable.UtilidadOperativa=Double.parseDouble(tblMisEstados.getValueAt(h, 1).toString());
            ObtenerUtilidadNeta();
            salir2=false;
            }h++;
        }if(salir2==true){
            SistemaContable.GastosOperativos=(SistemaContable.Gast1+SistemaContable.Gast2);
            SistemaContable.UtilidadOperativa=SistemaContable.UtilidadBruta-(SistemaContable.Gast1+SistemaContable.Gast2);
            ObtenerUtilidadNeta();
        }
    }
    public void ObtenerUtilidadNeta(){
        int j = 0;
        SistemaContable.UtilidadNeta=0;
        SistemaContable.OtrosGastos=0;
        SistemaContable.OtrosIngresos=0;
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
        SistemaContable.UtilidadPorDistribuir=0;
        SistemaContable.Impuestos=0;
        SistemaContable.ReservaLegal=0;
        while(k<tblMisEstados.getRowCount()){
        //   U T I L I D A D   P O R   D I S T R I B U I R    
        if(tblMisEstados.getValueAt(k, 0).equals("UTILIDAD DEL EJERCICIO")||tblMisEstados.getValueAt(k, 0).equals("UTILIDAD POR DISTRIBUIR")){
            SistemaContable.UtilidadPorDistribuir=Double.parseDouble(tblMisEstados.getValueAt(k, 1).toString());
            k=tblMisEstados.getRowCount()-1;
        }else{
            if(SistemaContable.UtilidadNeta<150000){
                SistemaContable.Impuestos=0.25*SistemaContable.UtilidadNeta;
                SistemaContable.ReservaLegal=SistemaContable.UtilidadNeta*0.07;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*0.07)+(SistemaContable.UtilidadNeta*0.25));
            }else{
                SistemaContable.Impuestos=0.30*SistemaContable.UtilidadNeta;
                SistemaContable.ReservaLegal=SistemaContable.UtilidadNeta*0.07;
                SistemaContable.UtilidadPorDistribuir=SistemaContable.UtilidadNeta-((SistemaContable.UtilidadNeta*0.07)+(SistemaContable.UtilidadNeta*0.30));
                }
            }k++;
        }         
    }
    
    public void generarTablaAnálisisVerticalER(){ 
            AnalisisVerticalERTableModel.setRowCount(0);
            AnalisisVerticalERTableModel.setRowCount(17);
            AnalisisVerticalERTableModel.setColumnCount(3);
            Object encabezados [] = {"Cuentas", "Valor", "Análisis Vertical %"};
            AnalisisVerticalERTableModel.setColumnIdentifiers(encabezados);
            Object matriz [][] = new Object[17][3];
            matriz[0][0] = "Rebajas en Ventas";
            matriz[1][0] = "Ventas Netas";
            matriz[2][0] = "Gastos en Compras";
            matriz[3][0] = "Total Compras";
            matriz[4][0] = "Rebajas en Compras";
            matriz[5][0] = "Compras Netas";
            matriz[6][0] = "Disponibilidad de Mercancía";
            matriz[7][0] = "Costo de lo Vendido";
            matriz[8][0] = "UTILIDAD BRUTA";
            matriz[9][0] = "Gastos Operativos";
            matriz[10][0] = "UTILIDAD OPERATIVA";
            matriz[11][0] = "Otros Gastos";
            matriz[12][0] = "Otros Ingresos";
            matriz[13][0] = "UTILIDAD NETA";
            matriz[14][0] = "Reserva Legal";
            matriz[15][0] = "Impuestos";
            matriz[16][0] = "UTILIDAD POR DISTRIBUIR";
            matriz[0][1] = SistemaContable.RebajasSobreVentas+SistemaContable.DevolucionesSobreVentas;
            matriz[1][1] = SistemaContable.VentasNetas;
            matriz[2][1] = SistemaContable.GastosCompras;
            matriz[3][1] = SistemaContable.ComprasTotales;
            matriz[4][1] = SistemaContable.RebajasSobreCompras+SistemaContable.DevolucionesSobreCompras;
            matriz[5][1] = SistemaContable.ComprasNetas;
            matriz[6][1] = SistemaContable.DisponibilidadMercanciasPeriodo;
            matriz[7][1] = SistemaContable.CostoVendido;
            matriz[8][1] = SistemaContable.UtilidadBruta;
            matriz[9][1] = SistemaContable.GastosOperativos;
            matriz[10][1] = SistemaContable.UtilidadOperativa;
            matriz[11][1] = SistemaContable.OtrosGastos;
            matriz[12][1] = SistemaContable.OtrosIngresos;
            matriz[13][1] = SistemaContable.UtilidadNeta;
            matriz[14][1] = SistemaContable.ReservaLegal;
            matriz[15][1] = SistemaContable.Impuestos;
            matriz[16][1] = SistemaContable.UtilidadPorDistribuir;
            for(int x=0; x<17; x++){
                matriz [x][2] = df.format((Object)(((Double.parseDouble(matriz [x][1].toString()))/(Double.parseDouble(matriz[1][1].toString())))*100));
            }
            
            for(int i=0; i<17; i++){
                for(int j=0; j<3; j++){
                    tblAnalisisVerticalER.setValueAt(matriz[i][j], i, j);
                }
            }
    }
    
    public void generarTablaAnálisisVerticalBG(){ 
            AnalisisVerticalBGTableModel.setRowCount(0);
            AnalisisVerticalBGTableModel.setRowCount(11);
            AnalisisVerticalBGTableModel.setColumnCount(3);
            Object encabezados [] = {"Cuentas", "Valor", "Análisis Vertical %"};
            AnalisisVerticalBGTableModel.setColumnIdentifiers(encabezados);
            Object matriz [][] = new Object[11][3];
            matriz[0][0] = "Activos Corrientes";
            matriz[1][0] = "Inventario";
            matriz[2][0] = "Cuentas por Cobrar";
            matriz[3][0] = "Activos No Corrientes";
            matriz[4][0] = "TOTAL ACTIVOS";
            matriz[5][0] = "Pasivos Corrientes";
            matriz[6][0] = "Cuentas por Pagar";
            matriz[7][0] = "Pasivos No Corrientes";
            matriz[8][0] = "TOTAL PASIVOS";
            matriz[9][0] = "Capital";
            matriz[10][0] = "TOTAL PASIVOS + CAPITAL";
            matriz[0][1] = SistemaContable.ActivosCorrientes;
            matriz[1][1] = SistemaContable.Inventario;
            matriz[2][1] = SistemaContable.CuentasXCobrar;
            matriz[3][1] = SistemaContable.ActivosNoCorrientes;
            matriz[4][1] = SistemaContable.ActivosTotales;
            matriz[5][1] = SistemaContable.PasivosCorrientes;
            matriz[6][1] = SistemaContable.CuentasXPagar;
            matriz[7][1] = SistemaContable.PasivosNoCorrientes;
            matriz[8][1] = SistemaContable.PasivosTotales;
            matriz[9][1] = SistemaContable.Capital;
            matriz[10][1] = SistemaContable.PasivosTotales+SistemaContable.Capital;
            for(int x=0; x<11; x++){
                if(x<=5) {
                    matriz [x][2] = df.format((Object)(((Double.parseDouble(matriz [x][1].toString()))/(Double.parseDouble(matriz[4][1].toString())))*100));                
                }
                else {
                    matriz [x][2] = df.format((Object)(((Double.parseDouble(matriz [x][1].toString()))/(Double.parseDouble(matriz[10][1].toString())))*100));
                }
            } 
           
            for(int i=0; i<17; i++){
                for(int j=0; j<3; j++){
                    tblAnalisisVerticalBG.setValueAt(matriz[i][j], i, j);
                }
            }
           
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblBG = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMisEstados = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstadosReporte = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlVerEstados = new javax.swing.JPanel();
        lblIndices = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        cmbFecha = new javax.swing.JComboBox<>();
        lblBalance = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblCerrar1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        pnlComparar = new javax.swing.JPanel();
        lblBienvenida15 = new javax.swing.JLabel();
        lblBienvenida16 = new javax.swing.JLabel();
        lblBienvenida17 = new javax.swing.JLabel();
        lblBienvenida18 = new javax.swing.JLabel();
        pnlNuevos = new javax.swing.JPanel();
        lblBienvenida3 = new javax.swing.JLabel();
        lblBienvenida4 = new javax.swing.JLabel();
        lblBienvenida5 = new javax.swing.JLabel();
        lblBienvenida6 = new javax.swing.JLabel();
        pnlVer = new javax.swing.JPanel();
        lblBienvenida7 = new javax.swing.JLabel();
        lblBienvenida8 = new javax.swing.JLabel();
        lblBienvenida9 = new javax.swing.JLabel();
        lblBienvenida10 = new javax.swing.JLabel();
        pnlAnalisis = new javax.swing.JPanel();
        lblBienvenida11 = new javax.swing.JLabel();
        lblBienvenida12 = new javax.swing.JLabel();
        lblBienvenida13 = new javax.swing.JLabel();
        lblBienvenida14 = new javax.swing.JLabel();
        pnlIndices = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblR3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblR8 = new javax.swing.JLabel();
        lblR2 = new javax.swing.JLabel();
        lblR1 = new javax.swing.JLabel();
        lblR9 = new javax.swing.JLabel();
        lblR10 = new javax.swing.JLabel();
        lblSiguiente = new javax.swing.JLabel();
        lblVolver = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblR7 = new javax.swing.JLabel();
        lblR6 = new javax.swing.JLabel();
        lblR5 = new javax.swing.JLabel();
        lblR4 = new javax.swing.JLabel();
        pnlIndices2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblR16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblR15 = new javax.swing.JLabel();
        lblR14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblR13 = new javax.swing.JLabel();
        lblR12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblR11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblVolver1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblR17 = new javax.swing.JLabel();
        lblR18 = new javax.swing.JLabel();
        pnlAnalisisVertical = new javax.swing.JPanel();
        lblCerrar2 = new javax.swing.JLabel();
        cmbFecha1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblBalance1 = new javax.swing.JLabel();
        lblEstado1 = new javax.swing.JLabel();
        pnlAnalisisVerticalBG = new javax.swing.JPanel();
        lblCerrar3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAnalisisVerticalBG = new javax.swing.JTable();
        pnlAnalisisVerticalER = new javax.swing.JPanel();
        lblCerrar4 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAnalisisVerticalER = new javax.swing.JTable();
        lblComparar = new javax.swing.JLabel();
        lblCrearEstados = new javax.swing.JLabel();
        lblVerEstados = new javax.swing.JLabel();
        lblAnalisis = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblBienvenida1 = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();

        tblBG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblBG);

        tblMisEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane4.setViewportView(tblMisEstados);

        tblEstadosReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RebajasDevVenta", "VentasNetas", "GastosCompras", "ComprasTotales", "RebajasDevCompras", "ComprasNetas", "Disponibilidad", "CostoVendido", "UtilidadBruta", "GastoOperativo", "UtilidadOperativa", "OtrosGastos", "OtrosIngresos", "UtilidadNeta", "ReservaLegal", "Impuestos", "UtilidadDistribuir"
            }
        ));
        jScrollPane1.setViewportView(tblEstadosReporte);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 165, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 210, 20));

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 140, 20));

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 140, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 10));

        pnlVerEstados.setBackground(new java.awt.Color(72, 165, 234));
        pnlVerEstados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIndices.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblIndices.setForeground(new java.awt.Color(255, 255, 255));
        lblIndices.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIndices.setText("Indices");
        lblIndices.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblIndices.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIndices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIndicesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIndicesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIndicesMouseExited(evt);
            }
        });
        pnlVerEstados.add(lblIndices, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 230, 50));

        lblEstado.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(255, 255, 255));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Estado de Resultados");
        lblEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
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
        pnlVerEstados.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 230, 50));

        cmbFecha.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        pnlVerEstados.add(cmbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 190, 30));

        lblBalance.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblBalance.setForeground(new java.awt.Color(255, 255, 255));
        lblBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBalance.setText("Balance General");
        lblBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
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
        pnlVerEstados.add(lblBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 230, 50));

        jLabel1.setBackground(new java.awt.Color(72, 165, 234));
        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel1.setText("Selecciona el año a realizar los reportes:");
        pnlVerEstados.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 280, 30));

        lblCerrar1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar1.setText("x");
        lblCerrar1.setToolTipText("Cerrar");
        lblCerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrar1MouseClicked(evt);
            }
        });
        pnlVerEstados.add(lblCerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 30, 30));

        jSeparator3.setBackground(new java.awt.Color(240, 240, 240));
        jSeparator3.setForeground(new java.awt.Color(240, 240, 240));
        pnlVerEstados.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 700, 10));

        jPanel1.add(pnlVerEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        pnlComparar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida15.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida15.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida15.setText("eficiente.");
        pnlComparar.add(lblBienvenida15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 370, 20));

        lblBienvenida16.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida16.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida16.setText("Al tener 2 o más estados financieros en fechas distintas de tu empresa,");
        pnlComparar.add(lblBienvenida16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 370, 20));

        lblBienvenida17.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida17.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida17.setText("podrás hacer una comparación por medio de todos los índices y así");
        pnlComparar.add(lblBienvenida17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 370, 20));

        lblBienvenida18.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida18.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida18.setText("poder conocer los períodos de fecha en los que la empresa fue más");
        pnlComparar.add(lblBienvenida18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 370, 20));

        jPanel1.add(pnlComparar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 410, 90));

        pnlNuevos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida3.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida3.setText("No es posible crear nuevos estados en un mismo año.");
        pnlNuevos.add(lblBienvenida3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida4.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida4.setText("Podrás crear nuevos estados financieros a partir de cuentas y sus saldos.");
        pnlNuevos.add(lblBienvenida4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida5.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida5.setText("Cada vez que crees un estado financiero se guardará para luego poder");
        pnlNuevos.add(lblBienvenida5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida6.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida6.setText("revisarlo y  compararlo si es que posees más estados en otros años.");
        pnlNuevos.add(lblBienvenida6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlNuevos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 410, 90));

        pnlVer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida7.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida7.setText("para una fecha en específica.");
        pnlVer.add(lblBienvenida7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida8.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida8.setText("Aquí están guardados todos los estados financieros de tu empresa");
        pnlVer.add(lblBienvenida8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida9.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida9.setText("con la opción de seleccionarlos por una fecha.");
        pnlVer.add(lblBienvenida9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida10.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida10.setText("Verás el balance general, estado de resultados y todos los índices");
        pnlVer.add(lblBienvenida10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 90, 410, 90));

        pnlAnalisis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida11.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida11.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida11.setText("Antes es necesario poseer estados financieros para la empresa.");
        pnlAnalisis.add(lblBienvenida11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 390, 20));

        lblBienvenida12.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida12.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida12.setText("Podrás generar el análisis vertical de los estados financieros de tu empresa.");
        pnlAnalisis.add(lblBienvenida12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 390, 20));

        lblBienvenida13.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida13.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida13.setText("Se generará el Balance General y el Estado de resultados como forma");
        pnlAnalisis.add(lblBienvenida13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 390, 20));

        lblBienvenida14.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblBienvenida14.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida14.setText("del análisis vertical.");
        pnlAnalisis.add(lblBienvenida14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 390, 20));

        jPanel1.add(pnlAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 410, 90));

        pnlIndices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(72, 165, 234));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("RAZONES FINANCIERAS");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 310, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Prueba ácida:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 170, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Capital de Trabajo:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 170, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Líquidez:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 700, 30));

        lblR3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR3.setForeground(new java.awt.Color(72, 165, 234));
        lblR3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 170, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Razón cargo interés fijo:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 170, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Índice de Endeudamiento:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 170, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Endeudamiento:");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 700, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("índ. cover. de pagos fijos:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 170, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Líquidez corriente:");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 30));

        lblR8.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR8.setForeground(new java.awt.Color(72, 165, 234));
        lblR8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 170, 30));

        lblR2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR2.setForeground(new java.awt.Color(72, 165, 234));
        lblR2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 170, 30));

        lblR1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR1.setForeground(new java.awt.Color(72, 165, 234));
        lblR1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 170, 30));

        lblR9.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR9.setForeground(new java.awt.Color(72, 165, 234));
        lblR9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 170, 30));

        lblR10.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR10.setForeground(new java.awt.Color(72, 165, 234));
        lblR10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 170, 30));

        lblSiguiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e4ae(0)_48.png"))); // NOI18N
        lblSiguiente.setToolTipText("Página 2");
        lblSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSiguienteMouseClicked(evt);
            }
        });
        jPanel2.add(lblSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 40, 40));

        lblVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e4ad(0)_48.png"))); // NOI18N
        lblVolver.setToolTipText("Volver a Mis Estados");
        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
        });
        jPanel2.add(lblVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Actividad:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 700, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Rotación de Inventarios:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 170, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Período Promedio Cobro:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 170, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Período Promedio Pago:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 170, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Rotación Activos Totales:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 170, 30));

        lblR7.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR7.setForeground(new java.awt.Color(72, 165, 234));
        lblR7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 170, 30));

        lblR6.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR6.setForeground(new java.awt.Color(72, 165, 234));
        lblR6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 170, 30));

        lblR5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR5.setForeground(new java.awt.Color(72, 165, 234));
        lblR5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 170, 30));

        lblR4.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR4.setForeground(new java.awt.Color(72, 165, 234));
        lblR4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblR4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 170, 30));

        pnlIndices.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 620));

        jPanel1.add(pnlIndices, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        pnlIndices2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(72, 165, 234));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("RAZONES FINANCIERAS");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 310, 40));

        lblR16.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR16.setForeground(new java.awt.Color(72, 165, 234));
        lblR16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 170, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Rendimiento s. Patrimonio:");
        jLabel15.setToolTipText("RSP - ROE");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 170, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Rendimiento Act. Totales:");
        jLabel24.setToolTipText("RSA - ROA - RSI");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 170, 30));

        lblR15.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR15.setForeground(new java.awt.Color(72, 165, 234));
        lblR15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 170, 30));

        lblR14.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR14.setForeground(new java.awt.Color(72, 165, 234));
        lblR14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 170, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Ganancias x Acción:");
        jLabel23.setToolTipText("GPA - EPS");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 170, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Margen Utilidad Neta:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 170, 30));

        lblR13.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR13.setForeground(new java.awt.Color(72, 165, 234));
        lblR13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 170, 30));

        lblR12.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR12.setForeground(new java.awt.Color(72, 165, 234));
        lblR12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 170, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Margen Utilidad Operativa:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 170, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Margen Utilidad Bruta:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 30));

        lblR11.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR11.setForeground(new java.awt.Color(72, 165, 234));
        lblR11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 170, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Rentabilidad:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 700, 30));

        lblVolver1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e4ad(0)_48.png"))); // NOI18N
        lblVolver1.setToolTipText("Página 1");
        lblVolver1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolver1MouseClicked(evt);
            }
        });
        jPanel3.add(lblVolver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Mercado:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 700, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Relación Precio/Ganancia:");
        jLabel27.setToolTipText("P/G - P/E");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 170, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Razón Mercado/Libro:");
        jLabel18.setToolTipText("M/L");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 170, 30));

        lblR17.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR17.setForeground(new java.awt.Color(72, 165, 234));
        lblR17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 170, 30));

        lblR18.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblR18.setForeground(new java.awt.Color(72, 165, 234));
        lblR18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblR18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 170, 30));

        pnlIndices2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 620));

        jPanel1.add(pnlIndices2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        pnlAnalisisVertical.setBackground(new java.awt.Color(72, 165, 234));
        pnlAnalisisVertical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar2.setText("x");
        lblCerrar2.setToolTipText("Cerrar");
        lblCerrar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrar2MouseClicked(evt);
            }
        });
        pnlAnalisisVertical.add(lblCerrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 30, 30));

        cmbFecha1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        pnlAnalisisVertical.add(cmbFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 190, 30));

        jLabel2.setText("Selecciona un año del cual desea ver su Análisis Vertical:");
        pnlAnalisisVertical.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 420, 40));

        lblBalance1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblBalance1.setForeground(new java.awt.Color(255, 255, 255));
        lblBalance1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBalance1.setText("Balance General");
        lblBalance1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblBalance1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBalance1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBalance1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBalance1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBalance1MouseEntered(evt);
            }
        });
        pnlAnalisisVertical.add(lblBalance1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 230, 50));

        lblEstado1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblEstado1.setForeground(new java.awt.Color(255, 255, 255));
        lblEstado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado1.setText("Estado de Resultados");
        lblEstado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblEstado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEstado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEstado1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEstado1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEstado1MouseEntered(evt);
            }
        });
        pnlAnalisisVertical.add(lblEstado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 230, 50));

        jPanel1.add(pnlAnalisisVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        pnlAnalisisVerticalBG.setBackground(new java.awt.Color(72, 165, 234));
        pnlAnalisisVerticalBG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar3.setText("x");
        lblCerrar3.setToolTipText("Cerrar");
        lblCerrar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrar3MouseClicked(evt);
            }
        });
        pnlAnalisisVerticalBG.add(lblCerrar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 30, 30));

        jLabel7.setText("Análisis Vertical Balance General: ");
        pnlAnalisisVerticalBG.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 420, 40));

        tblAnalisisVerticalBG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblAnalisisVerticalBG);

        pnlAnalisisVerticalBG.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 610, 260));

        jPanel1.add(pnlAnalisisVerticalBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        pnlAnalisisVerticalER.setBackground(new java.awt.Color(72, 165, 234));
        pnlAnalisisVerticalER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCerrar4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar4.setText("x");
        lblCerrar4.setToolTipText("Cerrar");
        lblCerrar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrar4MouseClicked(evt);
            }
        });
        pnlAnalisisVerticalER.add(lblCerrar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 30, 30));

        jLabel29.setText("Análisis Vertical Estado de Resultados: ");
        pnlAnalisisVerticalER.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 420, 40));

        tblAnalisisVerticalER.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblAnalisisVerticalER);

        pnlAnalisisVerticalER.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 610, 350));

        jPanel1.add(pnlAnalisisVerticalER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 700, 620));

        lblComparar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblComparar.setForeground(new java.awt.Color(51, 51, 51));
        lblComparar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        jPanel1.add(lblComparar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 140, 40));

        lblCrearEstados.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblCrearEstados.setForeground(new java.awt.Color(51, 51, 51));
        lblCrearEstados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        jPanel1.add(lblCrearEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, 40));

        lblVerEstados.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVerEstados.setForeground(new java.awt.Color(51, 51, 51));
        lblVerEstados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVerEstados.setText("Ver mis Estados");
        lblVerEstados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVerEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblVerEstadosMouseExited(evt);
            }
        });
        jPanel1.add(lblVerEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 140, 40));

        lblAnalisis.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblAnalisis.setForeground(new java.awt.Color(51, 51, 51));
        lblAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnalisis.setText("Análisis Vertical");
        lblAnalisis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnalisisMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAnalisisMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAnalisisMouseEntered(evt);
            }
        });
        jPanel1.add(lblAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 140, 40));

        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_25be(0)_48.png"))); // NOI18N
        jPanel1.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 140, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 700, 700));

        lblBienvenida.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("2017");
        getContentPane().add(lblBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 150, 60));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 300, 20));

        lblBienvenida1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblBienvenida1.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida1.setText("SISTEMA CONTABLE");
        getContentPane().add(lblBienvenida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 50));

        lblEmpresa.setBackground(new java.awt.Color(72, 165, 234));
        lblEmpresa.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        lblEmpresa.setForeground(new java.awt.Color(72, 165, 234));
        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setText("CASA");
        getContentPane().add(lblEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 40));

        lblCerrar.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(72, 165, 234));
        lblCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/FontAwesome_f011(0)_32.png"))); // NOI18N
        lblCerrar.setText("Cerrar sesión");
        lblCerrar.setToolTipText("Cerrar Sesión");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        getContentPane().add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 150, -1));

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
            pnlAnalisisVertical.setVisible(false);
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
        pnlVerEstados.setVisible(false);
        pnlIndices.setVisible(true);
        generarBG();
        actualizarMisEstados();
        ObtenerDatosEstado();
        generarTablaReporteEstado();
        lblR1.setText(""+(SistemaContable.ActivosCorrientes/SistemaContable.PasivosCorrientes));
        lblR2.setText(""+((SistemaContable.ActivosCorrientes-SistemaContable.Inventario)/SistemaContable.PasivosCorrientes));
        lblR3.setText(""+(SistemaContable.ActivosCorrientes-SistemaContable.PasivosCorrientes));
        lblR4.setText(""+(SistemaContable.CostoVendido/SistemaContable.Inventario));
        lblR5.setText(""+(SistemaContable.CuentasXCobrar)/(SistemaContable.Ventas/360));
        //lblR6.setText(""+);
        lblR7.setText(""+(SistemaContable.Ventas/SistemaContable.ActivosTotales));
        lblR8.setText(""+(SistemaContable.PasivosTotales/SistemaContable.ActivosTotales));
        lblR9.setText(""+(SistemaContable.UtilidadOperativa/SistemaContable.OtrosGastos));
        //lbR10.setText();
        lblR11.setText(""+(SistemaContable.UtilidadBruta/SistemaContable.Ventas));
        lblR12.setText(""+(SistemaContable.UtilidadOperativa/SistemaContable.Ventas));
        lblR13.setText(""+(SistemaContable.UtilidadNeta/SistemaContable.Ventas));
        //lblR14.setText(""+(SistemaContable.UtilidadPorDistribuir/SistemaContable.Ventas));
        lblR15.setText(""+(SistemaContable.UtilidadPorDistribuir/SistemaContable.ActivosTotales));
        lblR16.setText(""+(SistemaContable.UtilidadPorDistribuir/SistemaContable.Capital));
        //lblR17.setText(""+(SistemaContable.UtilidadNeta/SistemaContable.Ventas));
        //lblR18.setText(""+(SistemaContable.UtilidadNeta/SistemaContable.Ventas));
        
        
    }//GEN-LAST:event_lblIndicesMouseClicked

    private void lblEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseClicked
        actualizarMisEstados();
        ObtenerDatosEstado();
        generarTablaReporteEstado();
        generarEstado();        
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
            parametro.put("empresa", lblEmpresa.getText());
            actualizarMisEstados();
            ObtenerDatosEstado();
            parametro.put("Utilidad", SistemaContable.UtilidadPorDistribuir);
            parametro.put("Reserva", SistemaContable.ReservaLegal);
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
        lblBalance.setForeground(java.awt.Color.white);
    }//GEN-LAST:event_lblBalanceMouseExited

    private void lblEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseEntered
        lblEstado.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_lblEstadoMouseEntered

    private void lblEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstadoMouseExited
        lblEstado.setForeground(java.awt.Color.white);
    }//GEN-LAST:event_lblEstadoMouseExited

    private void lblIndicesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndicesMouseEntered
        lblIndices.setForeground(java.awt.Color.black);
    }//GEN-LAST:event_lblIndicesMouseEntered

    private void lblIndicesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndicesMouseExited
        lblIndices.setForeground(java.awt.Color.white);
    }//GEN-LAST:event_lblIndicesMouseExited

    private void lblAnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnalisisMouseClicked
        try {
            CargarFechas();
            pnlVerEstados.setVisible(false);
            pnlAnalisisVertical.setVisible(true);
        } catch (ErrorSistemaContable ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblAnalisisMouseClicked

    private void lblAnalisisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnalisisMouseEntered
        pnlAnalisis.setVisible(true);
        lbl4.setVisible(true);
    }//GEN-LAST:event_lblAnalisisMouseEntered

    private void lblAnalisisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnalisisMouseExited
        pnlAnalisis.setVisible(false);
        lbl4.setVisible(false);
    }//GEN-LAST:event_lblAnalisisMouseExited

    private void lblCerrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrar2MouseClicked
        pnlAnalisisVertical.setVisible(false);
    }//GEN-LAST:event_lblCerrar2MouseClicked

    private void lblVolver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolver1MouseClicked
        pnlIndices2.setVisible(false);
        pnlIndices.setVisible(true);
    }//GEN-LAST:event_lblVolver1MouseClicked

    private void lblSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSiguienteMouseClicked
        pnlIndices.setVisible(false);
        pnlIndices2.setVisible(true);
    }//GEN-LAST:event_lblSiguienteMouseClicked

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        pnlIndices.setVisible(false);
        pnlVerEstados.setVisible(true);
    }//GEN-LAST:event_lblVolverMouseClicked

    private void lblBalance1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalance1MouseClicked
        pnlAnalisisVertical.setVisible(false);
        pnlAnalisisVerticalBG.setVisible(true);
        generarBG();
        generarTablaAnálisisVerticalBG();
    }//GEN-LAST:event_lblBalance1MouseClicked

    private void lblBalance1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalance1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBalance1MouseEntered

    private void lblBalance1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalance1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBalance1MouseExited

    private void lblEstado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstado1MouseClicked
        pnlAnalisisVertical.setVisible(false);
        pnlAnalisisVerticalBG.setVisible(false);
        pnlAnalisisVerticalER.setVisible(true);
        ObtenerDatosEstado();
        generarTablaAnálisisVerticalER();
    }//GEN-LAST:event_lblEstado1MouseClicked

    private void lblEstado1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstado1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEstado1MouseEntered

    private void lblEstado1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEstado1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEstado1MouseExited

    private void lblCerrar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrar3MouseClicked
        pnlAnalisisVerticalBG.setVisible(false);
    }//GEN-LAST:event_lblCerrar3MouseClicked

    private void lblCerrar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrar4MouseClicked
        pnlAnalisisVerticalBG.setVisible(false);
        pnlAnalisisVerticalER.setVisible(false);
    }//GEN-LAST:event_lblCerrar4MouseClicked

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
    private javax.swing.JComboBox<String> cmbFecha1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    public static javax.swing.JLabel lblAnalisis;
    public static javax.swing.JLabel lblBalance;
    public static javax.swing.JLabel lblBalance1;
    public static javax.swing.JLabel lblBienvenida;
    public static javax.swing.JLabel lblBienvenida1;
    public static javax.swing.JLabel lblBienvenida10;
    public static javax.swing.JLabel lblBienvenida11;
    public static javax.swing.JLabel lblBienvenida12;
    public static javax.swing.JLabel lblBienvenida13;
    public static javax.swing.JLabel lblBienvenida14;
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
    private javax.swing.JLabel lblCerrar2;
    private javax.swing.JLabel lblCerrar3;
    private javax.swing.JLabel lblCerrar4;
    public static javax.swing.JLabel lblComparar;
    public static javax.swing.JLabel lblCrearEstados;
    public static javax.swing.JLabel lblEmpresa;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblEstado1;
    public static javax.swing.JLabel lblIndices;
    private javax.swing.JLabel lblR1;
    private javax.swing.JLabel lblR10;
    private javax.swing.JLabel lblR11;
    private javax.swing.JLabel lblR12;
    private javax.swing.JLabel lblR13;
    private javax.swing.JLabel lblR14;
    private javax.swing.JLabel lblR15;
    private javax.swing.JLabel lblR16;
    private javax.swing.JLabel lblR17;
    private javax.swing.JLabel lblR18;
    private javax.swing.JLabel lblR2;
    private javax.swing.JLabel lblR3;
    private javax.swing.JLabel lblR4;
    private javax.swing.JLabel lblR5;
    private javax.swing.JLabel lblR6;
    private javax.swing.JLabel lblR7;
    private javax.swing.JLabel lblR8;
    private javax.swing.JLabel lblR9;
    private javax.swing.JLabel lblSiguiente;
    public static javax.swing.JLabel lblVerEstados;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JLabel lblVolver1;
    private javax.swing.JPanel pnlAnalisis;
    private javax.swing.JPanel pnlAnalisisVertical;
    private javax.swing.JPanel pnlAnalisisVerticalBG;
    private javax.swing.JPanel pnlAnalisisVerticalER;
    private javax.swing.JPanel pnlComparar;
    private javax.swing.JPanel pnlIndices;
    private javax.swing.JPanel pnlIndices2;
    private javax.swing.JPanel pnlNuevos;
    private javax.swing.JPanel pnlVer;
    public static javax.swing.JPanel pnlVerEstados;
    private javax.swing.JTable tblAnalisisVerticalBG;
    private javax.swing.JTable tblAnalisisVerticalER;
    private javax.swing.JTable tblBG;
    private javax.swing.JTable tblEstadosReporte;
    private javax.swing.JTable tblMisEstados;
    // End of variables declaration//GEN-END:variables
}
