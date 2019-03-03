### Lucen Index Example

### Build
```$xslt
mvn package
java -jar target/*.jar
```

#### Run 
```
mvn exec:java -D exec.mainClass=com.nitesh.newsearch.NewSearchApp
mvn exec:java -D exec.mainClass=com.nitesh.newsearch.SearchIndexedDocument
```