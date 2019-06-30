# Alibaba Cloud Toolkit
[toc]
## 简介
Cloud Toolkit 是针对 IDE 平台为开发者提供的一款插件，帮助开发者更高效地开发、测试、诊断并部署应用。通过插件，可以将本地应用一键部署到云端（ECS、EDAS、容器服务 Kubernetes 等）和任意服务器；并且它还内嵌了 Arthas 程序诊断、 Terminal Shell 终端和 MySQL 执行器等工具。
## 资源
- 官网：https://toolkit.aliyun.com
- 文档：https://yq.aliyun.com/articles/665049?spm=a2c4e.11153940.bloghomeflow.178.2b9f291a8EFTlI
- 产品：https://help.aliyun.com/product/29966.html?spm=a2c4g.11186623.6.540.5dfe26efoYUmUh

## 安装
Alibaba Cloud Toolkit 对 IntelliJ IDEA ，Eclipse ，Maven ，PyCharm、PhpStorm、RubyMine 和 WebStorm 都有支持，此处只做 IntelliJ IDEA 说明。


### IntelliJ IDEA 安装 Cloud Toolkit
- 前提：IntelliJ 在 2018.1 或更高版本
- 第 1 步：打开 Intellij 的 Settings 窗口
- 第 2 步：进入 Plugins 选项，搜索“Alibaba Cloud Toolkit”，并安装即可
- 第 3 步：插件安装成功后，重启 Intellij，工具栏显示 Alibaba Cloud Toolkit 的图标


## 在 IntelliJ IDEA 中部署 Spring Boot / Spring Cloud 应用到服务器
- 第 1 步： 创建案例， Demo 目录 
- 第 2 步： 开启虚拟机
- 第 3 步： 配置连接信息，部署至 Linux ，查看日志
- 第 4 步： 访问 http://192.168.129.128:8081/hello
- 第 5 步： killall java 停止 spring boot 应用

脚本方式实现，/usr/local/docker 目录下编写 restart.sh 脚本，修改连接信息，Command 配置 sh /usr/local/docker/restart.sh 即可实现 spring boot 程序的重启
脚本如下：
```html
killall java
nohup java -jar /usr/loacl/docker/alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar  > spring.log  2>&1 &
```
## Command 编写
```html
java -jar alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar > log.out  2>&1
```
## 部署参数说明
- Deploy File：部署文件包含两种方式。
```html
Maven Build：如果当前工程采用 Maven 构建，可以使用 Cloud Toolkit 直接构建并部署。
Upload File：如果当前工程并非采用 Maven 构建，或者本地已经存在打包好的部署文件，可以选择并直接上传本地的部署文件。
```
- Target Deploy host：在下拉列表中选择Tag，然后选择要部署的服务器。
- Deploy Location ：输入在服务器上部署路径，如 /usr/lcoal/docker。
- Commond：输入应用启动命令， sh 脚本或者原生 shell 命令。表示在完成应用包的部署后，需要执行的命令。
## 部署包备份
在应用部署过程中，对历史包进行备份，以便快速回滚
- 第 1 步： 创建临时目录 /usr/local/docker/app_backup ，用于存储历史版本
- 第 2 步： restart_with_backup.sh 脚本放置于目录  /usr/local/docker/app_backup/  中，内容如下

```html
killall java

#备份上次的包
DATE=$(date +%Y_%m_%d-%H%M%S)
cp /usr/local/docker/alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar   /usr/local/docker/app_backup/alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT${DATE}.jar
#删除上次的包
rm -rf /usr/local/docker/alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar

mv /usr/local/docker/app_backup/alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar  /usr/local/docker/
cd /usr/local/docker
java -jar alibaba-cloud-toolkit-sample-1.0.0-SNAPSHOT.jar > log.out  2>&1

```
- 第 3 步： Command 配置： sh restart_with_backup.sh

## 登陆远程服务器终端 Terminal
功能同 Xshell ，但比 Xshell 方便

## Arthas 远程诊断
文档：https://alibaba.github.io/arthas/quick-start.html

![image](https://github.com/AC-HOME/Learning-Issues-Workspace/blob/master/IDEA%20%E7%B3%BB%E5%88%97/alibaba%20cloud%20toolkit/%E5%9B%BE%E5%BA%93/01.png)

## PelicanDT 分布式应用测试工具
