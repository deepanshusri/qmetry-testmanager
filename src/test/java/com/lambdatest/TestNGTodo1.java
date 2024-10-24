package com.lambdatest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo1 {

    private RemoteWebDriver driver;
    private String testStatus;

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "deepanshulambdatest";
        String accessKey = "SMKwpdntRhBpUbdLrUqk0tU0TjAZquV9kz0YxnOWUo56EaqtQw";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("platform", "windows 11");
        ltOptions.put("browserName", "chrome");
        ltOptions.put("version", "125");
        ltOptions.put("build", "Qmetry test case build");
        ltOptions.put("console", true);
        ltOptions.put("visual", true);
        ltOptions.put("network", true);
        ltOptions.put("name", "SCRUM-TC-4");
        ltOptions.put("w3c", true);
        capabilities.setCapability("lt:options", ltOptions);
        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + hub), capabilities);
    }

    @Test
    public void basicTest() {
        try
        {
            driver.get("https://lambdatest.com/");
            testStatus = "passed";
        } catch (Exception e)
        {
            System.out.println("Exception occurred "+e);
            testStatus = "failed";
        }

    }

    private String getGETApiResponse(String apiUrl, String requestPropertyKey, String requestPropertyValue) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty(requestPropertyKey, requestPropertyValue);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    private String getPOSTApiResponse(String apiUrl, String requestBody) throws IOException {
        URL url = new URL(apiUrl);
        String apiKey = "cd9a7315357f0e8f6866ba50515928617182c4d8f58fec0b1397c8534c93537f73bf8fad9d0be32f4f6d07d26502f6949780e00365547ccfc7dfc56b33d3382d616a1443aa0cc38531566209d5c33d75";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apiKey", apiKey);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    errorResponse.append(inputLine);
                }
                throw new IOException("Error: " + errorResponse.toString());
            }
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    private void getPUTApiResponse(String apiUrl, BigInteger dynamicId) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("executionResultId", dynamicId);
        String requestBody = jsonBody.toString();

        String apiKey = "cd9a7315357f0e8f6866ba50515928617182c4d8f58fec0b1397c8534c93537f73bf8fad9d0be32f4f6d07d26502f6949780e00365547ccfc7dfc56b33d3382d616a1443aa0cc38531566209d5c33d75";

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apiKey", apiKey);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
    }

    private String getAddExecutionResultResponse(String apiUrl, String userTestResultStatus) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("color", userTestResultStatus.equals("passed") ? "#BAB86C" : "#800000");
        jsonBody.put("name", userTestResultStatus);
        jsonBody.put("description", "Test result updated according to the USER's test case result");
        String requestBody = jsonBody.toString();

        String apiKey = "cd9a7315357f0e8f6866ba50515928617182c4d8f58fec0b1397c8534c93537f73bf8fad9d0be32f4f6d07d26502f6949780e00365547ccfc7dfc56b33d3382d616a1443aa0cc38531566209d5c33d75";
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apiKey", apiKey);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        BufferedReader in;
        if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private Map<String, String> getStatusIndFromJsonResponse(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        String testName =  dataObject.optString("name", "unknown");
        String status =  dataObject.optString("status_ind", "unknown");
        // Create a map to hold the results
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("testName", testName);
        resultMap.put("status", status);

        return resultMap; // Return the map
    }

    private BigInteger getExecutionResultUsersStatus(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        return jsonObject.optBigInteger("id", BigInteger.valueOf(0));
    }

    private JSONArray getSearchTestCycleResponse(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        return jsonObject.getJSONArray("data");
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.executeScript("lambda-status=" + testStatus);
                SessionId session = driver.getSessionId();
                String sessionApiUrl = "https://api.lambdatest.com/automation/api/v1/sessions/" + session;
                String authKey = "Basic ZGVlcGFuc2h1bGFtYmRhdGVzdDpTTUt3cGRudFJoQnBVYmRMclVxazB0VTBUakFacXVWOWt6MFl4bk9XVW81NkVhcXRRdw==";
                String response = getGETApiResponse(sessionApiUrl, "Authorization", authKey);
                Map<String, String> result = getStatusIndFromJsonResponse(response);
                String userTestResultStatus = result.get("status");
                String userTestResultName = result.get("testName");

                // POST API for getting test cycle
                String searchTestCycleApiUrl = "https://qtmcloud.qmetry.com/rest/api/latest/testcycles/search/";
                String searchTestCycleResponse = getPOSTApiResponse(searchTestCycleApiUrl, "{\"filter\":{\"projectId\":10000}}");
                JSONArray searchTestCycleResult = getSearchTestCycleResponse(searchTestCycleResponse);
                String testCycleId = "";
                for (int i = 0; i < searchTestCycleResult.length(); i++) {
                    JSONObject obj = searchTestCycleResult.getJSONObject(i);
                    String key = obj.getString("key");
                    if ("SCRUM-TR-9".equals(key)) {
                        String id = obj.getString("id");
                        testCycleId = id;
                        break;
                    }
                }

                // POST API for get linked test cases of test cycle
                String getLinkedTestCasesApiUrl = "https://qtmcloud.qmetry.com/rest/api/latest/testcycles/" + testCycleId + "/testcases/search/";
                String linkedTestCasesResponse = getPOSTApiResponse(getLinkedTestCasesApiUrl, "{\n" +
                        "    \"filter\": {\n" +
                        "        \"status\": [\n" +
                        "            \"In Progress\",\n" +
                        "            \"To Do\",\n" +
                        "            \"Passed\",\n" +
                        "            \"Failed\",\n" +
                        "            \"Blocked\",\n" +
                        "            \"Not Executed\"\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}");

                JSONArray linkedTestCasesResult = getSearchTestCycleResponse(linkedTestCasesResponse); // array of objects of testCases ; [{key: "TC-1", testCaseExecutionId: 1425552, name: 'hello'}, {key: "TC-2", testCaseExecutionId: 636667673, name: 'hvsd'}, {key: "TC-3", testCaseExecutionId: 77787878, name: 'bkbvk'}]
                BigInteger testCaseExecutionId = BigInteger.valueOf(0);
                for (int i = 0; i < linkedTestCasesResult.length(); i++) { // iterating through test cases i.e objects
                    JSONObject obj = linkedTestCasesResult.getJSONObject(i);
                    String key = obj.getString("key");
                    if (userTestResultName.equals(key)) { // fetching the specific object of test case whose "key" matches with userTestResultName
                        BigInteger id = BigInteger.valueOf(obj.getLong("testCaseExecutionId")); // fetching the "testCaseExecutionId" from the object of that particular test case that matched above
                        System.out.println("----------LT  id-----------" + id);
                        testCaseExecutionId = id; // assigning the fetched "testCaseExecutionId" into a string varible named testCaseExecutionId
                        break;
                    }
                }
                //Here we are fetching the individual test case after mapping the key of test case with test name capability.

                // GET execution result
                String getExecutionResultApiUrl = "https://qtmcloud.qmetry.com/rest/api/latest/projects/10000/execution-results";
                String apiKey = "cd9a7315357f0e8f6866ba50515928617182c4d8f58fec0b1397c8534c93537f73bf8fad9d0be32f4f6d07d26502f6949780e00365547ccfc7dfc56b33d3382d616a1443aa0cc38531566209d5c33d75";
                String getExecutionResultResponse = getGETApiResponse(getExecutionResultApiUrl, "apiKey", apiKey);
                JSONArray jsonArray = new JSONArray(getExecutionResultResponse);
                BigInteger executionResultId = BigInteger.valueOf(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject executionResultResponseObj = jsonArray.getJSONObject(i); // get the individual obj
                    String name = executionResultResponseObj.getString("name");
                    if (userTestResultStatus.equals(name)) {
                        executionResultId = executionResultResponseObj.getBigInteger("id");
                        break;
                    }
                }
                if (executionResultId.equals(BigInteger.valueOf(0))) {
                    String addExecutionResultApiUrl = "https://qtmcloud.qmetry.com/rest/api/latest/projects/10000/execution-results";
                    String addExecutionResultResponse = getAddExecutionResultResponse(addExecutionResultApiUrl, userTestResultStatus);
                    executionResultId = getExecutionResultUsersStatus(addExecutionResultResponse);
                }

                String updateTestCaseExecutionApiUrl = "https://qtmcloud.qmetry.com/rest/api/latest/testcycles/" + testCycleId + "/testcase-executions/" + testCaseExecutionId;
                getPUTApiResponse(updateTestCaseExecutionApiUrl, executionResultId);
            }
        } catch (Exception e) {
            System.out.println("----------EXCEPTIONS-----------" + e);
        } finally {
            if (driver != null) {
                driver.quit(); // Ensure driver quits
            }
        }
    }
}
