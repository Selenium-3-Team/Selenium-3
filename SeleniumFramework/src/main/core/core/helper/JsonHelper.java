package core.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import core.common.Constant;

/**
 * <p>
 * A Collection of helper methods for json.
 * </p>
 */
public class JsonHelper {

	private static final Logger logger = Constant.createLogger(FileHelper.class.getName());

	/**
	 * <p>
	 * Convert data from Json string to Map.
	 * </p>
	 *
	 * @param json the string
	 * 
	 * @return an object of type Map<String, String>
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code String jsonString = "{'Selenium': '3', 'Team': '4'}";}
	 *          //Create the json string
	 *          {@code Map<String, String> info = JsonHelper.convertJsonToMap(jsonString);}
	 *          //Convert to map, return Map<String, String>
	 *          {@code info.get("Selenium"); info.get("Team");} //Get data in map
	 */
	public static <T> Map<String, T> convertJsonToMap(String json) {
		try {
			logger.info("JsonHelper: convertJsonToMap");
			Type mapType = new TypeToken<Map<String, T>>() {
			}.getType();
			Gson gson = new Gson();
			return gson.fromJson(json, mapType);
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}

	/**
	 * <p>
	 * Get data from a file with values in file must be Json type.
	 * </p>
	 *
	 * @param file    path the string
	 * @param desired data type after converting from json
	 * 
	 * @return an object of type List<T>
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code File file = new File("\\data.json");} //File path to
	 *          data.txt/data.json (not required *.json file) Create a data.json
	 *          file and enter the values as follows: [{"Selenium": 3, "Team": 4}]
	 *          {@code String path = file.getAbsolutePath();} //Get absolute path of
	 *          data file {@code Type mapType = new TypeToken<List<Map<String,
	 *          String>>>() {}.getType();} //Create Type type variable, use to
	 *          convert from json to type
	 *          {@code List<Map<String, String>> info = JsonHelper.getListData(path, mapType);}
	 *          //Convert to list map, return List<Map<String, String>>
	 *          {@code info.get(0).get("Selenium"); info.get(0).get("Team");} //Get
	 *          data in list map
	 */
	public static <T> List<T> getListData(String jsonPath, Type type) {
		try {
			logger.info("JsonHelper: getListData");
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst = new ArrayList<T>();
			Gson gson = new Gson();
			lst = gson.fromJson(reader, type);
			return lst;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}

	/**
	 * <p>
	 * Get data from a file with values in file must be Json type.
	 * </p>
	 *
	 * @param file    path the string
	 * @param desired data type after converting from json
	 * 
	 * @return an object of type T
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code File file = new File("\\data.json");} //File path to
	 *          data.txt/data.json (not required *.json file) Create a data.json
	 *          file and enter the values as follows: [{"Selenium": 3, "Team": 4}]
	 *          {@code String path = file.getAbsolutePath();} //Get absolute path of
	 *          data file {@code Type mapType = new TypeToken<Map<String, String>>()
	 *          {}.getType();} //Create Type type variable, use to convert from json
	 *          to type
	 *          {@code Map<String, String> info = JsonHelper.getData(path, mapType);}
	 *          //Convert to map, return Map<String, String>
	 *          {@code info.get("Selenium"); info.get("Team");} //Get data in map
	 */
	public static <T> T getData(String jsonPath, Type type) {
		try {
			logger.info("JsonHelper: getData");
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(reader, type);
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}

	/**
	 * <p>
	 * Get data from a file with values in file must be Json type.
	 * </p>
	 *
	 * @param file path the string
	 * 
	 * @return an object of JsonObject
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code File file = new File("\\data.json");} //File path to
	 *          data.txt/data.json (not required *.json file) Create a data.json
	 *          file and enter the values as follows: [{"Selenium": 3, "Team": 4}]
	 *          {@code String path = file.getAbsolutePath();} //Get absolute path of
	 *          data file {@code JsonObject info = JsonHelper.getJsonObject(path);}
	 *          //Convert to json object, return JsonObject
	 *          {@code info.get("Selenium").toString(); info.get("Team").toString();}
	 *          //Get data in json object
	 */
	public static JsonObject getJsonObject(String jsonPath) {
		try {
			logger.info("JsonHelper: getJsonObject");
			JsonObject obj = new JsonObject();
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			obj = gson.fromJson(reader, JsonObject.class);
			return obj;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}

	/**
	 * <p>
	 * Convert data from Json string to JsonElement.
	 * </p>
	 *
	 * @param json the string
	 * 
	 * @return an object of type JsonElement
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code String jsonString = "{'Selenium': '3', 'Team': '4'}";}
	 *          //Create the json string
	 *          {@code JsonElement jsonElement = JsonHelper.getJsonObjectFromString(jsonString);}
	 *          //Convert to json element, return JsonElement
	 *          {@code JsonObject info = jsonElement.getAsJsonObject();} //Convert
	 *          to json object, return JsonObject
	 *          {@code info.get("Selenium").toString(); info.get("Team").toString();}
	 *          //Get data in json object
	 */
	public static JsonElement getJsonObjectFromString(String jsonString) {
		try {
			logger.info("JsonHelper: getJsonObjectFromString");
			JsonElement obj = null;
			Gson gson = new Gson();
			obj = gson.fromJson(jsonString, JsonElement.class);
			return obj;
		} catch (Exception e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}

	/**
	 * <p>
	 * Convert data from Json string to Capabilities.
	 * </p>
	 *
	 * @param json the string
	 * 
	 * @return an object of type DesiredCapabilities
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code String jsonString = "{\"browserName\": \"Chrome\",
	 *          \"browserVersion\": \"105.0.5195.127\"}";} //Create the json string
	 *          {@code DesiredCapabilities info = JsonHelper.convertJsonToCapabilities(jsonString);}
	 *          //Convert to capabilities, return DesiredCapabilities
	 *          {@code info.getBrowserName(); info.getVersion();} //Get data in
	 *          capabilities
	 */
	public static DesiredCapabilities convertJsonToCapabilities(String json) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		Map<String, String> caps = convertJsonToMap(json);
		if (caps != null) {
			Set<String> keys = caps.keySet();
			for (String key : keys) {
				capabilities.setCapability(key, caps.get(key));
			}
		}
		return capabilities;
	}

	/**
	 * <p>
	 * Convert data from Json string to List<String> arguments.
	 * </p>
	 *
	 * @param json the string
	 * 
	 * @return an object of type List<String>
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code String jsonString = "{\"browserName\": \"Chrome\",
	 *          \"browserVersion\": \"105.0.5195.127\"}";} //Create the json string
	 *          {@code List<String> info = JsonHelper.convertJsonToArguments(jsonString);}
	 *          //Convert to list arguments, return List<String>
	 *          {@code info.get(0); info.get(1);} //Get data in list arguments
	 */
	public static List<String> convertJsonToArguments(String json) {
		List<String> args = new ArrayList<String>();
		Map<String, String> maps = convertJsonToMap(json);
		if (maps != null) {
			Set<String> keys = maps.keySet();
			for (String key : keys) {
				args.add(maps.get(key));
			}
		}
		return args;
	}

	/**
	 * <p>
	 * Get data from a file with values in file must be Json type.
	 * </p>
	 *
	 * @param file path the string
	 * 
	 * @return an object of type JsonReader
	 * 
	 * @throws JsonParseException  if json is not a valid representation for an
	 *                             object of type typeOfT
	 * @throws JsonSyntaxException if json is not a valid representation for an
	 *                             object of type
	 * 
	 * @example {@code File file = new File("\\data.json");} //File path to
	 *          data.txt/data.json (not required *.json file) Create a data.json
	 *          file and enter the values as follows: [{"Selenium": 3, "Team": 4}]
	 *          {@code String path = file.getAbsolutePath();} //Get absolute path of
	 *          data file {@code JsonReader reader = getJsonReader(path);} //Convert
	 *          to json reader, return getJsonReader
	 */
	private static JsonReader getJsonReader(String jsonPath) {
		try {
			JsonReader reader;
			reader = new JsonReader(new FileReader(jsonPath));
			return reader;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

}
