package modelo.factura;

import modelo.detallesfactura.DetallesFactura;
import java.util.List;

public class Factura {

    private int idFactura;
    private int idCliente;
    private String fecha; 
    private double total;
    private List<DetallesFactura> ListaDtlls;

    public Factura() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetallesFactura> getListaDtlls() {
        return ListaDtlls;
    }

    public void setListaDtlls(List<DetallesFactura> ListaDtlls) {
        this.ListaDtlls = ListaDtlls;
    }
   
}
