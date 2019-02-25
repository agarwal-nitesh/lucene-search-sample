package com.nitesh.newsearch;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class LuceneUtility {

    public static Document getDocument(Object object) {
        try {
            if (object == null) {
                throw new NullPointerException("Object is null in getDocument(Object object)");
            }
            Class<?> objectClass = object.getClass();
            Document document = new Document();
            for (java.lang.reflect.Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(LuceneDocumentField.class)) {
                    String annotationValue = field.getAnnotation(LuceneDocumentField.class).value();
                    if (annotationValue.isEmpty()) {
                        throw new Exception("Empty Field: " + field.getName());
                    } else {
                        if (field.getType().equals(String.class) || field.getType().equals(int.class) || field.getType().equals(double.class)) {
                            if (field != null) {
                                Object fieldValue = field.get(object);
                                if (fieldValue == null) {
                                    fieldValue = "";
                                }
                                TextField textField = new TextField(annotationValue, fieldValue.toString(), Field.Store.YES);
                                document.add(textField);
                            }
                        } else {
                            throw new Exception("Expected field type String, int or double. Unrecognized type : " + field.getType().toString());
                        }
                    }
                }
            }
            return document;
        } catch (Exception e) {
            System.out.println("Some exception occured while converting object to Lucene Document: " + e.getMessage());
            return null;
        }
    }
}
