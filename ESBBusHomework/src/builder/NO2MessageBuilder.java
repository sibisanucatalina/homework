package builder;

public class NO2MessageBuilder implements MessageBuilder {
    NO2Message result;

    @Override
    public void create() {
        result = new NO2Message();
    }

    @Override
    public void addDate(String date) {
        result.date = date;
    }

    @Override
    public void addErrorRange(String errorRange) {
        result.errorRange = errorRange;
    }

    @Override
    public void addValue(String value) {
        result.value = value;
    }

    @Override
    public void addTechnology(String technology) {

    }

    public NO2Message getResult() {
        return result;
    }
}
