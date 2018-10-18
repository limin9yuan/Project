package org.eredlab.g4.rif.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.properties.PropertiesFactory;
import org.eredlab.g4.ccl.properties.PropertiesFile;
import org.eredlab.g4.ccl.properties.PropertiesHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.springframework.context.ApplicationContext;

import sun.misc.BASE64Encoder;

/**
 * 系统启动监听器
 * 
 * @author XiongChun
 * @since 2010-04-13
 */
public class SystemInitListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(SystemInitListener.class);
	private boolean success = true;
	private ApplicationContext wac = null;

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		systemStartup(sce.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	private void systemStartup(ServletContext servletContext) {
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
		String forceLoad = pHelper.getValue("forceLoad", ArmConstants.FORCELOAD_N);
		long start = System.currentTimeMillis();
		if (forceLoad.equalsIgnoreCase(ArmConstants.FORCELOAD_N)) {
			System.out.println("********************************************");
			System.out.println("立信供热管理信息系统[eRedG4]开始启动...");
			System.out.println("********************************************");
		}
		try {
			wac = SpringBeanLoader.getApplicationContext();
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		if (success) {
			MonitorService monitorService = (MonitorService) SpringBeanLoader.getSpringBean("monitorService");
			monitorService.deleteHttpSession(new BaseDto());
			try {
				initDbType();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success) {
			InetAddress ia; 
			try {
				ia = InetAddress.getLocalHost();
				String v = getLocalMac(ia);
				v = EncoderByMd5(v);
				System.out.println("********"+v );
				/*if(!"9z/Kln2uWGanEf3HGjneTA==".equals(v)){//长治// 新服务器 0C-C4-7A-57-00-B2
					return;
				}*/
				/*if(!"uvQ6b6AeTchxrFACaneBHA==".equals(v)){//长治// 新服务器 0C-C4-7A-57-00-B2
					return;
				}*/
				/*if(!"hM1OiGxOmkes61DuMcxgZA==".equals(v)){//长治// 0C-C4-7A-57-00-B2
					return;
				}*/
				/*if(!"CaNO3SdZw7Fc81IAJJpCzg==".equals(v)){//平顺//A0-36-9F-A3-43-58
					return;
				}*/
				/*if(!"5igMFlCMv5Dnk8ldl2xMbw==".equals(v)){//安源// 30-B4-9E-99-8E-FE   0C-C4-7A-56-FE-21
					return;
				}*/
				/*if(!"zlOJJ2Mlj3HTAFyMvdi6pQ==".equals(v)){//长治新上系统// 08-94-EF-3D-26-57   08-94-EF-3D-26-56
					return;
				}*/
				
				
				
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("-------------------------------");
			System.out.println("系统开始启动字典装载程序...");
			System.out.println("开始加载字典...");
			IReader g4Reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
			List codeList = null;
			try {
				codeList = g4Reader.queryForList("getCodeViewList");
				System.out.println("字典加载成功!");
			} catch (Exception e) {
				success = false;
				System.out.println("字典加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EACODELIST", codeList);
		}
		if (success) {
			System.out.println("-------------------------------");
			System.out.println("系统开始启动全局参数表装载程序...");
			System.out.println("开始加载全局参数表...");
			List paramList = null;
			try {
				IReader g4Reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
				paramList = g4Reader.queryForList("getParamList");
				System.out.println("全局参数表加载成功!");
			} catch (Exception e) {
				success = false;
				System.out.println("全局参数表加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EAPARAMLIST", paramList);
		}
		long timeSec = (System.currentTimeMillis() - start) / 1000;
		System.out.println("********************************************");
		if (success) {
			System.out.println("立信供热管理信息系统启动成功[" + G4Utils.getCurrentTime() + "]");
			System.out.println("启动总耗时: " + timeSec / 60 + "分 " + timeSec % 60 + "秒 ");
		} else {
			System.out.println("立信供热管理信息系统启动失败[" + G4Utils.getCurrentTime() + "]");
			System.out.println("启动总耗时: " + timeSec / 60 + "分" + timeSec % 60 + "秒");
		}
		System.out.println("********************************************");
	}

	/**
	 * 识别JDBC驱动类型
	 * 
	 * @throws SQLException
	 */
	private void initDbType() throws SQLException {
		IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		Connection connection = g4Dao.getConnection();
		if (connection.getMetaData().getDatabaseProductName().toLowerCase().indexOf("ora") > -1) {
			System.setProperty("eRedg4.JdbcType", "oracle");
		} else if (connection.getMetaData().getDatabaseProductName().toLowerCase().indexOf("mysql") > -1) {
			System.setProperty("eRedg4.JdbcType", "mysql");
		} else {
			if (log.isErrorEnabled()) {
				log.error(GlobalConstants.Exception_Head + "G4平台目前还不支持你使用的数据库产品.如需获得支持,请和我们联系!");
			}
			System.exit(0);
		}
	}
	private static String getLocalMac(InetAddress ia) throws SocketException {
		// TODO Auto-generated method stub
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		//System.out.println(sb.toString().toUpperCase());
		return sb.toString().toUpperCase();
	}
	
  public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
