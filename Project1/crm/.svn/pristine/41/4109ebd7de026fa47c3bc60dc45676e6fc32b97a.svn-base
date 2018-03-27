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
import com.bootdo.payment.domain.ProjectExpenditureDO;
import com.bootdo.payment.service.ProjectExpenditureService;


@Service
public class ProjectExpenditureImpl implements ProjectExpenditureService {
	@Autowired
	private  ProjectExpenditureDao projectExpenditureDao;
	
	@Override
	public ProjectExpenditureDO get(String projectId){
		return projectExpenditureDao.get(projectId);
	}
	
	@Override
	public List<ProjectExpenditureDO> list(Map<String, Object> map){
		return projectExpenditureDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectExpenditureDao.count(map);
	}
	
	@Override
	public int save(ProjectExpenditureDO ProjectExpenditure){
		return projectExpenditureDao.save(ProjectExpenditure);
	}
	
	@Override
	public int update(ProjectExpenditureDO ProjectExpenditure){
		return projectExpenditureDao.update(ProjectExpenditure);
	}
	
	@Override
	public int remove(String projectId){
		return projectExpenditureDao.remove(projectId);
	}
	
	@Override
	public int batchRemove(String[] projectIds){
		return projectExpenditureDao.batchRemove(projectIds);
	}
	@Override	
	public List<DictDO> listDic(){
		return projectExpenditureDao.listDic();
	}

	@Override
	public List<DictDO> listDicManager() {
		return projectExpenditureDao.listDicManager();
	}
	
	/**
	 * 导出Excel写入文件方法
	 */
	public void export(String[] titles, ServletOutputStream out, List<ProjectExpenditureDO> list) {
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
								ProjectExpenditureDO report = list.get(i);
								/** 第六步，创建单元格，并设置值 **/
								// 序号
								hssfRow.createCell(0).setCellValue(i + 1);
								// 项目编号
								String projectId = "";
								if (report.getProjectId() != null) {
									projectId = sdf.format(report.getProjectId());
								}
								hssfRow.createCell(1).setCellValue(projectId);
								// 企业客户编号
								String customerId = "";
								if (report.getCustomerId() != null) {
									customerId = report.getCustomerId();
								}
								hssfRow.createCell(2).setCellValue(customerId);
								// 项目名称
								String projectName = "";
								if (report.getProjectName() != null) {
									projectName = report.getProjectName();
								}
								hssfRow.createCell(3).setCellValue(projectName);
								// 销售负责人
								String ProjectSales = "";
								if (report.getProjectSales() != null) {
									ProjectSales = report.getProjectSales();
								}
								hssfRow.createCell(4).setCellValue(ProjectSales);
								// 项目开始时间
								String projectBeginDate = "";
								if (report.getProjectBeginDate() != null) {
									projectBeginDate = sdf.format(report.getProjectBeginDate());
								}
								hssfRow.createCell(5).setCellValue(projectBeginDate);
								// 项目结束时间
								String projectEndDate = "";
								if (report.getProjectEndDate() != null) {
									projectEndDate = sdf.format(report.getProjectEndDate());
								}
								hssfRow.createCell(6).setCellValue(projectEndDate);
								// 负责人
								String projectOwner = "";
								if (report.getProjectOwner() != null) {
									projectOwner = report.getProjectOwner();
								}
								hssfRow.createCell(7).setCellValue(projectOwner);
								//研发经理
								String ProjectManager = "";
								if (report.getProjectManager() != null) {
									ProjectManager = report.getProjectManager();
								}
								hssfRow.createCell(8).setCellValue(ProjectManager);
								// 研发开始时间
								String ProjectDevelopmentBeginDate = "";
								if (report.getProjectDevelopmentBeginDate() != null) {
									ProjectDevelopmentBeginDate = sdf.format(report.getProjectDevelopmentBeginDate());
								}
								hssfRow.createCell(9).setCellValue(ProjectDevelopmentBeginDate);
								// 研发结束时间
								String projectDevelopmentEndDate = "";
								if (report.getProjectDevelopmentEndDate() != null) {
									projectDevelopmentEndDate = sdf.format(report.getProjectDevelopmentEndDate());
								}
								hssfRow.createCell(10).setCellValue(projectDevelopmentEndDate);
								// 项目类型
								String projectGategory = "";
								if (report.getProjectGategory() != null) {
									projectGategory = report.getProjectGategory();
								}
								hssfRow.createCell(11).setCellValue(projectGategory);
								// 项目阶段
								String projectPhase = "";
								if (report.getProjectPhase() != null) {
									projectPhase = report.getProjectPhase();
								}
								hssfRow.createCell(12).setCellValue(projectPhase);
								// 项目描述
								String projectDescription = "";
								if (report.getProjectDescription() != null) {
									projectDescription = report.getProjectDescription();
								}
								hssfRow.createCell(13).setCellValue(projectDescription);
								// 旧项目编号
								String projectOldId = "";
								if (report.getProjectOldId() != null) {
									projectOldId = report.getProjectOldId();
								}
								hssfRow.createCell(14).setCellValue(projectOldId);
								// 备注
								String projectRemarks = "";
								if (report.getProjectRemarks() != null) {
									projectRemarks = report.getProjectRemarks();
								}
								hssfRow.createCell(15).setCellValue(projectRemarks);
								// 创建人
								String ProjectOperatorName = "";
								if (report.getProjectOperatorName() != null) {
									ProjectOperatorName = report.getProjectOperatorName();
								}
								hssfRow.createCell(16).setCellValue(ProjectOperatorName);
								// 创建时间
								String projectOperateTime = "";
								if (report.getProjectOperateTime() != null) {
									projectOperateTime = sdf.format(report.getProjectOperateTime());
								}
								hssfRow.createCell(17).setCellValue(projectOperateTime);
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
	public List<ProjectExpenditureDO> getQuery(Map<String, Object> params) {
		List<ProjectExpenditureDO> returnData=projectExpenditureDao.list(params);
		return returnData;
	}

	

	
}
