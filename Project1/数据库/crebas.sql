/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/11/2 18:33:06                           */
/*==============================================================*/


drop table if exists Accounts_Payable;

drop table if exists Accounts_Receivable;

drop table if exists Approval_Assignment;

drop table if exists Approval_Expenses_Normal;

drop table if exists Approval_Expenses__Travel;

drop table if exists Approval_Payout;

drop table if exists Approval_Purchase;

drop table if exists Approval_Travel;

drop table if exists Budget_Expenses;

drop table if exists Budget_Labor;

drop table if exists Budget_Purchase;

drop table if exists Contract;

drop table if exists Contract_Additional_Records;

drop table if exists Contract_Delivery;

drop table if exists Delivery_Plan;

drop table if exists Function_Module;

drop table if exists Inner_Org_Dept;

drop table if exists Inner_Org_Employee;

drop table if exists Inner_Org_Job;

drop table if exists Inner_User;

drop table if exists Invoice;

drop table if exists Payment_Paid;

drop table if exists Payment_Received;

drop table if exists Project_Budget;

drop table if exists Purchase;

drop table if exists Sales_Bug_Category;

drop table if exists Sales_Business;

drop table if exists Sales_Company_Customer;

drop table if exists Sales_Competitor;

drop table if exists Sales_Customer_Child_Company;

drop table if exists Sales_Customer_Contact;

drop table if exists Sales_Customer_Dept;

drop table if exists Sales_Customer__Job;

drop table if exists Sales_Module_Category;

drop table if exists Sales_Online_FeedBack;

drop table if exists Sales_Product_Category;

drop table if exists Sales_Project;

drop table if exists Sales_Project_Consultation;

drop table if exists Sales_Record;

drop table if exists Sales_Record_Complaint;

drop table if exists Sales_Record_Service;

drop table if exists Sales_Requirement_Category;

drop table if exists TimeSheet;

drop table if exists Travel_Schedule;

drop table if exists User_Role;
drop table if exists Inner_Org_Dept;

/*==============================================================*/
/* Table: Accounts_Payable                                      */
/*==============================================================*/
create table Accounts_Payable
(
   Payable_ID           varchar(50) not null,
   Contract_ID          varchar(50),
   Payable_Date         datetime not null,
   Payable_Price        numeric(16,2) not null,
   Payable_Remarks      varchar(500),
   Payable_Operator     varchar(6) not null,
   Payable_Operate_Time timestamp not null,
   primary key (Payable_ID)
);

alter table Accounts_Payable comment '����ƻ���';

alter table Accounts_Payable modify column Payable_ID varchar(50) comment '����ƻ�ID';

alter table Accounts_Payable modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Accounts_Payable modify column Payable_Date datetime comment '����ƻ�ʱ��';

alter table Accounts_Payable modify column Payable_Price numeric(16,2) comment '����ƻ����';

alter table Accounts_Payable modify column Payable_Remarks varchar(500) comment '��ע';

alter table Accounts_Payable modify column Payable_Operator varchar(6) comment '������';

alter table Accounts_Payable modify column Payable_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Accounts_Receivable                                   */
/*==============================================================*/
create table Accounts_Receivable
(
   Receivable_ID        varchar(50) not null,
   Contract_ID          varchar(50),
   Receivable_Date      datetime not null,
   Receivable_Price     numeric(16,2) not null,
   Receivable_Owner     varchar(6) not null,
   Receivable_Remarks   varchar(500),
   Receivable_Operator  varchar(6) not null,
   Receivable_Operate_Time timestamp not null,
   primary key (Receivable_ID)
);

alter table Accounts_Receivable comment '�տ�ƻ���';

alter table Accounts_Receivable modify column Receivable_ID varchar(50) comment '�տ�ƻ�ID';

alter table Accounts_Receivable modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Accounts_Receivable modify column Receivable_Date datetime comment '�տ�ƻ�ʱ��';

alter table Accounts_Receivable modify column Receivable_Price numeric(16,2) comment '�տ�ƻ����';

alter table Accounts_Receivable modify column Receivable_Owner varchar(6) comment '�տ����';

alter table Accounts_Receivable modify column Receivable_Remarks varchar(500) comment '��ע';

alter table Accounts_Receivable modify column Receivable_Operator varchar(6) comment '������';

alter table Accounts_Receivable modify column Receivable_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Assignment                                   */
/*==============================================================*/
create table Approval_Assignment
(
   Assignment_ID        varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Project_ID           varchar(50),
   Assignment_Project_Cagegory varchar(20),
   Assignment_PM        varchar(6),
   Assignment_Task_Name varchar(50) not null,
   Assignment_Begin_Time datetime not null,
   Assignment_End_Time  datetime not null,
   Assignment_Dept      varchar(20) not null,
   Assignment_Principal varchar(6) not null,
   Assignment_Recipient_Dept varchar(20) not null,
   Assignment_Recipient varchar(6) not null,
   Assignment_Task_Description varchar(1000) not null,
   Assignment_Task_Performance varchar(200) not null,
   Assignment_Task_Finish_Time datetime not null,
   Assignment_Remarks   varchar(200),
   Assignment_Approval_Status int(1) not null,
   Assignment_Operator  varchar(6) not null,
   Assignment_Operate_Time timestamp not null,
   primary key (Assignment_ID)
);

alter table Approval_Assignment comment '����ί�б�';

alter table Approval_Assignment modify column Assignment_ID varchar(50) comment '����ί�б��';

alter table Approval_Assignment modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Approval_Assignment modify column Business_ID varchar(50) comment 'ҵ����';

alter table Approval_Assignment modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Assignment modify column Assignment_Project_Cagegory varchar(20) comment '��Ŀ���';

alter table Approval_Assignment modify column Assignment_PM varchar(6) comment '��Ŀ����';

alter table Approval_Assignment modify column Assignment_Task_Name varchar(50) comment '��������';

alter table Approval_Assignment modify column Assignment_Begin_Time datetime comment '����ί��ʱ��';

alter table Approval_Assignment modify column Assignment_End_Time datetime comment 'Ҫ�����ʱ��';

alter table Approval_Assignment modify column Assignment_Dept varchar(20) comment 'ί�в���';

alter table Approval_Assignment modify column Assignment_Principal varchar(6) comment 'ί����';

alter table Approval_Assignment modify column Assignment_Recipient_Dept varchar(20) comment '�нӲ���';

alter table Approval_Assignment modify column Assignment_Recipient varchar(6) comment '�н���';

alter table Approval_Assignment modify column Assignment_Task_Description varchar(1000) comment 'ί����������';

alter table Approval_Assignment modify column Assignment_Task_Performance varchar(200) comment '����������';

alter table Approval_Assignment modify column Assignment_Task_Finish_Time datetime comment '�������ʱ��';

alter table Approval_Assignment modify column Assignment_Remarks varchar(200) comment '��ע';

alter table Approval_Assignment modify column Assignment_Approval_Status int(1) comment '����״̬';

alter table Approval_Assignment modify column Assignment_Operator varchar(6) comment '������';

alter table Approval_Assignment modify column Assignment_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Expenses_Normal                              */
/*==============================================================*/
create table Approval_Expenses_Normal
(
   Expenses_Normal      varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Project_ID           varchar(50),
   Expenses_Normal_Name varchar(6) not null,
   Expenses_Normal_Reason varchar(500) not null,
   Expenses_Normal_Price numeric(9,1) not null,
   Expenses_Normal_Result varchar(10) not null,
   Expenses_Normal_RelatedID varchar(50),
   Expenses_Normal_Rmarks varchar(200),
   Expenses_Normal_Status int(1) not null,
   Expenses_Normal_Operator varchar(6) not null,
   Expenses_Normal_Opt_Time timestamp not null,
   primary key (Expenses_Normal)
);

alter table Approval_Expenses_Normal comment '��ͨ����������Ϣ��';

alter table Approval_Expenses_Normal modify column Expenses_Normal varchar(50) comment '����������';

alter table Approval_Expenses_Normal modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Approval_Expenses_Normal modify column Business_ID varchar(50) comment 'ҵ����';

alter table Approval_Expenses_Normal modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Name varchar(6) comment '����������';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Reason varchar(500) comment '֧��ԭ��';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Price numeric(9,1) comment '�������';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Result varchar(10) comment '��Ʊ���';

alter table Approval_Expenses_Normal modify column Expenses_Normal_RelatedID varchar(50) comment '�������������';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Rmarks varchar(200) comment '��ע';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Status int(1) comment '����״̬';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Operator varchar(6) comment '������';

alter table Approval_Expenses_Normal modify column Expenses_Normal_Opt_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Expenses__Travel                             */
/*==============================================================*/
create table Approval_Expenses__Travel
(
   Expenses_Travel_ID   varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Project_ID           varchar(50),
   Expenses_Travel_Name varchar(6) not null,
   Expenses_Travel_Task varchar(1000) not null,
   Expenses_Travel_Allowance numeric(10,2) not null,
   Expenses_Travel_Type varchar(10),
   Expenses_Travel_Price numeric(10,2),
   Expenses_Travel_TID  varchar(50),
   Expenses_Travel_PayID varchar(50),
   Expenses_Travel_Pay_Price numeric(10,2),
   Expenses_Travel_Pay_Mode varchar(10) not null,
   Expenses_Travel_Bank varchar(50),
   Expenses_Travel_Card_Num varchar(25),
   Expenses_Travel_Remarks varchar(200),
   Expenses_Travel_Status int(1) not null,
   Expenses_Travel_Operator varchar(6) not null,
   Expenses_Travel_Opt_Time timestamp not null,
   primary key (Expenses_Travel_ID)
);

alter table Approval_Expenses__Travel comment '���ñ��������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_ID varchar(50) comment '����������';

alter table Approval_Expenses__Travel modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Approval_Expenses__Travel modify column Business_ID varchar(50) comment 'ҵ����';

alter table Approval_Expenses__Travel modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Name varchar(6) comment '����������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Task varchar(1000) comment '��������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Allowance numeric(10,2) comment '�����';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Type varchar(10) comment '������������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Price numeric(10,2) comment '�������ý��';

alter table Approval_Expenses__Travel modify column Expenses_Travel_TID varchar(50) comment '��������������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_PayID varchar(50) comment '�������������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Pay_Price numeric(10,2) comment 'Ԥ����';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Pay_Mode varchar(10) comment '���㷽ʽ';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Bank varchar(50) comment '�տ�������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Card_Num varchar(25) comment '����';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Remarks varchar(200) comment '��ע';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Status int(1) comment '����״̬';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Operator varchar(6) comment '������';

alter table Approval_Expenses__Travel modify column Expenses_Travel_Opt_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Payout                                       */
/*==============================================================*/
create table Approval_Payout
(
   Payout_ID            varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Project_ID           varchar(50),
   Payout_Person        varchar(6) not null,
   Payout_Useage        varchar(500) not null,
   Payout_Price         numeric(9,1) not null,
   Payout_Means         varchar(10) not null,
   Payout_Beneficiary_Bank varchar(50),
   Payout_Card_Number   varchar(25),
   Payout_Related_Contract_ID varchar(50),
   Payout_Result        varchar(6),
   Payout_Remarks       varchar(200),
   Payout_Approval_Status int(1) not null,
   Payout_Operator      varchar(6) not null,
   Payout_Operate_Time  timestamp not null,
   primary key (Payout_ID)
);

alter table Approval_Payout comment '��������';

alter table Approval_Payout modify column Payout_ID varchar(50) comment '���������';

alter table Approval_Payout modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Approval_Payout modify column Business_ID varchar(50) comment 'ҵ����';

alter table Approval_Payout modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Payout modify column Payout_Person varchar(6) comment '����������';

alter table Approval_Payout modify column Payout_Useage varchar(500) comment '�����;';

alter table Approval_Payout modify column Payout_Price numeric(9,1) comment '�����';

alter table Approval_Payout modify column Payout_Means varchar(10) comment '���㷽ʽ';

alter table Approval_Payout modify column Payout_Beneficiary_Bank varchar(50) comment '�տ�������';

alter table Approval_Payout modify column Payout_Card_Number varchar(25) comment '���п���';

alter table Approval_Payout modify column Payout_Related_Contract_ID varchar(50) comment '��������������';

alter table Approval_Payout modify column Payout_Result varchar(6) comment 'ʵ�������';

alter table Approval_Payout modify column Payout_Remarks varchar(200) comment '��ע';

alter table Approval_Payout modify column Payout_Approval_Status int(1) comment '����״̬';

alter table Approval_Payout modify column Payout_Operator varchar(6) comment '������';

alter table Approval_Payout modify column Payout_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Purchase                                     */
/*==============================================================*/
create table Approval_Purchase
(
   Purchase_ID          varchar(50) not null,
   Project_ID           varchar(50) not null,
   Purchase_Dept        varchar(20) not null,
   Purchase_Person      varchar(6) not null,
   Purchase_Date        datetime not null,
   Purchase_Name        varchar(50) not null,
   Purchase_Brand       varchar(50) not null,
   Purchase_Mode        varchar(50),
   Purchase_Config      varchar(200),
   Purchase_Unit        varchar(10) not null,
   Purchase_Number      int(10) not null,
   Purchase_Unit_Price  numeric(10,2) not null,
   Purchase_Total_Price numeric(10,2) not null,
   Purchase_Delivery_Time datetime not null,
   Purchase_Delivery_Place varchar(50) not null,
   Purchase_Consignee   varchar(6) not null,
   Purchase_Phone_Number varchar(20) not null,
   Purchase_Remarks     varchar(200),
   Purchase_Approval_Status int(1) not null,
   Purchase_Operator    varchar(6) not null,
   Purchase_Operate_Time timestamp not null,
   primary key (Purchase_ID)
);

alter table Approval_Purchase comment '�ɹ������';

alter table Approval_Purchase modify column Purchase_ID varchar(50) comment '�ɹ�������';

alter table Approval_Purchase modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Purchase modify column Purchase_Dept varchar(20) comment '�깺����';

alter table Approval_Purchase modify column Purchase_Person varchar(6) comment '�깺��';

alter table Approval_Purchase modify column Purchase_Date datetime comment '�깺ʱ��';

alter table Approval_Purchase modify column Purchase_Name varchar(50) comment '��Ʒ����';

alter table Approval_Purchase modify column Purchase_Brand varchar(50) comment 'Ʒ��';

alter table Approval_Purchase modify column Purchase_Mode varchar(50) comment '�ͺ�';

alter table Approval_Purchase modify column Purchase_Config varchar(200) comment '���/����';

alter table Approval_Purchase modify column Purchase_Unit varchar(10) comment '��λ';

alter table Approval_Purchase modify column Purchase_Number int(10) comment '����';

alter table Approval_Purchase modify column Purchase_Unit_Price numeric(10,2) comment '����';

alter table Approval_Purchase modify column Purchase_Total_Price numeric(10,2) comment '�ܼ�';

alter table Approval_Purchase modify column Purchase_Delivery_Time datetime comment 'Ҫ�󽻻�ʱ��';

alter table Approval_Purchase modify column Purchase_Delivery_Place varchar(50) comment '�����ص�';

alter table Approval_Purchase modify column Purchase_Consignee varchar(6) comment '�ջ���';

alter table Approval_Purchase modify column Purchase_Phone_Number varchar(20) comment '��ϵ�绰';

alter table Approval_Purchase modify column Purchase_Remarks varchar(200) comment '��ע';

alter table Approval_Purchase modify column Purchase_Approval_Status int(1) comment '����״̬';

alter table Approval_Purchase modify column Purchase_Operator varchar(6) comment '������';

alter table Approval_Purchase modify column Purchase_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Approval_Travel                                       */
/*==============================================================*/
create table Approval_Travel
(
   Travel_ID            varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Project_ID           varchar(50),
   Business_ID          varchar(50) not null,
   Travel_Name          varchar(6) not null,
   Travel_Place_Issue   varchar(20) not null,
   Travel_Place_Ended   varchar(20) not null,
   Travel_Partner       varchar(6),
   Travel_Departure_Date datetime not null,
   Travel_Return_Date   datetime not null,
   Travel_Plan_Cost_Type varchar(20) not null,
   Travel_Visit_Mode    varchar(20) not null,
   Travel_Plan_Task     varchar(1000) not null,
   Travel_Actual_Performance varchar(10),
   Travel_Uncompleted_Cause varchar(1000),
   Travel_Task_Confirm  varchar(10),
   Travel_Approval_Status int(1) not null,
   Travel_Operator      varchar(6) not null,
   Travel_Operate_Time  timestamp not null,
   primary key (Travel_ID)
);

alter table Approval_Travel comment '���������';

alter table Approval_Travel modify column Travel_ID varchar(50) comment '����������';

alter table Approval_Travel modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Approval_Travel modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Approval_Travel modify column Business_ID varchar(50) comment 'ҵ����';

alter table Approval_Travel modify column Travel_Name varchar(6) comment '����������';

alter table Approval_Travel modify column Travel_Place_Issue varchar(20) comment '������';

alter table Approval_Travel modify column Travel_Place_Ended varchar(20) comment 'Ŀ�ĵ�';

alter table Approval_Travel modify column Travel_Partner varchar(6) comment 'ͬ����';

alter table Approval_Travel modify column Travel_Departure_Date datetime comment '�����ʱ��';

alter table Approval_Travel modify column Travel_Return_Date datetime comment '�ⷵ��ʱ��';

alter table Approval_Travel modify column Travel_Plan_Cost_Type varchar(20) comment 'Ԥ�Ʒ������';

alter table Approval_Travel modify column Travel_Visit_Mode varchar(20) comment 'Ԥ�ƽ�ͨ��ʽ';

alter table Approval_Travel modify column Travel_Plan_Task varchar(1000) comment '�ƻ�������Ϣ';

alter table Approval_Travel modify column Travel_Actual_Performance varchar(10) comment 'ʵ����ɽ��';

alter table Approval_Travel modify column Travel_Uncompleted_Cause varchar(1000) comment 'δ���ԭ��';

alter table Approval_Travel modify column Travel_Task_Confirm varchar(10) comment '��������ȷ��';

alter table Approval_Travel modify column Travel_Approval_Status int(1) comment '����״̬';

alter table Approval_Travel modify column Travel_Operator varchar(6) comment '������';

alter table Approval_Travel modify column Travel_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Budget_Expenses                                       */
/*==============================================================*/
create table Budget_Expenses
(
   Expenses_ID          varchar(50) not null,
   Budget_ID            varchar(50),
   Expenses_Type        varchar(20) not null,
   Expenses_Plan_Price  numeric(10,2) not null,
   Expenses_Plan_Description varchar(200),
   Expenses_Customer_Rate numeric(5,2) not null,
   Expenses_Project_Rate numeric(5,2) not null,
   Expenses_Remarks     varchar(200),
   Expenses_Operator    varchar(6) not null,
   Expenses_Operate_TIme timestamp not null,
   primary key (Expenses_ID)
);

alter table Budget_Expenses comment '��Ŀ����Ԥ���';

alter table Budget_Expenses modify column Expenses_ID varchar(50) comment '��Ŀ����Ԥ����';

alter table Budget_Expenses modify column Budget_ID varchar(50) comment '��ĿԤ����';

alter table Budget_Expenses modify column Expenses_Type varchar(20) comment '��������';

alter table Budget_Expenses modify column Expenses_Plan_Price numeric(10,2) comment '�������Ԥ��';

alter table Budget_Expenses modify column Expenses_Plan_Description varchar(200) comment 'Ԥ��˵��';

alter table Budget_Expenses modify column Expenses_Customer_Rate numeric(5,2) comment '�ͻ��е�';

alter table Budget_Expenses modify column Expenses_Project_Rate numeric(5,2) comment '��Ŀ��е�';

alter table Budget_Expenses modify column Expenses_Remarks varchar(200) comment '��ע';

alter table Budget_Expenses modify column Expenses_Operator varchar(6) comment '������';

alter table Budget_Expenses modify column Expenses_Operate_TIme timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Budget_Labor                                          */
/*==============================================================*/
create table Budget_Labor
(
   Labor_ID             varchar(50) not null,
   Budget_ID            varchar(50),
   Employee_ID          varchar(50) not null,
   Labor_Begin_Time     datetime not null,
   Labor_End_Time       datetime not null,
   Labor_Total_Day_Num  int(4) not null,
   Labor_Rate           numeric(5,2) not null,
   Labor_Total_Hour_Num int(10) not null,
   Labor_Total_Cost     numeric(12,2) not null,
   Labor_Remarks        varchar(200),
   Labor_Operator       varchar(6) not null,
   Labor_Operate_TIme   timestamp not null,
   primary key (Labor_ID)
);

alter table Budget_Labor comment '��Ŀ�������ű�';

alter table Budget_Labor modify column Labor_ID varchar(50) comment '��Ŀ�������ű��';

alter table Budget_Labor modify column Budget_ID varchar(50) comment '��ĿԤ����';

alter table Budget_Labor modify column Employee_ID varchar(50) comment 'Ա�����';

alter table Budget_Labor modify column Labor_Begin_Time datetime comment 'Ͷ�뿪ʼʱ��';

alter table Budget_Labor modify column Labor_End_Time datetime comment 'Ͷ�����ʱ��';

alter table Budget_Labor modify column Labor_Total_Day_Num int(4) comment '�ϼ�����';

alter table Budget_Labor modify column Labor_Rate numeric(5,2) comment 'Ͷ��ٷֱ�';

alter table Budget_Labor modify column Labor_Total_Hour_Num int(10) comment '�ϼƹ�ʱ��';

alter table Budget_Labor modify column Labor_Total_Cost numeric(12,2) comment '�˹��ɱ��ϼ�';

alter table Budget_Labor modify column Labor_Remarks varchar(200) comment '��ע';

alter table Budget_Labor modify column Labor_Operator varchar(6) comment '������';

alter table Budget_Labor modify column Labor_Operate_TIme timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Budget_Purchase                                       */
/*==============================================================*/
create table Budget_Purchase
(
   Budget_ID            varchar(50),
   Purchase_ID          varchar(50) not null,
   Purchase_Type        varchar(20) not null,
   Purchase_Price       numeric(10,2) not null,
   Purchase_Description varchar(200),
   Purchase_Customer_Rate numeric(5,2) not null,
   Purchase_Project_Rate numeric(5,2) not null,
   Purchase_Remarks     varchar(200),
   Purchase_Operator    varchar(6) not null,
   Purchase_OPerate_Time timestamp not null,
   primary key (Purchase_ID)
);

alter table Budget_Purchase comment '��Ŀ�ɹ�Ԥ���';

alter table Budget_Purchase modify column Budget_ID varchar(50) comment '��ĿԤ����';

alter table Budget_Purchase modify column Purchase_ID varchar(50) comment '��Ŀ�ɹ�Ԥ����';

alter table Budget_Purchase modify column Purchase_Type varchar(20) comment '�ɹ�����';

alter table Budget_Purchase modify column Purchase_Price numeric(10,2) comment '�ɹ����Ԥ��';

alter table Budget_Purchase modify column Purchase_Description varchar(200) comment 'Ԥ��˵��';

alter table Budget_Purchase modify column Purchase_Customer_Rate numeric(5,2) comment '�ͻ��е�';

alter table Budget_Purchase modify column Purchase_Project_Rate numeric(5,2) comment '��Ŀ��е�';

alter table Budget_Purchase modify column Purchase_Remarks varchar(200) comment '��ע';

alter table Budget_Purchase modify column Purchase_Operator varchar(6) comment '������';

alter table Budget_Purchase modify column Purchase_OPerate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Contract                                              */
/*==============================================================*/
create table Contract
(
   Contract_ID          varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Contract_Name        varchar(50) not null,
   Contract_Applicant_Name varchar(6) not null,
   Contract_Build_Company varchar(50) not null,
   Contract_Type        varchar(20) not null,
   Contract_Category    varchar(20) not null,
   Contract_Total_Price numeric(16,2) not null,
   Contract_Dept        varchar(20) not null,
   Contract_Applicant   varchar(6) not null,
   Contract_Draft_Person varchar(6) not null,
   Contract_Sales       varchar(6) not null,
   Contract_Commit_Time datetime not null,
   Contract_Related_ID  varchar(50),
   Contract_Invoice_Type varchar(20) not null,
   Contract_Invoice_Time datetime,
   Contract_Hardware_List longblob,
   Contract_Software_List longblob,
   Contract_Project_Management varchar(6) not null,
   Contract_Remarks     varchar(200),
   Contract_Attachment  longblob,
   Contract_Approval_Status int(1) not null,
   Contract_Operator    varchar(50) not null,
   Contract_Operate_Time timestamp not null,
   primary key (Contract_ID)
);

alter table Contract comment '��ͬ��Ϣ��';

alter table Contract modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Contract modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Contract modify column Business_ID varchar(50) comment 'ҵ����';

alter table Contract modify column Contract_Name varchar(50) comment '��ͬ����';

alter table Contract modify column Contract_Applicant_Name varchar(6) comment '����������';

alter table Contract modify column Contract_Build_Company varchar(50) comment '���赥λ';

alter table Contract modify column Contract_Type varchar(20) comment '��ͬ����';

alter table Contract modify column Contract_Category varchar(20) comment '��ͬ����';

alter table Contract modify column Contract_Total_Price numeric(16,2) comment '��ͬ�ܽ��';

alter table Contract modify column Contract_Dept varchar(20) comment '��ͬ������';

alter table Contract modify column Contract_Applicant varchar(6) comment 'ҵ������';

alter table Contract modify column Contract_Draft_Person varchar(6) comment '��ͬ�ⶨ��';

alter table Contract modify column Contract_Sales varchar(6) comment '���۸�����';

alter table Contract modify column Contract_Commit_Time datetime comment '�ύ����ʱ��';

alter table Contract modify column Contract_Related_ID varchar(50) comment '������ͬ���';

alter table Contract modify column Contract_Invoice_Type varchar(20) comment '��Ʊ����';

alter table Contract modify column Contract_Invoice_Time datetime comment 'Ԥ�ƿ��߷�Ʊʱ��';

alter table Contract modify column Contract_Hardware_List longblob comment 'Ӳ���豸��ϸ��';

alter table Contract modify column Contract_Software_List longblob comment '��������б�';

alter table Contract modify column Contract_Project_Management varchar(6) comment '��Ŀ����';

alter table Contract modify column Contract_Remarks varchar(200) comment '��ͬ��Ϣ��ע';

alter table Contract modify column Contract_Attachment longblob comment '����';

alter table Contract modify column Contract_Approval_Status int(1) comment '����״̬';

alter table Contract modify column Contract_Operator varchar(50) comment '������';

alter table Contract modify column Contract_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Contract_Additional_Records                           */
/*==============================================================*/
create table Contract_Additional_Records
(
   Record_ID            varchar(50) not null,
   Contract_ID          varchar(50),
   Record_Name          varchar(6) not null,
   Record_Bulid_Company varchar(50) not null,
   Record_Total_Price   numeric(16,2) not null,
   Record_Description   varchar(1000) not null,
   Record_Reason        varchar(1000) not null,
   Record_Sales         varchar(6) not null,
   Record_Attachment    longblob not null,
   Record_Related_Contract_ID varchar(50),
   Record_Hardware_Equipment_List longblob,
   Record_Software_Function_List longblob,
   Record_Remarks       varchar(200),
   Record_Approval_Status int(1) not null,
   Record_Operator      varchar(6) not null,
   Record_Commit_Time   timestamp not null,
   primary key (Record_ID)
);

alter table Contract_Additional_Records comment '��ͬ������¼';

alter table Contract_Additional_Records modify column Record_ID varchar(50) comment '��ͬ������¼���';

alter table Contract_Additional_Records modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Contract_Additional_Records modify column Record_Name varchar(6) comment '����������';

alter table Contract_Additional_Records modify column Record_Bulid_Company varchar(50) comment '���赥λ';

alter table Contract_Additional_Records modify column Record_Total_Price numeric(16,2) comment '�����ܽ��';

alter table Contract_Additional_Records modify column Record_Description varchar(1000) comment '������������';

alter table Contract_Additional_Records modify column Record_Reason varchar(1000) comment '����ԭ��';

alter table Contract_Additional_Records modify column Record_Sales varchar(6) comment '���۸�����';

alter table Contract_Additional_Records modify column Record_Attachment longblob comment '���ĸ���';

alter table Contract_Additional_Records modify column Record_Related_Contract_ID varchar(50) comment '������ͬ���';

alter table Contract_Additional_Records modify column Record_Hardware_Equipment_List longblob comment 'Ӳ���豸��ϸ��';

alter table Contract_Additional_Records modify column Record_Software_Function_List longblob comment '��������б�';

alter table Contract_Additional_Records modify column Record_Remarks varchar(200) comment '��ע';

alter table Contract_Additional_Records modify column Record_Approval_Status int(1) comment '����״̬';

alter table Contract_Additional_Records modify column Record_Operator varchar(6) comment '������';

alter table Contract_Additional_Records modify column Record_Commit_Time timestamp comment '�ύ����ʱ��';

/*==============================================================*/
/* Table: Contract_Delivery                                     */
/*==============================================================*/
create table Contract_Delivery
(
   Delivery_ID          varchar(50) not null,
   Contract_ID          varchar(50),
   Delivery_Person_Name varchar(6) not null,
   Delivery_Date        datetime not null,
   Delivery_Content     varchar(1000) not null,
   Delivery_Status      varchar(10) not null,
   Delivery_Attachment  longblob,
   Delivery_Rmarks      varchar(200),
   Delivery_Operator    varchar(6) not null,
   Delivery_Operate_Time timestamp not null,
   primary key (Delivery_ID)
);

