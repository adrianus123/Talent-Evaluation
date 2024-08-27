import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class GeneratorId {
    public static void main(String[] args) {
        generateObjectId();
    }

    private static void generateObjectId() {
        String timestamps = getTimestamps();
        String machineId = getMachineId();
        String processId = getProcessId();
        String inCounter = getIncrementNumber();
        String objectId = timestamps + machineId + processId + inCounter;

        System.out.println(objectId);
    }

    private static String getProcessId() {
        String processId = "";

        String processName = ManagementFactory.getRuntimeMXBean().getName();
        int pid = Integer.parseInt(processName.split("@")[0]);

        if (pid > 65535) {
            System.out.println("PID exceeds 2-byte limit.");
        } else {
            byte[] pidBytes = new byte[2];
            pidBytes[0] = (byte) (pid >> 8);
            pidBytes[1] = (byte) pid;

            processId = String.format("%02x%02x", pidBytes[0], pidBytes[1]);
        }

        return processId;
    }

    private static String getMachineId() {
        try {
            String machineId = "";

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null && mac.length >= 3) {
                    byte[] machineIdentifier = new byte[3];
                    System.arraycopy(mac, 0, machineIdentifier, 0, 3);

                    machineId = String.format("%02X%02X%02X", machineIdentifier[0], machineIdentifier[1],
                            machineIdentifier[2]);
                    break;
                }
            }

            return machineId;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getTimestamps() {
        long currentTimeMillis = System.currentTimeMillis();
        int epochTime = (int) (currentTimeMillis / 1000);
        return Integer.toHexString(epochTime);
    }

    private static String getIncrementNumber() {
        int counter = (int) Math.floor(Math.random() * 0xffffff);
        counter = (counter + 1) & 0xffffff;
        return Integer.toHexString(counter);
    }
}
