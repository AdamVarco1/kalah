package com.backbase.kalah;

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Stavroula
 */
//This class is responsible for the rest services
//which are used to communicate with the front end
@Path("service")
public class ServiceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceResource
     */
    public ServiceResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.backbase.kalah.ServiceResource
     *
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "service";
    }

    //This service is called to initiate the game
    //Returns the game's board
    @GET
    @Path("/kalah")
    @Produces("text/html")
    public String getKalah() throws IOException {
        return Arrays.toString(Kalah.init());
    }

    //This service is called to make a move
    //Returns the game board
    @POST
    @Path("/kalahPlay")
    public String playKalah(int pit) throws IOException {

        if (Kalah.flag == Kalah.playerSouth.id) {
            Kalah.gameBoard[14] = Kalah.play(Kalah.playerSouth, pit);
        } else {
            Kalah.gameBoard[14] = Kalah.play(Kalah.playerNorth, pit);
        }

        return Arrays.toString(Kalah.gameBoard);

    }

}
