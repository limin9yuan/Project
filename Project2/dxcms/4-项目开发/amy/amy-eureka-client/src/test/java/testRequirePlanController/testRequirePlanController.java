package testRequirePlanController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.material.controller.RequirePlanController;
import com.dx.service.purchase.service.api.IRequirePlanService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration  
@Transactional
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:spring-context-jedis.xml",
		"classpath:spring-context-activiti.xml" })
public class testRequirePlanController {
	private MockMvc mockMvc;

	@InjectMocks
	private RequirePlanController requirePlanController; 
	
	@Mock
	private IRequirePlanService requirePlanService;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(requirePlanController).build();
		MockitoAnnotations.initMocks(this);
	}
	
	private String route ="/requirementPlan/requirementPlan";
	
	@Test
	public void nextStep() throws Exception {
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/nextStep/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void check() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/check/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void check_ajax() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/check_ajax/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void edit() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/edit/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void edit_ajax() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/edit_ajax/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void getMaterialDetailByCode() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/getMaterialDetailByCode/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void getRequirePlanDetail() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/getRequirePlanDetail")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("code", "2")
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void requirePlanAddList() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/getRequirePlanDetail")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("limit", "").param("offset", "")
				).andExpect(status().isOk())
				.andReturn();
	}
}
