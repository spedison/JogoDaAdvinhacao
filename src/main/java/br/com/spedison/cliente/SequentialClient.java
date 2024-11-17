package br.com.spedison.cliente;

import br.com.spedison.utils.ConfiguracaoURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class SequentialClient {
    private static final Logger log = LoggerFactory.getLogger(SequentialClient.class);

    public static void main(String[] args) throws Exception {
        int number = 1;
        int attempts = 0;

        int max = Integer.parseInt(args[2]);

        // Start guessing on the server
        String ret = sendRequest(ConfiguracaoURL.getCaminhoCompleto("?acao=iniciar&max="+max));
        log.info("Resposta do Server :: " + ret);

        Random r = new Random();
        number = r.nextInt(1,max);

        while (true) {
            attempts++;
            String response = sendRequest(ConfiguracaoURL.getCaminhoCompleto("?acao=jogar&numero=" + number));
            log.info("Resposta do Servidor :: " + response);

            if (response.contains("maior")) {
                number--;
            } else if (response.contains("menor")) {
                number++;
            } else {
                log.info("Conseguiu " + attempts + " tentativas!");
                break;
            }
            try{
                Thread.sleep(200);
            }catch (InterruptedException ie){}
        }
    }

    private static String sendRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }
}
