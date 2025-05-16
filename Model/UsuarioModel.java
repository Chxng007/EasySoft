package Model;

public class UsuarioModel {
    
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private String peticion;
    private String direccion;
    private int pago;

    public UsuarioModel() {
    }

    
    
    public UsuarioModel(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.peticion = peticion;
        this.direccion = direccion;
        this.pago = pago;
    }

    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getPeticion() {
        return peticion;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPago() {
        return pago;
    }

    // Setters (si los necesitas)
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", telefono=" + telefono + ", peticion=" + peticion + ", direccion=" + direccion + ", pago=" + pago + '}';
    }

    

    
    
}
