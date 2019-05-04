package com.framework.utilities;

import java.util.HashMap;
import java.util.Map;

public class TestScenario {
	private static TestScenario scenario = new TestScenario();
	private  Map<String, Object> scenarioContext;

    private TestScenario(){
        scenarioContext = new HashMap<>();
    }

    public static TestScenario getScenario() {
    	return scenario;
    }
    
    public void setSessionVariable(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getSessionVariable(String key){
        return scenarioContext.get(key);
    }

    public Boolean containsSessionVariable(String key){
        return scenarioContext.containsKey(key);
    }
}
