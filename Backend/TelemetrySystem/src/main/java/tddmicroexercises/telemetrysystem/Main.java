package tddmicroexercises.telemetrysystem;

public class Main {
    public static void main(String[] args) {

        TelemetryClientInterface telemetryClient = new TelemetryClient();
        TelemetryDiagnosticClient diagnosticClient = new TelemetryDiagnosticClient(telemetryClient);

        try {
            diagnosticClient.checkTransmission();
            String diagnosticInfo = telemetryClient.receive();
            System.out.println("Diagnostic Info:\n" + diagnosticInfo);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
