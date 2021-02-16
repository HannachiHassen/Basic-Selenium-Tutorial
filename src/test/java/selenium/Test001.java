package selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.RetryCountIfFailed;

public class Test001 {
	
	@Test
	@RetryCountIfFailed(10)
	public void Test1()
	{
		Assert.assertEquals(false, true);
	}
	
	@Test
	public void Test2()
	{
		Assert.assertEquals(false, true);
	}
}
