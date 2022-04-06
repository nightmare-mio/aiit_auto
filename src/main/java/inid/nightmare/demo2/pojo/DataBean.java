package inid.nightmare.demo2.pojo;

/**
 * dataBean
 */

//  抽奖卡
public class DataBean {
    private int AllCardNum;
    private int LuckDrawCardNum;
    // 该返回值暂时不知道有什么用
    private String LuckDrawID;
    public int getAllCardNum() {
        return AllCardNum;
    }
    public void setAllCardNum(int allCardNum) {
        AllCardNum = allCardNum;
    }
    public int getLuckDrawCardNum() {
        return LuckDrawCardNum;
    }
    public void setLuckDrawCardNum(int luckDrawCardNum) {
        LuckDrawCardNum = luckDrawCardNum;
    }
    public String getLuckDrawID() {
        return LuckDrawID;
    }
    public void setLuckDrawID(String luckDrawID) {
        LuckDrawID = luckDrawID;
    }
    
    
}