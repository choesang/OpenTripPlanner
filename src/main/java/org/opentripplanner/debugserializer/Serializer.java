package org.opentripplanner.debugserializer;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (13.08.2017)
 */
public interface Serializer {

    void debug(Object graphChildObject);

    void printStr(String value);

    void print(Object value);

    void ln();

    void indentInc();

    void indentDec();

    void printRef(Object graph);
}
