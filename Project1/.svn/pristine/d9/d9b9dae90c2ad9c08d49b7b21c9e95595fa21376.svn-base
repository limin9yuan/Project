package com.bootdo.payment.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.bootdo.common.domain.DictDO;
import com.bootdo.payment.dao.ProjectExpenditureDao;
import com.bootdo.payment.dao.SummaryOfIncomeAndExpenditureDao;
import com.bootdo.payment.domain.ProjectExpenditureDO;
import com.bootdo.payment.domain.SummaryOfIncomeAndExpenditureDO;
import com.bootdo.payment.service.ProjectExpenditureService;
import com.bootdo.payment.service.SummaryOfIncomeAndExpenditureService;


@Service
public class SummaryOfIncomeAndExpenditureImpl implements SummaryOfIncomeAndExpenditureService {
	@Autowired
	private  SummaryOfIncomeAndExpenditureDao summaryOfIncomeAndExpenditureDao;
	
	@Override
	public SummaryOfIncomeAndExpenditureDO get(String projectId){
		return summaryOfIncomeAndExpenditureDao.get(projectId);
	}
	
	@Override
	public List<SummaryOfIncomeAndExpenditureDO> list(Map<String, Object> map){
		return summaryOfIncomeAndExpenditureDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return summaryOfIncomeAndExpenditureDao.count(map);
	}
	
	@Override
	public int save(SummaryOfIncomeAndExpenditureDO summaryOfIncomeAndExpenditure){
		return summaryOfIncomeAndExpenditureDao.save(summaryOfIncomeAndExpenditure);
	}
	
	@Override
	public int update(SummaryOfIncomeAndExpenditureDO summaryOfIncomeAndExpenditure){
		return summaryOfIncomeAndExpenditureDao.update(summaryOfIncomeAndExpenditure);
	}
	
	@Override
	public int remove(String projectId){
		return summaryOfIncomeAndExpenditureDao.remove(projectId);
	}
	
	@Override
	public int batchRemove(String[] projectIds){
		return summaryOfIncomeAndExpenditureDao.batchRemove(projectIds);
	}
	@Override	
	public List<DictDO> listDic(){
		return summaryOfIncomeAndExpenditureDao.listDic();
	}

	@Override
	public List<DictDO> listDicManager() {
		return summaryOfIncomeAndExpenditureDao.listDicManager();
	}
}
