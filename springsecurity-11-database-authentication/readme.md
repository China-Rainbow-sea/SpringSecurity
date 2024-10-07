# 编码顺序：
entity->dao->dao的单元测试->service接口->service实现->service单元测试->controller->controller的单元测试


# 涉及到的权限的多表查询


```
sql


select * from sys_role_user where uid=1
select * from sys_role_menu where rid =1

select * from sys_menu where id in(1,3,4,5,9,10,17)

select DISTINCT sm.code
from sys_role_user sru INNER JOIN sys_role_menu srm on sru.rid = srm.rid
INNER JOIN sys_menu sm on sm.id = srm.mid WHERE sru.uid =1;

```
