# Android-aliyun-mysql-DEMO

本文使用阿里云、Navicat与Android Studio实现安卓app端对云端sql数据库的简单管理操作。
对于使用MySQL作为服务器的电脑也可以参照部分内容进行操作。
@[TOC](目录)

# 云端服务器配置（阿里云）
阿里云的sql数据库管理较为简单，如果使用的是例如CentOS、Windows等其他服务器，可能需要服务器端的相应代码配置，建议参考相应资料。
## 服务器租用
本教程使用的是阿里云的RDS数据库，阿里云RDS数据库支持MySQL与SQL server的综合功能，并且价格相对实惠。或者使用电脑的MySQL数据库也可以让自己的电脑作为服务器。如果使用的是其他平台或类型的服务器也可以参照下面的步骤，原理都是相同的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903155809968.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
## 获取公网ip，开放端口
注册服务器之后登录进入服务器的控制台。点击左侧的实例列表。第一次开启服务器的话需要等待一段时间，等到服务器的状态变成运行中后就可以获取公网ip了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903160403149.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
在实例列表运行中的数据库实例的右侧点击管理按钮就可以进入数据库的基本信息页面，点击就可以生成内网和外网地址，也就是公网ip。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903160755239.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
同时右侧也可以看到当前开放的端口。一般服务器默认都是开放3306端口，如果出于安全性考虑可以更改，相对在后续的Android Studio代码中也要做相应的改动。

## 白名单放行设备ip地址
进入服务器的白名单设置界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903164835583.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
新建的服务器一般默认只有127.0.0.1这一个ip地址。网页中说明，设置为127.0.0.1表示禁止所有地址访问。因此需要添加放行自己设备的ip地址。直接百度或者使用cmd都可以非常快的获取本机的ip地址。如果涉及到多个设备访问，图方便也可以放行整个ip地址段，例如123.124.140.0/24 代表放行123.124.140.1~123.124.140.255 的所有ip地址

## 创建数据库，管理账号
在上面数据库的基本信息页面中点击左侧的导航栏的账号管理，新建的服务器的账号一开始是空白的，需要我们建立一个管理员账号用于访问数据库。管理员的账号和密码要记牢，后续有用到。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903161310773.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
在下面的数据库管理页面可以直接创建数据库，也可以点击右上角的登录数据库进入DMS界面进行数据库管理。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903161701923.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
DMS界面可以直接使用SQL语句进行各个表的管理操作，推荐熟悉使用MySQL与SQL server的童鞋使用。
如果没有这方面基础，推荐使用Navicat软件进行数据库的连接与管理，此软件可以在电脑上非常直观快速的进行数据库的连接与操作。下面介绍Navicat的简单使用

