package org.swustmc.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollUtil {
    /**
     * 集合转,分隔的字符串
     *
     * @param list 集合
     * @param <T>  字符串
     * @return 分隔的字符串
     */
    public static <T> String listToStr(List<T> list) {
        if (isEmpty(list)) {
            return "";
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return true 是
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合是否不为空
     *
     * @param collection 集合
     * @return true 是
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断list是否相等
     *
     * @param list  list
     * @param list1 list1
     * @return true/等于
     */
    public static boolean equals(List<String> list, List<String> list1) {
        if (list == list1) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list1);
    }

    /**
     * 对list进行分组
     *
     * @param list    list
     * @param toIndex 分组数
     * @param <T>     t
     * @return List
     * @since 2.3.1
     */
    public static <T> List<List<T>> partition(List<T> list, int toIndex) {
        List<List<T>> listGroup = new ArrayList<>();
        int listSize = list.size();
        for (int i = 0; i < list.size(); i += toIndex) {
            if (i + toIndex > listSize) {
                toIndex = listSize - i;
            }
            List<T> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

    /**
     * 获取List中第一个包含的值
     *
     * @param list 集合
     * @param str  包含的值
     * @return List中的值
     * @since 3.1.2
     */
    public static String getContainsStr(List<String> list, String str) {
        if (CollUtil.isEmpty(list) || StrUtil.isEmpty(str)) {
            return null;
        }
        for (String string : list) {
            if (string.contains(str)) {
                return string;
            }
        }
        return null;
    }

    /**
     * 获取List中对应的的值
     *
     * @param list 集合
     * @param str  包含的值
     * @return List中对应的的值
     * @since 3.1.2
     */
    public static String getStr(List<String> list, String str) {
        if (CollUtil.isEmpty(list) || StrUtil.isEmpty(str)) {
            return null;
        }
        int i = list.indexOf(str);
        return i != -1 ? list.get(i) : null;
    }
}
