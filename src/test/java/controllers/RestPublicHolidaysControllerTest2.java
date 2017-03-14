//package controllers;
//
//import static org.junit.Assert.fail;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.koena.RestPublicHolidayController;
//import junit.framework.TestCase;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.nio.charset.Charset;
//
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration("classpath:WEB-INF/dispatcher-servlet.xml")
////@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
////@WebAppConfiguration
//public class RestPublicHolidaysControllerTest extends TestCase {
//	
//	private MockMvc mockMvc;
//	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
//			MediaType.APPLICATION_JSON.getType(),
//			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
//	
//	@Autowired
//	private RestPublicHolidayController restPublicHolidayController;
//	
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(restPublicHolidayController).build();
//	}
//	
//	@Test
//	public void shouldFindAll() throws Exception {
//		mockMvc.perform(get("/getData"))
//		.andExpect(status().isOk())
//		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
//		.andExpect(jsonPath("$[0]", is("01Jan")))
//        .andExpect(jsonPath("$[1]", is("16June")))
//        .andExpect(jsonPath("$[2]", is("24Sept")))
//        .andExpect(jsonPath("$[3]", is("16Dec")))
//        .andExpect(jsonPath("$[1]", is("25Dec")));
//	}
//
//}
