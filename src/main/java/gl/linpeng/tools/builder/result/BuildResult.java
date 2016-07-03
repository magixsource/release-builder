package gl.linpeng.tools.builder.result;

import java.io.File;

import gl.linpeng.tools.builder.service.BuildService;

/**
 * Build result Model,BuildResult is a result of process after builderService
 * 
 * @author linpeng
 *
 */
public interface BuildResult {

	/**
	 * According modules and operations parse to a really buildresult
	 * 
	 * @return
	 */
	public BuildResult toResult(BuildService service);

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
