package Pacakge1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Datatesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
	}
	
	@DataProvider()
	public List<HashMap<String,String>> getjsondatatoMap() throws IOException
	{
		String jsoncontent= FileUtils.readFileToString(new File("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Pacakge1\\data.json"),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
			
		});		
		return data;
	}

}
