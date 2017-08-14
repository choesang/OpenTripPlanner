package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Use #toString() to serialize.
 *
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public class ValueObjectsObjectHandler implements ObjectHandler {

    private Set<Class> voTypes;

    public ValueObjectsObjectHandler(Collection<Class> valueObjectTypes) {
        this.voTypes = new HashSet<>(valueObjectTypes);
    }

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return voTypes.contains(type) || type.isEnum();
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        out.print(graph);
    }
}
