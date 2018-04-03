# web_admin
此项目是一个通用的后台管理系统，使用Spring Boot + Mybatis实现，前端使用layui框架。功能包含基本的用户管理、权限管理等，业务相关逻辑不与管理后台有过多耦合，数据应存储于不同的数据表中。

## 配置
创建mysql数据库后，修改application-dev.yml中数据库配置，再执行init.sql文件，无需更多配置

## 管理员账号
执行init.sql会生成一个管理员账号，用户名为admin，密码是123456

## 问题
1、MYSQL5.7版本sql_mode=only_full_group_by问题
处理方法:
①查看sql_mode

select @@global.sql_mode

查询出来的值为：

ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION

②去掉ONLY_FULL_GROUP_BY，重新设置值。

set @@global.sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';



2、客户信息没有可以作为唯一识别的字段，可能会多个经纪人推荐同一个客户（同一个创建多个经纪人账号推荐同一个人）
   建议留一个电话
现处理：客户信息以手机号码与经纪人身份证号作为唯一标识

3、银行卡以信息以银行卡号与经纪人身份证号作为唯一标识