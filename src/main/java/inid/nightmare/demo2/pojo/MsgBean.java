package inid.nightmare.demo2.pojo;


// 验证码
public class MsgBean {
    private String words;

    
    @Override
    public String toString() {
        return "MsgBean [words=" + words + "]";
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
