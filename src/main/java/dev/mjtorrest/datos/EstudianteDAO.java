package dev.mjtorrest.datos;

import dev.mjtorrest.dominio.Estudiante;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                System.out.println("Ocurrio un error al cerrar conexion " + e.getMessage());
            }
        }
        return estudiantes;
    }

    // findById
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar estudiante");
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUE(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexion " + e.getMessage());
            }
        }
        return false;
    }

    // MAIN DE PRUEBA DE LISTAR ESTUDIANTES
    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        // AGREGAR ESTUDIANTE
        //var nuevoEstudiante = new Estudiante("Carlos", "Lara", "5684236952", "carlos@mail.com");
        //var agregar = estudianteDao.agregarEstudiante(nuevoEstudiante);
        //if(agregar)
            //System.out.println("Estudiante agregado: " + nuevoEstudiante);
        //else
            //System.out.println("No se agrego el estudiante:" + nuevoEstudiante);

        //MODIFICAR ESTUDIANTE
        var estudianteModificar = new Estudiante(1, "Juan Carlos", "Juarez", "55871255", "juan@email.com");
        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
        if (modificado)
            System.out.println("Estudiante modificado: " + estudianteModificar);
        else
            System.out.println("No se modifico estudiante: " + estudianteModificar);

        // LISTAR ESTUDIANTE
        System.out.println("Listar Estudiantes");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        // BUSCAR POR ID
        //var estudiante1 = new Estudiante(3);
        //System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        //var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        //if(encontrado)
            //System.out.println("Estudiante encontrado: " + estudiante1);
        //else
            //System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
    }
}
