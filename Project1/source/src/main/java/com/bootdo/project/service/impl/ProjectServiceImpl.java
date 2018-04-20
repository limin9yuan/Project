package com.bootdo.project.service.impl;

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
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.Query;
import com.bootdo.project.dao.ProjectDao;
import com.bootdo.project.dao.ProjectDeptDao;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.domain.ProjectDeptDO;
import com.bootdo.project.service.ProjectService;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;



@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ProjectDao proProjectMapper;
	@Autowired
	private ProjectDeptDao projectDeptDao;
	
	
	@Override
	public ProjectDO get(String projectId){
		return projectDao.get(projectId);
	}
	
	@Override
	public List<ProjectDO> list(Map<String, Object> map){
		return projectDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectDao.count(map);
	}
	
	@Override
	public int save(ProjectDO project){
		String deptids = project.getDeptId();
		String[] arrayDept = deptids.split(",");
		for(int i=0;i<arrayDept.length;i++){
			ProjectDeptDO projectDept = new ProjectDeptDO();
			//projectDept.setProjectId(project.getProjectId());
			projectDept.setProjectId(project.getProjectRelatedId());
			projectDept.setDeptId(arrayDept[i]);
			projectDept.setOperateTime(new Date());
			projectDept.setOperator(project.getProjectOperator());
			projectDeptDao.save(projectDept);
			//arrayDept[i];
		}
		int r = projectDao.save(project);
		return r;
		//return projectDao.save(project);
	}
	
	@Override
	public int update(ProjectDO project){
		
		String deptids = project.getDeptId();
		String[] arrayDept = deptids.split(",");
		for(int i=0;i<arrayDept.length;i++){
			ProjectDeptDO projectDept = new ProjectDeptDO();
			//projectDept.setProjectId(project.getProjectId());
			projectDept.setProjectId(project.getProjectId());
			projectDept.setDeptId(arrayDept[i]);
			projectDept.setOperateTime(new Date());
			projectDept.setOperator(project.getProjectOperator());
			projectDeptDao.update(projectDept);
			//arrayDept[i];
		}
		int r = projectDao.update(project);
		return r;
		//return projectDao.update(project);
	}
	
	@Override
	public int remove(String projectId){
		
		projectDeptDao.remove(projectId);			
		return projectDao.remove(projectId);
	}
	
	@Override
	public int batchRemove(String[] projectIds){
		return projectDao.batchRemove(projectIds);
	}
	
	@Override
	public Tree<ProjectDO> getTree() {
		List<Tree<ProjectDO>> trees = new ArrayList<Tree<ProjectDO>>();
		List<ProjectDO> proProjects = proProjectMapper.list(new HashMap<String,Object>(16));
		for (ProjectDO proProject : proProjects) {
			Tree<ProjectDO> tree = new Tree<ProjectDO>();
			tree.setId(proProject.getProjectId().toString());
			tree.setParentId(proProject.getParentId().toString());
			tree.setText(proProject.getProjectName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<ProjectDO> t = BuildTree.build(trees);
		return t;
	}
	
	
	@Override	
	public List<DictDO> listDic(){
		return projectDao.listDic();
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
				ProjectDO projectDO = new ProjectDO(); // 售前项目信息表

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
							projectDO.setProjectId(cellvalue);
						}else if (j == 1) {
							projectDO.setCustomerId(cellvalue);
						}else if (j == 2) {
							projectDO.getCollectionId();
						}else if (j == 3) {
							projectDO.setProjectName(cellvalue);
						}else if (j == 4) {
							projectDO.setProjectSales(cellvalue);
						}else if (j == 5) {
							projectDO.setProjectBeginDate(cellvalue);
						}else if (j == 6) {
							projectDO.setProjectEndDate(cellvalue);
						}else if (j == 7) {
							projectDO.setProjectOwner(cellvalue);
						}else if (j == 8) {
							projectDO.setProjectManager(cellvalue);
						}else if (j == 9) {
							projectDO.setProjectDevelopmentBeginDate(cellvalue);
						}else if (j == 10) {
							projectDO.setProjectDevelopmentEndDate(cellvalue);
						}else if (j == 11) {
							projectDO.setProjectGategory(cellvalue);
						}else if (j == 12) {
							projectDO.setProjectPhase(cellvalue);
						}else if (j == 13) {
							projectDO.setProjectDescription(cellvalue);
						}else if (j == 14) {
							projectDO.setProjectOldId(cellvalue);
						}else if (j == 15) {
							projectDO.setProjectRemarks(cellvalue);
						}else if (j == 16) {
							projectDO.setFllowType(cellvalue);
						}else if (j == 17) {
							projectDO.setIfOutSource(cellvalue);
						}else if (j == 18) {
							projectDO.setDeliveryStatus(cellvalue);
						}else if (j == 19) {
							projectDO.setProjectRelatedId(cellvalue);
						}else if (j == 20) {
							projectDO.setDeptId(cellvalue);
						}
					} // --->遍历列
					//salesProjectDO.setProjectId(String.valueOf(i));
					projectDO.setProjectOperator(userid); 
					projectDO.setProjectOperateTime(new Date());
					projectDO.setProjectCreator(userid); 
					projectDO.setProjectCreateTime(new Date());
					//projectDO.setProjectOwnerName(projectDO.getProjectBusiness());
					//projectDO.setContractCenterratio(projectDO.getContractCenterratio());
					projectDO.setCustomerName(projectDO.getCustomerId());
					rtn = projectDao.save(projectDO);
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
	public void export(String[] titles, ServletOutputStream out, List<ProjectDO> list) {
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
					ProjectDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					//hssfRow.createCell(0).setCellValue(i + 1);
					// 项目编号
					String projectId = "";
					if (report.getProjectId() != null) {
						projectId = report.getProjectId();
					}
					hssfRow.createCell(0).setCellValue(projectId);
					// 企业客户编号
					String customerId = "";
					if (report.getCustomerId() != null) {
						customerId = report.getCustomerId();
					}
					hssfRow.createCell(1).setCellValue(customerId);
					// 项目名称
					String projectName = "";
					if (report.getProjectName() != null) {
						projectName = report.getProjectName();
					}
					hssfRow.createCell(2).setCellValue(projectName);
					// 销售负责人
					String projectSalesName = "";
					if (report.getProjectSalesName() != null) {
						projectSalesName = report.getProjectSalesName();
					}
					hssfRow.createCell(3).setCellValue(projectSalesName);
					// 项目开始时间
					String projectBeginDate = "";
					if (report.getProjectBeginDate() != null) {
						//projectBeginDate = sdf.format(report.getProjectBeginDate());
						projectBeginDate = report.getProjectBeginDate();
					}
					hssfRow.createCell(4).setCellValue(projectBeginDate);
					// 项目结束时间
					String projectEndDate = "";
					if (report.getProjectEndDate() != null) {
						//projectEndDate = sdf.format(report.getProjectEndDate());
						projectEndDate = report.getProjectEndDate();
					}
					hssfRow.createCell(5).setCellValue(projectEndDate);
					// 项目经理
					String projectOwnerName = "";
					if (report.getProjectOwnerName() != null) {
						projectOwnerName = report.getProjectOwnerName();
					}
					hssfRow.createCell(6).setCellValue(projectOwnerName);
					//研发经理
					String projectManagerName = "";
					if (report.getProjectManagerName() != null) {
						projectManagerName = report.getProjectManagerName();
					}
					hssfRow.createCell(7).setCellValue(projectManagerName);
					// 研发开始时间
					String projectDevelopmentBeginDate = "";
					if (report.getProjectDevelopmentBeginDate() != null) {
						//projectDevelopmentBeginDate = sdf.format(report.getProjectDevelopmentBeginDate());
						projectDevelopmentBeginDate = report.getProjectDevelopmentBeginDate();
					}
					hssfRow.createCell(8).setCellValue(projectDevelopmentBeginDate);
					// 研发结束时间
					String projectDevelopmentEndDate = "";
					if (report.getProjectDevelopmentEndDate() != null) {
						//projectDevelopmentEndDate = sdf.format(report.getProjectDevelopmentEndDate());
						projectDevelopmentEndDate = report.getProjectDevelopmentEndDate();
					}
					hssfRow.createCell(9).setCellValue(projectDevelopmentEndDate);
					// 项目类型
					String projectGategory = "";
					if (report.getProjectGategory() != null) {
						projectGategory = report.getProjectGategory();
					}
					hssfRow.createCell(10).setCellValue(projectGategory);
					// 项目阶段
					String projectPhase = "";
					if (report.getProjectPhase() != null) {
						projectPhase = report.getProjectPhase();
					}
					hssfRow.createCell(11).setCellValue(projectPhase);
					// 项目描述
					String projectDescription = "";
					if (report.getProjectDescription() != null) {
						projectDescription = report.getProjectDescription();
					}
					hssfRow.createCell(12).setCellValue(projectDescription);
					// 旧项目编号
					String projectOldId = "";
					if (report.getProjectOldId() != null) {
						projectOldId = report.getProjectOldId();
					}
					hssfRow.createCell(13).setCellValue(projectOldId);
					// 备注
					String projectRemarks = "";
					if (report.getProjectRemarks() != null) {
						projectRemarks = report.getProjectRemarks();
					}
					hssfRow.createCell(14).setCellValue(projectRemarks);
					// 操作人
					String projectOperatorName = "";
					if (report.getProjectOperatorName() != null) {
						projectOperatorName = report.getProjectOperatorName();
					}
					hssfRow.createCell(15).setCellValue(projectOperatorName);
					// 操作时间
					String projectOperateTime = "";
					if (report.getProjectOperateTime() != null) {
						projectOperateTime = sdf.format(report.getProjectOperateTime());
					}
					hssfRow.createCell(16).setCellValue(projectOperateTime);
					// 创建人
					String projectCreatorName = "";
					if (report.getProjectCreatorName() != null) {
						projectCreatorName= report.getProjectCreatorName();
					}
					hssfRow.createCell(17).setCellValue(projectCreatorName);
					// 创建时间
					String projectCreateTime = "";
					if (report.getProjectCreateTime() != null) {
						projectCreateTime = sdf.format(report.getProjectCreateTime());
					}
					hssfRow.createCell(18).setCellValue(projectCreateTime);					
					// 流程种类
					String fllowType = "";
					if (report.getFllowType() != null) {
						fllowType = report.getFllowType();
					}
					hssfRow.createCell(19).setCellValue(fllowType);
					// 是否分包
					String ifOutSource = "";
					if (report.getIfOutSource() != null) {
						ifOutSource = report.getIfOutSource();
					}
					hssfRow.createCell(20).setCellValue(ifOutSource);
					// 是否签订合同
					String deliveryStatus = "";
					if (report.getDeliveryStatus() != null) {
						deliveryStatus = report.getDeliveryStatus();
					}
					hssfRow.createCell(21).setCellValue(deliveryStatus);
					// 关联售前项目编号
					String projectRelatedId = "";
					if (report.getProjectRelatedId() != null) {
						projectRelatedId = report.getProjectRelatedId();
					}
					hssfRow.createCell(22).setCellValue(projectRelatedId);
					// 部门编号
					String deptId = "";
					if (report.getDeptId() != null) {
						deptId = report.getDeptId();
					}
					hssfRow.createCell(23).setCellValue(deptId);
					// 项目集合编号
					String collectionId = "";
					if (report.getCollectionId() != null) {
						collectionId = report.getCollectionId();
					}
					hssfRow.createCell(23).setCellValue(collectionId);
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
	public List<ProjectDO> getQuery(Map<String, Object> params) {
		List<ProjectDO> returnData=projectDao.list(params);
		return returnData;
		
		/*for (int i = 0; i < returnDate.size(); i++) {
		}*/
	}
}
