package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.BuilderOperation;
import gl.linpeng.tools.builder.filters.MinifyOperation;
import gl.linpeng.tools.builder.resources.BasicResource;
import gl.linpeng.tools.builder.resources.BuilderResource;
import gl.linpeng.tools.builder.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.NameFileFilter;
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

	private List<BasicResource> resources;
	private List<BuilderOperation> operations;

	private List<String> includes;
	private List<String> excludes;

	private File source;
	private File target;

	@Override
	public void build() {
		loadOperations();
		loadResources();

		for (BuilderOperation operation : operations) {
			for (BuilderResource resource : resources) {
				if (operation.isSupported(resource)) {
					operation.onProcess(resource);
				}
			}
			String tempResult = operation.toText();

			logger.debug("{} process result -> {}", operation, tempResult);
		}

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
	public List<BasicResource> loadResources() {
		if (this.resources == null) {
			this.resources = FileUtils.getFiles(this.source, includes,
					new NameFileFilter(new String[] { "js", "app" }));
		}
		return this.resources;
	}

	@Override
	public List<BuilderOperation> loadOperations() {
		if (this.operations == null) {
			this.operations = new ArrayList<BuilderOperation>();
			this.operations.add(new MinifyOperation());
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
