package observer;

import builder.CO2Message;
import builder.CO2MessageBuilder;
import builder.MessageGenerator;

import java.util.List;

public class CO2Subscriber implements SensorObserver {
    MessageGenerator messageGenerator;
    CO2MessageBuilder builder;

    public CO2Subscriber() {
        builder = new CO2MessageBuilder();
        messageGenerator = new MessageGenerator(builder);
    }

    @Override
    public void update(List<String> data) {
        System.out.println("CO2 Observer update");

        messageGenerator.makeCO2Message(data);
        CO2Message co2Message = builder.getResult();

        System.out.println(co2Message);
    }
}
