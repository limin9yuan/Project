package com.bootdo.contract.service.impl;

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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.dao.AdditionalRecordsDao;
import com.bootdo.contract.domain.AdditionalRecordsDO;
import com.bootdo.contract.service.AdditionalRecordsService;
import com.bootdo.sales.domain.SalesProjectDO;



@Service
public class AdditionalRecordsServiceImpl implements AdditionalRecordsService {
	@Autowired
	private AdditionalRecordsDao additionalRecordsDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	
	@Override
	public AdditionalRecordsDO get(String recordId){
		return additionalRecordsDao.get(recordId);
	}
	
	@Override
	public List<AdditionalRecordsDO> list(Map<String, Object> map){
		return additionalRecordsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return additionalRecordsDao.count(map);
	}
	
	@Override
	public int save(AdditionalRecordsDO additionalRecords){
		int ret=additionalRecordsDao.save(additionalRecords);
		String recordId=additionalRecords.getRecordId();
		
		//添加保存时发起审批流程
		actTaskService.startProcess(ActivitiConstant.ACTIVITI_CONTRACT_ADDITIONALRECORDS[0],ActivitiConstant.ACTIVITI_CONTRACT_ADDITIONALRECORDS[1],recordId,additionalRecords.getRecordId(),new HashMap<String,Object>());
		return Integer.parseInt(recordId);
	}
	
	@Override
	public int update(AdditionalRecordsDO additionalRecords){
		return additionalRecordsDao.update(additionalRecords);
	}
	
	@Override
	public int remove(String recordId){
		return additionalRecordsDao.remove(recordId);
	}
	
