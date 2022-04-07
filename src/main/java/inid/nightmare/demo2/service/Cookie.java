package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/* 博思登入需要三个cookie
1.userName              为恒定不变
2.ASP.NET_SessionId     通过set-cookie方式设置
3.getCookie_sign        通过放回json数据设置
asp         临时存储ASP.NET_SessionId 
conn        连接对象
param       请求getCookie_sign时所需要发送的正文
msg         请求返回
 */

public class Cookie {
    private HttpURLConnection conn;
    private static final Logger logger=LoggerFactory.getLogger(Cookie.class);

    private String ASP;
    private String iflysse_client_sign;
    private static Cookie cookie;

    private UserBean user=UserBean.initUser();

    public static Cookie initcookie() {
        if (Cookie.cookie==null) {
            return cookie = new Cookie();
        }else{
            return cookie;
        }
    }

    private Cookie() {
        try {
            this.ASP=this.getCookie_asp();
            this.iflysse_client_sign=this.getCookie_sign();
            logger.info("ASP.NET_SessionId:{},iflysse_client_sign:{}",
            this.ASP,
            this.iflysse_client_sign);
        
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Cause:{},StackTrace:{}",
            e.getCause(),
            e.getStackTrace());
        }
    
    }

    private String getCookie_asp() throws IOException {
        conn=GetHeaderUtil.get(ParamUtil.login_cookie1, "GET");

        conn.setRequestProperty("Cookie", "userName="+user.getUsername());
        String asp=conn.getHeaderField("Set-Cookie");
        return asp.substring(asp.indexOf("=")+1, asp.indexOf(";"));
    }

    private String getCookie_sign() throws IOException {
        
        conn=GetHeaderUtil.get(ParamUtil.login_cookie2, "POST");

        conn.setRequestProperty("Cookie","userName="+user.getUsername()+
        "; ASP.NET_SessionId="+this.ASP);

        String param="action=3";
        
        OutputStream out=conn.getOutputStream();
        out.write(param.getBytes());
        
        out.flush();
        out.close();

        String ch="";
        
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder msg=new StringBuilder();
        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }

        // 此处使用了第二种方法 反序列化json json->map
        Map<String,String> map=new Gson().fromJson(msg.toString(),new TypeToken<Map<String,String>>(){}.getType());
        return map.get("Data");
    }

    public String getASP() {
        return ASP;
    }
    
    public String getIflysse_client_sign() {
        return iflysse_client_sign;
    }
}
