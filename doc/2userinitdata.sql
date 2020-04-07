insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('20', '0', '系统管理', '#', null, '0', 'fa fa-fw fa-cog', 10, '20190219171300');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2010', '20', '用户管理', '/mu_userManager', null, '1', 'fa fa-circle-o', 11, '20190219173200');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2011', '20', '角色管理', '/mu_roleManager', null, '1', 'fa fa-circle-o', 12, '20190219173300');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('21', '0', '基本信息', '#', null, '0', 'fa fa-fw fa-clock-o', 20, '20190219171400');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2110', '21', '名单导入', '/mu_listimport', null, '1', 'fa fa-circle-o', 21, '20190220084900');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2111', '21', '名单维护', '/mu_listmgr', null, '1', 'fa fa-circle-o', 22, '20190220085000');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2112', '21', '流水查询', '/mu_dtlquery', null, '1', 'fa fa-circle-o', 23, '20190220085000');

insert into DA_MENU (MENUID, PMENUID, MENUNAME, URL, PERMS, TYPE, ICON, ORDERNUM, CREATETIME)
values ('2113', '21', '流水分析', '/mu_dtlanalyse', null, '1', 'fa fa-circle-o', 24, '20190220085000');


--ROLE初始化数据（系统管理角色，拥有所有权限）
insert into DA_ROLE (IDS, ROLENAME, REMARK, CREATETIME)
values ('a88f04ac7ab14481bd340d44706d6740', '系统管理角色', '拥有所有权限', '20190225101216');

commit;

--ROLEMENU初始化数据（赋予系统管理员所有菜单权限）
insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '20');

insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '2010');

insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '2011');

insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '21');

insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '2110');

insert into DA_ROLEMENU (ROLEID, MENUID)
values ('a88f04ac7ab14481bd340d44706d6740', '2111');

commit;

--USER初始化数据（创建system系统管理用户）
insert into DA_USER (IDS, LOGINNAME, USERNAME, PASSWORD, DEPTCODE, STATUS, CREATETIME, UPDATETIME, CREATOR)
values ('110ad320b3b543e7b11b4b95c4fc9342', 'system', '系统管理用户', '96E79218965EB72C92A549DD5A330112', null, '1', '20190225101216', '20190227090659', '菜单测试用户');
commit;

insert into DA_USERROLE (USERID, ROLEID)
values ('110ad320b3b543e7b11b4b95c4fc9342', 'a88f04ac7ab14481bd340d44706d6740');

commit;

create or replace view da_device as
select * from t_device where devusage=1003 and devicename like '%保卫处%';

create or replace synonym v_iv_record
  for ECARD.v_iv_record@dbl_ykt;

  create or replace synonym da_device
  for ECARD.da_device@dbl_ykt;

  create or replace synonym t_iv_devcust
  for ECARD.t_iv_devcust@dbl_ykt;