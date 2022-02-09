package com.mcnz.rest.tomcat.eclipse;

import java.sql.SQLException;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.mcnz.rest.tomcat.eclipse.conexion.ConexionController;


@ApplicationPath("/")
public class ScoreApplication extends ResourceConfig {

	public ScoreApplication() {
        packages("com.mcnz.rest.tomcat.eclipse");
        ConexionController cc = new ConexionController();
        try {
        	Class.forName("org.sqlite.JDBC");
			cc.iniciarConexion();
			System.out.println("Conexion realizada con éxito");
		} catch (SQLException e) {
			System.out.println("La conexión con la Base de Datos ha fallado");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error al cargar el driver sqlite");
			e.printStackTrace();
		} finally {
			try {
				cc.cerrarConexion();
				System.out.println("Conexion finalizada con éxito");
			} catch (SQLException e) {
				System.out.println("Error al cerrar la Base de Datos");
				e.printStackTrace();
			}
		}
    }
}
