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
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(contractController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void ContractCreation() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/ContractCreation/ContractCreation").param("userName", "admin").param("userId", "1")
						.param("deptName", "研发部").param("deptId", "6").accept(MediaType.TEXT_HTML_VALUE))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		int status = mvcResult.getResponse().getStatus(); // 得到返回代码
		String content = mvcResult.getResponse().getContentAsString(); // 得到返回结果

		Assert.assertEquals(200, status); // 断言，判断返回代码是否正确
		Assert.assertEquals(content, "");// 断言，判断返回的值是否正确
		System.out.println("首次加载数据ContractCreation()方法");
	}

	/**
	 * standAlone方式，Service DAO都没有注入bean，不能进行和Service有关的测试.
	 * 列表测试程序
	 * @throws Exception
	 */
	@Test
	public void listData() throws Exception {
		mockMvc.perform(get("/ContractCreation/ContractCreation/listData") // 请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
				.param("pageNumber", "10").param("pageSize", "10").param("order", "").param("contractCode", "")
				.param("contractCode", "").param("projectId", "").param("contractName", "").param("dateFrom", "")
				.param("dateTo", "") // 添加参数
		).andExpect(status().isOk()) // 返回的状态是200
				// .andDo(print()) //打印出请求和相应的内容
				.andReturn(); // 将相应的数据转换为字符串
		System.out.println("--------列表测试程序-------- ");
	}

	@Test
	public void getJson() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/getJson").param("message", "nothing to show")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				// jsonPath refer to https://github.com/json-path/JsonPath.
				.andExpect(MockMvcResultMatchers.jsonPath(".message").value("nothing to show")).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}

}