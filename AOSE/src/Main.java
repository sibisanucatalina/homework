import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        List<String> initialConfiguration = new ArrayList<String>();
        List<String> finalConfiguration = new ArrayList<String>();
        List<String> finalConfiguration2 = new ArrayList<String>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("in.txt"));
            String line = reader.readLine();
            while (line != null) {
                initialConfiguration.add(line);
                line = reader.readLine();
            }
            reader.close();

            reader = new BufferedReader(new FileReader("out.txt"));
            line = reader.readLine();
            while (line != null) {
                finalConfiguration.add(line);
                line = reader.readLine();
            }
            reader.close();

            reader = new BufferedReader(new FileReader("out2.txt"));
            line = reader.readLine();
            while (line != null) {
                finalConfiguration2.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("BlockWorld");

        Table table = new Table(initialConfiguration, finalConfiguration, finalConfiguration2);

        frame.getContentPane().add(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 700);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
