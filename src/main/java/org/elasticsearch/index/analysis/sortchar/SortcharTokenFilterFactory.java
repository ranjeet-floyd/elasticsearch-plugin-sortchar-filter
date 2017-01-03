package org.elasticsearch.index.analysis.sortchar;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettings;
import fry.future.analysis.elasticsearch.filter.SortCharFilter;

public class SortcharTokenFilterFactory extends AbstractTokenFilterFactory {

    @Inject
    public SortcharTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new SortCharFilter(Version.LATEST, tokenStream);
    }

}
