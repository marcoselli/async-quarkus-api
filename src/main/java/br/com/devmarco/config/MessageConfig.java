package br.com.devmarco.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;


@ConfigMapping(prefix = "mensagem", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface MessageConfig {

    @WithName("exibicao")
    String getMensagem1();

    @WithName("nova")
    String getMensagem2();

}
