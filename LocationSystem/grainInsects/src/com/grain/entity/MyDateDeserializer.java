package com.grain.entity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public  class MyDateDeserializer extends JsonDeserializer<Date>{  
    @Override  
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) 
    		throws IOException, JsonProcessingException {  
        String value = jp.getValueAsString();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try {  
            return dateFormat.parse(value);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}  