package gl.linpeng.tools.builder.filters;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gl.linpeng.tools.builder.resources.CssResource;
import gl.linpeng.tools.builder.resources.DirectoryResource;
import gl.linpeng.tools.builder.resources.JavascriptResource;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.ResourceType;

/**
 * CopyOperation单元测试用例
 * 
 * @author linpeng
 *
 */
public class CopyOperationTest {

	// Prepare test object
	CopyOperation copyOperation;
	CssResource css;
	JavascriptResource javascript;
	DirectoryResource directory;
	LocalStorageResource image;
	LocalStorageResource invalidResource;

	@Before
	public void setUp() throws Exception {
		copyOperation = new CopyOperation();
		css = new CssResource();
		image = new LocalStorageResource();
		image.setType(ResourceType.IMAGE);
		image.setSource(new File("./test.tmp"));
		javascript = new JavascriptResource();
		directory = new DirectoryResource();
		invalidResource = new LocalStorageResource();
	}

	@Test
	public void testIsSupported() {
		// 文本资源以压缩、混淆的方式发布，拷贝操作主要用于二进制资源以及第三方整体目录
		Assert.assertTrue(copyOperation.isSupported(directory));
		Assert.assertTrue(copyOperation.isSupported(image));

		Assert.assertFalse(copyOperation.isSupported(css));
		Assert.assertFalse(copyOperation.isSupported(javascript));

	}

	@Test(expected = NullPointerException.class)
	public void testUnsupported() {
		Assert.assertFalse(copyOperation.isSupported(invalidResource));
	}

	@Test
	public void testOnProcess() {
		Assert.assertTrue(copyOperation.isSupported(image));
		copyOperation.onProcess(image);
		Assert.assertEquals("./test.tmp;", copyOperation.toText());
	}

}
