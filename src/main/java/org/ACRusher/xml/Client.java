package org.ACRusher.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xiliang.zxl
 * @date 2016-06-06 下午1:23
 */
@XmlRootElement
public class Client {
    private String url;
    private String pwd;
    private String user;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
