package br.com.kientrega;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CadastroTestLifeCycleManager implements QuarkusTestResourceLifecycleManager {

    static PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:13.1");

    @Override
    public Map<String, String> start() {
        POSTGRE_SQL_CONTAINER.start();
        Map<String, String> propriedades = new HashMap<>();
        propriedades.put("quarkus.datasource.jdbc.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
        propriedades.put("quarkus.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());

        return propriedades;
    }

    @Override
    public void stop() {
        if(POSTGRE_SQL_CONTAINER != null && POSTGRE_SQL_CONTAINER.isRunning()){
            POSTGRE_SQL_CONTAINER.stop();
        }
    }
}
