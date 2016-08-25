/*
package utils;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
*/
/**
 * Created by nwcemp01 on 19/7/16.
 *//*

public class Jsonparser {

    public static String postMethodsignIn(String url, String email,
                                          String password) {
        try {
            HttpResponse mResponse;
            // Interface for HTTP Client
            HttpClient httpClient = new DefaultHttpClient();
            // Context for executing the request
            HttpContext localContext = new BasicHttpContext();
            HttpParams httpParameters = new BasicHttpParams();
            // Makes the request to the orgin server
            HttpPost httpPost = new HttpPost(url);
            // Instantaiting the list of type namevalue pair
            List<NameValuePair> Details = new ArrayList<NameValuePair>();
            // Adding the entries in to the list
            Details.add(new BasicNameValuePair("grant_type", "password"));
            Details.add(new BasicNameValuePair("UserName", email));

            Details.add(new BasicNameValuePair("Password", password));

            int timeoutConnection = 50000;
            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            // Set the default socket timeout (SO_TIMEOUT)
            // in milliseconds which is the timeout for waiting for
            // data.
            int timeoutSocket = 10000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            // Hands the entity to the request
            httpPost.setEntity(new UrlEncodedFormEntity(Details));
            // Execute the request using the given context
            mResponse = httpClient.execute(httpPost, localContext);
            mResponsetext = mResponse.getStatusLine().getStatusCode();
            responseString = EntityUtils.toString(mResponse.getEntity());
            // Log.i("Response", "Response :" + responseString);
            // System.out.println("Response Login: " + responseString);

       //     status = Jsonparser.ReadJsonSignInValue(responseString);

            // status = ReadJsonSendSms(responseString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(mResponsetext);
    }

    public static String convertStreamToString(InputStream is) {
        // TODO Auto-generated method stub
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }




}
*/
