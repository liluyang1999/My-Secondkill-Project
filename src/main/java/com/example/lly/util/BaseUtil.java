package com.example.lly.util;

import jodd.typeconverter.impl.LocalDateTimeConverter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

@Component
public class BaseUtil {

    public static final long SERIAL_VERSION_UID = 1L;

    public static final long MIN_LONG = Long.MIN_VALUE;

    public static final long MAX_LONG = Long.MAX_VALUE;

    public static final double MIN_DOUBLE = Double.MIN_VALUE;

    public static final double MAX_DOUBLE = Double.MAX_VALUE;

    public static Charset defaultCharset = StandardCharsets.UTF_8;

    public static String addQuotationMark(String str) {
        return "'" + str + "'";
    }

    public static String addPrefix(String str, String prefix) {
        return prefix + str;
    }

    public static String getTableName(Object entity) {
        return "t_" + entity.getClass().getSimpleName().toLowerCase();
    }

    public static String getTableName(Class<?> clazz) {
        return "t_" + clazz.getSimpleName().toLowerCase();
    }

    public static Boolean notNullAndBlank(String str) {
        return (str != null ) && (!"".equals(str));
    }

    //复制一份
    public static <T> T copyFrom(T src) throws RuntimeException {
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T copy = null;
        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();  //输出加缓冲进入memoryBuffer里
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            copy = (T) in.readObject();
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(out != null) {
                try{
                    out.close();
                    out = null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(in != null) {
                    try {
                        in.close();
                        in = null;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return copy;
    }

    public static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static Timestamp convertToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }



    public static void main(String[] args) {
        Integer joker = 3;
        Integer joker2 = null;
        joker2 = BaseUtil.copyFrom(joker);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        System.out.println(date.getTime());
        System.out.println(Calendar.getInstance().getCalendarType());
        System.out.println(Calendar.getInstance().getFirstDayOfWeek());
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Calendar.getInstance().getTimeZone());
    }

}
