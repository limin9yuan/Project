
package org.module.sf.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.module.fc.service.BuildingService;
import org.module.fc.service.CommunityService;
import org.module.fc.service.HouseService;
import org.module.sf.service.ChargeService;


/**
 * Excel数据读取器
 * 
 * @author smile
 * @since 2011-08-27
 */
public class ExcelReaderQf  extends BaseAction{
	private String metaData = null;
	private InputStream is = null;
	private ChargeService chargeService = (ChargeService) super.getService("ChargeService");

	public ExcelReaderQf(){};
	
	/**
	 * 构造函数
	 * @param pMetaData 元数据
	 * @param pIs Excel数据流
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public ExcelReaderQf(String pMetaData, InputStream pIs){
		setIs(pIs);
		setMetaData(pMetaData);
	}
	
	/**
	 * 读取Excel数据
	 * @param pBegin 从第几行开始读数据<br>
	 * <b>注意下标索引从0开始的哦!
	 * @return 以List<BaseDTO>形式返回数据
	 * @throws BiffException
	 * @throws IOException
	 */
	public List read(int pBegin) throws BiffException, IOException{
		List list = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(getIs());
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();
		for (int i = pBegin; i < rows; i++) {
			Dto rowDto = new BaseDto();
			Cell[] cells = sheet.getRow(i);
			for (int j = 0; j < cells.length; j++) {
				String key = getMetaData().trim().split(",")[j];
				if(G4Utils.isNotEmpty(key)) 
					rowDto.put(key, cells[j].getContents());
			}
			list.add(rowDto);
		}
		return list;
	}
	
	/**
	 * 读取Excel数据
	 * @param pBegin 从第几行开始读数据<br> 
	 * <b>注意下标索引从0开始的哦!</b>
	 * @param pBack 工作表末尾减去的行数
	 * @return 以List<BaseDTO>形式返回数据
	 * @throws BiffException
	 * @throws IOException
	 */
	public List read(int pBegin, int pBack) throws BiffException, IOException{
		List list = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(getIs());
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();
		for (int i = pBegin; i < rows - pBack; i++) {
			Dto rowDto = new BaseDto();
			Cell[] cells = sheet.getRow(i);
			String[] arrMeta = getMetaData().trim().split(",");
			for (int j = 0; j < arrMeta.length; j++) {
				String key = arrMeta[j];
				if(G4Utils.isNotEmpty(key)) 
					rowDto.put(key, cells[j].getContents());
			}
			list.add(rowDto);
		}
		return list;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	};
	
	
	
