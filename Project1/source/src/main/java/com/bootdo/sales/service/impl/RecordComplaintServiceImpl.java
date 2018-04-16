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
import com.bootdo.payment.domain.ReceivedDO;
import com.bootdo.sales.dao.RecordComplaintDao;
import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.RecordComplaintDO;
import com.bootdo.sales.service.RecordComplaintService;

@Service
public class RecordComplaintServiceImpl implements RecordComplaintService {
	@Autowired
	private RecordComplaintDao recordComplaintDao;

	@Override
	public RecordComplaintDO get(String complaintId) {
		return recordComplaintDao.get(complaintId);
	}

	@Override
	public List<RecordComplaintDO> list(Map<String, Object> map) {
		return recordComplaintDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return recordComplaintDao.count(map);
	}

	@Override
	public int save(RecordComplaintDO recordComplaint) {
		return recordComplaintDao.save(recordComplaint);
	}

	@Override
	public int update(RecordComplaintDO recordComplaint) {
		return recordComplaintDao.update(recordComplaint);
	}

	@Override
	public int remove(String complaintId) {
		return recordComplaintDao.remove(complaintId);
	}

	@Override
	public int batchRemove(String[] complaintIds) {
		return recordComplaintDao.batchRemove(complaintIds);
	}

	@Override
	public List<DictDO> listDic() {
		return recordComplaintDao.listDic();
	}

