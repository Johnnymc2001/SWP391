/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author JohnnyMC
 */
public class ImageUtils {

//    public static Date getCurrentDateAsSQLDate() {
//        return new Date(System.currentTimeMillis());
//    }

    public static byte[] InputStreamToBytes(InputStream is) throws IOException {
        return IOUtils.toByteArray(is);
    }

    public static String BytesToBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }
}
