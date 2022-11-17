package Lab_07.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class clazz = Example.class;

//        Method[] methods = clazz.getDeclaredMethods();
//        Arrays.stream(methods)
//                .filter(method -> method.getName().startsWith("get") &&  method.getParameterCount() == 0)
//                .sorted(Comparator.comparing(Method::getName))
//                .forEach(method -> System.out.printf("%s will return %s%n", method.getName(), method.getReturnType()));
//        Arrays.stream(methods)
//                .filter(method -> method.getName().startsWith("set") &&  method.getParameterCount() == 1)
//                .sorted(Comparator.comparing(Method::getName))
//                .forEach(method -> System.out.printf("%s will return %s%n", method.getName(),
//                        method.getParameterTypes()[0].getName()));
//
//        Field[] fields = clazz.getDeclaredFields();
//        Arrays.stream(fields)
//                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
//                .forEach(field -> System.out.printf("%s%n", field.getName()));
//
//        Class a = Example.class;
//        Annotation[] annotations = a.getAnnotations();
//        System.out.println(annotations);

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            Author annotation = method.getAnnotation(Author.class);
            if (annotation != null){
                System.out.println(method.getName()+ " " + annotation.name());
            }
        }

    }

}
