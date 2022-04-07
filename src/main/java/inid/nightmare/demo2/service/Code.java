package inid.nightmare.demo2.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.script.ScriptException;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inid.nightmare.demo2.pojo.ResultBean;
import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.util.Calculation;
import inid.nightmare.demo2.util.GetHeaderUtil;

/**
 * GetCode
 * 下载获取验证码并返回验证码
 * conn         连接对象     
 * filePath     验证码存储地址
 * url          传入验证码url
 */

public class Code {
    
    private HttpURLConnection conn;
    private String filePath="src\\main\\resources\\img\\code.jpg";
    private static final Logger logger=LoggerFactory.getLogger(Code.class);
    private static Code code;
    
    private UserBean user=UserBean.initUser();
    private Cookie cookie=Cookie.initcookie();
    
    /**
     * 签到用
     * get
     * @param url
     * @return 验证码
     * @throws IOException
     * @throws ScriptException
     */
    public String getCode(String url) throws IOException, ScriptException {
        conn=GetHeaderUtil.get(url, "GET");

        conn.setRequestProperty("Cookie", "userName="+user.getUsername() +"; ASP.NET_SessionId="+cookie.getASP());

        return defaultCode(conn);
    }


    /**
     * 兑换用
     * post
     * @param url
     * @param cookie
     * @return 验证码
     * @throws IOException
     * @throws ScriptException
     */
    public String getCode(String url,String cookie) throws IOException, ScriptException {
        conn=GetHeaderUtil.get(url, "GET");

        conn.setRequestProperty("Cookie", cookie);

        String codeString;

        do {
            codeString=defaultCode(conn);
        } while (codeString==null);

        return codeString;
    }

    private String defaultCode(HttpURLConnection conn) throws ScriptException, IOException {
        InputStream in=conn.getInputStream();
            
        FileOutputStream outFile=new FileOutputStream(filePath);
        int ch;
        while ((ch=in.read())!=-1) {
            outFile.write(ch);
        }
        outFile.close();
        in.close();

        //解析对象
        ResultBean resultBean=new Gson().fromJson(SetCode.setCode(filePath), ResultBean.class);
        //取得string验证码
        try {
            String result = Calculation.jisuan(resultBean.getWords_result().get(0).getWords().replace(" ", ""));
            logger.info("msg:{}",resultBean.getWords_result().get(0).getWords().replace(" ", "")+
                "=="+
                result
                );
            return result;
        } catch (IndexOutOfBoundsException e) {
            logger.error("Cause:{}",e.getCause());
            return null;
        }
    }
    

    /**
     * 单例
     * @return
     */
    public static Code initcode() {
        if (code == null) {
            code = new Code();
        }
        return code;
    }
    private Code() {
    }
    


}