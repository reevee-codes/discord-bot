package org.borb;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.borb.commands.air.AirPollutionCommands;
import org.borb.commands.newyorktimes.NewYorkTimesCommands;
import org.borb.commands.pictures.PictureCommands;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GrindAssistantBot {
    protected static GrindAssistantBot selfBot;
    private ShardManager shardManager = null;
    private final Properties properties;

    public GrindAssistantBot(String token) {
        properties = loadProperties();
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot, RIPPU");
            System.exit(0);
        }
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            props.load(input);
        } catch (IOException ex) {
            System.out.println("Error loading properties file");
            System.exit(1);
        }
        return props;
    }

    private ShardManager buildShardManager(String token) throws LoginException {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = properties.getProperty("airvisual.api.key");
        
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(
                        new PictureCommands(),
                        new AirPollutionCommands(restTemplate, apiKey),
                        new NewYorkTimesCommands()
                    );

        return builder.build();
    }
}
