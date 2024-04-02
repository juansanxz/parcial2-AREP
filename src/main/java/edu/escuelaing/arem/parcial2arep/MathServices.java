package edu.escuelaing.arem.parcial2arep;

import java.util.ArrayList;

import static spark.Spark.*;
import static spark.Spark.get;

public class MathServices {
    public static void main(String... args){
        port(getPort());
        get("/factors", (req,res) -> {
            int value = Integer.parseInt(req.queryParams("value"));
            res.type("application/json");
            String answer = "{\r\n" +
                    "\"operation\": \"factors\",\r\n" +
                    "\"input\": " + value + ",\r\n" +
                    "\"output\": \"" + factors(value) + "\"\r\n" +
                    "}";
            return answer;
        });

        get("/primes", (req, res) -> {
            int value = Integer.parseInt(req.queryParams("value"));
            res.type("application/json");
            String answer = "{\r\n" +
                    "\"operation\": \"primes\",\r\n" +
                    "\"input\": " + value + ",\r\n" +
                    "\"output\": \"" + primes(value) + "\"\r\n" +
                    "}";
           return answer;
        });

    }


    private static String factors(int n){
        String answer = "1";
        for (int i = 2; i < n/2 + 1; i++) {
            if ((n % i) == 0) {
                answer = answer + "," + i;
            }
        }
        if(n != 1) {
            answer = answer + "," + n;
        }


        return answer;

    }

    private static String primes(int n) {
        String answer = "2";

        if (n > 2) {
            for (int i = 3; i < n + 1; i++) {
                String[] factors = factors(i).split(",");

                if (factors.length == 2) {
                    answer = answer + "," + i;
                    System.out.println(i);
                }

            }
        } else if (n == 1){
            return "1 is not a prime";

        }

        return answer;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

}
