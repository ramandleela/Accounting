package accounting;

// Java
import java.util.Arrays;

// Spring
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

//static methods for mocking HTTP request and response
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

/* Annotations
	@RunWith - Junit4ClassRunner class loads Spring application context
	           for use in a JUnit test and enables autowiring of beans into test class
	@SpringApplicationConfiguration comprises of
		@ContextCofiguration - Loads Spring Application Context in MvcApplication class
		                       in the same as if it was being loaded in a Prod application
	@WebAppConfiguration - Enables web context testing by declaring application context created
	                       by JUnit should a WebApplicationContext not ApplicationContext

*/
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration

public class TestService {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
    /*
    	Inject WebApplication Context
    */
    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

	/*
	   This method should executed Before any test methods
    */
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	// hEath Check
	@Test
	public void homePage() throws Exception {
	    //// Performs a request and returns ResultActions class to chain further actions, such as asserting expectations, on the result.
		mockMvc.perform(get("/healthcheck")) 
			    .andExpect(status().isOk())
				;
	}

}


