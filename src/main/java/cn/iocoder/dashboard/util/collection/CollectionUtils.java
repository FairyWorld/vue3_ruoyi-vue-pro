package cn.iocoder.dashboard.util.collection;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Collection 工具类
 *
 * @author 芋道源码
 */
public class CollectionUtils {

    public static boolean isAnyEmpty(Collection<?>... collections) {
        return Arrays.stream(collections).anyMatch(CollectionUtil::isEmpty);
    }

    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        return from.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    public static <T, U> Set<U> convertSet(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, T> convertMap(List<T> from, Function<T, K> keyFunc) {
        return from.stream().collect(Collectors.toMap(keyFunc, item -> item));
    }

    public static <T, K, V> Map<K, V> convertMap(List<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        return from.stream().collect(Collectors.toMap(keyFunc, valueFunc));
    }

    public static <T, K> Map<K, List<T>> convertMultiMap(List<T> from, Function<T, K> keyFunc) {
        return from.stream().collect(Collectors.groupingBy(keyFunc,
                Collectors.mapping(t -> t, Collectors.toList())));
    }

    public static <T, K, V> Map<K, List<V>> convertMultiMap(List<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        return from.stream().collect(Collectors.groupingBy(keyFunc,
                Collectors.mapping(valueFunc, Collectors.toList())));
    }

    // 暂时没想好名字，先以 2 结尾噶
    public static <T, K, V> Map<K, Set<V>> convertMultiMap2(List<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(valueFunc, Collectors.toSet())));
    }

    public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
        return org.springframework.util.CollectionUtils.containsAny(source, candidates);
    }

    public static <T> T getFirst(List<T> from) {
        return !CollectionUtil.isEmpty(from) ? from.get(0) : null;
    }

    public static <T> void addIfNotNull(Collection<T> coll, T item) {
        if (item == null) {
            return;
        }
        coll.add(item);
    }

}