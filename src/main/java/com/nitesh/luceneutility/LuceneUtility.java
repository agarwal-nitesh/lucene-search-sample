package com.nitesh.luceneutility;

import org.apache.lucene.document.*;

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
                    LuceneDocumentField annotation = field.getAnnotation(LuceneDocumentField.class);
                    if (annotation.value().isEmpty()) {
                        throw new Exception("Empty Field: " + field.getName());
                    } else {
                        if (field != null) {
                            if (annotation.type().equals(LuceneDocumentFieldType.StringField)) {
                                Object fieldValue = field.get(object);
                                if (fieldValue != null) {
                                    TextField textField = new TextField(annotation.value(), fieldValue.toString(), Field.Store.YES);
                                    document.add(textField);
                                }
                            } else if (annotation.type().equals(LuceneDocumentFieldType.IntegerField)) {
                                Object fieldValueObj = field.get(object);
                                if (fieldValueObj != null) {
                                    String fieldValueStr = fieldValueObj.toString();
                                    fieldValueStr = fieldValueStr.replaceAll(",", "");
                                    Integer fieldValue = null;
                                    try {
                                        fieldValue = Integer.parseInt(fieldValueStr);
                                        NumericDocValuesField numericDocValuesField = new NumericDocValuesField(annotation.value(), fieldValue);
                                        document.add(numericDocValuesField);
                                    } catch (Exception e) {
                                        System.out.println("Unable to parse string to int: " + e.getMessage());
                                    }
                                }
                            } else if (annotation.type().equals(LuceneDocumentFieldType.DoubleField)) {
                                Double fieldValue = (Double) field.get(object);
                                if (fieldValue != null) {
                                    DoubleDocValuesField doubleDocValuesField = new DoubleDocValuesField(annotation.value(), fieldValue);
                                    document.add(doubleDocValuesField);
                                }
                            } else if (annotation.type().equals(LuceneDocumentFieldType.LatLonField)) {
                                Double[] fieldValues = (Double[]) field.get(object);
                                if (fieldValues != null && fieldValues.length == 2) {
                                    LatLonDocValuesField latLonDocValuesField = new LatLonDocValuesField(annotation.value(), fieldValues[0], fieldValues[1]);
                                    document.add(latLonDocValuesField);
                                }
                            } else {
                                throw new Exception("Expected field type String, int or double. Unrecognized type : " + field.getType().toString());
                            }

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
