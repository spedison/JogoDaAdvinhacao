package br.com.spedison.utils;

import java.util.Objects;

public class ConfiguracaoURL {
    static public String protocoloEHost;

    static public String getCaminhoCompleto(String parteUrl) {
        return protocoloEHost + parteUrl;
    }

    static public int getPorta() {
        String strPorta = protocoloEHost.trim().replaceAll("^(https?:\\/\\/[^:\\/]+)(:\\d+)?(.*|$)", "$2");


        if (strPorta.isBlank())
            strPorta = "8080";
        else
            strPorta = strPorta.replaceAll("[:]","");

        return
                Integer.parseInt(strPorta);
    }

    static public String getHost(){

        if (Objects.isNull(protocoloEHost))
            return "";

        return protocoloEHost.trim().replaceAll("^(https?:\\/\\/)([^:\\/]+)(:\\d+)?(.*|$)", "$2");
    }


}
