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
        user.setUsername(args[0]);
        user.setPassword(Base64Util.encode(args[1].getBytes()));
        ParamUtil.clientId=args[2];
        ParamUtil.clientSecret=args[3];
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  