alter table Contract_Delivery comment '��ͬ������Ϣ��';

alter table Contract_Delivery modify column Delivery_ID varchar(50) comment '��ͬ������ϢID';

alter table Contract_Delivery modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Contract_Delivery modify column Delivery_Person_Name varchar(6) comment '������';

alter table Contract_Delivery modify column Delivery_Date datetime comment '����ʱ��';

alter table Contract_Delivery modify column Delivery_Content varchar(1000) comment '��������';

alter table Contract_Delivery modify column Delivery_Status varchar(10) comment '��ͬ״̬';

alter table Contract_Delivery modify column Delivery_Attachment longblob comment '����';

alter table Contract_Delivery modify column Delivery_Rmarks varchar(200) comment '��ע';

alter table Contract_Delivery modify column Delivery_Operator varchar(6) comment '������';

alter table Contract_Delivery modify column Delivery_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Delivery_Plan                                         */
/*==============================================================*/
create table Delivery_Plan
(
   Plan_ID              varchar(50) not null,
   Contract_ID          varchar(50),
   Plan_Delivery_Date   datetime not null,
   Plan_Delivery_Content varchar(1000) not null,
   Plan_Attachment      longblob,
   Plan_Remarks         varchar(500),
   Plan_Operator        varchar(6) not null,
   Plan_Operate_Time    timestamp not null,
   primary key (Plan_ID)
);

alter table Delivery_Plan comment '�����ƻ���';

alter table Delivery_Plan modify column Plan_ID varchar(50) comment '�����ƻ�ID';

alter table Delivery_Plan modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Delivery_Plan modify column Plan_Delivery_Date datetime comment '�ƻ�����ʱ��';

alter table Delivery_Plan modify column Plan_Delivery_Content varchar(1000) comment '�ƻ���������';

alter table Delivery_Plan modify column Plan_Attachment longblob comment '����';

alter table Delivery_Plan modify column Plan_Remarks varchar(500) comment '��ע';

alter table Delivery_Plan modify column Plan_Operator varchar(6) comment '������';

alter table Delivery_Plan modify column Plan_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Function_Module                                       */
/*==============================================================*/
create table Function_Module
(
   Module_ID            varchar(50) not null,
   Module_Name          varchar(50) not null,
   Module_Page          varchar(50) not null,
   Module_Operator      varchar(6) not null,
   Module_Operate_Time  timestamp not null,
   primary key (Module_ID)
);

alter table Function_Module comment '����ģ���';

alter table Function_Module modify column Module_ID varchar(50) comment 'ģ��ID';

alter table Function_Module modify column Module_Name varchar(50) comment 'ģ������';

alter table Function_Module modify column Module_Page varchar(50) comment '��Ӧҳ��';

alter table Function_Module modify column Module_Operator varchar(6) comment '������';

alter table Function_Module modify column Module_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Inner_Org_Dept                                        */
/*==============================================================*/
create table Inner_Org_Dept
(
   Dept_ID              varchar(16) not null,
   Dept_Name            varchar(100) not null,
   Dept_Description     varchar(200),
   Dept_Create_Date     datetime not null,
   Dept_Parent          varchar(100),
   Dept_Remarks         varchar(200),
   Dept_Operator        varchar(6),
   Dept_Operate_Time    timestamp,
   primary key (Dept_ID)
);

alter table Inner_Org_Dept comment '�ڲ���֯����_����';

alter table Inner_Org_Dept modify column Dept_ID varchar(16) comment '���ű��';

alter table Inner_Org_Dept modify column Dept_Name varchar(100) comment '��������';

alter table Inner_Org_Dept modify column Dept_Description varchar(200) comment '��������';

alter table Inner_Org_Dept modify column Dept_Create_Date datetime comment '����ʱ��';

alter table Inner_Org_Dept modify column Dept_Parent varchar(100) comment '�ϼ�����';

alter table Inner_Org_Dept modify column Dept_Remarks varchar(200) comment '��ע';

alter table Inner_Org_Dept modify column Dept_Operator varchar(6) comment '������';

alter table Inner_Org_Dept modify column Dept_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Inner_Org_Employee                                    */
/*==============================================================*/
create table Inner_Org_Employee
(
   Employee_ID          varchar(16) not null,
   Dept_ID              varchar(16),
   Job_ID               varchar(16),
   Inner_User_ID        varchar(16),
   User_Role_ID         varchar(8),
   Module_ID            varchar(32),
   Employee_Name        varchar(6) not null,
   Employee_Dept        varchar(100) not null,
   Employee_Center      varchar(100),
   Employee_Join_Date   datetime not null,
   Employee_Salary_Hour numeric(6,2) not null,
   Employee_Status      int(1) not null,
   Employee_Phone_Number varchar(50) not null,
   Employee_Inner_Phone_Number varchar(4),
   Employee_QQ          varchar(50) not null,
   Employee_Remarks     varchar(200),
   Employee_Operator    varchar(6),
   Employee_Operate_Time timestamp,
   primary key (Employee_ID)
);

alter table Inner_Org_Employee comment '�ڲ���֯����_Ա����';

alter table Inner_Org_Employee modify column Employee_ID varchar(16) comment 'Ա������';

alter table Inner_Org_Employee modify column Dept_ID varchar(16) comment '���ű��';

alter table Inner_Org_Employee modify column Job_ID varchar(16) comment '��λ���';

alter table Inner_Org_Employee modify column Inner_User_ID varchar(16) comment '�ڲ��˺�ID';

alter table Inner_Org_Employee modify column User_Role_ID varchar(8) comment '�û���ɫID';

alter table Inner_Org_Employee modify column Module_ID varchar(32) comment 'ģ��ID';

alter table Inner_Org_Employee modify column Employee_Name varchar(6) comment 'Ա������';

alter table Inner_Org_Employee modify column Employee_Dept varchar(100) comment '����';

alter table Inner_Org_Employee modify column Employee_Center varchar(100) comment '��������';

alter table Inner_Org_Employee modify column Employee_Join_Date datetime comment '��ְʱ��';

alter table Inner_Org_Employee modify column Employee_Salary_Hour numeric(6,2) comment 'ʱн';

alter table Inner_Org_Employee modify column Employee_Status int(1) comment 'Ա��״̬';

alter table Inner_Org_Employee modify column Employee_Phone_Number varchar(50) comment '�绰';

alter table Inner_Org_Employee modify column Employee_Inner_Phone_Number varchar(4) comment '�绰С��';

alter table Inner_Org_Employee modify column Employee_QQ varchar(50) comment 'QQ';

alter table Inner_Org_Employee modify column Employee_Remarks varchar(200) comment '��ע';

alter table Inner_Org_Employee modify column Employee_Operator varchar(6) comment '������';

alter table Inner_Org_Employee modify column Employee_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Inner_Org_Job                                         */
/*==============================================================*/
create table Inner_Org_Job
(
   Job_ID               varchar(16) not null,
   Dept_ID              varchar(16),
   Job_Name             varchar(50) not null,
   Job_Dept             varchar(100),
   Job_Rank             varchar(50) not null,
   Job_Rank_Description varchar(200),
   Job_Descrription     varchar(200),
   Job_Remarks          varchar(200),
   Job_Operator         varchar(6),
   Job_Operate_Time     timestamp,
   primary key (Job_ID)
);

alter table Inner_Org_Job comment '�ڲ���֯����_��λ';

alter table Inner_Org_Job modify column Job_ID varchar(16) comment '��λ���';

alter table Inner_Org_Job modify column Dept_ID varchar(16) comment '���ű��';

alter table Inner_Org_Job modify column Job_Name varchar(50) comment '��λ����';

alter table Inner_Org_Job modify column Job_Dept varchar(100) comment '��������';

alter table Inner_Org_Job modify column Job_Rank varchar(50) comment '��������';

alter table Inner_Org_Job modify column Job_Rank_Description varchar(200) comment '��������';

alter table Inner_Org_Job modify column Job_Descrription varchar(200) comment '��λ����';

alter table Inner_Org_Job modify column Job_Remarks varchar(200) comment '��ע';

alter table Inner_Org_Job modify column Job_Operator varchar(6) comment '������';

alter table Inner_Org_Job modify column Job_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Inner_User                                            */
/*==============================================================*/
create table Inner_User
(
   Inner_User_ID        varchar(16) not null,
   Inner_User_Account   varchar(16) not null,
   Inner_User_Pwd       varchar(16) not null,
   Inner_User_Log_Status int(1) not null,
   Inner_User_Log_DATE  datetime not null,
   Inner_User_log_IP    varchar(16) not null,
   Inner_User_Operator  varchar(6) not null,
   Inner_User_Operate_Time timestamp not null,
   primary key (Inner_User_ID)
);

alter table Inner_User comment '�ڲ��˺�';

alter table Inner_User modify column Inner_User_ID varchar(16) comment '�ڲ��˺�ID';

alter table Inner_User modify column Inner_User_Account varchar(16) comment '�ڲ��˺�';

alter table Inner_User modify column Inner_User_Pwd varchar(16) comment '�ڲ��˺�����';

alter table Inner_User modify column Inner_User_Log_Status int(1) comment '��¼״̬';

alter table Inner_User modify column Inner_User_Log_DATE datetime comment '�ϴε�¼ʱ��';

alter table Inner_User modify column Inner_User_log_IP varchar(16) comment '�ϴε�¼IP';

alter table Inner_User modify column Inner_User_Operator varchar(6) comment '������';

alter table Inner_User modify column Inner_User_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Invoice                                               */
/*==============================================================*/
create table Invoice
(
   Invoice_ID           varchar(50) not null,
   Contract_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Invoice_Number       varchar(50) not null,
   Invoice_Price        numeric(20,4) not null,
   Invoice_Type         varchar(20) not null,
   Invoice_Content      varchar(100),
   Invoice_Date         datetime not null,
   Invoice_Person       varchar(6) not null,
   Invoice_Receiver_Time datetime not null,
   Invoice_Receiver     varchar(6) not null,
   Invoice_Attachment   longblob,
   Invoice_Contract_Status varchar(20),
   Invoice_Remarks      varchar(200),
   Invoice_Operator     varchar(6) not null,
   Invoice_Operate_Time timestamp not null,
   primary key (Invoice_ID)
);

alter table Invoice comment '��Ʊ��Ϣ��';

alter table Invoice modify column Invoice_ID varchar(50) comment '��Ʊ���';

alter table Invoice modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Invoice modify column Business_ID varchar(50) comment 'ҵ����';

alter table Invoice modify column Invoice_Number varchar(50) comment '��Ʊ����';

alter table Invoice modify column Invoice_Price numeric(20,4) comment '��Ʊ���';

alter table Invoice modify column Invoice_Type varchar(20) comment '��Ʊ����';

alter table Invoice modify column Invoice_Content varchar(100) comment '��Ʊ����';

alter table Invoice modify column Invoice_Date datetime comment '��Ʊ����';

alter table Invoice modify column Invoice_Person varchar(6) comment '��Ʊ��';

alter table Invoice modify column Invoice_Receiver_Time datetime comment '��ȡ����';

alter table Invoice modify column Invoice_Receiver varchar(6) comment '��Ʊ��ȡ��';

alter table Invoice modify column Invoice_Attachment longblob comment '����';

alter table Invoice modify column Invoice_Contract_Status varchar(20) comment '��ͬ״̬';

alter table Invoice modify column Invoice_Remarks varchar(200) comment '��ע';

alter table Invoice modify column Invoice_Operator varchar(6) comment '������';

alter table Invoice modify column Invoice_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Payment_Paid                                          */
/*==============================================================*/
create table Payment_Paid
(
   Paid_ID              varchar(50) not null,
   Contract_ID          varchar(50),
   Purchase_ID          varchar(50),
   Contract__D          varchar(50) not null,
   Paid_Time            datetime not null,
   Paid_Price           numeric(10,2) not null,
   Paid_Account_Number  varchar(25) not null,
   Paid_Type            varchar(20) not null,
   Paid_Status          varchar(10),
   Paid_Remarks         varchar(200),
   Paid_Operator        varchar(6) not null,
   Paid_Operate_Time    timestamp not null,
   primary key (Paid_ID)
);

alter table Payment_Paid comment '������Ϣ��';

alter table Payment_Paid modify column Paid_ID varchar(50) comment '������Ϣ���';

alter table Payment_Paid modify column Contract_ID varchar(50) comment '��ͬ���2';

alter table Payment_Paid modify column Purchase_ID varchar(50) comment '�ɹ����';

alter table Payment_Paid modify column Contract__D varchar(50) comment '��ͬ���';

alter table Payment_Paid modify column Paid_Time datetime comment '����ʱ��';

alter table Payment_Paid modify column Paid_Price numeric(10,2) comment '������';

alter table Payment_Paid modify column Paid_Account_Number varchar(25) comment '�����˺�';

alter table Payment_Paid modify column Paid_Type varchar(20) comment '��������';

alter table Payment_Paid modify column Paid_Status varchar(10) comment '��ͬ״̬';

alter table Payment_Paid modify column Paid_Remarks varchar(200) comment '��ע';

alter table Payment_Paid modify column Paid_Operator varchar(6) comment '������';

alter table Payment_Paid modify column Paid_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Payment_Received                                      */
/*==============================================================*/
create table Payment_Received
(
   Received_ID          varchar(50) not null,
   Contract_ID          varchar(50) not null,
   Business_ID          varchar(50),
   Received_Time        datetime not null,
   Received_Price       numeric(10,2) not null,
   Received_Card_Number varchar(25) not null,
   Received_Type        varchar(20) not null,
   Received_Contract_Status varchar(10),
   Received_Remarks     varchar(200),
   Received_Operator    varchar(6) not null,
   Received_Operate_Time timestamp not null,
   primary key (Received_ID)
);

alter table Payment_Received comment '�ؿ���Ϣ��';

alter table Payment_Received modify column Received_ID varchar(50) comment '�ؿ���Ϣ���';

alter table Payment_Received modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Payment_Received modify column Business_ID varchar(50) comment 'ҵ����';

alter table Payment_Received modify column Received_Time datetime comment '�ؿ�ʱ��';

alter table Payment_Received modify column Received_Price numeric(10,2) comment '�ؿ���';

alter table Payment_Received modify column Received_Card_Number varchar(25) comment '�ؿ��˺�';

alter table Payment_Received modify column Received_Type varchar(20) comment '��������';

alter table Payment_Received modify column Received_Contract_Status varchar(10) comment '��ͬ״̬';

alter table Payment_Received modify column Received_Remarks varchar(200) comment '��ע';

alter table Payment_Received modify column Received_Operator varchar(6) comment '������';

alter table Payment_Received modify column Received_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Project_Budget                                        */
/*==============================================================*/
create table Project_Budget
(
   Budget_ID            varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Business_ID          varchar(50) not null,
   Project_ID           varchar(50) not null,
   Budget_Profit_Rate   numeric(5,2) not null,
   Budget_Account_Receivable numeric(20,4) not null,
   Budget_Total_Cost    numeric(20,4) not null,
   Budget_Conformance   varchar(1) not null,
   Budget_Service_Revenue numeric(20,4) not null,
   Budget_Tax           numeric(20,4) not null,
   Budget_Service_Revenue_Net numeric(20,4),
   Budget_Purchase_Cost numeric(20,4) not null,
   Budget_Labor_Cost    numeric(20,4) not null,
   Budget_Travel_Cost   numeric(20,4) not null,
   Budget_Cost          numeric(20,4) not null,
   Budget_Profit        numeric(20,4) not null,
   Budget_Operator      varchar(6) not null,
   Budget_Operate_Time  timestamp not null,
   primary key (Budget_ID)
);

alter table Project_Budget comment '��ĿԤ���';

alter table Project_Budget modify column Budget_ID varchar(50) comment '��ĿԤ����';

alter table Project_Budget modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Project_Budget modify column Business_ID varchar(50) comment 'ҵ����';

alter table Project_Budget modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Project_Budget modify column Budget_Profit_Rate numeric(5,2) comment '��Ŀ�ƻ�������';

alter table Project_Budget modify column Budget_Account_Receivable numeric(20,4) comment 'Ӧ���˿��ܶ�';

alter table Project_Budget modify column Budget_Total_Cost numeric(20,4) comment '�ƻ��ɱ��ܶ�';

alter table Project_Budget modify column Budget_Conformance varchar(1) comment '�ƻ��Ƿ�Ϲ�';

alter table Project_Budget modify column Budget_Service_Revenue numeric(20,4) comment '��������';

alter table Project_Budget modify column Budget_Tax numeric(20,4) comment '˰��';

alter table Project_Budget modify column Budget_Service_Revenue_Net numeric(20,4) comment '��������';

alter table Project_Budget modify column Budget_Purchase_Cost numeric(20,4) comment '�ɹ��ɱ�';

alter table Project_Budget modify column Budget_Labor_Cost numeric(20,4) comment '�˹��ɱ�';

alter table Project_Budget modify column Budget_Travel_Cost numeric(20,4) comment '���óɱ�';

alter table Project_Budget modify column Budget_Cost numeric(20,4) comment '���ú�֧�֣���˰��';

alter table Project_Budget modify column Budget_Profit numeric(20,4) comment '����';

alter table Project_Budget modify column Budget_Operator varchar(6) comment '������';

alter table Project_Budget modify column Budget_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Purchase                                              */
/*==============================================================*/
create table Purchase
(
   Purchase_ID          varchar(50) not null,
   Project_ID           varchar(50) not null,
   Approval_Purchase_ID varchar(50),
   Purchase_Person      varchar(6) not null,
   Purchase_TIme        datetime not null,
   Purchase_Delivery_Time datetime not null,
   Purchase_Delivery_Place varchar(50) not null,
   Purchase_Consignee   varchar(6) not null,
   Purchase_Phone_Number varchar(20) not null,
   Purchase_Name        varchar(50) not null,
   Purchase_Brand       varchar(30) not null,
   Purchase_Mode        varchar(30) not null,
   Purchase_Config      varchar(200),
   Purchase_Unit        varchar(20) not null,
   Purchase_Unit_Price  numeric(10,2) not null,
   Purchase_Number      int(10) not null,
   Purchase_Total_Price numeric(10,2) not null,
   Purchase_Order_Status varchar(10) not null,
   Purchase_Paid        numeric(10,2) not null,
   Purchase_Not_Paid    numeric(10,2) not null,
   Purchase_Fulfilment_Status varchar(10) not null,
   Purchase_Remarks     varchar(200),
   Purchase_Operator    varchar(6) not null,
   Purchase_Operate_Time timestamp not null,
   primary key (Purchase_ID)
);

alter table Purchase comment '�ɹ���Ϣ��';

alter table Purchase modify column Purchase_ID varchar(50) comment '�ɹ����';

alter table Purchase modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Purchase modify column Approval_Purchase_ID varchar(50) comment '�ɹ�������';

alter table Purchase modify column Purchase_Person varchar(6) comment '�ɹ���';

alter table Purchase modify column Purchase_TIme datetime comment '�ɹ�ʱ��';

alter table Purchase modify column Purchase_Delivery_Time datetime comment '����ʱ��';

alter table Purchase modify column Purchase_Delivery_Place varchar(50) comment '�����ص�';

alter table Purchase modify column Purchase_Consignee varchar(6) comment '�ջ���';

alter table Purchase modify column Purchase_Phone_Number varchar(20) comment '��ϵ�绰';

alter table Purchase modify column Purchase_Name varchar(50) comment '��Ʒ����';

alter table Purchase modify column Purchase_Brand varchar(30) comment 'Ʒ��';

alter table Purchase modify column Purchase_Mode varchar(30) comment '�ͺ�';

alter table Purchase modify column Purchase_Config varchar(200) comment '���/����';

alter table Purchase modify column Purchase_Unit varchar(20) comment '��λ';

alter table Purchase modify column Purchase_Unit_Price numeric(10,2) comment '����';

alter table Purchase modify column Purchase_Number int(10) comment '����';

alter table Purchase modify column Purchase_Total_Price numeric(10,2) comment '�ܼ�';

alter table Purchase modify column Purchase_Order_Status varchar(10) comment '�������';

alter table Purchase modify column Purchase_Paid numeric(10,2) comment '�Ѹ���';

alter table Purchase modify column Purchase_Not_Paid numeric(10,2) comment 'δ����';

alter table Purchase modify column Purchase_Fulfilment_Status varchar(10) comment '�������';

alter table Purchase modify column Purchase_Remarks varchar(200) comment '��ע';

alter table Purchase modify column Purchase_Operator varchar(6) comment '������';

alter table Purchase modify column Purchase_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Bug_Category                                    */
/*==============================================================*/
create table Sales_Bug_Category
(
   BUG_ID               varchar(50) not null,
   Complaint_ID         varchar(50),
   Service_ID           varchar(50),
   Product_ID           varchar(50),
   Module_ID            varchar(50) not null,
   Feedback_ID          varchar(50),
   Bug_Remarks          varchar(200),
   Bug_Recorder         varchar(6) not null,
   Bug_Record_Time      timestamp not null,
   primary key (BUG_ID)
);

alter table Sales_Bug_Category comment 'BUG������Ϣ��';

alter table Sales_Bug_Category modify column BUG_ID varchar(50) comment 'BUG���';

alter table Sales_Bug_Category modify column Complaint_ID varchar(50) comment '�ͻ�Ͷ�߼�¼���';

alter table Sales_Bug_Category modify column Service_ID varchar(50) comment '�ͷ���¼���';

alter table Sales_Bug_Category modify column Product_ID varchar(50) comment '��Ʒ���';

alter table Sales_Bug_Category modify column Module_ID varchar(50) comment 'ģ����';

alter table Sales_Bug_Category modify column Feedback_ID varchar(50) comment '�ͻ��������ݱ��';

alter table Sales_Bug_Category modify column Bug_Remarks varchar(200) comment '��ע';

alter table Sales_Bug_Category modify column Bug_Recorder varchar(6) comment '�����¼��';

alter table Sales_Bug_Category modify column Bug_Record_Time timestamp comment '��¼ʱ��';

/*==============================================================*/
/* Table: Sales_Business                                        */
/*==============================================================*/
create table Sales_Business
(
   Business_ID          varchar(50) not null,
   Customer_ID          varchar(50) not null,
   Contact_ID           varchar(50),
   Business_Name        varchar(50) not null,
   Business_Category    varchar(20) not null,
   Business_Status      varchar(20) not null,
   Business_Sales       varchar(6) not null,
   Business_Old_ID      varchar(50),
   Business_Description varchar(1000) not null,
   Business_Remarks     varchar(200),
   Business_Operator    varchar(6) not null,
   Business_Operate_Time timestamp not null,
   primary key (Business_ID)
);

alter table Sales_Business comment 'ҵ����Ϣ��';

alter table Sales_Business modify column Business_ID varchar(50) comment 'ҵ����';

alter table Sales_Business modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Business modify column Contact_ID varchar(50) comment '��ϵ�˱��';

alter table Sales_Business modify column Business_Name varchar(50) comment 'ҵ������';

alter table Sales_Business modify column Business_Category varchar(20) comment 'ҵ������';

alter table Sales_Business modify column Business_Status varchar(20) comment 'ҵ��״̬';

alter table Sales_Business modify column Business_Sales varchar(6) comment '���۸�����';

alter table Sales_Business modify column Business_Old_ID varchar(50) comment '��ҵ����';

alter table Sales_Business modify column Business_Description varchar(1000) comment 'ҵ������';

alter table Sales_Business modify column Business_Remarks varchar(200) comment '��ע';

alter table Sales_Business modify column Business_Operator varchar(6) comment 'ҵ�񴴽���';

alter table Sales_Business modify column Business_Operate_Time timestamp comment 'ҵ�񴴽�ʱ��';

/*==============================================================*/
/* Table: Sales_Company_Customer                                */
/*==============================================================*/
create table Sales_Company_Customer
(
   Customer_ID          varchar(50) not null,
   Customer_Province    varchar(25) not null,
   Customer_City        varchar(25) not null,
   Customer_County      varchar(25) not null,
   Customer_Name        varchar(100),
   Customer_Simple_Name varchar(50),
   Customer_Owner       varchar(6) not null,
   Customer_Sales       varchar(6) not null,
   Customer_Product     varchar(50),
   Customer_Req_Des     varchar(200),
   Customer_Attachment  longblob,
   Customer_Category    varchar(25) not null,
   Customer_Character   varchar(25) not null,
   Customer_Status      varchar(20) not null,
   Customer_Level       int(1) not null,
   Customer_Sale_Phase  varchar(20) not null,
   Customer_Inner_Phase varchar(20) not null,
   Customer_Source      varchar(20) not null,
   Customer_Industry    varchar(50),
   Customer_Staff_Size  int(8),
   Customer_Credit_Rank int(1),
   Customer_Potential   varchar(20),
   Customer_Emp_Number  int(8),
   Customer_Parent      varchar(50),
   Customer_Introduction varchar(500),
   Customer_Visit_Mode  varchar(20),
   Customer_Old_ID      varchar(50),
   Customer_Child_Company varchar(50),
   Customer_Hot         int(1) not null,
   Customer_Hot_Rank    varchar(20),
   Customer_Hot_Classif varchar(20),
   Customer_Volume      numeric(16,4),
   Customer_Hot_Desc    varchar(200),
   Customer_Invoice_Name varchar(50),
   Customer_Tax_Number  varchar(20),
   Customer_Bank        varchar(50),
   Customer_Account_Num varchar(30),
   Customer_Contact_Sta varchar(20) not null,
   Customer_Address     varchar(100),
   Customer_Phone_Num   varchar(20) not null,
   Customer_Fax         varchar(20),
   Customer_MailBox     varchar(50),
   Customer_URL         varchar(50),
   Customer_Postcode    varchar(10),
   Customer_Leader      varchar(6),
   Customer_Job_Title   varchar(20),
   Customer_Payment_Rate numeric(6,4),
   Customer_Heating_Share numeric(6,4),
   Customer_Complaint_Rate numeric(6,4),
   Customer_Heating_Area numeric(16,2),
   Customer_Heating_Source_Number int(10),
   Customer_Steam_Area  numeric(16,2),
   Customer_Hot_Water_Area numeric(16,2),
   Customer_Own_Heating_Source int(10),
   Customer_Out_Heating_Source int(10),
   Customer_Heat_Loss   numeric(10,2),
   Customer_Water_Loss  numeric(10,2),
   Customer_Electrick_Loss numeric(10,2),
   Customer_Plan_One_Year varchar(500),
   Customer_Plan_Tow_Year varchar(500),
   Customer_Plan_Three_Year varchar(500),
   Customer_Remarks     varchar(500),
   Customer_Operator    varchar(6) not null,
   Customer_Operate_Time timestamp not null,
   primary key (Customer_ID)
);

alter table Sales_Company_Customer comment '��ҵ�ͻ���Ϣ��';

alter table Sales_Company_Customer modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Company_Customer modify column Customer_Province varchar(25) comment 'ʡ��';

alter table Sales_Company_Customer modify column Customer_City varchar(25) comment '����';

alter table Sales_Company_Customer modify column Customer_County varchar(25) comment '����';

alter table Sales_Company_Customer modify column Customer_Name varchar(100) comment '��ҵ����';

alter table Sales_Company_Customer modify column Customer_Simple_Name varchar(50) comment '���Ǽ��';

alter table Sales_Company_Customer modify column Customer_Owner varchar(6) comment '�ͻ�������';

alter table Sales_Company_Customer modify column Customer_Sales varchar(6) comment '���۸�����';

alter table Sales_Company_Customer modify column Customer_Product varchar(50) comment '��Ʒ����';

alter table Sales_Company_Customer modify column Customer_Req_Des varchar(200) comment '�����Ҫ����';

alter table Sales_Company_Customer modify column Customer_Attachment longblob comment '������и���';

alter table Sales_Company_Customer modify column Customer_Category varchar(25) comment '�ͻ����';

alter table Sales_Company_Customer modify column Customer_Character varchar(25) comment '��ҵ����';

alter table Sales_Company_Customer modify column Customer_Status varchar(20) comment '�ͻ�״̬';

alter table Sales_Company_Customer modify column Customer_Level int(1) comment '�ͻ�����';

alter table Sales_Company_Customer modify column Customer_Sale_Phase varchar(20) comment '���۽׶�';

alter table Sales_Company_Customer modify column Customer_Inner_Phase varchar(20) comment '�ͻ��ڲ��׶�';

alter table Sales_Company_Customer modify column Customer_Source varchar(20) comment '��Դ';

alter table Sales_Company_Customer modify column Customer_Industry varchar(50) comment '��ҵ';

alter table Sales_Company_Customer modify column Customer_Staff_Size int(8) comment '��Ա��ģ';

alter table Sales_Company_Customer modify column Customer_Credit_Rank int(1) comment '���õȼ�';

alter table Sales_Company_Customer modify column Customer_Potential varchar(20) comment '�ͻ�Ǳ��';

alter table Sales_Company_Customer modify column Customer_Emp_Number int(8) comment 'Ա������';

alter table Sales_Company_Customer modify column Customer_Parent varchar(50) comment '�ϼ���λ';

alter table Sales_Company_Customer modify column Customer_Introduction varchar(500) comment '��˾���';

