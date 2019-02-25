package com.nitesh.newsearch;

public class NewSearchApp {
    public static void main(String args[]) {
        System.out.println("New Search");

        try {
            SimpleHotelsIndexer simpleHotelsIndexer = new SimpleHotelsIndexer();
            simpleHotelsIndexer.setIndexDirectoryPath("lucene-index");
            simpleHotelsIndexer.indexCSVFileInResources("booking_com-travel_sample.csv");
        } catch (Exception e) {
            System.out.println("Exception occured while indexing csv file: " + e.getMessage());
        }
    }
}
