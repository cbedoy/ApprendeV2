package com.cbedoy.apprende.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;


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

    public Bitmap blurRenderScript(Bitmap smallBitmap, Context context) {

        Bitmap output = Bitmap.createBitmap(smallBitmap.getWidth(), smallBitmap.getHeight(), smallBitmap.getConfig());

        RenderScript rs = RenderScript.create(context);
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
}