alter table Sales_Company_Customer modify column Customer_Visit_Mode varchar(20) comment '�ݷý�ͨ��ʽ';

alter table Sales_Company_Customer modify column Customer_Old_ID varchar(50) comment '�ɿͻ����';

alter table Sales_Company_Customer modify column Customer_Child_Company varchar(50) comment '�ӹ�˾����';

alter table Sales_Company_Customer modify column Customer_Hot int(1) comment '�ȵ�ͻ�';

alter table Sales_Company_Customer modify column Customer_Hot_Rank varchar(20) comment '�ȶ�';

alter table Sales_Company_Customer modify column Customer_Hot_Classif varchar(20) comment '�ȵ�ͻ�����';

alter table Sales_Company_Customer modify column Customer_Volume numeric(16,4) comment 'Ԥ�ڳɽ����';

alter table Sales_Company_Customer modify column Customer_Hot_Desc varchar(200) comment '�ȵ�˵��';

alter table Sales_Company_Customer modify column Customer_Invoice_Name varchar(50) comment '��Ʊ����';

alter table Sales_Company_Customer modify column Customer_Tax_Number varchar(20) comment '��˰��˰��';

alter table Sales_Company_Customer modify column Customer_Bank varchar(50) comment '������';

alter table Sales_Company_Customer modify column Customer_Account_Num varchar(30) comment '�˺�';

alter table Sales_Company_Customer modify column Customer_Contact_Sta varchar(20) comment '��ϵ���';

alter table Sales_Company_Customer modify column Customer_Address varchar(100) comment '��ҵ�칫��ַ';

alter table Sales_Company_Customer modify column Customer_Phone_Num varchar(20) comment '�绰����';

alter table Sales_Company_Customer modify column Customer_Fax varchar(20) comment '����';

alter table Sales_Company_Customer modify column Customer_MailBox varchar(50) comment '����';

alter table Sales_Company_Customer modify column Customer_URL varchar(50) comment '������ַ';

alter table Sales_Company_Customer modify column Customer_Postcode varchar(10) comment '�ʱ�';

alter table Sales_Company_Customer modify column Customer_Leader varchar(6) comment '��ҵ������';

alter table Sales_Company_Customer modify column Customer_Job_Title varchar(20) comment 'ְ��';

alter table Sales_Company_Customer modify column Customer_Payment_Rate numeric(6,4) comment '�շ��ս���';

alter table Sales_Company_Customer modify column Customer_Heating_Share numeric(6,4) comment '��ҵռȫ���Ȼ���';

alter table Sales_Company_Customer modify column Customer_Complaint_Rate numeric(6,4) comment 'Ͷ����';

alter table Sales_Company_Customer modify column Customer_Heating_Area numeric(16,2) comment '�������';

alter table Sales_Company_Customer modify column Customer_Heating_Source_Number int(10) comment '����վ����';

alter table Sales_Company_Customer modify column Customer_Steam_Area numeric(16,2) comment '�������';

alter table Sales_Company_Customer modify column Customer_Hot_Water_Area numeric(16,2) comment '��ˮ���';

alter table Sales_Company_Customer modify column Customer_Own_Heating_Source int(10) comment '������Դ';

alter table Sales_Company_Customer modify column Customer_Out_Heating_Source int(10) comment '�⹺��Դ';

alter table Sales_Company_Customer modify column Customer_Heat_Loss numeric(10,2) comment '�Ⱥ�';

alter table Sales_Company_Customer modify column Customer_Water_Loss numeric(10,2) comment 'ˮ��';

alter table Sales_Company_Customer modify column Customer_Electrick_Loss numeric(10,2) comment '���';

alter table Sales_Company_Customer modify column Customer_Plan_One_Year varchar(500) comment 'δ��һ�������滮';

alter table Sales_Company_Customer modify column Customer_Plan_Tow_Year varchar(500) comment 'δ�����������滮';

alter table Sales_Company_Customer modify column Customer_Plan_Three_Year varchar(500) comment 'δ�����������滮';

alter table Sales_Company_Customer modify column Customer_Remarks varchar(500) comment '��ע';

alter table Sales_Company_Customer modify column Customer_Operator varchar(6) comment '������';

alter table Sales_Company_Customer modify column Customer_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Competitor                                      */
/*==============================================================*/
create table Sales_Competitor
(
   Complaint_ID         varchar(50) not null,
   Customer_ID          varchar(50),
   Complaint_Project_Type varchar(20) not null,
   Complaint_Product_Category varchar(20) not null,
   Complaint_Product_Name varchar(50) not null,
   Complaint_Product_Price numeric(16,2),
   Complaint_Product_Description varchar(1000),
   Complaint_Company_Name varchar(50) not null,
   Complaint_Attachment longblob,
   Complaint_Remarks    varchar(200),
   Complaint_Operator   varchar(6) not null,
   Complaint_Operate_Time timestamp not null,
   primary key (Complaint_ID)
);

alter table Sales_Competitor comment '����������Ϣ��';

alter table Sales_Competitor modify column Complaint_ID varchar(50) comment '��������ID';

alter table Sales_Competitor modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Competitor modify column Complaint_Project_Type varchar(20) comment '��Ŀ����';

alter table Sales_Competitor modify column Complaint_Product_Category varchar(20) comment '��Ʒ����';

alter table Sales_Competitor modify column Complaint_Product_Name varchar(50) comment '��Ʒ����';

alter table Sales_Competitor modify column Complaint_Product_Price numeric(16,2) comment '��Ʒ�۸�';

alter table Sales_Competitor modify column Complaint_Product_Description varchar(1000) comment '��Ʒ����';

alter table Sales_Competitor modify column Complaint_Company_Name varchar(50) comment '��˾����';

alter table Sales_Competitor modify column Complaint_Attachment longblob comment '����';

alter table Sales_Competitor modify column Complaint_Remarks varchar(200) comment '��ע';

alter table Sales_Competitor modify column Complaint_Operator varchar(6) comment '������';

alter table Sales_Competitor modify column Complaint_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Customer_Child_Company                          */
/*==============================================================*/
create table Sales_Customer_Child_Company
(
   Child_Company_ID     varchar(50) not null,
   Customer__ID         varchar(50),
   Child_Company_Name   varchar(50) not null,
   Child_Company_Remarks varchar(200),
   Child_Company_Operator varchar(6) not null,
   Child_Company_Operate_Time timestamp not null,
   primary key (Child_Company_ID)
);

alter table Sales_Customer_Child_Company comment '�ͻ���֯����_�ӹ�˾';

alter table Sales_Customer_Child_Company modify column Child_Company_ID varchar(50) comment '�ӹ�˾���';

alter table Sales_Customer_Child_Company modify column Customer__ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Customer_Child_Company modify column Child_Company_Name varchar(50) comment '�ӹ�˾����';

alter table Sales_Customer_Child_Company modify column Child_Company_Remarks varchar(200) comment '��ע';

alter table Sales_Customer_Child_Company modify column Child_Company_Operator varchar(6) comment '������';

alter table Sales_Customer_Child_Company modify column Child_Company_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Customer_Contact                                */
/*==============================================================*/
create table Sales_Customer_Contact
(
   Contact_ID           varchar(50) not null,
   Contact_Name         varchar(6) not null,
   Contact_Sex          int(1) not null,
   Contact_Salutation   varchar(20),
   Contact_Title        varchar(50) not null,
   Contact_Responsibility varchar(100),
   Contact_Role         varchar(50),
   Customer_ID          varchar(50) not null,
   Contact_Dept         varchar(50),
   Contact_Job          varchar(50),
   Contact_Marital_Status int(1),
   Contact_Age          int(2),
   Contact_Family_Status varchar(50),
   Contact_Graduate_Institutions varchar(30),
   Contact_Skill        varchar(50),
   Contact_Years_Working int(2),
   Contact_Experience   int(2),
   Contact_Previous_Company varchar(50),
   Contact_Superiors    varchar(6),
   Contact_Status       varchar(20) not null,
   Contact_Introduction varchar(20) not null,
   Contact_Owner        varchar(6) not null,
   Contact_Sales        varchar(6) not null,
   Contact_Phone_Number varchar(20) not null,
   Contact_Mailbox      varchar(50),
   Contact_Work_Phone_Number varchar(20),
   Contact_Family_Phone_Number varchar(20),
   Contact_Fax          varchar(20),
   Contact_Family_Address varchar(100),
   Contact_Weixin       varchar(20),
   Contact_QQ           varchar(20),
   Contact_Special_Day_Category varchar(20),
   Contact_Special_Day  datetime,
   Contact_Interest     varchar(50),
   Contact_Remarks      varchar(200),
   Contact_Operator     varchar(6) not null,
   Contact_Operate_Time timestamp not null,
   primary key (Contact_ID)
);

alter table Sales_Customer_Contact comment '��ϵ����Ϣ��';

alter table Sales_Customer_Contact modify column Contact_ID varchar(50) comment '��ϵ�˱��';

alter table Sales_Customer_Contact modify column Contact_Name varchar(6) comment '����';

alter table Sales_Customer_Contact modify column Contact_Sex int(1) comment '�Ա�';

alter table Sales_Customer_Contact modify column Contact_Salutation varchar(20) comment '��ν';

alter table Sales_Customer_Contact modify column Contact_Title varchar(50) comment 'ְ��';

alter table Sales_Customer_Contact modify column Contact_Responsibility varchar(100) comment '����ҵ��';

alter table Sales_Customer_Contact modify column Contact_Role varchar(50) comment '��ɫ';

alter table Sales_Customer_Contact modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Customer_Contact modify column Contact_Dept varchar(50) comment '����';

alter table Sales_Customer_Contact modify column Contact_Job varchar(50) comment '��λ';

alter table Sales_Customer_Contact modify column Contact_Marital_Status int(1) comment '���';

alter table Sales_Customer_Contact modify column Contact_Age int(2) comment '����';

alter table Sales_Customer_Contact modify column Contact_Family_Status varchar(50) comment '��ͥ���';

alter table Sales_Customer_Contact modify column Contact_Graduate_Institutions varchar(30) comment '��ҵԺУ';

alter table Sales_Customer_Contact modify column Contact_Skill varchar(50) comment 'רҵ����';

alter table Sales_Customer_Contact modify column Contact_Years_Working int(2) comment '��������';

alter table Sales_Customer_Contact modify column Contact_Experience int(2) comment '��������';

alter table Sales_Customer_Contact modify column Contact_Previous_Company varchar(50) comment '����ְ��λ';

alter table Sales_Customer_Contact modify column Contact_Superiors varchar(6) comment '�ϼ��쵼';

alter table Sales_Customer_Contact modify column Contact_Status varchar(20) comment '��ϵ��״̬';

alter table Sales_Customer_Contact modify column Contact_Introduction varchar(20) comment '��ϵ���';

alter table Sales_Customer_Contact modify column Contact_Owner varchar(6) comment '�ͻ�������';

alter table Sales_Customer_Contact modify column Contact_Sales varchar(6) comment '���۸�����';

alter table Sales_Customer_Contact modify column Contact_Phone_Number varchar(20) comment '�ֻ�';

alter table Sales_Customer_Contact modify column Contact_Mailbox varchar(50) comment '����';

alter table Sales_Customer_Contact modify column Contact_Work_Phone_Number varchar(20) comment '�����绰';

alter table Sales_Customer_Contact modify column Contact_Family_Phone_Number varchar(20) comment '��ͥ�绰';

alter table Sales_Customer_Contact modify column Contact_Fax varchar(20) comment '����';

alter table Sales_Customer_Contact modify column Contact_Family_Address varchar(100) comment '��ͥסַ';

alter table Sales_Customer_Contact modify column Contact_Weixin varchar(20) comment '΢��';

alter table Sales_Customer_Contact modify column Contact_QQ varchar(20) comment 'QQ';

alter table Sales_Customer_Contact modify column Contact_Special_Day_Category varchar(20) comment '���������';

alter table Sales_Customer_Contact modify column Contact_Special_Day datetime comment '������';

alter table Sales_Customer_Contact modify column Contact_Interest varchar(50) comment '����';

alter table Sales_Customer_Contact modify column Contact_Remarks varchar(200) comment '��ע';

alter table Sales_Customer_Contact modify column Contact_Operator varchar(6) comment '������';

alter table Sales_Customer_Contact modify column Contact_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Customer_Dept                                   */
/*==============================================================*/
create table Sales_Customer_Dept
(
   Customer_Dept_ID     varchar(50) not null,
   Child_Company_ID     varchar(50),
   Customer_Dept_Name   varchar(50) not null,
   Customer_Dept_Description varchar(200),
   Customer_Dept_Parent_Dept varchar(50),
   Customer_Dept_Remarks varchar(200),
   Customer_Dept_Operator varchar(6) not null,
   Customer_Dept_Operate_Time timestamp not null,
   primary key (Customer_Dept_ID)
);

alter table Sales_Customer_Dept comment '�ͻ���֯����_����';

alter table Sales_Customer_Dept modify column Customer_Dept_ID varchar(50) comment '���ű��';

alter table Sales_Customer_Dept modify column Child_Company_ID varchar(50) comment '�ӹ�˾���';

alter table Sales_Customer_Dept modify column Customer_Dept_Name varchar(50) comment '��������';

alter table Sales_Customer_Dept modify column Customer_Dept_Description varchar(200) comment '��������';

alter table Sales_Customer_Dept modify column Customer_Dept_Parent_Dept varchar(50) comment '�ϼ�����';

alter table Sales_Customer_Dept modify column Customer_Dept_Remarks varchar(200) comment '��ע';

alter table Sales_Customer_Dept modify column Customer_Dept_Operator varchar(6) comment '������';

alter table Sales_Customer_Dept modify column Customer_Dept_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Customer__Job                                   */
/*==============================================================*/
create table Sales_Customer__Job
(
   Customer_Job_ID      varchar(50) not null,
   Customer_Dept_ID     varchar(50),
   Customer_Job_name    varchar(50) not null,
   Customer_Job_Description varchar(200),
   Customer_Job_Remarks varchar(200),
   Customer_Job_Operator varchar(6) not null,
   Customer_Job_Operate_Time timestamp not null,
   primary key (Customer_Job_ID)
);

alter table Sales_Customer__Job comment '�ͻ���֯����_��λ';

alter table Sales_Customer__Job modify column Customer_Job_ID varchar(50) comment '��λ���';

alter table Sales_Customer__Job modify column Customer_Dept_ID varchar(50) comment '���ű��';

alter table Sales_Customer__Job modify column Customer_Job_name varchar(50) comment '��λ����';

alter table Sales_Customer__Job modify column Customer_Job_Description varchar(200) comment '��λ����';

alter table Sales_Customer__Job modify column Customer_Job_Remarks varchar(200) comment '��ע';

alter table Sales_Customer__Job modify column Customer_Job_Operator varchar(6) comment '������';

alter table Sales_Customer__Job modify column Customer_Job_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Module_Category                                 */
/*==============================================================*/
create table Sales_Module_Category
(
   Module_ID            varchar(50) not null,
   Product_ID           varchar(50) not null,
   Module_Name          varchar(50) not null,
   Module_Description   varchar(1000) not null,
   Module_Attachment    longblob,
   Module_Remark        varchar(200),
   Module_Recorder      varchar(6) not null,
   Module_Record_Time   timestamp not null,
   primary key (Module_ID)
);

alter table Sales_Module_Category comment 'ģ�������Ϣ��';

alter table Sales_Module_Category modify column Module_ID varchar(50) comment 'ģ����';

alter table Sales_Module_Category modify column Product_ID varchar(50) comment '��Ʒ���';

alter table Sales_Module_Category modify column Module_Name varchar(50) comment 'ģ������';

alter table Sales_Module_Category modify column Module_Description varchar(1000) comment 'ģ������';

alter table Sales_Module_Category modify column Module_Attachment longblob comment '����';

alter table Sales_Module_Category modify column Module_Remark varchar(200) comment '��ע';

alter table Sales_Module_Category modify column Module_Recorder varchar(6) comment '������';

alter table Sales_Module_Category modify column Module_Record_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Online_FeedBack                                 */
/*==============================================================*/
create table Sales_Online_FeedBack
(
   FeedBack_ID          varchar(50) not null,
   FeedBack_Category    int(1) not null,
   FeedBack_Name        varchar(50) not null,
   FeedBack_Phone_Number varchar(20),
   FeedBack_Mailbox     varchar(50),
   FeedBack_Company_Name varchar(50),
   FeedBack_Product     varchar(50),
   FeedBack_Description varchar(1000) not null,
   FeedBack_Time        datetime not null,
   FeedBack_Attachment  longblob,
   FeedBack_Excute_Status varchar(25),
   FeedBack_Excute_Description varchar(1000),
   FeedBack_Excute_Time timestamp,
   primary key (FeedBack_ID)
);

alter table Sales_Online_FeedBack comment '�ͻ����߷�����Ϣ��';

alter table Sales_Online_FeedBack modify column FeedBack_ID varchar(50) comment '�ͻ��������ݱ��';

alter table Sales_Online_FeedBack modify column FeedBack_Category int(1) comment '�������ݷ���';

alter table Sales_Online_FeedBack modify column FeedBack_Name varchar(50) comment '����';

alter table Sales_Online_FeedBack modify column FeedBack_Phone_Number varchar(20) comment '�ֻ�';

alter table Sales_Online_FeedBack modify column FeedBack_Mailbox varchar(50) comment '����';

alter table Sales_Online_FeedBack modify column FeedBack_Company_Name varchar(50) comment '��˾����';

alter table Sales_Online_FeedBack modify column FeedBack_Product varchar(50) comment 'ʹ�ò�Ʒ';

alter table Sales_Online_FeedBack modify column FeedBack_Description varchar(1000) comment '��������';

alter table Sales_Online_FeedBack modify column FeedBack_Time datetime comment '���߷���ʱ��';

alter table Sales_Online_FeedBack modify column FeedBack_Attachment longblob comment '����';

alter table Sales_Online_FeedBack modify column FeedBack_Excute_Status varchar(25) comment '��������״̬';

alter table Sales_Online_FeedBack modify column FeedBack_Excute_Description varchar(1000) comment '��������״̬����';

alter table Sales_Online_FeedBack modify column FeedBack_Excute_Time timestamp comment '��������ʱ��';

/*==============================================================*/
/* Table: Sales_Product_Category                                */
/*==============================================================*/
create table Sales_Product_Category
(
   Product_ID           varchar(50) not null,
   Product_Name         varchar(50) not null,
   Product_Description  varchar(1000) not null,
   Product_Attachment   longblob,
   Product_Remars       varchar(200),
   Product_Recorder     varchar(6) not null,
   Product_Record_Time  timestamp not null,
   primary key (Product_ID)
);

alter table Sales_Product_Category comment '��Ʒ������Ϣ��';

alter table Sales_Product_Category modify column Product_ID varchar(50) comment '��Ʒ���';

alter table Sales_Product_Category modify column Product_Name varchar(50) comment '��Ʒ����';

alter table Sales_Product_Category modify column Product_Description varchar(1000) comment '��Ʒ����';

alter table Sales_Product_Category modify column Product_Attachment longblob comment '����';

alter table Sales_Product_Category modify column Product_Remars varchar(200) comment '��ע';

alter table Sales_Product_Category modify column Product_Recorder varchar(6) comment '������';

alter table Sales_Product_Category modify column Product_Record_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Project                                         */
/*==============================================================*/
create table Sales_Project
(
   Project_ID           varchar(50) not null,
   Customer_ID          varchar(50),
   Contract_ID          varchar(50) not null,
   Bussiness_ID         varchar(50) not null,
   Project_Name         varchar(50) not null,
   Project_Sales        varchar(6) not null,
   Project_Begin_Date   datetime not null,
   Project_End_Date     datetime not null,
   Project_Manager      varchar(50) not null,
   Project_Development_Begin_Date datetime not null,
   Project_Development_End_Date datetime not null,
   Project_Gategory     varchar(20) not null,
   Project_Phase        varchar(20) not null,
   Project_Description  varchar(1000) not null,
   Project_Old_ID       varchar(50),
   Project_Remarks      varchar(500),
   Project_Operator     varchar(50) not null,
   Project_Operate_Time timestamp not null,
   primary key (Project_ID)
);

alter table Sales_Project comment '��Ŀ��Ϣ��';

alter table Sales_Project modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Sales_Project modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Project modify column Contract_ID varchar(50) comment '��ͬ���';

alter table Sales_Project modify column Bussiness_ID varchar(50) comment 'ҵ����';

alter table Sales_Project modify column Project_Name varchar(50) comment '��Ŀ����';

alter table Sales_Project modify column Project_Sales varchar(6) comment '���۸�����';

alter table Sales_Project modify column Project_Begin_Date datetime comment '��Ŀ��ʼʱ��';

alter table Sales_Project modify column Project_End_Date datetime comment '��Ŀ����ʱ��';

alter table Sales_Project modify column Project_Manager varchar(50) comment '�з�����';

alter table Sales_Project modify column Project_Development_Begin_Date datetime comment '�з���ʼʱ��';

alter table Sales_Project modify column Project_Development_End_Date datetime comment '�з�����ʱ��';

alter table Sales_Project modify column Project_Gategory varchar(20) comment '��Ŀ����';

alter table Sales_Project modify column Project_Phase varchar(20) comment '��Ŀ�׶�';

alter table Sales_Project modify column Project_Description varchar(1000) comment '��Ŀ����';

alter table Sales_Project modify column Project_Old_ID varchar(50) comment '����Ŀ���';

alter table Sales_Project modify column Project_Remarks varchar(500) comment '��ע';

alter table Sales_Project modify column Project_Operator varchar(50) comment '������';

alter table Sales_Project modify column Project_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Project_Consultation                            */
/*==============================================================*/
create table Sales_Project_Consultation
(
   Consultation_ID      varchar(50) not null,
   Consultation_Name    varchar(6) not null,
   Consultation_Phone_Number varchar(20),
   Consultation_Mailbox varchar(50),
   Consultation_Company_Name varchar(50) not null,
   Consultation_Job     varchar(20),
   Consultation_Provence varchar(20) not null,
   Consultation_City    varchar(20) not null,
   Consultation_County  varchar(20) not null,
   Consultation_Industry varchar(50) not null,
   Consultation_Product varchar(50) not null,
   Consultation_Project_Introduct varchar(1000) not null,
   Consultation_Project_Scale varchar(50) not null,
   Consultation_Attachment longblob,
   Consultation����Rmarks varchar(200),
   Consultation_Time    datetime not null,
   Consultation_Excute_Status varchar(25),
   Consultation_Excute_Descriptio varchar(1000),
   Consultation_Excute_Time timestamp,
   primary key (Consultation_ID)
);

alter table Sales_Project_Consultation comment '�ͻ���Ŀ��ѯ��Ϣ��';

alter table Sales_Project_Consultation modify column Consultation_ID varchar(50) comment '��Ŀ��ѯ���';

alter table Sales_Project_Consultation modify column Consultation_Name varchar(6) comment '����';

alter table Sales_Project_Consultation modify column Consultation_Phone_Number varchar(20) comment '�ֻ�';

alter table Sales_Project_Consultation modify column Consultation_Mailbox varchar(50) comment '����';

alter table Sales_Project_Consultation modify column Consultation_Company_Name varchar(50) comment '��˾����';

alter table Sales_Project_Consultation modify column Consultation_Job varchar(20) comment 'ְλ';

alter table Sales_Project_Consultation modify column Consultation_Provence varchar(20) comment 'ʡ��';

alter table Sales_Project_Consultation modify column Consultation_City varchar(20) comment '����';

alter table Sales_Project_Consultation modify column Consultation_County varchar(20) comment '����';

alter table Sales_Project_Consultation modify column Consultation_Industry varchar(50) comment '������ҵ';

alter table Sales_Project_Consultation modify column Consultation_Product varchar(50) comment '����Ȥ��Ʒ';

alter table Sales_Project_Consultation modify column Consultation_Project_Introduct varchar(1000) comment '��Ŀ����';

alter table Sales_Project_Consultation modify column Consultation_Project_Scale varchar(50) comment '��Ŀ��ģ';

alter table Sales_Project_Consultation modify column Consultation_Attachment longblob comment '����';

alter table Sales_Project_Consultation modify column Consultation����Rmarks varchar(200) comment '��ע';

alter table Sales_Project_Consultation modify column Consultation_Time datetime comment '��ѯʱ��';

alter table Sales_Project_Consultation modify column Consultation_Excute_Status varchar(25) comment '��ѯ����״̬';

alter table Sales_Project_Consultation modify column Consultation_Excute_Descriptio varchar(1000) comment '��ѯ����״̬����';

alter table Sales_Project_Consultation modify column Consultation_Excute_Time timestamp comment '��ѯ����ʱ��';

/*==============================================================*/
/* Table: Sales_Record                                          */
/*==============================================================*/
create table Sales_Record
(
   Record_ID            varchar(50) not null,
   Customer_ID          varchar(50),
   Business_ID          varchar(50),
   Project_ID           varchar(50),
   Record_Name          varchar(50) not null,
   Record_Phase         varchar(20) not null,
   Record_Sale_Opportunity varchar(200) not null,
   Record_Expense_Category varchar(20),
   Record_Expense_Actual numeric(10,2),
   Record_Executor      varchar(6) not null,
   Record_Begin_Date    datetime not null,
   Record_End_Date      datetime not null,
   Record_Execute_Status varchar(10) not null,
   Record_Attachment    longblob,
   Record_Remarks       varchar(200) not null,
   Record_Operator      varchar(6) not null,
   Record_Operate_Time  timestamp,
   primary key (Record_ID)
);

alter table Sales_Record comment '�ж���¼��Ϣ��';

alter table Sales_Record modify column Record_ID varchar(50) comment '�ж���¼���';

alter table Sales_Record modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Record modify column Business_ID varchar(50) comment 'ҵ����';

alter table Sales_Record modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Sales_Record modify column Record_Name varchar(50) comment '�ж�����';

alter table Sales_Record modify column Record_Phase varchar(20) comment '�׶�';

alter table Sales_Record modify column Record_Sale_Opportunity varchar(200) comment '���ۻ���';

alter table Sales_Record modify column Record_Expense_Category varchar(20) comment '��������';

alter table Sales_Record modify column Record_Expense_Actual numeric(10,2) comment 'ʵ�ʷ���';

alter table Sales_Record modify column Record_Executor varchar(6) comment 'ִ����';

alter table Sales_Record modify column Record_Begin_Date datetime comment 'ʵ��ִ�п�ʼʱ��';

alter table Sales_Record modify column Record_End_Date datetime comment 'ʵ��ִ�н���ʱ��';

alter table Sales_Record modify column Record_Execute_Status varchar(10) comment 'ִ��״̬';

alter table Sales_Record modify column Record_Attachment longblob comment '����';

alter table Sales_Record modify column Record_Remarks varchar(200) comment '��ע';

alter table Sales_Record modify column Record_Operator varchar(6) comment '�ж�������';

alter table Sales_Record modify column Record_Operate_Time timestamp comment '�ж�����ʱ��';

/*==============================================================*/
/* Table: Sales_Record_Complaint                                */
/*==============================================================*/
create table Sales_Record_Complaint
(
   Complaint_ID         varchar(50) not null,
   Project_ID           varchar(50) not null,
   Customer_ID          varchar(50),
   Complaint_Product    varchar(50) not null,
   Complaint_Name       varchar(6) not null,
   Complaint_Date       datetime not null,
   Complaint_Company    varchar(50),
   Complaint_Feedback_Type varchar(10) not null,
   Complaint_Problem    varchar(500) not null,
   Complaint_Attachment longblob,
   Complaint_Attachment_Customer varchar(200),
   Complaint_Problem_Remarks varchar(200),
   Complaint_After_Sale_Type varchar(10),
   Complaint_Problem_Description varchar(500),
   Complaint_Operator   varchar(6),
   Complaint_Executor   varchar(500),
   Complaint_Result     varchar(10),
   Complaint_After_Sale_Remarks varchar(200),
   Complaint_Phone_Number varchar(20),
   Complaint_Mailbox    varchar(50),
   Complaint_Recorder   varchar(6) not null,
   Complaint_Record_Time timestamp not null,
   primary key (Complaint_ID)
);

alter table Sales_Record_Complaint comment '�ͻ�Ͷ����Ϣ��';

alter table Sales_Record_Complaint modify column Complaint_ID varchar(50) comment '�ͻ�Ͷ�߼�¼���';

alter table Sales_Record_Complaint modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Sales_Record_Complaint modify column Customer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Record_Complaint modify column Complaint_Product varchar(50) comment 'ʹ�ò�Ʒ';

alter table Sales_Record_Complaint modify column Complaint_Name varchar(6) comment 'Ͷ��������';

alter table Sales_Record_Complaint modify column Complaint_Date datetime comment 'Ͷ��ʱ��';

alter table Sales_Record_Complaint modify column Complaint_Company varchar(50) comment '���ڵ�λ';

alter table Sales_Record_Complaint modify column Complaint_Feedback_Type varchar(10) comment 'Ͷ�߷�ʽ';

alter table Sales_Record_Complaint modify column Complaint_Problem varchar(500) comment 'Ͷ������';

alter table Sales_Record_Complaint modify column Complaint_Attachment longblob comment '����';

alter table Sales_Record_Complaint modify column Complaint_Attachment_Customer varchar(200) comment '�ͷ���������';

alter table Sales_Record_Complaint modify column Complaint_Problem_Remarks varchar(200) comment '����������ע';

