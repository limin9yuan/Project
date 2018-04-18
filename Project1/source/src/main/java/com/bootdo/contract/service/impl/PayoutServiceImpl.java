package com.bootdo.contract.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.contract.domain.ContractDO;
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

import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.dao.PayoutDao;
import com.bootdo.contract.domain.PayoutDO;
import com.bootdo.contract.service.PayoutService;
import com.bootdo.sales.domain.RecordServiceDO;



@Service
public class PayoutServiceImpl implements PayoutService {
	@Autowired
	private PayoutDao payoutDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	
	@Override
	public PayoutDO get(String payoutId){
		return payoutDao.get(payoutId);
	}
	
	@Override
	public List<PayoutDO> list(Map<String, Object> map){
		return payoutDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payoutDao.count(map);
	}

	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(PayoutDO payout){
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("taskAction",  payout.getTaskAction() );
		actTaskService.complete(payout.getTaskId(),payout.getProcessInstanceId(),payout.getTaskComment(),"",vars);

		return payoutDao.update(payout);
	}
	
	@Override
	public int save(PayoutDO payout){
		int ret = payoutDao.save(payout);

		String payoutId=payout.getPayoutId();
		//流程标题，每个业务根据自己特点，体现主要信息
		String title="";
		title=payout.getPayoutId()+"-"+payout.getPayoutPerson();
		//添加保存时发起审批流程
		String PROCESS_INSTANCE_ID=actTaskService.startProcess(ActivitiConstant.ACTIVITI_PAYOUT[0],
				ActivitiConstant.ACTIVITI_PAYOUT[1],payoutId,title,new HashMap<String,Object>());
		//更新流程实例ID到业务表
		payout.setProcessInstanceId(PROCESS_INSTANCE_ID);
		payoutDao.update(payout);


		return ret;
	}
	
	@Override
	public int update(PayoutDO payout){
		return payoutDao.update(payout);
	}
	
	@Override
	public int remove(String payoutId){
		return payoutDao.remove(payoutId);
	}
	
	@Override
	public int batchRemove(String[] payoutIds){
		return payoutDao.batchRemove(payoutIds);
	}
	
	@Override	
	public List<DictDO> listDic(){
		return payoutDao.listDic();
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
			PayoutDO payoutDO = new PayoutDO(); // 工厂信息表

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
						payoutDO.setCustomerId(cellvalue);
					} else if (j == 1) {
						
						payoutDO.setBusinessId(cellvalue);
					}else if (j == 2) {
						payoutDO.setProjectId(cellvalue);
					}
					else if (j == 3) {
						payoutDO.setPayoutPerson(cellvalue);
					}
					else if (j == 4) {
						payoutDO.setPayoutUseage(cellvalue);
					}
					else if (j == 5) {
						payoutDO.setPayoutPrice(new BigDecimal(cellvalue));
					}
					else if (j == 6) {
						payoutDO.setPayoutMeans(cellvalue);
					}
					else if (j == 7) {
						payoutDO.setPayoutBeneficiaryBank(cellvalue);
					}
					else if (j == 8) {
						payoutDO.setPayoutCardNumber(cellvalue);
					}
					else if (j == 9) {
						payoutDO.setPayoutRelatedContractId(cellvalue);
					}
					else if (j == 10) {
						payoutDO.setPayoutResult(cellvalue);
					}
					else if (j == 11) {
						payoutDO.setPayoutRemarks(cellvalue);
					}
					
				} // --->遍历列
				payoutDO.setPayoutOperator(Long.toString(userid));
				payoutDO.setPayoutCreator(userid);
				payoutDO.setPayoutOperateTime(new Date());
				payoutDO.setPayoutCreateTime(new Date());
				rtn =payoutDao.save(payoutDO);
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