	@Override
	public List<DictDO> listDicxmbh() {
		return recordComplaintDao.listDicxmbh();
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
				RecordComplaintDO recordComplaintDO = new RecordComplaintDO(); // l1

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
							recordComplaintDO.setProjectId(cellvalue);
						} else if (j == 1) {
							recordComplaintDO.setCustomerId(cellvalue);
						} else if (j == 2) {
							recordComplaintDO.setComplaintProduct(cellvalue);
						} else if (j == 3) {
							recordComplaintDO.setComplaintName(cellvalue);
						} else if (j == 4) {
							recordComplaintDO.setComplaintDate(new Date());
						} else if (j == 5) {
							recordComplaintDO.setComplaintCompany(cellvalue);
						} else if (j == 6) {
							recordComplaintDO.setComplaintFeedbackType(cellvalue);
						} else if (j == 7) {
							recordComplaintDO.setComplaintProblem(cellvalue);
						} else if (j == 8) {
							recordComplaintDO.setComplaintAttachmentCustomer(cellvalue);
						} else if (j == 9) {
							recordComplaintDO.setComplaintProblemRemarks(cellvalue);
						} else if (j == 11) {
							recordComplaintDO.setComplaintAfterSaleType(cellvalue);
						} else if (j == 12) {
							recordComplaintDO.setComplaintProblemDescription(cellvalue);
						} else if (j == 13) {
							recordComplaintDO.setComplaintOperator(new Long(cellvalue));
						} else if (j == 14) {
							recordComplaintDO.setComplaintExecutor(cellvalue);
						} else if (j == 15) {
							recordComplaintDO.setComplaintResult(cellvalue);
						} else if (j == 16) {
							recordComplaintDO.setComplaintAfterSaleRemarks(cellvalue);
						} else if (j == 17) {
							recordComplaintDO.setComplaintPhoneNumber(cellvalue);
						} else if (j == 18) {
							recordComplaintDO.setComplaintMailbox(cellvalue);
						} else if (j == 19) {
							recordComplaintDO.setComplaintRecorder(new Long(cellvalue));
						}
					} // --->遍历列
					recordComplaintDO.setComplaintId(String.valueOf(i));
					recordComplaintDO.setComplaintRecordTime(new Date());
					recordComplaintDO.setComplaintCreateTime(new Date());
					rtn = recordComplaintDao.save(recordComplaintDO);
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
	public void export(String[] titles, ServletOutputStream out, List<RecordComplaintDO> list) {
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
					RecordComplaintDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					hssfRow.createCell(0).setCellValue(i + 1);
					// 客户投诉记录编号
					String ComplaintId = "";
					if (report.getComplaintId() != null) {
						ComplaintId = report.getComplaintId();
					}
					hssfRow.createCell(0).setCellValue(ComplaintId);
					// 项目编号
					String ProjectId = "";
					if (report.getProjectId() != null) {
						ProjectId = report.getProjectId();
					}
					hssfRow.createCell(1).setCellValue(ProjectId);
					// 企业客户编号
					String CustomerId = "";
					if (report.getCustomerId() != null) {
						CustomerId = report.getCustomerId();
					}
					hssfRow.createCell(2).setCellValue(CustomerId);
					// 使用产品
					String ComplaintProduct = "";
					if (report.getComplaintProduct() != null) {
						ComplaintProduct = report.getComplaintProduct();
					}
					hssfRow.createCell(3).setCellValue(ComplaintProduct);
					// 投诉人姓名
					String ComplaintName = "";
					if (report.getComplaintName() != null) {
						ComplaintName = report.getComplaintName();
					}
					hssfRow.createCell(4).setCellValue(ComplaintName);
					// 投诉时间
					String ComplaintDate = "";
					if (report.getComplaintDate() != null) {
						Date getComplaintDate1 = report.getComplaintDate();
						ComplaintDate = sdf.format(getComplaintDate1);
					}
					hssfRow.createCell(5).setCellValue(ComplaintDate);
					// 所在单位
					String getComplaintCompany = "";
					if (report.getComplaintCompany() != null) {
						getComplaintCompany = report.getComplaintCompany();
					}
					hssfRow.createCell(6).setCellValue(getComplaintCompany);
					// 投诉方式
					String ComplaintFeedbackType = "";
					if (report.getComplaintFeedbackType() != null) {
						ComplaintFeedbackType = report.getComplaintFeedbackType();
					}
					hssfRow.createCell(7).setCellValue(ComplaintFeedbackType);
					// 投诉内容
					String ComplaintProblem = "";
					if (report.getComplaintProblem() != null) {
						ComplaintProblem = report.getComplaintProblem();
					}
					hssfRow.createCell(8).setCellValue(ComplaintProblem);
					// 客服发送资料
					String ComplaintAttachmentCustomer = "";
					if (report.getComplaintAttachmentCustomer() != null) {
						ComplaintAttachmentCustomer = report.getComplaintAttachmentCustomer();
					}
					hssfRow.createCell(10).setCellValue(ComplaintAttachmentCustomer);
					// 问题描述备注
					String ComplaintProblemRemarks = "";
					if (report.getComplaintProblemRemarks() != null) {
						ComplaintProblemRemarks = report.getComplaintProblemRemarks();
					}
					hssfRow.createCell(11).setCellValue(ComplaintProblemRemarks);
					// 售后服务类型
					String ComplaintAfterSaleType = "";
					if (report.getComplaintAfterSaleType() != null) {
						ComplaintAfterSaleType = report.getComplaintAfterSaleType();
					}
					hssfRow.createCell(12).setCellValue(ComplaintAfterSaleType);
					// 问题描述
					String ComplaintProblemDescription = "";
					if (report.getComplaintProblemDescription() != null) {
						ComplaintProblemDescription = report.getComplaintProblemDescription();
					}
					hssfRow.createCell(13).setCellValue(ComplaintProblemDescription);
					// 处理人
					String ComplaintOperator = "";
					if (report.getComplaintOperator() != null) {
						ComplaintOperator = String.valueOf(report.getComplaintOperator());
						;
					}
					hssfRow.createCell(14).setCellValue(ComplaintOperator);
					// 处理过程
					String ComplaintExecutor = "";
					if (report.getComplaintExecutor() != null) {
						ComplaintExecutor = report.getComplaintExecutor();
					}
					hssfRow.createCell(15).setCellValue(ComplaintExecutor);
					// 处理结果
					String ComplaintResult = "";
					if (report.getComplaintResult() != null) {
						ComplaintResult = report.getComplaintResult();
					}
					hssfRow.createCell(16).setCellValue(ComplaintResult);
					// 售后备注
					String ComplaintAfterSaleRemarks = "";
					if (report.getComplaintAfterSaleRemarks() != null) {
						ComplaintAfterSaleRemarks = report.getComplaintAfterSaleRemarks();
					}
					hssfRow.createCell(17).setCellValue(ComplaintAfterSaleRemarks);
					// 客户电话
					String ComplaintPhoneNumber = "";
					if (report.getComplaintPhoneNumber() != null) {
						ComplaintPhoneNumber = report.getComplaintPhoneNumber();
					}
					hssfRow.createCell(18).setCellValue(ComplaintPhoneNumber);
					// 客户邮箱
					String ComplaintMailbox = "";
					if (report.getComplaintMailbox() != null) {
						ComplaintMailbox = report.getComplaintMailbox();
					}
					hssfRow.createCell(19).setCellValue(ComplaintMailbox);
					// 操作人
					String ComplaintRecorder = "";
					if (report.getComplaintRecorder() != null) {
						long getComplaintRecorder1 = report.getComplaintRecorder();
						ComplaintDate = sdf.format(getComplaintRecorder1);
					}
					hssfRow.createCell(20).setCellValue(ComplaintRecorder);
					// 修改时间
					String ComplaintRecordTime = "";
					if (report.getComplaintRecordTime() != null) {
						Date ComplaintRecordTime1 = report.getComplaintRecordTime();
						ComplaintRecordTime = sdf.format(ComplaintRecordTime1);
					}
					hssfRow.createCell(21).setCellValue(ComplaintRecordTime);
					// 业务创建时间
					String ComplaintCreateTime = "";
					if (report.getComplaintCreateTime() != null) {
						Date getComplaintCreateTime1 = report.getComplaintCreateTime();
						ComplaintCreateTime = sdf.format(getComplaintCreateTime1);
					}
					hssfRow.createCell(22).setCellValue(ComplaintCreateTime);
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
	public List<RecordComplaintDO> getQuery(Map<String, Object> params) {
		List<RecordComplaintDO> returnData = recordComplaintDao.list(params);
		return returnData;
	}

}