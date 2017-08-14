package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class TypeNotHandledObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return true;
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        System.err.println("ERROR | Class not handled: " + type);
    }
    @Override public String toString() {
        return "TypeNotHandledTypeHandler";
    }

}
