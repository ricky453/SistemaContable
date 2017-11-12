/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;

/**
 *
 * @author Ricky
 */
public class Cuenta {
    public int IdCuenta;
    public String Cuenta;
    public int IdEstadoFinanciero;
    public String IdTipoCuenta;
    public String IdTipoSubCuenta;
    public int IdEmpresa;
    public String Empresa;
    public String Valor;
    public Date Fecha;

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
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
