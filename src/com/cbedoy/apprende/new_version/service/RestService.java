package com.cbedoy.apprende.new_version.service;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.cbedoy.apprende.new_version.interfaces.IRestService;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Carlos on 14/10/2014.
 */
public class RestService implements IRestService {

    private int mPort;
    private String mUrl;
    private HashMap<String, Object> mEnvironmentMaps;

    public RestService() {
        mEnvironmentMaps = new HashMap<String, Object>();

        HashMap<String, Object> infoMap = new HashMap<String, Object>();
        HashMap<String, Object> countryMap = new HashMap<String, Object>();
        countryMap.put("key", "60buval65vfmugo");
        countryMap.put("id", "298965");
        infoMap.put("mx", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("key", "j2hfm24bjwe3ida");
        countryMap.put("id", "3225");
        infoMap.put("es", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("key", "uiolgkq60rbt0ce");
        countryMap.put("id", "3226");
        infoMap.put("us", countryMap);
        mEnvironmentMaps.put("debug", infoMap);

        infoMap = new HashMap<String, Object>();
        countryMap = new HashMap<String, Object>();
        countryMap.put("privateKey", "a0cx02spq2s7nq9");
        countryMap.put("userId", "6352");
        infoMap.put("mx", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("privateKey", "bekohi7b6guu9xn");
        countryMap.put("userId", "2573");
        infoMap.put("es", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("privateKey", "fpzrykfv0w12i9t");
        countryMap.put("userId", "2572");
        infoMap.put("us", countryMap);
        mEnvironmentMaps.put("preproduction", infoMap);

        infoMap = new HashMap<String, Object>();
        countryMap = new HashMap<String, Object>();
        countryMap.put("key", "hpigxvms09majjm");
        countryMap.put("id", "1106215");
        infoMap.put("mx", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("key", "fpnyau2mgyrvb7w");
        countryMap.put("id", "992");
        infoMap.put("es", countryMap);
        countryMap = new HashMap<String, Object>();
        countryMap.put("key", "d0a4t4jk0frd8nf");
        countryMap.put("id", "994");
        infoMap.put("us", countryMap);
        mEnvironmentMaps.put("production", infoMap);
    }

    @Override
    public void setURL(String url) {
        mUrl = url;
    }

    @Override
    public void setPort(int port) {
        mPort = port;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void request(String url, HashMap<String, Object> parameters, IRestCallback callback) {
        HashMap<String, Object> request = new HashMap<String, Object>();
        request.put("url", url);
        request.put("callback", callback);
        request.put("parameters", parameters);

        AsyncCall call = new AsyncCall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
        } else {
            call.execute(request);
        }
    }

    private class AsyncCall extends AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>> {

        @Override
        @SuppressWarnings("unchecked")
        protected HashMap<String, Object> doInBackground(HashMap<String, Object>... params) {
            HashMap<String, Object> request = params[0];
            String url = request.get("url").toString();
            HashMap<String, Object> parameters = (HashMap<String, Object>) request.get("parameters");

            if (parameters == null) {
                parameters = new HashMap<String, Object>();
            }
            if (!parameters.containsKey("debug")) {
                parameters.put("debug", 1);
            }
            if (!parameters.containsKey("id_canal")) {
                parameters.put("id_canal", 9);
            }
            if (!parameters.containsKey("idioma")) {
                parameters.put("idioma", Locale.getDefault().getLanguage());
            }

            boolean needsSignature = !parameters.containsKey("id_sesion") || !parameters.containsKey("id_usuario");
            HashMap<String, Object> envSignMap = (HashMap<String, Object>) mEnvironmentMaps.get(
                    InjectionManager.getInstance().isProduction() ?
                            (InjectionManager.getInstance().isPreProduction() ?
                                    "preproduction" : "production") : "debug");
            String codigoPais = parameters.containsKey("codigo_pais") ? parameters.get("codigo_pais").toString().toLowerCase(Locale.getDefault()) : "us";
            HashMap<String, Object> signData = (HashMap<String, Object>) (envSignMap.containsKey(codigoPais) ? envSignMap.get(codigoPais) : envSignMap.get("us"));
            String key = signData.get("key").toString();
            String id = signData.get("id").toString();

            HttpResponse httpResponse;
            HttpUriRequest httpUriRequest;
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HashMap<String, Object> response;

            try {
                if (InjectionManager.getInstance().isProduction()) {
                    if (needsSignature) {
                        parameters.put("id_usuario", id);
                        String input = Utils.mapToUrlString(parameters);
                        String sign = Utils.createHMAC(input, key);
                        parameters.put("firma", sign);
                    }
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                        nameValuePairs.add(pair);
                    }
                    httpUriRequest = new HttpPost(mUrl + mPort + url);
                    UrlEncodedFormEntity body = new UrlEncodedFormEntity(nameValuePairs);
                    ((HttpPost) httpUriRequest).setEntity(body);
                } else {
                    String query;
                    if (needsSignature) {
                        parameters.put("id_usuario", id);
                        String input = Utils.mapToUrlString(parameters);
                        String sign = Utils.createHMAC(input, key);
                        query = input + "&firma=" + sign;
                    } else {
                        query = Utils.mapToUrlString(parameters);
                    }
                    httpUriRequest = new HttpGet(mUrl + mPort + url + (query.length() > 0 ? ("?" + query) : ""));
                    Log.e("Request", mUrl + mPort + url + (query.length() > 0 ? ("?" + query) : ""));
                }

                httpResponse = defaultHttpClient.execute(httpUriRequest);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();
                for (String line; (line = bufferedReader.readLine()) != null; ) {
                    builder.append(line).append("\n");
                }

                JSONTokener jsonTokener = new JSONTokener(builder.toString());
                JSONObject jsonObject = new JSONObject(jsonTokener);
                response = (HashMap<String, Object>) Utils.toMap(jsonObject);
            } catch (UnsupportedEncodingException uee) {
                response = new HashMap<String, Object>();
                response.put("status", false);
                response.put("error", "char_encoding");
                response.put("message", "Character Conversion Unavailable");
            } catch (ClientProtocolException cpe) {
                response = new HashMap<String, Object>();
                response.put("status", false);
                response.put("error", "http_protocol");
                response.put("message", "HTTP Error Protocol");
            } catch (IOException ioe) {
                response = new HashMap<String, Object>();
                response.put("status", false);
                response.put("error", "io_exception");
                response.put("message", "Connection Un Available");
            } catch (JSONException jsone) {
                response = new HashMap<String, Object>();
                response.put("status", false);
                response.put("error", "json_exception");
                response.put("message", "Incorrect JSON Format");
            }

            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("callback", request.get("callback"));
            result.put("response", response);
            return result;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void onPostExecute(HashMap<String, Object> result) {
            IRestCallback callback = (IRestCallback) result.get("callback");
            HashMap<String, Object> response = (HashMap<String, Object>) result.get("response");
            callback.run(response);
            super.onPostExecute(result);
        }

    }
}
