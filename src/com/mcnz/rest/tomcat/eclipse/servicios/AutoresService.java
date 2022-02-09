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

	private static ArrayList<Autores> listaAutores = new ArrayList<>();

	@GET
	public Response getAutores() {
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
		Autores autor;

		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		String consulta = "SELECT * FROM autores WHERE ID=" + autorId + ";";
		System.out.println(consulta);
		ResultSet rs = cc.ejecutarConsulta(consulta);

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

	@DELETE
	@Path("{autorId}")
	public Response deleteAutores(@PathParam("autorId") int autorId) {
		ConexionController cc = new ConexionController();
		int rs = 0;
		cc.iniciarConexion();

		//Connection conexion;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			conexion = DriverManager.getConnection("jdbc:sqlite:/home/tsi/eclipse-workspace/tomcat-rest-eclipse/propuestas.db");
//			System.out.println("Conexión con la Base de Datos realizada con éxito");
////			String sql = "DELETE FROM a WHERE ID=?";
////			PreparedStatement stmt = conexion.prepareStatement(sql);
////			stmt.setInt(1, autorId);
////			rs = stmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("La conexión con la Base de Datos ha fallado");
//			e.printStackTrace();
//		} catch (Exception e) {
//			System.out.println("Error al cargar el driver sqlite");
//			e.printStackTrace();
//		}
		// Ejecutar sentencia SQL
		rs = cc.ejecutarUpdate("DELETE FROM autores WHERE ID=" + autorId);

		
		cc.cerrarConexion();
		
		if (rs == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Autor no encontrado").build();
		}
		
		return Response.status(Status.OK).entity("Autor eliminado").build();
	}
}
