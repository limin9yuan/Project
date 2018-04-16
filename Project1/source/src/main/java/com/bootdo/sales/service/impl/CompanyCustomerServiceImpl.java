package com.bootdo.sales.service.impl;

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
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.dao.CompanyCustomerDao;
import com.bootdo.sales.domain.CompanyCustomerDO;
import com.bootdo.sales.domain.CustomerContactDO;
import com.bootdo.sales.service.CompanyCustomerService;

@Service
public class CompanyCustomerServiceImpl implements CompanyCustomerService {
	@Autowired
	private CompanyCustomerDao companyCustomerDao;

	@Override
	public CompanyCustomerDO get(String customerId) {
		return companyCustomerDao.get(customerId);
	}

	@Override
	public List<CompanyCustomerDO> list(Map<String, Object> map) {
		if (map.get("customerName") != null && !"".equals(map.get("customerName"))) {
			map.put("customerName", "%" + map.get("customerName") + "%");
		}
		if (map.get("customerId") != null && !"".equals(map.get("customerId"))) {
			map.put("customerId", "%" +map.get("customerId") + "%");
		}
		if (map.get("customerContactName") != null && !"".equals(map.get("customerContactName"))) {
			map.put("customerContactName","%" + map.get("customerContactName") + "%");
		}
		if (map.get("businessName") != null && !"".equals(map.get("businessName"))) {
			map.put("businessName", "%" +map.get("businessName") + "%");
		}
		if (map.get("projectName") != null && !"".equals(map.get("projectName"))) {
			map.put("projectName", "%" +map.get("projectName") + "%");
		}
		if (map.get("contactName") != null && !"".equals(map.get("contactName"))) {
			map.put("contactName", "%" +map.get("contactName") + "%");
		}
		return companyCustomerDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return companyCustomerDao.count(map);
	}

	@Override
	public int save(CompanyCustomerDO companyCustomer) {
		int red=companyCustomerDao.save(companyCustomer);
		String customerId=companyCustomer.getCustomerId();
//		return companyCustomerDao.save(companyCustomer);
		return Integer.parseInt(customerId);
	}

	@Override
	public int update(CompanyCustomerDO companyCustomer) {
		return companyCustomerDao.update(companyCustomer);
	}

	@Override
	public int remove(String customerId) {
		return companyCustomerDao.remove(customerId);
	}

	@Override
	public int batchRemove(String[] customerIds) {
		return companyCustomerDao.batchRemove(customerIds);
	}

