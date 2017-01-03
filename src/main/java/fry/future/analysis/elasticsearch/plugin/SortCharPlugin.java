package fry.future.analysis.elasticsearch.plugin;

import org.elasticsearch.plugins.AbstractPlugin;

public class SortCharPlugin extends AbstractPlugin {

    @Override
    public String name() {
        return "SortCharPlugin";
    }

    @Override
    public String description() {
        return "Plugin that provides a Token Filter that sort all character of the tokens in a token stream .";
    }
}
