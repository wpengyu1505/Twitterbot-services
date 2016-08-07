package wpy.twitterbot.viewer.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import wpy.twitterbot.viewer.model.Sentiment;

public class HBaseUtils {

    private static final String TBL_SENTIMENT = "SENTIMENT";
    private static final byte[] CONTENT_FAMILY = Bytes.toBytes("C");
    private static final byte[] TWEET_QUALIFIER = Bytes.toBytes("TWEET");
    private static final byte[] SENTIMETN_FAMILY = Bytes.toBytes("S");
    private static final byte[] VALENCE_QUALIFIER = Bytes.toBytes("VALENCE");
    private static final byte[] AROUSAL_QUALIFIER = Bytes.toBytes("AROUSAL");
    private static final byte[] DOMINANCE_QUALIFIER = Bytes.toBytes("DOMINANCE");
    private static final byte[] KEYWORDS_QUALIFIER = Bytes.toBytes("KEYWORD");

    private Configuration conf = null;

    public HBaseUtils() {
        conf = HBaseConfiguration.create();
    }

    @SuppressWarnings("deprecation")
    public ArrayList<Sentiment> getTweets(String symbol, String date) throws IOException, ParseException {

        HTable table = new HTable(conf, TBL_SENTIMENT);

        String currentRowKey = symbol + "|" + date;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        calendar.add(Calendar.DATE, 1);
        String nextDate = sdf.format(calendar.getTime());

        String stopRowKey = symbol + "|" + nextDate;
        Scan scan = new Scan();
        scan.setCaching(1000);
        scan.setCacheBlocks(true);
        scan.setStartRow(Bytes.toBytes(currentRowKey));
        scan.setStopRow(Bytes.toBytes(stopRowKey));
        ResultScanner scanner = table.getScanner(scan);
        int counter = 0;

        ArrayList<Sentiment> sentiments = new ArrayList<Sentiment>();
        for (Result result : scanner) {
            String key = Bytes.toString(result.getRow());
            String datetime = key.split("\\|")[1];
            String tweet = Bytes.toString(result.getColumnLatest(CONTENT_FAMILY, TWEET_QUALIFIER).getValue());
            String valence = Bytes.toString(result.getColumnLatest(SENTIMETN_FAMILY, VALENCE_QUALIFIER).getValue());
            String arousal = Bytes.toString(result.getColumnLatest(SENTIMETN_FAMILY, AROUSAL_QUALIFIER).getValue());
            String dominance = Bytes.toString(result.getColumnLatest(SENTIMETN_FAMILY, DOMINANCE_QUALIFIER).getValue());
            String keyword = Bytes.toString(result.getColumnLatest(SENTIMETN_FAMILY, KEYWORDS_QUALIFIER).getValue());

            ArrayList<String> keywords = new ArrayList<String>();
            for (String word : keyword.split("-")) {
                keywords.add(word);
            }
            sentiments.add(new Sentiment(datetime, tweet, valence, arousal, keywords));
            counter++;

            if (counter >= 20) {
                break;
            }
        }

        return sentiments;
    }

}
