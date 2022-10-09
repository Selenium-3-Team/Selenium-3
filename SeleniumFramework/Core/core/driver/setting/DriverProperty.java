package core.driver.setting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import core.helper.JsonHelper;

/**
 *	Driver properties defined 
 */
public class DriverProperty {

	/**
	 * Driver OS platform 
	 */
	private Platform platform;
	
	/**
	 * Driver browser type
	 */
	private DriverType driverType;
	
	/**
	 * Driver running mode
	 */
	private RunningMode mode;
	
	/**
	 * Driver's executable
	 */
	private String driverExecutable;
	
	/**
	 * Driver remote url 
	 */
	private URL remoteUrl;
	
	/**
	 * Driver arguments
	 */
	private List<String> arguments;
	
	/**
	 * Driver user preference
	 */
	private Map<String, Object> userProfilePreference;
	
	/**
	 * Driver capabilities
	 */
	private DesiredCapabilities capabilities;
	
	/**
	 * Page time out in seconds
	 */
	private int pageTimeOut = 60; 
	
	/**
	 * Element time out in seconds
	 */
	private int elementTimeOut = 0; 
	
	/**
	 * Get driver platform
	 * @return platform
	 */
	public Platform getPlatform() {
		return platform;
	}
	
	/**
	 * Set driver platform
	 * @param platform - constanted platform type of driver: Window, Linux, Mac
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	/**
	 * Set driver platform
	 * @param platform - a string of platform value
	 * @throws Exception
	 */
	public void setPlatform(String platform) throws Exception {
		this.platform = Platform.fromString(platform);
	}
	
	/**
	 * Get driver browser type
	 * @return driver type
	 */
	public DriverType getDriverType() {
		return driverType;
	}
	
	/**
	 * Set driver browser type
	 * @param driverType - constanted driver type of browser: Chrome, firefox, Safari, Edge
	 */
	public void setDriverType(DriverType driverType) {
		this.driverType = driverType;
	}
	
	/**
	 * Set driver browser type
	 * @param driverType - a string of driver type
	 * @throws Exception
	 */
	public void setDriverType(String driverType) throws Exception {
		this.driverType = DriverType.fromString(driverType);
	}
	
	/**
	 * Get driver running mode
	 * @return running mode
	 */
	public RunningMode getMode() {
		return mode;
	}
	
	/**
	 * Set driver running mode
	 * @param runningMode - constanted running mode of driver: Local, Remote
	 */
	public void setMode(RunningMode runningMode) {
		this.mode = runningMode;
	}
	
	/**
	 * Set driver running mode
	 * @param runningMode - a string of running mode
	 * @throws Exception
	 */
	public void setMode(String runningMode) throws Exception {
		this.mode = RunningMode.fromString(runningMode);
	}
	
	/**
	 * Get driver executable
	 * @return driver executable
	 */
	public String getDriverExecutable() {
		return driverExecutable;
	}
	
	/**
	 * Set driver executable
	 * @param driverExecutable - a string of driver executable
	 */
	public void setDriverExecutable(String driverExecutable) {
		this.driverExecutable = driverExecutable;
	}
	
	/**
	 * Get driver remote Url
	 * @return remote Url
	 */
	public URL getRemoteUrl() {
		return remoteUrl;
	}
	
	/**
	 * Set driver remote Url
	 * @param remoteUrl - a string of url, if url is null, it create a URL object
	 * @throws MalformedURLException
	 */
	public void setRemoteUrl(String remoteUrl) throws MalformedURLException {
		if(remoteUrl != null) {
			this.remoteUrl = new URL(remoteUrl);
		}
	}
	
	/**
	 * Get list of driver arguments
	 * @return a list string of arguments
	 */
	public List<String> getArguments(){
		return arguments;
	}
	
	/**
	 * Get array of driver arguments
	 * @return an array string of arguments
	 */
	public String[] getArgumentsAsArray() {
		return (String[])arguments.toArray();
	}
	
	/**
	 * Set driver arguments
	 * @param arguments - a list of string arguments
	 */
	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}
	
	/**
	 * Set driver arguments
	 * @param arguments - a String of arguments
	 */
	public void setArguments(String arguments) throws Exception{
		this.arguments = JsonHelper.convertJsonToArguments(arguments);
	}
	
	/**
	 * Get user preference
	 * @return - a map of string and object user profile preference
	 */
	public Map<String, Object> getUserProfilePreference(){
		return userProfilePreference;
	}
	
	/**
	 * Set user preference
	 * @param userProfilePreference - a map of string and object user profile preference
	 */
	public void setUserProfilePreference(Map<String, Object> userProfilePreference) {
		this.userProfilePreference = userProfilePreference;
	}
	
	/**
	 *	Set user preference 
	 * @param userProfilePreference - a string of user profile preference
	 */
	public void setUserProfilePreference(String userProfilePreference) throws Exception {
		this.userProfilePreference = JsonHelper.convertJsonToMap(userProfilePreference);
	}
	
	/**
	 * Get browser type's capabilities
	 * @return browser capabilities
	 */
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}
	
	/**
	 * Set browser type's capabilities
	 * @param capabilities - a class used to set properties of browser
	 */
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}
	
	/**
	 * Set browser type's capabilities
	 * @param capabilities - a string of browser property
	 */
	public void setCapabilities(String capabilities){
		this.capabilities = JsonHelper.convertJsonToCapabilities(capabilities);
	}
	
	/**
	 * Get time out for page
	 * @return timeout
	 */
	public int getPageTimeout() {
		return pageTimeOut;
	}
	
	/**
	 * Set time out for page
	 * @param pageTimeOut - int timeout in seconds 
	 */
	public void setPageTimeOut(int pageTimeOut) {
		this.pageTimeOut = pageTimeOut;
	}
	
	/**
	 * Set time out for page
	 * @param pageTimeOut - if user put a string of time out. This will check if that string is numeric then it will parse to int type
	 */
	public void setPageTimeOut(String pageTimeOut) {
		if (StringUtils.isNumeric(pageTimeOut))
			this.pageTimeOut = Integer.parseInt(pageTimeOut);
	}
	
	/**
	 * Get time out for element
	 * @return timeout
	 */
	public int getElementTimeOut() {
		return elementTimeOut;
	}
	
	/**
	 * Set time out for element
	 * @param pageTimeOut - int timeout in seconds 
	 */
	public void setElementTimeOut(int elementTimeOut) {
		this.elementTimeOut = elementTimeOut;
	}
	
	/**
	 * Set time out for element
	 * @param pageTimeOut - if user put a string of time out. This will check if that string is numeric then it will parse to int type
	 */
	public void setElementTimeOut(String elementTimeOut) {
		if (StringUtils.isNumeric(elementTimeOut))
			this.elementTimeOut = Integer.parseInt(elementTimeOut);
	}
	
}
