package com.mcnz.rest.tomcat.eclipse.conexion;

import java.sql.*;

public class ConexionController {

	private Connection conexion = null;
	
	public void iniciarConexion() throws SQLException {
		conexion = DriverManager.getConnection("jdbc:sqlite:test.db");
	}
	
	public void cerrarConexion() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}
}
