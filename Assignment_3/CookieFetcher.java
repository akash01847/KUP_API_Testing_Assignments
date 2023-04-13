import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class CookieFetcher {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://google.com/");

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Send a GET request to the URL
        connection.setRequestMethod("GET");

        // Get the cookies from the response headers
        Map<String, List<String>> headers = connection.getHeaderFields();
        List<String> cookies = headers.get("Set-Cookie");

        // Print the cookies to the console
        System.out.println("Cookies: " + cookies);
    }
}
