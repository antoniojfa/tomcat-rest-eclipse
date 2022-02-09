package com.mcnz.rest.tomcat.eclipse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mcnz.rest.tomcat.eclipse.conexion.ConexionController;
import com.mcnz.rest.tomcat.eclipse.entidades.Autores;

@Path("/")
public class ScoreService {

	@GET
	@Path("/autores")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutores() {
		Autores autor = new Autores();
		ArrayList<Autores> listaAutores = new ArrayList<>();
		ConexionController cc = new ConexionController();
		cc.iniciarConexion();

		// Ejecutar sentencia SQL
		ResultSet rs = cc.ejecutarSentencia("SELECT * FROM autores;");

		try {
			// Leer el ResultSet
			while (rs.next()) {
				//mensaje = rs.getString("ID") + " - " + rs.getString("nick") + "\n";
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

	@GET
	@Path("/score/losses")
	@Produces("text/plain")
	public int getLosses() {
		return Score.LOSSES;
	}

	@GET
	@Path("/score/ties")
	@Produces("text/plain")
	public int getTies() {
		return Score.TIES;
	}

	@POST
	@Path("/score/wins")
	@Produces("text/plain")
	public int increaseWins() {
		return Score.WINS++;
	}

	@POST
	@Path("/score/ties")
	@Produces("text/plain")
	public int increaseTies() {
		return Score.WINS++;
	}

	@POST
	@Path("/score/losses")
	@Produces("text/plain")
	public int increaseLosses() {
		return Score.LOSSES++;
	}
}
