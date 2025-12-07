## 部署

### 本地启动项目

1. 初始化db
```
./doc/init_db.sql
```
2. 在 application.yml 更改db连接信息
```
./src/main/resources/application.yml
```
3. 运行main方法
```
./src/main/java/com/xxc/xia/Main.java
```

## 需求分析

### 功能性需求

- 用户
  - 用户注册
  - 用户登录
  - 用户退出
  - 区分C端用户和管理员用户
- 权限拦截
- 题目
  - 查询题目
  - 提交题目答案
  - 创建题目
- 题集
  - 查询题集
  - 创建题集
- 记录用户每天是否答题

## 数据实体

- 用户
- 角色
- 权限
- 题目
- 题集
- 打卡记录
- 用户答案

## 系统交互设计

### 1. 用户
#### 注册账号
```puml
user -> ui++: 点击注册按钮
user -> ui: 输入密码
user -> ui: 确认注册
ui -> server++: /userAccount/register
server -> server: 创建新用户
server --> ui--: 返回用户id
ui --> user++: 展示用户id
```

## 接口设计
### 1. 用户注册账号
> path: `/userAccount/register`
> 
> method: `POST`

request:

| fieldName | type   | 是否必填 | 备注   |
| --- |--------|------|------|
| nickName | string | M    | 用户昵称 |
| password | string | M    | 密码   |

response:

| fieldName | type   | 是否必填 | 备注   |
| --- |--------|------|------|
| success | bool | M    | 是否成功 |
| data | object | C    | 数据   |
| data.id | string | C | 用户id |