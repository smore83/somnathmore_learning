package tddmicroexercises.telemetrysystem;

public class TelemetryClient implements TelemetryClientInterface {
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";

    private boolean onlineStatus;
    private String diagnosticMessageResult = "";

    public boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void connect(String telemetryServerConnectionString) {
        if (telemetryServerConnectionString == null || telemetryServerConnectionString.isEmpty()) {
            throw new IllegalArgumentException("Invalid telemetry server connection string");
        }

        boolean success = simulateConnection();
        onlineStatus = success;
    }

    private boolean simulateConnection() {
        return Math.random() <= 0.8;
    }

    public void disconnect() {
        onlineStatus = false;
    }

    public void send(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Invalid message");
        }

        if (message.equals(DIAGNOSTIC_MESSAGE)) {
            simulateDiagnosticMessage();
        }
        // Real Send operation can be added here if needed
    }

    private void simulateDiagnosticMessage() {
        diagnosticMessageResult = "LAST TX rate................ 100 MBPS\r\n" +
                "HIGHEST TX rate............. 100 MBPS\r\n" +
                // ... (rest of the diagnostic message)
                "Remote Rtrn Count........... 00";
    }

    public String receive() {
        if (diagnosticMessageResult.isEmpty()) {
            return simulateReceivedMessage();
        } else {
            String message = diagnosticMessageResult;
            diagnosticMessageResult = "";
            return message;
        }
    }

    private String simulateReceivedMessage() {
        StringBuilder message = new StringBuilder();
        int messageLength = (int) (Math.random() * 50) + 60;
        for (int i = messageLength; i >= 0; i--) {
            message.append((char) (Math.random() * 40) + 86);
        }
        return message.toString();
    }
}