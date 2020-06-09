package com.grain.entity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * 
 * @author szy
 * json 日期格式转化，如：
 *  @JsonSerialize(using = MyDateSerializer.class)  
    @JsonDeserialize(using = MyDateDeserializer.class)  
    private Date birthday; 
 * 或者
 *   @JsonSerialize(as = Sub1.class)  
    @JsonDeserialize(as = Sub1.class) 
    private Date birthday;  
 */
public  class MyDateSerializer extends JsonSerializer<Date>{  
    @Override  
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) 
    		throws IOException, JsonProcessingException {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateStr = dateFormat.format(value);  
        jgen.writeString(dateStr);  
    }  
}