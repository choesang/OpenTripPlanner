package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

import java.lang.reflect.Array;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class ArrayObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return type.isArray();
    }

    @Override public void print(Object graph, Class type, Serializer out) {
        int idxLast = Array.getLength(graph) - 1;
        if (idxLast == -1) {
            out.print("[]");
            return;
        }

        out.print('[');
        out.indentInc();
        out.ln();

        boolean primitive = type.getComponentType().isPrimitive();


        for (int i = 0; i < idxLast; ++i) {
            out.debug(Array.get(graph, i));
            out.print(",");

            // Wrap for each object and every 20th primitive
            if(!primitive || (i+1) % 20 == 0) {
                out.ln();
            }
        }
        // Print last element
        out.debug(Array.get(graph, idxLast));
        out.ln();

        out.indentDec();
        out.print(']');
    }

    @Override public String toString() {
        return "ArrayTypeHandler";
    }
}
