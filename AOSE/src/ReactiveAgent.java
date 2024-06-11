import java.util.Random;

public class ReactiveAgent {
    public int getWinner(Command agent1Command, Command agent2Command) {
        if (agent1Command == null || agent2Command == null) {
            return -1;
        }

        if (agent1Command.method.equals(agent2Command.method) &&
                agent1Command.blockToSearch.equals(agent2Command.blockToSearch) &&
                agent1Command.blockToStack.equals(agent2Command.blockToStack)) {
            Random rand = new Random();
            int winner = rand.nextInt(2) + 1;
            System.out.println("Winner is " + winner);
            return winner;
        }

        return -1;
    }
}
