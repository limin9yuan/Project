package org.module.fc.excel;

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

/**
 * Excel数据读取器
 * 
 * @author smile
 * @since 2011-08-27
 */
public class ExcelReaderFamen  extends BaseAction{
	private String metaData = null;
	private InputStream is = null;
	private HouseService houseService = (HouseService) super.getService("HouseService");
	public ExcelReaderFamen(){};
	
	/**
	 * 构造函数
	 * @param pMetaData 元数据
	 * @param pIs Excel数据流
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public ExcelReaderFamen(String pMetaData, InputStream pIs){
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
	public void readToFcDatabaseFamen(int pBegin,String cid,String operator) throws BiffException, IOException{
		//List comlist = new ArrayList();
		//List builist = new ArrayList();
		List houlist = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(getIs());
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();	
		String[] mdArray = getMetaData().trim().split(",");
		for (int i = pBegin; i < rows; i++) {
			Dto rowDto = new BaseDto();
			Cell[] cells = sheet.getRow(i);
			for (int j = 0; j < cells.length; j++) {
				String key = mdArray[j];
				if(G4Utils.isNotEmpty(key)) 
					rowDto.put(key, cells[j].getContents());
			}
			rowDto.put("excel_row", i+1);
			rowDto.put("cid", cid);
			rowDto.put("operator", operator);
			//rowDto.put("area_id", "0");
			//rowDto.put("item_code", "A");
			if(rowDto.get("house_code")!=null && !"".equals((String)rowDto.get("house_code"))){	
				houlist=setHouseFamen(rowDto,houlist);
			}
			
		}
		houlist=null;
	}

	/*
	 * 用户的存储处理
	 * rowDto 行记录
	 * comlist 大楼list
	 * */
	private List setHouseFamen(Dto rowDto,List houlist){
		boolean bRet = false;//是否在builist里已经存在了该大楼 fasle否 true是
		String rowHouse = "";
		String strHouse = "";
		//循环builist
		for(int i=0;i<houlist.size();i++){
			Dto tdto = (Dto)houlist.get(i);
			rowHouse = (String)rowDto.get("house_code")+(String)rowDto.get("valvecode");
			strHouse = (String)tdto.get("house_code")+(String)tdto.get("valvecode");
			if((rowHouse).equals(strHouse)){
				bRet = true;
				rowDto.put("error", "导入阀门号的房间编号已存在!");
				houseService.importErrorFamen(rowDto);
				System.out.print("房间已存在"+rowHouse);
				break;
			}
		}
		//如果不存在 则添加进去
		if(!bRet){
			houlist.add(rowDto);
			System.out.print("给房间添加阀门号"+rowHouse);
			Dto outdto = houseService.updateHouseFamen(rowDto);
			if((Boolean)outdto.get("success")!=true){
				rowDto.put("error",(String)outdto.get("error"));
				houseService.importErrorFamen(rowDto);
			}
		}
		return houlist;
	}
	
	
}
