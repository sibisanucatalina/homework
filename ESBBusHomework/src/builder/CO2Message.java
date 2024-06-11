package builder;

public class CO2Message {
    public String date;
    public String technology;
    public String value;

    @Override
    public String toString() {
        return "CO2Message{" +
                "date='" + date + '\'' +
                ", technology='" + technology + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
