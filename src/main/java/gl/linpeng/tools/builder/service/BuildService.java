package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.model.BuildModel;
import gl.linpeng.tools.builder.module.Module;
import gl.linpeng.tools.builder.result.BuildResult;

import java.util.List;
import java.util.Map;

/**
 * build service load modules and operation,and process module by operation,get
 * one build result bean in the end.
 * 
 * @author linpeng
 * 
 */
public interface BuildService<T extends Module<T>> {

	/**
	 * Load modules to service
	 * 
	 * @return modules
	 */
	public List<T> loadModules();

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
	public BuildResult<T> build(BuildModel model);

	/**
	 * build service setup,load modules and operations canbe here.
	 */
	public void setup(BuildModel model);

	/**
	 * register module to service
	 * 
	 * @param module
	 */
	public void registerModule(T module);

	/**
	 * register operation to service
	 * 
	 * @param operation
	 */
	public void registerOperation(Operation operation);

	/**
	 * Service context
	 * 
	 * @return
	 */
	public Map<String, Object> getContext();

}
