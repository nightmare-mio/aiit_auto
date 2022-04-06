package inid.nightmare.demo2.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* 网络连接的通用设置
获取服务器响应
向服务器发送数据
否使用缓存
自动重定向
超时时间
return连接对象
conn        连接对象
time        超时时间
url         传入的连接地址
methor      连接的访问方式 
*/


public class GetHeaderUtil {
    private static HttpURLConnection conn;
    private static int time=3000;
 
    //封装get请求，只需要传入url即可
    public static HttpURLConnection get(String url,String methor) throws IOException {
        
        URL url2=new URL(url);
        //建立连接
        conn =(HttpURLConnection) url2.openConnection();
        //GET请求方式 默认是GET
        if (methor=="POST") {
            conn.setRequestMethod("POST");
        } else {
            conn.setRequestMethod("GET");
        }
        //设置是否获取服务器响应    默认是false
        conn.setDoOutput(true);
        //设置是否向服务器发送数据  默认是true 
        conn.setDoInput(true);
        //设置是否使用缓存 不太懂，需要多浪费老子的空间？爪巴 
        conn.setUseCaches(false);
        //设置是否自动重定向
        conn.setInstanceFollowRedirects(true);
        //设置超时时间
        conn.setConnectTimeout(time);
        
        return conn;
    }
}