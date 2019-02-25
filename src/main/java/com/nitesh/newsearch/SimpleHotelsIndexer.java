package com.nitesh.newsearch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.csvparser.OpenCSVReader;
import com.nitesh.model.HotelModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;

public class SimpleHotelsIndexer {
    private ObjectMapper mapper;
    @Setter @Getter
    private String indexDirectoryPath;
    @Getter
    private IndexWriter indexWriter;

    public SimpleHotelsIndexer() throws Exception {
        this.setMapper();
    }

    public void indexCSVFileInResources(String fileName) throws Exception {
        if (this.mapper == null || this.indexDirectoryPath == null) {
            throw new NullPointerException("mapper or indexDirectoryPath is null");
        }
        this.setIndexWriter();
        OpenCSVReader reader = new OpenCSVReader(',');
        reader.open(fileName);
        String[] columnHeader = reader.getHeader();
        for (int c=1; c < 10; c++) {
            String[] columns = reader.next();
            int columnNumber = 0;
            HashMap<String, Object> map = new HashMap<String, Object>();
            for (String column : columns) {
                map.put(columnHeader[columnNumber], column);
                columnNumber += 1;
            }
            System.out.println("Hotel Map -------------------");
            System.out.println(Collections.singletonList(map));
            System.out.println("-----------------------------");
            HotelModel hotel = this.mapper.convertValue(map, HotelModel.class);
            Document document = LuceneUtility.getDocument(hotel);
            this.indexDocument(document);
        }
        this.closeIndexWriter();
    }

    private void setMapper() {
        ObjectMapper mapper = new ObjectMapper();
        this.mapper = mapper;
    }

    private IndexWriter setIndexWriter() throws Exception {
        Analyzer analyzer = getAnalyzer();
        Directory indexDirectory = FSDirectory.open(Paths.get(this.getIndexDirectoryPath()));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexDirectory, indexWriterConfig);
        this.indexWriter = writer;
        return writer;
    }

    private Analyzer getAnalyzer() {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        return analyzer;
    }

    private void indexDocument(Document document) throws Exception {
        if (document == null) {
            throw new NullPointerException("Index Document Null exception");
        }
        IndexWriter indexWriter = this.getIndexWriter();
        indexWriter.addDocument(document);
    }

    private void closeIndexWriter() throws Exception {
        this.indexWriter.close();
    }
}