## Navicat使用
在安装MySQL时一般会自带Navicat的安装包。也可以直接搜索下载+破解，这里不再赘述。可以在这下载 链接：[度盘](https://pan.baidu.com/s/1r4jJ2epupfiShOEXQHYQNA) 
提取码：xb6t 

打开Navicat后点击左上角的连接，选择MySQL或者阿里云MySQL都可以。
进入下图界面，连接名可以随意取，主机填写刚刚在服务器控制台获取到的公网ip地址，端口默认3306也可以修改，用户名和密码填写刚刚在管理界面添加并记住的数据库管理员账户密码。点击确定就创建连接成功了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903163013439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
双击刚刚创建的连接，如果变成了绿色就说明连接成功了。此时就可以在电脑上直接查看云端服务器内的数据了。如果连接没有成功或者一直转圈的话可以检查一下公网ip和端口号是否正确，以及服务器的白名单有没有放行这台电脑的ip。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903163452817.png#pic_center)
新创建的数据库一般会自带几个数据库，像是图中的mysql、sys、information_schema等等都是系统自己创建的，可以选择删除或者保留用于测试。![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903163733174.png#pic_center)
这里我创建了一个名为person的数据库以及两个表格person和manager。
右键表格点击设计表，就可以进入表格的管理界面。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903163955987.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
对于数据表的编辑以及添加数据的操作较为简单，可以根据需要自己查询。
==在对数据表进行编辑或者修改数据之后要记得保存和点击下方的打钩上传云端，否则不会产生更改==

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903164339659.png#pic_center)
同样，后续对服务器的数据有改动的话也要记得刷新查看数据。

# Android Studio 代码编辑
## 环境配置
本教程使用的是java.sql相关的代码，需要mysql-connector的java包。
链接：[度盘](https://pan.baidu.com/s/1VqUQQWnqsVoOml7GMOdUHg) 
提取码：g4kh

下载了对应的java包之后，拖入Android工程的libs目录中，并右键Add As Library。
在项目目录下右键新建一个java class文件用于保存连接服务器所需要使用的方法，下面的方法都尽量写在这个java文件中方便后续调用。
==非常重要！！在AndroidManifest.xml中需要加入这三句获取网络权限==


```java
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903213918213.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
如果会报错的话只保留中间的INTERNET那句也可以。


## 连接数据库

```
public static Connection getConnection(String dbName) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "填入你的公网ip地址";
            conn =(Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":填入你的端口号/" + dbName,
                    "填入你的管理员账号", "填入你的管理员密码");
        } catch (SQLException ex) {//错误捕捉
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;//返回Connection型变量conn用于后续连接
    }
```
## 增改删查
Android Studio中调用sql语句难度不大，增改删用的是同一个框架，查询稍微复杂一些。可以参考下面这篇文章，讲的比较详细。我做一个大致的总结。
[【参考】YancyChang： executeQuery、executeUpdate和execute](https://blog.csdn.net/yancychas/article/details/57489004?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)
### 增加数据
先放上代码
```java
    public static int insertIntoData(final String number, final String password,final String name,final String school) throws SQLException {//增加数据
        Connection  conn = null;
        conn = getConnection("person");//填写需要连接的数据库的名称，我是用的是person
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "insert INTO person (number,password,name,school)VALUES('"+number+"','"+password+"','"+name+"','"+school+"')";
        return stmt.executeUpdate(sql);//返回的同时执行sql语句，返回受影响的条目数量，一般不作处理
    }
```
这里举了一个同时操作了四列数据的插入语句为例。可以看到，此方法的核心是String里的sql语句，对应的内容可以参考sql有关的知识。并且java sql中对sql语句的写法要求非常严格，查询列的名字要与云端数据库中的能够对应上。sql语句中需要添加空格、单引号、括号的地方都需要仔细检查。
### 修改（更新）数据
```java
    public static int updateData(final String col, final int key,final String name) throws SQLException {//修改数据
        Connection  conn = null;
        conn = getConnection("person");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "UPDATE person SET "+col+"='"+key+"' WHERE number=name'";//修改的sql语句
        return stmt.executeUpdate(sql);//返回的同时执行sql语句，返回受影响的条目数量，一般不作处理
    }
```
可以看到，更新数据和添加数据同样调用的是executeUpdate方法，区别在于括号中的sql语句。我编写的这个方法中，col承接需要被修改的列名称，key承接需要修改的键值。用WHERE语句定位需要被修改的行。
### 删除数据

```java
    public static void delete(final String number1)throws SQLException{   //删除数据
        Connection  conn = null;
        conn = getConnection("person");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
          String sql = "DELETE FROM person WHERE number='"+number1+"'";    // 写删除的SQL语句
        stmt.executeUpdate(sql);//返回的同时执行sql语句，返回受影响的条目数量，一般不作处理
    }
```
删除数据也同样调用的是executeUpdate方法，在sql语句中使用了DELETE，删除一列的数据
### 查询数据
查询语句是sql数据库中最复杂的一块内容，在sql语句的使用方法上与前面类似，但是对于接收数据的提取需要费点功夫。
首先提供的是比较复杂的一种数据提取的方法，代码的核心依然是sql语句，提取person表格中name对应的条目中col列的数据，并返回一个哈希表以供提取。如果对哈希表使用较为熟练的童鞋可以直接使用。
```java
    public static HashMap<String, String> getUserInfoByName(String col,String name) {//查询数据
        HashMap<String, String> map = new HashMap<>();
        MainActivity.conn = getConnection("person");
        try {
            Statement st = MainActivity.conn.createStatement();
            String sql = "select "+col+" from person where number = '" + name + "'";//用name定位一行数据，删除col对应列的数据、
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                //res.last(); int rowCnt = res.getRow(); res.first();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }
    }
```
这里也提供另外一种比较简单且简短的查询方法

```java
public static String querycol(final String col) throws SQLException {//读取某一行
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("person");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select "+col+" from person where number='"+name+"'");//用rs接收sql语句返回的查询结果
        //执行查询语句并且保存结果
        rs.first();//rs行指针指向第一行
        a=rs.getString(1);//返回得到的第一列的数据
        rs.close();//查询关闭
        return a;返回结果
    }
```
可以看到，实现同样功能的查询语句相比上面使用哈希表的方法简短了不少。
与增改删语句相比，查询语句多了一个ResultSet类型的变量rs用于接收executeQuery方法返回的数据。
关于ResultSet的使用方法可以参照[【参考】xiuluomenshen1986：java.sql.resultset方法与使用技巧](https://blog.csdn.net/xiuluomenshen1986/article/details/2387220?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param)
在查询条件非常准确的情况下往往得到的结果只有一行，读取数据时只需要通过getXXX方法来操纵列。如果查询结果有多行可以通过使用re.next()方法进行遍历。
## 调用方式
相信大家在其他文章中也有了解,Android为了保证程序的执行效率，程序中有关联网的工作是不允许直接在Activity中执行的。因此与连接数据库有关的操作需要使用多线程来编写。这里给出多线程的一个框架。

```java
final Handler handler = new Handler(new Handler.Callback() {
    @Override
	public boolean handleMessage(Message message) {
         下一步执行的代码
          return false;
   		}
	});
new Thread(new Runnable() {
    @Override
    public void run() {
    Message msg = new Message();//message用于给handler传递参数
        try {
           调用连接数据库方法的代码
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("TAG", " 数据操作异常");
        }handler.sendMessage(msg);
    }
}).start();
```

程序会先进入thread执行其中的try语句，然后继续向下执行到handler.sendMessage(msg)之后，回到上方执行handler中的内容。因此，由于连接服务器需要几毫秒到几秒钟的延迟，如果后面的代码需要用到上面的查询方法返回的数据，出于稳定性考虑建议把下方的代码放到handler中。否则有可能会遇到数据还没返回就被调用的情况。同样，如果是增改删这些不用等待接收返回值的操作就可以省去Handler的部分。
值得强调的是，像是Toast，setText之类的UI操作在Thread是不允许的，会引发闪退。同样，如果需要写与连接相关的UI操作，就需要放在handler里面。
上述代码传递的message可以用于调试与传值，这个可以自行探索。
## 应用举例
掌握了增改删查四大功能之后就可以把对云端sql数据库的操作应用到软件中了，这里以一个简单的注册登录功能为例。
### 数据库准备
先在数据库中新建一个表格，名为login用于测试。创建三个字段，一列是id一列是password一列是username。
==把id设为主键，让他自增长，用于记录每一条数据的编号。否则插入数据会出错。==
password记录用户的密码，username记录用户名。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903215033100.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)


### 应用布局
在activity_main.xml中布局。由于是测试程序，只保留了非常简单的功能。
两个EditText输入框接收输入的字符串。两个按钮用于注册和登陆。点击注册时，把当前输入的信息上传到阿里云sql数据库。点击登录时，根据已输入的账号去数据库中查询密码，并将返回值与当前输入的密码相比对，如果一致，登陆成功。
左上角简单做了一个会变色的文字框，用于显示与数据库是否连接成功。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903202556549.png#pic_center)
布局的代码如下

```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >>
    <TextView
        android:id="@+id/welcome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="欢迎进入课程中心！"
        android:layout_centerHorizontal="true"
        android:textSize="30dp"
        android:layout_marginTop="60dp"
        />
    <EditText
        android:id="@+id/IDinput"
        android:layout_width="150dp"
        android:layout_height="30dp"
        android:layout_below="@id/welcome"
        android:layout_marginTop="80dp"
        android:layout_marginLeft="70dp"
        android:hint="请输入学号"
        android:singleLine="true"
        android:imeOptions="actionDone"
        android:background="@android:color/transparent"
        />
    <EditText
        android:id="@+id/code_input"
        android:layout_width="150dp"
        android:layout_height="30dp"
        android:hint="请输入密码"
        android:inputType="textPassword"
        android:layout_below="@id/IDinput"
        android:layout_marginLeft="70dp"
        android:layout_marginTop="25dp"
        android:singleLine="true"
        android:imeOptions="actionDone"
        android:background="@android:color/transparent"/>
    <Button
        android:id="@+id/log_on"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="登 录"
        android:textStyle="bold"
        android:textSize="25dp"
        android:layout_below="@id/code_input"
        android:layout_marginLeft="72dp"
        android:layout_marginTop="110dp"
        />
    <Button
        android:id="@+id/log_on_button"
        android:layout_width="101dp"
        android:layout_height="60dp"
        android:layout_below="@id/code_input"
        android:text="注 册"
        android:layout_marginLeft="92dp"
        android:layout_marginTop="107dp"
        android:layout_toRightOf="@+id/log_on" />
    <TextView
        android:id="@+id/conn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="网络未连接" />
</RelativeLayout>

```
### 编写连接数据库的方法
在工程中新建一个java class文件，我命名为connect
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020090321002724.png#pic_center)
并把上文提到的连接、插入以及查询方法写进去


```java
package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
    public static Connection getConnection(String dbName) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "填入你的公网ip地址";
            conn =(Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":填入你的端口号/" + dbName,
                    "填入你的管理员账号", "填入你的管理员密码");
            MainActivity.conn_on=1;//用于向主函数传参，判断连接是否成功
        }catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            MainActivity.conn_on=2;//用于向主函数传参，判断连接是否成功
        }
        return conn;//返回Connection型变量conn用于后续连接
    }
    public static int insertIntoData(final String username, final String password) throws SQLException {//增加数据
        Connection  conn = null;
        conn = getConnection("person");
        //使用DriverManager获取数据库连接
        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "insert INTO login (username,password)VALUES('"+username+"','"+password+"')";//把用户名和密码插入到数据库中
        return stmt.executeUpdate(sql);
        //执行DML语句，返回受影响的记录条数
    }
    public static String querycol(final String id) throws SQLException {//读取某一行
        //加载数据库驱动
        String a;
        Connection  conn = null;
        conn = getConnection("person");
        //使用DriverManager获取数据库连接
        Statement  stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs =stmt.executeQuery(
                "select password from login where username='"+id+"'");//从数据库中查询用户名对应的密码并返回
        rs.first();
        a=rs.getString(1);
        rs.close();
        return a;
        //把查询结果输出来
    }
}

