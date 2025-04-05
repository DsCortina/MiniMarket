package modelo.cliente;

import java.util.List;

public class DtoListaClientes {
    
    private String status;
    private String mensaje;
    private List<Cliente> ListaCliente;

    public DtoListaClientes() {
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

    public List<Cliente> getCliente() {
        return ListaCliente;
    }

    public void setCliente(List<Cliente> Cliente) {
        this.ListaCliente = Cliente;
    }
    
    
    

}
