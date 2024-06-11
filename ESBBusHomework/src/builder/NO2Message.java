package builder;

public class NO2Message {
    public String date;
    public String errorRange;
    public String value;

    @Override
    public String toString() {
        return "NO2Message{" +
                "date='" + date + '\'' +
                ", errorRange='" + errorRange + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
