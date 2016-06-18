package org.ACRusher.xml;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author xiliang.zxl
 * @date 2016-06-04 下午5:28
 */
@Deprecated
public strictfp class XmlTest {


    public static void main(String[] args) {
        MessageConfig messageConfig=MessageConfig.parse(
                XmlTest.class.getClassLoader().getResourceAsStream("demo.xml"));
        System.out.println(messageConfig.toString());
        for(Client client : messageConfig.getClient()){
            System.out.println("----client----");
            System.out.println(client.getUrl());
            System.out.println(client.getUser());
            System.out.println(client.getPwd());
        }
        for(Destination destination:messageConfig.getDestination()){
            System.out.println("----destination---");
            System.out.println(destination.getClient());
            System.out.println(destination.getName());
            System.out.println(destination.getType());
            System.out.println("params thread:"+destination.getParams().getThreads());
            System.out.println("params autostart:"+destination.getParams().getAutostart());
        }
    }
}
