package com.gov.sys.util;

import com.google.gson.*;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

final class GsonDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

  // TODO: migrate to streaming adapter
    
    private static final String[] DATE_FORMATS = {"dd/MM/yyyy","dd/MM/yyyy HH:mm:ss:SSS","dd/MM/yyyy HH","dd/MM/yyyy HH:mm","dd/MM/yyyy HH:mm:ss","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss:SSS"};

  private final DateFormat enUsFormat;
  private final DateFormat localFormat;
  private final DateFormat iso8601Format;

  GsonDateTypeAdapter() {
    this(DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US),
        DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT));
  }

  GsonDateTypeAdapter(String datePattern) {
    this(new SimpleDateFormat(datePattern, Locale.US), new SimpleDateFormat(datePattern));
  }

  GsonDateTypeAdapter(int style) {
    this(DateFormat.getDateInstance(style, Locale.US), DateFormat.getDateInstance(style));
  }

  public GsonDateTypeAdapter(int dateStyle, int timeStyle) {
    this(DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US),
        DateFormat.getDateTimeInstance(dateStyle, timeStyle));
  }

  GsonDateTypeAdapter(DateFormat enUsFormat, DateFormat localFormat) {
    this.enUsFormat = enUsFormat;
    this.localFormat = localFormat;
    this.iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    this.iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  // These methods need to be synchronized since JDK DateFormat classes are not thread-safe
  // See issue 162
  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
    synchronized (localFormat) {
      String dateFormatAsString = enUsFormat.format(src);
      return new JsonPrimitive(dateFormatAsString);
    }
  }

  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!(json instanceof JsonPrimitive)) {
      throw new JsonParseException("The date should be a string value");
    }
    Date date = deserializeToDate(json);
    if (typeOfT == Date.class) {
      return date;
    } else if (typeOfT == Timestamp.class) {
      return new Timestamp(date.getTime());
    } else if (typeOfT == java.sql.Date.class) {
      return new java.sql.Date(date.getTime());
    } else {
      throw new IllegalArgumentException(getClass() + " cannot deserialize to " + typeOfT);
    }
  }

  private Date deserializeToDate(JsonElement json) {
    synchronized (localFormat) {
      try {
          
          
//        return localFormat.parse(json.getAsString());
          return DateUtils.parseDate(json.getAsString(), DATE_FORMATS);
      } catch (ParseException ignored) {
      }
      try {
        return enUsFormat.parse(json.getAsString());
      } catch (ParseException ignored) {
      }
      try {
        return iso8601Format.parse(json.getAsString());
      } catch (ParseException e) {
        throw new JsonSyntaxException(json.getAsString(), e);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(GsonDateTypeAdapter.class.getSimpleName());
    sb.append('(').append(localFormat.getClass().getSimpleName()).append(')');
    return sb.toString();
  }
}