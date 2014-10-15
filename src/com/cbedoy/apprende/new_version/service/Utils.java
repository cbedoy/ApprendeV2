package com.cbedoy.apprende.new_version.service;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.UrlQuerySanitizer;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Carlos on 14/10/2014.
 */
public class Utils
{
    private static Handler mHandler;

    public static void init(Activity activity) {
        mHandler = new Handler(activity.getMainLooper());
    }

    public static void deInit() {
        mHandler = null;
    }

    public static void postRunnable(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static void postRunnableAtFrontOfQueue(Runnable runnable) {
        mHandler.postAtFrontOfQueue(runnable);
    }

    public static void postDelayedRunnable(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }

    public static HashMap<String, Object> mergeHashMaps(HashMap<String, Object> map1, HashMap<String, Object> map2) {
        HashMap<String, Object> merge = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            merge.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            merge.put(entry.getKey(), entry.getValue());
        }

        return merge;
    }

    public static Object toJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable) object)) {
                json.put(value);
            }
            return json;
        } else
            return object;
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else {
            if (json instanceof JSONObject) {
                return toMap((JSONObject) json);
            } else {
                if (json instanceof JSONArray) {
                    return toList((JSONArray) json);
                } else {
                    return json;
                }
            }
        }
    }

    public static String mapToUrlString(HashMap<String, Object> map) {
        String result = "";
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String to_append = URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value.toString(), "UTF-8");
                result = result + (result.length() > 0 ? "&" : "") + to_append;
            }
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }

    public static boolean isValidEmail(String eMail) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(eMail);
        return matcher.matches();
    }

    public static String createHMAC(String input, String key) {
        String hmac;
        try {
            Formatter formatter = new Formatter();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            for (byte b : mac.doFinal(input.getBytes())) {
                formatter.format("%02x", b);
            }
            hmac = formatter.toString();
            formatter.close();
        } catch (Exception ex) {
            hmac = "";
        }
        return hmac;
    }

    public static String createMD5(String raw) {
        String md5;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(raw.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            md5 = hexString.toString();
        } catch (Exception ex) {
            md5 = raw;
        }
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

    public static String createMD5(String input, String key) {
        String hmac;
        try {
            Formatter formatter = new Formatter();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            for (byte b : mac.doFinal(input.getBytes())) {
                formatter.format("%02x", b);
            }
            hmac = formatter.toString();
            formatter.close();
        } catch (Exception ex) {
            hmac = "";
        }
        return hmac;
    }

    public static String createSHA1(String raw) {
        String sha1;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update(raw.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            sha1 = hexString.toString();
        } catch (Exception ex) {
            sha1 = raw;
        }
        while (sha1.length() < 32) {
            sha1 = "0" + sha1;
        }
        return sha1;
    }

    public static String unescapeString(String text) {
        Pattern pattern = Pattern.compile("\\\\u([\\d\\w]{4})");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int regexIdx = 1;
            String match = matcher.group(regexIdx);
            int value = Integer.parseInt(match, 16);
            String charValue = fromCharCode(value);
            text = text.replace(String.format("\\u%s", match), charValue);
        }
        return text;
    }

    public static Drawable getDrawableImage(String url){
        return null;
    }

    public static String fromCharCode(int... codePoints) {
        StringBuilder builder = new StringBuilder(codePoints.length);
        for (int codePoint : codePoints) {
            builder.append(Character.toChars(codePoint));
        }
        return builder.toString();
    }

    public static String unescape(String in) {
        String unescaped = new UrlQuerySanitizer().unescape(in);
        String out = null;
        try {
            out = new String(unescaped.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            out = in;
        }
        return out;
    }

    public static String unescape_perl_string(String oldstr) {

    /*
     * In contrast to fixing Java's broken regex charclasses,
     * this one need be no bigger, as unescaping shrinks the string
     * here, where in the other one, it grows it.
     */

        StringBuilder newstr = new StringBuilder(oldstr.length());

        boolean saw_backslash = false;

        for (int i = 0; i < oldstr.length(); i++) {
            int cp = oldstr.codePointAt(i);
            if (oldstr.codePointAt(i) > Character.MAX_VALUE) {
                i++; /****WE HATES UTF-16! WE HATES IT FOREVERSES!!!****/
            }

            if (!saw_backslash) {
                if (cp == '\\') {
                    saw_backslash = true;
                } else {
                    newstr.append(Character.toChars(cp));
                }
                continue; /* switch */
            }

            if (cp == '\\') {
                saw_backslash = false;
                newstr.append('\\');
                newstr.append('\\');
                continue; /* switch */
            }

            switch (cp) {

                case 'r':
                    newstr.append('\r');
                    break; /* switch */

                case 'n':
                    newstr.append('\n');
                    break; /* switch */

                case 'f':
                    newstr.append('\f');
                    break; /* switch */

            /* PASS a \b THROUGH!! */
                case 'b':
                    newstr.append("\\b");
                    break; /* switch */

                case 't':
                    newstr.append('\t');
                    break; /* switch */

                case 'a':
                    newstr.append('\007');
                    break; /* switch */

                case 'e':
                    newstr.append('\033');
                    break; /* switch */

            /*
             * A "control" character is what you get when you xor its
             * codepoint with '@'==64.  This only makes sense for ASCII,
             * and may not yield a "control" character after all.
             *
             * Strange but true: "\c{" is ";", "\c}" is "=", etc.
             */
                case 'c': {
                    if (++i == oldstr.length()) {
                        die("trailing \\c");
                    }
                    cp = oldstr.codePointAt(i);
                /*
                 * don't need to grok surrogates, as next line blows them up
                 */
                    if (cp > 0x7f) {
                        die("expected ASCII after \\c");
                    }
                    newstr.append(Character.toChars(cp ^ 64));
                    break; /* switch */
                }

                case '8':
                case '9':
                    die("illegal octal digit");
                      /* NOTREACHED */

    /*
     * may be 0 to 2 octal digits following this one
     * so back up one for fallthrough to next case;
     * unread this digit and fall through to next case.
     */
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    --i;
                      /* FALLTHROUGH */

            /*
             * Can have 0, 1, or 2 octal digits following a 0
             * this permits larger values than octal 377, up to
             * octal 777.
             */
                case '0': {
                    if (i + 1 == oldstr.length()) {
                    /* found \0 at end of string */
                        newstr.append(Character.toChars(0));
                        break; /* switch */
                    }
                    i++;
                    int digits = 0;
                    int j;
                    for (j = 0; j <= 2; j++) {
                        if (i + j == oldstr.length()) {
                            break; /* for */
                        }
                    /* safe because will unread surrogate */
                        int ch = oldstr.charAt(i + j);
                        if (ch < '0' || ch > '7') {
                            break; /* for */
                        }
                        digits++;
                    }
                    if (digits == 0) {
                        --i;
                        newstr.append('\0');
                        break; /* switch */
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(
                                oldstr.substring(i, i + digits), 8);
                    } catch (NumberFormatException nfe) {
                        die("invalid octal value for \\0 escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += digits - 1;
                    break; /* switch */
                } /* end case '0' */

                case 'x': {
                    if (i + 2 > oldstr.length()) {
                        die("string too short for \\x escape");
                    }
                    i++;
                    boolean saw_brace = false;
                    if (oldstr.charAt(i) == '{') {
                        /* ^^^^^^ ok to ignore surrogates here */
                        i++;
                        saw_brace = true;
                    }
                    int j;
                    for (j = 0; j < 8; j++) {

                        if (!saw_brace && j == 2) {
                            break;  /* for */
                        }

                    /*
                     * ASCII test also catches surrogates
                     */
                        int ch = oldstr.charAt(i + j);
                        if (ch > 127) {
                            die("illegal non-ASCII hex digit in \\x escape");
                        }

                        if (saw_brace && ch == '}') {
                            break; /* for */
                        }

                        if (!((ch >= '0' && ch <= '9')
                                ||
                                (ch >= 'a' && ch <= 'f')
                                ||
                                (ch >= 'A' && ch <= 'F')
                        )
                                ) {
                            die(String.format(
                                    "illegal hex digit #%d '%c' in \\x", ch, ch));
                        }

                    }
                    if (j == 0) {
                        die("empty braces in \\x{} escape");
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\x escape");
                    }
                    newstr.append(Character.toChars(value));
                    if (saw_brace) {
                        j++;
                    }
                    i += j - 1;
                    break; /* switch */
                }

                case 'u': {
                    if (i + 4 > oldstr.length()) {
                        die("string too short for \\u escape");
                    }
                    i++;
                    int j;
                    for (j = 0; j < 4; j++) {
                    /* this also handles the surrogate issue */
                        if (oldstr.charAt(i + j) > 127) {
                            die("illegal non-ASCII hex digit in \\u escape");
                        }
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\u escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += j - 1;
                    break; /* switch */
                }

                case 'U': {
                    if (i + 8 > oldstr.length()) {
                        die("string too short for \\U escape");
                    }
                    i++;
                    int j;
                    for (j = 0; j < 8; j++) {
                    /* this also handles the surrogate issue */
                        if (oldstr.charAt(i + j) > 127) {
                            die("illegal non-ASCII hex digit in \\U escape");
                        }
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\U escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += j - 1;
                    break; /* switch */
                }

                default:
                    newstr.append('\\');
                    newstr.append(Character.toChars(cp));
           /*
            * say(String.format(
            *       "DEFAULT unrecognized escape %c passed through",
            *       cp));
            */
                    break; /* switch */

            }
            saw_backslash = false;
        }

    /* weird to leave one at the end */
        if (saw_backslash) {
            newstr.append('\\');
        }

        return newstr.toString();
    }

    /*
     * Return a string "U+XX.XXX.XXXX" etc, where each XX set is the
     * xdigits of the logical Unicode code point. No bloody brain-damaged
     * UTF-16 surrogate crap, just true logical characters.
     */
    public static String uniplus(String s) {
        if (s.length() == 0) {
            return "";
        }
     /* This is just the minimum; sb will grow as needed. */
        StringBuffer sb = new StringBuffer(2 + 3 * s.length());
        sb.append("U+");
        for (int i = 0; i < s.length(); i++) {
            sb.append(String.format("%X", s.codePointAt(i)));
            if (s.codePointAt(i) > Character.MAX_VALUE) {
                i++; /****WE HATES UTF-16! WE HATES IT FOREVERSES!!!****/
            }
            if (i + 1 < s.length()) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static void die(String foa) {
        throw new IllegalArgumentException(foa);
    }

    private static void say(String what) {
        //System.out.println(what);
    }
}
