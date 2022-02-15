package com.mcnz.rest.tomcat.eclipse.entidades;

public class Comentarios {

	private int id;
	private String comentario;
	private String fecha;
	private int likes;
	private int dislikes;
	private int idComentarioPadre;
	private int idPropuesta;
	private int idAutor;
	
	public Comentarios() {
		super();
	}	
	
	public Comentarios(int id, String comentario, String fecha, int likes, int dislikes, int idComentarioPadre, int idPropuesta, int idAutor) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.fecha = fecha;
		this.likes = likes;
		this.dislikes =  dislikes;
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
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
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
