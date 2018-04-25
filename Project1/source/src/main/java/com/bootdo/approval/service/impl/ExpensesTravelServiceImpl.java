package com.bootdo.approval.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.approval.domain.ExpensesNormalDO;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.approval.dao.ExpensesTravelDao;
import com.bootdo.approval.domain.ExpensesTravelDO;
import com.bootdo.approval.service.ExpensesTravelService;
import com.bootdo.contract.domain.PayoutDO;



@Service
public class ExpensesTravelServiceImpl implements ExpensesTravelService {
	@Autowired
	private ExpensesTravelDao expensesTravelDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;


	@Override
	public ExpensesTravelDO view(String expensesTravelId){
		return expensesTravelDao.view(expensesTravelId);
	}
	
	@Override
	public ExpensesTravelDO get(String expensesTravelId){
		return expensesTravelDao.get(expensesTravelId);
	}
	
	@Override
	public List<ExpensesTravelDO> list(Map<String, Object> map){
		return expensesTravelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return expensesTravelDao.count(map);
	}
	
	@Override
	public int save(ExpensesTravelDO expensesTravel){
		int ret = expensesTravelDao.save(expensesTravel);

		String expensesTravelId=expensesTravel.getExpensesTravelId();
		//流程标题，每个业务根据自己特点，体现主要信息
		String title="";
		title=expensesTravel.getExpensesTravelId()+"-"+expensesTravel.getExpensesTravelName();
		//添加保存时发起审批流程
		String PROCESS_INSTANCE_ID=actTaskService.startProcess(ActivitiConstant.ACTIVITI_EXPENSES_TRAVEL[0],
				ActivitiConstant.ACTIVITI_EXPENSES_TRAVEL[1],expensesTravelId,title,new HashMap<String,Object>());
		//更新流程实例ID到业务表
		expensesTravel.setProcessInstanceId(PROCESS_INSTANCE_ID);
		expensesTravelDao.update(expensesTravel);


		return ret;
	}

	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(ExpensesTravelDO expensesTravel){
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("taskAction",  expensesTravel.getTaskAction() );
		actTaskService.complete(expensesTravel.getTaskId(),expensesTravel.getProcessInstanceId(),
				expensesTravel.getTaskComment(),"",vars);
		//判断流程是否结束
		if(actTaskService.isProcessInstanceFinish(expensesTravel.getProcessInstanceId())){
			expensesTravel.setExpensesTravelStatus("1");
			expensesTravel.setExpensesTravelApprovalTime(new Date());
		}else{
			expensesTravel.setExpensesTravelStatus("0");
		}

		return expensesTravelDao.update(expensesTravel);
	}


	@Override
	public int update(ExpensesTravelDO expensesTravel){
		return expensesTravelDao.update(expensesTravel);
	}
	
	@Override
	public int remove(String expensesTravelId){
		return expensesTravelDao.remove(expensesTravelId);
	}
	
	@Override
	public int batchRemove(String[] expensesTravelIds){
		return expensesTravelDao.batchRemove(expensesTravelIds);
	}


