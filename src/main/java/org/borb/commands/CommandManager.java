package org.borb.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.equals("!ping")) {
            //debug purposes
            channel.sendMessage("Pong!").queue();
        }
        if (content.equals("!jacek")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setTitle("only carbs <3");
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1179779625757442108/IMG20231130144243.jpg?ex=657b0666&is=65689166&hm=b39cf34c23378709e40f83b8468a97c17bca8fafb7a9f662710b0f0dd233b51f&=&format=webp&width=503&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
        if (content.equals("!kocia")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1181625789469433927/20231205_165848.jpg?ex=6581bdc5&is=656f48c5&hm=e0450c0e394900bf571ea9348d963efaa523f2697ecaef69dc2a5b382cbfc35a&=&format=webp&width=302&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
    }


}
