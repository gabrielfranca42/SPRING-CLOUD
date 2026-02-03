package cloudspring.springcloud.grpc;


// --- Imports Corretos ---
import cloudspring.billing.grpc.BillingRequest;
import cloudspring.billing.grpc.BillingResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value; // Import correto do Value
import org.springframework.stereotype.Service;

// Tente importar a classe gerada. Se ficar vermelho, coloque o mouse e dê ALT+ENTER
import cloudspring.billing.grpc.BillingServiceGrpc;

@Slf4j
@Service
public class BillingServiceGrpcClient {

    // A referência correta é BillingServiceGrpc (gerada), não BillingServiceGrpcClient (atual)
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort) {

        log.info("Connecting to billing service grpc at {}:{}", serverAddress, serverPort);

        // 1. Criar o Canal
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();

        // 2. Inicializar o Stub usando o canal (Isso faltava no seu código)
        this.blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name,
                                                String email){
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId)
                .setName(name).setEmail(email).build();

        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("recebeu essa porra: {}",response);
        return response;

    }
}