package modelo.empleado;

public class Empleado {

    private int id;
    private String nombreCompleto;
    private String documento;
    private String correoElectronico;

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "-- -- -- -- -- -- -- -- --"
                + "\nNombre Completo: " + nombreCompleto
                + "\nDocumento: " + documento
                + "\nCorreo Electronico: " + correoElectronico
                + "\n";
    }

}
