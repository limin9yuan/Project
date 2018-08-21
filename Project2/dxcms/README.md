开发人员见意使用ssh方式提交代码，此时需要把自己的ssh公钥设置到环境中，请注意！


``` 
create table test
(
	id int auto_increment
		primary key,
	test1 varchar(20) null,
	test2 varchar(20) null,
	test3 varchar(20) null,
	test4 varchar(20) null,
	constraint test_id_uindex
		unique (id)
)
engine=InnoDB
;

```