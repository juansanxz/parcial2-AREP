package edu.escuelaing.arem.parcial2arep;

import static spark.Spark.*;

/**
 *
 *
 */
public class ServiceProxy
{

    private static String[] urls = {"http://localhost:5000/", "http://localhost:5000/"};
    public static void main(String... args){
        port(getPort());
        staticFileLocation("/public");
        RemoteConnection remoteConnection = new RemoteConnection(urls);
        get("/factors", (req,res) -> {
            res.type("application/json");
            return remoteConnection.invoke("factors?value=" + req.queryParams("value"));
        });

        get("/primes", (req, res) -> {
            res.type("application/json");
            return remoteConnection.invoke("primes?value=" + req.queryParams("value"));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
