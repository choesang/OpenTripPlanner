package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;
import org.opentripplanner.routing.edgetype.Timetable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public class PruneObjectHandler implements ObjectHandler {

    private Set<Object> objectRefs = new HashSet<>();
    private Set<Class> excludeType = new HashSet<>();

    public PruneObjectHandler() {
        this.objectRefs.add(Timetable.class);
    }

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        if (objectVisited(graph)) {
            return true;
        }
        if(!excludeType.contains(type)) {
            insertObject(graph);
        }
        return false;
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        out.print(type.getSimpleName());
        out.print(" -> ");
        out.printRef(graph);
    }

    @Override public String toString() {
        return "ObjectVisitedTypeHandler";
    }

    private void insertObject(Object object) {
        objectRefs.add(object);
    }

    private boolean objectVisited(Object object) {
        return objectRefs.contains(object);
    }
}
