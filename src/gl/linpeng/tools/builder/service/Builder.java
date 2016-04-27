package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.MinifyOperation;
import gl.linpeng.tools.builder.filters.Operation;
import gl.linpeng.tools.builder.filters.UglifyOperation;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sample builder implement
 * 
 * @author linpeng
 *
 */
public class Builder implements BuildService {
	final Logger logger = LoggerFactory.getLogger(Builder.class);

	private List<LocalStorageResource> resources;
	private List<Operation> operations;

	private List<String> includes;
	private List<String> excludes;

	private File source;
	private File target;

	@Override
	public void build() {
		loadOperations();
		loadResources();

		String result = "";

		for (int i = 0; i <= operations.size() - 1; i++) {
			Operation operation = operations.get(i);
			for (Resource resource : resources) {
				if (operation.isSupported(resource)) {
					operation.onProcess(resource);
				}
			}
			String tempResult = operation.toText();

			logger.debug("{} process result -> {}", operation, tempResult);
			if (i == operations.size() - 1) {
				result = tempResult;
			}
		}
		logger.info("final process result -> {}", result);

		// store to target
	}

	@Override
	public void setSource(File file) {
		this.source = file;
	}

	@Override
	public void setTarget(File file) {
		this.target = file;
	}

	@Override
	public List<LocalStorageResource> loadResources() {
		if (this.resources == null) {
			this.resources = FileUtils.getFiles(this.source, includes,
					new RegexFileFilter("\\w+"));
		}
		return this.resources;
	}

	@Override
	public List<Operation> loadOperations() {
		if (this.operations == null) {
			this.operations = new ArrayList<Operation>();
			this.operations.add(new MinifyOperation());
			this.operations.add(new UglifyOperation());
		}
		return this.operations;
	}

	@Override
	public List<String> setIncludes(List<String> includes) {
		return this.includes = includes;
	}

	@Override
	public List<String> setExcludes(List<String> excludes) {
		return this.excludes = excludes;
	}

}
