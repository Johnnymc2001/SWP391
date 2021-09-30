/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import javax.imageio.ImageIO;
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

    public static String resizeImageFromInputStream(InputStream is, int width, int height) throws IOException {
        BufferedImage image = ImageIO.read(is);
            Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
    
//        BufferedImage scaledImage = new BufferedImage((width), (height), BufferedImage.SCALE_SMOOTH);

//        final AffineTransform at = AffineTransform.getScaleInstance(2.0, 2.0);
//        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
//
//        scaledImage = ato.filter(image, scaledImage);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(resized, "png", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        return Base64.getEncoder().encodeToString(os.toByteArray());
        
    }
}
