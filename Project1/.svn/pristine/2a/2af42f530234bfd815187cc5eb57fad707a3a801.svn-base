package com.bootdo.common.task;

import org.activiti.engine.TaskService;
import org.apache.shiro.SecurityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.domain.Response;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.SessionService;

@Component
public class ActivitiJob implements Job{
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
    SessionService sessionService;
	
	@Autowired
	TaskService taskService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	
    	for (UserDO userDO : sessionService.listOnlineUser()) {
    		String userName=userDO.getUsername();
    		long travelTaskCount = taskService.createTaskQuery().processDefinitionKey("travel")
    				.taskAssignee(userName).count();
    		long payoutTaskCount = taskService.createTaskQuery().processDefinitionKey("payout")
    				.taskAssignee(userName).count();
    		long expensesTravelTaskCount = taskService.createTaskQuery().processDefinitionKey("expensesTravel")
    				.taskAssignee(userName).count();
    		long expensesNormalTaskCount = taskService.createTaskQuery().processDefinitionKey("expensesNormal")
    				.taskAssignee(userName).count();
    		long contractTaskCount = taskService.createTaskQuery().processDefinitionKey("contract")
    				.taskAssignee(userName).count();
    		long additionalRecordsTaskCount = taskService.createTaskQuery().processDefinitionKey("additionalRecords")
    				.taskAssignee(userName).count();
    		long purchaseTaskCount = taskService.createTaskQuery().processDefinitionKey("purchase")
    				.taskAssignee(userName).count();
    		long approvalAssignmentTaskCount = taskService.createTaskQuery().processDefinitionKey("approvalAssignment")
    				.taskAssignee(userName).count();
    		long timeSheetTaskCount = taskService.createTaskQuery().processDefinitionKey("timeSheet")
    				.taskAssignee(userName).count();
    		long budgetTaskCount = taskService.createTaskQuery().processDefinitionKey("budget")
    				.taskAssignee(userName).count();
    		
    		String msg="";
    		if(travelTaskCount>0){
    			msg=msg+"待审批出差:"+travelTaskCount+"项;<br>";
    		}
    		if(payoutTaskCount>0){
    			msg=msg+"待审批请款:"+payoutTaskCount+"项;<br>";
    		}
    		if(expensesTravelTaskCount>0){
    			msg=msg+"待审批差旅报销:"+expensesTravelTaskCount+"项;<br>";
    		}
    		if(expensesNormalTaskCount>0){
    			msg=msg+"待审批普通报销:"+expensesNormalTaskCount+"项;<br>";
    		}
    		if(contractTaskCount>0){
    			msg=msg+"待审批合同:"+contractTaskCount+"项;<br>";
    		}
    		if(additionalRecordsTaskCount>0){
    			msg=msg+"待审批增补合同:"+additionalRecordsTaskCount+"项;<br>";
    		}
    		if(purchaseTaskCount>0){
    			msg=msg+"待审批采购:"+purchaseTaskCount+"项;<br>";
    		}
    		if(approvalAssignmentTaskCount>0){
    			msg=msg+" 待审批任务:"+approvalAssignmentTaskCount+"项;<br>";
    		}
    		if(timeSheetTaskCount>0){
    			msg=msg+" 待审批工时:"+timeSheetTaskCount+"项;<br>";
    		}
    		if(budgetTaskCount>0){
    			msg=msg+" 待审批预算:"+budgetTaskCount+"项;<br>";
    		}
    		
    		if(!msg.equals("")){
    			msg="待办流程提醒:<br>"+msg;
    			template.convertAndSendToUser(userDO.toString(), "/queue/notifications", msg );
 	            //System.out.println(userDO.toString());
    		}
    	      
            
        }
    }

}