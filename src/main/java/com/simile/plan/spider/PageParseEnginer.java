package com.simile.plan.spider;

import com.sun.xml.internal.xsom.impl.parser.DelayedRef;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * @author yitao
 * update date 2019-02-28
 */
public class PageParseEnginer {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (String s : SeedManager.readSeed()) {
            if (StringUtils.isNotBlank(s)) {
                System.out.println(s);
                Document document = Jsoup.parse(new URL(s), 10000);
                int asize = 0;
                document.getElementsByTag("a").forEach(item -> {
//                    System.out.println(item);
                });
                System.out.println("=========================");
                document.getAllElements().stream().filter(item -> StringUtils.containsAny(item.text(),  "热量")).forEach(item -> {
                    System.out.println(item.parent());
                });
                System.out.println(asize);
                Thread.sleep(20000);
            }
        }
    }
}
