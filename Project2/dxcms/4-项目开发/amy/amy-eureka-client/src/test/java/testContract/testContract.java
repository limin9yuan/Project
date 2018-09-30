package testContract;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootdo.contract.controller.ContractController;
import com.dx.client.model.contract.ContractBean;
import com.dx.service.contract.service.api.IContractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

import io.swagger.models.Model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:spring-context-jedis.xml",
		"classpath:spring-context-activiti.xml" })
public class testContract {
	private MockMvc mockMvc;

	@InjectMocks
	private ContractController contractController;

	@Mock
	private IContractService iContractService;
	
	public static String route="/ContractCreation/ContractCreation";
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(contractController).build();
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void ContractCreation() throws Exception {
//		MvcResult mvcResult = mockMvc
//				.perform(get("/ContractCreation/ContractCreation"))
//				.andDo(MockMvcResultHandlers.print())
//				.andReturn();
//		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
//		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果
//
//		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
//		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("首次加载数据ContractCreation()方法");
	}
	
	/**
	 * standAlone方式，Service DAO都没有注入bean，不能进行和Service有关的测试.
	 * 列表测试程序
	 * @throws Exception
	 */
	@Test
	public void listData() throws Exception {
		mockMvc.perform(get(route+"/listData") // 请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
				.param("pageNumber", "10").param("pageSize", "10").param("order", "").param("contractCode", "")
				.param("contractCode", "").param("projectId", "").param("contractName", "").param("dateFrom", "")
				.param("dateTo", "") // 添加参数
		).andExpect(status().isOk()) // 返回的状态是200
				// .andDo(print()) //打印出请求和相应的内容
				.andReturn(); // 将相应的数据转换为字符串
		System.out.println("--------列表测试程序-------- ");
	}
	/**
	 * 修改数据
	 * @throws Exception
	 */
	@Test
	public void edit() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/edit/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("测试修改数据");
	}
	
	/**
	 * 查看数据
	 * @throws Exception
	 */
	@Test
	public void see() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(route+"/see/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("测试查看数据");
	}

	/**
	 *  删除测试
	 * @throws Exception
	 */
	@Test
	public void remove() throws Exception {
		//报空指针是因删除接口为null所以为空指针，将接口注掉即可
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(route+"/remove")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("id", "1"))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":0}");// 断言，判断返回的值是否正确
		System.out.println("测试删除数据");
	}
	
	/**
	 * 修改测试
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception {
	//不通过原因:接口为空指针所以报错
		String contractMaterialBeans="[{\"id\":null,\"contractId\":null,\"suitCorpId\":\"沈阳工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"3001\",\"materialName\":\"干啤纸箱\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":12,\"qty\":100,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false},{\"id\":null,\"contractId\":null,\"suitCorpId\":\"沈阳工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"bz001\",\"materialName\":\"缠膜\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":0.2,\"qty\":100,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"3001\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false},{\"id\":null,\"contractId\":null,\"suitCorpId\":\"沈阳工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"bz002\",\"materialName\":\"托盘\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":0.1,\"qty\":100,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"3001\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false},{\"id\":null,\"contractId\":null,\"suitCorpId\":\"沈阳工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"3002\",\"materialName\":\"干啤纸箱\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":12,\"qty\":100,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false},{\"id\":null,\"contractId\":null,\"suitCorpId\":\"沈阳工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"bz003\",\"materialName\":\"运费\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":0.5,\"qty\":100,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"3002\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false},{\"id\":null,\"contractId\":null,\"suitCorpId\":\"大连工厂\",\"suitCorpName\":null,\"materialId\":null,\"materilaCode\":\"3003\",\"materialName\":\"载具\",\"materialUnitName\":null,\"specification\":null,\"texture\":null,\"price\":5200,\"qty\":10,\"qtyFrom\":null,\"qtyTo\":null,\"dateFrom\":null,\"dateTo\":null,\"parentId\":\"\",\"createDate\":null,\"description\":null,\"remark\":\"\",\"actived\":false}]";
		String signupForm="[{\"name\":\"id\",\"value\":\"null\"},{\"name\":\"authorDeptId\",\"value\":\"\"},{\"name\":\"authorUserId\",\"value\":\"\"},{\"name\":\"projectId\",\"value\":\"\"},{\"name\":\"deliverCompanyId\",\"value\":\"\"},{\"name\":\"suitCorpId\",\"value\":\"6\"},{\"name\":\"authorCorpId\",\"value\":\"6\"},{\"name\":\"performUserId\",\"value\":\"135\"},{\"name\":\"contractName\",\"value\":\"合同名称\"},{\"name\":\"contractCode\",\"value\":\"123\"},{\"name\":\"projectName\",\"value\":\"项目名称\"},{\"name\":\"bidSchemeId\",\"value\":\"1\"},{\"name\":\"currencyTypeId\",\"value\":\"euro\"},{\"name\":\"taxRate\",\"value\":\"1\"},{\"name\":\"performanceBond\",\"value\":\"123456\"},{\"name\":\"warrantyBond\",\"value\":\"123456\"},{\"name\":\"timeMin\",\"value\":\"2018-09-11\"},{\"name\":\"timeMax\",\"value\":\"2018-09-11\"},{\"name\":\"totalMoney\",\"value\":\"54480\"},{\"name\":\"suitCorpId\",\"value\":\"研发部\"},{\"name\":\"suitCorpId\",\"value\":\"6\"},{\"name\":\"deliverCompanyName\",\"value\":\"供货公司0\"},{\"name\":\"authorCorpId\",\"value\":\"研发部\"},{\"name\":\"authorCorpId\",\"value\":\"6\"},{\"name\":\"authorDeptId\",\"value\":\"研发部\"},{\"name\":\"authorDeptId\",\"value\":\"6\"},{\"name\":\"performUserId\",\"value\":\"王健林\"},{\"name\":\"performUserId\",\"value\":\"135\"},{\"name\":\"performUserId\",\"value\":\"admin\"},{\"name\":\"checkbox\",\"value\":\"1\"},{\"name\":\"abstract\",\"value\":\"asdfa\"}]";
		String jsonCompanyName="[{\"deliverCompanyId\":\"1234\",\"deliverCompanyName\":\"供货公司4\"},{\"deliverCompanyId\":\"1235\",\"deliverCompanyName\":\"供货公司5\"},{\"deliverCompanyId\":\"\",\"deliverCompanyName\":\"\"}]";
		String richTextKVJson= "[{\"key\":\"text-input1\",\"value\":\"设备\"},{\"key\":\"text-input2\",\"value\":\"合同编号:\"},{\"key\":\"text-input3\",\"value\":\"华润雪花啤酒（\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t）有限公司\"},{\"key\":\"text-input4\",\"value\":\"法定代表人或负责人：\"},{\"key\":\"text-input5\",\"value\":\"\"},{\"key\":\"text-input6\",\"value\":\"\"},{\"key\":\"text-input7\",\"value\":\" \"}]";
		String fileInformation="[{\"url\":\"/files/一键清除maven仓库中下载失败的jar包.bat\",\"typeId\":99,\"createDate\":\"2018-09-26 09:37:57\"}]";
		String htmlText="\"\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t 设备定作合同 合同编号:定作方:  华润雪花啤酒（\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t）有限公司（以下简称甲方）注册地址:  法定代表人或负责人：经办人:  业务电话:  承揽方:   （以下简称乙方）\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\"";
		String jsonSuitBeans="[{\"deliverCompanyId\":\"13\",\"deliverCompanyName\":\"测试部\"}]";
		
		JSONObject object=new JSONObject();
		object.accumulate("htmlText", htmlText);
		object.accumulate("contractId", "1");
		object.accumulate("contractMaterialBeans",contractMaterialBeans);
		object.accumulate("signupForm", signupForm);
		object.accumulate("fileInformation", fileInformation);
		object.accumulate("jsonCompanyName", jsonCompanyName);
		object.accumulate("richTextKVJson", richTextKVJson);
		object.accumulate("jsonSuitBeans", jsonSuitBeans);
		JSONArray array=new JSONArray();
		array.add(object);
		MvcResult mvcResult = this.mockMvc
				.perform(post(route+"/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("contractMaterialBeans",contractMaterialBeans).param("signupForm",signupForm).param("jsonCompanyName",jsonCompanyName).param("richTextKVJson",richTextKVJson)
				.param("fileInformation",fileInformation).param("htmlText",htmlText).param("jsonSuitBeans",jsonSuitBeans).param("contractId", "1"))
				.andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "{\"msg\":\"操作成功\",\"code\":0}");// 断言，判断返回的值是否正确
		System.out.println("测试修改数据");
	}
	
	/**
	 *  项目名称弹出列表数据
	 * @throws Exception
	 */
	@Test
	public void projectList() throws Exception{
		mockMvc.perform(get(route+"/projectList")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("pageNumber", "10").param("pageSize", "10").param("order", "").param("projectId", "")
				.param("projectName", ""))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("项目页面测试程序");
	}
	
	/**
	 *  供货公司弹出页面数据
	 * @throws Exception
	 */
	@Test
	public void contractDeliversList() throws Exception{
		//ResultMsg空指针
		mockMvc.perform(get(route+"/contractDeliversList")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("pageNumber", "10").param("pageSize", "10").param("order", "")
				.param("deliverCompanyName", "").param("deliverCompanyId", ""))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("供货公司弹出页面数据页面测试程序");
	}
	
	/**
	 * 合同物料数据
	 * @throws Exception
	 */
	@Test
	public void materila() throws Exception{
		//ResultMsg空指针
		mockMvc.perform(get(route+"/materila/1")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) )
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("供货公司弹出页面数据页面测试程序");
	}
	
	/**
	 * 根据id文件显示测试
	 * @throws Exception
	 */
	@Test
	public void listId() throws Exception {
		//空指针   接口为null
		mockMvc.perform(get(route+"/listId")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "1") )
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("根据ID查看附件列表测试程序");
	}
	
	/**
	 * 文件上传测试
	 * @throws Exception
	 */
	@Test
	public void upload() throws Exception{
		try {
			String result =mockMvc.perform(MockMvcRequestBuilders.fileUpload(route+"/upload")
					.file( new MockMultipartFile("file", "u205.png", ",multipart/form-data", "hello upload".getBytes("UTF-8")))
					).andExpect(MockMvcResultMatchers.status().isOk())
				      .andReturn().getResponse().getContentAsString();
		      System.out.println(result);
					
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件名称下载测试
	 * @throws Exception
	 */
	@Test
	public void down() throws Exception{
		mockMvc.perform(get(route+"/down")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("fileName", "u205.png") )
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("根据文件名称下载测试程序");
	}
}