package solid;

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
        log("Implement the method createContainer()");
    }

    @OPERATION
    public void publishData(String containerName, String fileName, Object[] data) {
        log("Implement the method publishData()");
    }

    @OPERATION
    public void readData(String containerName, String fileName, OpFeedbackParam<Object[]> data) {
        data.set(readData(containerName, fileName));
    }

    public Object[] readData(String containerName, String fileName) {
        log("Implement the method readData(). Currently, the method returns mock data");

        // Remove the following mock responses once you have implemented the method
        switch(fileName) {
            case "watchlist.txt":
                Object[] mockWatchlist = new Object[]{"Matrix", "Inseption", "Avengers: Endgame"};
                return mockWatchlist;
            case "sleep.txt":
                Object[] mockSleepData = new Object[]{"6", "7", "5"};
                return mockSleepData;
            case "trail.txt":
                Object[] mockTrailData = new Object[]{"3", "5.5", "5.5"};
                return mockTrailData; 
            default:
                return new Object[0];
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