	/**
	 * 将list写入数据库
	 * @param pBegin 从第几行开始读数据<br>
	 * <b>注意下标索引从0开始的哦!
	 * @return 以List<BaseDTO>形式返回数据
	 * @throws BiffException
	 * @throws IOException
	 */
	public void readToFcDatabaseQf(int pBegin,String cid,String operator,String chargeMonth) throws BiffException, IOException{

		List chalist = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(getIs());
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();	
		String[] mdArray = getMetaData().trim().split(",");
		
		System.out.println(chargeMonth);

		                                            
		    String[] result = chargeMonth.split(",");
			for (int i = 1; i < rows; i++) {
				
				Cell[] cells = sheet.getRow(i);
				
				System.out.println(cells[0].getContents()); 
				System.out.println(result[0]); 
				for (int j = 0; j < result.length; j++) {					
					String key = mdArray[j];
					if(G4Utils.isNotEmpty(key)){ 
						System.out.println(cells[0].getContents()); 
					    System.out.println(cells[j].getContents());
					    System.out.println(result[j]);
					    Dto rowDto = new BaseDto();
						rowDto.put(key, cells[j].getContents());
						rowDto.put("excel_row",i+1);
					    rowDto.put("house_code", cells[0].getContents());
				        rowDto.put("charge_month", result[j]);
				        rowDto.put("qf_account", cells[j+1].getContents());
				        rowDto.put("cid", cid);
				        rowDto.put("operator", operator);
				        if(rowDto.get("house_code")!=null 
				        		&& !"".equals((String)rowDto.get("house_code"))
				        		&& cells[j+1].getContents()!=null 
						        && !"".equals(cells[j+1].getContents())	
				        				
				        		){
							System.out.println(rowDto);
							chalist=setCharge(rowDto,chalist);
						}
				        
					}
					
				}
				
			 }
		  
			//System.out.println(s1);
			//System.out.println(s2);
			//System.out.println(s3);
			chalist=null;
		}
	
	
	/**
	 * 将list写入数据库
	 * @param pBegin 从第几行开始读数据<br>
	 * <b>注意下标索引从0开始的哦!
	 * @return 以List<BaseDTO>形式返回数据
	 * @throws BiffException
	 * @throws IOException
	 */
	public void readToFcDatabaseQf1(int pBegin,String cid,String operator,String chargeMonth) throws BiffException, IOException{
		//List comlist = new ArrayList();
		//List builist = new ArrayList();
		//List houlist = new ArrayList();
		
		List chalist = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(getIs());
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();	
		String[] mdArray = getMetaData().trim().split(",");
	
		//String chargeMonth = ImportQfAction.importQfExcel("chargeMonth");
		System.out.println(chargeMonth);
		
		String s1=chargeMonth.toString().substring(0, 4);
		String s2=chargeMonth.toString().substring(5, 9);
		String s3=chargeMonth.toString().substring(chargeMonth.toString().length()-4);
		
			if("2015,2016,2017".equals(chargeMonth)){
			for (int i = pBegin; i < rows; i++) {
				Dto rowDto = new BaseDto();
				Cell[] cells = sheet.getRow(i);
				for (int j = 0; j < cells.length; j++) {
					if(cells.length==2){
					String key = mdArray[j];
					if(G4Utils.isNotEmpty(key)) 
						rowDto.put(key, cells[j].getContents());
				        rowDto.put("chargeMonth", s1); 
				  }
					
					if(cells.length==3){
						String key = mdArray[j];
						if(G4Utils.isNotEmpty(key)) 
							rowDto.put(key, cells[j].getContents());
					        rowDto.put("chargeMonth", s2); 
					  }
					if(cells.length==4){
						String key = mdArray[j];
						if(G4Utils.isNotEmpty(key)) 
							rowDto.put(key, cells[j].getContents());
					        rowDto.put("chargeMonth", s3); 
					  }
				}				
				rowDto.put("excel_row", i+1);
				rowDto.put("cid", cid);
				rowDto.put("operator", operator);
				rowDto.put("area_id", "0");
				//rowDto.put("item_code", "A");
				if(rowDto.get("house_code")!=null && !"".equals((String)rowDto.get("house_code"))){
					chalist=setCharge(rowDto,chalist);
				}
				
			}
		 } 
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			chalist=null;
		}
	
	/*
	 * 欠费的存储处理
	 * rowDto 行记录
	 * comlist 大楼list
	 * */
	private List setCharge(Dto rowDto,List chalist){
		boolean bRet = false;//是否在chalist里已经存在了该房间
		String rowCharge = "";
		String strCharge = "";
		//循环chalist
		for(int i=0;i<chalist.size();i++){
			Dto tdto = (Dto)chalist.get(i);
			rowCharge = (String)rowDto.get("house_code")+(String)rowDto.get("charge_month");
			strCharge = (String)tdto.get("house_code")+(String)tdto.get("charge_month");
			if((rowCharge).equals(strCharge)){
				bRet = true;
				rowDto.put("error", "导入欠费房间编号重复!");
				chargeService.importErrorQf(rowDto);
				System.out.print("欠费房间已存在"+rowCharge);
				break;
			}
		}
		//如果不存在 则添加进去
		if(!bRet){
			chalist.add(rowDto);
			System.out.print("添加新收费"+rowCharge);
			Dto outdto = chargeService.insertQf(rowDto);
			if((Boolean)outdto.get("success")!=true){
				rowDto.put("error",(String)outdto.get("error"));
				chargeService.importErrorQf(rowDto);
			}
		}
		return chalist;
	}
	
	
	
}

