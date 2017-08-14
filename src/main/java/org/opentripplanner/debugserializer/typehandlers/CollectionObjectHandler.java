package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.opentripplanner.debugserializer.IdentityHashCodeComparator.identityComparator;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class CollectionObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return graph instanceof Collection;
    }

    @Override public void print(Object graph, Class type, Serializer out) {

        List<Object> list = new ArrayList<Object>((Collection) graph);
        list.sort(identityComparator);

        out.print('{');

        if (!list.isEmpty()) {
            out.ln();
        }
        out.indentInc();

        for (Object obj : list) {
            out.debug(obj);
            out.ln();
        }

        out.indentDec();
        out.print('}');
    }

    @Override public String toString() {
        return "CollectionTypeHandler";
    }
}
