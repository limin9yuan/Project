package com.bootdo.common.controller;

import com.bootdo.common.config.BootdoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
@Controller
@RequestMapping("/common/downloadFile")
public class downloadFileController extends BaseController {
    @Autowired
    private BootdoConfig bootdoConfig;
    // 模板下载
    @ResponseBody
    @RequestMapping("/downloadFile")
    public void download(HttpServletResponse response, HttpServletRequest request) {
        try {

            // File files = new
            // File(".\\src\\main\\resources\\downloadTemplate\\企业客户导入摸板.xls");
            // System.out.println("getAbsolutePath:"+files.getAbsolutePath());
            // //getAbsolutePath()会将.认为是一个以.命名的文件
            // System.out.println("getCanonicalPath:"+files.getCanonicalPath());//getCanonicalPath()得到的是一个规范路径没有.
            //
            String downloadPath = bootdoConfig.getDownloadPath();
            String fileName = request.getParameter("fileName");
            File file = new File(downloadPath+"/"+fileName);
            // 取得文件名。
            String filename = file.getName();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getCanonicalPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            // 设置文件名
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));
            // 设置文件打下
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
