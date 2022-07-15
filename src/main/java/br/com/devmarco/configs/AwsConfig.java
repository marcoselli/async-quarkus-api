package br.com.devmarco.configs;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "aws", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface AwsConfig {

    @WithName("acess.key.id")
    String getAcessKeyId();

    @WithName("secret.key.id")
    String getSecretAcessKey();

    @WithName("default.region")
    String getDefaultRegion();

}
