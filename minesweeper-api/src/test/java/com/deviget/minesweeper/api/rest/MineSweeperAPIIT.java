package com.deviget.minesweeper.api.rest;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class MineSweeperAPIIT {
    private static String port;
    private static String baseUrl;
    private final String GAME_CONFIG = "game/minesweeper/start/rows/{rows}/columns/{columns}/mines/{mines}";
    private Client client;

    @BeforeAll
    public static void oneTimeSetup() {
        port = System.getProperty("http.port");
        baseUrl = "http://localhost:" + port + "/";
    }

    @BeforeEach
    public void setup() {
        client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @AfterEach
    public void teardown() {
        client.close();
    }

    @Test
    public void testMineSweeperAPI() throws Exception {
        String localhosturl = baseUrl+GAME_CONFIG;
        WebTarget localhostarget = client.target(localhosturl).resolveTemplate("rows", "10").
                resolveTemplate("columns", "15").resolveTemplate("mines", "30");
        Response localResponse = localhostarget.request().accept("application/json").get();
        JsonObject localhostObj = localResponse.readEntity(JsonObject.class);
        Assert.assertNotNull(localhostObj.getInt("rows"));
        Assert.assertNotNull(localhostObj.getInt("columns"));
        Assert.assertNotNull(localhostObj.getInt("mines"));
        Assert.assertNotNull(localhostObj.getJsonArray("configArray"));
        localResponse.close();
    }
}
