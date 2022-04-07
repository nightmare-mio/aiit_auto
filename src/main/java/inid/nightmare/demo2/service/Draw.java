package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/* 抽奖
conn            连接对象
cookie          cookie
msg             抽奖发送请求后返回值
param           正文
 */



public class Draw extends Thread{
    private static final Logger logger =LoggerFactory.getLogger(Draw.class);
    private static HttpURLConnection conn;
    private String param="";
    
    private UserBean user=UserBean.initUser();
    private Cookie tcookie=Cookie.initcookie();
  
    private void draw() throws IOException {
        conn=GetHeaderUtil.get(ParamUtil.draw_url, "POST");
        setCookieAndParam();
        OutputStream out=conn.getOutputStream();

        out.write(param.getBytes());

        out.flush();
        out.close();
        
        StringBuilder msg=new StringBuilder();
        String ch="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }
    }

    @Override
    public void run() {
        try {
            logger.info("status:{}","抽奖开始");
            LuckDrawCar luckDrawCar=new LuckDrawCar();
            while(luckDrawCar.getLuckCar()!=0){
                draw();
            }
            logger.info("status:{}","抽奖结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    private void setCookieAndParam() {
        String cookie="userName="+user.getUsername()+
        "; cacheHeader="+user.getCacheHeader()+
        "; ASP.NET_SessionId="+tcookie.getASP()+
        "; iflysse_client_sign="+tcookie.getIflysse_client_sign()+
        "; SessionId="+user.getSessionId();

        conn.setRequestProperty("Cookie", cookie);

        // LuckDrawID每次都一样的，虽然能通过请求获取LuckDrawID，但没必要
        param="Action=4&LuckDrawID=d202331d-c158-4959-bf62-cd221f2300c0";

    }


    
}
