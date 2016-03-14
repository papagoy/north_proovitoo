package com.nortal.assignment.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

  // TODO Use this formatter
  public static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
    gen.writeString(value.toString());
  }

}