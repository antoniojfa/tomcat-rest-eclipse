package com.mcnz.rest.tomcat.eclipse.entidades;

public class Comentarios {

	private int id;
	private String comentario;
	private int idComentarioPadre;
	private int idPropuesta;
	private int idAutor;
	
	public Comentarios() {
		super();
	}	
	
	public Comentarios(int id, String comentario, int idComentarioPadre, int idPropuesta, int idAutor) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.idComentarioPadre = idComentarioPadre;
		this.idPropuesta = idPropuesta;
		this.idAutor = idAutor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getIdComentarioPadre() {
		return idComentarioPadre;
	}
	public void setIdComentarioPadre(int idComentarioPadre) {
		this.idComentarioPadre = idComentarioPadre;
	}
	public int getIdPropuesta() {
		return idPropuesta;
	}
	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	@Override
	public String toString() {
		return "id=" + id + ", comentario=" + comentario + ", idComentarioPadre=" + idComentarioPadre
				+ ", idPropuesta=" + idPropuesta + ", idAutor=" + idAutor;
	}
	
}
