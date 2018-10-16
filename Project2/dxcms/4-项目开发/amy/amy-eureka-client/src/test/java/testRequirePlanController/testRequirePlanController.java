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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/requirePlanAddList")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("limit", "1").param("offset", "10")
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void list() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/list")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("pageNumber", "10").param("pageSize", "10").param("order", "")
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void save() throws Exception {
		//空指针
		JSONArray array = new JSONArray();
		JSONObject object =new JSONObject();
		object.accumulate("arriveDate", "2018-08-27");
		object.accumulate("budgetPrice", "8908");
		object.accumulate("budgetQty", "8768");
		object.accumulate("description", "sb");
		object.accumulate("materialName", "物资A0");
		object.accumulate("materialSubArray", "包装物料0");
		object.accumulate("materialUnitName", "单位0");
		object.accumulate("materilaCode", "物资编码1");
		object.accumulate("onwayQty", "878");
		object.accumulate("purchaseQty", "456");
		object.accumulate("purchaserName", "张三");
		object.accumulate("referenceAmount", "359784");
		object.accumulate("referencePrice", "789");
		object.accumulate("requireDate", "2018-08-27");
		object.accumulate("requirePlanid", "物资编码1");
		object.accumulate("requireQty", "25345");
		object.accumulate("reserveQty", "57657");
		object.accumulate("specification", "规格0");
		object.accumulate("stockQty", "47");
		array.add(object);
		System.out.println(array);
		
		String title="asdasd";
		String planNo="1231231231";
		String type="1";
		String purchaseDept="adsas";
		String invoiceDate="2018-9-11";
		String authorUser="adad";
		String createDate="2018-10-11";
		String remark="adsadas";
		mockMvc	.perform(MockMvcRequestBuilders.post(route+"/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("title", title).param("planNo",planNo ).param("type",type ).param("purchaseDept",purchaseDept ).param("invoiceDate",invoiceDate )
				.param("authorUser",authorUser ).param("createDate", createDate).param("remark", remark).param("applyEntryJson", array.toString())
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void update() throws Exception{
		//空指针
				JSONArray array = new JSONArray();
				JSONObject object =new JSONObject();
				object.accumulate("arriveDate", "2018-08-27");
				object.accumulate("budgetPrice", "8908");
				object.accumulate("budgetQty", "8768");
				object.accumulate("description", "sb");
				object.accumulate("materialName", "物资A0");
				object.accumulate("materialSubArray", "包装物料0");
				object.accumulate("materialUnitName", "单位0");
				object.accumulate("materilaCode", "物资编码1");
				object.accumulate("onwayQty", "878");
				object.accumulate("purchaseQty", "456");
				object.accumulate("purchaserName", "张三");
				object.accumulate("referenceAmount", "359784");
				object.accumulate("referencePrice", "789");
				object.accumulate("requireDate", "2018-08-27");
				object.accumulate("requirePlanid", "物资编码1");
				object.accumulate("requireQty", "25345");
				object.accumulate("reserveQty", "57657");
				object.accumulate("specification", "规格0");
				object.accumulate("stockQty", "47");
				array.add(object);
				System.out.println(array);
				
				String title="asdasd";
				String planNo="1231231231";
				String type="1";
				String purchaseDept="adsas";
				String invoiceDate="2018-9-11";
				String authorUser="adad";
				String createDate="2018-10-11";
				String remark="adsadas";
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("title", title).param("planNo",planNo ).param("type",type ).param("purchaseDept",purchaseDept ).param("invoiceDate",invoiceDate )
				.param("authorUser",authorUser ).param("createDate", createDate).param("remark", remark).param("applyEntryJson", array.toString())
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void remove() throws Exception{
		//空指针异常
		mockMvc	.perform(MockMvcRequestBuilders.post(route+"/remove")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("id", "1")
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void submitApproval() throws Exception{
		
		mockMvc	.perform(MockMvcRequestBuilders.post(route+"/submitApproval")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("planNo", "1")
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void withdrawApproval() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.post(route+"/withdrawApproval")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("planNo", "1")
				).andExpect(status().isOk())
				.andReturn();
	}
}
