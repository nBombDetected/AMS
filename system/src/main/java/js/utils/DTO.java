package js.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


public interface DTO<T, D> {
    default List<T> parseList(List<D> dList, Class<T> tClazz, Class<D> dClazz) {
        List<T> tList = new ArrayList<>();
        try {
            Constructor<T> tConstructor = tClazz.getDeclaredConstructor(dClazz);
            for(D d: dList) {
                tList.add(tConstructor.newInstance(d));
            }
        } catch (Exception ignore) {}

        return tList;
    }
}
