package br.com.spedison.cliente;

import br.com.spedison.utils.ConfiguracaoURL;
import br.com.spedison.utils.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryClient {

    private static final Logger log = LoggerFactory.getLogger(BinaryClient.class);

    public static void main(String[] args) throws Exception {

        int attempts = 0;
        int max = 100;

        if (args.length > 2)
            max = Integer.parseInt(args[2]);

        int low = 1;
        int high = max;


        // Start guessing on the server
        String ret = HttpRequest.sendRequest( ConfiguracaoURL.getCaminhoCompleto("?acao=iniciar&max=%d".formatted(max)));
        log.info("Retorno do Servidor : " + ret);

        while (low <= high) {
            int mid = (low + high) / 2;
            attempts++;
            String response = HttpRequest.sendRequest( ConfiguracaoURL.getCaminhoCompleto("?acao=jogar&numero=" + mid));
            log.info("Resposta do Servidor :: " + response);

            if (response.toLowerCase().contains("maior")) {
                high = mid - 1;
            } else if (response.toLowerCase().contains("menor")) {
                low = mid + 1;
            } else {
                log.info(" Conseguiu terminar o jogo em " + attempts + " tentativas.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }
}
