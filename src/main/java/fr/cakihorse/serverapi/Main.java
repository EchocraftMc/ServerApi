package fr.cakihorse.serverapi;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class Main {
    public static void uploadFile(Uri serverUrl, String pathFile) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(String.valueOf(serverUrl));

        File file = new File(getFile(pathFile));

        MultipartEntity entity = new MultipartEntity();
        entity.addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY));

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);
        System.out.println(response);

    }

    /*
    public static void fileDownload() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:3000/file/filename.txt"); // Remplace avec l'URL de ton serveur

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream inputStream = entity.getContent();
                 OutputStream outputStream = new FileOutputStream("filename.txt")) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        EntityUtils.consume(entity);
    }
    */

    public static void setFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
    public static String getFile(String path) {
        File file = new File(path);

        return path;
    }
}
