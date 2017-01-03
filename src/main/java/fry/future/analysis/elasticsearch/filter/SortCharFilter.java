package fry.future.analysis.elasticsearch.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public final class SortCharFilter extends TokenFilter {

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public SortCharFilter(Version matchVersion, TokenStream input) {
        super(input);
    }

    /**
     * Get all token in stringBuilder and sort the chars using Arrays.sort.
     * <p>
     * @return boolean : token should be considered or not.
     * @throws IOException
     */
    @Override
    public boolean incrementToken() throws IOException {
        boolean empty = false;
        StringBuilder builder = new StringBuilder();
        while (input.incrementToken()) {
            //append the term of the current token.
            builder.append(termAtt.buffer(), 0, termAtt.length());
            //System.out.println("builder : " + builder.toString());
        }
        char[] stringBuilderChars = builder.toString().toLowerCase(Locale.US).toCharArray();
        /*
         * use natural sort
         */
        Arrays.sort(stringBuilderChars);
        String sortedCharString = new String(stringBuilderChars);
        //System.out.println("sortedCharString : " + sortedCharString);
        if (sortedCharString.length() > 0) {
            termAtt.setEmpty().append(sortedCharString);
            empty = true;
        }
        return empty;
    }

}
