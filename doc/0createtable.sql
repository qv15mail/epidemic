-- Create table
create table DA_USER
(
  ids        VARCHAR2(32) not null,
  loginname  VARCHAR2(60),
  username   VARCHAR2(120),
  password   VARCHAR2(60),
  deptcode   VARCHAR2(60),
  status     CHAR(1),
  createtime VARCHAR2(20),
  updatetime VARCHAR2(20),
  creator    VARCHAR2(32)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column DA_USER.loginname
  is '登录名';
comment on column DA_USER.username
  is '名称';
comment on column DA_USER.password
  is '密码';
comment on column DA_USER.deptcode
  is '部门编号';
comment on column DA_USER.status
  is '状态0关闭，1启用';
comment on column DA_USER.createtime
  is '创建时间';
comment on column DA_USER.updatetime
  is '修改时间';
comment on column DA_USER.creator
  is '创建人';
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_USER
  add constraint DA_USER primary key (IDS)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DA_MENU
(
  menuid     VARCHAR2(10) not null,
  pmenuid    VARCHAR2(10),
  menuname   VARCHAR2(60),
  url        VARCHAR2(100),
  perms      VARCHAR2(100),
  type       CHAR(1),
  icon       VARCHAR2(60),
  ordernum   NUMBER(6),
  createtime VARCHAR2(20)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column DA_MENU.menuid
  is '菜单编号';
comment on column DA_MENU.pmenuid
  is '父菜单';
comment on column DA_MENU.menuname
  is '菜单名称';
comment on column DA_MENU.url
  is '菜单url';
comment on column DA_MENU.perms
  is '授权(多个用逗号分隔，如：user:list,user:create)';
comment on column DA_MENU.type
  is '类型0：目录,1：菜单,2：按钮';
comment on column DA_MENU.icon
  is '菜单图标';
comment on column DA_MENU.ordernum
  is '排序';
comment on column DA_MENU.createtime
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_MENU
  add constraint DA_MENU primary key (MENUID)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DA_ROLE
(
  ids        VARCHAR2(32) not null,
  rolename   VARCHAR2(60),
  remark     VARCHAR2(120),
  createtime VARCHAR2(20)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column DA_ROLE.ids
  is '角色编号';
comment on column DA_ROLE.rolename
  is '角色名称';
comment on column DA_ROLE.remark
  is '标识';
comment on column DA_ROLE.createtime
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_ROLE
  add constraint DA_ROLE primary key (IDS)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DA_ROLEMENU
(
  roleid VARCHAR2(32) not null,
  menuid VARCHAR2(32) not null
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns
comment on column DA_ROLEMENU.roleid
  is '角色编号';
comment on column DA_ROLEMENU.menuid
  is '菜单编号';
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_ROLEMENU
  add constraint DA_ROLEMENU primary key (ROLEID, MENUID)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DA_USERROLE
(
  userid VARCHAR2(32) not null,
  roleid VARCHAR2(32) not null
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column DA_USERROLE.userid
  is '用户编号';
comment on column DA_USERROLE.roleid
  is '角色编号';
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_USERROLE
  add constraint DA_USERROLE primary key (USERID, ROLEID)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DA_DEVICE
(
  deviceid   NUMBER(9),
  devicename VARCHAR2(120),
  devphyid   VARCHAR2(120)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Create table
create table DA_CUSTLIMIT
(
  stuempno   VARCHAR2(32),
  custname   VARCHAR2(120),
  deviceid   VARCHAR2(10),
  opercode   VARCHAR2(32),
  importtime VARCHAR2(30),
  updatetime VARCHAR2(30),
  expiredate VARCHAR2(30),
  ids        VARCHAR2(32) not null,
  syncflag   CHAR(1) default 0,
  synctime   VARCHAR2(30),
  verno      NUMBER(6) default 0,
  adddelflag CHAR(1),
  begindate  VARCHAR2(30)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column DA_CUSTLIMIT.stuempno
  is '学号';
comment on column DA_CUSTLIMIT.custname
  is '姓名';
comment on column DA_CUSTLIMIT.deviceid
  is '设备编号';
comment on column DA_CUSTLIMIT.opercode
  is '操作员号';
comment on column DA_CUSTLIMIT.importtime
  is '导入时间';
comment on column DA_CUSTLIMIT.updatetime
  is '更新时间';
comment on column DA_CUSTLIMIT.expiredate
  is '过期日期';
comment on column DA_CUSTLIMIT.ids
  is '编号';
comment on column DA_CUSTLIMIT.syncflag
  is '同步标志';
comment on column DA_CUSTLIMIT.synctime
  is '同步时间';
comment on column DA_CUSTLIMIT.verno
  is '版本号';
comment on column DA_CUSTLIMIT.adddelflag
  is '增删标志1增加，2删除';
comment on column DA_CUSTLIMIT.begindate
  is '开始日期';
-- Create/Recreate indexes
create index IDX_CUST on DA_CUSTLIMIT (STUEMPNO, DEVICEID, OPERCODE)
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints
alter table DA_CUSTLIMIT
  add constraint PRI_ID primary key (IDS)
  using index
  tablespace TS_YKT_CUR
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table epidemic.da_custlimit add addstatus varchar2(10) default 'success'; -- success 成功，fail 失败
alter table epidemic.da_custlimit add addremark varchar2(60); -- 说明

-- Create table
create table DA_APPOINTMENT
(
  ids         VARCHAR2(32),
  custname    VARCHAR2(120),
  mobile      VARCHAR2(32),
  idno        VARCHAR2(60),
  idtype      VARCHAR2(30),
  sdate       VARCHAR2(30),
  linkman     VARCHAR2(60),
  deptname    VARCHAR2(120),
  reason      VARCHAR2(300),
  approve     VARCHAR2(30),
  approvetime VARCHAR2(30),
  suggest     VARCHAR2(300),
  opercode    VARCHAR2(32),
  updatetime  VARCHAR2(30)
)
tablespace TS_YKT_CUR
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns
comment on column DA_APPOINTMENT.custname
  is '申请人姓名';
comment on column DA_APPOINTMENT.mobile
  is '手机';
comment on column DA_APPOINTMENT.idno
  is '证件号码';
comment on column DA_APPOINTMENT.idtype
  is '证件类型';
comment on column DA_APPOINTMENT.sdate
  is '预约进入时间';
comment on column DA_APPOINTMENT.linkman
  is '预约联系人';
comment on column DA_APPOINTMENT.deptname
  is '预约部门';
comment on column DA_APPOINTMENT.reason
  is '预约事由';
comment on column DA_APPOINTMENT.approve
  is '是否审批';
comment on column DA_APPOINTMENT.approvetime
  is '审批时间';
comment on column DA_APPOINTMENT.suggest
  is '审批意见';
comment on column DA_APPOINTMENT.opercode
  is '操作员';
comment on column DA_APPOINTMENT.updatetime
  is '更新时间';
