package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.module.Module;
import gl.linpeng.tools.builder.result.BuildResult;

import java.util.List;

/**
 * build service load modules and operation,and process module by operation,get
 * one build result bean in the end.
 * 
 * @author linpeng
 *
 */
public interface BuildService {

	/**
	 * Load modules to service
	 * 
	 * @return modules
	 */
	public List<Module> loadModules();

	/**
	 * Load operations if operations is null
	 * 
	 * @return operations
	 */
	public List<Operation> loadOperations();

	/**
	 * load operations and modules,and make operation process module and store
	 * process result.
	 * 
	 * @return build result
	 */
	public BuildResult build();

	/**
	 * build service setup,load modules and operations canbe here.
	 */
	public void setup();

	/**
	 * register module to service
	 * 
	 * @param module
	 */
	public void registerModule(Module module);

	/**
	 * register operation to service
	 * 
	 * @param operation
	 */
	public void registerOperation(Operation operation);

}
