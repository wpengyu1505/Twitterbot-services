package wpy.twitterbot.viewer.model;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Sentiment implements Serializable {

    private String tweetId;
    private String dateTime;
    private String tweet;
    private double valence;
    private double arousal;
    private ArrayList<String> keywords;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public Sentiment(String tweet, String dateTime, double valence, double arousal, ArrayList<String> keywords) {

        this.tweet = tweet;
        this.dateTime = dateTime;
        this.valence = valence;
        this.arousal = arousal;
        this.keywords = keywords;
    }

    public Sentiment(String tweet, String datetime, String valence, String arousal, ArrayList<String> keywords) {
        this.tweet = tweet;
        this.dateTime = datetime;
        this.valence = Double.parseDouble(valence);
        this.arousal = Double.parseDouble(arousal);
        this.keywords = keywords;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }

    public double getArousal() {
        return arousal;
    }

    public void setArousal(double arousal) {
        this.arousal = arousal;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String toString() {
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String keys = "";
        for (String key : keywords) {
            keys = keys + key + "-";
        }
        keys = keys.substring(0, keys.length() - 1);
        return "v=" + decimalFormat.format(valence) + ",a=" + decimalFormat.format(arousal) + ",key=" + keys;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
