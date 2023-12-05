package org.borb;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordEventListener extends ListenerAdapter {
    public MaryoBot bot;
    public DiscordEventListener(MaryoBot bot) {
        this.bot = bot;
    }
}