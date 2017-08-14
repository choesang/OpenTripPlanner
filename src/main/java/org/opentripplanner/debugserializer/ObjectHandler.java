package org.opentripplanner.debugserializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public interface ObjectHandler {
    boolean accept(Object graph, Class type, Serializer out);

    void print(Object graph, Class type, Serializer out);
}
