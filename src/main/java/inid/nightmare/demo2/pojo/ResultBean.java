package inid.nightmare.demo2.pojo;

import java.util.List;


// 验证码
public class ResultBean {
    private String log_id;
    private String words_result_num;
    private List<MsgBean> words_result;

    @Override
    public String toString() {
        return "ResultBean [log_id=" + log_id + ", words_result=" + words_result + ", words_result_num="
                + words_result_num + "]";
    }
    public String getLog_id() {
        return log_id;
    }
    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getWords_result_num() {
        return words_result_num;
    }
    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }
    public List<MsgBean> getWords_result() {
        return words_result;
    }
    public void setWords_result(List<MsgBean> words_result) {
        this.words_result = words_result;
    }


}
