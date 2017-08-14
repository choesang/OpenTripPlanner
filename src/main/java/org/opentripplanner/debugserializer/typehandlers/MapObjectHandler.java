package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.opentripplanner.debugserializer.IdentityHashCodeComparator.identityComparator;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (12.08.2017)
 */
public class MapObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return graph instanceof Map;
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        Map map = (Map) graph;

        if (map.isEmpty()) {
            out.print("{:}");
            return;
        }

        List<Object> keys = new ArrayList<Object>(map.keySet());
        keys.sort(identityComparator);

        out.print('{');
        out.ln();
        out.indentInc();

        for (Object key : keys) {
            out.debug(key);
            out.print(" : ");
            out.debug(map.get(key));
            out.ln();
        }

        out.indentDec();
        out.print('}');
    }

    @Override public String toString() {
        return "MapTypeHandler";
    }
}
