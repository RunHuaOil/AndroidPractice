package com.runhuaoil.xmlparsetest;


import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by RunHua on 2016/10/13.
 */

public class SAXParseHandler extends DefaultHandler {

    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;
    private String nodeName = "";


    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
        Log.d("Test","!!!startDocument" );
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
        Log.d("Test","!!!startElement nodeName : " + nodeName);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("app")){
            Log.d("Test",id.toString().trim());
            Log.d("Test",name.toString().trim());
            Log.d("Test",version.toString().trim());

            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
        Log.d("Test","!!!endElement");

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        Log.d("Test","!!!characters:" + String.valueOf(ch) + ",start:" + start + ",length:" + length);

        switch (nodeName){
            case "id":
                id.append(ch, start, length);
                break;
            case "name":
                name.append(ch, start, length);
                break;
            case "version":
                version.append(ch, start, length);
                break;
            default:
                break;

        }

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.d("Test","!!!endDocument" );
    }
}
