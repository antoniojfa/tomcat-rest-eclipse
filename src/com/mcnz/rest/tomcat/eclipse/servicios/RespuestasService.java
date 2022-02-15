package com.mcnz.rest.tomcat.eclipse.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mcnz.rest.tomcat.eclipse.conexion.ConexionController;
import com.mcnz.rest.tomcat.eclipse.entidades.Comentarios;


@Path("/respuestas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RespuestasService {
	
	@GET
	@Path("{comentarioPadreId}")
	public Response getRespuestasByIdPadre(@PathParam("comentarioPadreId") int comentarioPadreId) {
		ArrayList<Comentarios> listaComentarios = new ArrayList<>();
		Comentarios comentario;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		ResultSet rs = cc.ejecutarConsulta("SELECT * FROM comentarios WHERE ID_comentario_padre=" + comentarioPadreId);

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

}
