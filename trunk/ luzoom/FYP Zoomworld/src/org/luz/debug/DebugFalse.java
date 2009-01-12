package org.luz.debug;

public class DebugFalse implements DebugInterface {

	@Override
	public void debug(Object debug){}

	@Override
	public void error(Exception debug) {}

	@Override
	public void error(Object debug) {}

	@Override
	public String getDebugType() {
		return "";
	}

}
