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
        if (content.equals("!shirley")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1182322436646125589/remix-0389d2b9-6372-418f-901d-70d52cf92069.png?ex=65844693&is=6571d193&hm=09302313eaa820c3acbff3cdea992f136068e0a2c7d09af2b5fe9546ae5ace25&=&format=webp&quality=lossless&width=671&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
        if (content.equals("!sorinuwu")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setImage("https://media.discordapp.net/attachments/1136329134998556842/1182322757342613606/1182321263973249074remix-1701958107945.png?ex=658446df&is=6571d1df&hm=745441f859937da8ee3e0853f78b9af9821e1bb4bee759f33245cc66ddf7b81b&=&format=webp&quality=lossless&width=671&height=671");
            channel.sendMessageEmbeds(result.build()).queue();
        }
        if (content.equals("!ganbatte")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setImage("https://cdn.discordapp.com/attachments/1136329134998556842/1182335936789479525/image.png?ex=65845326&is=6571de26&hm=0015238dbcd7f48c1e093dede9ec7c5a55d926a0e9770c0c636b2904703fab3d&");
            channel.sendMessageEmbeds(result.build()).queue();
        }
    }
}
