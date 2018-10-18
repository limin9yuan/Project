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
public class ExcelReader  extends BaseAction{
	private String metaData = null;
	private InputStream is = null;
	private CommunityService communityService = (CommunityService) super.getService("CommunityService");
	private BuildingService buildingService = (BuildingService) super.getService("BuildingService");
	private HouseService houseService = (HouseService) super.getService("HouseService");
	public ExcelReader(){};
	
	/**
	 * 构造函数
	 * @param pMetaData 元数据
	 * @param pIs Excel数据流
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public ExcelReader(String pMetaData, InputStream pIs){
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
	public void readToFcDatabase(int pBegin,String cid,String operator) throws BiffException, IOException{
		List comlist = new ArrayList();
		List builist = new ArrayList();
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
			rowDto.put("area_id", "0");
			rowDto.put("item_code", "A");
			if(rowDto.get("community_code")!=null && !"".equals((String)rowDto.get("community_code"))){
				comlist=setCommunity(rowDto,comlist);
				builist=setBuilding(rowDto,builist);
				houlist=setHouse(rowDto,houlist);
				setArea2(rowDto,houlist);
			}
			
		}
		comlist=null;
	}
	/*
	 * 小区的存储处理
	 * rowDto 行记录
	 * comlist 小区list
	 * */
	private List setCommunity(Dto rowDto,List comlist){
		boolean bRet = false;//是否在comlist里已经存在了该小区 fasle否 true是
		//循环comlist
		for(int i=0;i<comlist.size();i++){
			Dto tdto = (Dto)comlist.get(i);
			if(((String)tdto.get("community_code")).equals((String)rowDto.get("community_code"))){
				bRet = true;
				System.out.print("小区已存在"+(String)rowDto.get("community_code"));
				break;
			}
		}
		
		//如果不存在 则添加进去
		if(!bRet){
			comlist.add(rowDto);
			System.out.print("添加新小区"+(String)rowDto.get("community_code"));
			communityService.insertCommunity(rowDto);			
		}
		return comlist;
	}
	/*
	 * 大楼的存储处理
	 * rowDto 行记录
	 * comlist 大楼list
	 * */
	private List setBuilding(Dto rowDto,List builist){
		boolean bRet = false;//是否在builist里已经存在了该大楼 fasle否 true是
		String rowBuilding = "";
		String strBuilding = "";
		//循环builist
		for(int i=0;i<builist.size();i++){
			Dto tdto = (Dto)builist.get(i);
			rowBuilding = (String)rowDto.get("community_code") + "-" 
			+ (String)rowDto.get("building_code");
			strBuilding = (String)tdto.get("community_code") + "-" 
			+ (String)tdto.get("building_code");
			
			if(rowBuilding.equals(strBuilding)){
				bRet = true;
				System.out.print("大楼已存在"+(String)rowDto.get("building_code"));
				break;
			}
		}
		//如果不存在 则添加进去
		if(!bRet){
			rowDto.put("building_code",(String)rowDto.get("community_code") + "-" 
					+ (String)rowDto.get("building_code"));
			builist.add(rowDto);
			System.out.print("添加新大楼"+(String)rowDto.get("building_code"));
			buildingService.insertBuilding(rowDto);
		}
		return builist;
	}
	/*
	 * 用户的存储处理
	 * rowDto 行记录
	 * comlist 大楼list
	 * */
	private List setHouse(Dto rowDto,List houlist){
		boolean bRet = false;//是否在builist里已经存在了该大楼 fasle否 true是
		String rowHouse = "";
		String strHouse = "";
		//循环builist
		for(int i=0;i<houlist.size();i++){
			Dto tdto = (Dto)houlist.get(i);
			rowHouse = (String)rowDto.get("community_code")+ "-"
						+(String)rowDto.get("building_code")+ "-"
								+ (String)rowDto.get("cell_code") + "-" 
								+ (String)rowDto.get("floor") + "-" 
								+ (String)rowDto.get("door_code");
			//(String)rowDto.get("house_code") + "-" 
			//(String)tdto.get("house_code") + "-"
			strHouse = (String)tdto.get("community_code")+ "-"
			+(String)tdto.get("building_code")+ "-" 
			+ (String)tdto.get("cell_code") + "-" 
			+ (String)tdto.get("floor") + "-" 
			+ (String)tdto.get("door_code");
			if((rowHouse).equals(strHouse)){
				bRet = true;
				rowDto.put("error", "用户编号已存在!");
				houseService.importErrorHouse(rowDto);
				System.out.print("房间已存在"+rowHouse);
				break;
			}
		}
		//如果不存在 则添加进去
		if(!bRet){
			houlist.add(rowDto);
			System.out.print("添加新房间"+rowHouse);
			Dto outdto = houseService.insertHouse(rowDto);
			if((Boolean)outdto.get("success")!=true){
				rowDto.put("error",(String)outdto.get("error"));
				houseService.importErrorHouse(rowDto);
			}
		}
		return houlist;
	}
	/*
	 * 添加面积2
	 * rowDto 行记录
	 * comlist 大楼list
	 * */
	private void setArea2(Dto rowDto,List houlist){
		//如果不存在 则添加进去
		if(
				(rowDto.get("use_type2")!=null && !"".equals(rowDto.getAsString("use_type2").trim()))
				|| (rowDto.get("standard_id2")!=null && !"".equals(rowDto.getAsString("standard_id2").trim()))
				|| (rowDto.get("charge_area2")!=null && !"".equals(rowDto.getAsString("charge_area2").trim()))
		){
			Dto inDto = new BaseDto();	
			inDto.putAll(rowDto);
			inDto.put("use_type", rowDto.getAsString("use_type2"));
			inDto.put("build_area", rowDto.getAsString("build_area2"));
			inDto.put("use_area", rowDto.getAsString("use_area2"));
			inDto.put("standard_id", rowDto.getAsString("standard_id2"));
			inDto.put("charge_area", rowDto.getAsString("charge_area2"));
			inDto.put("super_area", rowDto.getAsString("super_area2"));
			inDto.put("area_id", "1");
			inDto.put("item_code", "A");
			Dto outdto = houseService.insertArea(inDto);
			if((Boolean)outdto.get("success")!=true){
				rowDto.put("error",(String)outdto.get("error"));
				houseService.importErrorHouse(rowDto);
			}
		}
	}
	
}