	@Override
	public int batchRemove(String[] recordIds){
		return additionalRecordsDao.batchRemove(recordIds);
	}
	@Override	
	public List<DictDO> listDic(){
		return additionalRecordsDao.listDic();
	}
	/**
	 * 数据导入功能
	 */
	@Override
	public Map<String, Object> uploadExcel(File file, long userid) {
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
				AdditionalRecordsDO additionalRecordsDO = new AdditionalRecordsDO(); // 售前项目信息表

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
					//String agentCode = null;
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
							additionalRecordsDO.getContractId();
						} else if (j == 1) {
							additionalRecordsDO.setRecordName(cellvalue);
						}else if (j == 2) {
							additionalRecordsDO.setRecordBulidCompany(cellvalue);
						}else if (j == 3) {
							additionalRecordsDO.setRecordTotalPrice(null);
						}else if (j == 4) {
							additionalRecordsDO.setRecordDescription(cellvalue);
						}else if (j == 5) {
							additionalRecordsDO.setRecordReason(cellvalue);
						}else if (j == 6) {
							additionalRecordsDO.setRecordSales(cellvalue);
						}else if (j == 7) {
							additionalRecordsDO.setRecordRelatedContractId(cellvalue);
						}else if (j == 8) {
							additionalRecordsDO.setRecordRemarks(cellvalue);
						}else if (j == 9) {
							additionalRecordsDO.setRecordApprovalStatus(j);  
						}else if (j == 10) {
							additionalRecordsDO.setRecordCommitTime(cellvalue);
						}else if (j == 11) {
							additionalRecordsDO.setProjectId(cellvalue);
						}else if (j == 12) {
							additionalRecordsDO.setContractName(cellvalue);
						}else if (j == 13) {
							additionalRecordsDO.setPreInvoiceDate(cellvalue);
						}
					} // --->遍历列
					//additionalRecordsDO.setRecordId(String.valueOf(i));
					additionalRecordsDO.setRecordOperator(userid); 
					//additionalRecordsDO.setProjectOperator(userid); 
					//additionalRecordsDO.setProjectCreateTime(new Date());
					//additionalRecordsDO.setProjectOperateTime(new Date());
					//additionalRecordsDO.setprojectBusinesName(additionalRecordsDO.getProjectBusiness());
					//additionalRecordsDO.setProjectSalesName(additionalRecordsDO.getProjectSales());
					//additionalRecordsDO.setProjectManagerName(additionalRecordsDO.getProjectManager());
					rtn = additionalRecordsDao.save(additionalRecordsDO);
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
	/**
	 * 导出Excel写入文件方法
	 */
	public void export(String[] titles, ServletOutputStream out, List<AdditionalRecordsDO> list) {
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}
			// 第五步，写入实体数据
			if (list != null && !list.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < list.size(); i++) {
					hssfRow = hssfSheet.createRow(i + 1);
					AdditionalRecordsDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					//hssfRow.createCell(0).setCellValue(i + 1);
					//合同增补记录编号
					String recordId = "";
					if (report.getRecordId() != null) {
						recordId = report.getRecordId();
					}
					hssfRow.createCell(0).setCellValue(recordId);
					// 合同编号
					String contractId = "";
					if (report.getContractId() != null) {
						contractId = report.getContractId();
					}
					hssfRow.createCell(1).setCellValue(contractId);
					// 申请人姓名
					String recordName = "";
					if (report.getRecordName() != null) {
						recordName = report.getRecordName();
					}
					hssfRow.createCell(2).setCellValue(recordName);
					// 建设单位
					String recordBulidCompany = "";
					if (report.getRecordBulidCompany() != null) {
						recordBulidCompany = report.getRecordBulidCompany();
					}
					hssfRow.createCell(3).setCellValue(recordBulidCompany);
					// 增补总金额
					String recordTotalPrice = "";
					if (report.getRecordTotalPrice() != null) {
						BigDecimal recordTotalPrice1 = report.getRecordTotalPrice();
						recordTotalPrice = String.valueOf(recordTotalPrice1);
					}
					hssfRow.createCell(4).setCellValue(recordTotalPrice);
					// 增补内容描述
					String recordDescription = "";
					if (report.getRecordDescription() != null) {
						recordDescription =  report.getRecordDescription();
					}
					hssfRow.createCell(5).setCellValue(recordDescription);
					// 增补原因
					String recordReason = "";
					if (report.getRecordReason() != null) {
						recordReason = report.getRecordReason();
					}
					hssfRow.createCell(6).setCellValue(recordReason);
					// 销售负责人
					String recordSales = "";
					if (report.getRecordSales() != null) {
						recordSales = report.getRecordSales();
					}
					hssfRow.createCell(7).setCellValue(recordSales);
					// 关联合同编号
					String recordRelatedContractId = "";
					if (report.getRecordRelatedContractId() != null) {
						recordRelatedContractId = report.getRecordRelatedContractId();
					}
					hssfRow.createCell(8).setCellValue(recordRelatedContractId);
					// 备注
					String recordRemarks = "";
					if (report.getRecordRemarks() != null) {
						recordRemarks = report.getRecordRemarks();
					}
					hssfRow.createCell(9).setCellValue(recordRemarks);
					// 审批状态
					String recordApprovalStatus = "";
					if (report.getRecordApprovalStatus() != null) {
						Integer recordApprovalStatus1 = report.getRecordApprovalStatus();
						recordApprovalStatus = String.valueOf(recordApprovalStatus1);
					}
					hssfRow.createCell(10).setCellValue(recordApprovalStatus);
					// 创建人
					String recordOperatorName = "";
					if (report.getRecordOperatorName() != null) {
						recordOperatorName = report.getRecordOperatorName();
					}
					hssfRow.createCell(11).setCellValue(recordOperatorName);
					// 提交评审时间
					String recordCommitTime = "";
					if (report.getRecordCommitTime() != null) {
						//recordCommitTime = sdf.format(report.getRecordCommitTime());
						recordCommitTime = report.getRecordCommitTime();
					}
					hssfRow.createCell(12).setCellValue(recordCommitTime);
					// 项目编号
					String projectId = "";
					if (report.getProjectId() != null) {
						projectId = report.getProjectId();
					}
					hssfRow.createCell(13).setCellValue(projectId);
					// 合同名称
					String contractName = "";
					if (report.getContractName() != null) {
						contractName = report.getContractName();
					}
					hssfRow.createCell(14).setCellValue(contractName);
					// 预计开发票时间
					String preInvoiceDate = "";
					if (report.getPreInvoiceDate() != null) {
						//preInvoiceDate = sdf.format(report.getPreInvoiceDate());
						preInvoiceDate = report.getPreInvoiceDate();
					}
					hssfRow.createCell(15).setCellValue(preInvoiceDate);	
				}
			}
			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("日报信息导出失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 导出excel
	 */
	public List<AdditionalRecordsDO> getQuery(Map<String, Object> params) {
		List<AdditionalRecordsDO> returnData=additionalRecordsDao.list(params);
		return returnData;
		
		/*for (int i = 0; i < returnDate.size(); i++) {
		}*/
	}
	/**
	 * ******************* 审批流程相关 *************************
	 */
	//审批处理保存
	@Override
	public int formUpdate(AdditionalRecordsDO additionalRecords){
		//流程审批处理
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("taskAction",  additionalRecords.getTaskAction() );
		actTaskService.complete(additionalRecords.getTaskId(),vars);
		
		return additionalRecordsDao.update(additionalRecords);
	}
}

