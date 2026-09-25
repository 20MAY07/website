import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            String html =
                    "<html>" +
                    "<head>" +
                    "<title>DevOps Website</title>" +
                    "<style>" +
                    "body { font-family: Arial; text-align: center; margin-top: 100px; }" +
                    "h1 { color: green; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h1>DevOps Website</h1>" +
                    "<p>Hello from Java!</p>" +
                    "<p>Jenkins + GitHub is working successfully.</p>" +
                    "</body>" +
                    "</html>";

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/html");

            exchange.sendResponseHeaders(
                    200, html.getBytes().length);

            OutputStream output = exchange.getResponseBody();
            output.write(html.getBytes());
            output.close();
        });

        server.start();

        System.out.println(
                "Website running at http://localhost:8080");
    }
}