alter table Sales_Record_Complaint modify column Complaint_After_Sale_Type varchar(10) comment '�ۺ��������';

alter table Sales_Record_Complaint modify column Complaint_Problem_Description varchar(500) comment '��������';

alter table Sales_Record_Complaint modify column Complaint_Operator varchar(6) comment '������';

alter table Sales_Record_Complaint modify column Complaint_Executor varchar(500) comment '�������';

alter table Sales_Record_Complaint modify column Complaint_Result varchar(10) comment '������';

alter table Sales_Record_Complaint modify column Complaint_After_Sale_Remarks varchar(200) comment '�ۺ�ע';

alter table Sales_Record_Complaint modify column Complaint_Phone_Number varchar(20) comment '�ͻ��绰';

alter table Sales_Record_Complaint modify column Complaint_Mailbox varchar(50) comment '�ͻ�����';

alter table Sales_Record_Complaint modify column Complaint_Recorder varchar(6) comment 'Ͷ�߼�¼��';

alter table Sales_Record_Complaint modify column Complaint_Record_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Record_Service                                  */
/*==============================================================*/
create table Sales_Record_Service
(
   Service_ID           varchar(50) not null,
   Project_ID           varchar(50),
   Custormer_ID         varchar(50),
   Service_Product      varchar(50) not null,
   Service_Name         varchar(6) not null,
   Service_Problem_Time datetime not null,
   Service_Dept         varchar(50) not null,
   Service_Feedback_Type varchar(10) not null,
   Service_Feedback_Info varchar(500),
   Service_Attachment   longblob,
   Service_Attachment_To_Customer varchar(200),
   Service_Problem_Remarks varchar(200),
   Service_Type         varchar(10),
   Service_Problem_Deascription varchar(500),
   Service_Operator     varchar(6),
   Service_Procedure    varchar(500),
   Service_Result       varchar(10),
   Service_After_Sale_Remarks varchar(200),
   Service_Phone_Number varchar(20),
   Service_Mailbox      varchar(50),
   Service_Recorder     varchar(6) not null,
   Service_Record_Time  timestamp not null,
   primary key (Service_ID)
);

alter table Sales_Record_Service comment '�ͷ���¼��Ϣ��';

alter table Sales_Record_Service modify column Service_ID varchar(50) comment '�ͷ���¼���';

alter table Sales_Record_Service modify column Project_ID varchar(50) comment '��Ŀ���';

alter table Sales_Record_Service modify column Custormer_ID varchar(50) comment '��ҵ�ͻ����';

alter table Sales_Record_Service modify column Service_Product varchar(50) comment 'ʹ�ò�Ʒ';

alter table Sales_Record_Service modify column Service_Name varchar(6) comment '����������';

alter table Sales_Record_Service modify column Service_Problem_Time datetime comment '�������ʱ��';

alter table Sales_Record_Service modify column Service_Dept varchar(50) comment '���ڵ�λ';

alter table Sales_Record_Service modify column Service_Feedback_Type varchar(10) comment '������ʽ';

alter table Sales_Record_Service modify column Service_Feedback_Info varchar(500) comment '��������';

alter table Sales_Record_Service modify column Service_Attachment longblob comment '����';

alter table Sales_Record_Service modify column Service_Attachment_To_Customer varchar(200) comment '�ͷ���������';

alter table Sales_Record_Service modify column Service_Problem_Remarks varchar(200) comment '����������ע';

alter table Sales_Record_Service modify column Service_Type varchar(10) comment '�ۺ��������';

alter table Sales_Record_Service modify column Service_Problem_Deascription varchar(500) comment '��������';

alter table Sales_Record_Service modify column Service_Operator varchar(6) comment '������';

alter table Sales_Record_Service modify column Service_Procedure varchar(500) comment '�������';

alter table Sales_Record_Service modify column Service_Result varchar(10) comment '������';

alter table Sales_Record_Service modify column Service_After_Sale_Remarks varchar(200) comment '�ۺ�ע';

alter table Sales_Record_Service modify column Service_Phone_Number varchar(20) comment '�ͻ��绰';

alter table Sales_Record_Service modify column Service_Mailbox varchar(50) comment '�ͻ�����';

alter table Sales_Record_Service modify column Service_Recorder varchar(6) comment '�ͷ���¼��';

alter table Sales_Record_Service modify column Service_Record_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Sales_Requirement_Category                            */
/*==============================================================*/
create table Sales_Requirement_Category
(
   Requirement_ID       varchar(50) not null,
   Complaint_ID         varchar(50),
   Service_ID           varchar(50),
   Product_ID           varchar(50),
   Module_ID            varchar(50),
   Feedback_ID          varchar(50),
   Consultation_ID      varchar(50),
   Requirement_Description varchar(1000),
   Requirement_Remarks  varchar(6),
   Requirement_Recorder varchar(50) not null,
   Requirement_Record_Time timestamp not null,
   primary key (Requirement_ID)
);

alter table Sales_Requirement_Category comment '���������Ϣ��';

alter table Sales_Requirement_Category modify column Requirement_ID varchar(50) comment '������';

alter table Sales_Requirement_Category modify column Complaint_ID varchar(50) comment '�ͻ�Ͷ�߼�¼���';

alter table Sales_Requirement_Category modify column Service_ID varchar(50) comment '�ͷ���¼���';

alter table Sales_Requirement_Category modify column Product_ID varchar(50) comment '��Ʒ���';

alter table Sales_Requirement_Category modify column Module_ID varchar(50) comment 'ģ����';

alter table Sales_Requirement_Category modify column Feedback_ID varchar(50) comment '�ͻ��������ݱ��';

alter table Sales_Requirement_Category modify column Consultation_ID varchar(50) comment '��Ŀ��ѯ���';

alter table Sales_Requirement_Category modify column Requirement_Description varchar(1000) comment '����������';

alter table Sales_Requirement_Category modify column Requirement_Remarks varchar(6) comment '��ע';

alter table Sales_Requirement_Category modify column Requirement_Recorder varchar(50) comment '�����¼��';

alter table Sales_Requirement_Category modify column Requirement_Record_Time timestamp comment '��¼ʱ��';

/*==============================================================*/
/* Table: TimeSheet                                             */
/*==============================================================*/
create table TimeSheet
(
   TimeSheet_ID         varchar(50) not null,
   Project_ID           varchar(50) not null,
   Employee_ID          varchar(16),
   TimeSheet_Name       varchar(6) not null,
   TimeSheet_Project_Cagegory varchar(10) not null,
   TimeSheet_Date       datetime not null,
   TimeSheet_PM         varchar(6) not null,
   TimeSheet_Normal     numeric(3,1) not null,
   TimeSheet_Overtime   numeric(3,1),
   TimeSheet_Approved   numeric(3,1),
   TimeSheet_Planned    int(1) not null,
   TimeSheet_Plan       varchar(1000),
   TimeSheet_Content    varchar(1000) not null,
   TimeSheet_Problem    varchar(1000),
   TimeSheet_Assignment_ID varchar(50),
   TimeSheet_Approval_Status int(1),
   TimeSheet_Operate_Time timestamp not null,
   primary key (TimeSheet_ID)
);

alter table TimeSheet comment '��ʱ��Ϣ��';

alter table TimeSheet modify column TimeSheet_ID varchar(50) comment '��ʱ��Ϣ���';

alter table TimeSheet modify column Project_ID varchar(50) comment '��Ŀ���';

alter table TimeSheet modify column Employee_ID varchar(16) comment 'Ա������';

alter table TimeSheet modify column TimeSheet_Name varchar(6) comment 'Ա������';

alter table TimeSheet modify column TimeSheet_Project_Cagegory varchar(10) comment '��Ŀ���';

alter table TimeSheet modify column TimeSheet_Date datetime comment '����';

alter table TimeSheet modify column TimeSheet_PM varchar(6) comment '��Ŀ����';

alter table TimeSheet modify column TimeSheet_Normal numeric(3,1) comment '������ʱ';

alter table TimeSheet modify column TimeSheet_Overtime numeric(3,1) comment '�Ӱ๤ʱ';

alter table TimeSheet modify column TimeSheet_Approved numeric(3,1) comment '���ͨ���Ӱ๤ʱ';

alter table TimeSheet modify column TimeSheet_Planned int(1) comment '�Ƿ�ƻ�';

alter table TimeSheet modify column TimeSheet_Plan varchar(1000) comment '�ƻ�����';

alter table TimeSheet modify column TimeSheet_Content varchar(1000) comment '��������';

alter table TimeSheet modify column TimeSheet_Problem varchar(1000) comment '��������';

alter table TimeSheet modify column TimeSheet_Assignment_ID varchar(50) comment '��������ί�б��';

alter table TimeSheet modify column TimeSheet_Approval_Status int(1) comment '����״̬';

alter table TimeSheet modify column TimeSheet_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: Travel_Schedule                                       */
/*==============================================================*/
create table Travel_Schedule
(
   Schedule_ID          varchar(50) not null,
   Travel_Expenses_ID   varchar(50) not null,
   Schedule_Place_Issue varchar(20) not null,
   Schedule_Issue_Time  datetime not null,
   Schedule_Place_Ended varchar(20) not null,
   Schedule_Ended_Time  datetime not null,
   Schedule_Cost_Type   varchar(10) not null,
   Schedule_Cost_Price  numeric(10,2) not null,
   Schedule_Cost_Local_Price numeric(10,2),
   Schedule_Operator    varchar(506) not null,
   Schedule_Operate_Time timestamp not null,
   primary key (Schedule_ID)
);

alter table Travel_Schedule comment '������г̱�';

alter table Travel_Schedule modify column Schedule_ID varchar(50) comment '���ñ����г�ID';

alter table Travel_Schedule modify column Travel_Expenses_ID varchar(50) comment '����������';

alter table Travel_Schedule modify column Schedule_Place_Issue varchar(20) comment '������';

alter table Travel_Schedule modify column Schedule_Issue_Time datetime comment '����ʱ��';

alter table Travel_Schedule modify column Schedule_Place_Ended varchar(20) comment '�����';

alter table Travel_Schedule modify column Schedule_Ended_Time datetime comment '����ʱ��';

alter table Travel_Schedule modify column Schedule_Cost_Type varchar(10) comment '��ͨ��������';

alter table Travel_Schedule modify column Schedule_Cost_Price numeric(10,2) comment '���ý��';

alter table Travel_Schedule modify column Schedule_Cost_Local_Price numeric(10,2) comment '���ڽ�ͨ��';

alter table Travel_Schedule modify column Schedule_Operator varchar(506) comment '������';

alter table Travel_Schedule modify column Schedule_Operate_Time timestamp comment '����ʱ��';

/*==============================================================*/
/* Table: User_Role                                             */
/*==============================================================*/
create table User_Role
(
   User_Role_ID         varchar(10) not null,
   User_Role_Name       varchar(25) not null,
   User_Role_Status     int(1) not null,
   User_Role_Operator   varchar(6) not null,
   User_Role_Operate_Time timestamp not null,
   primary key (User_Role_ID)
);

alter table User_Role comment '�û���ɫ��';

alter table User_Role modify column User_Role_ID varchar(10) comment '�û���ɫID';

alter table User_Role modify column User_Role_Name varchar(25) comment '��ɫ����';

alter table User_Role modify column User_Role_Status int(1) comment '��ɫ״̬';

alter table User_Role modify column User_Role_Operator varchar(6) comment '������';

alter table User_Role modify column User_Role_Operate_Time timestamp comment '����ʱ��';

alter table Accounts_Payable add constraint FK_Reference_30 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Accounts_Receivable add constraint FK_Reference_29 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Budget_Expenses add constraint FK_Reference_40 foreign key (Budget_ID)
      references Project_Budget (Budget_ID) on delete restrict on update restrict;

alter table Budget_Labor add constraint FK_Reference_39 foreign key (Budget_ID)
      references Project_Budget (Budget_ID) on delete restrict on update restrict;

alter table Budget_Purchase add constraint FK_Reference_41 foreign key (Budget_ID)
      references Project_Budget (Budget_ID) on delete restrict on update restrict;

alter table Contract add constraint FK_Reference_32 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Contract add constraint FK_Reference_34 foreign key (Business_ID)
      references Sales_Business (Business_ID) on delete restrict on update restrict;

alter table Contract_Additional_Records add constraint FK_Reference_36 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Contract_Delivery add constraint FK_Reference_37 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Delivery_Plan add constraint FK_Reference_31 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Inner_Org_Employee add constraint FK_Reference_2 foreign key (Dept_ID)
      references Inner_Org_Dept (Dept_ID) on delete restrict on update restrict;

alter table Inner_Org_Employee add constraint FK_Reference_3 foreign key (Job_ID)
      references Inner_Org_Job (Job_ID) on delete restrict on update restrict;

alter table Inner_Org_Employee add constraint FK_Reference_4 foreign key (Inner_User_ID)
      references Inner_User (Inner_User_ID) on delete restrict on update restrict;

alter table Inner_Org_Employee add constraint FK_Reference_5 foreign key (User_Role_ID)
      references User_Role (User_Role_ID) on delete restrict on update restrict;

alter table Inner_Org_Employee add constraint FK_Reference_7 foreign key (Module_ID)
      references Function_Module (Module_ID) on delete restrict on update restrict;

alter table Inner_Org_Job add constraint FK_Reference_6 foreign key (Dept_ID)
      references Inner_Org_Dept (Dept_ID) on delete restrict on update restrict;

alter table Payment_Paid add constraint FK_Reference_44 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Payment_Paid add constraint FK_Reference_45 foreign key (Purchase_ID)
      references Purchase (Purchase_ID) on delete restrict on update restrict;

alter table Payment_Received add constraint FK_Reference_42 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Payment_Received add constraint FK_Reference_43 foreign key (Business_ID)
      references Sales_Business (Business_ID) on delete restrict on update restrict;

alter table Purchase add constraint FK_Reference_46 foreign key (Project_ID)
      references Sales_Project (Project_ID) on delete restrict on update restrict;

alter table Sales_Bug_Category add constraint FK_Reference_18 foreign key (Complaint_ID)
      references Sales_Record_Complaint (Complaint_ID) on delete restrict on update restrict;

alter table Sales_Bug_Category add constraint FK_Reference_19 foreign key (Service_ID)
      references Sales_Record_Service (Service_ID) on delete restrict on update restrict;

alter table Sales_Bug_Category add constraint FK_Reference_22 foreign key (Product_ID)
      references Sales_Product_Category (Product_ID) on delete restrict on update restrict;

alter table Sales_Bug_Category add constraint FK_Reference_25 foreign key (Module_ID)
      references Sales_Module_Category (Module_ID) on delete restrict on update restrict;

alter table Sales_Bug_Category add constraint FK_Reference_26 foreign key (Feedback_ID)
      references Sales_Online_FeedBack (FeedBack_ID) on delete restrict on update restrict;

