package dev.mjtorrest.datos;

import dev.mjtorrest.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static dev.mjtorrest.conexion.Conexion.getConexion;


public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al seleccionar datos " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion");
            }
        }
        return estudiantes;
    }

    // MAIN DE PRUEBA DE LISTAR ESTUDIANTES
    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        System.out.println("Listar Estudiantes");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);
    }
}
