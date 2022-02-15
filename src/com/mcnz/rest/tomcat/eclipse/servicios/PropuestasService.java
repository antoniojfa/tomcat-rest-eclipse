package com.mcnz.rest.tomcat.eclipse.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mcnz.rest.tomcat.eclipse.conexion.ConexionController;
import com.mcnz.rest.tomcat.eclipse.entidades.Propuestas;

@Path("/propuestas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropuestasService {

	@GET
	public Response getPropuestas() {
		ArrayList<Propuestas> listaPropuestas = new ArrayList<>();
		Propuestas propuesta;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		ResultSet rs = cc.ejecutarConsulta("SELECT * FROM propuestas;");

		try {
			// Leer el ResultSet
			while (rs.next()) {
				propuesta = new Propuestas();
				propuesta.setId(rs.getInt("ID"));
				propuesta.setTitulo(rs.getString("titulo"));
				propuesta.setIdioma(rs.getString("idioma"));
				propuesta.setFecha(rs.getString("fecha"));
				propuesta.setIdAutor(rs.getInt("ID_autor"));
				listaPropuestas.add(propuesta);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(listaPropuestas, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{propuestaId}")
	public Response getPropuestas(@PathParam("propuestaId") int propuestaId) {
		Propuestas propuesta = null;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		String consulta = "SELECT * FROM propuestas WHERE ID=" + propuestaId + ";";
		ResultSet rs = cc.ejecutarConsulta(consulta);

		try {
			// Leer el ResultSet
			while (rs.next()) {
				propuesta = new Propuestas();
				propuesta.setId(rs.getInt("ID"));
				propuesta.setTitulo(rs.getString("titulo"));
				propuesta.setIdioma(rs.getString("idioma"));
				propuesta.setFecha(rs.getString("fecha"));
				propuesta.setIdAutor(rs.getInt("ID_autor"));
			}

		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(propuesta, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response crearPropuestas(Propuestas propuesta) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("INSERT INTO propuestas (ID, titulo, idioma, fecha, ID_autor) VALUES (" + propuesta.getId() + ", '" + propuesta.getTitulo() + "', '" + propuesta.getIdioma() + "', '" + propuesta.getFecha() + "', " + propuesta.getIdAutor() + ");");
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al insertar propuesta").build();
		}
		
		return Response.status(Status.OK).entity("Propuesta insertada").build();
	}
	
	@PUT
	@Path("{propuestaId}")
	public Response actualizarPropuestas(@PathParam("propuestaId") int propuestaId, Propuestas propuesta) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("UPDATE propuestas SET titulo = '" + propuesta.getTitulo() + "', idioma = '" + propuesta.getIdioma() + "', fecha = '" + propuesta.getFecha() + "' WHERE id = " + propuesta.getId());	
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al actualizar propuesta").build();
		}
		
		return Response.status(Status.OK).entity("Propuesta actualizada").build();
	}
	
	@DELETE
	@Path("{propuestaId}")
	public Response deletePropuestas(@PathParam("propuestaId") int propuestaId) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("DELETE FROM propuestas WHERE ID=" + propuestaId);

		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Propuesta no encontrada").build();
		}
		
		return Response.status(Status.OK).entity("Propuesta eliminada").build();
	}
	
}
