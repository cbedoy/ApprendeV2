package com.cbedoy.apprende.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import com.cbedoy.apprende.activity.ApplicationLoader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;


/**
 * Created by Carlos on 14/10/2014.
 */
public class BlurService
{
    public static BlurService instance;
    public static BlurService getInstance(){
        if(instance == null){
            instance = new BlurService();
        }
        return instance;
    }

    public Bitmap blurRenderScript(Bitmap smallBitmap) {
        Bitmap output = Bitmap.createBitmap(smallBitmap.getWidth(), smallBitmap.getHeight(), smallBitmap.getConfig());
        RenderScript rs = RenderScript.create(ApplicationLoader.mainContext);
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation inAlloc = Allocation.createFromBitmap(rs, smallBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_GRAPHICS_TEXTURE);
        Allocation outAlloc = Allocation.createFromBitmap(rs, output);
        script.setRadius(25);
        script.setInput(inAlloc);
        script.forEach(outAlloc);
        outAlloc.copyTo(output);
        rs.destroy();
        return output;
    }


    public Bitmap performRequestByImage(String url){
        BlurAsyncService blurAsyncService = new BlurAsyncService();
        blurAsyncService.execute(url);
        try {
            return blurAsyncService.get();
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }
    }

    public Bitmap performRequestBlurByImage(Bitmap bitmap){
        DoAsyncBlur doAsyncBlur = new DoAsyncBlur();
        doAsyncBlur.execute(bitmap);
        try {
            return doAsyncBlur.get();
        } catch (InterruptedException e) {
            return bitmap;
        } catch (ExecutionException e) {
            return bitmap;
        }
    }

    private class BlurAsyncService extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            return downloadBitmap(strings[0]);
        }

        private Bitmap downloadBitmap(String url) {
            final DefaultHttpClient client = new DefaultHttpClient();
            final HttpGet getRequest = new HttpGet(InjectionManager.MEDIA_URL+url);
            try {
                HttpResponse response = client.execute(getRequest);
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }
                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = entity.getContent();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                getRequest.abort();
            }
            return null;
        }
    }

    private class DoAsyncBlur extends AsyncTask<Bitmap, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Bitmap... bitmaps) {
            return blurRenderScript(bitmaps[0]);

        }
    }
}
