package gl.linpeng.tools.builder.result;

import gl.linpeng.tools.builder.filters.CopyOperation;
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
		LocalStorageBuildService localStorageService = (LocalStorageBuildService) service;

		List<Operation> operations = localStorageService.loadOperations();
		// Just in DEV mode, to easy add operations 2016-7-1 by LINPENG
		if (null == operations) {
			localStorageService.registerOperation(new CopyOperation());
			operations = localStorageService.loadOperations();
		}
		List<LocalStorageModule> modules = localStorageService
				.loadLocalStorageModules();
		Map<String, Object> context = localStorageService.getContext();

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

		// final custom result
		customResult(context);
		return this;
	}

	/**
	 * Custom result what we wanna
	 * 
	 * @param context
	 */
	private void customResult(Map<String, Object> context) {
		String basePath = FileUtils.getTempDirectoryPath() + "result";
		for (Map.Entry<String, Object> entry : context.entrySet()) {
			try {
				if ("Css".equalsIgnoreCase(entry.getKey())) {
					File customCss = FileUtils.getFile(basePath
							+ "\\css\\frontend.custom.css");
					FileUtils.write(customCss, entry.getValue().toString());
				} else if ("JavaScript".equalsIgnoreCase(entry.getKey())) {
					File customJs = FileUtils.getFile(basePath
							+ "\\js\\frontend.custom.js");
					FileUtils.write(customJs, entry.getValue().toString());
				} else if ("Image".equalsIgnoreCase(entry.getKey())) {
					File customImgDir = FileUtils.getFile(basePath + "\\img");
					String[] paths = entry.getValue().toString().split(";");
					gl.linpeng.tools.builder.utils.FileUtils
							.copyFilesToDirectory(paths, customImgDir);
				} else if ("Directory".equalsIgnoreCase(entry.getKey())) {
					File destDir = FileUtils.getFile(basePath + "\\lib");
					String[] paths = entry.getValue().toString().split(";");
					gl.linpeng.tools.builder.utils.FileUtils
							.copyDirectorysToDirectory(paths, destDir);
				}

			} catch (IOException e) {
				logger.error("Frontend Build CustomResult error. {}", e);
			}
		}
		// zip result
		gl.linpeng.tools.builder.utils.FileUtils.zip(basePath);
	}

	private void storeToContext(LocalStorageModule module,
			Map<String, Object> context) {
		for (LocalStorageResource resource : module.getResources()) {
			String key = resource.getType().name();
			String content = context.get(key) == null ? "" : context.get(key)
					.toString();
			if (ResourceType.IMAGE.equals(resource.getType())
					|| ResourceType.DIRECTORY.equals(resource.getType())) {
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
