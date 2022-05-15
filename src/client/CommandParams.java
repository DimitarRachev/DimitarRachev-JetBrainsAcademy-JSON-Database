package client;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CommandParams {
    @Parameter(names = "-t")
    private String command;

    public String getCommand() {
        return command;
    }

    @Parameter(names = "-k")
    private  String key;

    public String getKey() {
        return key;
    }

    @Parameter(names = "-v")
    private  List<String> data = new ArrayList<>();

    public List<String> getData() {
        return data;
    }
}
