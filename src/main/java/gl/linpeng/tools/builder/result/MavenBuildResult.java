package gl.linpeng.tools.builder.result;

import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.service.BuildService;

import java.io.File;

/**
 * Maven build result parse result as a pom.xml
 * 
 * @author linpeng
 *
 */
public class MavenBuildResult implements BuildResult<LocalStorageModule> {

	@Override
	public String toText() {
		return null;
	}

	@Override
	public BuildResult<LocalStorageModule> toResult(
			BuildService<LocalStorageModule> service) {
		return null;
	}

	@Override
	public File getFile() {
		return null;
	}

}
