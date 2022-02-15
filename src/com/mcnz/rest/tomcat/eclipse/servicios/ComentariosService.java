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
import com.mcnz.rest.tomcat.eclipse.entidades.Comentarios;


@Path("/comentarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComentariosService {

	@GET
	public Response getComentarios() {
		ArrayList<Comentarios> listaComentarios = new ArrayList<>();
		Comentarios comentario;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		ResultSet rs = cc.ejecutarConsulta("SELECT * FROM comentarios WHERE ID_comentario_padre=0;");

		try {
			// Leer el ResultSet
			while (rs.next()) {
				comentario = new Comentarios();
				comentario.setId(rs.getInt("ID"));
				comentario.setComentario(rs.getString("comentario"));
				comentario.setFecha(rs.getString("fecha"));
				comentario.setLikes(rs.getInt("likes"));
				comentario.setDislikes(rs.getInt("dislikes"));
				comentario.setIdComentarioPadre(rs.getInt("ID_comentario_padre"));
				comentario.setIdPropuesta(rs.getInt("ID_propuesta"));
				comentario.setIdAutor(rs.getInt("ID_autor"));
				listaComentarios.add(comentario);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(listaComentarios, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{comentarioId}")
	public Response getComentarios(@PathParam("comentarioId") int comentarioId) {
		Comentarios comentario = null;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		String consulta = "SELECT * FROM comentarios WHERE ID=" + comentarioId + " AND ID_comentario_padre=0;";
		ResultSet rs = cc.ejecutarConsulta(consulta);

		try {
			// Leer el ResultSet
			while (rs.next()) {
				comentario = new Comentarios();
				comentario.setId(rs.getInt("ID"));
				comentario.setComentario(rs.getString("comentario"));
				comentario.setFecha(rs.getString("fecha"));
				comentario.setLikes(rs.getInt("likes"));
				comentario.setDislikes(rs.getInt("dislikes"));
				comentario.setIdComentarioPadre(rs.getInt("ID_comentario_padre"));
				comentario.setIdPropuesta(rs.getInt("ID_propuesta"));
				comentario.setIdAutor(rs.getInt("ID_autor"));
			}

		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		} finally {
			try{rs.close();  }catch(Exception ignored){}
		}

		cc.cerrarConexion();
		return Response.ok(comentario, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response crearComentarios(Comentarios comentario) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("INSERT INTO comentarios (ID, comentario, fecha, likes, dislikes, ID_comentario_padre, ID_propuesta, ID_autor) VALUES (" + comentario.getId() + ", '" + comentario.getComentario() + "', '" + comentario.getFecha() + "', " + comentario.getLikes() + ", " + comentario.getDislikes() + ", " + comentario.getIdComentarioPadre() + ", " + comentario.getIdPropuesta() + ", " + comentario.getIdAutor() + ");");
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al insertar comentario").build();
		}
		
		return Response.status(Status.OK).entity("Comentario insertado").build();
	}
	
	@PUT
	@Path("{comentarioId}")
	public Response actualizarComentarios(@PathParam("comentarioId") int comentarioId, Comentarios comentario) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("UPDATE comentarios SET comentario='" + comentario.getComentario() + "' WHERE ID=" + comentarioId);
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al actualizar comentario").build();
		}
		
		return Response.status(Status.OK).entity("Comentario actualizado").build();
	}
	
	@PUT
	@Path("likes/{comentarioId}")
	public Response lilkeComentarios(@PathParam("comentarioId") int comentarioId, Comentarios comentario) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("UPDATE comentarios SET likes = likes+1 WHERE ID=" + comentarioId);
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al actualizar comentario").build();
		}
		
		return Response.status(Status.OK).entity("Comentario actualizado").build();
	}
	
	@PUT
	@Path("dislikes/{comentarioId}")
	public Response dislilkeComentarios(@PathParam("comentarioId") int comentarioId, Comentarios comentario) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("UPDATE comentarios SET dislikes = dislikes+1 WHERE ID=" + comentarioId);
		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Error al actualizar comentario").build();
		}
		
		return Response.status(Status.OK).entity("Comentario actualizado").build();
	}
	
	@DELETE
	@Path("{comentarioId}")
	public Response deleteComentarios(@PathParam("comentarioId") int comentarioId) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		rs = cc.ejecutarUpdate("DELETE FROM comentarios WHERE ID=" + comentarioId);

		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Comentario no encontrado").build();
		}
		
		return Response.status(Status.OK).entity("Comentario eliminado").build();
	}
	
}
