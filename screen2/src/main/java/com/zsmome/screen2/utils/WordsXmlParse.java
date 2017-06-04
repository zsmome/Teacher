package com.zsmome.screen2.utils;

import com.zsmome.screen2.models.WordsTable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * 本地单词解析
     <item>
     <word>absolute</word>
     <trans><![CDATA[n. 绝对；绝对事物adj. 绝对的；完全的；专制的]]></trans>
     <phonetic><![CDATA[['æbsəlju:t, ,æbsə'lju:t]]]></phonetic>
     <tags>700核心词汇</tags>
     <progress>-1</progress>
     </item>
 * Created by Administrator on 2017/5/14.
 */

public class WordsXmlParse {
    public static ArrayList<WordsTable> getWordsArrayList(InputStream xml)throws Exception
    {
        //XmlPullParserFactory pullPaser = XmlPullParserFactory.newInstance();
        ArrayList<WordsTable> wordsTableArrayList = null;
        WordsTable wordsTable = null;
        // 创建一个xml解析的工厂
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // 获得xml解析类的引用
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(xml, "UTF-8");
        // 获得事件的类型
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    wordsTableArrayList = new ArrayList<WordsTable>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("item".equals(parser.getName())) {
                        wordsTable = new WordsTable();
                    } else if ("word".equals(parser.getName())) {
                        String word = parser.nextText();// 获取该节点的内容
                        wordsTable.setWord(word);
                    } else if ("trans".equals(parser.getName())) {
                        String trans = parser.nextText();
                        wordsTable.setTrans(trans);
                    } else if ("phonetic".equals(parser.getName())) {
                        String phonetic = parser.nextText();
                        wordsTable.setPhonetic(phonetic);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("item".equals(parser.getName())) {
                        wordsTableArrayList.add(wordsTable);
                        wordsTable = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return wordsTableArrayList;
    }
}
