# 博思智慧学习平台自动签到

#### 现有自动功能

- 签到
- 兑换抽奖卡
- 抽奖

**注意**:在 action 运行完后可能存在剩余一张抽奖卡，是因为兑换和抽奖是**并行运行**

默认运行时间**中午12点**，如需修改自行进入.github/workflows/main.yml修改cron参数，cron使用的是国际标准时间，请自行换算成北京标准时间
## 目录
- [使用教程](#使用教程)
- [变量获取](#变量获取)
- [脚本测试](#脚本测试)


## 使用教程
1、fork仓库
![](https://s3.bmp.ovh/imgs/2021/09/fc7b4008a9ce2710.jpg)
2、进入设置，添加必须变量USERNAME、PSD、CLIENTID、CLIENTSECRET
依次是用户名，密码，**API Key**,**Secret Key**
后两个变量是为了识别验证码，使用了百度智能云**免费**的文字识别api

- [变量获取具体教程]（#变量获取）

按照顺序点击即可 
**ps:**是4个变量分别创建,大小写不重要
![](https://s3.bmp.ovh/imgs/2021/09/63ff0827a742e058.jpg)
![](https://s3.bmp.ovh/imgs/2021/09/17c583b73e298980.jpg)

## 变量获取
1.访问百度智能云官网[https://login.bce.baidu.com](#https://login.bce.baidu.com/)

2.**注册**或**登入**

3，搜索并找到文字识别![](https://s3.bmp.ovh/imgs/2021/09/ba794f0d61497812.jpg)

4.具体操作略，因为我做过一次了，所以不太一样就不一一展示了。实在不会

百度[https://www.baidu.com](https://www.baidu.com)
![](https://s3.bmp.ovh/imgs/2021/09/28c0100749f5b8fb.jpg)

## 脚本测试
1.进入Action界面，启用Action

此处借用不知道谁从不知道哪里搞到的图
![](https://s3.bmp.ovh/imgs/2021/09/7728e9ea68ee48a3.png)
![](https://s3.bmp.ovh/imgs/2021/09/7728e9ea68ee48a3.png)


本仓库涉及知识:httpurlconnection类，json序列化与反序列化，api的使用，javaWeb，fiddler，git，workflow，markdown,maven
