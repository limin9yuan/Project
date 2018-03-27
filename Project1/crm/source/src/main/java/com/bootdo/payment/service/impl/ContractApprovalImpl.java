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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.bootdo.common.domain.DictDO;
import com.bootdo.payment.dao.ContractApprovalDao;
import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.payment.service.ContractApprovalService;



@Service
public class ContractApprovalImpl implements ContractApprovalService {
	@Autowired
	private  ContractApprovalDao contractApprovalDao;
	
	@Override
	public ContractApprovalDO get(String contractId){
		return contractApprovalDao.get(contractId);
	}
	
	@Override
	public List<ContractApprovalDO> list(Map<String, Object> map){
		return contractApprovalDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contractApprovalDao.count(map);
	}
	
	@Override
	public int save(ContractApprovalDO contractApproval){
		return contractApprovalDao.save(contractApproval);
	}
	
	@Override
	public int update(ContractApprovalDO contractApproval){
		return contractApprovalDao.update(contractApproval);
	}
	
	@Override
	public int remove(String contractId){
		return contractApprovalDao.remove(contractId);
	}
	
	@Override
	public int batchRemove(String[] contractIds){
		return contractApprovalDao.batchRemove(contractIds);
	}
	@Override	
	public List<DictDO> listDic(){
		return contractApprovalDao.listDic();
	}
	
