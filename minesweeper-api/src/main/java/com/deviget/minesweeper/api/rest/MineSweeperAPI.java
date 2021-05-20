package com.deviget.minesweeper.api.rest;

import com.deviget.minesweeper.api.model.GameConfig;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.logging.Logger;

@RequestScoped
@Path("minesweeper")
public class MineSweeperAPI {

    private static final Logger logger = Logger.getLogger("MineSweeperAPI");

    @GET
    @Path("start/rows/{rows}/columns/{columns}/mines/{mines}")
    @Produces({MediaType.APPLICATION_JSON})
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "400",
                    description = "Bad parameters for the request",
                    content = @Content(mediaType = "text/plain")
            ),
            @APIResponse(
                    responseCode = "200",
                    description = "The configuration for the game.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameConfig.class)))})
    @Operation(
            summary = "Get the configuration to start the MineSweeper Game",
            description = "Creates the structure of the base application configuring the position for the mines and numbers."
    )
    public Response startGame(
            @Parameter(
                    description = "Number of rows used to configure the game.",
                    required = true,
                    example = "10",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("rows") Integer rows,
            @Parameter(
                    description = "Number of columns used to configure the game.",
                    required = true,
                    example = "15",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("columns") Integer columns,
            @Parameter(
                    description = "Number of mines used to configure the game.",
                    required = true,
                    example = "50",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("mines") Integer mines) {
        logger.info("rows:" + rows);
        logger.info("cols:" + columns);
        logger.info("mines:" + mines);
        if (Objects.isNull(rows) || Objects.isNull(columns) || Objects.isNull(mines)) {
            logger.severe(String.format("Bad parameters for the request row=%s, columns=%s and mines=%s", rows, columns, mines));
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameters for the request").build();
        }

        if (rows <=0 || columns <= 0 || mines <= 0) {
            logger.severe(String.format("Bad parameters for the request row=%s, columns=%s and mines=%s", rows, columns, mines));
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameters for the request").build();
        }

        GameConfig gameConfig = new GameConfig(Integer.valueOf(rows), Integer.valueOf(columns), Integer.valueOf(mines));
        gameConfig.configureGame();

        return Response
                .ok(gameConfig)
                .build();
    }
}
