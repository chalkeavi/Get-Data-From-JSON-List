import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetDataFromServer {

	public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
		getJsonDataFromServer();
	}

	public static String getJsonDataFromServer()
			throws IOException, ParseException, java.text.ParseException, org.json.simple.parser.ParseException {

		String REST_URL = "https://dummy.restapiexample.com/api/v1/employees";
		URL url = new URL(REST_URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		String lines = "";
		try {
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			while ((output = br.readLine()) != null) {
				lines += output;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		ObjectMapper mapper = new ObjectMapper();
		Sample sample = mapper.readValue(lines, Sample.class);
		writeToFile(sample);
		return lines;
	}

	public static void writeToFile(Sample sample) throws IOException {
		FileWriter fWriter = new FileWriter("Result.txt");

		// Writing into the file
		String data1 = "";
		for(Employee emp: sample.getData()) {
			data1 += emp.getEmployee_name() + "\n";	
		}
		fWriter.write(data1);
		fWriter.close();
		System.out.println("The File Generated Successfully");
	}

}

class Sample {
	private String status;
	private List<Employee> data;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Employee> getData() {
		return data;
	}

	public void setData(List<Employee> data) {
		this.data = data;
	}

}

class Employee {
	private Long id;
	private String employee_name;
	private String employee_salary;
	private String employee_age;
	private String profile_image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_salary() {
		return employee_salary;
	}

	public void setEmployee_salary(String employee_salary) {
		this.employee_salary = employee_salary;
	}

	public String getEmployee_age() {
		return employee_age;
	}

	public void setEmployee_age(String employee_age) {
		this.employee_age = employee_age;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

}