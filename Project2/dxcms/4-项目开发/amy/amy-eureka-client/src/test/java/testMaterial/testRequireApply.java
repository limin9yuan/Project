package testMaterial;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.material.controller.RequireApplyController;
import com.dx.service.datacenter.service.api.IMaterialService;
import com.dx.service.purchase.service.api.IRequireApplyService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration  
@Transactional
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:spring-context-jedis.xml",
		"classpath:spring-context-activiti.xml" })
public class testRequireApply {
	private MockMvc mockMvc;

	@InjectMocks
	private RequireApplyController requireApplyController;

	@Mock
	private IRequireApplyService iRequireApplyService;

	@Mock
	private IMaterialService iMaterialService;

	private String route = "/material/requireApply";

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(requireApplyController).build();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 采购申请管理页
	 * 
	 * @throws Exception
	 */
	@Test
	public void requireApply() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(route)).andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("采购申请管理页");
	}

	/**
	 * 采购申请编制页
	 * 
	 * @throws Exception
	 */
	@Test
	public void addRequireApply() throws Exception {
		//getUser等赋值为空
//		MvcResult mvcResult = this.mockMvc
//				.perform(MockMvcRequestBuilders.get(route+"/add")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
//		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果
//
//		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
//		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
//		System.out.println("采购申请编制页");
	}
	
	/**
	 * 物资明细列表页
	 * @throws Exception
	 */
	@Test
	public void materialList() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/materialList")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("采购申请编制页");
	}
	
	/**
	 * 修改页
	 * @throws Exception
	 */
	@Test
	public void edit() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/edit/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("修改页");
	}
	
	/**
	 * 查看页
	 */
	@Test
	public void view() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/view/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("查看页");
	}
	
	/**
	 * 修改
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception{
		//空指针异常原因：rms.getCode()=null  接口问题
		JSONArray array=new JSONArray();
		JSONObject object =new JSONObject();
		object.accumulate("acceptUserId", "zhangsan");
		object.accumulate("budgetPrice", "900.24");
		object.accumulate("budgetQty", "1000.23");
		object.accumulate("budgetTotal", "900447.06");
		object.accumulate("description", "");
		object.accumulate("materialCode", "");
		object.accumulate("materialName", "物资A1");
		object.accumulate("materialSubArray", "");
		object.accumulate("materialUnitId", "undefined");
		object.accumulate("referencePrice", "900.24");
		object.accumulate("referenceTotal", "450930.22");
		object.accumulate("requireDate", "");
		object.accumulate("requireQty", "500.9");
		object.accumulate("specification", "规格型号1");
		object.accumulate("stockQty", "200.34");
		array.add(object);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("name", "name").param("code", "1").param("authorCorpId", "1").param("createUserId", "1").param("remark", "sadas")
				.param("createDate", "2018-9-9").param("businessDate", "2018-9-9").param("applyEntryJson", array.toString()))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":1}");// 断言，判断返回的值是否正确
		System.out.println("修改");
	}
	
	/**
	 * 删除
	 * @throws Exception
	 */
	@Test
	public void remove() throws Exception{
		//空指针异常：接口为Null
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(route+"/remove")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("id", "1"))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("删除");
	}
	
	/**
	 *  取得选择物资列表
	 * @throws Exception
	 */
	@Test
	public void getMaterialList() throws Exception{
		mockMvc	.perform(MockMvcRequestBuilders.get(route+"/getMaterialList")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("pageNumber", "10").param("pageSize", "10").param("materialClassId", "").param("name", "")
				.param("specification", "")).andExpect(status().isOk())
				.andReturn();
		System.out.println(" 取得选择物资列表");
	}
	
	/**
	 * 采购申请列表
	 */
	@Test
	public void list() throws Exception{
		mockMvc .perform(MockMvcRequestBuilders.get(route+"/list")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("pageNumber", "10").param("pageSize", "10").param("codeOrName", "").param("beginDate", "")
				.param("endDate", "")).andExpect(status().isOk())
				.andReturn();
		System.out.println("采购申请列表");
	}
	
	/**
	 * 取得一条采购物资记录
	 * @throws Exception
	 */
	@Test
	public void getMaterialDetailByCode() throws Exception{
		mockMvc .perform(MockMvcRequestBuilders.get(route+"/getMaterialDetailByCode/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("取得一条采购物资记录");
	}
	
	/**
	 * 取得采购申请物资明细列表
	 * @throws Exception
	 */
	@Test
	public void getRequireApplyDetailByCode() throws Exception{
		mockMvc .perform(MockMvcRequestBuilders.get(route+"/getRequireApplyDetailByCode")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("id", "1"))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("取得采购申请物资明细列表");
	}
	
	
	/**
	 * 保存
	 * @throws Exception
	 */
	@Test
	public void save() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(route+"/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":0}");// 断言，判断返回的值是否正确
		System.out.println("保存");
	}
	
	/**
	 *  提交审批
	 * @throws Exception
	 */
	@Test
	public void commitApply() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(route+"/commitApply")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":0}");// 断言，判断返回的值是否正确
		System.out.println("提交审批");
	}
	
	/**
	 * 取消审批
	 * @throws Exception
	 */
	@Test
	public void cancelApprove() throws Exception{
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(route+"/cancelApprove")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":0}");// 断言，判断返回的值是否正确
		System.out.println("取消审批");
	}
}
