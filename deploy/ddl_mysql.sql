
drop table if exists rs_bank_info;
create table rs_bank_info
(
	id bigint auto_increment not null comment '主键'
		primary key,
	mid bigint null comment '码商id',
	name varchar(45) null comment '收款人',
	status varchar(32) null,
	min_amount varchar(45) null,
	max_amount varchar(45) null,
	total_received_amount decimal(26,6) default 0 comment '总收款金额',
	total_tx_times int(32) default 0 comment '总单数',
	succeed_tx_times int(32) default 0 comment '成功单数',
	tx_succeed_rate decimal(10,2) default 0 comment '收款成功比例（%）',
	account varchar(45) null comment '账号',
	bank_name varchar(45) null comment '银行名称',
	branch_bank_name varchar(45) null comment '支行名称',
	address varchar(45) null comment '地址',
	remark varchar(255) null comment '备注'
);

drop table if exists rs_pay_alipay_info;
create table rs_pay_alipay_info
(
	id bigint auto_increment not null comment '主键'
		primary key,
	mid bigint null comment '码商id',
	name varchar(45) null comment '收款人',
	status varchar(32) null,
	img_url varchar(255) null comment '二维码地址',
	min_amount varchar(45) null,
	max_amount varchar(45) null,
	total_received_amount decimal(26,6) default 0 comment '总收款金额',
	total_tx_times int(32) default 0 comment '总单数',
	succeed_tx_times int(32) default 0 comment '成功单数',
	tx_succeed_rate decimal(10,2) default 0 comment '收款成功比例（%）',
	account varchar(45) null comment '账号',
	remark varchar(255) null comment '备注'
);

drop table if exists rs_pay_wechat_info;
create table rs_pay_wechat_info
(
	id bigint auto_increment not null comment '主键'
		primary key,
	mid bigint null comment '码商id',
	name varchar(45) null comment '收款人',
	status varchar(32) null,
	img_url varchar(255) null comment '二维码地址',
	min_amount varchar(45) null,
	max_amount varchar(45) null,
	total_received_amount decimal(26,6) default 0 comment '总收款金额',
	total_tx_times int(32) default 0 comment '总单数',
	succeed_tx_times int(32) default 0 comment '成功单数',
	tx_succeed_rate decimal(10,2) default 0 comment '收款成功比例（%）',
	account varchar(45) null comment '账号',
	remark varchar(255) null comment '备注'
);
