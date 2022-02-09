package com.mcnz.rest.tomcat.eclipse.entidades;

public class Autores {
	private int id;
	private String nick;
	
	public Autores() {
		
	}
	
	public Autores(int id, String nick) {
		super();
		this.id = id;
		this.nick = nick;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", nick=" + nick;
	}
}
