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
            cn.st.executeUpdate("INSERT INTO cuenta(IdCuenta,Cuenta,IdEstadoFinanciero,IdTipoCuenta,IdTipoSubCuenta) VALUES('"+cu.getIdCuenta()+"','"+cu.getCuenta()+"','"+cu.getIdEstadoFinanciero()+"',"+cu.getIdTipoCuenta()+","+cu.getIdTipoSubCuenta()+")");
            //cn.st.executeUpdate("INSERT INTO cuentasanio VALUES('"+cu.getIdCuenta()+"', '"+cu.getEmpresa()+"', '"+cu.getIdEstadoFinanciero()+"', '"+cu.getIdTipoSubCuenta()+"', '"+cu.getFecha()+"', '"+cu.getValor()+"')");
        } catch (SQLException ex) {
            throw new ErrorSistemaContable("Class ControladorCuenta/AgregarCuentas", ex.getMessage());
        }
    }
    public static void AgregarEmpresa(Cuenta cu)throws ErrorSistemaContable{ 
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO empresa(IdEmpresa,Usuario,Empresa,Password) VALUES('"+cu.getIdEmpresa()+"','"+cu.getUsuario()+"','"+cu.getEmpresa()+"','"+cu.getPassword()+"')");
            
        } catch (SQLException ex) { 
            throw new ErrorSistemaContable("Class ControladorCuenta/AgregarEmpresa", ex.getMessage());
        }
    }
    public static void Agregar(Cuenta cu)throws ErrorSistemaContable{ 
        try {

            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO cuentasanio(Anio,IdEmpresa,IdCuenta,Valor) VALUES('"+cu.getFecha()+"','"+cu.getIdEmpresa()+"','"+cu.getIdCuenta()+"',"+cu.getValor()+")");
            
        } catch (SQLException ex) {
            throw new ErrorSistemaContable("Class ControladorCuenta/Agregar", ex.getMessage());
        }
    }
    
    public static ArrayList<Cuenta> ReporteBG(int IdEstadoFinanciero)throws ErrorSistemaContable{
    ArrayList<Object> cuenta = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT `cuenta`.`Cuenta` AS `cuenta`, `tipocuenta`.`Nombre` AS `tipocuenta`, `subtipocuenta`.`Nombre` AS `subtipo`, `financieros`.`IdEstadoFinanciero` AS `id`\n" +
                                    "FROM `financieros`\n" +
                                    "    LEFT JOIN `cuentasanio` ON `cuentasanio`.`IdEstadoFinanciero` = `financieros`.`IdEstadoFinanciero`\n" +
                                    "    LEFT JOIN `subtipocuenta` ON `cuentasanio`.`IdTipoSubCuenta` = `subtipocuenta`.`IdTipoSubCuenta`\n" +
                                    "    LEFT JOIN `tipocuenta` ON `cuentasanio`.`IdTipoCuenta` = `tipocuenta`.`IdTipoCuenta`\n" +
                                    "    LEFT JOIN `cuenta` ON `cuentasanio`.`IdCuenta` = `cuenta`.`IdCuenta`\n" +
                                    "WHERE (`financieros`.`IdEstadoFinanciero` ='"+IdEstadoFinanciero+"') GROUP BY IdTipoCuenta;");
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
    
    public static ArrayList<Cuenta> ObtenerCuentas(int IdEstadoFinanciero)throws ErrorSistemaContable{
        ArrayList<Object> cuenta = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT CONCAT(`tipocuenta`.`Nombre`,' ',`subtipocuenta`.`Nombre`) AS TIPO, `cuenta`.`Cuenta` FROM `cuenta` LEFT JOIN `tipocuenta` ON `tipocuenta`.`IdTipoCuenta`=`cuenta`.`IdTipoCuenta` LEFT JOIN `subtipocuenta` ON `subtipocuenta`.`IdTipoSubCuenta`=`cuenta`.`IdTipoSubCuenta` WHERE IdEstadoFinanciero='"+IdEstadoFinanciero+"' ORDER BY TIPO");
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
            rs=cn.st.executeQuery("SELECT Cuenta FROM cuenta WHERE IdEstadoFinanciero='"+IdEstadoFinanciero+"' ORDER BY Cuenta ASC");
            while (rs.next()) {
 
                cuenta.add(rs.getString(1));
            }
            
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerCuentas",e.getMessage());
        }
        
        ArrayList<Cuenta> cuentas=(ArrayList) cuenta;
        return cuentas;
    }
    
    public static ArrayList<Cuenta> MisEstados(String anio, String Empresa)throws ErrorSistemaContable{
    ArrayList<Object> cuenta = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT `cuenta`.`Cuenta`, `cuentasanio`.`Valor` FROM `cuenta` LEFT JOIN `cuentasanio` ON `cuentasanio`.`IdCuenta`=`cuenta`.`IdCuenta` WHERE `cuenta`.IdEstadoFinanciero='"+anio+"' AND `cuentasanio`.Anio= '"+Empresa+"'");
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
        rs = cn.st.executeQuery("SELECT IdCuenta FROM cuenta WHERE Cuenta='"+cu+"'");
        
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
        rs = cn.st.executeQuery("SELECT MAX(IdCuenta) FROM cuenta");
        
            while(rs.next()){
                IdMax = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerID", ex.getMessage());
        } 
        return IdMax;
    
    }
    public static String ObtenerPassword(String cu)throws ErrorSistemaContable{
        String pass="";   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT Password FROM empresa WHERE Usuario='"+cu+"'");
        
            while(rs.next()){
                pass = rs.getString(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerPassword", ex.getMessage());
        } 
        return pass;
    
    }
    public static String NombreEmpresa(String cu)throws ErrorSistemaContable{
        String empresa="";   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT Empresa FROM empresa WHERE Usuario='"+cu+"'");
        
            while(rs.next()){
                empresa = rs.getString(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerPassword", ex.getMessage());
        } 
        return empresa;
    
    }
    public static int ObtenerMaxEmpresa()throws ErrorSistemaContable{
        int IdMax=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT MAX(IdEmpresa) FROM empresa");
        
            while(rs.next()){
                IdMax = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorSistemaContable("Class ControladorCuenta/ObtenerMaxEmpresa", ex.getMessage());
        } 
        return IdMax;
    
    }

        public static ArrayList<Cuenta> ObtenerFechas(String cu)throws ErrorSistemaContable{
    ArrayList<Object> proveedor = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT DISTINCT Anio FROM cuentasanio LEFT JOIN `empresa` ON `cuentasanio`.`IdEmpresa`=`empresa`.`IdEmpresa` WHERE `empresa`.`Empresa`='"+cu+"'");
            while (rs.next()) {
 
                proveedor.add(rs.getString(1));

            }
            
        } catch (SQLException e) {
            throw new ErrorSistemaContable("Class ControladorProveedor/Obtener",e.getMessage());
        }
        
        ArrayList<Cuenta> proveedores=(ArrayList) proveedor;
        return proveedores;
    }
    

}
