package delight.graaljssandbox;

import static org.junit.Assert.assertEquals;

import javax.script.ScriptException;

import org.junit.Test;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

@SuppressWarnings("restriction")
public class TestMapReduce {
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testMapReduce_graal() throws ScriptException {
		String script = "[1,2,3,4].map(function(n){return n+1}).reduce(function(prev,cur){return prev+cur}, 0)";
		String[] NASHORN_ARGS = {"--no-java", "--no-syntax-extensions" };
		
		Double nashornResult = (Double) new NashornScriptEngineFactory().getScriptEngine(NASHORN_ARGS).eval(script);
		assertEquals(14, nashornResult.intValue());
		
		Double sandboxResults = (Double) GraalSandboxes.create(NASHORN_ARGS).eval(script);
		assertEquals(14, sandboxResults.intValue());
	}
}
