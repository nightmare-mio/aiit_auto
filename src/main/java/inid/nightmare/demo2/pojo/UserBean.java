package inid.nightmare.demo2.pojo;

public class UserBean {
    private String username;
    private String password;
    private String cacheHeader;
    private String SessionId;
    private static UserBean user;    
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCacheHeader() {
        return cacheHeader;
    }
    public void setCacheHeader(String cacheHeader) {
        this.cacheHeader = cacheHeader;
    }
    public String getSessionId() {
        return SessionId;
    }
    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    
    public static UserBean initUser() {
        if (user==null) {
            user = new UserBean();
        }
        return user;
    }
    private UserBean() {
    }
    



}
