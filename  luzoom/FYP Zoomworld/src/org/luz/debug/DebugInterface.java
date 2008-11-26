package org.luz.debug;

public interface DebugInterface {
	
	public abstract void debug(Object debug);
	public abstract void error(Exception debug);
	public abstract void error(Object debug);
	public abstract String getDebugType();
}
