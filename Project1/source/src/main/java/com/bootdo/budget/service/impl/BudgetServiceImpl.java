package com.bootdo.budget.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.bootdo.budget.dao.BudgetDao;
import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.service.BudgetService;
import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProjectDO;




@Service
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	private BudgetDao budgetDao;
	
	@Override
	public BudgetDO get(String budgetId){
		return budgetDao.get(budgetId);
	}
	
	@Override
	public List<BudgetDO> list(Map<String, Object> map){
		return budgetDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return budgetDao.count(map);
	}
	
	@Override
	public int save(BudgetDO budget){
		//return budgetDao.save(budget);
		int ret=budgetDao.save(budget);
		String budgetId=budget.getBudgetId();
		return Integer.parseInt(budgetId);
	}
	
	@Override
	public int update(BudgetDO budget){
		return budgetDao.update(budget);
	}
	
	@Override
	public int remove(String budgetId){
		return budgetDao.remove(budgetId);
	}
	
	@Override
	public int batchRemove(String[] budgetIds){
		return budgetDao.batchRemove(budgetIds);
	}
	
	@Override
	public List<DictDO> listDic() {
		return budgetDao.listDic();
	}
	
	@Override
	public ProjectDO getProjectId(String projectId) {
		return budgetDao.getProjectId(projectId);
	}	
	/**
	 * 导出Excel写入文件方法
	 */
	public void export(String[] titles, ServletOutputStream out, List<BudgetDO> list) {
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
					BudgetDO report = list.get(i);
					/** 第六步，创建单元格，并设置值 **/
					// 序号
					//hssfRow.createCell(0).setCellValue(i + 1);
					// 项目预算编号
					String budgetId = "";
					if (report.getBudgetId() != null) {
						budgetId = report.getBudgetId();
					}
					hssfRow.createCell(0).setCellValue(budgetId);
					// 企业客户编号
					String customerId = "";
					if (report.getCustomerId() != null) {
						customerId = report.getCustomerId();
					}
					hssfRow.createCell(1).setCellValue(customerId);
					// 业务编号
					String businessId = "";
					if (report.getBusinessId() != null) {
						businessId = report.getBusinessId();
					}
					hssfRow.createCell(2).setCellValue(businessId);
					// 项目编号
					String projectId = "";
					if (report.getProjectId() != null) {
						projectId = report.getProjectId();
					}
					hssfRow.createCell(3).setCellValue(projectId);
					// 项目计划利润率
					String budgetProfitRate = "";
					if (report.getBudgetProfitRate() != null) {
						BigDecimal budgetProfitRate1 = report.getBudgetProfitRate();
						budgetProfitRate = String.valueOf(budgetProfitRate1);
					}
					hssfRow.createCell(4).setCellValue(budgetProfitRate);
					// 应收账款总额
					String budgetAccountReceivable = "";
					if (report.getBudgetAccountReceivable() != null) {
						BigDecimal budgetAccountReceivable1 = report.getBudgetAccountReceivable();
						budgetAccountReceivable = String.valueOf(budgetAccountReceivable1);
					}
					hssfRow.createCell(5).setCellValue(budgetAccountReceivable);
					// 计划成本总额
					String budgetTotalCost = "";
					if (report.getBudgetTotalCost() != null) {
						BigDecimal budgetTotalCost1 = report.getBudgetTotalCost();
						budgetTotalCost = String.valueOf(budgetTotalCost1);
					}
					hssfRow.createCell(6).setCellValue(budgetTotalCost);
					// 计划是否合规
					String budgetConformance = "";
					if (report.getBudgetConformance() != null) {
						budgetConformance = report.getBudgetConformance();
					}
					hssfRow.createCell(7).setCellValue(budgetConformance);
					// 服务收入
					String budgetServiceRevenue = "";
					if (report.getBudgetServiceRevenue() != null) {
						BigDecimal budgetServiceRevenue1 = report.getBudgetServiceRevenue();
						budgetServiceRevenue = String.valueOf(budgetServiceRevenue1);
					}
					hssfRow.createCell(8).setCellValue(budgetServiceRevenue);
					// 税金
					String budgetTax = "";
					if (report.getBudgetTax() != null) {
						BigDecimal budgetTax1 = report.getBudgetTax();
						budgetTax = String.valueOf(budgetTax1);
					}
					hssfRow.createCell(9).setCellValue(budgetTax);
					// 服务净收入
					String budgetServiceRevenueNet = "";
					if (report.getBudgetServiceRevenueNet() != null) {
						BigDecimal budgetServiceRevenueNet1 = report.getBudgetServiceRevenueNet();
						budgetServiceRevenueNet = String.valueOf(budgetServiceRevenueNet1);
					}
					hssfRow.createCell(10).setCellValue(budgetServiceRevenueNet);
					// 采购成本
					String budgetPurchaseCost = "";
					if (report.getBudgetPurchaseCost() != null) {
						BigDecimal budgetPurchaseCost1 = report.getBudgetPurchaseCost();
						budgetPurchaseCost = String.valueOf(budgetPurchaseCost1);
					}
					hssfRow.createCell(11).setCellValue(budgetPurchaseCost);
					// 人工成本
					String budgetLaborCost = "";
					if (report.getBudgetLaborCost() != null) {
						BigDecimal budgetLaborCost1 = report.getBudgetLaborCost();
						budgetLaborCost = String.valueOf(budgetLaborCost1);
					}
					hssfRow.createCell(12).setCellValue(budgetLaborCost);
					// 差旅成本
					String budgetTravelCost = "";
					if (report.getBudgetTravelCost() != null) {
						BigDecimal budgetTravelCost1 = report.getBudgetTravelCost();
						budgetTravelCost = String.valueOf(budgetTravelCost1);
					}
					hssfRow.createCell(13).setCellValue(budgetTravelCost);
					// 费用和支持（含税）
					String budgetCost = "";
					if (report.getBudgetCost() != null) {
						BigDecimal budgetCost1 = report.getBudgetCost();
						budgetCost = String.valueOf(budgetCost1);
					}
					hssfRow.createCell(14).setCellValue(budgetCost);
					// 利润
					String budgetProfit = "";
					if (report.getBudgetProfit() != null) {
						BigDecimal budgetProfit1 = report.getBudgetProfit();
						budgetProfit = String.valueOf(budgetProfit1);
					}
					hssfRow.createCell(15).setCellValue(budgetProfit);
					// 操作人
					String budgetOperatorName = "";
					if (report.getBudgetOperatorName() != null) {
						budgetOperatorName = report.getBudgetOperatorName();
					}
					hssfRow.createCell(16).setCellValue(budgetOperatorName);
					// 操作时间
					String budgetOperateTime = "";
					if (report.getBudgetOperateTime() != null) {
						budgetOperateTime = sdf.format(report.getBudgetOperateTime());
					}
					hssfRow.createCell(17).setCellValue(budgetOperateTime);
					// 负责中心
					String responsibleCenter = "";
					if (report.getResponsibleCenter() != null) {
						responsibleCenter = report.getResponsibleCenter();
					}
					hssfRow.createCell(18).setCellValue(responsibleCenter);
					// 部门名称
					String deptName = "";
					if (report.getDeptName() != null) {
						deptName = report.getDeptName();
					}
					hssfRow.createCell(19).setCellValue(deptName);
					// 项目主管
					String projectSupervisorName = "";
					if (report.getProjectSupervisorName() != null) {
						projectSupervisorName = report.getProjectSupervisorName();
					}
					hssfRow.createCell(20).setCellValue(projectSupervisorName);
					// 项目类别
					String projectGategory = "";
					if (report.getProjectGategory() != null) {
						projectGategory = report.getProjectGategory();
					}
					hssfRow.createCell(21).setCellValue(projectGategory);
					// 项目名称
					String projectName = "";
					if (report.getProjectName() != null) {
						projectName = report.getProjectName();
					}
					hssfRow.createCell(22).setCellValue(projectName);
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
	public List<BudgetDO> getQuery(Map<String, Object> params) {
		List<BudgetDO> returnData=budgetDao.list(params);
		return returnData;
		
		/*for (int i = 0; i < returnDate.size(); i++) {
		}*/
	}
}
