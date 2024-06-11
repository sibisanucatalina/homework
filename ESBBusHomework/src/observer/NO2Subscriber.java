package observer;

import builder.*;

import java.util.List;

public class NO2Subscriber implements SensorObserver {
    MessageGenerator messageGenerator;
    NO2MessageBuilder builder;

    public NO2Subscriber() {
        builder = new NO2MessageBuilder();
        messageGenerator = new MessageGenerator(builder);
    }

    @Override
    public void update(List<String> data) {
        System.out.println("NO2 Observer update");

        messageGenerator.makeNO2Message(data);
        NO2Message no2Message = builder.getResult();

        System.out.println(no2Message);
    }
}
