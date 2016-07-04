package gl.linpeng.tools.builder.result;

import gl.linpeng.tools.builder.module.Module;
import gl.linpeng.tools.builder.service.BuildService;

import java.io.File;

/**
 * Build result Model,BuildResult is a result of process after builderService
 * 
 * @author linpeng
 *
 */
public interface BuildResult<T extends Module<T>> {

	/**
	 * According modules and operations parse to a really buildresult
	 * 
	 * @return
	 */
	public BuildResult<T> toResult(BuildService<T> service);

	/**
	 * Parse BuildResult to readable string
	 * 
	 * @return
	 */
	public String toText();

	/**
	 * Get buildResult if it's file
	 * 
	 * @return
	 */
	public File getFile();

}
