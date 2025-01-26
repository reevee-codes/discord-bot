package org.borb.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PictureCommand extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PictureCommand.class);
    private final Properties imageCommands;
    
    // Discord supported image formats
    private static final Set<String> SUPPORTED_FORMATS = new HashSet<>(Arrays.asList(
        ".jpg", ".jpeg", ".png", ".gif", ".webp"
    ));

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

        if (!isValidImageFormat(imagePath)) {
            logger.error("Unsupported image format for command: {}", command);
            channel.sendMessage("Sorry, this image format is not supported. Supported formats: JPG, JPEG, PNG, GIF, WEBP").queue();
            return;
        }

        if (imagePath.startsWith("http")) {
            EmbedBuilder result = new EmbedBuilder();
            result.setTitle(command);
            result.setImage(imagePath);
            channel.sendMessageEmbeds(result.build()).queue();
        } else {
            try {
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream == null) {
                    logger.error("Image file not found: {}", imagePath);
                    channel.sendMessage("Sorry, I couldn't find that image.").queue();
                    return;
                }
                
                String fileName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
                channel.sendFiles(FileUpload.fromData(imageStream, fileName)).queue();
            } catch (Exception e) {
                logger.error("Error sending local image for command: {}", command, e);
                channel.sendMessage("Sorry, there was an error sending the image.").queue();
            }
        }
    }

    private boolean isValidImageFormat(String path) {
        String lowerPath = path.toLowerCase();
        return SUPPORTED_FORMATS.stream().anyMatch(lowerPath::endsWith);
    }

    private String findImageByCommand(String command) {
        return imageCommands.getProperty(command);
    }
}
