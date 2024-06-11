package observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SensorPublisher {
    HashMap<SensorType, List<SensorObserver>> subscribersMap;
    List<String> filenames;

    public SensorPublisher() {
        subscribersMap = new HashMap<>();
        filenames = new ArrayList<>();

        filenames.add("CO2Data/data.txt");
        filenames.add("NO2Data/data.txt");
    }

    public void subscribe(SensorType type, SensorObserver observer) {
        if (subscribersMap.get(type) == null) {
            List<SensorObserver> list = new ArrayList<>();
            list.add(observer);
            subscribersMap.put(type, list);
        } else {
            subscribersMap.get(type).add(observer);
        }
    }

    public void unsubscribe(SensorType type, SensorObserver observer) {
        subscribersMap.get(type).remove(observer);
    }

    private void notifySubscribers(SensorType type, List<String> data) {
        for (SensorObserver subscriber : subscribersMap.get(type)) {
            subscriber.update(data);
        }
    }

    private List<String> readFile(String filename) {
        File file = new File(filename);
        Scanner myReader;
        List<String> data = new ArrayList<>();
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (myReader.hasNextLine()) {
            data.add(myReader.nextLine());
        }
        myReader.close();

        return data;
    }

    public void readDataFromFiles() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (String filename : filenames) {
                List<String> data = readFile(filename);
                if (!data.get(0).equals("2022-10-20")) {
                    if (filename.contains("CO2")) {
                        notifySubscribers(SensorType.CO2, data);
                    }
                    if (filename.contains("NO2")) {
                        notifySubscribers(SensorType.NO2, data);
                    }
                }
            }
        }
    }
}
