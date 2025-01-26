package org.borb.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PictureCommand extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PictureCommand.class);
    private final Properties imageCommands;

    public PictureCommand() {
        imageCommands = loadImageCommands();
    }

    private Properties loadImageCommands() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("imageCommands.properties")) {
            if (input == null) {
                logger.error("Unable to find imageCommands.properties");
                return props;
            }
            props.load(input);
        } catch (IOException ex) {
            logger.error("Error loading imageCommands.properties", ex);
        }
        return props;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String command = message.getContentRaw();
        
        if (command.startsWith("!")) {
            String imageCommand = command.substring(1); // Remove the ! prefix
            if (imageCommands.containsKey(imageCommand)) {
                pictureMessageBuilder(event.getChannel(), imageCommand);
            }
        }
    }

    private void pictureMessageBuilder(MessageChannel channel, String command) {
        String imagePath = findImageByCommand(command);
        if (imagePath == null) {
            logger.error("No image found for command: {}", command);
            return;
        }

        if (imagePath.startsWith("http")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setTitle(command);
            result.setImage(imagePath);
            channel.sendMessageEmbeds(result.build()).queue();
        } else {
            try {
                File imageFile = new File(getClass().getResource(imagePath).getFile());
                if (!imageFile.exists()) {
                    logger.error("Image file not found: {}", imagePath);
                    return;
                }
                channel.sendFiles(FileUpload.fromData(imageFile)).queue();
            } catch (Exception e) {
                logger.error("Error sending local image for command: {}", command, e);
            }
        }
    }

    private String findImageByCommand(String command) {
        return imageCommands.getProperty(command);
    }
}
