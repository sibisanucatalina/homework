public class Command {
    String method; // stack or unstack
    String blockToStack;
    String blockToSearch;

    Command(String method, String blockToStack){
        this.method = method;
        this.blockToStack = blockToStack;
        this.blockToSearch = "";
    }

    Command(String method, String blockToSearch, String blockToStack){
        this.method = method;
        this.blockToStack = blockToStack;
        this.blockToSearch = blockToSearch;
    }

    public String getCommandString() {
        if(!blockToSearch.equals("")) {
            return this.method + "(" + blockToStack + "," + blockToSearch + ")";
        }
        return this.method + "(" + blockToStack + ")";
    }
}
