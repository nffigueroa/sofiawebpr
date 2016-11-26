    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructores;

/**
 *
 * @author Nestor1
 */
public class Contructor_CortesCaja {
    
    private float total;
    private float total_efectivo;
    private float total_tarjeta_credito;
    private float total_tarjeta_debito;
    private float total_cheque;
    private float total_transaccion;
    private float total_nota_credito;
    private float total_importe_egreso;
    private float total_importe_ingreso;

    
     public Contructor_CortesCaja() {
    }

    public Contructor_CortesCaja(float total, float total_efectivo, float total_tarjeta_credito, float total_tarjeta_debito, float total_cheque, float total_transaccion, float total_nota_credito) {
        this.total = total;
        this.total_efectivo = total_efectivo;
        this.total_tarjeta_credito = total_tarjeta_credito;
        this.total_tarjeta_debito = total_tarjeta_debito;
        this.total_cheque = total_cheque;
        this.total_transaccion = total_transaccion;
        this.total_nota_credito = total_nota_credito;
    }

    public Contructor_CortesCaja(float total_importe_egreso, float total_importe_ingreso) {
        this.total_importe_egreso = total_importe_egreso;
        this.total_importe_ingreso = total_importe_ingreso;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal_efectivo() {
        return total_efectivo;
    }

    public void setTotal_efectivo(float total_efectivo) {
        this.total_efectivo = total_efectivo;
    }

    public float getTotal_tarjeta_credito() {
        return total_tarjeta_credito;
    }

    public void setTotal_tarjeta_credito(float total_tarjeta_credito) {
        this.total_tarjeta_credito = total_tarjeta_credito;
    }

    public float getTotal_tarjeta_debito() {
        return total_tarjeta_debito;
    }

    public void setTotal_tarjeta_debito(float total_tarjeta_debito) {
        this.total_tarjeta_debito = total_tarjeta_debito;
    }

    public float getTotal_cheque() {
        return total_cheque;
    }

    public void setTotal_cheque(float total_cheque) {
        this.total_cheque = total_cheque;
    }

    public float getTotal_transaccion() {
        return total_transaccion;
    }

    public void setTotal_transaccion(float total_transaccion) {
        this.total_transaccion = total_transaccion;
    }

    public float getTotal_nota_credito() {
        return total_nota_credito;
    }

    public void setTotal_nota_credito(float total_nota_credito) {
        this.total_nota_credito = total_nota_credito;
    }

    public float getTotal_importe_egreso() {
        return total_importe_egreso;
    }

    public void setTotal_importe_egreso(float total_importe_egreso) {
        this.total_importe_egreso = total_importe_egreso;
    }

    public float getTotal_importe_ingreso() {
        return total_importe_ingreso;
    }

    public void setTotal_importe_ingreso(float total_importe_ingreso) {
        this.total_importe_ingreso = total_importe_ingreso;
    }
    
    
}
