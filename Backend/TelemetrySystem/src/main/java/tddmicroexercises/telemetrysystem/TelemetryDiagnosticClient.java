package tddmicroexercises.telemetrysystem;


class TelemetryDiagnosticClient {
    private final String DiagnosticChannelConnectionString = "*111#";
    private final TelemetryClientInterface telemetryClient;

    public TelemetryDiagnosticClient(TelemetryClientInterface telemetryClient) {
        this.telemetryClient = telemetryClient;
    }

    public void checkTransmission() throws Exception {
        telemetryClient.disconnect();

        int retryLeft = 3;
        while (!telemetryClient.getOnlineStatus() && retryLeft > 0) {
            telemetryClient.connect(DiagnosticChannelConnectionString);
            retryLeft--;
        }

        if (!telemetryClient.getOnlineStatus()) {
            throw new Exception("Unable to connect.");
        }

        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
    }
}