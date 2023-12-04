package org.borb;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class MaryoBot {

        protected static MaryoBot selfBot;
    private ShardManager shardManager = null;
    public MaryoBot(String token) {
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

    // The JDA Shardmanager instance - this is the main point of the entire bot
    private ShardManager buildShardManager(String token) throws LoginException {
        // It is often better to load your token in from an external file or environment variable, especially if you plan on publishing the source code.
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token);

        return builder.build();
    }

    public ShardManager getJDA() {
        return shardManager;
    }
}
