
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLearnerCrawlerTwo {
    public static void main(String[] args) {
        List<String> titles = new ArrayList<String>();
        List<String> intros = new ArrayList<String>();
        List<String> authors = new ArrayList<String>();

        String url = "http://www.datalearner.com/blog_list";
        String rawHtmlContent = reqContent(url);

        // 第一步,将字符内容解析成一个Document类
        Document doc = Jsoup.parse(rawHtmlContent);
        

    }

    public static String reqContent(String uri) {
        System.out.println("hello");

        // 建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 使用HttpGet方式请求网址
//        HttpGet httpGet = new HttpGet("http://www.datalearner.com/blog");
        HttpGet httpGet = new HttpGet(uri);

        // 获取网址的返回结果
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取返回结果中的实体
        HttpEntity entity = response.getEntity();

        String rawHTMLContent = "";
        // 将返回的实体输出
        try {
            System.out.println();
            rawHTMLContent = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawHTMLContent;
    }

}
