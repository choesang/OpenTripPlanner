package org.opentripplanner.debugserializer;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Thomas Gran (Capra) - tgr@capraconsulting.no (11.08.2017)
 */
public class Printer {

    private final PrintStream out;

    private final StringBuffer indent = new StringBuffer();

    private boolean newLine = false;

    Printer(OutputStream out) {
        if (out instanceof PrintStream) {
            this.out = (PrintStream) out;
        } else {
            this.out = new PrintStream(out);
        }
    }

    void printStr(String value) {
        printIndent();
        out.print('\'');
        out.print(value.replace("\n", "\n" + indent));
        out.print('\'');
    }

    void print(Object value) {
        printIndent();
        out.print(value);
    }

    void ln() {
        out.print('\n');
        newLine = true;
    }

    void indentInc() {
        indent.append("  ");
    }

    void indentDec() {
        indent.setLength(Math.max(0, indent.length() - 2));
    }

    void printRef(String ref) {
        print('[');
        print(ref);
        print(']');
    }

    private void printIndent() {
        if (newLine) {
            out.print(indent.toString());
            newLine = false;
        }
    }
}
