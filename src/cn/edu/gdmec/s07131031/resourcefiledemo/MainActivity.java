package cn.edu.gdmec.s07131031.resourcefiledemo;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView myTv1,myTv2;
	Button mybtn1,mybtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTv1=(TextView) findViewById(R.id.textView1);
        myTv2=(TextView) findViewById(R.id.textView2);
    }
    public void ReadRaw(View V){
    	InputStream is = null;
    	is=getResources().openRawResource(R.raw.raw_file);
    	try {
			byte [] reader = new byte[is.available()];
			while(is.read(reader)!=-1){
			myTv1.setText(new String(reader,"GBk"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void ReadXml(View V){
    	XmlPullParser myXpp=getResources().getXml(R.xml.people);
    	String msg="";
    	try {
			while(myXpp.next()!=XmlPullParser.END_DOCUMENT){
			String nodeName = myXpp.getName();
			if(nodeName!=null&&nodeName.equals("person")){
				int attrCount=myXpp.getAttributeCount();
				for(int i=0;i<attrCount;i++){
					String attrName=myXpp.getAttributeName(i);
					if(attrName!=null&&attrName.equals("name")){
						msg+="����"+myXpp.getAttributeValue(i);
					}
					else if(attrName!=null&&attrName.equals("age")){
						msg+="����"+myXpp.getAttributeValue(i);
					}
					else if(attrName!=null&&attrName.equals("height")){
						msg+="����"+myXpp.getAttributeValue(i);
					}
				}
				msg+="\n";
			}
			}
			myTv2.setText(msg);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
