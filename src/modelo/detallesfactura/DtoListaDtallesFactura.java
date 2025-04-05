package modelo.detallesfactura;

import java.util.List;

public class DtoListaDtallesFactura {

    private String status;
    private String mensaje;
    private List<DetallesFactura> ListaDetallesFacturas;

    public DtoListaDtallesFactura() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DetallesFactura> getListaDetallesFc() {
        return ListaDetallesFacturas;
    }

    public void setListaDetallesFc(List<DetallesFactura> ListaDetallesFc) {
        this.ListaDetallesFacturas = ListaDetallesFc;
    }
}
