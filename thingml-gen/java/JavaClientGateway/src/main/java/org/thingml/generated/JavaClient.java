/**
 * File generated by the ThingML IDE
 * /!\Do not edit this file/!\
 * In case of a bug in the generated code,
 * please submit an issue on our GitHub
 **/

package org.thingml.generated;

import no.sintef.jasm.*;
import no.sintef.jasm.ext.*;

import org.thingml.generated.api.*;
import org.thingml.generated.messages.*;

import java.util.*;

/**
 * Definition for type : JavaClient
 **/
public class JavaClient extends Component implements IJavaClient_sensor {


	// START: @java_features annotation
org.thingml.pressuresensor.ui.MainFrame ui;

	// END: @java_features annotation

private boolean debug = false;
public boolean isDebug() {return debug;}
public void setDebug(boolean debug) {this.debug = debug;}
public String toString() {
String result = "instance " + getName() + "\n";
result += "";
return result;
}

public synchronized void pressure_via_sensor(int LoggerMsgs_pressure_a_var, int LoggerMsgs_pressure_b_var){
final Event _msg = pressureType.instantiate(LoggerMsgs_pressure_a_var, LoggerMsgs_pressure_b_var);
_msg.setPort(sensor_port);
receive(_msg);
}

//Attributes
private int JavaClient_SC_bias_var;
private int debug_JavaClient_SC_bias_var;
//Ports
private Port sensor_port;
//Message types
protected final PressureMessageType pressureType = new PressureMessageType();
//Empty Constructor
public JavaClient() {
super();
JavaClient_SC_bias_var = (int) 0;
}

//Constructor (all attributes)
public JavaClient(String name, final int JavaClient_SC_bias_var) {
super(name);
this.JavaClient_SC_bias_var = JavaClient_SC_bias_var;
}

//Getters and Setters for non readonly/final attributes
public int getJavaClient_SC_bias_var() {
return JavaClient_SC_bias_var;
}

public void setJavaClient_SC_bias_var(int JavaClient_SC_bias_var) {
this.JavaClient_SC_bias_var = JavaClient_SC_bias_var;
}

//Getters for Ports
public Port getSensor_port() {
return sensor_port;
}
private CompositeState buildJavaClient_SC(){
final AtomicState state_JavaClient_SC_READY = new AtomicState("READY");
Handler h1805398191 = new Handler();
h1805398191.from(state_JavaClient_SC_READY);
h1805398191.event(pressureType);
h1805398191.port(sensor_port);
h1805398191.action((Event e)->{
final PressureMessageType.PressureMessage pressure = (PressureMessageType.PressureMessage) e;
System.out.print("Pressure A=" + pressure.a + " B=" + pressure.b + "\n");
int diff_var = (int) (pressure.b - pressure.a);

JavaClient_SC_bias_var = (int) (getJavaClient_SC_bias_var() - getJavaClient_SC_bias_var() / 100 + diff_var);
diff_var = (int) (diff_var - getJavaClient_SC_bias_var() / 100);
ui.insertData(pressure.a, pressure.b, diff_var);
});

final CompositeState state_JavaClient_SC = new CompositeState("SC"){
private int JavaClient_SC_bias_var = 0;
public int getJavaClient_SC_bias_var() {
return JavaClient_SC_bias_var;
}

public void setJavaClient_SC_bias_var(int JavaClient_SC_bias_var) {
this.JavaClient_SC_bias_var = JavaClient_SC_bias_var;
}

};
state_JavaClient_SC.onEntry(()->{
System.out.print("Java Client Ready. Waiting for sensor data...");
ui = new org.thingml.pressuresensor.ui.MainFrame();
java.awt.EventQueue.invokeLater(new Runnable() {
            	public void run() {
                	ui.setVisible(true);
            	}
        	});
});
state_JavaClient_SC.add(state_JavaClient_SC_READY);
state_JavaClient_SC.initial(state_JavaClient_SC_READY);
return state_JavaClient_SC;
}

public Component buildBehavior(String session, Component root) {
if (root == null) {
//Init ports
sensor_port = new Port("sensor", this);
} else {
sensor_port = ((JavaClient)root).sensor_port;
}
if (session == null){
//Init state machine
behavior = buildJavaClient_SC();
}
return this;
}

}