	@Override
	public List<DictDO> listDic() {
		return companyCustomerDao.listDic();
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
				CompanyCustomerDO companyCustomerDO = new CompanyCustomerDO(); // 工厂信息表

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
							companyCustomerDO.setCustomerProvince(cellvalue);
						} else if (j == 1) {

							companyCustomerDO.setCustomerCity(cellvalue);
						} else if (j == 2) {
							companyCustomerDO.setCustomerCounty(cellvalue);
						} else if (j == 3) {
							companyCustomerDO.setCustomerName(cellvalue);
						} else if (j == 4) {
							companyCustomerDO.setCustomerSimpleName(cellvalue);
						} else if (j == 5) {
							companyCustomerDO.setCustomerOwner(cellvalue);
						} else if (j == 6) {
							companyCustomerDO.setCustomerSales(cellvalue);
						} else if (j == 7) {
							companyCustomerDO.setCustomerProduct(cellvalue);
						} else if (j == 8) {
							companyCustomerDO.setCustomerReqDes(cellvalue);
						} else if (j == 9) {
							companyCustomerDO.setCustomerAttachment(cellvalue);
						} else if (j == 10) {
							companyCustomerDO.setCustomerCategory(cellvalue);
						} else if (j == 11) {
							companyCustomerDO.setCustomerCharacter(cellvalue);
						} else if (j == 12) {
							companyCustomerDO.setCustomerStatus(cellvalue);
						} else if (j == 13) {
							companyCustomerDO.setCustomerLevel(Long.valueOf(cellvalue));
						} else if (j == 14) {
							companyCustomerDO.setCustomerSalePhase(cellvalue);
						} else if (j == 15) {
							companyCustomerDO.setCustomerInnerPhase(cellvalue);
						} else if (j == 16) {
							companyCustomerDO.setCustomerSource(cellvalue);
						} else if (j == 17) {
							companyCustomerDO.setCustomerIndustry(cellvalue);
						} else if (j == 18) {
							companyCustomerDO.setCustomerStaffSize(Integer.valueOf(cellvalue));
						} else if (j == 19) {
							companyCustomerDO.setCustomerCreditRank(Integer.valueOf(cellvalue));
						} else if (j == 20) {
							companyCustomerDO.setCustomerPotential(cellvalue);
						} else if (j == 21) {
							companyCustomerDO.setCustomerEmpNumber(Integer.valueOf(cellvalue));
						} else if (j == 22) {
							companyCustomerDO.setCustomerParent(cellvalue);
						} else if (j == 23) {
							companyCustomerDO.setCustomerIntroduction(cellvalue);
						} else if (j == 24) {
							companyCustomerDO.setCustomerVisitMode(cellvalue);
						} else if (j == 25) {
							companyCustomerDO.setCustomerOldId(cellvalue);
						} else if (j == 26) {
							companyCustomerDO.setCustomerChildCompany(cellvalue);
						} else if (j == 27) {
							companyCustomerDO.setCustomerHot(String.valueOf(cellvalue));
						} else if (j == 28) {
							companyCustomerDO.setCustomerHotRank(cellvalue);
						} else if (j == 29) {
							companyCustomerDO.setCustomerHotClassif(cellvalue);
						} else if (j == 30) {
							companyCustomerDO.setCustomerVolume(new BigDecimal(cellvalue));
						} else if (j == 31) {
							companyCustomerDO.setCustomerHotDesc(cellvalue);
						} else if (j == 32) {
							companyCustomerDO.setCustomerInvoiceName(cellvalue);
						}

