package org.ACRusher.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-06-06 下午1:23
 */
@XmlRootElement(name="message")
public class MessageConfig {
    private List<Client> client;
    private List<Destination> destination;

    public static MessageConfig parse(InputStream inputStream){
        try {
            JAXBContext jaxbContext=JAXBContext.newInstance(MessageConfig.class);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
            MessageConfig messageConfig= (MessageConfig) unmarshaller.unmarshal(inputStream);
            return messageConfig;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public List<Destination> getDestination() {
        return destination;
    }

    public void setDestination(List<Destination> destination) {
        this.destination = destination;
    }
}
