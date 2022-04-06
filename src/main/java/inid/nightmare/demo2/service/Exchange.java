package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Date;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/* 兑换抽奖卡
conn            连接对象
cookie          cookie
msg             抽奖发送请求后返回值
param           正文 
*/

public class Exchange extends Thread {
    private static HttpURLConnection conn;
    private static final Logger Logger=LoggerFactory.getLogger(Exchange.class);
    private String param="";

    private UserBean user=UserBean.initUser();
    private Cookie tcookie=Cookie.initcookie();
    private Code code=Code.initcode();



    private Boolean exchange() throws IOException, ScriptException{

        conn=GetHeaderUtil.get(ParamUtil.exchange_url,"POST");      
        setCookieAndParam();

        OutputStream out=conn.getOutputStream();
        out.write(param.getBytes());

        out.flush();
        out.close();

        StringBuilder msg=new StringBuilder();
        String ch="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        
        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }
        // 验证码识别错误的情况
        if (msg.indexOf("验证码错误")!=-1) {
            return false;
        }else{
            if (msg.indexOf("您今日购买次数已超过限购数量")!=-1) {
                Logger.info("date:{},status:{}",new Date(),"兑换成功");
            }else

            Logger.info("date:{},status:{}",new Date(),"兑换成功");
            return true;
        }

    }

    @Override
    public void run() {
        // 睡1s，百度识图的api短时间内不能同时请求
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        super.run();
        try {
            while (exchange()==false) {
                if (ParamUtil.max_count--<0) {
                    Logger.info("Error:{}","超过最大验证次数");
                    break;
                }
            }
        } catch (IOException | ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setCookieAndParam(){
        String cookie="userName="+user.getUsername()+
        "; cacheHeader="+user.getCacheHeader()+
        "; SessionId="+user.getSessionId()+
        "; ASP.NET_SessionId="+tcookie.getASP()+
        "; iflysse_client_sign="+tcookie.getIflysse_client_sign();
        
        conn.setRequestProperty("Referer","http://aiit.iflysse.com/Pages/Shop/ExchangeDetails.aspx?ID=9885032b-40a5-41d0-914a-ee6b2cebb7c4");
        conn.setRequestProperty("Cookie",cookie);

        try {
            param="action=0&ID="+ParamUtil.exchange_ID+"&Code="+code.getCode(ParamUtil.exchange_code,cookie);
        } catch (IOException | ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
