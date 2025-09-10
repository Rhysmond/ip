package nyanchan.main;

import nyanchan.app.ChatBot;

// For level 10 branch
public class Nyanchan {
    public static final String NAME = "Nyanchan";
    private final ChatBot chatbot;

    public Nyanchan() {
        this.chatbot = new ChatBot("./data/nyanchan.txt");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return chatbot.getResponse(input);
    }
}
