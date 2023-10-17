package tddmicroexercises.telemetrysystem;

public interface TelemetryClientInterface extends OnlineStatusProvider, TelemetrySender, TelemetryReceiver, TelemetryConnector {
    // No additional methods here
}