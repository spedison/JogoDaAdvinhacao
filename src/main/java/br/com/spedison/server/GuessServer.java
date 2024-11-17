package br.com.spedison.server;

import br.com.spedison.utils.ConfiguracaoURL;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;

public class GuessServer {
    private static final Logger log = LoggerFactory.getLogger(GuessServer.class);
    private static int numberToJogar;
    private static int temtativas;

    public static void main(String[] args) throws Exception {
        Server server = new Server(new InetSocketAddress(ConfiguracaoURL.getHost(), ConfiguracaoURL.getPorta()));
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        handler.addServlet(new ServletHolder(new GuessServlet()), "/*");
        log.info("Servidor Rodando em :: " + ConfiguracaoURL.getHost() + " na porta " + ConfiguracaoURL.getPorta());
        server.start();
        server.join();
    }

    public static class GuessServlet extends HttpServlet {

        public GuessServlet() {
            super();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String action = req.getParameter("acao");
            if ("iniciar".equals(action)) {
                int maxValue = Integer.parseInt(req.getParameter("max").trim());
                numberToJogar = new Random().nextInt(maxValue) + 1;
                temtativas = 0;
                log.info("defini o número %d. No intervalo de 1 a %d".formatted(numberToJogar, maxValue));
                resp.setStatus(200);
                resp.getWriter().write("Número definido. Pode começar.");
            } else if ("jogar".equals(action)) {
                int numeroEnviadoParaTentativa = Integer.parseInt(req.getParameter("numero").trim());
                temtativas++;
                if (numeroEnviadoParaTentativa > numberToJogar) {
                    resp.getWriter().write("%d é maior em %d tentativas".formatted(numeroEnviadoParaTentativa,
                            temtativas));
                    log.info("Tentativa %d".formatted(temtativas));
                } else if (numeroEnviadoParaTentativa < numberToJogar) {
                    resp.getWriter().write("%d é menor em %d tentativas".formatted(numeroEnviadoParaTentativa
                            , temtativas));
                    log.info("Tentativa %d".formatted(temtativas));
                } else {
                    resp.getWriter().write("Correto " + numeroEnviadoParaTentativa + " em " + temtativas + " tentativas!");
                    log.info("Acertou em %d tentativas".formatted(temtativas));
                }
                resp.setStatus(200);
            } else {
                resp.setStatus(404);
                resp.getWriter().write("opção não localizada");
            }
        }
    }
}