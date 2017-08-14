package org.opentripplanner.debugserializer.customtypehandlers;

import org.onebusaway.gtfs.model.Stop;
import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public class StopObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return Stop.class.equals(type);
    }

    @Override public void print(Object value, Class type, Serializer out) {
        Stop stop = (Stop) value;
        out.print("Stop:");
        out.print(stop.getName());
    }
}
