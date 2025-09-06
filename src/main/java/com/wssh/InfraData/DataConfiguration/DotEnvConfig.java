package com.wssh.InfraData.DataConfiguration;

import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotEnvConfig {
    private static final Dotenv dotenv = Dotenv.configure().directory(".").load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
