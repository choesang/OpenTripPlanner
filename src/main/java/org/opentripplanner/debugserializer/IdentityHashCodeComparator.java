package org.opentripplanner.debugserializer;

import java.util.Comparator;


/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (12.08.2017)
 */
public class IdentityHashCodeComparator implements Comparator<Object> {

    public static final IdentityHashCodeComparator identityComparator = new IdentityHashCodeComparator();
    private ObjetRefGenerator refGenerator = new ObjetRefGenerator();

    @Override public int compare(Object o1, Object o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return sortKey(o1).compareTo(sortKey(o2));
    }

    private String sortKey(Object obj) {
        return obj.getClass().getSimpleName() + ":" + refGenerator.ref(obj);
    }
}
