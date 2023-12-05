package org.borb;

import net.dv8tion.jda.api.requests.GatewayIntent;
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
            System.out.println("Failed to start bot, RIPPU");
            System.exit(0);
        }
    }

    private ShardManager buildShardManager(String token) throws LoginException {
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).
                          addEventListeners(new DiscordEventListener(this), new ListenerJakis());

        return builder.build();
    }

    public ShardManager getJDA() {
        return shardManager;
    }
}
