package org.example;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.config.AppConfig;
import org.example.db.*;
import org.example.resources.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Objects;

public class ChoresBuddyApplication extends Application<AppConfig> {
    public static void main(String[] args) throws Exception {
        new ChoresBuddyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        // Initialization logic
    }

    @Override
    public void run(AppConfig config, Environment environment) {
        final Jdbi jdbi = Jdbi.create(config.getDataSourceFactory().getUrl(),
                Objects.requireNonNull(config.getDataSourceFactory().getUser()),
                Objects.requireNonNull(config.getDataSourceFactory().getPassword()));
        jdbi.installPlugin(new SqlObjectPlugin());

        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        environment.jersey().register(new UserResource(userDAO));
    }
}
