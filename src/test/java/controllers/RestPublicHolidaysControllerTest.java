package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.koena.controller.RestPublicHolidayController;
import com.koena.dao.PublicHolidaysDAO;
import com.koena.model.PublicHolidaysEntity;

public class RestPublicHolidaysControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	PublicHolidaysDAO publicHolidaysDAO;
	
	@InjectMocks
	RestPublicHolidayController restPublicHolidayController;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(restPublicHolidayController)
                .build();
    }
	
	@Test
	public void test_getPublicHolidays() throws Exception
	{
		List<PublicHolidaysEntity> publicHolidaysEntities= Arrays.asList(
				new PublicHolidaysEntity("25", "Dec"),
				new PublicHolidaysEntity("01", "Jan"));
		when(publicHolidaysDAO.getAllHolidays()).thenReturn(publicHolidaysEntities);
		mockMvc.perform(get("/getData2"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].day", is("25")))
            .andExpect(jsonPath("$[0].month", is("Dec")))
            .andExpect(jsonPath("$[1].day", is("01")))
            .andExpect(jsonPath("$[1].month", is("Jan")));
		
		verify(publicHolidaysDAO, times(1)).getAllHolidays();
		verifyNoMoreInteractions(publicHolidaysDAO);
	}
	
	@Test
	public void test_getPublicHoliday_byId_success() throws Exception {
		PublicHolidaysEntity publicHolidayEntity = new PublicHolidaysEntity("16", "June");
		when(publicHolidaysDAO.findByPk(1)).thenReturn(publicHolidayEntity);
		mockMvc.perform(get("/getData2/{pk}", 1))
			.andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.day", is("16")))
	        .andExpect(jsonPath("$.month", is("June")));
		verify(publicHolidaysDAO, times(1)).findByPk(1);
	    verifyNoMoreInteractions(publicHolidaysDAO);
		
	}
	
	@Test
	public void test_getPublicHoliday_byId_fail_404NotFount() throws Exception {
		when(publicHolidaysDAO.findByPk(1)).thenReturn(null);
		mockMvc.perform(get("/getData2/{pk}", 1))
			.andExpect(status().isNotFound());
		verify(publicHolidaysDAO, times(1)).findByPk(1);
	    verifyNoMoreInteractions(publicHolidaysDAO);
	}
	
	@Test
	public void test_createPublicHoliday_success() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(new PublicHolidaysEntity("31", "Jan"));
		
		PublicHolidaysEntity publicHolidaysEntity = new PublicHolidaysEntity("31", "Jan");
		when(publicHolidaysDAO.exists(publicHolidaysEntity)).thenReturn(false);
		doNothing().when(publicHolidaysDAO).addPublicHoliday(publicHolidaysEntity);
		mockMvc.perform(
				post("/create2")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.day").doesNotExist())
		        .andExpect(jsonPath("$.month").doesNotExist());
		
				verify(publicHolidaysDAO, times(1)).exists(any(PublicHolidaysEntity.class));
			    verify(publicHolidaysDAO, times(1)).addPublicHoliday(any(PublicHolidaysEntity.class));
			    verifyNoMoreInteractions(publicHolidaysDAO);
	}
	
	@Test
	public void test_createPublicHoliday_fail_404NotFound() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(new PublicHolidaysEntity("31", "Jan"));
		
		PublicHolidaysEntity publicHolidaysEntity = new PublicHolidaysEntity("31", "Jan");
		when(publicHolidaysDAO.exists(eq(publicHolidaysEntity))).thenReturn(true);
			mockMvc.perform(
					post("/create2")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isConflict());
			verify(publicHolidaysDAO, times(1)).exists(eq(publicHolidaysEntity));
		    verifyNoMoreInteractions(publicHolidaysDAO);
			
	}
	
}
