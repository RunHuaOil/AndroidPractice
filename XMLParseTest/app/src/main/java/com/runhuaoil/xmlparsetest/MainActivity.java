package com.runhuaoil.xmlparsetest;

import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {


    private HttpURLConnection connection = null;
    private InputStream inputStream = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });



    }


    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2:8080/AndroidData/get_data.xml");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(8000);
                    connection.connect();
                    if (connection.getResponseCode() == 200){
                        Log.d("Test", "successConnect");
                        inputStream = connection.getInputStream();

                          //用 Pull 方式解析XML
                        parseXMLWithPull(inputStream);

                          //用 SAX 方式解析XML
//                        SAXParserFactory factory = SAXParserFactory.newInstance();
//                        XMLReader reader = factory.newSAXParser().getXMLReader();
//                        SAXParseHandler handler = new SAXParseHandler();
//                        reader.setContentHandler(handler);
//                        reader.parse(new InputSource(inputStream));

//                        //用 DOM 方式解析XML
//                        parseXMLWithDom(inputStream);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseXMLWithPull(InputStream inputStream1) {
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(inputStream1));

            String id = "";
            String name = "";
            String version = "";

            int eventType = parser.getEventType();
            //开始解析，获取当前解析的状态事件
            while(eventType != XmlPullParser.END_DOCUMENT){

                String nodeName = parser.getName();
                Log.d("Test", "EventType ：" + eventType);
                switch (eventType){

                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)){
                            id = parser.nextText();
                        }else if ("name".equals(nodeName)){
                            name = parser.nextText();
                        }else if ("version".equals(nodeName)){
                            version = parser.nextText();
                        }
                        break;

                    case XmlPullParser.END_TAG:

                        if ("app".equals(nodeName)){
                            Log.d("Test", id + "," + name + "," + version);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void parseXMLWithDom(InputStream inputStream){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            //载入获取的XML输入流

            Element element = document.getDocumentElement();
            NodeList nodeList = element.getElementsByTagName("app");
            /*将标签为 app 的几组标签列为一个 NodeList，在我们的演示XML中
            该 NodeList 有三个成员，
             */

            for (int i = 0;i < nodeList.getLength(); i++){
                Element element1 = (Element)nodeList.item(i);
                //获取第一个 <app> 成员
                NodeList nodeList1 = element1.getChildNodes();
                //将该 <app></app>标签里面的其他标签划分为一个 NodeList,也就是该列表里有 <id> <name><version>
                for (int y = 0;y < nodeList1.getLength(); y++){
                    Node node1 = nodeList1.item(y);
                    //取出nodeList1第一个成员<id>
                    if (node1.getNodeType() == Node.ELEMENT_NODE){
                        Element element2 = (Element) node1;
                        //将node1节点转换为元素，以获取其包括的内容
                        switch (element2.getNodeName()){
                            case "id":
                                Log.d("Test",element2.getFirstChild().getNodeValue());
                                break;
                            case "name":
                                Log.d("Test",element2.getFirstChild().getNodeValue());
                                break;
                            case "version":
                                Log.d("Test",element2.getFirstChild().getNodeValue());
                                break;
                            default:
                                break;
                        }
                    }


                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
