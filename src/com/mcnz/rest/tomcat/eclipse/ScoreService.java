package com.mcnz.rest.tomcat.eclipse;

import javax.ws.rs.*;

@Path("/")
public class ScoreService {

	@GET
	@Path("/score/wins")
	@Produces("text/plain")
	public int getWins() {
		return Score.WINS;
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
