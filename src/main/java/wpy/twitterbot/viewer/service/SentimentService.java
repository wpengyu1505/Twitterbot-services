package wpy.twitterbot.viewer.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import wpy.twitterbot.viewer.model.Sentiment;

public class SentimentService {

    public String get(String symbol, String date) throws IOException, ParseException {
        ArrayList<String> words = new ArrayList<String>();
        words.add("time");
        words.add("good");

        Sentiment sentiment1 = new Sentiment("20160728195804", "Hellow world1", "3.5", "4.5", words);
        Sentiment sentiment2 = new Sentiment("20160728195805", "Hellow world2", "3.5", "4.7", words);

        ArrayList<Sentiment> sentiments = new ArrayList<Sentiment>();
        sentiments.add(sentiment2);
        sentiments.add(sentiment1);

        // HBaseUtils utils = new HBaseUtils();
        //
        // ArrayList<Sentiment> sentiments = utils.getTweets(symbol, date);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(sentiments);
    }
}
