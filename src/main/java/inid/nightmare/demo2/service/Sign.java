package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/* 签到
conn            连接对象
cookie          cookie
msg             抽奖发送请求后返回值 
*/



public class Sign extends Thread{
    private static final Logger logger=LoggerFactory.getLogger(Login.class);
    private HttpURLConnection conn;
    
    private UserBean user=UserBean.initUser();

    private StringBuilder msg=new StringBuilder();
    //签到

    private void sign() throws IOException {
        conn=GetHeaderUtil.get(ParamUtil.sign_url, "GET");
        setCookieAndParam();
        
        String ch="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }
        
    }
    
    @Override
    public void run() {
        super.run();
        try {
            logger.info("status:{}","签到开始");
            sign();
            logger.info("msg:{},status:{}",msg.toString(),"签到结束");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Cause:{},StackTrace:{}",
            e.getCause(),
            e.getStackTrace());
        }
    }
    
    private void setCookieAndParam() {
        Cookie tcookie=Cookie.initcookie();
        String cookie="userName="+user.getUsername()+
        "; cacheHeader="+user.getCacheHeader()+
        "; ASP.NET_SessionId="+tcookie.getASP()+
        "; iflysse_client_sign="+tcookie.getIflysse_client_sign()+
        "; SessionId="+user.getSessionId();

        conn.setRequestProperty("Referer", "http://aiit.iflysse.com/web/student");

        conn.setRequestProperty("Cookie",cookie);
        
        conn.setRequestProperty("SessionId",user.getSessionId());
    }

}
