package modelo.empleado;

import java.util.List;

public class DtoListaEmpleados {

    private String status;
    private String mensaje;
    private List<Empleado> ListaEmpleados;

    public DtoListaEmpleados() {
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

    public List<Empleado> getListaEmpleados() {
        return ListaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> ListaEmpleados) {
        this.ListaEmpleados = ListaEmpleados;
    }

}
