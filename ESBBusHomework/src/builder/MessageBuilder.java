package builder;

public interface MessageBuilder {
    void create();
    void addDate(String date);
    void addErrorRange(String error);
    void addValue(String value);
    void addTechnology(String technology);
}
