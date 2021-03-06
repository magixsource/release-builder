package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.model.BuildModel;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.result.BuildResult;
import gl.linpeng.tools.builder.result.FrontendBuildResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sample builder implement
 * 
 * @author linpeng
 * 
 */
public class Builder implements LocalStorageBuildService {
	final Logger logger = LoggerFactory.getLogger(Builder.class);

	private List<LocalStorageModule> modules;
	private List<Operation> operations;
	// store handle result
	private Map<String, Object> context = new HashMap<String, Object>();
	private FrontendBuildResult buildResult = new FrontendBuildResult();

	private boolean isModuleSorted = false;

	@Override
	public List<Operation> loadOperations() {
		return this.operations;
	}

	@Override
	public List<LocalStorageModule> loadLocalStorageModules() {
		if (this.modules == null) {
			throw new NullPointerException("Modules can't be null.");
		}

		// re-sort modules, make dependencies in collections
		if (!isModuleSorted) {
			Set<LocalStorageModule> sortedModules = new HashSet<LocalStorageModule>();
			sortModules(modules, sortedModules);
			modules = new ArrayList<LocalStorageModule>();
			modules.addAll(sortedModules);

			isModuleSorted = true;
		}
		// sorted
		return this.modules;
	}

	/**
	 * Sort module list
	 * 
	 * @param modules
	 * @param sortedModules
	 */
	private void sortModules(List<LocalStorageModule> modules,
			Set<LocalStorageModule> sortedModules) {
		for (LocalStorageModule module : modules) {
			sortedModules.add(module);
			if (module.getDependencies() != null) {
				List<LocalStorageModule> dependencies = (List<LocalStorageModule>) module
						.getDependencies();
				sortModules(dependencies, sortedModules);
			}
		}
	}

	@Override
	public List<LocalStorageModule> loadModules() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BuildResult<LocalStorageModule> build(BuildModel model) {
		// setup service
		setup(model);
		return buildResult.toResult(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setup(BuildModel model) {
		List<LocalStorageModule> muildModules = (List<LocalStorageModule>) model
				.getModules();
		for (LocalStorageModule module : muildModules) {
			registerModule(module);
		}

		loadOperations();
		loadLocalStorageModules();
	}

	@Override
	public void registerOperation(Operation operation) {
		if (this.operations == null) {
			this.operations = new ArrayList<Operation>();
		}
		this.operations.add(operation);
	}

	@Override
	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

	public FrontendBuildResult getBuildResult() {
		return buildResult;
	}

	public void setBuildResult(FrontendBuildResult buildResult) {
		this.buildResult = buildResult;
	}

	@Override
	public void registerModule(LocalStorageModule module) {
		if (this.modules == null) {
			this.modules = new ArrayList<LocalStorageModule>();
		}
		LocalStorageModule localStorageModule = (LocalStorageModule) module;
		this.modules.add(localStorageModule);

	}

}
