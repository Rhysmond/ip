package nyanchan.main;

import nyanchan.app.ChatBot;

public class Nyanchan {
    public static String name = "Nyanchan";
    public static void main(String[] args) {
        ChatBot chatbot = new ChatBot("./data/nyanchan.txt");
        chatbot.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public static String getResponse(String input) {
        return "Nyanchan heard: " + input;
    }
}
