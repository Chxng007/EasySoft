package controller;

import Controller.crudPeticiones;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Properties;
import Model.UsuarioModel;

public class UsuarioController implements crudPeticiones {

    private UsuarioModel usuario_model;
    private ArrayList<String[]> filas;
    private String mensaje;

    private final String separador = File.separator;
    private final String ruta = System.getProperty("user.dir") + separador + "usuarios" + separador;
    private final String extension = ".txt";

    public UsuarioController() {
        usuario_model = new UsuarioModel();
        mensaje = "";
        File directorio = new File(ruta);
        directorio.mkdir();
    }

    public UsuarioModel getUsuario() {
        return usuario_model;
    }

    private void instanciarUsuario(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago) {
        usuario_model = new UsuarioModel(id, nombre, apellido, correo, telefono, peticion, direccion, pago);
    }

    private void actualizarUsuarios(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago) {
        getUsuario().setId(id);
        getUsuario().setNombre(nombre);
        getUsuario().setApellido(apellido);
        getUsuario().setCorreo(correo);
        getUsuario().setTelefono(telefono);
        getUsuario().setPeticion(peticion);
        getUsuario().setDireccion(direccion);
        getUsuario().setPago(pago);
    }

    @Override
    public String crearArchivo(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago) {
        instanciarUsuario(id, nombre, apellido, correo, telefono, peticion, direccion, pago);
        File directorio = new File(ruta);
        File archivo = new File(ruta + id + extension);

        if (!archivo.exists()) {
            try {
                directorio.mkdir();
                try (Formatter formatter = new Formatter(archivo)) {
                    formatter.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n",
                            "Identificacion: " + usuario_model.getId(),
                            "nombre: " + usuario_model.getNombre(),
                            "apellido: " + usuario_model.getApellido(),
                            "correo: " + usuario_model.getCorreo(),
                            "telefono: " + usuario_model.getTelefono(),
                            "peticion: " + usuario_model.getPeticion(),
                            "direccion: " + usuario_model.getDireccion(),
                            "pago: " + usuario_model.getPago());

                    mensaje = "archivo " + usuario_model.getId() + " creado";

                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            mensaje = "el archivo: " + id + extension + " ya existe";
        }
        return mensaje;
    }

    @Override
    public String actualizarArchivo(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, int pago) {
        actualizarUsuarios(id, nombre, apellido, correo, telefono, peticion, direccion, pago);
        File ubicacionArchivo = new File(ruta + id + extension);
        if (ubicacionArchivo.exists()) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(ubicacionArchivo))) {
                printWriter.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n",
                        "Identificacion: " + usuario_model.getId(),
                        "nombre: " + usuario_model.getNombre(),
                        "apellido: " + usuario_model.getApellido(),
                        "correo: " + usuario_model.getCorreo(),
                        "telefono: " + usuario_model.getTelefono(),
                        "peticion: " + usuario_model.getPeticion(),
                        "direccion: " + usuario_model.getDireccion(),
                        "pago: " + usuario_model.getPago());

                mensaje = "archivo " + usuario_model.getId() + " actualizado";

            } catch (IOException e) {
                mensaje = "error, vuelva a intentar";
                System.out.println(e.getMessage());
            }
        } else {
            mensaje = "archivo " + id + " no existe";
        }
        return mensaje;
    }

    @Override
    public String eliminarArchivo(int numeroDocumento) {
        File archivo = new File(ruta + numeroDocumento + extension);
        if (archivo.exists()) {
            try {
                if (archivo.delete()) {
                    mensaje = "El registro fue eliminado correctamente.";
                } else {
                    mensaje = "El registro se encuentra en uso y no pudo eliminarse.";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                mensaje = "Error al intentar eliminar. Vuelva a intentarlo.";
            }
        } else {
            mensaje = "El registro no existe.";
        }
        return mensaje;

    }

    @Override
    public String limpiarArchivo(int numeroDocumento) {
        File archivo = new File(ruta + numeroDocumento + extension);
        if (archivo.exists()) {
            try {
                if (archivo.exists()) {
                    mensaje = "El registro fue limpiado correctamente.";
                } else {
                    mensaje = "El registro se encuentra en uso y no pudo eliminarse.";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                mensaje = "Error al intentar limpiar el archivo. Vuelva a intentarlo.";
            }
        } else {
            mensaje = "El registro no existe.";
        }
        return mensaje;

    }

     public ArrayList<String[]> mostrarPersonasTabla() {
        filas = new ArrayList<>();
        File archivo = new File(ruta);
        File[] listarArchivos = archivo.listFiles();

        try {
            for (File file : listarArchivos) {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    Properties properties = new Properties();
                    properties.load(fileInputStream);
                    filas.add(new String[]{
                        properties.getProperty("Identificacion"),
                        properties.getProperty("nombre"),
                        properties.getProperty("apellido"),
                        properties.getProperty("correo"),
                        properties.getProperty("telefono"),
                        properties.getProperty("peticion"),
                        properties.getProperty("direccion"),
                        properties.getProperty("pago")
                    });
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return filas;
    }


    public boolean existePersona(int id) {
        File archivo = new File(ruta + id + extension);
        return archivo.exists();
    }

    public boolean validarFormulario(int id, String nombre, String apellido, String correo, int telefono, String peticion, String direccion, double pago) {
        return !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !peticion.isEmpty() && !direccion.isEmpty() && id > 0 && telefono > 0 && pago > 0;
    }

}
    