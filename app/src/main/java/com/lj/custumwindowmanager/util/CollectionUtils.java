package com.lj.custumwindowmanager.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dajunhu on 12/10/2014.
 */
public class CollectionUtils {


    public final static int size(Collection c) {
        return c == null ? 0 : c.size();
    }

    public final static boolean isEmpty(Collection c) {
        return size(c) == 0;
    }

    public final static <M> boolean isEmpty(M[] c) {
        return c == null || c.length == 0;
    }

    public final static boolean isNotEmpty(Collection c) {
        return size(c) > 0;
    }

    public final static boolean isLargerThan(Collection c, int mount) {
        return size(c) > mount;
    }

    public final static boolean isNotLargerThan(Collection c, int mount) {
        return size(c) <= mount;
    }

    public final static boolean isNotLessThan(Collection c, int mount) {
        return size(c) >= mount;
    }

    public final static boolean isLessThan(Collection c, int mount) {
        return size(c) < mount;
    }

    public final static boolean isBetween(Collection c, int min, int max) {
        int s = size(c);
        return s > min && s < max;
    }

    public final static <T> List<T> resetList(List<T> list, List<T> newList) {
        if (list == null) {
            list = new ArrayList<T>();
        } else {
            list.clear();
        }

        if (isNotEmpty(newList)) {
            list.addAll(newList);
        }
        return list;
    }

    public final static <T> ArrayList<T> resetList(ArrayList<T> list, List<T> newList) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        list.clear();
        if (isNotEmpty(newList)) {
            list.addAll(newList);
        }
        return list;
    }

    public final static <T> T getLast(ArrayList<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public final static <T> void add(ArrayList<T> origin, ArrayList<T> list) {
        if (origin == null) {
            throw new IllegalArgumentException("");
        }

        if (list == null || list.size() == 0) {
            return;
        }

        origin.addAll(list);
    }

    public static <T> T getFirst(ArrayList<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public static void clear(Collection c) {
        if (c != null) {
            c.clear();
        }
    }

    public static List getSubList(List list, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return null;
        }

        int size = size(list);

        if (fromIndex < 0 || toIndex > size) {
            return null;
        }


        return list.subList(fromIndex, toIndex);
    }

    public static List getSubListByCount(List list, int fromIndex, int length) {
        return getSubList(list, fromIndex, fromIndex + length);
    }

    public static String getString(List<String> list, String sep) {
        if (isEmpty(list)) {
            return "";
        }

        String result = "";
        for (String word : list) {
            result = (result + sep + word);
        }
        if (result != null && result.length() > 0) {
            return result.substring(1);
        } else {
            return "";
        }
    }

    public static String getStringInOrder(String sep, String... arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        String result = "";
        String word;
        for (int i = 0; i < arr.length; i++) {
            word = arr[i];
            if (word == null) {
                word = "";
            }
            result = (result + sep + word);
        }

        if (result != null && result.length() > 0) {
            return result.substring(1);
        } else {
            return "";
        }
    }


    public static String getString(String sep, Set<String> set) {
        if (set == null || set.size() == 0) {
            return "";
        }

        String result = "";

        for (String aSet : set) {
            result += (aSet + sep);
        }

        if (result.length() > 0) {
            return result.substring(0, result.length() - sep.length());
        }
        return "";
    }


    public static <T> Set<T> convertArrayToSet(ArrayList<T> t) {
        if (t == null) {
            return null;
        }
        Set<T> result = new HashSet<T>();
        for (T a : t) {
            result.add(a);
        }
        return result;
    }


    public static <T> ArrayList<T> convertSetArray(Set<T> set) {
        if (set == null) {
            return null;
        }
        ArrayList<T> result = new ArrayList<T>();
        for (T a : set) {
            result.add(a);
        }
        return result;
    }


    public static ArrayList<String> convertToArray(HashMap<String, String> map) {
        if (map == null) {
            return new ArrayList<String>();
        }

        String[] arr = new String[map.size()];
        int i;
        for (String index : map.keySet()) {
            try {
                i = Integer.parseInt(index);
                arr[i] = map.get(index);
            } catch (Exception ex) {
            }
        }
        ArrayList<String> result = new ArrayList<String>();
        for (i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                result.add(arr[i]);
            }
        }
        return result;
    }

    public static <TMP> ArrayList<TMP> convertToArray(TMP[] arr) {
        if (isEmpty(arr)) {
            return new ArrayList<>();
        }

        ArrayList<TMP> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

        }
        return result;
    }


    public static <T> ArrayList<T> convertToArrayList(List<T> l) {
        ArrayList<T> result = new ArrayList<T>();
        if (l != null) {
            for (T t : l) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> T get(List<List<T>> data, int i, int j) {
        if (data != null && i < data.size()) {
            List<T> temp = data.get(i);
            if (temp != null && temp.size() < j) {
                return temp.get(j);
            }
        }
        return null;
    }


    public static <T> T get(List<T> data, int index) {
        if (data != null && index < data.size()) {
            return data.get(index);
        }
        return null;
    }


}