	/**
	 * 数据导入功能
	 */
	@Override
	public Map<String, Object> dataImport(File file, long userid) {
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
				ContractApprovalDO contractApprovalDO = new ContractApprovalDO(); // 合同表
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
//					String contact = "";
//					String agentCode = null;
//					String companyName = null;
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
							contractApprovalDO.setCustomerId(cellvalue);
						} else if (j == 1) {
							contractApprovalDO.setBusinessId(cellvalue);
						} else if (j == 2) {
							contractApprovalDO.setContractName(cellvalue);
						} else if (j == 3) {
							contractApprovalDO.setContractApplicantName(cellvalue);
						} else if (j == 4) {
							contractApprovalDO.setContractBuildCompany(cellvalue);
						} else if (j == 5) {
							contractApprovalDO.setContractType(cellvalue);
						} else if (j == 6) {
							contractApprovalDO.setContractCategory(cellvalue);
						} else if (j == 7) {
							contractApprovalDO.setContractDept(cellvalue);
						} else if (j == 8) {
							contractApprovalDO.setContractApplicant(cellvalue);
						} else if (j == 9) {
							contractApprovalDO.setContractDraftPerson(cellvalue);
						} else if (j == 10) {
							contractApprovalDO.setContractSales(cellvalue);
						} else if (j == 11) {
							contractApprovalDO.setContractRelatedId(cellvalue);
						} else if (j == 12) {
							contractApprovalDO.setContractInvoiceType(cellvalue);
						} else if (j == 13) {
							contractApprovalDO.setContractHardwareList(cellvalue);
						} else if (j == 14) {
							contractApprovalDO.setContractSoftwareList(cellvalue);
						} else if (j == 15) {
							contractApprovalDO.setContractProjectManagement(cellvalue);
						} else if (j == 16) {
							contractApprovalDO.setContractRemarks(cellvalue);
						} else if (j == 17) {
							contractApprovalDO.setContractApprovalStatus(new Integer(cellvalue));
						} 
					} // --->遍历列
					contractApprovalDO.setContractId(Long.valueOf(i));
					contractApprovalDO.setContractOperateTime(new Date());
					rtn = contractApprovalDao.save(contractApprovalDO);
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
	public void export(String[] titles, ServletOutputStream out, List<ContractApprovalDO> list) {
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
								ContractApprovalDO report = list.get(i);
								/** 第六步，创建单元格，并设置值 **/
								// 序号
								hssfRow.createCell(0).setCellValue(i + 1);
								// 合同编号
								String contractId = "";
								if (report.getContractId() != null) {
									Long getContractId = report.getContractId();
									contractId = sdf.format(getContractId);
								}
								hssfRow.createCell(0).setCellValue(contractId);
								// 企业客户编号
								String CustomerId = "";
								if (report.getCustomerId() != null) {
									CustomerId = report.getCustomerId();
								}
								hssfRow.createCell(1).setCellValue(CustomerId);
								// 业务编号
								String BusinessId = "";
								if (report.getBusinessId() != null) {
									BusinessId = report.getBusinessId();
								}
								hssfRow.createCell(2).setCellValue(BusinessId);
								// 合同名称
								String ContractName = "";
								if (report.getContractName() != null) {
									ContractName = report.getContractName();
								}
								hssfRow.createCell(3).setCellValue(ContractName);
								// 申请人姓名
								String ContractApplicantName = "";
								if (report.getContractApplicantName() != null) {
									ContractApplicantName = report.getContractApplicantName();
								}
								hssfRow.createCell(4).setCellValue(ContractApplicantName);
								// 建设单位
								String ContractBuildCompany = "";
								if (report.getContractBuildCompany() != null) {
									ContractBuildCompany = report.getContractBuildCompany();
								}
								hssfRow.createCell(5).setCellValue(ContractBuildCompany);
								// 合同类型
								String ContractType = "";
								if (report.getContractType() != null) {
									ContractType = report.getContractType();
								}
								hssfRow.createCell(7).setCellValue(ContractType);
								// 合同种类
								String ContractCategory = "";
								if (report.getContractCategory() != null) {
									ContractCategory = report.getContractCategory();
								}
								hssfRow.createCell(8).setCellValue(ContractCategory);
								// 合同总金额
								String ContractTotalPrice = "";
								if (report.getContractTotalPrice() != null) {
									BigDecimal getContractTotalPrice = report.getContractTotalPrice();
									ContractTotalPrice = sdf.format(getContractTotalPrice);
								}
								hssfRow.createCell(9).setCellValue(ContractTotalPrice);
								// 合同发起部门
								String ContractDept = "";
								if (report.getContractDept() != null) {
									ContractDept = report.getContractDept();
								}
								hssfRow.createCell(10).setCellValue(ContractDept);
								// 业务发起人
								String ContractApplicant = "";
								if (report.getContractApplicant() != null) {
									ContractApplicant = report.getContractApplicant();
								}
								hssfRow.createCell(11).setCellValue(ContractApplicant);
								// 合同拟定人
								String ContractDraftPerson = "";
								if (report.getContractDraftPerson() != null) {
									ContractDraftPerson = report.getContractDraftPerson();
								}
								hssfRow.createCell(12).setCellValue(ContractDraftPerson);
								// 销售负责人
								String ContractSales = "";
								if (report.getContractSales() != null) {
									ContractSales = report.getContractSales();
								}
								hssfRow.createCell(13).setCellValue(ContractSales);
								// 提交评审时间
								String ContractCommitTime = "";
								if (report.getContractCommitTime() != null) {
									Date getContractCommitTime = report.getContractCommitTime();
									ContractCommitTime = sdf.format(getContractCommitTime);
								}
								hssfRow.createCell(14).setCellValue(ContractCommitTime);
								// 关联合同编号
								String ContractRelatedId = "";
								if (report.getContractRelatedId() != null) {
									ContractRelatedId = report.getContractRelatedId();
								}
								hssfRow.createCell(15).setCellValue(ContractRelatedId);
								// 发票类型
								String ContractInvoiceType = "";
								if (report.getContractInvoiceType() != null) {
									ContractInvoiceType = report.getContractInvoiceType();
								}
								hssfRow.createCell(16).setCellValue(ContractInvoiceType);
								// 预计开具发票时间
								String ContractInvoiceTime = "";
								if (report.getContractInvoiceTime() != null) {
									Date getContractInvoiceTime1 = report.getContractInvoiceTime();
									ContractInvoiceTime = sdf.format(getContractInvoiceTime1);
								}
								hssfRow.createCell(17).setCellValue(ContractInvoiceTime);
								// 硬件设备明细表
								String getContractHardwareList = "";
								if (report.getContractHardwareList() != null) {
									getContractHardwareList = report.getContractHardwareList();
								}
								hssfRow.createCell(18).setCellValue(getContractHardwareList);
								// 软件功能列表
								String ContractSoftwareList = "";
								if (report.getContractSoftwareList() != null) {
									ContractSoftwareList = report.getContractSoftwareList();
								}
								hssfRow.createCell(19).setCellValue(ContractSoftwareList);
								// 项目经理 
								String ContractProjectManagement = "";
								if (report.getContractProjectManagement() != null) {
									ContractProjectManagement = report.getContractProjectManagement();
								}
								hssfRow.createCell(20).setCellValue(ContractProjectManagement);
								// 合同信息备注
								String ContractRemarks = "";
								if (report.getContractRemarks() != null) {
									ContractRemarks = report.getContractRemarks();
								}
								hssfRow.createCell(21).setCellValue(ContractRemarks);
								// 审批状态
								String ContractApprovalStatus = "";
								if (report.getContractApprovalStatus() != null) {
									Integer getContractApprovalStatus = report.getContractApprovalStatus();
									ContractApprovalStatus = sdf.format(getContractApprovalStatus);
								}
								hssfRow.createCell(22).setCellValue(ContractApprovalStatus);
								// 操作人
								String ContractOperator = "";
								if (report.getContractOperator() != null) {
									ContractOperator = report.getContractOperator();
								}
								hssfRow.createCell(23).setCellValue(ContractOperator);
								// 操作时间
								String getContractOperateTime = "";
								if (report.getContractOperateTime() != null) {
									Date getContractOperateTime1 = report.getContractOperateTime();
									getContractOperateTime = sdf.format(getContractOperateTime1);
								}
								hssfRow.createCell(24).setCellValue(getContractOperateTime);
								// 项目编号
								String ProjectId = "";
								if (report.getProjectId() != null) {
									ProjectId = report.getProjectId();
								}
								hssfRow.createCell(25).setCellValue(ProjectId);
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
	public List<ContractApprovalDO> getQuery(Map<String, Object> params) {
		List<ContractApprovalDO> returnData=contractApprovalDao.list(params);
		return returnData;
	}
}
