package org.opentripplanner.debugserializer.customtypehandlers;

import com.vividsolutions.jts.geom.Geometry;
import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (14.08.2017)
 */
public class GeometryObjectHandler implements ObjectHandler {
    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return Geometry.class.isAssignableFrom(type);
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        out.print(graph.toString());
    }
}