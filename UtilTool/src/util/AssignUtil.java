package util;

import dto.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 两个javabean之间赋值的工具类
 */
public class AssignUtil {
    /**
     * 允许赋值的属性类型
     */
    public static Set<Class> typeSet = new HashSet<Class>(){{
        add(Integer.class);
        add(int.class);
        add(Double.class);
        add(double.class);
        add(Float.class);
        add(float.class);
        add(Long.class);
        add(long.class);
        add(Short.class);
        add(short.class);
        add(Boolean.class);
        add(boolean.class);
        add(Byte.class);
        add(byte.class);
        add(Character.class);
        add(char.class);
        add(String.class);
    }};

    /**
     * 新建一个目的对象
     * 拷贝一个对象的属性值
     * 注：与source相同的属性
     * @param source 数据源
     * @param classR 赋值对象的类
     * @param filterSet 不需要的拷贝的属性名集合
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    public static <T> T copy(Object source,Class<T> classR,Set<String> filterSet) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        T result = classR.newInstance();

        Field[] fields = source.getClass().getDeclaredFields();
        // 循环遍历属性
        for ( Field field : fields ) {
            field.setAccessible( true );
            String fieldName = field.getName();// 属性名
            String firstLetter = fieldName.substring(0, 1).toUpperCase();// 首字母大写
            String setMethodName = "set" + firstLetter + fieldName.substring(1); //该属性的set方法名
            if (filterSet.contains(fieldName)) {
                continue;
            }
            if (!typeSet.contains(field.getType())) { //只赋值基本属性
                continue;
            }
            try {
                Method setMethod = result.getClass().getMethod(setMethodName, field.getType()); //该属性的set方法对象
                setMethod.invoke(result, field.get(source)); // 调用reslut的set方法，值从source中获取
            }catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 对已有的对象进行赋值
     * 注：属性要相同
     * @param source 数据源
     * @param result 赋值对象
     * @param filter 不需赋值的属性集合
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static void assign(Object source,Object result,Set<String> filter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Field[] fields = source.getClass().getDeclaredFields();
        // 循环遍历属性
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();// 属性名
            String firstLetter = fieldName.substring(0, 1).toUpperCase();// 首字母大写
            String setMethodName = "set" + firstLetter + fieldName.substring(1); //该属性的set方法名
            if (filter.contains(fieldName)) {
                continue;
            }
            if (!typeSet.contains(field.getType())) { //只赋值基本属性
                continue;
            }
            try {
                Method setMethod = result.getClass().getMethod(setMethodName, field.getType()); //该属性的set方法对象
                setMethod.invoke(result, field.get(source)); // 调用reslut的set方法，值从source中获取
            } catch (Exception e) {

            }
        }
    }

}
