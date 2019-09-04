package com.test.annotation;

import com.test.entity.Book;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationFactory {
    public static Book createBook(){
        Book book = new Book();

        Class<? extends Book> bookClass = book.getClass();

        Field[] fields = bookClass.getDeclaredFields();

        for (Field field : fields) {
            //判断这个属性中是否有Column这个注解
            if (!field.isAnnotationPresent(Init.class)) {
                continue;
            }

            Init init = field.getAnnotation(Init.class);
            try {
                field.setAccessible(true);
                field.set(book, init.value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return book;
    }
}
