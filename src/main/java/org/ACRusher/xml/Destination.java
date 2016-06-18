package org.ACRusher.xml;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * @author xiliang.zxl
 * @date 2016-06-06 下午1:24
 */
@XmlRootElement
public class Destination {
    private String type;
    private String name;
    private String client;

    @XmlAttribute
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    private Params params;

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static class Params{
        private Integer threads;
        private Boolean autostart;

        public Integer getThreads() {
            return threads;
        }

        public void setThreads(Integer threads) {
            this.threads = threads;
        }

        public Boolean getAutostart() {
            return autostart;
        }

        public void setAutostart(Boolean autostart) {
            this.autostart = autostart;
        }
    }
}
