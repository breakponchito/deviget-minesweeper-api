package com.deviget.minesweeper.api.rest;

import com.deviget.minesweeper.api.model.GameConfig;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.logging.Logger;

@Path("minesweeper")
public class MineSweeperAPI {

    private static final Logger logger = Logger.getLogger("MineSweeperAPI");

    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }

    @GET
    @Path("start/rows/{rows}/columns/{columns}/mines/{mines}")
    @Produces({MediaType.APPLICATION_JSON})
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "The configuration for the game.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GameConfig.class)))})
    @Operation(
            summary = "Get the configuration to start the MineSweeper Game"
    )
    public Response startGame(@PathParam("rows") String rows,
                              @PathParam("columns") String columns,
                              @PathParam("mines") String mines) {
        logger.info("rows:"+rows);
        logger.info("cols:"+columns);
        logger.info("mines:"+mines);
        if(Objects.isNull(rows) || Objects.isNull(columns) || Objects.isNull(mines)) {
            return Response.serverError().build();
        }

        if(rows.isEmpty() || columns.isEmpty() || mines.isEmpty()) {
            return Response.serverError().build();
        }

        GameConfig gameConfig = new GameConfig(Integer.valueOf(rows), Integer.valueOf(columns), Integer.valueOf(mines));
        gameConfig.configureGame();


        return Response
                .ok(gameConfig)
                .build();
    }
}
