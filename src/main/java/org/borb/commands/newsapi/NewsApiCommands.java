package org.borb.commands.newsapi;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsApiCommands extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NewsApiCommands.class);
    private final NewsApiClient newsApiClient;
    private static final String API_KEY = "836ffd7035d24f9dabd389debe63851a";  // Added API key

    public NewsApiCommands() {
        this.newsApiClient = new NewsApiClient(API_KEY);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();

        if (content.startsWith("!world")) {
            newsApiClient.getTopHeadlines(
                    new TopHeadlinesRequest.Builder()
                            .q("Trump")
                            .language("en")
                            .build(),
                    new NewsApiClient.ArticlesResponseCallback() {
                        @Override
                        public void onSuccess(ArticleResponse response) {
                            if (response.getArticles() == null || response.getArticles().isEmpty()) {
                                channel.sendMessage("No articles found.").queue();
                                return;
                            }
                            
                            try {
                                String title = response.getArticles().getFirst().getTitle();
                                channel.sendMessage(title).queue();
                            } catch (Exception e) {
                                logger.error("Error processing news response", e);
                                channel.sendMessage("Error getting news articles.").queue();
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            logger.error("Failed to fetch news", throwable);
                            channel.sendMessage("Failed to fetch news articles.").queue();
                        }
                    }
            );
        }
    }
}

// todo iterate if "world 5" for 5 times and get 5 articles, add name of topic in command

