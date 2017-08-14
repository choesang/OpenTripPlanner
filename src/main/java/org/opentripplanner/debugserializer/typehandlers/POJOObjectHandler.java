package org.opentripplanner.debugserializer.typehandlers;

import org.opentripplanner.debugserializer.ObjectHandler;
import org.opentripplanner.debugserializer.Serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isTransient;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class POJOObjectHandler implements ObjectHandler {

    @Override public boolean accept(Object graph, Class type, Serializer out) {
        return true;
    }

    @Override public void print(Object graph, Class type, Serializer out) {

        out.print(type.getSimpleName());
        out.printRef(graph);
        out.print(" {");
        out.indentInc();
        out.ln();

        for (Field field : fields(type)) {
            printField(graph, out, field);
        }
        out.indentDec();
        out.print("}");
    }

    private Iterable<? extends Field> fields(Class type) {
        List<Field> fields = new ArrayList<>();
        aggregateFields(type, fields);
        return fields;
    }

    private void aggregateFields(Class type, List<Field> fields) {
        if(type == null) {
            return;
        }
        aggregateFields(type.getSuperclass(), fields);

        for (Field field : type.getDeclaredFields()) {
            if(skipBasedOnModifiers(field.getModifiers())) {
               continue;
            }
            fields.add(field);
        }
    }

    private boolean skipBasedOnModifiers(int modifiers) {
        return isStatic(modifiers) || isTransient(modifiers);
    }

    private void printField(Object graph, Serializer out, Field field) {
        try {
            field.setAccessible(true);
            out.print(field.getName());
            out.print(" = ");
            out.debug(field.get(graph));
            out.ln();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override public String toString() {
        return "POJOHandler";
    }
}
