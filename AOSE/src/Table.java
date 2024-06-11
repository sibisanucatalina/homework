import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class Table extends JPanel implements Runnable {
    List<Stack<String>> blocks;

    Agent agent1;
    Agent agent2;

    ReactiveAgent reactiveAgent;

    int windowSize = 500;
    int spacing = windowSize / 8;

    public Table(List<String> initialConfiguration, List<String> finalConfiguration, List<String> finalConfiguration2) {
        Agent dummyAgent = new Agent(0, initialConfiguration);
        blocks = dummyAgent.result;

        Collections.reverse(blocks);

        agent1 = new Agent(1, finalConfiguration);
        agent2 = new Agent(2, finalConfiguration2);

        reactiveAgent = new ReactiveAgent();

        agent1.createPlan(blocks);
        agent2.createPlan(blocks);

        new Thread(this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    public void update() {
        repaint();
        // Wait a bit more to be sure everything is ok
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void drawBlock(Graphics g, String block, int i, int j) {
        int thickness = spacing / 15;
        int x = i * spacing;
        int y = windowSize - j * spacing - spacing;
        int width = spacing;
        int height = spacing;

        g.setColor(Color.black);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.fillRect(x + thickness, y + thickness, width - 2 * thickness, height - 2 * thickness);

        g.setColor(Color.black);
        int fontSize = 24;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.drawString(block, i * spacing + spacing / 2 + thickness - fontSize / 2, windowSize - j * spacing - spacing + spacing / 2 + thickness);
    }

    public void drawTable(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, windowSize, windowSize + 50, 30);

        //Draw legs
        g.fillRect(20, windowSize + 30, 30, 720 - windowSize);
        g.fillRect(windowSize - 40, windowSize + 30, 30, 720 - windowSize);
        g.setColor(Color.black);
    }

    public void drawComponents(Graphics g) {
        for (int i = 0; i < blocks.size(); i++) {
            for (int j = 0; j < blocks.get(i).size(); j++) {
                String block = blocks.get(i).get(j);
                drawBlock(g, block, i, j);
            }
        }

        drawTable(g);
    }

    public void executeCommand(Agent agent, Command command) {
        if (command == null) {
            return;
        }

        if(command.method.equals("UNSTACK")) {
            agent.unStack(blocks, command.blockToStack);
        } else if(command.method.equals("STACK")) {
            agent.stack(blocks, command.blockToSearch, command.blockToStack);
        }
    }

    public void startAgents() {
        int round = 0;
        while(true) {
            Command agent1Command = agent1.getNextPlan();
            Command agent2Command = agent2.getNextPlan();

            if (agent1Command == null && agent2Command == null) {
                return;
            }

            if (agent1Command != null) {
                System.out.println("Round " + round + " - Agent 1: " + agent1Command.getCommandString());
            }

            if (agent2Command != null) {
                System.out.println("Round " + round + " - Agent 2: " + agent2Command.getCommandString());
            }

            int winner = reactiveAgent.getWinner(agent1Command, agent2Command);

            if (winner == -1) {
                executeCommand(agent1, agent1Command);
                executeCommand(agent2, agent2Command);
            } else if (winner == 1) {
                executeCommand(agent1, agent1Command);
            } else if (winner == 2) {
                executeCommand(agent2, agent2Command);
            }

            update();
            round++;
        }
    }

    @Override
    public void run() {
        update();
        startAgents();
    }
}