						else if (j == 33) {
							companyCustomerDO.setCustomerTaxNumber(cellvalue);
						} else if (j == 34) {
							companyCustomerDO.setCustomerBank(cellvalue);
						} else if (j == 35) {
							companyCustomerDO.setCustomerAccountNum(cellvalue);
						} else if (j == 36) {
							companyCustomerDO.setCustomerContactSta(cellvalue);
						} else if (j == 37) {
							companyCustomerDO.setCustomerAddress(cellvalue);
						} else if (j == 38) {
							companyCustomerDO.setCustomerPhoneNum(cellvalue);
						} else if (j == 39) {
							companyCustomerDO.setCustomerFax(cellvalue);
						} else if (j == 40) {
							companyCustomerDO.setCustomerMailbox(cellvalue);
						} else if (j == 41) {
							companyCustomerDO.setCustomerUrl(cellvalue);
						} else if (j == 42) {
							companyCustomerDO.setCustomerPostcode(cellvalue);
						} else if (j == 43) {
							companyCustomerDO.setCustomerLeader(cellvalue);
						} else if (j == 44) {
							companyCustomerDO.setCustomerJobTitle(cellvalue);
						} else if (j == 45) {
							companyCustomerDO.setCustomerPaymentRate(new BigDecimal(cellvalue));
						} else if (j == 46) {
							companyCustomerDO.setCustomerHeatingShare(new BigDecimal(cellvalue));
						} else if (j == 47) {
							companyCustomerDO.setCustomerComplaintRate(new BigDecimal(cellvalue));
						} else if (j == 48) {
							companyCustomerDO.setCustomerHeatingArea(new BigDecimal(cellvalue));
						} else if (j == 49) {
							companyCustomerDO.setCustomerHeatingSourceNumber(Integer.valueOf(cellvalue));
						} else if (j == 50) {
							companyCustomerDO.setCustomerSteamArea(new BigDecimal(cellvalue));
						} else if (j == 51) {
							companyCustomerDO.setCustomerHotWaterArea(new BigDecimal(cellvalue));
						} else if (j == 52) {
							companyCustomerDO.setCustomerOwnHeatingSource(Integer.valueOf(cellvalue));
						} else if (j == 53) {
							companyCustomerDO.setCustomerOutHeatingSource(Integer.valueOf(cellvalue));
						} else if (j == 54) {
							companyCustomerDO.setCustomerHeatLoss(new BigDecimal(cellvalue));
						} else if (j == 55) {
							companyCustomerDO.setCustomerWaterLoss(new BigDecimal(cellvalue));
						} else if (j == 56) {
							companyCustomerDO.setCustomerElectrickLoss(new BigDecimal(cellvalue));
						} else if (j == 57) {
							companyCustomerDO.setCustomerPlanOneYear(cellvalue);
						} else if (j == 58) {
							companyCustomerDO.setCustomerPlanTowYear(cellvalue);
						} else if (j == 59) {
							companyCustomerDO.setCustomerPlanThreeYear(cellvalue);
						} else if (j == 60) {
							companyCustomerDO.setCustomerRemarks(cellvalue);
						}

					} // --->遍历列
					companyCustomerDO.setCustomerOperator(Long.toString(userid));
					companyCustomerDO.setCustomerOperateTime(new Date());

					rtn = companyCustomerDao.save(companyCustomerDO);
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
	public void export(String[] titles, ServletOutputStream out, List<CompanyCustomerDO> list) {
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
					CompanyCustomerDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					hssfRow.createCell(0).setCellValue(i + 1);
					// 企业客户编号
					String CustomerId = "";
					if (report.getCustomerId() != null) {
						CustomerId = report.getCustomerId();
					}
					hssfRow.createCell(0).setCellValue(CustomerId);
					// 省份
					String CustomerProvince = "";
					if (report.getCustomerProvince() != null) {
						CustomerProvince = report.getCustomerProvince();
					}
					hssfRow.createCell(1).setCellValue(CustomerProvince);
					// 城市
					String CustomerCity = "";
					if (report.getCustomerCity() != null) {
						CustomerCity = report.getCustomerCity();
					}
					hssfRow.createCell(2).setCellValue(CustomerCity);
					// 区县
					String CustomerCounty = "";
					if (report.getCustomerCounty() != null) {
						CustomerCounty = report.getCustomerCounty();
					}
					hssfRow.createCell(3).setCellValue(CustomerCounty);
					// 企业名称
					String CustomerName = "";
					if (report.getCustomerName() != null) {
						CustomerName = report.getCustomerName();
					}
					hssfRow.createCell(4).setCellValue(CustomerName);
					// 助记简称
					String CustomerSimpleName = "";
					if (report.getCustomerSimpleName() != null) {
						CustomerSimpleName = report.getCustomerSimpleName();
					}
					hssfRow.createCell(5).setCellValue(CustomerSimpleName);
					// 客户所有者
					String CustomerOwner = "";
					if (report.getCustomerOwner() != null) {
						CustomerOwner = report.getCustomerOwner();
					}
					hssfRow.createCell(6).setCellValue(CustomerOwner);
					// 销售负责人
					String CustomerSales = "";
					if (report.getCustomerSales() != null) {
						CustomerSales = report.getCustomerSales();
					}
					hssfRow.createCell(7).setCellValue(CustomerSales);
					// 产品需求
					String CustomerProduct = "";
					if (report.getCustomerProduct() != null) {
						CustomerProduct = report.getCustomerProduct();
					}
					hssfRow.createCell(8).setCellValue(CustomerProduct);
					// 需求简要描述
					String CustomerReqDes = "";
					if (report.getCustomerReqDes() != null) {
						CustomerReqDes = report.getCustomerReqDes();
					}
					hssfRow.createCell(9).setCellValue(CustomerReqDes);
					// 需求调研附件
					String CustomerAttachment = "";
					if (report.getCustomerAttachment() != null) {

						CustomerAttachment = String.valueOf(CustomerAttachment);
					}
					hssfRow.createCell(10).setCellValue(CustomerAttachment);
					// 客户类别
					String CustomerCategory = "";
					if (report.getCustomerCategory() != null) {
						CustomerCategory = report.getCustomerCategory();
					}
					hssfRow.createCell(11).setCellValue(CustomerCategory);
					// 企业性质
					String CustomerCharacter = "";
					if (report.getCustomerCharacter() != null) {
						CustomerCharacter = String.valueOf(report.getCustomerCharacter());
					}
					hssfRow.createCell(12).setCellValue(CustomerCharacter);
					// 客户状态
					String CustomerStatus = "";
					if (report.getCustomerStatus() != null) {
						CustomerStatus = String.valueOf(report.getCustomerStatus());
					}
					hssfRow.createCell(13).setCellValue(CustomerStatus);
					// 客户级别
					String CustomerLevel = "";
					if (report.getCustomerLevel() != null) {
						CustomerLevel = String.valueOf(report.getCustomerLevel());
					}
					hssfRow.createCell(14).setCellValue(CustomerLevel);
					// 销售阶段
					String CustomerSalePhase = "";
					if (report.getCustomerSalePhase() != null) {
						CustomerSalePhase = report.getCustomerSalePhase();
					}
					hssfRow.createCell(15).setCellValue(CustomerSalePhase);
					// 客户内部阶段
					String CustomerInnerPhase = "";
					if (report.getCustomerInnerPhase() != null) {
						CustomerInnerPhase = report.getCustomerInnerPhase();
					}
					hssfRow.createCell(16).setCellValue(CustomerInnerPhase);
					// 来源
					String CustomerSource = "";
					if (report.getCustomerSource() != null) {
						CustomerSource = report.getCustomerSource();
					}
					hssfRow.createCell(17).setCellValue(CustomerSource);
					// 行业
					String CustomerIndustry = "";
					if (report.getCustomerIndustry() != null) {
						CustomerIndustry = report.getCustomerIndustry();
					}
					hssfRow.createCell(18).setCellValue(CustomerIndustry);
					// 人员规模
					String CustomerStaffSize = "";
					if (report.getCustomerStaffSize() != null) {
						Integer CustomerStaffSize1 = report.getCustomerStaffSize();
						CustomerStaffSize = String.valueOf(CustomerStaffSize1);
					}
					hssfRow.createCell(19).setCellValue(CustomerStaffSize);
					// 信用等级
					String CustomerCreditRank = "";
					if (report.getCustomerCreditRank() != null) {
						Integer CustomerCreditRank1 = report.getCustomerCreditRank();
						CustomerCreditRank = String.valueOf(CustomerCreditRank1);
					}
					hssfRow.createCell(20).setCellValue(CustomerCreditRank);
					// 客户潜力
					String CustomerPotential = "";
					if (report.getCustomerPotential() != null) {
						CustomerPotential = report.getCustomerPotential();
					}
					hssfRow.createCell(21).setCellValue(CustomerPotential);
					// 员工数量
					String CustomerEmpNumber = "";
					if (report.getCustomerEmpNumber() != null) {
						Integer CustomerEmpNumber1 = report.getCustomerEmpNumber();
						CustomerEmpNumber = String.valueOf(CustomerEmpNumber1);
					}
					hssfRow.createCell(22).setCellValue(CustomerEmpNumber);
					// 上级单位
					String CustomerParent = "";
					if (report.getCustomerParent() != null) {
						CustomerParent = report.getCustomerParent();
					}
					hssfRow.createCell(23).setCellValue(CustomerParent);
					// 公司简介
					String CustomerIntroduction = "";
					if (report.getCustomerIntroduction() != null) {
						CustomerIntroduction = report.getCustomerIntroduction();
					}
					hssfRow.createCell(24).setCellValue(CustomerIntroduction);
					// 拜访交通方式
					String CustomerVisitMode = "";
					if (report.getCustomerVisitMode() != null) {
						CustomerVisitMode = report.getCustomerVisitMode();
					}
					hssfRow.createCell(25).setCellValue(CustomerVisitMode);
					// 旧客户编号
					String CustomerOldId = "";
					if (report.getCustomerOldId() != null) {
						CustomerOldId = report.getCustomerOldId();
					}
					hssfRow.createCell(26).setCellValue(CustomerOldId);
					// 子公司名称
					String CustomerChildCompany = "";
					if (report.getCustomerChildCompany() != null) {
						CustomerChildCompany = report.getCustomerChildCompany();
					}
					hssfRow.createCell(27).setCellValue(CustomerChildCompany);
					// 热点客户
					String CustomerHot = "";
					if (report.getCustomerHot() != null) {
						String CustomerHot1 = report.getCustomerHot();
						CustomerHot = String.valueOf(CustomerHot1);
					}
					hssfRow.createCell(28).setCellValue(CustomerHot);
					// 热度
					String CustomerHotRank = "";
					if (report.getCustomerHotRank() != null) {
						CustomerHotRank = report.getCustomerHotRank();
					}
					hssfRow.createCell(29).setCellValue(CustomerHotRank);
					// '热点客户分类
					String CustomerHotClassif = "";
					if (report.getCustomerHotClassif() != null) {
						CustomerHotClassif = report.getCustomerHotClassif();
					}
					hssfRow.createCell(30).setCellValue(CustomerHotClassif);
					// 预期成交金额
					String CustomerVolume = "";
					if (report.getCustomerVolume() != null) {
						BigDecimal CustomerVolume1 = report.getCustomerVolume();
						CustomerVolume = String.valueOf(CustomerVolume1);
					}
					hssfRow.createCell(31).setCellValue(CustomerVolume);
					// 热点说明
					String CustomerHotDesc = "";
					if (report.getCustomerHotDesc() != null) {
						CustomerHotDesc = report.getCustomerHotDesc();
					}
					hssfRow.createCell(32).setCellValue(CustomerHotDesc);
					// 开票名称
					String CustomerInvoiceName = "";
					if (report.getCustomerInvoiceName() != null) {
						CustomerInvoiceName = report.getCustomerInvoiceName();
					}
					hssfRow.createCell(33).setCellValue(CustomerInvoiceName);
					// 纳税人税号
					String CustomerTaxNumber = "";
					if (report.getCustomerTaxNumber() != null) {
						CustomerTaxNumber = report.getCustomerTaxNumber();
					}
					hssfRow.createCell(34).setCellValue(CustomerTaxNumber);
					// 开户行
					String CustomerBank = "";
					if (report.getCustomerBank() != null) {
						CustomerBank = report.getCustomerBank();
					}
					hssfRow.createCell(35).setCellValue(CustomerBank);
					// 账号
					String CustomerAccountNum = "";
					if (report.getCustomerAccountNum() != null) {
						CustomerAccountNum = report.getCustomerAccountNum();
					}
					hssfRow.createCell(36).setCellValue(CustomerAccountNum);
					// 联系情况
					String CustomerContactSta = "";
					if (report.getCustomerContactSta() != null) {
						CustomerContactSta = report.getCustomerContactSta();
					}
					hssfRow.createCell(37).setCellValue(CustomerContactSta);
					// 企业办公地址
					String CustomerAddress = "";
					if (report.getCustomerAddress() != null) {
						CustomerAddress = report.getCustomerAddress();
					}
					hssfRow.createCell(38).setCellValue(CustomerAddress);
					// 电话号码
					String CustomerPhoneNum = "";
					if (report.getCustomerPhoneNum() != null) {
						CustomerPhoneNum = report.getCustomerPhoneNum();
					}
					hssfRow.createCell(39).setCellValue(CustomerPhoneNum);
					// 传真
					String CustomerFax = "";
					if (report.getCustomerFax() != null) {
						CustomerFax = report.getCustomerFax();
					}
					hssfRow.createCell(40).setCellValue(CustomerFax);
					// 邮箱
					String CustomerMailbox = "";
					if (report.getCustomerMailbox() != null) {
						CustomerMailbox = report.getCustomerMailbox();
					}
					hssfRow.createCell(41).setCellValue(CustomerMailbox);
					// 官网地址
					String CustomerUrl = "";
					if (report.getCustomerUrl() != null) {
						CustomerUrl = report.getCustomerUrl();
					}
					hssfRow.createCell(42).setCellValue(CustomerUrl);
					// 邮编
					String CustomerPostcode = "";
					if (report.getCustomerPostcode() != null) {
						CustomerPostcode = report.getCustomerPostcode();
					}
					hssfRow.createCell(43).setCellValue(CustomerPostcode);
					// 企业负责人
					String CustomerLeader = "";
					if (report.getCustomerLeader() != null) {
						CustomerLeader = report.getCustomerLeader();
					}
					hssfRow.createCell(44).setCellValue(CustomerLeader);
					// 职务
					String CustomerJobTitle = "";
					if (report.getCustomerJobTitle() != null) {
						CustomerJobTitle = report.getCustomerJobTitle();
					}
					hssfRow.createCell(45).setCellValue(CustomerJobTitle);
					// 收费收缴率
					String CustomerPaymentRate = "";
					if (report.getCustomerPaymentRate() != null) {
						BigDecimal CustomerPaymentRate1 = report.getCustomerPaymentRate();
						CustomerPaymentRate = String.valueOf(CustomerPaymentRate1);
					}
					hssfRow.createCell(46).setCellValue(CustomerPaymentRate);
					// 企业占全市热化率
					String CustomerHeatingShare = "";
					if (report.getCustomerHeatingShare() != null) {
						BigDecimal CustomerHeatingShare1 = report.getCustomerHeatingShare();
						CustomerHeatingShare = String.valueOf(CustomerHeatingShare1);
					}
					hssfRow.createCell(47).setCellValue(CustomerHeatingShare);
					// 投诉率
					String CustomerComplaintRate = "";
					if (report.getCustomerComplaintRate() != null) {
						BigDecimal CustomerComplaintRate1 = report.getCustomerComplaintRate();
						CustomerComplaintRate = String.valueOf(CustomerComplaintRate1);
					}
					hssfRow.createCell(48).setCellValue(CustomerComplaintRate);
					// 供热面积
					String CustomerHeatingArea = "";
					if (report.getCustomerHeatingArea() != null) {
						BigDecimal CustomerHeatingArea1 = report.getCustomerHeatingArea();
						CustomerHeatingArea = String.valueOf(CustomerHeatingArea1);
					}
					hssfRow.createCell(49).setCellValue(CustomerHeatingArea);
					// 热力站数量
					String CustomerHeatingSourceNumber = "";
					if (report.getCustomerHeatingSourceNumber() != null) {
						Integer CustomerHeatingSourceNumber1 = report.getCustomerHeatingSourceNumber();
						CustomerHeatingSourceNumber = String.valueOf(CustomerHeatingSourceNumber1);
					}
					hssfRow.createCell(50).setCellValue(CustomerHeatingSourceNumber);
					// 蒸汽面积
					String CustomerSteamArea = "";
					if (report.getCustomerSteamArea() != null) {
						BigDecimal CustomerSteamArea1 = report.getCustomerSteamArea();
						CustomerHeatingArea = String.valueOf(CustomerSteamArea1);
					}
					hssfRow.createCell(51).setCellValue(CustomerSteamArea);
					// 热水面积
					String CustomerHotWaterArea = "";
					if (report.getCustomerHotWaterArea() != null) {
						BigDecimal CustomerHotWaterArea1 = report.getCustomerHotWaterArea();
						CustomerHotWaterArea = String.valueOf(CustomerHotWaterArea1);
					}
					hssfRow.createCell(52).setCellValue(CustomerHotWaterArea);
					// 自有热源
					String CustomerOwnHeatingSource = "";
					if (report.getCustomerOwnHeatingSource() != null) {
						Integer CustomerOwnHeatingSource1 = report.getCustomerOwnHeatingSource();
						CustomerOwnHeatingSource = String.valueOf(CustomerOwnHeatingSource1);
					}
					hssfRow.createCell(53).setCellValue(CustomerOwnHeatingSource);
					// 外购热源
					String CustomerOutHeatingSource = "";
					if (report.getCustomerOutHeatingSource() != null) {
						Integer CustomerOutHeatingSource1 = report.getCustomerOutHeatingSource();
						CustomerOutHeatingSource = String.valueOf(CustomerOutHeatingSource1);
					}
					hssfRow.createCell(54).setCellValue(CustomerOutHeatingSource);
					// 热耗'
					String CustomerHeatLoss = "";
					if (report.getCustomerHeatLoss() != null) {
						BigDecimal CustomerHeatLoss1 = report.getCustomerHeatLoss();
						CustomerHeatLoss = String.valueOf(CustomerHeatLoss1);
					}
					hssfRow.createCell(55).setCellValue(CustomerHeatLoss);
					// 水耗
					String CustomerWaterLoss = "";
					if (report.getCustomerWaterLoss() != null) {
						BigDecimal CustomerWaterLoss1 = report.getCustomerWaterLoss();
						CustomerWaterLoss = String.valueOf(CustomerWaterLoss1);
					}
					hssfRow.createCell(56).setCellValue(CustomerWaterLoss);
					// 电耗
					String CustomerElectrickLoss = "";
					if (report.getCustomerElectrickLoss() != null) {
						BigDecimal CustomerElectrickLoss1 = report.getCustomerElectrickLoss();
						CustomerElectrickLoss = String.valueOf(CustomerElectrickLoss1);
					}
					hssfRow.createCell(57).setCellValue(CustomerElectrickLoss);
					// 未来一年新增规划
					String CustomerPlanOneYear = "";
					if (report.getCustomerPlanOneYear() != null) {
						CustomerPlanOneYear = report.getCustomerPlanOneYear();
					}
					hssfRow.createCell(58).setCellValue(CustomerPlanOneYear);
					// 未来两年新增规划
					String CustomerPlanTowYear = "";
					if (report.getCustomerPlanTowYear() != null) {
						CustomerPlanTowYear = report.getCustomerPlanTowYear();
					}
					hssfRow.createCell(59).setCellValue(CustomerPlanTowYear);
					// 未来三年新增规划
					String CustomerPlanThreeYear = "";
					if (report.getCustomerPlanThreeYear() != null) {
						CustomerPlanThreeYear = report.getCustomerPlanThreeYear();
					}
					hssfRow.createCell(60).setCellValue(CustomerPlanThreeYear);
					// 备注
					String CustomerRemarks = "";
					if (report.getCustomerRemarks() != null) {
						CustomerRemarks = report.getCustomerRemarks();
					}
					hssfRow.createCell(61).setCellValue(CustomerRemarks);

					// 操作人
					String customerOperatorName = "";
					if (report.getCustomerOperatorName() != null) {
						customerOperatorName = report.getCustomerOperatorName();
					}
					hssfRow.createCell(62).setCellValue(customerOperatorName);
					// 操作时间
					String getCustomerOperateTime = "";
					if (report.getCustomerOperateTime() != null) {
						getCustomerOperateTime = sdf.format(report.getCustomerOperateTime());
					}
					hssfRow.createCell(63).setCellValue(getCustomerOperateTime);
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
				throw new Exception("企业信息导出失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 导出excel
	 */
	public List<CompanyCustomerDO> getQuery(Map<String, Object> params) {
		List<CompanyCustomerDO> returnData = companyCustomerDao.list(params);
		return returnData;

		/*
		 * for (int i = 0; i < returnDate.size(); i++) { }
		 */
	}

	@Override
	public List<DictDO> listAllDicByArea(String areaId) {
		// TODO Auto-generated method stub
		return companyCustomerDao.listAllDicByArea(areaId);
	}
}