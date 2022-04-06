package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.Gson;

import inid.nightmare.demo2.pojo.LuckDrawCarBean;
import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/* 获取抽奖卡数量
返回抽奖卡数量
cookie          cookie
msg             抽奖发送请求后返回值 
*/



public class LuckDrawCar {
    private UserBean user=UserBean.initUser();
    private Cookie tcookie=Cookie.initcookie();

    // SessionId要有效，不然401
    public int getLuckCar() throws IOException{
        

        String cookie="userName="+user.getUsername()+
        "; cacheHeader="+user.getCacheHeader()+
        "; SessionId="+user.getSessionId()+
        "; ASP.NET_SessionId="+tcookie.getASP()+
        "; iflysse_client_sign="+tcookie.getIflysse_client_sign();

        HttpURLConnection conn=GetHeaderUtil.get(ParamUtil.draw_card, "GET");
        
        conn.setRequestProperty("Cookie",cookie);
        conn.setRequestProperty("Referer", "http://aiit.iflysse.com/web/student");

        StringBuilder msg = new StringBuilder();
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String ch="";
        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }
        LuckDrawCarBean card=new Gson().fromJson(msg.toString(), LuckDrawCarBean.class);

        return card.getData().getLuckDrawCardNum();
    }
}