alter table Sales_Business add constraint FK_Reference_12 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Competitor add constraint FK_Reference_35 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Customer_Child_Company add constraint FK_Reference_8 foreign key (Customer__ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Customer_Contact add constraint FK_Reference_11 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Customer_Dept add constraint FK_Reference_9 foreign key (Child_Company_ID)
      references Sales_Customer_Child_Company (Child_Company_ID) on delete restrict on update restrict;

alter table Sales_Customer__Job add constraint FK_Reference_10 foreign key (Customer_Dept_ID)
      references Sales_Customer_Dept (Customer_Dept_ID) on delete restrict on update restrict;

alter table Sales_Project add constraint FK_Reference_13 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Project add constraint FK_Reference_14 foreign key (Bussiness_ID)
      references Sales_Business (Business_ID) on delete restrict on update restrict;

alter table Sales_Project add constraint FK_Reference_33 foreign key (Contract_ID)
      references Contract (Contract_ID) on delete restrict on update restrict;

alter table Sales_Record add constraint FK_Reference_15 foreign key (Customer_ID)
      references Sales_Company_Customer (Customer_ID) on delete restrict on update restrict;

alter table Sales_Record_Complaint add constraint FK_Reference_17 foreign key (Project_ID)
      references Sales_Project (Project_ID) on delete restrict on update restrict;

alter table Sales_Record_Service add constraint FK_Reference_16 foreign key (Project_ID)
      references Sales_Project (Project_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_20 foreign key (Service_ID)
      references Sales_Record_Service (Service_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_21 foreign key (Complaint_ID)
      references Sales_Record_Complaint (Complaint_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_23 foreign key (Product_ID)
      references Sales_Product_Category (Product_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_24 foreign key (Module_ID)
      references Sales_Module_Category (Module_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_27 foreign key (Feedback_ID)
      references Sales_Online_FeedBack (FeedBack_ID) on delete restrict on update restrict;

alter table Sales_Requirement_Category add constraint FK_Reference_28 foreign key (Consultation_ID)
      references Sales_Project_Consultation (Consultation_ID) on delete restrict on update restrict;

alter table TimeSheet add constraint FK_Reference_47 foreign key (Project_ID)
      references Sales_Project (Project_ID) on delete restrict on update restrict;

alter table TimeSheet add constraint FK_Reference_48 foreign key (Employee_ID)
      references Inner_Org_Employee (Employee_ID) on delete restrict on update restrict;

alter table Travel_Schedule add constraint FK_Reference_38 foreign key (Travel_Expenses_ID)
      references Approval_Expenses__Travel (Expenses_Travel_ID) on delete restrict on update restrict;

-- ---------------------------------------------------------bootdo start------------------------------------------------------

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bootdo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-24 14:33:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog_content`
-- ----------------------------
DROP TABLE IF EXISTS `blog_content`;
CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '����',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '������id',
  `modified` bigint(20) DEFAULT NULL COMMENT '����޸���id',
  `content` text COMMENT '����',
  `type` varchar(16) DEFAULT NULL COMMENT '����',
  `tags` varchar(200) DEFAULT NULL COMMENT '��ǩ',
  `categories` varchar(200) DEFAULT NULL COMMENT '����',
  `hits` int(5) DEFAULT NULL,
  `comments_num` int(5) DEFAULT '0' COMMENT '��������',
  `allow_comment` int(1) DEFAULT '0' COMMENT '��������',
  `allow_ping` int(1) DEFAULT '0' COMMENT '����ping',
  `allow_feed` int(1) DEFAULT '0' COMMENT '������',
  `status` int(1) DEFAULT NULL COMMENT '״̬',
  `author` varchar(100) DEFAULT NULL COMMENT '����',
  `gtm_create` datetime DEFAULT NULL COMMENT '����ʱ��',
  `gtm_modified` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='��������';

-- ----------------------------
-- Records of blog_content
-- ----------------------------
INSERT INTO `blog_content` VALUES ('75', '���� Springboot �� Mybatis �ĺ�̨����ϵͳ BootDo', null, null, null, '<h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">��Ŀ����</h3><ul style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 0px 30px; list-style-position: initial; list-style-image: initial; color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><li><p>����ѧϰ�͵Ŀ�Դ��ܣ�����Ч�����ٹ��ɷ�װ��չ�ּ�������</p></li><li><p>Springboot��Ϊ������ܣ�ʹ��mybatis��Ϊ�־ò���</p></li><li><p>ʹ�ùٷ��Ƽ���thymeleaf��Ϊģ�����棬shiro��Ϊ��ȫ���,������������һ���򾡡�</p></li><li><p>����ע���sqlд������XML���������ã�һ��ǰ��̨��������</p></li></ul><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">��ʾ��ַ</span>&nbsp;<a href=\"http://47.93.239.129/\" style=\"outline: 0px; color: rgb(68, 102, 187);\">http://47.93.239.129</a></p><h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">���ܼ��</h3><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\">1. �û�����<br>2. ��ɫ����<br>3. ���Ź���<br>4. �˵�����<br>5. ϵͳ��־<br>6. ��������<br>7. ���ݹ���</p><h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">���ÿ��</h3><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">ǰ��</span><br>1. Bootstrap<br>2. jQuery<br>3. bootstrap-table<br>4. layer<br>5. jsTree&nbsp;<br>6. summernote<br>7. jquery-validate<br>8. jquery-treegrid</p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">���</span><br>1. SpringBoot&nbsp;<br>2. MyBatis<br>3. Thymeleaf<br>4. Shiro<br>5. druid</p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">��Ŀ��ͼ</span></p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><img height=\"400\" src=\"https://static.oschina.net/uploads/space/2017/0912/182421_5LaN_3244087.png\" width=\"650\" style=\"border-width: initial; border-style: none; outline: 0px; width: 882px; max-width: -webkit-fit-content; height: auto;\"></p>', 'article', null, null, null, null, '0', '0', '1', '1', 'bootdo', '2017-09-22 14:44:44', '2017-09-22 14:44:44');
INSERT INTO `blog_content` VALUES ('100', 'springboot thymeleaf��shiro ���ϡ�����ť�ɼ���', null, null, null, '<p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">�������</p><pre class=\"hljs xml\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; padding: 2px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 3px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">&lt;<span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">dependency</span>&gt;</span><code class=\"hljs xml\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; display: block; overflow-x: auto; padding: 10px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 4px; font-size: 13px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\"> \r\n   <span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">groupId</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span>com.github.theborakompanioni<span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;/</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">groupId</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span>\r\n    <span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">artifactId</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span>thymeleaf-extras-shiro<span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;/</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">artifactId</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span>\r\n    <span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">version</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span></code>1.2.1<code class=\"hljs xml\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; display: block; overflow-x: auto; padding: 10px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 4px; font-size: 13px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;/</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">version</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span> \r\n<span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&lt;/</span><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\"><span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">dependency</span></span></span><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">&gt;</span></span></code></pre><p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">&nbsp;</p><p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">��shiro��configuration������</p><pre class=\"hljs java\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; padding: 2px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 3px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\"><span class=\"hljs-meta\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(127, 159, 127);\">@Bean</span>\r\n    <span class=\"hljs-function\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\"><span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">public</span> ShiroDialect <span class=\"hljs-title\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">shiroDialect</span><span class=\"hljs-params\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">()</span> </span>{\r\n        <span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">return</span> <span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">new</span> ShiroDialect();\r\n    }</pre><p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">&nbsp;</p><p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">��html�м���xmlns</p><pre class=\"hljs xml\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; padding: 2px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 3px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\"><span class=\"hljs-tag\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">&lt;<span class=\"hljs-name\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(239, 239, 143);\">html</span> <span class=\"hljs-attr\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">lang</span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"zh_CN\"</span> <span class=\"hljs-attr\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">xmlns:th</span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"http://www.thymeleaf.org\"</span>\r\n      <span class=\"hljs-attr\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\">xmlns:shiro</span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"http://www.pollix.at/thymeleaf/shiro\"</span>&gt;</span></pre><p style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; margin-bottom: 16px; color: rgb(61, 70, 77); font-family: &quot;Pingfang SC&quot;, STHeiti, &quot;Lantinghei SC&quot;, &quot;Open Sans&quot;, Arial, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, SimSun, sans-serif; font-size: 16px; background-color: rgb(248, 248, 248);\">����</p><pre class=\"hljs scala\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; padding: 2px; background: rgb(63, 63, 63); color: rgb(220, 220, 220); border-radius: 3px; line-height: 1.4; word-wrap: normal; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace;\">&lt;button shiro:hasPermission=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"sys:user:add\"</span> <span class=\"hljs-class\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\"><span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">type</span></span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"button\"</span> <span class=\"hljs-class\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\"><span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">class</span></span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"btn &nbsp;btn-primary\"</span> onclick=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"add()\"</span>&gt;\r\n&nbsp;&nbsp; &lt;i <span class=\"hljs-class\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent;\"><span class=\"hljs-keyword\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(227, 206, 171);\">class</span></span>=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"fa fa-plus\"</span> aria-hidden=<span class=\"hljs-string\" style=\"box-sizing: inherit; -webkit-tap-highlight-color: transparent; color: rgb(204, 147, 147);\">\"true\"</span>&gt;&lt;/i&gt;���\r\n&lt;/button&gt;</pre>', 'article', null, null, null, null, '1', null, '0', '1', 'bootdo', '2017-09-22 13:24:30', '2017-09-22 13:24:30');
INSERT INTO `blog_content` VALUES ('108', 'spring boot ehcache����', null, null, null, '<h3 id=\"pomxml����-����������\" style=\"font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; color: rgb(63, 63, 63); margin: 0.8em 0px; font-size: 1.7em; padding: 0px;\">pom.xml���� ����������</h3><pre class=\"prettyprint\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; font-size: 14px; white-space: nowrap; padding: 5px 5px 5px 60px; margin-bottom: 1.1em; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-color: rgba(128, 128, 128, 0.075); border-radius: 0px; position: relative; overflow-y: hidden;\"><code class=\"hljs xml has-numbering\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; display: block; word-wrap: normal;\"><span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">dependency</span>&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">groupId</span>&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">artifactId</span>&gt;</span>spring-boot-starter-cache<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">artifactId</span>&gt;</span>\r\n<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">dependency</span>&gt;</span>\r\n<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">dependency</span>&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">groupId</span>&gt;</span>net.sf.ehcache<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">groupId</span>&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">artifactId</span>&gt;</span>ehcache<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">artifactId</span>&gt;</span>\r\n<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">dependency</span>&gt;</span></code><ul class=\"pre-numbering\" style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; list-style: none; position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; border-right: 1px solid rgb(221, 221, 221); text-align: right;\"><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">1</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">2</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">3</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">4</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">5</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">6</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">7</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">8</li></ul></pre><h3 id=\"��д���������û������\" style=\"font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; color: rgb(63, 63, 63); margin: 0.8em 0px; font-size: 1.7em; padding: 0px;\">��д������,���û������</h3><pre class=\"prettyprint\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; font-size: 14px; white-space: nowrap; padding: 5px 5px 5px 60px; margin-bottom: 1.1em; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-color: rgba(128, 128, 128, 0.075); border-radius: 0px; position: relative; overflow-y: hidden;\"><code class=\"hljs java has-numbering\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; display: block; word-wrap: normal;\"><span class=\"hljs-annotation\" style=\"margin: 0px; padding: 0px; color: rgb(155, 133, 157);\">@Configuration</span>\r\n<span class=\"hljs-annotation\" style=\"margin: 0px; padding: 0px; color: rgb(155, 133, 157);\">@EnableCaching</span>\r\n<span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">public</span> <span class=\"hljs-class\" style=\"margin: 0px; padding: 0px;\"><span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">class</span> <span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">CacheConfiguration</span> {</span>\r\n\r\n    <span class=\"hljs-annotation\" style=\"margin: 0px; padding: 0px; color: rgb(155, 133, 157);\">@Bean</span>\r\n    <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">public</span> EhCacheCacheManager <span class=\"hljs-title\" style=\"margin: 0px; padding: 0px;\">ehCacheCacheManager</span>(EhCacheManagerFactoryBean bean) {\r\n        <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">return</span> <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">new</span> EhCacheCacheManager(bean.getObject());\r\n    }\r\n\r\n    <span class=\"hljs-annotation\" style=\"margin: 0px; padding: 0px; color: rgb(155, 133, 157);\">@Bean</span>\r\n    <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">public</span> EhCacheManagerFactoryBean <span class=\"hljs-title\" style=\"margin: 0px; padding: 0px;\">ehCacheManagerFactoryBean</span>() {\r\n        EhCacheManagerFactoryBean cacheManagerFactoryBean = <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">new</span> EhCacheManagerFactoryBean();\r\n        cacheManagerFactoryBean.setConfigLocation(<span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">new</span> ClassPathResource(<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"config/ehcache.xml\"</span>));\r\n        cacheManagerFactoryBean.setShared(<span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">true</span>);\r\n        <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">return</span> cacheManagerFactoryBean;\r\n    }\r\n}</code><ul class=\"pre-numbering\" style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; list-style: none; position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; border-right: 1px solid rgb(221, 221, 221); text-align: right;\"><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">1</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">2</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">3</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">4</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">5</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">6</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">7</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">8</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">9</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">10</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">11</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">12</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">13</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">14</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">15</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">16</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">17</li></ul></pre><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(63, 63, 63); font-family: &quot;microsoft yahei&quot;; font-size: 15px;\">ehcache.xml����:</p><pre class=\"prettyprint\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; font-size: 14px; white-space: nowrap; padding: 5px 5px 5px 60px; margin-bottom: 1.1em; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-color: rgba(128, 128, 128, 0.075); border-radius: 0px; position: relative; overflow-y: hidden;\"><code class=\"hljs xml has-numbering\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; display: block; word-wrap: normal;\"><span class=\"hljs-pi\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;</span>\r\n<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">ehcache</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">xmlns:xsi</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"http://www.w3.org/2001/XMLSchema-instance\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">xsi:noNamespaceSchemaLocation</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"http://ehcache.org/ehcache.xsd\"</span>\r\n    <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">updateCheck</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span>&gt;</span>\r\n    <span class=\"hljs-comment\" style=\"margin: 0px; padding: 0px; color: rgb(136, 0, 0);\">&lt;!-- diskStore��Ϊ����·����ehcache��Ϊ�ڴ�ʹ��������������Զ�����̵Ļ���λ�á�\r\n    �����������£� user.home �C �û���Ŀ¼ \r\n    user.dir �C �û���ǰ����Ŀ¼ \r\n    java.io.tmpdir �C Ĭ����ʱ�ļ�·�� --&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">diskStore</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">path</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"java.io.tmpdir/Tmp_EhCache\"</span> /&gt;</span>\r\n    <span class=\"hljs-comment\" style=\"margin: 0px; padding: 0px; color: rgb(136, 0, 0);\">&lt;!-- defaultCache��Ĭ�ϻ�����ԣ���ehcache�Ҳ�������Ļ���ʱ����ʹ�����������ԡ�ֻ�ܶ���һ���� --&gt;</span>\r\n    <span class=\"hljs-comment\" style=\"margin: 0px; padding: 0px; color: rgb(136, 0, 0);\">&lt;!-- name:�������ơ� \r\n        maxElementsInMemory:���������Ŀ\r\n        maxElementsOnDisk��Ӳ����󻺴������ \r\n        eternal:�����Ƿ�������Ч��һ�������ˣ�timeout���������á� \r\n        overflowToDisk:�Ƿ񱣴浽���̣���ϵͳ����ʱ \r\n        timeToIdleSeconds:���ö�����ʧЧǰ����������ʱ�䣨��λ���룩������eternal=false������������Чʱʹ �ã���ѡ���ԣ�Ĭ��ֵ��0��Ҳ���ǿ�����ʱ�������\r\n        timeToLiveSeconds:���ö�����ʧЧǰ������ʱ�䣨��λ���룩�����ʱ����ڴ���ʱ���ʧЧʱ��֮�䡣�� ��eternal=false������������Чʱʹ�ã�Ĭ����0.��Ҳ���Ƕ�����ʱ������� \r\n        diskPersistent���Ƿ񻺴����������������Whether the disk store persists between restarts \r\n        of the Virtual Machine. The default value is false. \r\n        diskSpoolBufferSizeMB�������������DiskStore�����̻��棩�Ļ�������С��Ĭ����30MB��ÿ��Cache��Ӧ�� ���Լ���һ���������� \r\n        diskExpiryThreadIntervalSeconds������ʧЧ�߳�����ʱ������Ĭ����120�롣 \r\n        memoryStoreEvictionPolicy�����ﵽmaxElementsInMemory����ʱ��Ehcache�������ָ���Ĳ���ȥ������ �档Ĭ�ϲ�����LRU���������ʹ�ã���\r\n        ���������ΪFIFO���Ƚ��ȳ�������LFU������ʹ�ã��� \r\n        clearOnFlush���ڴ��������ʱ�Ƿ������\r\n        memoryStoreEvictionPolicy:��ѡ�����У�LRU���������ʹ�ã�Ĭ�ϲ��ԣ���FIFO���Ƚ��ȳ�����LFU������ ���ʴ������� \r\n         FIFO��first in first out������Ǵ������ģ��Ƚ��ȳ���\r\n         LFU�� Less Frequently Used����������������ʹ�õĲ��ԣ�ֱ��һ����ǽ�һֱ�������ٱ�ʹ�õġ������� �����������Ԫ����һ��hit���ԣ�hitֵ��С�Ľ��ᱻ������档 \r\n         LRU��Least Recently Used���������ʹ�õģ������Ԫ����һ��ʱ������������������ˣ�������Ҫ�ڳ��� ���������µ�Ԫ�ص�ʱ��\r\n         ��ô���л���Ԫ����ʱ����뵱ǰʱ����Զ��Ԫ�ؽ���������档 --&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">defaultCache</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">eternal</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">maxElementsInMemory</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"1000\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">overflowToDisk</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">diskPersistent</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span>\r\n        <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">timeToIdleSeconds</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"0\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">timeToLiveSeconds</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"600\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">memoryStoreEvictionPolicy</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"LRU\"</span> /&gt;</span>\r\n    <span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">cache</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">name</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"snailAuthCache\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">eternal</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">maxElementsInMemory</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"10000\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">overflowToDisk</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">diskPersistent</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"false\"</span>\r\n        <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">timeToIdleSeconds</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"0\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">timeToLiveSeconds</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"0\"</span> <span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">memoryStoreEvictionPolicy</span>=<span class=\"hljs-value\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"LFU\"</span> /&gt;</span>\r\n<span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">ehcache</span>&gt;</span></code><ul class=\"pre-numbering\" style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; list-style: none; position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; border-right: 1px solid rgb(221, 221, 221); text-align: right;\"><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">1</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">2</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">3</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">4</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">5</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">6</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">7</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">8</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">9</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">10</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">11</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">12</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">13</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">14</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">15</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">16</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">17</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">18</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">19</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">20</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">21</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">22</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">23</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">24</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">25</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">26</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">27</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">28</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">29</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">30</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">31</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">32</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">33</li></ul><div class=\"save_code tracking-ad\" data-mod=\"popu_249\" style=\"margin: 0px; padding: 0px; position: absolute; height: 60px; right: 30px; top: 5px; color: rgb(255, 255, 255); cursor: pointer; z-index: 2;\"><a style=\"color: rgb(202, 12, 22); margin: 0px; padding: 0px; outline: none;\"><img src=\"http://static.blog.csdn.net/images/save_snippets.png\" style=\"outline: none; max-width: 100%;\"></a></div></pre><h3 id=\"����\" style=\"font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; color: rgb(63, 63, 63); margin: 0.8em 0px; font-size: 1.7em; padding: 0px;\"><br></h3>', 'article', null, null, null, null, '1', null, '0', '1', 'bootdo', '2017-09-22 17:48:29', '2017-09-22 17:48:29');
INSERT INTO `blog_content` VALUES ('109', 'spring-boot����ehcacheʵ�ֻ������', null, null, null, '<p><br></p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����EhCache ��һ����Java�Ľ����ڻ����ܣ����п��١����ɵ��ص㣬��Hibernate��Ĭ�ϵ�CacheProvider��</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����ehcache�ṩ�˶��ֻ�����ԣ���Ҫ��Ϊ�ڴ�ʹ����������������赣���������⡣</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����spring-boot��һ�����ٵļ��ɿ�ܣ������Ŀ������������SpringӦ�õĳ�ʼ��Լ��������̡��ÿ��ʹ�����ض��ķ�ʽ���������ã��Ӷ�ʹ������Ա������Ҫ�������廯�����á�</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������spring-boot�����κ����廯�������ļ�������spring-boot����һЩ�������ʱ������΢�Ĳ�ͬ��</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����1.spring-boot��һ��ͨ��maven�����jar���Ŀ�ܣ�����ehcache��Ҫ����������</p><div class=\"cnblogs_code\" style=\"background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); padding: 5px; overflow: auto; margin: 5px 0px; color: rgb(0, 0, 0); font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div><pre style=\"margin-bottom: 0px; line-height: 1.42857; font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n     <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-context-support<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n         <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>net.sf.ehcache<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n      <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>ehcache<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n          <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>2.8.3<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>        </pre><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div></div><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">������������pom.xml�ļ�����</p><div class=\"cnblogs_code\" style=\"background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); padding: 5px; overflow: auto; margin: 5px 0px; color: rgb(0, 0, 0); font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div><pre style=\"margin-bottom: 0px; line-height: 1.42857; font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;?</span><span style=\"color: rgb(255, 0, 255); line-height: 1.5 !important;\">xml version=\"1.0\" encoding=\"UTF-8\"</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">?&gt;</span>\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">project </span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">xmlns</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"http://maven.apache.org/POM/4.0.0\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> xmlns:xsi</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"http://www.w3.org/2001/XMLSchema-instance\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">\r\n    xsi:schemaLocation</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">modelVersion</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>4.0.0<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">modelVersion</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>com.lclc.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>boot-cache<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>0.0.1-SNAPSHOT<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">&lt;!--</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\"> Inherit defaults from Spring Boot </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">--&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">parent</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-boot-starter-parent<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>1.1.3.RELEASE<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">parent</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencies</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-boot-starter-web<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-boot-starter-data-jpa<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-boot-starter-thymeleaf<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        \r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>mysql<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>mysql-connector-java<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>com.google.guava<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>guava<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>17.0<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        \r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-context-support<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>net.sf.ehcache<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>ehcache<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>2.8.3<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">version</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependency</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencies</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencyManagement</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencies</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencies</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">dependencyManagement</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">build</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">plugins</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">plugin</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n                <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>org.springframework.boot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">groupId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n                <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-boot-maven-plugin<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">artifactId</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">plugin</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">plugins</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">build</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repositories</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-snapshots<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>http://repo.spring.io/snapshot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">snapshots</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n                <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">enabled</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>true<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">enabled</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">snapshots</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-milestones<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>http://repo.spring.io/milestone<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">repositories</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepositories</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-snapshots<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>http://repo.spring.io/snapshot<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>spring-milestones<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">id</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>http://repo.spring.io/milestone<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">url</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n        <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepository</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">pluginRepositories</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">project</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span></pre><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div></div><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;����2.ʹ��ehcache��������Ҫһ��ehcache.xml������һЩcache�����ԡ�</p><div class=\"cnblogs_code\" style=\"background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); padding: 5px; overflow: auto; margin: 5px 0px; color: rgb(0, 0, 0); font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div><pre style=\"margin-bottom: 0px; line-height: 1.42857; font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;?</span><span style=\"color: rgb(255, 0, 255); line-height: 1.5 !important;\">xml version=\"1.0\" encoding=\"UTF-8\"</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">?&gt;</span>\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">ehcache </span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">xmlns:xsi</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"http://www.w3.org/2001/XMLSchema-instance\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> xsi:noNamespaceSchemaLocation</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"http://ehcache.org/ehcache.xsd\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">\r\n  updateCheck</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span>\r\n          <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">diskStore </span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">path</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"java.io.tmpdir/Tmp_EhCache\"</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">/&gt;</span>\r\n           <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">defaultCache </span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">eternal</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> maxElementsInMemory</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"1000\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> overflowToDisk</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> diskPersistent</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">\r\n    timeToIdleSeconds</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"0\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> timeToLiveSeconds</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"600\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> memoryStoreEvictionPolicy</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"LRU\"</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">/&gt;</span>\r\n\r\n            <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">cache </span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">name</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"demo\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> eternal</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> maxElementsInMemory</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"100\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> overflowToDisk</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> diskPersistent</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"false\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\">\r\n    timeToIdleSeconds</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"0\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> timeToLiveSeconds</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"300\"</span><span style=\"color: rgb(255, 0, 0); line-height: 1.5 !important;\"> memoryStoreEvictionPolicy</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">=\"LRU\"</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">/&gt;</span>\r\n\r\n<span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&lt;/</span><span style=\"color: rgb(128, 0, 0); line-height: 1.5 !important;\">ehcache</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">&gt;</span></pre><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div></div><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;�������������xml�ļ��еı�ǩ��</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����(1).<span style=\"color: rgb(128, 0, 0);\">diskStore��</span>&nbsp;Ϊ����·����ehcache��Ϊ�ڴ�ʹ��������������Զ�����̵Ļ���λ�á������������£���������<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��������user.home �C �û���Ŀ¼<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ������ ��user.dir&nbsp; �C �û���ǰ����Ŀ¼<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������&nbsp; java.io.tmpdir �C Ĭ����ʱ�ļ�·��</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����(2).<span style=\"color: rgb(128, 0, 0);\">defaultCache��<span style=\"color: rgb(0, 0, 0);\">Ĭ�ϻ�����ԣ���ehcache�Ҳ�������Ļ���ʱ����ʹ�����������ԡ�ֻ�ܶ���һ����</span></span></p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\"><span style=\"color: rgb(128, 0, 0);\"><span style=\"color: rgb(0, 0, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (3).<span style=\"color: rgb(128, 0, 0);\">cache</span>���Զ�������ԣ�Ϊ�Զ���Ļ�����ԡ������������£�</span></span></p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\"><span style=\"color: rgb(128, 0, 0);\"><span style=\"color: rgb(0, 0, 0);\">������ cacheԪ�ص����ԣ�&nbsp; &nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; name����������&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; maxElementsInMemory���ڴ�����󻺴������&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; maxElementsOnDisk��Ӳ������󻺴������������0��ʾ�����&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; eternal��true��ʾ�����������ڣ���ʱ�����timeToIdleSeconds��timeToLiveSeconds���ԣ�Ĭ��Ϊfalse &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; overflowToDisk��true��ʾ���ڴ滺��Ķ�����Ŀ�ﵽ��maxElementsInMemory���޺󣬻������Ķ���д��Ӳ�̻����С�ע�⣺�������Ķ���Ҫд�뵽Ӳ���еĻ�����ö������ʵ����Serializable�ӿڲ��С�&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; diskSpoolBufferSizeMB�����̻�������С��Ĭ��Ϊ30MB��ÿ��Cache��Ӧ�����Լ���һ����������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; diskPersistent���Ƿ񻺴����������������&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; diskExpiryThreadIntervalSeconds������ʧЧ�߳�����ʱ������Ĭ��Ϊ120��&nbsp; &nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; timeToIdleSeconds�� �趨��������ڿ���״̬���ʱ�䣬����Ϊ��λ���������Դ����һ�α����ʺ�������ڿ���״̬��ʱ�䳬����timeToIdleSeconds����ֵ���������ͻ���ڣ�EHCache�������ӻ�������ա�ֻ�е�eternal����Ϊfalse�������Բ���Ч�����������ֵΪ0�����ʾ������������ڵش��ڿ���״̬&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; timeToLiveSeconds���趨������������ڻ����е��ʱ�䣬����Ϊ��λ���������Դӱ���ŵ������к�������ڻ����е�ʱ�䳬���� timeToLiveSeconds����ֵ���������ͻ���ڣ�EHCache�������ӻ����������ֻ�е�eternal����Ϊfalse�������Բ���Ч�����������ֵΪ0�����ʾ������������ڵش����ڻ����С�timeToLiveSeconds�������timeToIdleSeconds���ԣ���������&nbsp; &nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; memoryStoreEvictionPolicy�����ﵽmaxElementsInMemory����ʱ��Ehcache�������ָ���Ĳ���ȥ�����ڴ档��ѡ�����У�LRU���������ʹ�ã�Ĭ�ϲ��ԣ���FIFO���Ƚ��ȳ�����LFU�����ٷ��ʴ�������&nbsp;&nbsp;</span></span></p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\"><span style=\"color: rgb(128, 0, 0);\"><span style=\"color: rgb(0, 0, 0);\">����3.��ehcache�Ĺ�������¶��spring��������������</span></span></p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\"><span style=\"color: rgb(128, 0, 0);\"><span style=\"color: rgb(0, 0, 0);\">����</span></span></p><div class=\"cnblogs_code\" style=\"background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); padding: 5px; overflow: auto; margin: 5px 0px; color: rgb(0, 0, 0); font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div><pre style=\"margin-bottom: 0px; line-height: 1.42857; font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">@Configuration\r\n</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">//</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\"> ��ע�����˻���</span>\r\n<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">@EnableCaching\r\n</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">class</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> CacheConfiguration {\r\n\r\n    </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">/*</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">\r\n     * ehcache ��Ҫ�Ĺ�����\r\n     </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">*/</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">\r\n    @Bean(name </span>= \"appEhCacheCacheManager\"<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">)\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){\r\n        </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">return</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">new</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> EhCacheCacheManager (bean.getObject ());\r\n    }\r\n\r\n    </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">/*</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">\r\n     * ��shared��������,Spring�ֱ�ͨ��CacheManager.create()��new CacheManager()��ʽ������һ��ehcache����.\r\n     </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">*/</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">\r\n    @Bean\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){\r\n        EhCacheManagerFactoryBean cacheManagerFactoryBean </span>= <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">new</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> EhCacheManagerFactoryBean ();\r\n        cacheManagerFactoryBean.setConfigLocation (</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">new</span> ClassPathResource (\"conf/ehcache-app.xml\"<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">));\r\n        cacheManagerFactoryBean.setShared (</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">true</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">);\r\n        </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">return</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> cacheManagerFactoryBean;\r\n    }\r\n}</span></pre><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div></div><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;&nbsp; ��������<span style=\"color: rgb(0, 0, 0);\">@Configuration</span>��Ϊspring-bootע�⣬��Ҫ��ע��Ϊ�����࣬����ɨ�衣</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp; ��������<span style=\"color: rgb(0, 0, 0);\">@Bean</span>����spring�����м���bean��</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">�����������е����ö������ˣ�ͨ��spring-boot���м��ɿ�ܾ�����ô�򵥡�</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����4.ʹ��ehcache</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������ʹ��ehcache��Ҫͨ��spring�Ļ�����ƣ��������ǽ�spring�Ļ������ʹ����ehcache����ʵ�֣�����ʹ�÷������ȫʹ��spring������ƾ����ˡ�<br>������������ǣ��������ע�⣺</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������@Cacheable�����𽫷����ķ���ֵ���뵽�����У�����3<br>��������@CacheEvict������������棬����4</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">�����������������ͣ�</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������value������λ�����ƣ�����Ϊ�գ����ʹ��EHCache������ehcache.xml��������cache��name<br>��������key�������key��Ĭ��Ϊ�գ��ȱ�ʾʹ�÷����Ĳ������ͼ�����ֵ��Ϊkey��֧��SpEL<br>��������condition������������ֻ����������������Ż���뻺�棬Ĭ��Ϊ�գ��ȱ�ʾȫ�������뻺�棬֧��SpEL</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������allEntries��CacheEvict������true��ʾ���value�е�ȫ�����棬Ĭ��Ϊfalse</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������˵��ֱ���ϴ��룺</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">����</p><div class=\"cnblogs_code\" style=\"background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); padding: 5px; overflow: auto; margin: 5px 0px; color: rgb(0, 0, 0); font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div><pre style=\"margin-bottom: 0px; line-height: 1.42857; font-family: &quot;Courier New&quot; !important; font-size: 12px !important;\"><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">@Service\r\n</span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">class</span> CacheDemoServiceImpl <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">implements</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> CacheDemoService {\r\n\r\n    </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">/**</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">\r\n     * �����key\r\n     </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">*/</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">static</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">final</span> String THING_ALL_KEY   = \"\\\"thing_all\\\"\"<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">;\r\n    </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">/**</span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">\r\n     * value���Ա�ʾʹ���ĸ�������ԣ����������ehcache.xml\r\n     </span><span style=\"color: rgb(0, 128, 0); line-height: 1.5 !important;\">*/</span>\r\n    <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">static</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">final</span> String DEMO_CACHE_NAME = \"demo\"<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">;\r\n   \r\n    @CacheEvict(value </span>= DEMO_CACHE_NAME,key =<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> THING_ALL_KEY)\r\n    @Override\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">void</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> create(Thing thing){\r\n        Long id </span>=<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> getNextId ();\r\n        thing.setId (id);\r\n        data.put (id, thing);\r\n    } \r\n      \r\n     @Cacheable(value </span>=<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> DEMO_CACHE_NAME,key = \"#thing.getId()+\'thing\'\")\r\n    @Override\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> Thing findById(Long id){\r\n        System.err.println (</span>\"û���߻��棡\" +<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> id);\r\n        </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">return</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> data.get (id);\r\n    }\r\n\r\n      @Cacheable(value </span>= DEMO_CACHE_NAME,key =<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> THING_ALL_KEY)\r\n    @Override\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> List&lt;Thing&gt;<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> findAll(){\r\n        </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">return</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> Lists.newArrayList (data.values ());\r\n    }\r\n   \r\n   </span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">\r\n      @Override\r\n    @CachePut(value </span>= DEMO_CACHE_NAME,key = \"#thing.getId()+\'thing\'\"<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\">)\r\n    @CacheEvict(value </span>= DEMO_CACHE_NAME,key =<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> THING_ALL_KEY)\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> Thing update(Thing thing){\r\n        System.out.println (thing);\r\n        data.put (thing.getId (), thing);\r\n        </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">return</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> thing;\r\n    }\r\n\r\n    @CacheEvict(value </span>=<span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> DEMO_CACHE_NAME)\r\n    @Override\r\n    </span><span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">public</span> <span style=\"color: rgb(0, 0, 255); line-height: 1.5 !important;\">void</span><span style=\"color: rgb(0, 0, 0); line-height: 1.5 !important;\"> delete(Long id){\r\n        data.remove (id);\r\n    }\r\n   \r\n}</span></pre><div class=\"cnblogs_code_toolbar\" style=\"margin-top: 5px;\"><span class=\"cnblogs_code_copy\" style=\"padding-right: 5px; line-height: 1.5 !important;\"><a title=\"���ƴ���\" style=\"color: rgb(86, 182, 233); background-color: rgb(245, 245, 245) !important; border: none !important;\"><img src=\"http://common.cnblogs.com/images/copycode.gif\" alt=\"���ƴ���\" style=\"max-width: 900px; border-width: initial !important; border-style: none !important;\"></a></span></div></div><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">��������5.ֻ��Ҫͨ��ע����service�㷽���ϴ�ע������ʹ�û��棬��find**�ϴ��뻺�棬��delete**,update**��������档</p><p style=\"margin-top: 10px; margin-right: auto; margin-left: auto; color: rgb(35, 35, 35); font-family: Verdana, Arial, helvetica, sans-seriff; font-size: 14px;\">&nbsp;</p>', 'article', null, null, null, null, '1', null, '0', '1', 'bootdo', '2017-09-24 11:15:18', '2017-09-24 11:15:18');
INSERT INTO `blog_content` VALUES ('110', 'spring boot ͼƬ�ϴ����ͼƬ��ȡ·����win��linux�������õĲ��', null, null, null, '<ol><li><p style=\"margin-bottom: 0px; padding: 0px; color: rgb(69, 69, 69); font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; font-size: 16px;\">win</p><p style=\"margin-bottom: 0px; padding: 0px; color: rgb(69, 69, 69); font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; font-size: 16px;\"></p><div class=\"dp-highlighter bg_java\" style=\"padding: 1px 0px 0px; font-family: Consolas, &quot;Courier New&quot;, Courier, mono, serif; font-size: 12px; background-color: rgb(231, 229, 220); width: 849.412px; overflow-x: auto; overflow-y: hidden; position: relative; color: rgb(69, 69, 69); margin: 18px 0px !important;\"><div class=\"bar\" style=\"margin: 0px; padding: 0px 0px 0px 45px;\"><div class=\"tools\" style=\"margin: 0px; padding: 3px 8px 10px 10px; font-stretch: normal; font-size: 9px; line-height: normal; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; color: silver; background-color: rgb(248, 248, 248); border-left: 3px solid rgb(108, 226, 108); border-right: 1px solid rgb(231, 229, 220);\"><b>[java]</b>&nbsp;<a href=\"http://blog.csdn.net/qq1115094858/article/details/51873698#\" class=\"ViewSource\" title=\"view plain\" style=\"background-image: url(&quot;images/default/ico_plain.gif&quot;); background-position: left top; background-repeat: no-repeat; color: rgb(202, 12, 22); margin: 0px 10px 0px 0px; padding: 1px; outline: none; border: none; font-size: 9px; display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">view plain</a><span class=\"tracking-ad\" data-mod=\"popu_168\" style=\"margin: 0px; padding: 0px;\">&nbsp;<a href=\"http://blog.csdn.net/qq1115094858/article/details/51873698#\" class=\"CopyToClipboard\" title=\"copy\" style=\"background-image: url(&quot;images/default/ico_copy.gif&quot;); background-position: left top; background-repeat: no-repeat; color: rgb(202, 12, 22); margin: 0px 10px 0px 0px; padding: 1px; outline: none; border: none; font-size: 9px; display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">copy</a><div style=\"margin: 0px; padding: 0px; position: absolute; left: 294px; top: 296px; width: 16px; height: 16px; z-index: 99;\"></div></span><span class=\"tracking-ad\" data-mod=\"popu_169\" style=\"margin: 0px; padding: 0px;\"></span></div></div><ol start=\"1\" class=\"dp-j\" style=\"padding: 0px; list-style-position: initial; list-style-image: initial; border-top: none; border-right: 1px solid rgb(231, 229, 220); border-bottom: none; border-left: none; border-image: initial; background-color: rgb(255, 255, 255); color: rgb(92, 92, 92); margin-right: 0px !important; margin-bottom: 1px !important; margin-left: 45px !important;\"><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Component</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">class</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;WebConfigurer&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">extends</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;WebMvcConfigurerAdapter&nbsp;{&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Override</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">void</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;addResourceHandlers(ResourceHandlerRegistry&nbsp;registry)&nbsp;{&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;registry.addResourceHandler(<span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"/files/**\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">).addResourceLocations(</span><span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"file:///E:/var/spring/uploaded_files/\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">);&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">}&nbsp;&nbsp;</span></li></ol></div><span style=\"color: rgb(69, 69, 69); font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; font-size: 16px;\">linux</span><br style=\"color: rgb(69, 69, 69); font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; font-size: 16px;\"><div class=\"dp-highlighter bg_java\" style=\"padding: 1px 0px 0px; font-family: Consolas, &quot;Courier New&quot;, Courier, mono, serif; font-size: 12px; background-color: rgb(231, 229, 220); width: 849.412px; overflow-x: auto; overflow-y: hidden; position: relative; color: rgb(69, 69, 69); margin: 18px 0px !important;\"><div class=\"bar\" style=\"margin: 0px; padding: 0px 0px 0px 45px;\"><div class=\"tools\" style=\"margin: 0px; padding: 3px 8px 10px 10px; font-stretch: normal; font-size: 9px; line-height: normal; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; color: silver; background-color: rgb(248, 248, 248); border-left: 3px solid rgb(108, 226, 108); border-right: 1px solid rgb(231, 229, 220);\"><b>[java]</b>&nbsp;<a href=\"http://blog.csdn.net/qq1115094858/article/details/51873698#\" class=\"ViewSource\" title=\"view plain\" style=\"background-image: url(&quot;images/default/ico_plain.gif&quot;); background-position: left top; background-repeat: no-repeat; color: rgb(202, 12, 22); margin: 0px 10px 0px 0px; padding: 1px; outline: none; border: none; font-size: 9px; display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">view plain</a><span class=\"tracking-ad\" data-mod=\"popu_168\" style=\"margin: 0px; padding: 0px;\">&nbsp;<a href=\"http://blog.csdn.net/qq1115094858/article/details/51873698#\" class=\"CopyToClipboard\" title=\"copy\" style=\"background-image: url(&quot;images/default/ico_copy.gif&quot;); background-position: left top; background-repeat: no-repeat; color: rgb(202, 12, 22); margin: 0px 10px 0px 0px; padding: 1px; outline: none; border: none; font-size: 9px; display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">copy</a><div style=\"margin: 0px; padding: 0px; position: absolute; left: 294px; top: 528px; width: 16px; height: 16px; z-index: 99;\"></div></span><span class=\"tracking-ad\" data-mod=\"popu_169\" style=\"margin: 0px; padding: 0px;\"></span></div></div><ol start=\"1\" class=\"dp-j\" style=\"padding: 0px; list-style-position: initial; list-style-image: initial; border-top: none; border-right: 1px solid rgb(231, 229, 220); border-bottom: none; border-left: none; border-image: initial; background-color: rgb(255, 255, 255); color: rgb(92, 92, 92); margin-right: 0px !important; margin-bottom: 1px !important; margin-left: 45px !important;\"><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Component</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">class</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;WebConfigurer&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">extends</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;WebMvcConfigurerAdapter&nbsp;{&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Override</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; font-weight: bold; border: none; color: rgb(0, 102, 153); background-color: inherit;\">void</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;addResourceHandlers(ResourceHandlerRegistry&nbsp;registry)&nbsp;{&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;registry.addResourceHandler(<span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"/files/**\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">).addResourceLocations(</span><span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"file:///var/spring/uploaded_files\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">);&nbsp;&nbsp;</span></span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; color: inherit; line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"\" style=\"margin-left: 40px; list-style: decimal; border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin-top: 0px !important; margin-right: 0px !important; margin-bottom: 0px !important; padding: 0px 3px 0px 10px !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">}&nbsp;&nbsp;</span></li><li></li></ol></div></li></ol>', 'article', null, null, null, null, '1', null, '1', '1', 'bootdo', '2017-09-24 09:15:35', '2017-09-24 09:15:35');
INSERT INTO `blog_content` VALUES ('111', 'Springmvc�ύ�������Ͳ���', null, null, null, '<ol style=\"color: rgb(63, 63, 63); font-family: &quot;microsoft yahei&quot;; font-size: 15px;\"><li><p style=\"margin-bottom: 1.1em; padding: 0px;\">��������&nbsp;<br>��springmvc����У�ǰ̨���뵽��̨��form�ᾭ��springmvc�Զ���װ��pojo���У���̨���ܵ�ʱ������ڲ�����ֱ�ӽ������java�ࡣ</p></li><li><p style=\"margin-bottom: 1.1em; padding: 0px;\">����&nbsp;<br>ͨ������£�ǰ̨�ı�����������int,string�ȣ��������pojo���ֶε������Զ�ת��������Ϊ����ʡȥ�˲����鷳�����ܿ�ϧ���в������������͡�</p></li><li><p style=\"margin-bottom: 1.1em; padding: 0px;\">ԭ��&nbsp;<br>��Ϊ���ڵĸ�ʽ���ֶ�����spring�����ʺ϶�����з�װ������spring�����˱�ݵķ����������Լ�ת���������͡�</p></li><li><p style=\"margin-bottom: 1.1em; padding: 0px;\">����ʵ��</p></li></ol><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(63, 63, 63); font-family: &quot;microsoft yahei&quot;; font-size: 15px;\">��controller���У��������´����</p><pre class=\"prettyprint\" name=\"code\" style=\"white-space: nowrap; position: relative; overflow-y: hidden; margin-bottom: 1.1em; font-family: &quot;Source Code Pro&quot;, monospace; padding: 5px 5px 5px 60px; font-size: 14px; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-width: 0px; border-color: rgb(136, 136, 136); border-radius: 0px;\"><code class=\"hljs java has-numbering\" style=\"display: block; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; word-wrap: normal;\"><span class=\"hljs-annotation\" style=\"color: rgb(155, 133, 157);\">@InitBinder</span>\r\n<span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">public</span> <span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">void</span> <span class=\"hljs-title\">initBinder</span>(WebDataBinder binder) {\r\n    SimpleDateFormat dateFormat = <span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">new</span> SimpleDateFormat(<span class=\"hljs-string\" style=\"color: rgb(0, 136, 0);\">\"yyyy-MM-dd\"</span>);\r\n    dateFormat.setLenient(<span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">false</span>);\r\n    binder.registerCustomEditor(Date.class, <span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">new</span> CustomDateEditor(dateFormat, <span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">true</span>));<span class=\"hljs-comment\" style=\"color: rgb(136, 0, 0);\">//true:���������ֵ��false:����Ϊ��ֵ</span>\r\n}</code><ul class=\"pre-numbering\" style=\"position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; border-right: 1px solid rgb(221, 221, 221); list-style: none; text-align: right;\"><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">1</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">2</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">3</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">4</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">5</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">6</li></ul></pre><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(63, 63, 63); font-family: &quot;microsoft yahei&quot;; font-size: 15px;\">���Խ��������⡣�������ʱ��Date���͵Ĳ�����null�Ļ������ǻᱨ����������һ�ַ�ʽ����ã�ΪnullҲ���ᱨ�����ǰ����������װΪһ��vo�࣬�ڶ�Ӧ���������ϼ���ע�⣬����</p><pre class=\"prettyprint\" name=\"code\" style=\"white-space: nowrap; position: relative; overflow-y: hidden; margin-bottom: 1.1em; font-family: &quot;Source Code Pro&quot;, monospace; padding: 5px 5px 5px 60px; font-size: 14px; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-width: 0px; border-color: rgb(136, 136, 136); border-radius: 0px;\"><code class=\"hljs java has-numbering\" style=\"display: block; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; word-wrap: normal;\"><span class=\"hljs-annotation\" style=\"color: rgb(155, 133, 157);\">@DateTimeFormat</span>(iso = ISO.DATE_TIME, pattern = <span class=\"hljs-string\" style=\"color: rgb(0, 136, 0);\">\"w:yyyy\"</span>)\r\n<span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">private</span> Date startTime;\r\n����\r\n<span class=\"hljs-annotation\" style=\"color: rgb(155, 133, 157);\">@DateTimeFormat</span>(pattern=<span class=\"hljs-string\" style=\"color: rgb(0, 136, 0);\">\"yyyy-MM-dd HH:mm:ss\"</span>)\r\n<span class=\"hljs-keyword\" style=\"color: rgb(0, 0, 136);\">private</span> Date lastLoginDate;</code><ul class=\"pre-numbering\" style=\"position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; border-right: 1px solid rgb(221, 221, 221); list-style: none; text-align: right;\"><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">1</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">2</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">3</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">4</li><li style=\"padding: 0px 5px; list-style: none; margin-left: 0px;\">5</li></ul></pre><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(63, 63, 63); font-family: &quot;microsoft yahei&quot;; font-size: 15px;\">�������ʹ����֤��ܣ�������������д(@Valid XxxParam param, BindingResult binding) ������ֱ��ͨ��BindingResult�õ���֤����ˡ�</p>', 'article', null, null, null, null, '1', null, '1', '1', 'bootdo', '2017-09-25 21:34:51', '2017-09-25 21:34:51');
INSERT INTO `blog_content` VALUES ('112', ' SpringBoot ������ʱ���д���', null, null, null, '<p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">��Spring boot��Ŀ��ʵ�ʿ����У�������ʱ��Ҫ��Ŀ��������ʱ����һЩ���ݻ�Ԥ�����ĳЩ������Ϊ�˽�����������⣬Spring&nbsp;boot Ϊ�����ṩ��һ��������ͨ��ʵ�ֽӿ� CommandLineRunner ��ʵ������������</p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">ʵ�ַ�ʽ��ֻ��Ҫһ���༴�ɣ������������á�&nbsp;</p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">ʵ�ֲ��裺</p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">1.����ʵ�ֽӿ� CommandLineRunner ���� MyStartupRunnerTest</p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\"></p><div class=\"dp-highlighter bg_java\" style=\"font-family: Consolas, &quot;Courier New&quot;, Courier, mono, serif; font-size: 12px; background-color: rgb(231, 229, 220); width: 936.531px; overflow-x: auto; overflow-y: hidden; padding-top: 1px; position: relative; border-color: rgb(204, 204, 204); color: rgb(85, 85, 85); margin: 18px 0px !important;\"><div class=\"bar\" style=\"padding-left: 45px;\"><div class=\"tools\" style=\"padding: 3px 8px 10px 10px; font-stretch: normal; font-size: 9px; line-height: normal; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; color: silver; background-color: rgb(248, 248, 248); border-left: 3px solid rgb(108, 226, 108); border-right: 1px solid rgb(231, 229, 220);\"><strong>[java]</strong>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"ViewSource\" title=\"view plain\" style=\"background-image: url(&quot;images/default/ico_plain.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">view plain</a>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"CopyToClipboard\" title=\"copy\" style=\"background-image: url(&quot;images/default/ico_copy.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">copy</a><div style=\"position: absolute; left: 559px; top: 655px; width: 18px; height: 18px; z-index: 99;\"></div><div style=\"position: absolute; left: 721px; top: 723px; width: 18px; height: 18px; z-index: 99;\"></div></div></div><ol start=\"1\" class=\"dp-j\" style=\"padding: 0px; border: none; list-style-position: initial; list-style-image: initial; background-color: rgb(255, 255, 255); color: rgb(92, 92, 92); margin-right: 0px !important; margin-bottom: 1px !important; margin-left: 45px !important;\"><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">package</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;com.energy;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.boot.CommandLineRunner;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.core.annotation.Order;&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.stereotype.Component;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">/**</span>&nbsp;</span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">&nbsp;*&nbsp;Created&nbsp;by&nbsp;CavanLiu&nbsp;on&nbsp;2017/2/28&nbsp;0028.</span>&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">&nbsp;*/</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Component</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Order</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">(value=</span><span class=\"number\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(192, 0, 0); background-color: inherit;\">1</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">)</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">class</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;MyStartupRunnerTest&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">implements</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;CommandLineRunner&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">{&nbsp;&nbsp;</span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Override</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">void</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;run(String...&nbsp;args)&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">throws</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;Exception&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(<span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"&gt;&gt;&gt;&gt;This&nbsp;is&nbsp;MyStartupRunnerTest&nbsp;Order=1.&nbsp;Only&nbsp;testing&nbsp;CommandLineRunner...&lt;&lt;&lt;&lt;\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">);&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">}&nbsp;&nbsp;</span></li></ol></div><p style=\"margin-bottom: 0px; padding: 0px; color: rgb(85, 85, 85); font-family: Arial; font-size: 14px;\"></p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">2.����ʵ�ֽӿ�CommandLineRunner ���� MyStartupRunnerTest2</p><div class=\"dp-highlighter bg_java\" style=\"font-family: Consolas, &quot;Courier New&quot;, Courier, mono, serif; font-size: 12px; background-color: rgb(231, 229, 220); width: 936.531px; overflow-x: auto; overflow-y: hidden; padding-top: 1px; position: relative; border-color: rgb(204, 204, 204); color: rgb(85, 85, 85); margin: 18px 0px !important;\"><div class=\"bar\" style=\"padding-left: 45px;\"><div class=\"tools\" style=\"padding: 3px 8px 10px 10px; font-stretch: normal; font-size: 9px; line-height: normal; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; color: silver; background-color: rgb(248, 248, 248); border-left: 3px solid rgb(108, 226, 108); border-right: 1px solid rgb(231, 229, 220);\"><strong>[java]</strong>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"ViewSource\" title=\"view plain\" style=\"background-image: url(&quot;images/default/ico_plain.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">view plain</a>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"CopyToClipboard\" title=\"copy\" style=\"background-image: url(&quot;images/default/ico_copy.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">copy</a><div style=\"position: absolute; left: 559px; top: 1094px; width: 18px; height: 18px; z-index: 99;\"></div><div style=\"position: absolute; left: 721px; top: 1160px; width: 18px; height: 18px; z-index: 99;\"></div></div></div><ol start=\"1\" class=\"dp-j\" style=\"padding: 0px; border: none; list-style-position: initial; list-style-image: initial; background-color: rgb(255, 255, 255); color: rgb(92, 92, 92); margin-right: 0px !important; margin-bottom: 1px !important; margin-left: 45px !important;\"><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">package</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;com.energy;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.boot.CommandLineRunner;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.core.annotation.Order;&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">import</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;org.springframework.stereotype.Component;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">/**</span>&nbsp;</span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">&nbsp;*&nbsp;Created&nbsp;by&nbsp;CavanLiu&nbsp;on&nbsp;2017/2/28&nbsp;0028.</span>&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"comment\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); background-color: inherit;\">&nbsp;*/</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Component</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Order</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">(value=</span><span class=\"number\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(192, 0, 0); background-color: inherit;\">2</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">)</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">class</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;MyStartupRunnerTest2&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">implements</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;CommandLineRunner&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">{&nbsp;&nbsp;</span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"annotation\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(100, 100, 100); background-color: inherit;\">@Override</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;&nbsp;</span></span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">public</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">void</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;run(String...&nbsp;args)&nbsp;</span><span class=\"keyword\" style=\"margin: 0px; padding: 0px; border: none; color: rgb(0, 102, 153); background-color: inherit; font-weight: bold;\">throws</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&nbsp;Exception&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(<span class=\"string\" style=\"margin: 0px; padding: 0px; border: none; color: blue; background-color: inherit;\">\"&gt;&gt;&gt;&gt;This&nbsp;is&nbsp;MyStartupRunnerTest&nbsp;Order=2.&nbsp;Only&nbsp;testing&nbsp;CommandLineRunner...&lt;&lt;&lt;&lt;\"</span><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">);&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;&nbsp;</span></li><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">}&nbsp;&nbsp;</span></li></ol></div><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">3.����Spring boot��鿴����̨�����Ϣ��������ʾ��</p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\"></p><div class=\"dp-highlighter bg_plain\" style=\"font-family: Consolas, &quot;Courier New&quot;, Courier, mono, serif; font-size: 12px; background-color: rgb(231, 229, 220); width: 936.531px; overflow-x: auto; overflow-y: hidden; padding-top: 1px; position: relative; border-color: rgb(204, 204, 204); color: rgb(85, 85, 85); margin: 18px 0px !important;\"><div class=\"bar\" style=\"padding-left: 45px;\"><div class=\"tools\" style=\"padding: 3px 8px 10px 10px; font-stretch: normal; font-size: 9px; line-height: normal; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; color: silver; background-color: rgb(248, 248, 248); border-left: 3px solid rgb(108, 226, 108); border-right: 1px solid rgb(231, 229, 220);\"><strong>[plain]</strong>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"ViewSource\" title=\"view plain\" style=\"background-image: url(&quot;images/default/ico_plain.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">view plain</a>&nbsp;<a target=\"_blank\" href=\"http://blog.csdn.net/mimica247706624/article/details/58596490#\" class=\"CopyToClipboard\" title=\"copy\" style=\"background-image: url(&quot;images/default/ico_copy.gif&quot;); background-position: left top; background-repeat: no-repeat; border: none; padding: 1px; margin: 0px 10px 0px 0px; font-size: 9px; color: rgb(160, 160, 160); display: inline-block; width: 16px; height: 16px; text-indent: -2000px;\">copy</a><div style=\"position: absolute; left: 563px; top: 1532px; width: 18px; height: 18px; z-index: 99;\"></div><div style=\"position: absolute; left: 725px; top: 1597px; width: 18px; height: 18px; z-index: 99;\"></div></div></div><ol start=\"1\" style=\"padding: 0px; border: none; list-style-position: initial; list-style-image: initial; background-color: rgb(255, 255, 255); color: rgb(92, 92, 92); margin-right: 0px !important; margin-bottom: 1px !important; margin-left: 45px !important;\"><li class=\"alt\" style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; color: inherit; line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\"><span style=\"margin: 0px; padding: 0px; border: none; background-color: inherit;\">&gt;&gt;&gt;&gt;This&nbsp;is&nbsp;MyStartupRunnerTest&nbsp;Order=1.&nbsp;Only&nbsp;testing&nbsp;CommandLineRunner...&lt;&lt;&lt;&lt;&nbsp;&nbsp;</span></span></li><li style=\"border-top: none; border-right: none; border-bottom: none; border-left: 3px solid rgb(108, 226, 108); border-image: initial; list-style-type: decimal-leading-zero; list-style-image: initial; background-color: rgb(248, 248, 248); line-height: 18px; margin: 0px !important; padding: 0px 3px 0px 10px !important; list-style-position: outside !important;\"><span style=\"margin: 0px; padding: 0px; border: none; color: black; background-color: inherit;\">&gt;&gt;&gt;&gt;This&nbsp;is&nbsp;MyStartupRunnerTest2&nbsp;Order=2.&nbsp;Only&nbsp;testing&nbsp;CommandLineRunner...&lt;&lt;&lt;&lt;&nbsp;&nbsp;</span></li></ol></div><p style=\"margin-bottom: 0px; padding: 0px; color: rgb(85, 85, 85); font-family: Arial; font-size: 14px;\"></p><p style=\"margin-bottom: 1.1em; padding: 0px; color: rgb(85, 85, 85); font-family: &quot;microsoft yahei&quot;; font-size: 14px;\">4.Application����������ԡ�</p><p><span style=\"color: rgb(85, 85, 85); font-family: Arial; font-size: 14px;\">˵����CommandLineRunner�ӿڵ�����˳��������@Orderע���value��С����ִ�У���valueֵԽС���ȼ�Խ�ߡ�</span><br></p>', 'article', null, null, null, null, '1', null, '1', '1', 'bootdo', '2017-09-26 15:18:15', '2017-09-26 15:18:15');
INSERT INTO `blog_content` VALUES ('115', 'communication', null, null, null, '<h2 style=\"color: rgb(103, 106, 108);\"><span style=\"font-family: Times; font-size: 17.5px; font-weight: bold;\"><a href=\"https://jq.qq.com/?_wv=1027&amp;k=5EfMNFL\" target=\"_blank\" style=\"color: rgb(42, 100, 150); text-decoration-line: underline; outline: 0px;\">qqȺ 669039323</a></span></h2>', null, null, 'communication', null, null, '1', null, '0', '1', 'bootdo', '2017-09-30 14:43:30', '2017-09-30 14:43:30');
INSERT INTO `blog_content` VALUES ('116', 'ablout', null, null, null, '<h1 style=\"box-sizing: inherit; font-size: 28px; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); min-height: 1rem; -webkit-font-smoothing: antialiased; cursor: text; position: relative; margin-top: 0px !important;\">BootDo ����ѧϰ�͵Ŀ�Դ���</h1><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"ƽ̨���\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E5%B9%B3%E5%8F%B0%E7%AE%80%E4%BB%8B\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>ƽ̨���</h2><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">BootDo�Ǹ�Ч�ʣ��ͷ�װ������ѧϰ�ͣ�����΢����<strong style=\"box-sizing: inherit;\">��Դ</strong>Java EE������ܡ�</p><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">BootDo����SpringBoot�����ϴ��һ��Java��������ƽ̨��MyBatisΪ���ݷ��ʲ㣬ApacheShiroΪȨ����Ȩ�㣬Ehcahe�Գ������ݽ��л��档</p><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">BootDo��Ҫ��λ�ں�̨����ϵͳѧϰ�����������ú�̨����ϵͳ�Ļ������ܺ͸�Ч��<strong style=\"box-sizing: inherit;\">��������</strong>���ߣ� ������ϵͳȨ�����������Ȩ������������ֵ���������Ĺ����������ͼ���������������������������ɵȡ� ǰ�˽���������˽ṹ�򵥡�����������ҳ�����۴�����Twitter Bootstrapҳ��չʾ��ܡ� ���÷ֲ���ơ�˫����֤���ύ���ݰ�ȫ���롢������ܡ�������֤������Ȩ����֤�� ʹ��Maven����Ŀ���������Ŀ���׿����ԡ���չ�ԡ�</p><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">BootDoĿǰ���������Ĵ�ģ�飬ϵͳ����SYS��ģ�顢 ���ݹ���CMS��ģ�顢���߰칫��OA��ģ�顢�������ɣ�GEN��ģ�顣&nbsp;<strong style=\"box-sizing: inherit;\">ϵͳ����ģ��</strong>&nbsp;��������ҵ��֯�ܹ����û���������������������� �˵�������ɫȨ�޹����ֵ����ȹ��ܣ�&nbsp;<strong style=\"box-sizing: inherit;\">���ݹ���ģ��</strong>&nbsp;���������ݹ������¡����ӣ�����Ŀ����վ����� �������ԡ��ļ�����ǰ����վչʾ�ȹ��ܣ�&nbsp;<strong style=\"box-sizing: inherit;\">���߰칫ģ��</strong>&nbsp;���ṩ�򵥵��������ʵ����<strong style=\"box-sizing: inherit;\">��������ģ��</strong>&nbsp;������ظ��Ĺ�����</p><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">BootDo �ṩ�˳��ù��߽��з�װ��������־���ߡ����湤�ߡ�����������֤�������ֵ䡢��ǰ��֯�������� ���û��������������Լ���������С���ߵȡ����⻹�ṩһ��ǿ�������&nbsp;<strong style=\"box-sizing: inherit;\">��������</strong>&nbsp;���ߡ�</p><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"���ù���\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E5%86%85%E7%BD%AE%E5%8A%9F%E8%83%BD\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>���ù���</h2><ol class=\"task-list\" style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\"><li style=\"box-sizing: inherit; list-style-type: decimal;\">�û������û���ϵͳ�����ߣ��ù�����Ҫ���ϵͳ�û����á�</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">������������ϵͳ��֯��������˾�����š�С�飩�����ṹչ�֣�������������¼���</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">�������ϵͳ��������ģ�ͣ��磺���ҡ�ʡ�С����С����ص�ά����</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">�˵���������ϵͳ�˵�������Ȩ�ޣ���ťȨ�ޱ�ʶ�ȡ�</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">��ɫ������ɫ�˵�Ȩ�޷��䡢���ý�ɫ�������������ݷ�ΧȨ�޻��֡�</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">�ֵ������ϵͳ�о���ʹ�õ�һЩ��Ϊ�̶������ݽ���ά�����磺�Ƿ���Ů����𡢼���ȡ�</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">������־��ϵͳ����������־��¼�Ͳ�ѯ��ϵͳ�쳣��Ϣ��־��¼�Ͳ�ѯ��</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">���ӳؼ��ӣ����ӵ���ϵͳ���ݿ����ӳ�״̬���ɽ��з���SQL�ҳ�ϵͳ����ƿ����</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">���������棺ʵ��ҵ�񹤵���ת�����������������</li></ol><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"����ѡ��\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E6%8A%80%E6%9C%AF%E9%80%89%E5%9E%8B\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>����ѡ��</h2><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">1�����</p><ul class=\"task-list\" style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\"><li style=\"box-sizing: inherit; list-style-type: initial;\">���Ŀ�ܣ�Spring Boot</li><li style=\"box-sizing: inherit; list-style-type: initial;\">��ȫ��ܣ�Apache Shiro</li><li style=\"box-sizing: inherit; list-style-type: initial;\">ģ�����棺Thymeleaf</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�־ò��ܣ�MyBatis</li><li style=\"box-sizing: inherit; list-style-type: initial;\">���ݿ����ӳأ�Alibaba Druid</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�����ܣ�Ehcache ��Redis</li><li style=\"box-sizing: inherit; list-style-type: initial;\">��־����SLF4J</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�����ࣺApache Commons��Jackson ��Xstream 1.4��Dozer 5.3��POI 3.9</li></ul><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">2��ǰ��</p><ul class=\"task-list\" style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\"><li style=\"box-sizing: inherit; list-style-type: initial;\">JS��ܣ�jQuery</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�ͻ�����֤��JQuery Validation</li><li style=\"box-sizing: inherit; list-style-type: initial;\">���ı����߱༭��summernote</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�����ļ�����CKFinder</li><li style=\"box-sizing: inherit; list-style-type: initial;\">���ݱ��bootstrapTable</li><li style=\"box-sizing: inherit; list-style-type: initial;\">�����㣺layer</li><li style=\"box-sizing: inherit; list-style-type: initial;\">���ṹ�ؼ���jsTree</li></ul><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">4��ƽ̨</p><ul class=\"task-list\" style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\"><li style=\"box-sizing: inherit; list-style-type: initial;\">�������м����SpringBoot����</li><li style=\"box-sizing: inherit; list-style-type: initial;\">���ݿ�֧�֣�Ŀǰ���ṩMySql���ݿ��֧�֣������������ݿ⣬ƽ̨�����������ݿ�֧�ֽӿڣ� ����Ժܷ���ĸ���Ϊ�������ݿ⣬�磺SqlServer 2008��MySql 5.5��H2��</li><li style=\"box-sizing: inherit; list-style-type: initial;\">����������Java��Eclipse Java EE ��Maven ��Git</li></ul><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"��ȫ����\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E5%AE%89%E5%85%A8%E8%80%83%E8%99%91\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>��ȫ����</h2><ol class=\"task-list\" style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\"><li style=\"box-sizing: inherit; list-style-type: decimal;\">�������ԣ�ϵͳ����Java ���Կ���������׿Խ��ͨ���ԡ���Ч�ԡ�ƽ̨��ֲ�ԺͰ�ȫ�ԡ�</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">�ֲ���ƣ������ݿ�㣬���ݷ��ʲ㣬ҵ���߼��㣬չʾ�㣩������������ϣ��������ͨ���ӿڲ��ܽ��벢���в���У�飨�磺��չʾ�㲻��ֱ�Ӳ������ݿ⣩����֤���ݲ����İ�ȫ��</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">˫����֤���û����ύ˫��֤����������������֤���ͻ�����֤����ֹ�û�ͨ������������޸ģ��粻��д�ı������ر����۸ġ��ϴ��Ƿ��ļ��ȣ��������ͻ�����֤�������ݿ⡣</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">��ȫ���룺�û����ύ�������ݣ��ڷ������˶����а�ȫ���룬��ֹ�û��ύ�Ƿ��ű���SQLע���ȡ�������ݵȣ�ȷ�����ݰ�ȫ��</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">������ܣ���¼�û��������SHA1ɢ�м��ܣ��˼��ܷ����ǲ�����ġ���֤����й¶��İ�ȫ���⡣</li><li style=\"box-sizing: inherit; list-style-type: decimal;\">ǿ�Ʒ��ʣ�ϵͳ�����й�������Ӷ������û����Ȩ����֤����ֹ�û�ֱ����дurl���з��ʡ�</li></ol><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"��ʾ��ַ\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E6%BC%94%E7%A4%BA%E5%9C%B0%E5%9D%80\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>��ʾ��ַ</h2><h6 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; color: rgb(119, 119, 119); margin-top: 20px; font-size: 14px; -webkit-font-smoothing: antialiased; cursor: text; position: relative;\"><a id=\"��ཿ�Դwwwbootdocom\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E5%B8%83%E5%98%9F%E5%BC%80%E6%BA%90wwwbootdocom\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>��ཿ�Դ<a href=\"http://www.bootdo.com./blog\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word;\">www.bootdo.com</a></h6><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"��������\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E4%BA%A4%E6%B5%81%E5%8F%8D%E9%A6%88\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>��������</h2><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"qqȺ-669039323\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#qq%E7%BE%A4-669039323\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>QQȺ 669039323</h2><h2 style=\"box-sizing: inherit; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-weight: bold; line-height: 1.33em; color: rgb(0, 0, 0); margin-top: 20px; font-size: 24px; padding-bottom: 0.3em; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom: 1px solid rgb(204, 204, 204);\"><a id=\"��Ȩ����\" class=\"anchor\" href=\"https://gitee.com/lcg0124/bootdo#%E7%89%88%E6%9D%83%E5%A3%B0%E6%98%8E\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none;\"></a>��Ȩ����</h2><p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; word-break: break-word; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px;\">�����ʹ��&nbsp;<a href=\"http://www.apache.org/licenses/LICENSE-2.0\" style=\"box-sizing: inherit; color: rgb(9, 94, 171); word-wrap: break-word;\">Apache License 2.0</a>&nbsp;Э�飬���ϸ�����Э������</p><ul class=\"task-list\" style=\"box-sizing: inherit; line-height: 24px; padding-left: 30px; color: rgb(64, 72, 91); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Liberation Sans&quot;, &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Wenquanyi Micro Hei&quot;, &quot;WenQuanYi Zen Hei&quot;, &quot;ST Heiti&quot;, SimHei, &quot;WenQuanYi Zen Hei Sharp&quot;, sans-serif; font-size: 15px; margin-bottom: 0px !important;\"><li class=\"task-list-item\" style=\"box-sizing: inherit; list-style-type: initial;\"><input type=\"checkbox\" class=\"task-list-item-checkbox\" checked=\"\" disabled=\"\" style=\"margin-top: 0px; cursor: default; transition: border 0.2s linear, box-shadow 0.2s linear; box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 3px inset; vertical-align: middle;\">&nbsp;ע����������Ϊ����滯�����ֹ��ܻ���ʵ����</li></ul>', null, null, 'about', null, null, '1', null, '0', '1', 'bootdo', '2017-09-30 14:43:09', '2017-09-30 14:43:09');
INSERT INTO `blog_content` VALUES ('117', 'ҳ������ٶ��Ż�����', null, null, null, '<p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">1���ϲ�Js�ļ���CSS</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">��JS�����CSS��ʽ�ֱ�ϲ���һ��������ļ������������ܼ򻯴��룬������ִ��JS�ļ���ʱ�����JS�ļ��Ƚ϶࣬����Ҫ���ж�Ρ�Get�������ӳ������ٶȣ���JS�ļ��ϲ���һ�����Ȼ�ͼ�����Get�������������˼����ٶȡ�</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">2��SpritesͼƬ����</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">Spriting��һ����ҳͼƬӦ�ô���ʽ�����ǽ�һ��ҳ���漰������������ͼƬ��������һ�Ŵ�ͼ��ȥ��Ȼ������CSS����չ�ֳ���������һ���������ʸ�ҳ��ʱ�������ͼƬ�Ͳ�������ǰ����һ��һ����������ʾ�����ˣ����Լ�����������ҳ��ͼƬ��С����������CSSSprites�ܺܺõؼ�����ҳ��http���󣬴Ӷ��������ҳ������ܡ�CSSSprites�ڹ��ںܶ��˽�css���飬��������ˣ��ںܶ������վ�����õ����ر���һЩ����ҳ�涼���ڵ�ͼ���õñȽ϶࣬�ܺõ����������ٶȡ�</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">3��ѹ���ı���ͼƬ</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">ѹ��������gzip������Ч����ҳ����ص�ʱ�䡣����HTML��XML��JSON(JavaScript�������)��JavaScript��CSS�ȣ�ѹ���ʶ������ڴ�С70%���ҡ��ı�ѹ���õñȽ϶࣬һ��ֱ���ڿռ俪�����У���ͼƬ��ѹ���ͱȽ����⣬�ܶ඼��ֱ���ϴ�����ʵ���кܴ��ѹ���ռ䡣</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">4���ӳ���ʾ�ɼ������������</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">Ϊ��ȷ���û����Ը���ؿ����ɼ��������ҳ�����ӳټ��ػ�չ�ֿɼ�����������ݣ�Ϊ�˱���ҳ����Σ�����ʹ��ռλ����ǩ�ƶ���ȷ�ĸ߶ȺͿ�ȡ�����WP��jQueryImage LazyLoad����Ϳ������û�ͣ���ڵ�һ����ʱ�򣬲������κε�һ�����µ�ͼƬ��Ϣ��ֻ�е��û���������¹�����ʱ����ЩͼƬ�ſ�ʼ���ء����������������ɼ�����ļ����ٶȣ�����û����顣</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">5��ȷ������ͼƬ���ȼ���</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">��վ��Ҫ���ǿ����Ե���Ҫ�ԣ�һ�����ܰ�ťҪ��ǰ���س������û���������ҳ��һ��ֻ��Ҫ8sʱ������ػ���5s�ڵȴ���Ѱ�����ذ�ťͼƬ��˭������?</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">6�����²���Call-to-Action��ť</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">��ʵ���������һ���ǲ��ģ����Ǵ��û������ٶ����֣���������ҳ����������ٶȡ��ٶ�û�䣬ֻ����һЩ��Ϊ��ť��ǰ��Call-to-Action��ťһ��ϰ�������ҳ��ײ���������ϰ�߶����û���˵�������Ǻõģ������û���Ҫ�ȵ���������س������ܵ����һ�����������Ե���CTA��ť��λ�û�ʹ�û�����ͼƬ��ť���ܶ���͹�����վ�ļ��빺�ﳵ�����������͡�</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">7��ͼƬ��ʽ�Ż�</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">��ǡ����ͼ���ʽ��һ�ּ�Ϊ�����ļ��������ٶȵ�������ס���ȷ��ͼƬ��ʽ������ͼƬ��С�������������Ϊ��Ѹ�ʽ�����Խ�ʡ�����������ٴ���ʱ��ʱ�䣬���ӿ�ҳ������ٶȣ�����һ�ֺܳ�����������</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">8��ʹ�� Progressive JPEGs</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">ProgressiveJPEGsͼƬ��JPEG��ʽ��һ��������֣���Ϊ���߼�JPEG�����ڴ����߼�JPEG�ļ�ʱ���������������ŵģ���װ��ͼ��ʱ����ʼֻ��ʾһ��ģ����ͼ���������ݵ�װ�룬ͼ���𲽱�����������൱�ڽ�֯��GIF��ʽ��ͼƬ���߼�JPEG��Ҫ�ǿ��ǵ�ʹ�õ��ƽ�����������������Ƶģ����������ʹ����ͨ��������ᵽ��������JPEG��ʽͼƬ�����𡣶������ٱȽ������û����������кܺõ����顣</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">9���������</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">�������˵����ֱ�ӵ�һ��������Ҳ���õñȽ϶�ģ�����ҳ�����������ɾ������Ҫ�ĳ��ߴ��룬���粻��Ҫ�Ŀո񡢻��з���ע�͵ȣ�����JS�����е����ô���Ҳ��Ҫ��������ж���ע�ʹ�������������Щ�˴��������������е�����������ؼ��ʡ�</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">10���ӳټ��غ�ִ�зǱ�Ҫ�ű�</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">��ҳ���кܶ�ű�����ҳ����ȫ������ǰ������Ҫִ�еģ������ӳټ��غ�ִ�зǱ�Ҫ�ű�����Щ�ű�������onload�¼�֮��ִ�У��������ҳ����Ҫ���ݵĳ������Ӱ�졣��Щ�ű����������Լ���ҳ�ļױ��������������һЩ�������ű����������кܶ࣬�������ۡ���桢�����Ƽ����ٶ���ͼ������ȵȣ���Щ��ȫ���Ե��������ݼ��������ִ�С�</p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\"><span style=\"font-weight: 700;\">11��ʹ��AJAX</span></p><p style=\"margin-bottom: 24px; color: rgb(14, 14, 14); font-family: Arial, &quot;Hiragino Sans GB&quot;, �����, &quot;Microsoft YaHei&quot;, ΢���ź�, SimSun, ����, Helvetica, Tahoma, &quot;Arial sans-serif&quot;; font-size: 16px; text-align: justify;\">AJAX����Asynchronous Javascript +XML������ָһ�ִ�������ʽ��ҳӦ�õ���ҳ����������ͨ���ں�̨������������������ݽ�����AJAX����ʹ��ҳʵ���첽���¡�����ζ�ſ����ڲ����¼���������ҳ������£�����ҳ��ĳ���ֽ��и��¡���ͳ����ҳ(��ʹ��AJAX)�����Ҫ�������ݣ���������������ҳ�档</p>', null, null, '', null, null, '1', null, '0', '1', 'bootdo', '2017-09-30 16:13:35', '2017-09-30 16:13:35');
INSERT INTO `blog_content` VALUES ('118', 'elementUI select���ʹ�����', null, null, null, '<article style=\"padding: 20px 0px; border-top: 1px solid rgb(228, 235, 244); border-left: 1px solid rgb(228, 235, 244); border-right: 1px solid rgb(228, 235, 244); color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun; font-size: 16px;\"><div id=\"article_content\" class=\"article_content csdn-tracking-statistics\" data-mod=\"popu_307\" data-dsm=\"post\" style=\"margin: 0px 0px 50px; padding: 20px 30px 0px; color: rgb(69, 69, 69); overflow: hidden;\"><div class=\"markdown_views\" style=\"margin: 0px; padding: 0px; font-family: &quot;microsoft yahei&quot;; font-size: 15px; color: rgb(63, 63, 63);\"><h1 id=\"elementui-select���ʹ�����\" style=\"margin-top: 0.8em; margin-bottom: 0.8em; font-size: 2.6em; font-family: inherit; padding: 0px;\">elementUI select���ʹ�����</h1><ul style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 0px; list-style-position: initial; list-style-image: initial;\"><li style=\"margin: 0px 0px 0px 40px; padding: 0px; list-style: disc;\"><strong>��̬����optionѡ��</strong></li><li style=\"margin: 0px 0px 0px 40px; padding: 0px; list-style: disc;\"><strong>optionѡ��󶨶�Ӧ���ı�ֵ��valueֵ</strong></li><li style=\"margin: 0px 0px 0px 40px; padding: 0px; list-style: disc;\"><strong>��Ϊ����Ŀ���������༭����</strong></li><li style=\"margin: 0px 0px 0px 40px; padding: 0px; list-style: disc;\"><strong>��ѡ��ı�󴥷�����¼�</strong></li></ul><pre class=\"prettyprint\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; font-size: 14px; white-space: nowrap; padding: 5px 5px 5px 60px; margin-bottom: 1.1em; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-color: rgba(128, 128, 128, 0.075); border-radius: 0px; position: relative; overflow-y: hidden;\"><code class=\"hljs lasso has-numbering\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; display: block; word-wrap: normal;\"><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>div id<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"app\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n    <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span> :model<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"form\"</span>  ref<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"form\"</span> label<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-width</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"100px\"</span> class<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"demo-ruleForm\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n        <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span><span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-item</span> label<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"����ѡ��\"</span> prop<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"typeId\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n            <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-select</span> v<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-model</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"form.typeId\"</span> placeholder<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"��ѡ��\"</span> @change<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"change\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n                <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-option</span> v<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-for</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"item in items\"</span> :label<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"item.name\"</span> :value<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"item.id\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-option</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n            <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-select</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n        <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span><span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-item</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n        <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span><span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-item</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n            <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span> <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">type</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"primary\"</span> @click<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"add()\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>����<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n            <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span> <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">type</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"primary\"</span> @click<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"edit()\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>�༭<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n            <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span> @click<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">=</span><span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"cancel()\"</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>ȡ��<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-button</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n        <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span><span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-item</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n    <span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/el<span class=\"hljs-attribute\" style=\"margin: 0px; padding: 0px;\">-form</span><span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span>\r\n<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&lt;</span>/div<span class=\"hljs-subst\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0);\">&gt;</span></code><ul class=\"pre-numbering\" style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; list-style: none; position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; border-right: 1px solid rgb(221, 221, 221); text-align: right;\"><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">1</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">2</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">3</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">4</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">5</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">6</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">7</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">8</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">9</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">10</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">11</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">12</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">13</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">14</li></ul></pre><pre class=\"prettyprint\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; font-size: 14px; white-space: nowrap; padding: 5px 5px 5px 60px; margin-bottom: 1.1em; line-height: 1.45; background-color: rgba(128, 128, 128, 0.05); border-color: rgba(128, 128, 128, 0.075); border-radius: 0px; position: relative; overflow-y: hidden;\"><code class=\"hljs xml has-numbering\" style=\"font-family: &quot;Source Code Pro&quot;, monospace; white-space: pre; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; display: block; word-wrap: normal;\"><span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">script</span>&gt;</span><span class=\"javascript\" style=\"margin: 0px; padding: 0px;\">\r\n    <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">var</span> vm = <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">new</span> Vue({\r\n        el:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"#app\"</span>,\r\n        mounted(){\r\n            <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.getData();\r\n        },\r\n        data:{\r\n            form:{\r\n                typeId:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\'\'</span>\r\n            },\r\n            items:[],\r\n            datas:[{name:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"senbo\"</span>,id:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\'1\'</span>},{name:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"muse\"</span>,id:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\'2\'</span>},{name:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"bobo\"</span>,id:<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\'3\'</span>}]\r\n        },\r\n        methods:{\r\n            getData:<span class=\"hljs-function\" style=\"margin: 0px; padding: 0px;\"><span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">function</span><span class=\"hljs-params\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">()</span>{</span>\r\n                <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.items = <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.datas; \r\n\r\n            },\r\n            add:<span class=\"hljs-function\" style=\"margin: 0px; padding: 0px;\"><span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">function</span><span class=\"hljs-params\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">()</span>{</span>\r\n                <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.form.typeId = <span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"\"</span>;\r\n            },\r\n            cancel(){\r\n                 <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.form.typeId = <span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"\"</span>;   \r\n            },\r\n            change:<span class=\"hljs-function\" style=\"margin: 0px; padding: 0px;\"><span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">function</span><span class=\"hljs-params\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">()</span>{</span>\r\n                console.log(<span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.form.typeId)\r\n            },\r\n            edit:<span class=\"hljs-function\" style=\"margin: 0px; padding: 0px;\"><span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">function</span><span class=\"hljs-params\" style=\"margin: 0px; padding: 0px; color: rgb(102, 0, 102);\">()</span>{</span>\r\n                <span class=\"hljs-keyword\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">this</span>.form.typeId =<span class=\"hljs-string\" style=\"margin: 0px; padding: 0px; color: rgb(0, 136, 0);\">\"1\"</span>;\r\n            }\r\n        }\r\n    })\r\n</span><span class=\"hljs-tag\" style=\"margin: 0px; padding: 0px; color: rgb(0, 102, 102);\">&lt;/<span class=\"hljs-title\" style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 136);\">script</span>&gt;</span></code><ul class=\"pre-numbering\" style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 6px 0px 40px; list-style: none; position: absolute; width: 50px; background-color: rgb(238, 238, 238); top: 0px; left: 0px; border-right: 1px solid rgb(221, 221, 221); text-align: right;\"><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">1</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">2</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">3</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">4</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">5</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">6</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">7</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">8</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">9</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">10</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">11</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">12</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">13</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">14</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">15</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">16</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">17</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">18</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">19</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">20</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">21</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">22</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">23</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">24</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">25</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">26</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">27</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">28</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">29</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">30</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">31</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">32</li><li style=\"margin: 0px; padding: 0px 5px; list-style: none; color: rgb(153, 153, 153);\">33</li></ul></pre></div></div></article><div class=\"article_copyright\" style=\"margin: 0px 0px -20px; padding: 0px 20px 30px; border-bottom: 1px solid rgb(228, 235, 244); border-left: 1px solid rgb(228, 235, 244); border-right: 1px solid rgb(228, 235, 244); font-size: 14px; color: rgb(120, 128, 135); clear: both; overflow: hidden; font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, SimHei, Arial, SimSun;\">ת��http://blog.csdn.net/museions/article/details/77824361</div>', 'article', null, '', null, null, '1', null, '0', '1', 'bootdo', '2017-10-12 10:41:07', '2017-10-12 10:41:07');
INSERT INTO `blog_content` VALUES ('119', 'Java����Ա�����澭��ϼ���BAT��������С�׻�Ϊ���˵ȣ�', null, null, null, '<h1 class=\"title\" style=\"font-size: 34px; margin-top: 20px; margin-bottom: 0px; font-family: -apple-system, &quot;SF UI Display&quot;, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; font-weight: 700; line-height: 1.3; color: rgb(51, 51, 51); word-break: break-word !important;\">Java����Ա�����澭��ϼ���BAT��������С�׻�Ϊ���˵ȣ�</h1><div data-note-content=\"\" class=\"show-content\" style=\"color: rgb(47, 47, 47); font-size: 16px; line-height: 1.7; font-family: -apple-system, &quot;SF UI Text&quot;, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif; word-break: break-word !important;\"><blockquote style=\"padding-top: 20px; padding-bottom: 20px; margin-bottom: 25px; font-size: 16px; border-left-width: 6px; border-left-color: rgb(180, 180, 180); background-color: rgb(247, 247, 247); line-height: 30px; word-break: break-word !important;\"><ul style=\"padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">Cvte��ǰ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">С������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ɽwps����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ƴ���ѧ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ѹ�У��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ϳѻ�ƶ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�й�����it�з�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ϊ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ٶ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ѷ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����������ÿ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������Ƽ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����</li><li style=\"line-height: 30px; margin-bottom: 0px;\">Vivo</li></ul></blockquote><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">Cvte��ǰ��</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���������Ŀ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ܽ����˽�ô�������㷨����һ�����˽��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���߳��˽�ô��ʲô���̰߳�ȫ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">˵һ��������Ϥ�����ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ������Ŀ���õ�����Щ���ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java��hashmap��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap���̰߳�ȫ�ԣ�ʲô���̰߳�ȫ�ģ����ʵ���̰߳�ȫ</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨��Ƶ��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������Ŀ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Mysql�����ݿ����棬�����ص�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ģʽ�˽⣿��һ������Ϥ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">дһ������ģʽ������д����˫���������������Ϊʲô��Volatile��synchronize�Ƶ��������������ô����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ģʽ������Ŀ����ЩӦ�ã�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������ӳ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�Ը߸������˽�ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ļ����������Ŀ飿�������ش�ĸ߲�����Ȼ�����Թ�˵�������߸��صģ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�Ը߲������˽�ô��</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">��˵���в��ͣ�����������ʲô���ݣ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ���ܣ���ӵı�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">HashmapΪʲô��С��2���ݴ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����һ�º����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Arraylist��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����⣺����ж����ĳ�Ϯ��ϵͳ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���󹤳��͹�������ģʽ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ģʽ��˼��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">object����֪���ķ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����õ��˹���ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Forward��redirect������</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨��Ƶ��</h2><p style=\"margin-bottom: 25px; word-break: break-word !important;\">1�� ���ҽ���<br>2�� ��Ŀ����<br>3�� ��Ŀ�ܹ�<br>4�� ��Ŀ�ѵ�<br>5�� Synchronize�ؼ���Ϊʲôjdk1.5��Ч�������<br>6�� �̳߳ص�ʹ��ʱ��ע������<br>7�� Spring��autowire��resourse�ؼ��ֵ�����<br>8�� Hashmap��ԭ��<br>9�� Hashmap�Ĵ�СΪʲôָ��Ϊ2���ݴ�<br>10�� ��һ���߳�״̬ת��ͼ<br>11�� ��Ϣ�����˽�ô<br>12�� �ֲ�ʽ�˽�ô</p><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">����������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">volatile��synchronized</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����㷨�⣺һ���������飬����һ�����ֳ��ֵĴ���������������֮�ͣ���������� ����Ԫ�أ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������һ����һ�����飬�����и������ı�˳�������£���������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ�õ�ʲô���ݿ⣿���뼶��ÿ�����뼶�������ʲô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ��������mysql��ͬ��������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���������㷨�Ĺ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���˽�������ռ����� Cms�ռ����Ĺ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ƽʱ�õ���ʲô���ģʽ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ����������������ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ù�ʲôϵͳ��shellд���ű���</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">С������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����������д����redis������redis��mysql������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis��Ӧ�ó���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��jdk1.8֮��������Щ�Ż�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������յĹ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Jvm�Ĳ�������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ�е��Ż�</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��ɽwps����</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Java�������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��java���̵߳����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ�ĸ��뼶��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ģʽ�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���������ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���㷨��ʲô�˽⣿�����Ȼش��˶�̬�滮��������һ��dp��˼��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ŵ�˼�뽲һ��</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Tcp��ô��֤�ɿ����䣨�м䴩���˺ö�С���⣩</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Tcp��ӵ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������һ����ʱ�����ϵͳ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">֧����ת�ˣ������ʵ�֣�����Сʱ֪ͨת�˳ɹ��ģ����Թ����ûش����ӣ�����һֱûget���㣩</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����һ�³�����</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨��Ƶ��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ܶ�̬</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java�½��߳����ļ��ַ�ʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�̳߳ص�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������Դ��ô</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">ƴ���ѧ����</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ��棩</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨��һ�ö���������������һ�������ҵ����������ֵ��С����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����⣺���һ��ϵͳ���������ʱ����Ҫ�Ĵ����Ķ����ӵĹ��ܣ���α�֤�߲����������ƶ�����</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ��棩</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">����������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����һ��ֽ���������ʣ����д����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ȼ�����������Щ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����⣺���һ��ϵͳ����¼qq�û�ǰһ��ĵ�¼״̬���ṩ16g�ڴ��2tb��Ӳ�̣�Ҫ������ѯָ��qq�ŵ�ǰһ��ĵ�¼״̬�����ٲ�ѯO(1)���Ӷ�</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">�ѹ�У��</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ�����</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨���������������A��B�ֱ���m��n�������ҵ����������������λ���������õĶ��֣�ʱ�临�Ӷ�ΪO(log (m+n))��������Թٲ����⣬���ù鲢��˼������ʱ�临�Ӷ���ʵ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����������</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">Ϳѻ�ƶ�</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ʹ�õ�ע������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java�������ջ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java��finalize��finally��final�����ؼ��ֵ������Ӧ�ó���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">String����Ա��̳�ô<br>��˺�㷨����������һ��רҵ��������׼������һ���ִ�ٷ��ݡ�ÿ�����Ӷ�������ض�����Ǯ�������ٵ�ΨһԼ�������ǣ����ڵķ���װ���໥��ϵ�ķ���ϵͳ���� �����ڵ���������ͬһ�챻���ʱ����ϵͳ���Զ�������<br>����һ���Ǹ������б���ʾÿ�������д�ŵ�Ǯ�� ��һ�㣬�������ȥ��٣��������Եõ�����Ǯ �ڲ���������װ�õ�����¡�</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����Ϸ���˽�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�㷨�⣺��һ���������飬�ҵ�������ʹ�����ǵĺ͵���һ���������� target��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis��Ӧ��</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">�й�����it�з�����</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ���õ�ʲô������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�Լ�дһ��tomcat�������������ôд</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ֲ�ʽ�������������Щ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô���sessionһ���Ի��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis�����ƺ��ص�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">һǧ���û�������������ô���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ɹ����û���10��redis�治����ô����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����Ŀ�е��ѵ�</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����spring�е���Ϥ��ע��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ʵ��autowireע��Ĺ���������ʵ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis��mysql������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis�ĳ־û�����Щ��ʽ������ԭ��</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">����</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">רҵ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���˽�����ģʽ��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java collection�࣬���ϣ����������˽�ģ�˵ʵ��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java�̳߳ص�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����������ʵ���Ҵ���ʲôˮƽ</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">�ۺ����ԣ��ֳ���</h2><p style=\"margin-bottom: 25px; word-break: break-word !important;\">˵�õ��ۺ����Դ������أ�<br>1�� ���ҽ���<br>2�� ��Ŀ����<br>3�� ˵һ����֪�������ģʽ<br>4�� ��һ������ģʽ��umlͼ<br>5�� Java���̵߳����<br>6�� �ڴ�������ʲô<br>7�� ���ݿ�����<br>8�� ��Ŀ�е��Ż�<br>9�� Ȼ��ʼ������<br>10�� ���ȱ�㣬���ϲ��ʲô�����ˣ���ļ�ͥ�ȵ�</p><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��Ϊ</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ�ܹ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀһ��������ִ�����̣��������Ǹ�java�ģ������Թ��Ǹ�c�ģ�����ȫ�����ģ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ�Ż�</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô������Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ƽ���İ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�о����Թ�Ҳ���Ǹ�java�ģ���������һ������</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����Щ��˾��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����Щoffer��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ĵ����棬���ǿ�ʼ�����΢���Ϻܻ�Ķ����ϰ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ó����ۣ��Һ����Թٶ����ö����ϰ����������⣬������Ϸ����Ҫƫִ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�㲩����Ҫ�ķ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���̲߳������˽�ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ��countDownLatch</li></ol><p style=\"margin-bottom: 25px; word-break: break-word !important;\">��������20���Ӱ��Ծ������ˣ�һ�ּ�����</p><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��������</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�绰��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�˽�redisԴ��ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�˽�redis��Ⱥô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">hashmap����Ϊʲô��2���ݴ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">hashset��Դ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">object����֪���ķ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">hashcode��equals</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����д��hashcode��equalsô��Ҫע��ʲô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������һ��ѧ���࣬��ѧ�ź�������������hashcode������д��ʱ��ֻ��ѧ�Ų�����㣬�����ʲô�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��set����putһ��ѧ������Ȼ�����ѧ�������ѧ�Ÿ��ˣ���put��ȥ�����ԷŽ�setô��������Ϊʲô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis�ĳ־û�������Щ��ʽ��ԭ����ʲô��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ���ȶ��������㷨�Ͳ��ȶ��������㷨</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ�¿��������˼��</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��һ�����ݵ�acid</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ʲô��һ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ʲô�Ǹ�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Mysql�ĸ��뼶��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ÿ�����뼶������ν��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">MysqlҪ����nextkey����������ôд</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java���ڴ�ģ�ͣ���������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�̳߳صĲ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ÿ����������һ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ȼ�����Թ�������ÿ�������������Ǹ��̣߳����������������̳߳�ִ�е�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Nio��IO��ʲô����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Nio��aio������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Spring��aop��ôʵ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Spring��aop����Щʵ�ַ�ʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��̬�����ʵ�ַ�ʽ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Linux�˽�ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô�鿴ϵͳ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Cpu load�Ĳ������Ϊ4������һ������ϵͳ����ʲô���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Linux�����Ҵ����������ļ�������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Linux����β鿴ϵͳ��־�ļ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨��leeetcodeԭ�� 22��Generate Parentheses������ n �����ţ���дһ�������Խ��������µ�������ϣ�������������Ͻ����</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><p style=\"margin-bottom: 25px; word-break: break-word !important;\">����û��ô�ʼ��������˺ܶ༼�������������</p><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô������Ŀ��Ա</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������һ��ʱ����ι�ͨ��˵��������Ա�����ٸ�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô��֤��Ŀ�Ľ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�Ǿ۴������;۴�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ʹ��ע������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ӵײ��������ƥ��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Mysql�������������Ż�ô�����Զ�����˳��ô���ĸ��汾��ʼ�Ż���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis��Ӧ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis�ĳ־û��ķ�ʽ��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ѡ�ͣ�һ���¼�����һ���ȶ��ľɼ����������ôѡ��ѡ��Ŀ�������Щ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">˵��ӡ����������ŵ��������Ŷӵ���ƪ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����ѧʲô�¼���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ôȥ�Ӵ�һ���¼�����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ῴ��Щ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ôѡ��Ҫ������</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">�ٶ�</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java�еĶ�̬</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Object���µķ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Finalize�����ú�ʹ�ó���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashcode��equals</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ΪʲôҪͬʱ��дhashcode��equals</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ͬʱ��д�������Щ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hashmap��α��̰߳�ȫ��ÿ�ַ�ʽ����ȱ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������ջ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Jvm�Ĳ�����֪����˵һ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ģʽ�˽��˵һ�°�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺һ������ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���������˼�뽲һ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������飬ģ����ŵĹ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��д����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����⣬һ��ͼ��ݹ���ϵͳ�����ݿ���ô��ƣ������Լ���</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis���ص�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ֲ�ʽ�����˽�ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������Ļ��ƣ�����Щ��ʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨����ת������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨��ʵ������΢���ӽṹ�����ݽṹ������һϵ�и��ӹ�ϵ�����һ������΢�����۵ĸ��ӽṹͼ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��дjava���߳�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��дjava��soeket��̣�����˺Ϳͻ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���̼��ͨ�ŷ�ʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨�� ��¥�ݣ�д��״̬ת�Ʒ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����⣺ʱ�����ʲôʱ���غ�</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">���棨�ֳ���</h2><p style=\"margin-bottom: 25px; word-break: break-word !important;\">�����������Թٲ���java���Ҳ���c�Ӽӣ�����ȫ������</p><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨������һ�����������Σ��ҵ��Ӷ������ײ�����С·���͡�ÿһ�������ƶ�������һ�е����������ϡ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\"></li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ȼ������������������չ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������������·��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ݹ�������е�·��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ģʽ��һ����Ϥ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�᲻���������ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���߳���������ΪʲôҪ��while����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ʲô����</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��Ѷ</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hibernate�����ã�������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���̵߳���⣬��α�֤�̰߳�ȫ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">mysql���ݿ�����������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����⣺ǧ���û���������δ���߲�����������һ�����ӣ�ָ��ǰһ�������ɹ����û������������ϵͳ�����ݿ�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����̨������������ķ�������ÿ��������200�ĸ��أ�ϵͳ����ô���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��˺�㷨����С�����������Լ��</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">����</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ��һ���������������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ���Ż�</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hibernate��mybatis������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ϊʲô��ssh���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Mysql�����ֱ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Redis��memcache ������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ϊʲôѡ��redis</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Java��full gc</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Full gc�ᵼ��ʲô����</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">�����������ÿ�</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ֲ�ʽ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">װ����ģʽ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����Щoffer</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ϊʲô����������</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">��������Ƽ�</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">дһ��������������ϲ���һ����������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">������ʲô��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô�������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">http��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Ϊʲô���ؾ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ôʵ�ָ��ؾ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������ô�죿�����ȱ��ݻ���ʲô����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�������spring����⣬��Ҫ��ioc��aop��������</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">����</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�㷨���ҳ�����������ȵ������������������ݽṹ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�㷨������һ�����֣�һ�����飬�ҳ���������ӵ������������ĺͣ����������ݽṹ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�㷨������ж�һ�����ǲ�����һ����������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ν���������ʵĴ���</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">����</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ�棨�ֳ���</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">I++������ô��֤�̰߳�ȫ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����⣺���һ���µ�ϵͳ���µ��ɹ�����Ը��û����Ż�ȯ</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�����泡���⣺���������ˣ��Ż�ȯ��û����ô��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������ô��ô��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô��֤һ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">�ֲ�ʽ����֪��ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ֲܷ�ʽ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ְҵ�滮</li></ol><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">����</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Nio��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Channel��buffer</li><li style=\"line-height: 30px; margin-bottom: 10px;\">directBuffer��buffer������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">nio��aio������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">����ʵ��ԭ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��ô�������������һ��������</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���滹û���µ����棬������������ô��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ������ô��</li></ol><h1 style=\"font-size: 26px; margin-top: 0px; margin-bottom: 15px; font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); text-rendering: optimizeLegibility;\">Vivo</h1><h2 style=\"font-family: inherit; font-weight: 700; line-height: 1.7; color: rgb(47, 47, 47); margin: 0px 0px 15px; font-size: 24px; text-rendering: optimizeLegibility;\">һ��</h2><ol style=\"margin-bottom: 20px; padding: 0px; margin-left: 22px; word-break: break-word !important;\"><li style=\"line-height: 30px; margin-bottom: 10px;\">���ҽ���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��Ŀ����</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Hibernate��batch����������ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Jquery�ù�ô</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Extjs����ȱ��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��û����չ��extjs</li><li style=\"line-height: 30px; margin-bottom: 10px;\">��д��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ʲôʱ���ö���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">ʲôʱ����д��</li><li style=\"line-height: 30px; margin-bottom: 10px;\">Cas��ԭ��ʹ�ó���</li><li style=\"line-height: 30px; margin-bottom: 10px;\">���ݿ��ƿ��</li></ol><div><br></div><div><br></div><div>ת��<a href=\"http://www.jianshu.com//p/72712546648b\" target=\"_blank\">http://www.jianshu.com//p/72712546648b</a></div></div>', 'article', null, '', null, null, '1', null, '0', '1', 'Bootdo', '2017-10-13 13:45:20', '2017-10-13 13:45:20');

-- ----------------------------
-- Table structure for `oa_notify`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '���',
  `type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `files` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '״̬',
  `create_by` bigint(20) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '������',
  `update_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '��ע��Ϣ',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT 'ɾ�����',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='֪ͨͨ��';

-- ----------------------------
-- Records of oa_notify
-- ----------------------------
INSERT INTO `oa_notify` VALUES ('41', '3', 'ʮ�Ŵ��ٿ���', 'ʮ�Ŵ��ٿ��ˣ���Ȼû������', '', '1', '1', null, null, '2017-10-10 17:21:11', '', null);
INSERT INTO `oa_notify` VALUES ('42', '3', 'ƻ���������ֻ���', '��ȫ������iphoneX', '', '1', '1', null, null, '2017-10-10 18:51:14', '', null);
INSERT INTO `oa_notify` VALUES ('43', '3', 'ʮ�Ŵ�Ҫ����ƶ���˿�', '�һ�ֻ��������Ļ�ͷ��', '', '1', '1', null, null, '2017-10-11 09:56:35', '', null);

-- ----------------------------
-- Table structure for `oa_notify_record`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '���',
  `notify_id` bigint(20) DEFAULT NULL COMMENT '֪ͨͨ��ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '������',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '�Ķ����',
  `read_date` date DEFAULT NULL COMMENT '�Ķ�ʱ��',
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_id`),
  KEY `oa_notify_record_read_flag` (`is_read`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='֪ͨͨ�淢�ͼ�¼';

-- ----------------------------
-- Records of oa_notify_record
-- ----------------------------
INSERT INTO `oa_notify_record` VALUES ('18', '41', '1', '0', null);
INSERT INTO `oa_notify_record` VALUES ('19', '42', '1', '0', null);
INSERT INTO `oa_notify_record` VALUES ('20', '43', '136', '0', null);
INSERT INTO `oa_notify_record` VALUES ('21', '43', '133', '0', null);
INSERT INTO `oa_notify_record` VALUES ('22', '43', '130', '0', null);
INSERT INTO `oa_notify_record` VALUES ('23', '43', '1', '0', null);

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '�ϼ�����ID��һ������Ϊ0',
  `name` varchar(50) DEFAULT NULL COMMENT '��������',
  `order_num` int(11) DEFAULT NULL COMMENT '����',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '�Ƿ�ɾ��  -1����ɾ��  0������',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='���Ź���';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '�з���', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '�аlһ��', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '�з�����', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '���۲�', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '����һ��', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '��Ʒ��', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '��Ʒһ��', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '���Բ�', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '����һ��', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '���Զ���', '2', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '���',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '��ǩ��',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '����ֵ',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '��������',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '�������',
  `create_by` int(64) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `update_by` bigint(64) DEFAULT NULL COMMENT '������',
  `update_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '��ע��Ϣ',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT 'ɾ�����',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='�ֵ��';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '����', '0', 'del_flag', 'ɾ�����', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '��ʾ', '1', 'show_hide', '��ʾ/����', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '����', '0', 'show_hide', '��ʾ/����', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '��', '1', 'yes_no', '��/��', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '��', '0', 'yes_no', '��/��', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '��ɫ', 'red', 'color', '��ɫֵ', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '��ɫ', 'green', 'color', '��ɫֵ', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '��ɫ', 'blue', 'color', '��ɫֵ', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '��ɫ', 'yellow', 'color', '��ɫֵ', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '��ɫ', 'orange', 'color', '��ɫֵ', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', 'Ĭ������', 'default', 'theme', '���ⷽ��', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '��������', 'cerulean', 'theme', '���ⷽ��', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '��ɫ����', 'readable', 'theme', '���ⷽ��', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '��ɫ����', 'united', 'theme', '���ⷽ��', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat����', 'flat', 'theme', '���ⷽ��', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '����', '1', 'sys_area_type', '��������', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', 'ʡ�ݡ�ֱϽ��', '2', 'sys_area_type', '��������', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '����', '3', 'sys_area_type', '��������', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '����', '4', 'sys_area_type', '��������', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '��˾', '1', 'sys_office_type', '��������', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '����', '2', 'sys_office_type', '��������', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', 'С��', '3', 'sys_office_type', '��������', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '����', '4', 'sys_office_type', '��������', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '�ۺϲ�', '1', 'sys_office_common', '���ͨ�ò���', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '������', '2', 'sys_office_common', '���ͨ�ò���', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '������', '3', 'sys_office_common', '���ͨ�ò���', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', 'һ��', '1', 'sys_office_grade', '�����ȼ�', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '����', '2', 'sys_office_grade', '�����ȼ�', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '����', '3', 'sys_office_grade', '�����ȼ�', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '�ļ�', '4', 'sys_office_grade', '�����ȼ�', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '��������', '1', 'sys_data_scope', '���ݷ�Χ', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '���ڹ�˾����������', '2', 'sys_data_scope', '���ݷ�Χ', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '���ڹ�˾����', '3', 'sys_data_scope', '���ݷ�Χ', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '���ڲ��ż���������', '4', 'sys_data_scope', '���ݷ�Χ', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '���ڲ�������', '5', 'sys_data_scope', '���ݷ�Χ', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '����������', '8', 'sys_data_scope', '���ݷ�Χ', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '����ϸ����', '9', 'sys_data_scope', '���ݷ�Χ', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', 'ϵͳ����', '1', 'sys_user_type', '�û�����', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '���ž���', '2', 'sys_user_type', '�û�����', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '��ͨ�û�', '3', 'sys_user_type', '�û�����', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '��������', 'basic', 'cms_theme', 'վ������', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '��ɫ����', 'blue', 'cms_theme', 'վ������', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '��ɫ����', 'red', 'cms_theme', 'վ������', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '����ģ��', 'article', 'cms_module', '��Ŀģ��', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', 'ͼƬģ��', 'picture', 'cms_module', '��Ŀģ��', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '����ģ��', 'download', 'cms_module', '��Ŀģ��', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '����ģ��', 'link', 'cms_module', '��Ŀģ��', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', 'ר��ģ��', 'special', 'cms_module', '��Ŀģ��', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', 'Ĭ��չ�ַ�ʽ', '0', 'cms_show_modes', 'չ�ַ�ʽ', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '����Ŀ�����б�', '1', 'cms_show_modes', 'չ�ַ�ʽ', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '��Ŀ��һ������', '2', 'cms_show_modes', 'չ�ַ�ʽ', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '����', '0', 'cms_del_flag', '����״̬', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', 'ɾ��', '1', 'cms_del_flag', '����״̬', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '���', '2', 'cms_del_flag', '����״̬', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '��ҳ����ͼ', '1', 'cms_posid', '�Ƽ�λ', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '��Ŀҳ�����Ƽ�', '2', 'cms_posid', '�Ƽ�λ', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '��ѯ', '1', 'cms_guestbook', '���԰����', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '����', '2', 'cms_guestbook', '���԰����', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', 'Ͷ��', '3', 'cms_guestbook', '���԰����', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '����', '4', 'cms_guestbook', '���԰����', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '����', '1', 'oa_leave_type', '�������', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '����', '2', 'oa_leave_type', '�������', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '�¼�', '3', 'oa_leave_type', '�������', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '����', '4', 'oa_leave_type', '�������', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '���', '5', 'oa_leave_type', '�������', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '������־', '1', 'sys_log_type', '��־����', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '�쳣��־', '2', 'sys_log_type', '��־����', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '�������', 'leave', 'act_type', '��������', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '������������', 'test_audit', 'act_type', '��������', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '����1', '1', 'act_category', '���̷���', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '����2', '2', 'act_category', '���̷���', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '��ɾ�Ĳ�', 'crud', 'gen_category', '�������ɷ���', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '��ɾ�Ĳ飨�����ӱ�', 'crud_many', 'gen_category', '�������ɷ���', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '���ṹ', 'tree', 'gen_category', '�������ɷ���', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '��ѯ��ʽ', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '��ѯ��ʽ', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '��ѯ��ʽ', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '��ѯ��ʽ', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '��ѯ��ʽ', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '��ѯ��ʽ', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '��ѯ��ʽ', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '��ѯ��ʽ', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '�ı���', 'input', 'gen_show_type', '�ֶ����ɷ���', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '�ı���', 'textarea', 'gen_show_type', '�ֶ����ɷ���', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '������', 'select', 'gen_show_type', '�ֶ����ɷ���', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '��ѡ��', 'checkbox', 'gen_show_type', '�ֶ����ɷ���', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '��ѡ��', 'radiobox', 'gen_show_type', '�ֶ����ɷ���', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '����ѡ��', 'dateselect', 'gen_show_type', '�ֶ����ɷ���', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '��Աѡ��', 'userselect', 'gen_show_type', '�ֶ����ɷ���', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '����ѡ��', 'officeselect', 'gen_show_type', '�ֶ����ɷ���', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '����ѡ��', 'areaselect', 'gen_show_type', '�ֶ����ɷ���', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java����', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java����', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '���־ò�', 'dao', 'gen_category', '�������ɷ���\0\0', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '��', '1', 'sex', '�Ա�', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', 'Ů', '2', 'sex', '�Ա�', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java����', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java����', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java����', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java����', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '����ͨ��', '1', 'oa_notify_type', '֪ͨͨ������', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '����ͨ��', '2', 'oa_notify_type', '֪ͨͨ������', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '�ͨ��', '3', 'oa_notify_type', '֪ͨͨ������', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '�ݸ�', '0', 'oa_notify_status', '֪ͨͨ��״̬', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '����', '1', 'oa_notify_status', '֪ͨͨ��״̬', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', 'δ��', '0', 'oa_notify_read', '֪ͨͨ��״̬', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '�Ѷ�', '1', 'oa_notify_read', '֪ͨͨ��״̬', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '�ݸ�', '0', 'oa_notify_status', '֪ͨͨ��״̬', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', 'ɾ��', '0', 'del_flag', 'ɾ�����', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '����', 'about', 'blog_type', '��������', null, null, null, null, null, null, 'ȫurl��:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '����', 'communication', 'blog_type', '��������', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '����', 'article', 'blog_type', '��������', null, null, null, null, null, null, '', '');

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '�ļ�����',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL��ַ',
  `create_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COMMENT='�ļ��ϴ�';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('110', '0', '/files/d64a62e3-6821-48f1-bac6-a1b9945f4afb.jpg', '2017-10-14 16:20:17');
INSERT INTO `sys_file` VALUES ('111', '0', '/files/aa2c3dc6-495f-48cc-8e12-446eceb2535e.jpg', '2017-10-14 16:20:21');
INSERT INTO `sys_file` VALUES ('114', '0', '/files/84c29777-11bc-44b9-818d-859f2d04d417.jpg', '2017-10-20 09:27:18');
INSERT INTO `sys_file` VALUES ('116', '0', '/files/7e38b411-1c72-413a-b9e7-2a367f111856.jpg', '2017-10-20 11:53:42');
INSERT INTO `sys_file` VALUES ('117', '0', '/files/40073f7e-82ec-43f2-b9d3-fd9068916d4b.jpg', '2017-10-20 11:53:47');
INSERT INTO `sys_file` VALUES ('118', '0', '/files/a973499e-3ec7-4d43-8a52-b6f6517c77e3.jpg', '2017-10-20 11:53:52');
INSERT INTO `sys_file` VALUES ('125', '0', '/files/e2901e59-2e65-45a0-9fd8-284c88133cdd.jpg', '2017-10-20 11:54:20');
INSERT INTO `sys_file` VALUES ('126', '0', '/files/1ca91ae9-799e-4b7b-a72e-680825688677.jpg', '2017-10-20 11:54:24');
INSERT INTO `sys_file` VALUES ('127', '0', '/files/1a42a630-5186-44c1-8378-5f974652d7c8.jpg', '2017-10-20 13:20:49');
INSERT INTO `sys_file` VALUES ('128', '0', '/files/f4f730a9-6bd6-41de-aa05-a0ba3eac59ae.jpg', '2017-10-20 13:21:54');
INSERT INTO `sys_file` VALUES ('131', '0', '/files/4f5f32fd-f5ed-48c5-90ac-f71c9d7ebc24.jpg', '2017-10-20 13:50:00');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '�û�id',
  `username` varchar(50) DEFAULT NULL COMMENT '�û���',
  `operation` varchar(50) DEFAULT NULL COMMENT '�û�����',
  `time` int(11) DEFAULT NULL COMMENT '��Ӧʱ��',
  `method` varchar(200) DEFAULT NULL COMMENT '���󷽷�',
  `params` varchar(5000) DEFAULT NULL COMMENT '�������',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP��ַ',
  `gmt_create` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7034 DEFAULT CHARSET=utf8 COMMENT='ϵͳ��־';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '���˵�ID��һ���˵�Ϊ0',
  `name` varchar(50) DEFAULT NULL COMMENT '�˵�����',
  `url` varchar(200) DEFAULT NULL COMMENT '�˵�URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '��Ȩ(����ö��ŷָ����磺user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '����   0��Ŀ¼   1���˵�   2����ť',
  `icon` varchar(50) DEFAULT NULL COMMENT '�˵�ͼ��',
  `order_num` int(11) DEFAULT NULL COMMENT '����',
  `gmt_create` datetime DEFAULT NULL COMMENT '����ʱ��',
  `gmt_modified` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='�˵�����';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '��������', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('2', '3', 'ϵͳ�˵�', 'sys/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO `sys_menu` VALUES ('3', '0', 'ϵͳ����', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('6', '3', '�û�����', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('7', '3', '��ɫ����', 'sys/role', 'sys:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO `sys_menu` VALUES ('12', '6', '����', '', 'sys:user:add', '2', '', '0', '2017-08-14 10:51:35', null);
INSERT INTO `sys_menu` VALUES ('13', '6', '�༭', '', 'sys:user:edit', '2', '', '0', '2017-08-14 10:52:06', null);
INSERT INTO `sys_menu` VALUES ('14', '6', 'ɾ��', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO `sys_menu` VALUES ('15', '7', '����', '', 'sys:role:add', '2', '', '0', '2017-08-14 10:56:37', null);
INSERT INTO `sys_menu` VALUES ('20', '2', '����', '', 'sys:menu:add', '2', '', '0', '2017-08-14 10:59:32', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '�༭', '', 'sys:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null);
INSERT INTO `sys_menu` VALUES ('22', '2', 'ɾ��', '', 'sys:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null);
INSERT INTO `sys_menu` VALUES ('24', '6', '����ɾ��', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO `sys_menu` VALUES ('25', '6', 'ͣ��', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO `sys_menu` VALUES ('26', '6', '��������', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO `sys_menu` VALUES ('27', '91', 'ϵͳ��־', 'common/log', 'common:log', '1', 'fa fa-warning', '0', '2017-08-14 22:11:53', null);
INSERT INTO `sys_menu` VALUES ('28', '27', 'ˢ��', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO `sys_menu` VALUES ('29', '27', 'ɾ��', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO `sys_menu` VALUES ('30', '27', '���', null, 'sys:log:clear', '2', null, '0', '2017-08-14 22:31:02', null);
INSERT INTO `sys_menu` VALUES ('48', '77', '��������', 'common/generator', 'common:generator', '1', 'fa fa-code', '3', null, null);
INSERT INTO `sys_menu` VALUES ('49', '0', '���͹���', '', '', '0', 'fa fa-rss', '6', null, null);
INSERT INTO `sys_menu` VALUES ('50', '49', '�����б�', 'blog/bContent', 'blog:bContent:bContent', '1', 'fa fa-file-image-o', '1', null, null);
INSERT INTO `sys_menu` VALUES ('51', '50', '����', '', 'blog:bContent:add', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('55', '7', '�༭', '', 'sys:role:edit', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', 'ɾ��', '', 'sys:role:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('57', '91', '���м��', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', null, null);
INSERT INTO `sys_menu` VALUES ('58', '50', '�༭', '', 'blog:bContent:edit', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('59', '50', 'ɾ��', '', 'blog:bContent:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('60', '50', '����ɾ��', '', 'blog:bContent:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '����ɾ��', '', 'sys:menu:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('62', '7', '����ɾ��', '', 'sys:role:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('68', '49', '��������', '/blog/bContent/add', 'blog:bContent:add', '1', 'fa fa-edit', '0', null, null);
INSERT INTO `sys_menu` VALUES ('71', '1', '�ļ�����', '/common/sysFile', 'common:sysFile:sysFile', '1', 'fa fa-folder-open', '2', null, null);
INSERT INTO `sys_menu` VALUES ('72', '77', '�ƻ�����', 'common/job', 'common:taskScheduleJob', '1', 'fa fa-hourglass-1', '4', null, null);
INSERT INTO `sys_menu` VALUES ('73', '3', '���Ź���', '/system/sysDept', 'system:sysDept:sysDept', '1', 'fa fa-users', '3', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '����', '/system/sysDept/add', 'system:sysDept:add', '2', null, '1', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '�h��', 'system/sysDept/remove', 'system:sysDept:remove', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '�༭', '/system/sysDept/edit', 'system:sysDept:edit', '2', null, '3', null, null);
INSERT INTO `sys_menu` VALUES ('77', '0', 'ϵͳ����', '', '', '0', 'fa fa-gear', '4', null, null);
INSERT INTO `sys_menu` VALUES ('78', '1', '�����ֵ�', '/common/sysDict', 'common:sysDict:sysDict', '1', 'fa fa-book', '1', null, null);
INSERT INTO `sys_menu` VALUES ('79', '78', '����', '/common/sysDict/add', 'common:sysDict:add', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('80', '78', '�༭', '/common/sysDict/edit', 'common:sysDict:edit', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('81', '78', 'ɾ��', '/common/sysDict/remove', 'common:sysDict:remove', '2', '', '3', null, null);
INSERT INTO `sys_menu` VALUES ('83', '78', '����ɾ��', '/common/sysDict/batchRemove', 'common:sysDict:batchRemove', '2', '', '4', null, null);
INSERT INTO `sys_menu` VALUES ('84', '0', '�칫����', '', '', '0', 'fa fa-laptop', '5', null, null);
INSERT INTO `sys_menu` VALUES ('85', '84', '֪ͨ����', 'oa/notify', 'oa:notify:notify', '1', 'fa fa-pencil-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('86', '85', '����', 'oa/notify/add', 'oa:notify:add', '2', 'fa fa-plus', '1', null, null);
INSERT INTO `sys_menu` VALUES ('87', '85', '�༭', 'oa/notify/edit', 'oa:notify:edit', '2', 'fa fa-pencil-square-o', '2', null, null);
INSERT INTO `sys_menu` VALUES ('88', '85', 'ɾ��', 'oa/notify/remove', 'oa:notify:remove', '2', 'fa fa-minus', null, null, null);
INSERT INTO `sys_menu` VALUES ('89', '85', '����ɾ��', 'oa/notify/batchRemove', 'oa:notify:batchRemove', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('90', '84', '�ҵ�֪ͨ', 'oa/notify/selfNotify', '', '1', 'fa fa-envelope-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('91', '0', 'ϵͳ���', '', '', '0', 'fa fa-video-camera', '5', null, null);
INSERT INTO `sys_menu` VALUES ('92', '91', '�����û�', 'sys/online', '', '1', 'fa fa-user', null, null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '��ɫ����',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '��ɫ��ʶ',
  `remark` varchar(100) DEFAULT NULL COMMENT '��ע',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '�����û�id',
  `gmt_create` datetime DEFAULT NULL COMMENT '����ʱ��',
  `gmt_modified` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='��ɫ';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '�����û���ɫ', 'admin', 'ӵ�����Ȩ��', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES ('48', '��ʯ��Ա', null, '����1w��', null, null, null);
INSERT INTO `sys_role` VALUES ('49', '�׽��Ա', null, '����5000����', null, null, null);
INSERT INTO `sys_role` VALUES ('52', '������Ա', null, '������ǧ����', null, null, null);
INSERT INTO `sys_role` VALUES ('56', '��ͨ�û�', null, '��ͨ�û�', null, null, null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '��ɫID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '�˵�ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2557 DEFAULT CHARSET=utf8 COMMENT='��ɫ��˵���Ӧ��ϵ';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '44', '1');
INSERT INTO `sys_role_menu` VALUES ('368', '44', '32');
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33');
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34');
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35');
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28');
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29');
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30');
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38');
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4');
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27');
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38');
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3');
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20');
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21');
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22');
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23');
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11');
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12');
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13');
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14');
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24');
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25');
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26');
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15');
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2');
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6');
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7');
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38');
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42');
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38');
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39');
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40');
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41');
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4');
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32');
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33');
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34');
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35');
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27');
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28');
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29');
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30');
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53');
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2');
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6');
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7');
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3');
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50');
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49');
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1');
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28');
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29');
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30');
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27');
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57');
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71');
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48');
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72');
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1');
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7');
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55');
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56');
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62');
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15');
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2');
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61');
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20');
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21');
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22');
INSERT INTO `sys_role_menu` VALUES ('1875', '49', '12');
INSERT INTO `sys_role_menu` VALUES ('1876', '49', '13');
INSERT INTO `sys_role_menu` VALUES ('1877', '49', '14');
INSERT INTO `sys_role_menu` VALUES ('1878', '49', '24');
INSERT INTO `sys_role_menu` VALUES ('1879', '49', '25');
INSERT INTO `sys_role_menu` VALUES ('1880', '49', '26');
INSERT INTO `sys_role_menu` VALUES ('1881', '49', '61');
INSERT INTO `sys_role_menu` VALUES ('1882', '49', '20');
INSERT INTO `sys_role_menu` VALUES ('1883', '49', '21');
INSERT INTO `sys_role_menu` VALUES ('1884', '49', '22');
INSERT INTO `sys_role_menu` VALUES ('1885', '49', '74');
INSERT INTO `sys_role_menu` VALUES ('1886', '49', '75');
INSERT INTO `sys_role_menu` VALUES ('1887', '49', '76');
INSERT INTO `sys_role_menu` VALUES ('1888', '49', '6');
INSERT INTO `sys_role_menu` VALUES ('1889', '49', '2');
INSERT INTO `sys_role_menu` VALUES ('1890', '49', '73');
INSERT INTO `sys_role_menu` VALUES ('2072', '52', '77');
INSERT INTO `sys_role_menu` VALUES ('2073', '52', '49');
INSERT INTO `sys_role_menu` VALUES ('2074', '52', '3');
INSERT INTO `sys_role_menu` VALUES ('2075', '52', '72');
INSERT INTO `sys_role_menu` VALUES ('2076', '52', '48');
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68');
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60');
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59');
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58');
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51');
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50');
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49');
INSERT INTO `sys_role_menu` VALUES ('2243', '48', '72');
INSERT INTO `sys_role_menu` VALUES ('2247', '63', '-1');
INSERT INTO `sys_role_menu` VALUES ('2248', '63', '84');
INSERT INTO `sys_role_menu` VALUES ('2249', '63', '85');
INSERT INTO `sys_role_menu` VALUES ('2250', '63', '88');
INSERT INTO `sys_role_menu` VALUES ('2251', '63', '87');
INSERT INTO `sys_role_menu` VALUES ('2252', '64', '84');
INSERT INTO `sys_role_menu` VALUES ('2253', '64', '89');
INSERT INTO `sys_role_menu` VALUES ('2254', '64', '88');
INSERT INTO `sys_role_menu` VALUES ('2255', '64', '87');
INSERT INTO `sys_role_menu` VALUES ('2256', '64', '86');
INSERT INTO `sys_role_menu` VALUES ('2257', '64', '85');
INSERT INTO `sys_role_menu` VALUES ('2258', '65', '89');
INSERT INTO `sys_role_menu` VALUES ('2259', '65', '88');
INSERT INTO `sys_role_menu` VALUES ('2260', '65', '86');
INSERT INTO `sys_role_menu` VALUES ('2262', '67', '48');
INSERT INTO `sys_role_menu` VALUES ('2263', '68', '88');
INSERT INTO `sys_role_menu` VALUES ('2264', '68', '87');
INSERT INTO `sys_role_menu` VALUES ('2265', '69', '89');
INSERT INTO `sys_role_menu` VALUES ('2266', '69', '88');
INSERT INTO `sys_role_menu` VALUES ('2267', '69', '86');
INSERT INTO `sys_role_menu` VALUES ('2268', '69', '87');
INSERT INTO `sys_role_menu` VALUES ('2269', '69', '85');
INSERT INTO `sys_role_menu` VALUES ('2270', '69', '84');
INSERT INTO `sys_role_menu` VALUES ('2271', '70', '85');
INSERT INTO `sys_role_menu` VALUES ('2272', '70', '89');
INSERT INTO `sys_role_menu` VALUES ('2273', '70', '88');
INSERT INTO `sys_role_menu` VALUES ('2274', '70', '87');
INSERT INTO `sys_role_menu` VALUES ('2275', '70', '86');
INSERT INTO `sys_role_menu` VALUES ('2276', '70', '84');
INSERT INTO `sys_role_menu` VALUES ('2277', '71', '87');
INSERT INTO `sys_role_menu` VALUES ('2278', '72', '59');
INSERT INTO `sys_role_menu` VALUES ('2279', '73', '48');
INSERT INTO `sys_role_menu` VALUES ('2280', '74', '88');
INSERT INTO `sys_role_menu` VALUES ('2281', '74', '87');
INSERT INTO `sys_role_menu` VALUES ('2282', '75', '88');
INSERT INTO `sys_role_menu` VALUES ('2283', '75', '87');
INSERT INTO `sys_role_menu` VALUES ('2284', '76', '85');
INSERT INTO `sys_role_menu` VALUES ('2285', '76', '89');
INSERT INTO `sys_role_menu` VALUES ('2286', '76', '88');
INSERT INTO `sys_role_menu` VALUES ('2287', '76', '87');
INSERT INTO `sys_role_menu` VALUES ('2288', '76', '86');
INSERT INTO `sys_role_menu` VALUES ('2289', '76', '84');
INSERT INTO `sys_role_menu` VALUES ('2292', '78', '88');
INSERT INTO `sys_role_menu` VALUES ('2293', '78', '87');
INSERT INTO `sys_role_menu` VALUES ('2294', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2295', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2296', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2308', '80', '87');
INSERT INTO `sys_role_menu` VALUES ('2309', '80', '86');
INSERT INTO `sys_role_menu` VALUES ('2310', '80', '-1');
INSERT INTO `sys_role_menu` VALUES ('2311', '80', '84');
INSERT INTO `sys_role_menu` VALUES ('2312', '80', '85');
INSERT INTO `sys_role_menu` VALUES ('2328', '79', '72');
INSERT INTO `sys_role_menu` VALUES ('2329', '79', '48');
INSERT INTO `sys_role_menu` VALUES ('2330', '79', '77');
INSERT INTO `sys_role_menu` VALUES ('2331', '79', '84');
INSERT INTO `sys_role_menu` VALUES ('2332', '79', '89');
INSERT INTO `sys_role_menu` VALUES ('2333', '79', '88');
INSERT INTO `sys_role_menu` VALUES ('2334', '79', '87');
INSERT INTO `sys_role_menu` VALUES ('2335', '79', '86');
INSERT INTO `sys_role_menu` VALUES ('2336', '79', '85');
INSERT INTO `sys_role_menu` VALUES ('2337', '79', '-1');
INSERT INTO `sys_role_menu` VALUES ('2338', '77', '89');
INSERT INTO `sys_role_menu` VALUES ('2339', '77', '88');
INSERT INTO `sys_role_menu` VALUES ('2340', '77', '87');
INSERT INTO `sys_role_menu` VALUES ('2341', '77', '86');
INSERT INTO `sys_role_menu` VALUES ('2342', '77', '85');
INSERT INTO `sys_role_menu` VALUES ('2343', '77', '84');
INSERT INTO `sys_role_menu` VALUES ('2344', '77', '72');
INSERT INTO `sys_role_menu` VALUES ('2345', '77', '-1');
INSERT INTO `sys_role_menu` VALUES ('2346', '77', '77');
INSERT INTO `sys_role_menu` VALUES ('2503', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('2504', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('2505', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('2506', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('2507', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('2508', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('2509', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('2510', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('2511', '1', '60');
INSERT INTO `sys_role_menu` VALUES ('2512', '1', '59');
INSERT INTO `sys_role_menu` VALUES ('2513', '1', '58');
INSERT INTO `sys_role_menu` VALUES ('2514', '1', '51');
INSERT INTO `sys_role_menu` VALUES ('2515', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('2516', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('2517', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('2518', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('2519', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('2520', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('2521', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('2522', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('2523', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('2524', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('2525', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('2526', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('2527', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('2528', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('2529', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('2530', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('2531', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('2532', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('2533', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('2534', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('2535', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('2536', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('2537', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('2538', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('2539', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('2540', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('2541', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('2542', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('2543', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('2544', '1', '50');
INSERT INTO `sys_role_menu` VALUES ('2545', '1', '49');
INSERT INTO `sys_role_menu` VALUES ('2546', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('2547', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('2548', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('2549', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2550', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('2551', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('2552', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('2553', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2554', '1', '91');
INSERT INTO `sys_role_menu` VALUES ('2555', '1', '92');
INSERT INTO `sys_role_menu` VALUES ('2556', '1', '-1');

-- ----------------------------
-- Table structure for `sys_task`
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron���ʽ',
  `method_name` varchar(255) DEFAULT NULL COMMENT '������õķ�����',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '�����Ƿ���״̬',
  `description` varchar(255) DEFAULT NULL COMMENT '��������',
  `update_by` varchar(64) DEFAULT NULL COMMENT '������',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '����ִ��ʱ�����ĸ���ķ��� ����+����',
  `create_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `job_status` varchar(255) DEFAULT NULL COMMENT '����״̬',
  `job_group` varchar(255) DEFAULT NULL COMMENT '�������',
  `update_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `create_by` varchar(64) DEFAULT NULL COMMENT '������',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '������',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('2', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '1', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '�û���',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '����',
  `dept_id` int(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '����',
  `mobile` varchar(100) DEFAULT NULL COMMENT '�ֻ���',
  `status` tinyint(255) DEFAULT NULL COMMENT '״̬ 0:���ã�1:����',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '�����û�id',
  `gmt_create` datetime DEFAULT NULL COMMENT '����ʱ��',
  `gmt_modified` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '��������Ա', '27bd386e70f280e24c2f4f2a549b82cf', '6', 'admin@example.com', '123456', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00');
INSERT INTO `sys_user` VALUES ('2', 'test', '��ʱ�û�', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@bootdo.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36');
INSERT INTO `sys_user` VALUES ('36', 'ldh', '���»�', 'bfd9394475754fbe45866eba97738c36', '6', 'ldh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('123', 'zxy', '��ѧ��', '35174ba93f5fe7267f1fb3c1bf903781', '6', 'zxy@bootdo', null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('124', 'wyf', '���ෲ', 'e179e6f687bbd57b9d7efc4746c8090a', '6', 'wyf@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('130', 'lh', '¹��', '7924710cd673f68967cde70e188bb097', '9', 'lh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('131', 'lhc', '�����', 'd515538e17ecb570ba40344b5618f5d4', '6', 'lhc@bootdo.com', null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('132', 'lyf', '�����', '7fdb1d9008f45950c1620ba0864e5fbd', '13', 'lyf@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('133', 'my', '����', '40545c12927485fc6ebf65a146246aa0', '9', 'my@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('134', 'lyh', '�����', 'dc26092b3244d9d432863f2738180e19', '8', 'lyh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('135', 'wjl', '������', '3967697dfced162cf6a34080259b83aa', '6', 'wjl@bootod.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('136', 'gdg', '���¸�', '3bb1bda86bc02bf6478cd91e42135d2f', '9', 'gdg@bootdo.com', null, '1', null, null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '�û�ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '��ɫID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='�û����ɫ��Ӧ��ϵ';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('97', '36', '48');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48');
INSERT INTO `sys_user_role` VALUES ('122', '133', '1');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');

