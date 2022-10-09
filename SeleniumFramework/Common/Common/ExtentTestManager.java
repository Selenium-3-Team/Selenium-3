package Common;

import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * An ExtentTest map holds the information of thread ids and ExtentTest instances
 * ExtentReports instance created by calling getInstance() method from ExtentReportManager 	
 */
public class ExtentTestManager {

	static ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<String, ExtentTest>();
	static ExtentReports extent = ExtentReportManager.getInstance();
	
	/**
	 * Get ExtentTest instance in via current thread ids of ExtentTest map
	 * @return an instance of ExtentTest
	 */
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get(String.valueOf(Thread.currentThread().getId()));
	}
	
	/**
	 * Terminate extent report
	 */
	public static synchronized void endTest() {
		ExtentReportManager.flushReport();
	}
	
	/**
	 * At this method, an instance of ExtentTest created and put into extentTestMap with current thread id
	 * @param testName - a string of test name
	 * @param parent - ExtentTest parent
	 * @return an instance of ExtentTest
	 */
	public static synchronized ExtentTest startTest(String testName, ExtentTest parent) {
		try {
			ExtentTest test;
			if (parent != null) {
				test = parent.createNode(testName);
				extentTestMap.put(String.valueOf(Thread.currentThread().getId()), test);
			} else {
				test = extent.createTest(testName);
				extentTestMap.put(String.valueOf(Thread.currentThread().getId()), test);
			}
			return test;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Check if the ExtentTest instance is existed in ExtentTest map
	 * @param key - a string of key
	 * @return true or false
	 */
	public static synchronized boolean isTestExisted(String key) {
		return extentTestMap.containsKey(key);
	}
	
}