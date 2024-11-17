package br.com.spedison;

import br.com.spedison.utils.ConfiguracaoURL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfiguracaoURLTest {

    @Test
    public void getPorta() {

        ConfiguracaoURL.protocoloEHost = "http://localhost:8080/agoravai.com";
        int porta = ConfiguracaoURL.getPorta();
        Assertions.assertEquals(8080, porta);

        ConfiguracaoURL.protocoloEHost = "http://localhost/agoravai.com";
        porta = ConfiguracaoURL.getPorta();
        Assertions.assertEquals(8080, porta);

        ConfiguracaoURL.protocoloEHost = "http://192.168.10.21:8181/agoravai.com";
        porta = ConfiguracaoURL.getPorta();
        Assertions.assertEquals(8181, porta);
    }

    @Test
    public void getHost(){
        ConfiguracaoURL.protocoloEHost = "http://localhost:8080/agoravai.com";
        String host = ConfiguracaoURL.getHost();
        Assertions.assertEquals("localhost", host);

        ConfiguracaoURL.protocoloEHost = "http://www.minhacasa.com.br/agoravai.com";
        host = ConfiguracaoURL.getHost();
        Assertions.assertEquals("www.minhacasa.com.br", host);

        ConfiguracaoURL.protocoloEHost = "http://192.168.10.21:8181/agoravai.com";
        host = ConfiguracaoURL.getHost();
        Assertions.assertEquals("192.168.10.21", host);
    }

}