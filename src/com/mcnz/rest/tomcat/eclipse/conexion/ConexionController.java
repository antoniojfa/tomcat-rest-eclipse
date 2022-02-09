package com.mcnz.rest.tomcat.eclipse.conexion;

import java.sql.*;

public class ConexionController {

	private Connection conexion = null;

	public void iniciarConexion() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:/home/tsi/eclipse-workspace/tomcat-rest-eclipse/propuestas.db");
			System.out.println("Conexión con la Base de Datos realizada con éxito");
		} catch (SQLException e) {
			System.out.println("La conexión con la Base de Datos ha fallado");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error al cargar el driver sqlite");
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
				System.out.println("Conexion finalizada con exito");
			} catch (SQLException e) {
				System.out.println("Error al desconetar con la Base de Datos");
				e.printStackTrace();
			}
		}
	}

	public ResultSet ejecutarConsulta(String sentencia) {
		PreparedStatement miStatement = null;
		ResultSet rs = null;
		if (conexion != null) {
			try {
				miStatement = conexion.prepareStatement(sentencia);
				rs = miStatement.executeQuery();
				System.out.println("Consulta realizada con exito");
				
			} catch (SQLException e) {
				System.out.println("Error al ejecutar la consulta SQL");
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int ejecutarUpdate(String sentencia) {
		PreparedStatement miStatement = null;
		int row = 0;
		if (conexion != null) {
			try {
				miStatement = conexion.prepareStatement(sentencia);
				row = miStatement.executeUpdate();
				System.out.println("Consulta de actualizacion realizada con exito");
			} catch (SQLException e) {
				System.out.println("Error al ejecutar la actualizacion SQL");
				e.printStackTrace();
			}
		}
		return row;
	}
}
