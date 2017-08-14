package org.opentripplanner.debugserializer;

import org.onebusaway.gtfs.model.calendar.ServiceDate;
import org.opentripplanner.common.geometry.PackedCoordinateSequence;
import org.opentripplanner.debugserializer.customtypehandlers.AgencyAndIdObjectHandler;
import org.opentripplanner.debugserializer.customtypehandlers.GeometryObjectHandler;
import org.opentripplanner.debugserializer.customtypehandlers.StopObjectHandler;
import org.opentripplanner.debugserializer.typehandlers.*;
import org.opentripplanner.routing.core.TraverseModeSet;
import org.opentripplanner.util.NonLocalizedString;

import java.util.*;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public class ObjectHandlers extends ArrayList<ObjectHandler> {
    public ObjectHandlers() {
        super(Arrays.asList(
                // Basic handlers
                new StringTypeHandler(),
                new JavaLangTypesHandler(),
                new ValueObjectsObjectHandler(valueObjectTypes()),

                // Custom handlers
                new AgencyAndIdObjectHandler(),
                new StopObjectHandler(),
                new GeometryObjectHandler(),

                // Collection handlers
                new ArrayObjectHandler(),
                new CollectionObjectHandler(),
                new MapObjectHandler(),

                // Print ref, not object, if object already visited
                new PruneObjectHandler(),

                // Default handlers
                new POJOObjectHandler(),
                new TypeNotHandledObjectHandler())
        );
    }

    private static Collection<Class> valueObjectTypes() {
        return Arrays.asList(
                Date.class,
                BitSet.class,
                PackedCoordinateSequence.Double.class,
                NonLocalizedString.class,
                ServiceDate.class,
                TraverseModeSet.class

        );
    }
}
