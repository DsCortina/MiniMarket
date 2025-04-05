package modelo.cliente;

public class Cliente {

    private int id;
    private String nombres;
    private String documento;
    private String telefono;
    private String correoElectronico;

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "+ -- -- -- --"
                + "\nNombre: " + nombres
                + "\nDocumento: " + documento
                + "\nTelefono: " + telefono
                + "\nCorreo Electronico: " + correoElectronico
                + "\n";

    }

}
