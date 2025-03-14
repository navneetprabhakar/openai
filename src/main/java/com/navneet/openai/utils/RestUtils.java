package com.navneet.openai.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RestUtils {

    public static final String RESPONSE_ENCODING="UTF-8";
    public static final Gson GSON =new Gson();
    /**
     * This method executes the HTTP GET API call with CloseableHttpClient
     * Since this client doesn't throw Exception on receiving HTTP 4xx or 5xx it makes easier to handle error scenarios upstream
     * @param url       : HTTP Base URL
     * @param headers   : Headers for request
     * @param queryParams: URL Query Parameters
     * @return          : ResponseEntity with HTTP code, headers and body
     */
    public ResponseEntity<String> restGetCall(String url, Map<String,String> headers, Map<String, String> queryParams) throws IOException, ParseException {
        CloseableHttpClient closeableHttpClient=null;
        try{
            closeableHttpClient= HttpClients.createDefault();
            String uri = url;
            if (null!= queryParams && !queryParams.isEmpty()) {
                uri = addQueryParameters(queryParams, uri);
            }
            HttpGet httpGet=new HttpGet(uri);
            if(null!= headers && !headers.isEmpty()){
                headers.forEach(httpGet::setHeader);
            }
            log.info("Request url:{}, headers:{}", uri,headers);
            CloseableHttpResponse response=closeableHttpClient.execute(httpGet);
            String responseString= EntityUtils.toString(response.getEntity(), RESPONSE_ENCODING);
            log.info("Response code:{}, body:{}",response.getCode(), responseString);
            return new ResponseEntity<>(responseString,getHeadersFromResponse(response), HttpStatus.valueOf(response.getCode()));
        }finally {
            if(null!=closeableHttpClient)
                closeableHttpClient.close();
        }

    }

    /**
     * This method executes the HTTP POST API call with CloseableHttpClient
     * Since this client doesn't throw Exception on receiving HTTP 4xx or 5xx it makes easier to handle error scenarios upstream
     * @param url       : HTTP Base URL
     * @param headers   : Headers for request
     * @param queryParams: URL Query Parameters
     * @param request   : HTTP Request Body
     * @return          : ResponseEntity with HTTP code, headers and body
     */
    public ResponseEntity<String> restPostCall(String url, Map<String,String> headers, Map<String, String> queryParams, Object request) throws IOException, ParseException {
        CloseableHttpClient closeableHttpClient=null;
        try {
            closeableHttpClient = HttpClients.createDefault();
            String uri = url;
            if (null != queryParams && !queryParams.isEmpty()) {
                uri = addQueryParameters(queryParams, uri);
            }
            HttpPost httpPost = new HttpPost(uri);
            // Added for Auth APIs
            if (request instanceof String) {
                httpPost.setEntity(new StringEntity((String) request));
            } else {
                httpPost.setEntity(new StringEntity(GSON.toJson(request)));
            }
            if (null != headers && !headers.isEmpty()) {
                headers.forEach(httpPost::setHeader);
            }
            log.info("Request url:{}, headers:{}, body:{}", uri, headers, httpPost.getEntity().toString());
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity(), RESPONSE_ENCODING);
            log.info("Response code:{}, body:{}", response.getCode(), responseString);
            return new ResponseEntity<>(responseString, getHeadersFromResponse(response), HttpStatus.valueOf(response.getCode()));
        }finally {
            if(null!=closeableHttpClient)
                closeableHttpClient.close();
        }
    }

    /**
     * This method prepares @MultiValueMap headers from @CloseableHttpResponse
     * @param response : HTTP Response
     * @return @LinkedMultiValueMap
     */
    private MultiValueMap<String, String> getHeadersFromResponse(CloseableHttpResponse response){
        MultiValueMap<String, String> headers=new LinkedMultiValueMap<>();
        for(Header ele:response.getHeaders()){
            headers.add(ele.getName(), ele.getValue());
        }
        return headers;
    }

    /**
     * This method add query parameters to the base url and returns URL encoded value
     * @param queryParams : Query param key value pair
     * @param url : Base Url
     * @return url: URL with query parameters
     */
    private String addQueryParameters(final Map<String, String> queryParams, final String url)
            throws IOException {
        try {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            return new URIBuilder(url).addParameters(params).build().toString();
        } catch (URISyntaxException ex) {
            throw new IOException(ex);
        }
    }
}
