package org.packt.swarm.petstore.order;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;

public class Main {

    public static void main(String[] args) throws Exception {
        DatasourcesFraction datasourcesFraction = new DatasourcesFraction()
                //1
                .jdbcDriver("postgres", (d) -> {
                    d.driverClassName("org.postgresql.Driver");
                    d.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
                    d.driverModuleName("org.postgresql.jdbc");
                })
                .dataSource("OrderDS", (ds) -> {
                    ds.driverName("postgres");
                    ds.connectionUrl("jdbc:postgresql://"
                            + System.getenv("POSTGRESQL_HOST")
                            + "/"+  System.getenv("POSTGRESQL_SCHEMA"));
                    ds.userName(System.getenv("POSTGRESQL_USER"));
                    ds.password(System.getenv("POSTGRESQL_PASSWORD"));
                });
        
        Swarm swarm = new Swarm();
        swarm.fraction(datasourcesFraction);
        swarm.start().deploy();
    }
}