package org.swustmc.Utils;

import java.util.Collection;

public class CollUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}
