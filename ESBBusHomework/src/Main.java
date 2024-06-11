import observer.CO2Subscriber;
import observer.NO2Subscriber;
import observer.SensorPublisher;
import observer.SensorType;

public class Main {
    public static void main(String[] args) {
        SensorPublisher publisher = new SensorPublisher();
        CO2Subscriber co2Subscriber = new CO2Subscriber();
        NO2Subscriber no2Subscriber = new NO2Subscriber();

        publisher.subscribe(SensorType.CO2, co2Subscriber);
        publisher.subscribe(SensorType.NO2, no2Subscriber);

        publisher.readDataFromFiles();
    }
}