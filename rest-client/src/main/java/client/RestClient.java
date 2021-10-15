package client;

import org.codehaus.jackson.map.ObjectMapper;

import com.mm.dts.illustration.svc.data.SalesIllustrationInput;
import com.mm.dts.illustration.svc.data.SalesIllustrationResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient {

    public static String run() {

        System.out.println("running JAX RS client.. \n");

        try {
            URL url = new URL("http://blue-sky-docker-d3.private.massmutual.com:19690/IllustrationSvc/services/determineWhichApp");
            //URL url = new URL("http://localhost:8080/IllustrationSvc/services/determineWhichApp");
            System.out.print("Target URI: "); System.out.println(url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //  indicate that the application intends to write data to the URL connection
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            // send the request body in JSON format
            conn.setRequestProperty("Content-Type", "application/json");

            SalesIllustrationInput inputObj = new SalesIllustrationInput();

            ObjectMapper mapper = new ObjectMapper();
            String inputJsonString = mapper.writeValueAsString(inputObj);

            System.out.print("\nClient input: "); System.out.println(inputObj.toString());
            // System.out.println(inputJsonString);

            OutputStream os = conn.getOutputStream();
            os.write(inputJsonString.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String outputJsonString;
            while ((outputJsonString = br.readLine()) != null) {
                // System.out.println(outputJsonString);

                SalesIllustrationResult resultObj = mapper.readValue(outputJsonString, SalesIllustrationResult.class);
                System.out.print("\nServer response: "); System.out.println(resultObj);

                conn.disconnect();

                return outputJsonString;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String args[]) {
        run();
    }
}

