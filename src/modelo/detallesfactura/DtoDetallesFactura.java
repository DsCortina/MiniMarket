package modelo.detallesfactura;

public class DtoDetallesFactura {

    private String status;
    private String mensaje;
    private DetallesFactura DF;

    public DtoDetallesFactura() {
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

    public DetallesFactura getDF() {
        return DF;
    }

    public void setDF(DetallesFactura DF) {
        this.DF = DF;
    }

    

}
