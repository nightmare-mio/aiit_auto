package inid.nightmare.demo2.util;


/* 
常量类
唯一需要修改的四个常量
username        博思用户名
psd             博思密码
clientId        API Key
clientSecret    Secret Key
 */

public class ParamUtil {

    // 官网获取的 API Key 更新为你注册的
    public static String clientId;
    
    // 官网获取的 Secret Key 更新为你注册的
    public static String clientSecret;
    
    // 登入url
    public static String login_url="http://aiit.iflysse.com/Handler/Login/LoginHandler.ashx";

    public static String login_cookie2="http://aiit.iflysse.com/Handler/Login.ashx";

    public static String login_cookie1="http://aiit.iflysse.com/Login_aiit.aspx";

    // 登入验证码url
    public static String login_code="http://aiit.iflysse.com/Pages/CheckCode.aspx";
    
    public static String sign_url="http://aiit.iflysse.com/java-api/excitation/sign/sign";

    public static String exchange_url="http://aiit.iflysse.com/Handler/Shop/ShopWriteSession.ashx";

    //兑换验证码url
    public static String exchange_code="http://aiit.iflysse.com/Pages/Shop/CheckCodeForExchange.aspx?ID=9885032b-40a5-41d0-914a-ee6b2cebb7c4&r=10";
   
    public static String draw_card="http://aiit.iflysse.com/net-api/excitation/bosicard/getEquityCardNumAndDrawId";

    public static String draw_url="http://aiit.iflysse.com/Handler/Shop/LotteryDraw.ashx";

    //抽奖卡id
    public static String exchange_ID="9885032b-40a5-41d0-914a-ee6b2cebb7c4";

    // 最大登入次数
    public static int flag=2;
    // 最大兑换次数    
    public static int max_count=3;

}
