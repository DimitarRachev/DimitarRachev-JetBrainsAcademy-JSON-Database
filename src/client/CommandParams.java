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

    @Parameter(names = "-i")
    private int index = 1;

    public Integer getIndex() {
        return index;
    }

    @Parameter(names = "-m")
    private List<String> data = new ArrayList<>();

    public List<String> getData() {
        return data;
    }
}
