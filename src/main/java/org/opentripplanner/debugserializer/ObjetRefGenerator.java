package org.opentripplanner.debugserializer;

import org.onebusaway.gtfs.model.IdentityBean;
import org.opentripplanner.model.StopPattern;
import org.opentripplanner.routing.edgetype.Timetable;
import org.opentripplanner.routing.edgetype.TripPattern;
import org.opentripplanner.routing.graph.Edge;
import org.opentripplanner.routing.graph.Vertex;
import org.opentripplanner.routing.trippattern.TripTimes;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public class ObjetRefGenerator {

    public String ref(Object object) {
        if(object instanceof Edge) {
            Edge edge = (Edge) object;
            return "E:From:" + edge.getFromVertex().getName() + ":To:" + edge.getToVertex().getName();
        }
        if(object instanceof Vertex) {
            Vertex vertex = (Vertex) object;
            return "V:" + vertex.getName();
        }
        if(object instanceof TripPattern) {
            TripPattern tripPattern = (TripPattern) object;
            return "TP:" + tripPattern.code;
        }
        if(object instanceof StopPattern) {
            return "SP:" + object.hashCode();
        }
        if(object instanceof IdentityBean) {
            return "ID:" + ((IdentityBean)object).getId();
        }
        if(object instanceof TripTimes) {
            return "TT:" + ((TripTimes)object).trip.getId();
        }
        if(object instanceof Timetable) {
            // Timetable is allways printed, no references should exist
            return "Timetable";
        }
        return "Hash:" + object.hashCode();
    }

}
