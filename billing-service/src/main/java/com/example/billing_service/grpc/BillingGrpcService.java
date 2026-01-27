package com.example.billing_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    // Instância manual do Logger sem usar Lombok
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount requested: {}", billingRequest.toString());

        // O gRPC gera métodos baseados no seu arquivo .proto.
        // Se der erro em 'setAccountId', verifique se no .proto não está 'accout_id'
        BillingResponse response = BillingResponse.newBuilder()
                .setAccoutId("12345") // CamelCase gerado a partir de account_id
                .setStatus("active")   // CamelCase gerado a partir de status
                .build();

        // Envia a resposta ao cliente
        responseObserver.onNext(response);

        // Finaliza a chamada (obrigatório no gRPC)
        responseObserver.onCompleted();
    }
}