package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class JavaLangTypesHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        Package pkg = type.getPackage();
        return pkg != null && "java.lang".equals(pkg.getName());
    }

    @Override public void print(Object value, Class type, Serializer out) {
        out.print(value);
    }
}
