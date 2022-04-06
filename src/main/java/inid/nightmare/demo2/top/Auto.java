package inid.nightmare.demo2.top;

import java.io.IOException;

import javax.script.ScriptException;

import inid.nightmare.demo2.pojo.UserBean;
import inid.nightmare.demo2.service.Draw;
import inid.nightmare.demo2.service.Exchange;
import inid.nightmare.demo2.service.Login;
import inid.nightmare.demo2.service.Sign;
import inid.nightmare.demo2.util.Base64Util;
import inid.nightmare.demo2.util.ParamUtil;

public class Auto {
        
    private UserBean user=UserBean.initUser();
    /**
     * 设置参数
     * @param args
     */
    private void setParameter(String args[]) {
          //设置代理，使fiddler能够抓取到
          System.setProperty("http.proxySet", "true");
 
          System.setProperty("http.proxyHost", "127.0.0.1");
  
          System.setProperty("http.proxyPort", "8888");
      
          
          user.setUsername("332501200009255316");
          user.setPassword(Base64Util.encode("wang5210".getBytes()));
          
          // ParamUtil.userName=args[0];
          // ParamUtil.psd=Base64Util.encode(args[1].getBytes());
          // ParamUtil.clientId=args[2];

          ParamUtil.clientId="M9woqE3Fo563VA3WYKxiSL1S";
          ParamUtil.clientSecret="uZOkXntG72jCPVWilMec81ZK4WGrGP7Z";
    }


    public void autoStart(String args[]) {
        setParameter(args);

        Login login = new Login();
            try {
                if (login.login(user.getUsername(),user.getPassword())) {
                    //通过多线程同时启动抽奖和签到和兑换
                    Sign thread1 =new Sign();
                    thread1.start();
                    Exchange thread2=new Exchange();
                    thread2.start();
                    Draw thread3=new Draw();
                    thread3.start();
                }
                else{  
                    ParamUtil.flag--;                 
                }
            } catch (IOException | ScriptException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  