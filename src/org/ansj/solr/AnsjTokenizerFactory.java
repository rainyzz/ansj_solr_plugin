package org.ansj.solr;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

import org.ansj.lucene.util.AnsjTokenizer;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeSource.AttributeFactory;


public class AnsjTokenizerFactory extends TokenizerFactory{
	boolean pstemming;
	boolean isQuery;
	public Set<String> filter;	
	
	public AnsjTokenizerFactory(Map<String, String> args) {
		super(args);
		assureMatchVersion();
		isQuery = getBoolean(args, "isQuery", true);
	}

	@Override
	public Tokenizer create(AttributeFactory factory, Reader input) {
		if(isQuery == true){
			//Query
			return new AnsjTokenizer(new ToAnalysis(new BufferedReader(input)), input, filter, pstemming);
		} else {
			//Index
			return new AnsjTokenizer(new IndexAnalysis(new BufferedReader(input)), input, filter, pstemming);
		}
	}	
	
}
