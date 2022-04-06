package inid.nightmare.demo2.service;

import java.net.URLEncoder;

import inid.nightmare.demo2.util.Base64Util;
import inid.nightmare.demo2.util.FileUtil;
import inid.nightmare.demo2.util.HttpUtil;

/**
 * SetCode
 * 识别验证码图片
 * 
 * 
 */
public class SetCode {
    //百度api
    private static String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

    //传入图片url
    public static String setCode(String imagePath) {

        try {
            // 本地文件路径
            String filePath = imagePath;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            //自动获取token
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    
}