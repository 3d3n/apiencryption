package com.example.apiencryption;

import com.example.apiencryption.util.Security;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ApiEncryptionApplicationTests extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test //POST
	public void test1() throws Exception {
		String uri = "/sendencdata";
        String r = "{\n" +
                "    \"name\": \"foo\",\n" +
                "    \"id\": \"01\"\n" +
                "}";
//        String inputJson = super.mapToJson(r);
        String encdata = Security.encrypt(r);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(encdata)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
        content = content.replace("\"", "");
        System.out.println(Security.decrypt(content)); //{"success":true}
//		assertEquals(content, "Product is created successfully");
	}
}
