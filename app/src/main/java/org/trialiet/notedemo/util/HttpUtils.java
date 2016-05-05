package org.trialiet.notedemo.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Trialiet on 2016/4/27.
 */
public class HttpUtils {
    private final static String PATH = "http://10.0.2.2:8080/IceNote/LoginAction";
    private static URL url;

    public HttpUtils(){

    }

    public static int sendMessage(Map<String, String> arg, String encode){
        StringBuffer buffer = new StringBuffer();
        if (arg != null && !arg.isEmpty()){
            for (Map.Entry<String, String> entry : arg.entrySet()){
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }
        try{
            url = new URL(PATH);
            if (url != null){
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn != null){
                    return -1;
                }
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                byte[] data = buffer.toString().getBytes();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(data.length));
                OutputStream os = conn.getOutputStream();
                os.write(data);
                os.close();
                int result = conn.getResponseCode();
                if (result == 200){
                    return changeInputStream(conn.getInputStream(), encode);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    private static int changeInputStream(InputStream inputStream, String encode) {
        // TODO Auto-generated method stub
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream == null){
            try{
                while ( (len = inputStream.read(data) ) != -1){
                    os.write(data, 0 ,len);
                }
                result = new String(os.toByteArray(), encode);
                len = Integer.parseInt(result.substring(0, 1));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return len;
    }
}
