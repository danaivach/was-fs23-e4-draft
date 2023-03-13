package solid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class Pod extends Artifact {

    private String podURL;

    public void init(String podURL) {
        this.podURL = podURL;
        log("Pod artifact initialized for: " + this.podURL);
    }

    @OPERATION
    public void createContainer(String containerName) {
        try {
            URI uri = new URI(podURL + "/" + containerName + "/");

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
             
            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201) {
                    log("Error message: " + response.body());
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @OPERATION
    public void readData(String containerName, String fileName, OpFeedbackParam<Object[]> data) {
        data.set(readData(containerName, fileName));
    }

    public Object[] readData(String containerName, String fileName) {
       
        try {
            URI uri = new URI(podURL + "/" + containerName + "/" + fileName);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
             
            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    log("Error message: " + response.body());
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                } else {
                    return createArrayFromString(response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new Object[0];
    }

    @OPERATION
    public void publishData(String containerName, String fileName, Object[] data) {
        try {
            URI uri = new URI(podURL + "/" + containerName + "/" + fileName);

            String requestBody = createStringFromArray(data);
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "text/plain")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
            
            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201 && response.statusCode() != 205) {
                    log("Error message: " + response.body());
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String createStringFromArray(Object[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : array) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }

    public static Object[] createArrayFromString(String str) {
        return str.split("\n");
    }

    @OPERATION
    public void updateData(String containerName, String fileName, Object[] data) {
        Object[] oldData = readData(containerName, fileName);
        Object[] allData = new Object[oldData.length + data.length];
        System.arraycopy(oldData, 0, allData, 0, oldData.length);
        System.arraycopy(data, 0, allData, oldData.length, data.length);
        publishData(containerName, fileName, allData);
    }
}
