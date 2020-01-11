package com.paysuper.appmanager.helpers;

import com.paysuper.appmanager.ApplicationManager;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Http {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public Http(){
 //       this.app = app;
        //новая сессия по работе с хттп, использует стратегию перенаправлений
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    //выполнение логина
    public void post() throws IOException {
        HttpPost post = new HttpPost((app.getProperties.value("ApiUrl") + app.getProperties.value("ApiOrderCreate")));
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("project", "5db8183cf0c9c60001a88c8c"));
        params.add(new BasicNameValuePair("amount", "30"));
        params.add(new BasicNameValuePair("currency", "USD"));
        params.add(new BasicNameValuePair("type", "simple"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = getTextFrom(response);
        System.out.println(body.contains(String.format("<span class=\"user-info\">%s</span>", "payment_form_url")));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperties.value("ApiUrl") + app.getProperties.value("ApiOrderCreate"));
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }
}