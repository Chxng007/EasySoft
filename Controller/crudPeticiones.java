package Controller;


public interface crudPeticiones {
    
    String crearArchivo(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago);
    
    String actualizarArchivo(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago);
    
    String eliminarArchivo(int numeroDocumento);
    
    String limpiarArchivo(int numeroDocumento);
   
}
