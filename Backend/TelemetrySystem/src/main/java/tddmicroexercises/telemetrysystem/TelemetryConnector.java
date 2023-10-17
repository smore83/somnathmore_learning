package tddmicroexercises.telemetrysystem;

public interface TelemetryConnector {
    void connect(String telemetryServerConnectionString);

    void disconnect();
}
