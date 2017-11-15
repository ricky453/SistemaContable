/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacontable;

import sistemacontable.frames.frmLogin;

/**
 *
 * @author Ricky
 */
public class SistemaContable {

    public static int posCuenta;
    public static String empresa;
    
    //ESTADO DE RESULTADOS
    public static double VentasNetas=0;
    public static double ComprasTotales=0;
    public static double ComprasNetas=0;
    public static double DisponibilidadMercanciasPeriodo=0;
    public static double CostoVendido=0;
    public static double UtilidadBruta=0;
    public static double GastoVentas=0;
    public static double GastoFinanciero=0;
    public static double GastoAdministrativo=0;
    public static double UtilidadOperativa=0;
    public static double UtilidadNeta=0;
    public static double OtrosGastos=0;
    public static double OtrosIngresos=0;
    public static double UtilidadPorDistribuir=0;
    public static double Impuestos;
    public static double ReservaLegal=0.07;
    
    public static void main(String[] args) {
        frmLogin home = new frmLogin();
        home.setVisible(true);
    }
    
}
