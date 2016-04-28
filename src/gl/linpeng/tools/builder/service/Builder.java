package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.module.Module;
import gl.linpeng.tools.builder.result.BuildResult;
import gl.linpeng.tools.builder.result.FrontendBuildResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Operation> loadOperations() {
		return this.operations;
	}

	@Override
	public List<LocalStorageModule> loadLocalStorageModules() {
		if (this.modules == null) {
			throw new NullPointerException("Modules can't be null.");
			// this.modules = FileUtils.getFiles(this.source, includes,
			// new RegexFileFilter("\\w+"));
		}
		return this.modules;
	}

	@Override
	public List<Module> loadModules() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BuildResult build() {
		// setup service
		setup();
		return buildResult.toResult(this);
	}

	@Override
	public void setup() {
		loadOperations();
		loadLocalStorageModules();
	}

	@Override
	public void registerModule(Module module) {
		if (this.modules == null) {
			this.modules = new ArrayList<LocalStorageModule>();
		}
		LocalStorageModule _module = (LocalStorageModule) module;
		this.modules.add(_module);
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

}
