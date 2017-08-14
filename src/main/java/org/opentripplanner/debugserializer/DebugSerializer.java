package org.opentripplanner.debugserializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class DebugSerializer implements Serializer {

    private Printer out;

    private ObjectHandlers handlers = new ObjectHandlers();

    private ObjetRefGenerator refGenerator = new ObjetRefGenerator();

    private DebugSerializer(OutputStream out) {
        this.out = new Printer(out);
    }

    public static void debug(Object graph, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            debug(graph, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void debug(Object graph, OutputStream out) {
        try {
            DebugSerializer serializer = new DebugSerializer(out);
            serializer.debug(graph);
            serializer.out.ln();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debug(Object graph) {
        if (graph == null) {
            out.print("null");
            return;
        }

        Class type = graph.getClass();

        for (ObjectHandler handler : handlers) {
            if (handler.accept(graph, type, this)) {
                handler.print(graph, type, this);
                break;
            }
        }
    }

    @Override public void printStr(String value) {
        out.printStr(value);
    }

    @Override public void print(Object value) {
        out.print(value);
    }

    @Override public void ln() {
        out.ln();
    }

    @Override public void indentInc() {
        out.indentInc();
    }

    @Override public void indentDec() {
        out.indentDec();
    }

    @Override public void printRef(Object graph) {
        out.printRef(refGenerator.ref(graph));
    }



/* TEST */

    public static void main(String[] args) throws InterruptedException {
        B b1 = new B("bOne");
        B b2 = new B("bTwo");
        B b3 = new C("bThree");

        DebugSerializer.debug(new A("Truls", b1, b2, b1, new B[] { b1, null, b3 }), System.out);

        Thread.sleep(10);
    }

    static class A {
        private final String name;

        private final B b1;

        private final B b2;

        private final B b3;

        private final B[] bs;

        private final Collection list = Arrays.asList("One", "Two", 8);

        private final Set emptySet = Collections.emptySet();

        private final HashMap map = new HashMap();

        private final Map emptyMap = Collections.emptyMap();

        private static final long serializationUIID = 99;

        public A(String name, B b1, B b2, B b3, B[] bs) {
            this.name = name;
            this.b1 = b1;
            this.b2 = b2;
            this.b3 = b3;
            this.bs = bs;
            map.put("A", "one");
            map.put("B", "two");
            map.put("C", "three");
            map.put("D", "four");
            map.put("E", b1);
        }
    }

    static class B {
        private final String name;

        public B(String name) {
            this.name = name;
        }
    }

    static class C extends B {
        private String localName = "C";

        public C(String name) {
            super(name);
        }
    }

}
