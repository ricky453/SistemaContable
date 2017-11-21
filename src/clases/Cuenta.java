/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


/**
 *
 * @author Ricky
 */
public class Cuenta {
    private int IdCuenta;
    private String Cuenta;
    private int IdEstadoFinanciero;
    private String estadoFinanciero;
    private String IdTipoCuenta;
    private String IdTipoSubCuenta;
    private int IdEmpresa;
    private String Empresa;
    private String Valor;
    private String Fecha;
    private String Usuario;
    private String Password;
    
    public String getEstadoFinanciero() {
        return estadoFinanciero;
    }

    public void setEstadoFinanciero(String estadoFinanciero) {
        this.estadoFinanciero = estadoFinanciero;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int IdCuenta) {
        this.IdCuenta = IdCuenta;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public int getIdEstadoFinanciero() {
        return IdEstadoFinanciero;
    }

    public void setIdEstadoFinanciero(int IdEstadoFinanciero) {
        this.IdEstadoFinanciero = IdEstadoFinanciero;
    }

    public String getIdTipoCuenta() {
        return IdTipoCuenta;
    }

    public void setIdTipoCuenta(String IdTipoCuenta) {
        this.IdTipoCuenta = IdTipoCuenta;
    }

    public String getIdTipoSubCuenta() {
        return IdTipoSubCuenta;
    }

    public void setIdTipoSubCuenta(String IdTipoSubCuenta) {
        this.IdTipoSubCuenta = IdTipoSubCuenta;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }
    

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
    
}
