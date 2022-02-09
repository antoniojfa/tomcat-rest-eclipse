package com.mcnz.rest.tomcat.eclipse.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		ResultSet rs = cc.ejecutarSentencia("SELECT * FROM autores;");

		try {
			// Leer el ResultSet
			while (rs.next()) {
				//mensaje = rs.getString("ID") + " - " + rs.getString("nick") + "\n";
				autor = new Autores();
				autor.setId(rs.getInt("ID"));
				autor.setNick(rs.getString("nick"));
				listaAutores.add(autor);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar el ResultSet");
			e.printStackTrace();
		}

		cc.cerrarConexion();
		return Response.ok(listaAutores, MediaType.APPLICATION_JSON).build();
	}
}
