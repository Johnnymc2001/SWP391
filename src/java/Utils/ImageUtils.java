/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import com.cloudinary.utils.ObjectUtils;
import java.util.Map;

/**
 *
 * @author JohnnyMC
 */
public class ImageUtils {

    public static byte[] InputStreamToBytes(InputStream is) throws IOException {
        return IOUtils.toByteArray(is);
    }

    public static String BytesToBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    // SET IS TO BYTE ARRAY
    // Main Image Upload
    public static String uploadImage(String base64) {
        try {
            String finalImageData = "data:image/png;base64," + base64;

            Map authConfig = ObjectUtils.asMap(
                    "cloud_name", "swpgogogo",
                    "api_key", "274465474966931",
                    "api_secret", "XfhGKe_VQyV8X1tdnNwDuvlf47k",
                    "secure", true);

            Map uploadConfig = ObjectUtils.asMap(
                    "folder", "attachments");

            Cloudinary cloudinary = new Cloudinary(authConfig);
            System.out.println("New file is being uploading....");
            Map uploadResult = cloudinary.uploader().upload(finalImageData, uploadConfig);
            System.out.println("File uploaded! [URL : " + uploadResult.get("secure_url") + " ]");
            return (String) uploadResult.get("secure_url");
        } catch (Exception ex) {
        }
        return null;
    }

//    public static String uploadImage(String base64) {
//        Map result = ImageUtils.uploadImage(base64);
//        String url = (String) result.get("secure_url");
//
//        return url;
//    }
    public static String uploadImage(InputStream is) {
        try {
            String base64 = BytesToBase64(InputStreamToBytes(is));

            return uploadImage(base64);
        } catch (IOException ex) {
        }
        return "";
    }
    // Multi Image Upload
//    public static boolean uploadImage(ArrayList<String> listOfBase64, int blogId) {
//        boolean sucess = true;
//        for (String base64 : listOfBase64) {
//            uploadImage(base64, blogId);
//        }
//        return false;
//
//    }
//    public static void main(String[] args) {
//        ImageUtils.uploadImage(
//                "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII=",
//                2
//        );
//    }
}
