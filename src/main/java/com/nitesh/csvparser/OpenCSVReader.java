package com.nitesh.csvparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Constructor;

import com.opencsv.CSVReader;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * OpenCSVReader uses the csv parser from OpenCSV project.
 *
 * Note: OpenCSV automaticly takes care of comment lines starting with "#", but does not expose setting the character for comments.
 *
 * @author Nitesh Agarwal <niteshagarwal1.618@gmail.com>
 *
 * @link http://opencsv.sourceforge.net/apidocs/
 */

@Data
public class OpenCSVReader {
    private static final Logger LOG = LoggerFactory.getLogger(OpenCSVReader.class);

    private String[] nextLine;
    private char separator = ',';
    private char quote = '"';
    private CSVReader csvReader;
    private int currentLineNumber = 0;
    private String[] header;
    private String fileName;
    /**
     * @param separator		field separator character.  usually ',' in North America, ';' in Europe and sometimes '\t' for tab.
     */
    public OpenCSVReader(final char separator){
        this(separator, '"');
    }

    /**
     * @param separator		field separator character.  usually ',' in North America, ';' in Europe and sometimes '\t' for tab.
     * @param quote			character use to enclose fields containing a separator. usually '"'
     */
    public OpenCSVReader(final char separator, final char quote){
        this.separator = separator;
        this.quote = quote;
    }

    public boolean hasNext(){
        return (this.nextLine != null ? true : false );
    }

    public String[] next(){
        String[] currentLine = this.nextLine;
        currentLineNumber++;

        this.nextLine = null;
        try {
            this.nextLine = csvReader.readNext();
        } catch (IOException e) {
            LOG.error("Failed reading line, at {}:{}", getFileName(), getCurrentLineNumber(), e);
        }

        return currentLine;
    }

    public void remove() {
        throw new UnsupportedOperationException("CsvIterator does not support remove operation");
    }

    public void open(String filename) throws IOException {
        this.fileName = filename;
        InputStream inputStream = ClassLoaderUtil.getResourceAsStream(filename, OpenCSVReader.class);
        this.csvReader = new CSVReader(new BufferedReader(new InputStreamReader(inputStream), 32768), this.separator, this.quote);
        this.header = csvReader.readNext();
        this.nextLine = csvReader.readNext();
    }

    public void opencsv(String textBlob) {
        this.csvReader = new CSVReader(new StringReader(textBlob));
        try {
            this.header = csvReader.readNext();
            this.nextLine = csvReader.readNext();
        } catch (IOException e) {
            LOG.error("Failed reading line, at {}:{}", getFileName(), getCurrentLineNumber(), e);
        }
    }

    public void close() throws IOException {
        if (csvReader != null){
            csvReader.close();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String filename = "booking_com-travel_sample.csv";
        OpenCSVReader reader = new OpenCSVReader(',');
        reader.open(filename);
        System.out.println("Header ------------------------------------");
        String[] columnHeader = reader.getHeader();
        System.out.println("Header  -----------------------------------");
        for(int c=1; c < 10; c++) {
            String[] columns = reader.next();
            int columnNumber = 0;
            for (String column: columns) {
                System.out.print(columnHeader[columnNumber]);
                System.out.print(" = ");
                System.out.print(column);
                System.out.print(";    ");
                columnNumber+=1;
            }
            System.out.println("");
            System.out.println("---------------------------------------");
        }
        reader.close();
    }

}
