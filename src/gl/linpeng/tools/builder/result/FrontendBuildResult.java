package gl.linpeng.tools.builder.result;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.BuildService;
import gl.linpeng.tools.builder.service.LocalStorageBuildService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Frontend build result parse result as a zip outputstream
 * 
 * @author linpeng
 *
 */
public class FrontendBuildResult implements BuildResult {

	final Logger logger = LoggerFactory.getLogger(FrontendBuildResult.class);

	@Override
	public String toText() {
		return null;
	}

	@Override
	public BuildResult toResult(BuildService service) {
		LocalStorageBuildService _service = (LocalStorageBuildService) service;
		String result = "";
		List<Operation> operations = _service.loadOperations();
		List<LocalStorageModule> modules = _service.loadLocalStorageModules();

		for (int i = 0; i <= operations.size() - 1; i++) {
			Operation operation = operations.get(i);
			for (LocalStorageModule module : modules) {
				processModule(module, operation);
			}
			String tempResult = operation.toText();

			logger.debug("{} process result -> {}", operation, tempResult);
			if (i == operations.size() - 1) {
				result = tempResult;
			}
		}
		logger.info("final process result -> {}", result);
		return this;
	}

	/**
	 * Use operation process module
	 * 
	 * @param module
	 * @param operation
	 */
	private void processModule(LocalStorageModule module, Operation operation) {
		for (LocalStorageResource resource : module.getResources()) {
			if (operation.isSupported(resource)) {
				operation.onProcess(resource);
			}
		}
	}

}
