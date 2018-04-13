package com.bootdo.inner.service.impl;

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
import com.bootdo.inner.dao.OrgJobDao;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.inner.domain.OrgJobDO;
import com.bootdo.inner.service.OrgJobService;



@Service
public class OrgJobServiceImpl implements OrgJobService {
	@Autowired
	private OrgJobDao orgJobDao;
	
	@Override
	public OrgJobDO get(String jobId){
		return orgJobDao.get(jobId);
	}
	
	@Override
	public List<OrgJobDO> list(Map<String, Object> map){
		return orgJobDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orgJobDao.count(map);
	}
	
	@Override
	public int save(OrgJobDO orgJob){
		return orgJobDao.save(orgJob);
	}
	
	@Override
	public int update(OrgJobDO orgJob){
		return orgJobDao.update(orgJob);
	}
	
	@Override
	public int remove(String jobId){
		return orgJobDao.remove(jobId);
	}
	
	@Override
	public int batchRemove(String[] jobIds){
		return orgJobDao.batchRemove(jobIds);
	}

	@Override
	public List<DictDO> listDic() {
		return orgJobDao.listDic();
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
				OrgJobDO orgJobDO = new OrgJobDO(); // 本表
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
							orgJobDO.setDeptId(cellvalue);
						} else if (j == 1) {
							orgJobDO.setDeptId(cellvalue);
						} else if (j == 2) {
							orgJobDO.setJobName(cellvalue);
						} else if (j == 3) {
							orgJobDO.setJobDept(cellvalue);
						} else if (j == 4) {
							orgJobDO.setJobRank(cellvalue);
						} else if (j == 5) {
							orgJobDO.setJobRankDescription(cellvalue);
						} else if (j == 6) {
							orgJobDO.setJobDescrription(cellvalue);
						} else if (j == 7) {
							orgJobDO.setJobRemarks(cellvalue);
						} else if (j == 8) {
							orgJobDO.setJobOperator(Long.valueOf(cellvalue));
						} else if (j == 9) {
							orgJobDO.setCreateJobDate(new Date());
						} 
					} // --->遍历列
					//orgJobDO.setJobId(String.valueOf(i));
					orgJobDO.setJobOperateTime(new Date());
					rtn = orgJobDao.save(orgJobDO);
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
	public void export(String[] titles, ServletOutputStream out, List<OrgJobDO> list) {
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
					OrgJobDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					hssfRow.createCell(0).setCellValue(i + 1);
					// 岗位编号
					String JobId = "";
					if (report.getJobId() != null) {
						JobId = report.getJobId();
					}
					hssfRow.createCell(0).setCellValue(JobId);
					// 部门编号
					String DeptId = "";
					if (report.getDeptId() != null) {
						DeptId = report.getDeptId();
					}
					hssfRow.createCell(1).setCellValue(DeptId);
					// 岗位名称
					String JobName = "";
					if (report.getJobId() != null) {
						JobName = report.getJobName();
					}
					hssfRow.createCell(2).setCellValue(JobName);
					// 所属部门
					String JobDept = "";
					if (report.getJobDept() != null) {
						JobDept = report.getJobDept();
					}
					hssfRow.createCell(3).setCellValue(JobDept);
					// 级别名称
					String JobRank = "";
					if (report.getJobRank() != null) {
						JobRank = report.getJobRank();
					}
					hssfRow.createCell(4).setCellValue(JobRank);
					// 级别描述
					String JobRankDescription = "";
					if (report.getJobRankDescription() != null) {
						JobRankDescription = report.getJobRankDescription();
					}
					hssfRow.createCell(5).setCellValue(JobRankDescription);
					// 岗位描述
					String JobDescrription = "";
					if (report.getJobDescrription() != null) {
						JobDescrription = report.getJobDescrription();
					}
					hssfRow.createCell(6).setCellValue(JobDescrription);
					// 备注
					String JobRemarks = "";
					if (report.getJobRemarks() != null) {
						JobRemarks = report.getJobRemarks();
					}
					hssfRow.createCell(7).setCellValue(JobRemarks);
					// 操作人姓名
					String jobOperatorName = "";
					if (report.getJobOperatorName() != null) {
						String jobOperatorName1 = report.getJobOperatorName();
						jobOperatorName = String.valueOf(jobOperatorName1);
					}
					hssfRow.createCell(8).setCellValue(jobOperatorName);
					// 操作时间
					String JobOperateTime = "";
					if (report.getJobOperateTime() != null) {
						Date getJobOperateTime1 = report.getJobOperateTime();
						JobOperateTime = String.valueOf(getJobOperateTime1);
					}
					hssfRow.createCell(9).setCellValue(JobOperateTime);
					// 岗位设立时间
					String CreateJobDate = "";
					if (report.getCreateJobDate() != null) {
						Date getCreateJobDate1 = report.getCreateJobDate();
						CreateJobDate = String.valueOf(getCreateJobDate1);
					}
					hssfRow.createCell(10).setCellValue(CreateJobDate);
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
	public List<OrgJobDO> getQuery(Map<String, Object> params) {
		List<OrgJobDO> returnData=orgJobDao.list(params);
		return returnData;
	}
}
