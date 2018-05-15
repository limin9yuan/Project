package com.bootdo.activiti.vo;

import java.util.Date;

import org.activiti.engine.task.Task;

/**

 */
public class TaskVO  {

    public TaskVO(Task task){
    	if(task!=null){
	        this.setId(task.getId());
	        this.setKey(task.getTaskDefinitionKey());
	        this.setName(task.getName());
	        this.setDescription(task.getDescription());
	        this.setAssignee(task.getAssignee());
	        this.setFormKey(task.getFormKey());
	        this.setProcessId(task.getProcessInstanceId());
	        this.setProcessDefinitionId(task.getProcessDefinitionId());
	        this.setExecutionId(task.getExecutionId());
    	}

    }
    private  String id;
    private String name;
    private String key;
    private String description;
    private  String formKey;
    private  String assignee;
    private String processId;
    private String processDefinitionId;
    private String executionId;
    
    private String processName; 	// 流程名称
    private String title; 		// 任务标题
    private String startUserName; // 发起人
    private Date startDate; // 发起日期
    private Date endDate; // 结束日期
    private String processStatus; //流程状态
    private  String assigneeName; //当前处理人姓名
    
    
    public String getAssigneeName() {
        return assigneeName;
    }
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }
    
    public String getProcessStatus() {
        return processStatus;
    }
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public String getStartUserName() {
        return startUserName;
    }
    public void setStartUserName(String startUserName) {
        this.startUserName = startUserName;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }
}
