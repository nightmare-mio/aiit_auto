package inid.nightmare.demo2.util;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/* 计算工具类
通过使用js引擎计算string类型的算式
其中整除有问题,只能另外计算
return计算结果
msg     传入的string算式
pi              整除计算
answeString     计算结果
*/


public class Calculation {
    
    public static String jisuan(String msg) throws ScriptException {
        String answeString;
        Integer pi;
        ScriptEngine anwser=new ScriptEngineManager().getEngineByName("javascript");
        if (msg.indexOf("=")!=-1) {
            msg=msg.substring(0,msg.indexOf("="));  //排除=
            if (msg.indexOf("/")!=-1) {
                pi=Integer.parseInt(msg.substring(0,msg.indexOf("/")));
                pi/= Integer.parseInt(msg.substring(msg.indexOf("/")+1));
                System.out.println("asnwer="+pi);
                answeString=pi+"";
            }else{
                answeString=anwser.eval(msg)+"";
                System.out.println("asnwer="+answeString);
            }     
        }else{
            answeString=msg;
        }
        return answeString;
    }

}
