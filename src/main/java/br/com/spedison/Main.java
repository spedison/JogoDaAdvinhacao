package br.com.spedison;

import br.com.spedison.cliente.BinaryClient;
import br.com.spedison.cliente.SequentialClient;
import br.com.spedison.server.GuessServer;
import br.com.spedison.utils.ConfiguracaoURL;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        /***
         * 0 - comando server,binario,sequencial.
         * 1 - nome do servidor com porta exemplo : http://localhost:8080
         * 2 - para cliente max, para server nao tem.
         */

        ConfiguracaoURL.protocoloEHost = args[1];

        if (args[0].equalsIgnoreCase("server")) {
            GuessServer.main(args);
        }else if (args[0].equalsIgnoreCase("binario")) {
            BinaryClient.main(args);
        }else if (args[0].equalsIgnoreCase("sequencial")) {
            SequentialClient.main(args);
        }
    }
}