	/**
	 * 数据导入功能
	 */
	@Override
	public Map<String, Object> Import(File file, long userid) {
		Workbook wookbook = null;
		List<String> errorMsgs = null;
		Map<String, Object> result = null;
		int rtn = 0;
		try {
			result = new HashMap<String, Object>();
			errorMsgs = new ArrayList<String>();
			FileInputStream is = new FileInputStream(file); // 文件流
			wookbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都是可以处理的

			// 在Excel文档中，第一张工作表的缺省索引是0
			// 其语句为：HSSFSheet sheet = wookbook.getSheetAt(0);
			Sheet sheet = wookbook.getSheetAt(0);// wookbook.getSheet("Sheet1");
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			// Excel文件中的第一行（标题行）
			int cellCount = 0;
			// 遍历行
			my: for (int i = 0; i < rows; i++) {
				// 读取左上端单元格(跳过第一行标题行)
				Row row = sheet.getRow(i);
				ExpensesTravelDO expensesTravelDO = new ExpensesTravelDO(); // 工厂信息表

				// 行不为空
				if (row != null) {
					if (i == 0) {
						// 获取到Excel文件中的第一行（标题行）
						Row rowCount = sheet.getRow(i);

						// 获取到Excel文件中的所有的列
						cellCount = rowCount.getPhysicalNumberOfCells();
						continue;
					}
					// 获取到Excel文件中的所有的列
					// int cells = row.getPhysicalNumberOfCells();
					String cellvalue = "";
					String contact = "";
					String agentCode = null;
					String companyName = null;
					// 遍历列
					for (int j = 0; j < cellCount; j++) {
						cellvalue = ""; // 清空之前之前取到的列的值
						// 获取到列的值
						Cell cell = row.getCell(j);
						// String value = "";
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								break;
							case XSSFCell.CELL_TYPE_NUMERIC: {
								short format = cell.getCellStyle().getDataFormat();
								if (format == 14 || format == 31 || format == 57 || format == 58) { // excel中的时间格式
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									Date date = DateUtil.getJavaDate(value);
									cellvalue = sdf.format(date);
								}
								// 判断当前的cell是否为Date
								else if (HSSFDateUtil.isCellDateFormatted(cell)) { // 先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
									// 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
									Date date = cell.getDateCellValue();
									DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
									cellvalue = formater.format(date);
								} else { // 如果是纯数字
									// 取得当前Cell的数值
									cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
								}
								break;
							}
							case XSSFCell.CELL_TYPE_STRING:
								cellvalue = cell.getStringCellValue();
								break;
							default:
								break;
							}
						}
						if (j == 0) {
							expensesTravelDO.setCustomerId(cellvalue);
						} else if (j == 1) {
							
							expensesTravelDO.setBusinessId(cellvalue);
						}else if (j == 2) {
							expensesTravelDO.setProjectId(cellvalue);
						}
						else if (j == 3) {
							expensesTravelDO.setExpensesTravelName(cellvalue);
						}
						else if (j == 4) {
							expensesTravelDO.setExpensesTravelTask(cellvalue);
						}
						else if (j == 5) {
							expensesTravelDO.setExpensesTravelAllowance(new BigDecimal(cellvalue));
						}
						else if (j == 6) {
							expensesTravelDO.setExpensesTravelType(cellvalue);
						}
						else if (j == 7) {
							expensesTravelDO.setExpensesTravelPrice(new BigDecimal(cellvalue));
						}
						else if (j == 8) {
							expensesTravelDO.setExpensesTravelTid(cellvalue);
						}
						else if (j == 9) {
							expensesTravelDO.setExpensesTravelPayid(cellvalue);
						}
						else if (j == 10) {
							expensesTravelDO.setExpensesTravelPayPrice(new BigDecimal(cellvalue));
						}
						else if (j == 11) {
							expensesTravelDO.setExpensesTravelPayMode(cellvalue);
						}
						else if (j == 12) {
							expensesTravelDO.setExpensesTravelBank(cellvalue);
						}
						else if (j == 13) {
							expensesTravelDO.setExpensesTravelCardNum(cellvalue);
						}
						else if (j == 14) {
							expensesTravelDO.setExpensesTravelRemarks(cellvalue);
						}
					} // --->遍历列
					expensesTravelDO.setExpensesTravelOperator(Long.toString(userid));
					expensesTravelDO.setExpensesTravelCreator(userid);
					expensesTravelDO.setExpensesTravelOptTime(new Date());
					expensesTravelDO.setExpensesTravelCreateTime(new Date());
					rtn =expensesTravelDao.save(expensesTravelDO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wookbook.cloneSheet(0); // 关闭sheet页
			try {
				wookbook.close(); // 关闭Excel文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (CollectionUtils.isEmpty(errorMsgs)) { // errorMsgs.size() == 0

			if (rtn > 0) {
				result.put("result", "success");
			} else {
				result.put("result", "false");
			}
		} else {
			result.put("result", "error");
			result.put("msg", errorMsgs);
		}
		return result;
	}
	}
