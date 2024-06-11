package builder;

public class CO2MessageBuilder implements MessageBuilder {
    CO2Message result;
    @Override
    public void create() {
        result = new CO2Message();
    }

    @Override
    public void addDate(String date) {
        result.date = date;
    }

    @Override
    public void addErrorRange(String errorRange) {
    }

    @Override
    public void addValue(String value) {
        result.value = value;
    }

    @Override
    public void addTechnology(String technology) {
        result.technology = technology;
    }

    public CO2Message getResult() {
        return result;
    }
}
