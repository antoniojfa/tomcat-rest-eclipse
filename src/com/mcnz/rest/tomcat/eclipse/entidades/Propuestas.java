package com.mcnz.rest.tomcat.eclipse.entidades;

public class Propuestas {

	private int id;
	private String titulo;
	private String idioma;
	private int idAutor;
	
	
	
	public Propuestas() {
		super();
	}
	
	public Propuestas(int id, String titulo, String idioma, int idAutor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.idioma = idioma;
		this.idAutor = idAutor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	@Override
	public String toString() {
		return "id=" + id + ", titulo=" + titulo + ", idioma=" + idioma + ", idAutor=" + idAutor;
	}
	
	
}
