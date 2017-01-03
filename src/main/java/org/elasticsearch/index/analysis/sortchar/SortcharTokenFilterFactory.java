package org.elasticsearch.index.analysis.sortchar;

import fry.future.analysis.elasticsearch.filter.SortCharFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettings;

/**
 * package name 'sortchar' become type of filter.
 * Elastic search find class @ package org.elasticsearch.index.analysis.{type}
 * <p>
 * @author ranjeet
 */
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
