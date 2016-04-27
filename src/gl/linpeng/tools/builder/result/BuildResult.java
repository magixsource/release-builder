package gl.linpeng.tools.builder.result;

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
	public BuildResult toResult();

	/**
	 * Parse BuildResult to readable string
	 * 
	 * @return
	 */
	public String toText();

}
