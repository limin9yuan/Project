package com.dx.server.dcservice.service;

import com.alibaba.fastjson.JSON;
import com.dx.client.model.datacenter.MaterialBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Auther: DX01
 * @Date: 2018/8/29 23:31
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestMaterialService {
    private static final Log log = LogFactory.getLog(TestMaterialService.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save() throws Exception {
        String url="/materialService/save";
        MaterialBean model = new MaterialBean();
        model.setCode("1");

        this.mockMvc
                .perform(
                        post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(model))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
