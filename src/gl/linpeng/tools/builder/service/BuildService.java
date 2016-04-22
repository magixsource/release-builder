package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.filters.BuilderOperation;
import gl.linpeng.tools.builder.resources.BasicResource;

import java.io.File;
import java.util.List;

/**
 * What should build service is
 * 
 * @author linpeng
 *
 */
public interface BuildService {

	/**
	 * Where build sources store
	 * 
	 * @param file
	 *            source store dir
	 */
	public void setSource(File file);

	/**
	 * Where build result store
	 * 
	 * @param file
	 *            result store dir
	 */
	public void setTarget(File file);

	/**
	 * Load resources if resources is null
	 * 
	 * @return resources
	 */
	public List<BasicResource> loadResources();

	/**
	 * Load operations if operations is null
	 * 
	 * @return operations
	 */
	public List<BuilderOperation> loadOperations();

	/**
	 * Names of file which should include
	 * 
	 * @param includes
	 * @return
	 */
	public List<String> setIncludes(List<String> includes);

	/**
	 * Names of file which should exclude
	 * 
	 * @param excludes
	 * @return
	 */
	public List<String> setExcludes(List<String> excludes);

	/**
	 * load operations and resources,and make operation process resource and
	 * store process result.
	 */
	public void build();

}
