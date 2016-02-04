/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backbase.kalah;

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Stavroula
 */
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
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "helloοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοοο";
    }

    @GET
    @Path("/kalah")
    @Produces("text/html")
    public String getKalah() throws IOException {
        //Kalah.init();
        return Arrays.toString(Kalah.init());
    }

    @POST
    @Path("/kalahPlay")
    public String playKalah(int pit) throws IOException {
        
        if ( Kalah.flag == Kalah.playerSouth.id){
                Kalah.gameBoard[14] = Kalah.play(Kalah.playerSouth, pit);
        }
        else
        {        Kalah.gameBoard[14] = Kalah.play(Kalah.playerNorth, pit);
}
        
        return Arrays.toString(Kalah.gameBoard);

    }

    /**
     * PUT method for updating or creating an instance of ServiceResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
}
