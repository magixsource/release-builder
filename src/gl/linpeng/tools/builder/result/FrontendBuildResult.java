package gl.linpeng.tools.builder.result;

import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.BuildService;
import gl.linpeng.tools.builder.service.LocalStorageBuildService;
import gl.linpeng.tools.builder.service.ResourceType;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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

		List<Operation> operations = _service.loadOperations();
		List<LocalStorageModule> modules = _service.loadLocalStorageModules();
		Map<String, Object> context = _service.getContext();

		for (Operation operation : operations) {
			for (LocalStorageModule module : modules) {
				processModule(module, operation);
			}
			String tempResult = operation.toText();

			logger.debug("{} process result -> {}", operation, tempResult);
		}

		for (LocalStorageModule module : modules) {
			storeToContext(module, context);
		}

		logger.info("final process result -> {}", context);

		// final result
		String basePath = FileUtils.getTempDirectoryPath();
		for (Map.Entry<String, Object> entry : context.entrySet()) {
			try {
				if (entry.getKey().equalsIgnoreCase("Css")) {
					File customCss = FileUtils.getFile(basePath
							+ "css\\frontend.custom.css");
					FileUtils.write(customCss, entry.getValue().toString());
				} else if (entry.getKey().equalsIgnoreCase("JavaScript")) {
					File customJs = FileUtils.getFile(basePath
							+ "js\\frontend.custom.js");
					FileUtils.write(customJs, entry.getValue().toString());
				} else if (entry.getKey().equalsIgnoreCase("Image")) {
					File customImgDir = FileUtils.getFile(basePath + "\\img");
					String[] paths = entry.getValue().toString().split(";");
					for (String path : paths) {
						FileUtils.copyFileToDirectory(FileUtils.getFile(path),
								customImgDir);
					}
				} else if (entry.getKey().equalsIgnoreCase("Directory")) {
					File destDir = FileUtils.getFile(basePath + "\\lib");
					String[] paths = entry.getValue().toString().split(";");
					for (String path : paths) {
						FileUtils.copyDirectoryToDirectory(
								FileUtils.getFile(path), destDir);
					}
				}
				// TODO store as zip result

			} catch (IOException e) {
				logger.error("Frontend Build parse error. {}", e);
			}
		}
		return this;
	}

	private void storeToContext(LocalStorageModule module,
			Map<String, Object> context) {
		for (LocalStorageResource resource : module.getResources()) {
			String key = resource.getType().name();
			String content = context.get(key) == null ? "" : context.get(key)
					.toString();
			if (ResourceType.Image.equals(resource.getType())
					|| ResourceType.Directory.equals(resource.getType())) {
				content += resource.getPath() + ";";
			} else {
				content += resource.getContent();
			}
			context.put(key, content);
		}
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
