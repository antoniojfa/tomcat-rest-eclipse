package com.mcnz.rest.tomcat.eclipse.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mcnz.rest.tomcat.eclipse.conexion.ConexionController;
import com.mcnz.rest.tomcat.eclipse.entidades.Autores;

@Path("/autores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutoresService {

	@GET
	public Response getAutores() {
		ArrayList<Autores> listaAutores = new ArrayList<>();
		Autores autor;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		ResultSet rs = cc.ejecutarConsulta("SELECT * FROM autores;");

		try {
			// Leer el ResultSet
			while (rs.next()) {
				autor = new Autores();
				autor.setId(rs.getInt("ID"));
				autor.setNick(rs.getString("nick"));
				listaAutores.add(autor);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(listaAutores, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{autorId}")
	public Response getAutores(@PathParam("autorId") int autorId) {
		Autores autor = null;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		String consulta = "SELECT * FROM autores WHERE ID=" + autorId + ";";
		ResultSet rs = cc.ejecutarConsulta(consulta);

		try {
			// Leer el ResultSet
			while (rs.next()) {
				autor = new Autores();
				autor.setId(rs.getInt("ID"));
				autor.setNick(rs.getString("nick"));
			}

		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(autor, MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("{autorId}")
	public Response deleteAutores(@PathParam("autorId") int autorId) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("DELETE FROM autores WHERE ID=" + autorId);

		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Autor no encontrado").build();
		}
		
		return Response.status(Status.OK).entity("Autor eliminado").build();
	}
	
	@POST
	public Response crearAutores(Autores autor) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("INSERT INTO autores (ID, nick) VALUES (" + autor.getId() + ", '" + autor.getNick() + "');");
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al insertar autor").build();
		}
		
		return Response.status(Status.OK).entity("Autor insertado").build();
	}
	
	@PUT
	@Path("{autorId}")
	public Response actualizarAutores(@PathParam("autorId") int autorId, Autores autor) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("UPDATE autores SET nick='" + autor.getNick() + "' WHERE ID=" + autorId);
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al actualizar autor").build();
		}
		
		return Response.status(Status.OK).entity("Autor actualizado").build();
	}
}