```
MainActivity中就可以使用上文介绍的多线程方法进行方法调用，连接数据库进行相应操作。
这里也附上MainActivity中的代码。

```java
public class MainActivity extends AppCompatActivity {
    public static int conn_on=0;//用于判断连接是否成功
    public static String password_receive;//用于接收数据库查询的返回数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText) findViewById(R.id.IDinput);//取得输入框的对象
        final EditText password = (EditText) findViewById(R.id.code_input);

        final TextView conn = (TextView) findViewById(R.id.conn);//取得网络提示框的对象
        conn.setBackgroundColor(Color.RED);//默认设成红色
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (conn_on)//根据返回值判断网络连接是否成功
                {
                    case 1:conn.setText("网络连接成功");conn.setBackgroundColor(Color.GREEN);break;
                    case 2:conn.setText("网络连接失败");break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    connect.getConnection("person");//执行连接测试
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);//跳转到handler1
            }
        }).start();

        Button Register = findViewById(R.id.log_on_button);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connect.insertIntoData(username.getText().toString(),password.getText().toString());//调用插入数据库语句
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        final Handler handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(password_receive.equals(password.getText().toString()))//判断输入密码与取得的密码是否相同
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button logon = findViewById(R.id.log_on);
        logon.setOnClickListener(new View.OnClickListener() {//登录
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        try {
                            password_receive=connect.querycol(username.getText().toString());//调用查询语句，获得账号对应的密码
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }handler2.sendMessage(msg);//跳转到handler2
                    }
                }).start();
            }
        });

    }
}
```
代码的长度不长，但是要用多线程的思想去理解对应的执行顺序。MainActivity中用到了三次多线程语句，两次handler调用。第一次用于在网络测试连接上之后把左上角的提示标签设置为绿色。第二次是在查询服务器取得密码后与用户输入的密码进行字符串比对，判断密码是否正确。

按照流程配置正常的话，运行后就会显示如下界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903221732934.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_10,color_FFFFFF,t_60#pic_center)
在经历一瞬间的延时后左上角的网络连接标识就会变绿
此时的数据库中已有两条数据
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903221902784.png#pic_center)
在登录界面输入用户名111与222之后点击注册
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903221956347.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_10,color_FFFFFF,t_70#pic_center)
刷新之后可以在Navicat中看到，数据已经成功添加进去了！
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903222123282.png#pic_center)
点击登录，密码账号正确，提示登陆成功。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903222149531.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
如果输入错误密码，提示密码错误
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200903222225274.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Nsamh3dA==,size_16,color_FFFFFF,t_70#pic_center)
本例只是调用了插入和查找两个功能，像是密码的修改，课程的选择与调整等等功能就会进一步的使用到增改删查的其他功能。也需要开发者根据自己的需求去改写方法内部的sql语句。

本文作为近期开发的一个小记录，作者也是sql和安卓的新手，代码编写也较为原始，如有不足，欢迎沟通指正。


参考：

 [1]https://blog.csdn.net/CHENEY0314/article/details/105567225
 [2]https://blog.csdn.net/yancychas/article/details/57489004?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
 [3]https://blog.csdn.net/xiuluomenshen1986/article/details/2387220?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
