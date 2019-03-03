package com.nitesh.newsearch;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

public class SearchIndexedDocument {

    String indexDirectoryPath;
    IndexSearcher indexSearcher;

    public SearchIndexedDocument(String indexDirectoryPath) throws Exception {
        this.indexDirectoryPath = indexDirectoryPath;
        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
        IndexReader reader = DirectoryReader.open(indexDirectory);
        this.indexSearcher = new IndexSearcher(reader);
    }

    public TopDocs search(String searchField, String searchQuery) throws Exception {
        QueryParser queryParser = new QueryParser(searchField, new StandardAnalyzer());
        Query query = queryParser.parse(searchQuery);
        TopDocs docs = this.indexSearcher.search(query, 100);
        System.out.println("Total hits" + docs.totalHits);
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = this.indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc);
        }
        return docs;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter field");
                String field = scanner.nextLine();
                System.out.println("Enter query string");
                String query = scanner.nextLine();
                SearchIndexedDocument searchIndexedDocument = new SearchIndexedDocument("lucene-index");
                searchIndexedDocument.search(field, query);

            } catch (Exception e) {
                System.out.println("Some exception occurred while searching index: " + e.getMessage());
            }
        }
    }



    /*public TopDocs search(String query, int pageNumber) throws IOException, ParseException {
        Query searchQuery = parser.parse(query);
        TopScoreDocCollector collector = TopScoreDocCollector.create(1000, true);

        int startIndex = (pageNumber - 1) * MyApp.SEARCH_RESULT_PAGE_SIZE;
        searcher.search(searchQuery, collector);

        TopDocs topDocs = collector.topDocs(startIndex, MyApp.SEARCH_RESULT_PAGE_SIZE);
        return topDocs;
    }*/
}
