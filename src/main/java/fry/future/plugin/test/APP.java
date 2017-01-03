/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fry.future.plugin.test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 *
 * @author ranjeet
 */
public class APP {

    public static void main(String[] args) {
        try {
            String text = "requirment of analysis++ #";
            Analyzer analyzer = new StandardAnalyzer();
            List<String> result = tokenString(analyzer, text);
            System.out.println("Custom==>" + result + "\n");
            List<String> result1 = tokenString(new StandardAnalyzer(), text);
            System.out.println("==>Standard" + result1 + "\n");//==>[test, some, text, text]
        } catch (IOException ex) {
            Logger.getLogger(APP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static List<String> tokenString(Analyzer analyzer, String str) throws IOException {
        List<String> result = new ArrayList<>();
        TokenStream tokenStream = analyzer.tokenStream("Test", new StringReader(str));
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(tokenStream.getAttribute(CharTermAttribute.class).toString());
        }
        return result;
    }
}
