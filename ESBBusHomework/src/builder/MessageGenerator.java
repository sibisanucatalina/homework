package builder;

import java.util.List;

public class MessageGenerator {
    MessageBuilder builder;

    public MessageGenerator(MessageBuilder builder) {
        this.builder = builder;
    }

    public void makeNO2Message(List<String> data) {
        builder.create();
        builder.addDate(data.get(0));
        builder.addErrorRange(data.get(1));
        builder.addValue(data.get(2));
    }

    public void makeCO2Message(List<String> data) {
        builder.create();
        builder.addDate(data.get(0));
        builder.addTechnology(data.get(1));
        builder.addValue(data.get(2));
    }
}
