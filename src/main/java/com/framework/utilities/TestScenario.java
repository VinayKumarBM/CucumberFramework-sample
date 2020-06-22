package com.framework.utilities;

import java.util.HashMap;

public class TestScenario {
	private static TestScenario scenario = null;
	private static ThreadLocal<HashMap<String, Object>> scenarioContext = new ThreadLocal<HashMap<String, Object>>() {
		 @Override
		    protected HashMap<String, Object> initialValue() {
		        return new HashMap<>();
		    }
	};

    private TestScenario(){
       
    }

    public static TestScenario getSession() {
    	if(scenario == null) {
    		synchronized (TestScenario.class) 
    		{ 
    			if(scenario==null) 
    			{ 
    				scenario = new TestScenario();
    			}
    		}
    	}
    	return scenario;
    }
    
    public void setVariable(String key, Object value) {
    	scenarioContext.get().put(key, value);
    }

    public Object getVariable(String key){
        return scenarioContext.get().get(key);
    }

    public boolean containsVariable(String key){
        return scenarioContext.get().containsKey(key);
    }
}
