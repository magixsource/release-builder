package gl.linpeng.tools.builder.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZip() {
		String sourceDir = "E:\\t1";
		gl.linpeng.tools.builder.utils.FileUtils.zip(sourceDir);
	}

}
