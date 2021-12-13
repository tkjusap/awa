package com.funtap.awass.python;
import org.python.util.PythonInterpreter;

import java.io.StringWriter;

import static javatests.TestSupport.assertEquals;

public class pythonInjava {
    public void givenPythonInterpreter_whenPrintExecuted_thenOutputDisplayed() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);
            pyInterp.exec("print('Hello Baeldung Readers!!')");
            assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", output.toString()
                    .trim());
        }
    }
}
