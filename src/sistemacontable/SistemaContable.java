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
    //BALACE GENERAL
    public static double ActivosCorrientes=0;
    public static double ActivosNoCorrientes=0;
    public static double ActivosTotales=0;
    public static double PasivosCorrientes=0;
    public static double PasivosNoCorrientes=0;
    public static double PasivosTotales=0;
    public static double Capital=0;
    public static double Inventario=0;
    public static double CuentasXCobrar=0;
    public static double CuentasXPagar=0;
    public static double ValorEnLibros=0;
    
    public static double Ventas=0;
    public static double GananciasAccionistas=0;
    public static double GastosArrendamiento=0;
    public static double PagoPrincipal=0;
    public static double NAccionesComunes=0;
    public static double PrecioMercado=0;
    public static double ComprasAnuales=0;
    
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
    public static double GastosOperativos=0;
    public static double Gast1=0;
    public static double Gast2=0;
    public static double UtilidadOperativa=0;
    public static double UtilidadNeta=0;
    public static double OtrosGastos=0;
    public static double OtrosIngresos=0;
    public static double UtilidadPorDistribuir=0;
    public static double Impuestos=0;
    public static double ReservaLegal=0;
    
    //Otras variables
    public static double RebajasSobreVentas = 0;
    public static double DevolucionesSobreVentas = 0;
    public static double GastosCompras = 0;
    public static double RebajasSobreCompras = 0;
    public static double DevolucionesSobreCompras = 0;
    
    public static void main(String[] args) {
        frmLogin home = new frmLogin();
        home.setVisible(true);
    }
    
}
