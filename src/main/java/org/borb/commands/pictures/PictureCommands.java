package org.borb.commands.pictures;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PictureCommands extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.equals("!andzelika")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setTitle("¿nani?");
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1182319561475887216/remix-f0f08266-cba7-4f0c-a8e5-508bce911d1f.png?ex=658443e5&is=6571cee5&hm=87e3505e0d0af8c15f5dafe62ffebb27320c2c0e034dda2f33c456c411a4483d&=&format=webp&quality=lossless&width=671&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
        if (content.equals("!jacek")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setTitle("itadakimasu");
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1179779625757442108/IMG20231130144243.jpg?ex=657b0666&is=65689166&hm=b39cf34c23378709e40f83b8468a97c17bca8fafb7a9f662710b0f0dd233b51f&=&format=webp&width=503&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
    }
}
