package wpy.twitterbot.viewer.model;

public class DailyStats {

    private String date;
    private double valence;
    private int volume;
    private double price;

    public DailyStats(String date, String valence, String volume, String price) {
        super();
        this.date = date;
        this.valence = Double.parseDouble(valence);
        this.volume = Integer.parseInt(volume);
        this.price = Double.parseDouble(price);
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
