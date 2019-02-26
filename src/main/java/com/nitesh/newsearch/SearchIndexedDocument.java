package com.nitesh.newsearch;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchIndexedDocument {

    String indexDirectoryPath;
    IndexSearcher indexSearcher;
    QueryParser queryParser;
    Query query;

    public SearchIndexedDocument(String indexDirectoryPath) throws Exception {
        this.indexDirectoryPath = indexDirectoryPath;
        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
        IndexReader reader = DirectoryReader.open(indexDirectory);
        this.indexSearcher = new IndexSearcher(reader);
        this.queryParser = new QueryParser("address", new StandardAnalyzer());
    }

    public TopDocs search(String searchQuery) throws Exception {
        this.query = this.queryParser.parse(searchQuery);
        return this.indexSearcher.search(this.query, 10);
    }

    public static void main(String[] args) {
        try {
            SearchIndexedDocument searchIndexedDocument = new SearchIndexedDocument("lucene-index");
            TopDocs docs = searchIndexedDocument.search("Khirsu");
            System.out.println(docs.totalHits);

        } catch (Exception e) {
            System.out.println("Some exception occurred while searching index: " + e.getMessage());
        }
    }
}
