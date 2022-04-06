package inid.nightmare.demo2.pojo;


// 抽奖卡
public class LuckDrawCarBean {
    private int status;
    private DataBean data;
    
    
    
    @Override
    public String toString() {
        return "LuckDrawCarBean [date=" + data + ", status=" + status + "]";
    }
    
    
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }



    public DataBean getData() {
        return data;
    }



    public void setData(DataBean data) {
        this.data = data;
    }

    

}
