/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricky
 */
public class ControladorCuenta {
    static Conexion cn;
    static ResultSet rs;
    
    
    public static void AgregarCuentas(Cuenta cu)throws ErrorSistemaContable{ 
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO cuentas(IdCuenta,Cuenta,IdEstadoFinanciero,IdTipoCuenta,IdTipoSubCuenta) VALUES('"+cu.getIdCuenta()+"','"+cu.getCuenta()+"','"+cu.getIdEstadoFinanciero()+"',"+cu.getIdTipoCuenta()+","+cu.getIdTipoSubCuenta()+")");
            
        } catch (SQLException ex) {
            throw new ErrorSistemaContable("Class ControladorCuenta/Agregar", ex.getMessage());
        }
    }
    public static void Agregar(Cuenta cu)throws ErrorSistemaContable{ 
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(cu.getFecha());
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO cuentasxempresa(Fecha,IdEmpresa,IdCuenta,Valor) VALUES('"+fecha+"','"+cu.getIdEmpresa()+"','"+cu.getIdCuenta()+"',"+cu.getValor()+")");
            
        } catch (SQLException ex) {
            throw new ErrorSistemaContable("Class ControladorCuenta/Agregar", ex.getMessage());
        }
    }

    public static ArrayList<Cuenta> ObtenerCuentas(int IdEstadoFinanciero)throws ErrorSistemaContable{
    ArrayList<Object> cuenta = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT CONCAT(`tipocuenta`.`Nombre`,' ',`subtipocuenta`.`Nombre`) AS TIPO, `cuentas`.`Cuenta` FROM `cuentas` LEFT JOIN `tipocuenta` ON `tipocuenta`.`IdTipoCuenta`=`cuentas`.`IdTipoCuenta` LEFT JOIN `subtipocuenta` ON `subtipocuenta`.`IdTipoSubCuenta`=`cuentas`.`IdTipoSubCuenta` WHERE IdEstadoFinanciero='"+IdEstadoFinanciero+"' ORDER BY TIPO");
            while (rs.next()) {
 
                cuenta.add(rs.getString(1));
                cuenta.add(rs.getString(2));
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerCuentas",e.getMessage());
        }
        
        ArrayList<Cuenta> cuentas=(ArrayList) cuenta;
        return cuentas;
    }
    
    public static ArrayList<Cuenta> ObtenerCuentas2(int IdEstadoFinanciero)throws ErrorSistemaContable{
    ArrayList<Object> cuenta = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT Cuenta FROM cuentas WHERE IdEstadoFinanciero='"+IdEstadoFinanciero+"' ORDER BY Cuenta ASC");
            while (rs.next()) {
 
                cuenta.add(rs.getString(1));
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerCuentas",e.getMessage());
        }
        
        ArrayList<Cuenta> cuentas=(ArrayList) cuenta;
        return cuentas;
    }
    public static ArrayList<Cuenta> ObtenerEstados()throws ErrorSistemaContable{
    ArrayList<Object> estados = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT * FROM financieros");
            while (rs.next()) {
 
                estados.add(rs.getString(1));
                estados.add(rs.getString(2));
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerEstados",e.getMessage());
        }
        
        ArrayList<Cuenta> estadosfinancieros=(ArrayList) estados;
        return estadosfinancieros;
    }
    
    public static ArrayList<Cuenta> ObtenerTipo()throws ErrorSistemaContable{
    ArrayList<Object> tipo = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT * FROM tipocuenta");
            while (rs.next()) {
 
                tipo.add(rs.getString(1));
                tipo.add(rs.getString(2));
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerTipo",e.getMessage());
        }
        
        ArrayList<Cuenta> tipocuenta=(ArrayList) tipo;
        return tipocuenta;
    }
    
    public static ArrayList<Cuenta> ObtenerSubTipo()throws ErrorSistemaContable{
    ArrayList<Object> tipo = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT * FROM subtipocuenta");
            while (rs.next()) {
 
                tipo.add(rs.getString(1));
                tipo.add(rs.getString(2));
            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerSubTipo",e.getMessage());
        }
        
        ArrayList<Cuenta> tipocuenta=(ArrayList) tipo;
        return tipocuenta;
    }
    public static int ObtenerIDCuenta(String cu)throws ErrorSistemaContable{
        int IdCuenta=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT IdCuenta FROM cuentas WHERE Cuenta='"+cu+"'");
        
            while(rs.next()){
                IdCuenta = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerIdCuenta", ex.getMessage());
        } 
        return IdCuenta;
    
    }
    public static int ObtenerIDEmpresa(String em)throws ErrorSistemaContable{
        int IdEmpresa=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT IdEmpresa FROM empresa WHERE Empresa='"+em+"'");
        
            while(rs.next()){
                IdEmpresa = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerIdEmpresa", ex.getMessage());
        } 
        return IdEmpresa;
    
    }
    public static int ObtenerID()throws ErrorSistemaContable{
        int IdMax=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT MAX(IdCuenta) FROM cuentas");
        
            while(rs.next()){
                IdMax = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerID", ex.getMessage());
        } 
        return IdMax;
    
    }

}
