package core.report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.helper.RandomHelper;

/**
 * In this class, we created an ExtentReport object and it can be reachable via
 * createInstance() method and set all options we need to ExtentReport report
 * HTML file location
 */
public class ExtentReportManager {

	/**
	 * Extent reports
	 */
	private static ExtentReports extent;

	/**
	 * Full report file name
	 */
	private static String reportFileName = "Test-Automation-Report " + RandomHelper.getDateNow("MM.dd.yyyy - HH.mm.ss")
			+ ".html";

	/**
	 * Prefix file
	 */
	private static String fileSeperator = System.getProperty("file.separator");

	/**
	 * Report file path
	 */
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";

	/**
	 * Full Report file location
	 */
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	/**
	 * Get instance of executing session for create extent report
	 * 
	 * @return ExtentReport object
	 */
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	/**
	 * Create extent report with set all HTML report options
	 * 
	 * @return ExtentReport object
	 */
	public static ExtentReports createInstance() {
		String fileName = getReportPath(reportFilepath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
		return extent;
	}

	/**
	 * After finishing write info into report, terminate the current instance
	 */
	public static void flushReport() {
		System.out.println("Report: " + reportFileLocation + " is created!");
		getInstance().flush();
	}

	/**
	 * Get report file location
	 * 
	 * @param path - report file location
	 * @return a string of file location with all info (Name, Time,..)
	 */
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Report directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				System.out.println("Failed to create report directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Report directory already exists: " + path);
		}
		return reportFileLocation;
	}

	/**
	 * Get screenshot photo location, if the folder is not existed, then this will
	 * create a new one
	 * 
	 * @return Screenshot file folder
	 */
	public static synchronized String getScreenshotFolder() {
		String path = reportFilepath + fileSeperator + "screenshots";
		File output = new File(path);
		if (!output.exists())
			output.mkdir();
		return path;
	}

}