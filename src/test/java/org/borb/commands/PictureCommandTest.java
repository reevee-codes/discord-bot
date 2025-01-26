package org.borb.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PictureCommandTest {

    @Mock
    private MessageReceivedEvent event;
    @Mock
    private Message message;
    @Mock
    private MessageChannelUnion channel;
    @Mock
    private User user;
    @Mock
    private MessageCreateAction messageAction;

    private PictureCommand pictureCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pictureCommand = new PictureCommand();

        when(event.getMessage()).thenReturn(message);
        when(event.getChannel()).thenReturn(channel);
        when(event.getAuthor()).thenReturn(user);

        when(channel.sendMessageEmbeds(any(MessageEmbed.class))).thenReturn(messageAction);
        when(channel.sendMessage(anyString())).thenReturn(messageAction);
        doNothing().when(messageAction).queue();
    }

    @Test
    void whenBotMessage_thenNoResponse() {
        // given
        when(user.isBot()).thenReturn(true);

        // when
        pictureCommand.onMessageReceived(event);

        // then
        verify(channel, never()).sendMessage(anyString());
    }

    @Test
    void whenValidCommand_thenSendImage() {
        when(user.isBot()).thenReturn(false);
        when(message.getContentRaw()).thenReturn("!jacek");

        pictureCommand.onMessageReceived(event);

        verify(channel, times(1)).sendMessageEmbeds(any(MessageEmbed.class));
        verify(messageAction, times(1)).queue();
    }

    @Test
    void whenInvalidCommand_thenNoResponse() {
        when(user.isBot()).thenReturn(false);
        when(message.getContentRaw()).thenReturn("!invalidcommand");

        pictureCommand.onMessageReceived(event);

        verify(channel, never()).sendMessageEmbeds(any());
    }

    @Test
    void whenLocalImageNotFound_thenSendErrorMessage() {
        when(user.isBot()).thenReturn(false);
        when(message.getContentRaw()).thenReturn("!nonexistentimage");

        pictureCommand.onMessageReceived(event);

        verify(channel, never()).sendMessageEmbeds(any());
    }
} 