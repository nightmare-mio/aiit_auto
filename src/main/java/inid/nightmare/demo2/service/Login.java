package inid.nightmare.demo2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.GetHeaderUtil;
import inid.nightmare.demo2.util.ParamUtil;

/**
 * 登入
 */
public class Login {

    private static final Logger logger=LoggerFactory.getLogger(Login.class);
    private static HttpURLConnection conn;
    private String param;

    private UserBean user=UserBean.initUser();
    private Code code=Code.initcode();
    
    /**
     * 
     * @param username 用户名
     * @param psd 密码
     * @return 成功/失败
     * @throws IOException
     * @throws ScriptException
     */
    public boolean login(String username,String psd) throws IOException, ScriptException {
        conn=GetHeaderUtil.get(ParamUtil.login_url, "POST");
        setCookieAndParam(username, psd);

        OutputStream out=conn.getOutputStream();
        out.write(param.getBytes());

        out.flush();
        out.close();

        StringBuilder msg=new StringBuilder();
        String ch="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        
        // 将所有消息头存储
        Map<String,List<String>> map=conn.getHeaderFields();

        // 提取Set-Cookie消息头
        List<String> list=map.get("Set-Cookie");
        
        while ((ch=reader.readLine())!=null) {
            msg.append(ch);
        }

        // 登入是否成功
        if (msg.toString().contains("验证码错误")) {
            logger.error("date:{},error:{}",new Date(),"CodeError");
            return false;
        } else if(msg.toString().contains("账号或密码错误")){
            logger.error("date:{},error:{}",new Date(),"AccountOrPasswordError");
            return false;
        }else {
            String delay;
            delay=list.get(0);
            user.setSessionId(delay.substring(delay.indexOf("=")+1, delay.indexOf(";")));
        
            delay=list.get(1);
            user.setCacheHeader(delay.substring(delay.indexOf("=")+1, delay.indexOf(";")));
            logger.info("date:{},SessionId:{},CacheHeader:{}",
            new Date(),
            user.getSessionId(),
            user.getCacheHeader());
            
            return true;
        }

    }


    private void setCookieAndParam(String username,String psd) throws IOException, ScriptException {
        Cookie tcookie=Cookie.initcookie();

        // 登入前需要重新获取两个cookie 且一般不会出错

        String cookie="userName="+username+"; "+
        "ASP.NET_SessionId="+tcookie.getASP()+"; "+
        "iflysse_client_sign="+tcookie.getIflysse_client_sign();

        conn.setRequestProperty("Cookie", cookie);

        // 正文 此处验证码经常出错

        String pCode = code.getCode(ParamUtil.login_code);
        logger.info("date:{},code:{}", new Date(),pCode);
        param="action=0&code="+pCode+
                                "&name="+username+"&password="+psd+"&isremember=false";
    }
}
