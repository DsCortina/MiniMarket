package modelo.producto;

import java.util.List;

public class DtoListaProducto {

    private String status;
    private String mensaje;
    private List<Producto> ListaProductos;

    public DtoListaProducto() {
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

    public List<Producto> getListaProductos() {
        return ListaProductos;
    }

    public void setListaProductos(List<Producto> ListaProductos) {
        this.ListaProductos = ListaProductos;
    }
    
    

}
