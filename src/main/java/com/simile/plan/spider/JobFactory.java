package com.simile.plan.spider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2019/1/8.
 */
public class JobFactory {
    public static final int default_timeout = 3000;

    public static void main(String[] args) throws Exception {
        int page = 1;
        String b1 = "https://www.zhipin.com/c101280600/b_%E5%8D%97%E5%B1%B1%E5%8C%BA/?query=java&page=";
        File file = new File("d:/data/job/1.json");
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        List<Job> jobs = new ArrayList<Job>();
        do {
            System.out.println("正在处理第" + page + "页数据");
            jobs = parse(b1 + page);
            List<String> contens = new ArrayList<String>();
            for (Job job : jobs) {
                contens.add(job.toString());
            }
            IOUtils.writeLines(contens, System.lineSeparator(), fos);
            page++;
            if (page == 10) {
                break;
            }
            Thread.sleep(5000);
        } while (jobs.size() > 0);
        IOUtils.closeQuietly(fos);
        System.out.println("end");
    }

    public static String escapeRegExp(String str) {
        if (str == null) {
            return str;
        } else {
            str = str.replace("\\", "\\\\").replace(")", "\\)").replace("(", "\\(").replace("[", "\\[").replace("]", "\\]").replace("|", "\\|").replace("$", "\\$").replace("{", "\\{").replace("}", "\\}").replace("^", "\\^").replace("?", "\\?").replace("+", "\\+").replace(".", "\\.").replace("-", "\\-").replace("*", "\\*");
            return str;
        }
    }

    public static String getTextByQuery(Element element, String query) {
        return element.selectFirst(query) != null ? element.selectFirst(query).text() : null;
    }

    public static String getTextByQuery(Element element, String query, String attr) {
        if (StringUtils.equals(attr, "href")) {
//            return element.selectFirst(query) != null ? element.selectFirst(query).absUrl() : null;
        }
        return element.selectFirst(query) != null ? element.selectFirst(query).absUrl(attr) : null;
    }

    public static String getHtmlByQuery(Element element, String query) {
        return element.selectFirst(query) != null ? element.selectFirst(query).html() : null;
    }

    public static String getHtmlByQueryWithSplit(Element element, String query, String splitor, int index) {
        String content = getHtmlByQuery(element, query);
        String[] items = content.contains(splitor) ? content.split(splitor) : new String[0];
        if (items.length != 0 && index < items.length - 1) {
            return items[index];
        }
        return null;
    }

    public static List<Job> parse(String path) {
        List<Job> jobs = new ArrayList<Job>();
        try {
            Document document = Jsoup.parse(new URL(path), default_timeout);
            Element container = document.selectFirst(".job-list");
            if (container == null) {
                return jobs;
            }
            Elements elements = container.getElementsByClass("job-primary");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                String detailUrl = getTextByQuery(element, ".info-primary h3 a", "href");
                String title = getTextByQuery(element, ".info-primary h3 a .job-title");
                String salary = getTextByQuery(element, ".info-primary h3 a span");
                String addr = getHtmlByQueryWithSplit(element, ".info-primary p", "<em class=\"vline\"></em>", 0);
                String age = getHtmlByQueryWithSplit(element, ".info-primary p", "<em class=\"vline\"></em>", 1);
                String education = getHtmlByQueryWithSplit(element, ".info-primary p", "<em class=\"vline\"></em>", 2);

                String company = getTextByQuery(element, ".info-company .name");
                String industry = getHtmlByQueryWithSplit(element, ".company-text p", "<em class=\"vline\"></em>", 0);
                String status = getHtmlByQueryWithSplit(element, ".company-text p", "<em class=\"vline\"></em>", 1);
                String size = getHtmlByQueryWithSplit(element, ".company-text p", "<em class=\"vline\"></em>", 2);
                String pubTime = getTextByQuery(element, ".info-publis p");
                Job job = new Job(title, salary, education, addr, company, industry, size, pubTime, detailUrl);
                jobs.add(job);
            }
//            System.out.println(document.body());
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return jobs;
    }
}
