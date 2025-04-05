package modelo.detallesfactura;

public class DetallesFactura {

    private int idFactura;
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double valorIndv;
    private double subtotal;

    public DetallesFactura() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorIndv() {
        return valorIndv;
    }

    public void setValorIndv(double valorIndv) {
        this.valorIndv = valorIndv;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return   nombreProducto + " " + idProducto +" "+  valorIndv +" "+ subtotal  + " "+ "\n";
    }

    
}
