import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Agent {
    int id;
    List<Command> plan;
    List<Stack<String>> result;

    public Agent(int id, List<String> finalConfiguration) {
        this.id = id;
        plan = new ArrayList<Command>();
        result = new ArrayList<Stack<String>>();

        for(int i = finalConfiguration.size() - 1; i >= 0; i--) {
            String command = finalConfiguration.get(i);

            if (command.contains("ONTABLE")) {
                applyOnTable(command);
            } else if(command.contains("ON")) {
                applyOn(command);
            }
        }
        Collections.reverse(result);
    }

    public void applyOnTable(String command) {
        command = command.substring(command.indexOf("(") + 1);
        String block = command.substring(0, command.indexOf(")"));

        Stack<String> stack = new Stack<String>();
        stack.add(block);

        result.add(stack);
    }

    public void applyOn(String command) {
        command = command.substring(command.indexOf("(") + 1);
        command = command.substring(0, command.indexOf(")"));

        String blockToSearch = Character.toString(command.charAt(2));
        String blockToStack =  Character.toString(command.charAt(0));

        System.out.println("Found ON with blocks: " + command + " for agent " + id);

        for (int j = 0; j < result.size(); j++) {
            Stack<String> stack = result.get(j);

            if (stack.peek().equals(blockToSearch)) {
                result.get(j).add(blockToStack);
                break;
            }
        }
    }

    public void pickUp(List<Stack<String>> blocks, String block) {
        for(int i = 0; i < blocks.size(); i++) {
            Stack<String> stack = blocks.get(i);
            if (stack.size() > 0 && stack.peek().equals(block)) {
                stack.pop();
                break;
            }
        }
    }

    public void unStack(List<Stack<String>> blocks, String block) {
        pickUp(blocks, block);

        Stack<String> stack = new Stack<String>();
        stack.add(block);
        blocks.add(stack);
    }

    public void stack(List<Stack<String>> blocks, String blockToSearch, String blockToStack) {
        pickUp(blocks, blockToStack);

        for (int j = 0; j < blocks.size(); j++) {
            Stack<String> stack = blocks.get(j);

            if (stack.size() > 0 && stack.peek().equals(blockToSearch)) {
                blocks.get(j).add(blockToStack);
                break;
            }
        }
    }

    public void createPlan(List<Stack<String>> blocks) {
        for(int i = 0; i < blocks.size(); i++) {
            Stack<String> stack = blocks.get(i);
            for(int j = stack.size() - 1; j>=1; j--) {
                String block = stack.get(j);
                plan.add(new Command("UNSTACK", block));
            }
        }

        for(int i = 0; i < result.size(); i++) {
            Stack<String> stack = result.get(i);
            for(int j=1; j < stack.size(); j++) {
                String block = stack.get(j);
                plan.add(new Command("STACK", stack.get(j - 1), block));
            }
        }
    }

    public Command getNextPlan() {
        if (plan.size() == 0) {
            return null;
        }

        return plan.remove(0);
    }
}
