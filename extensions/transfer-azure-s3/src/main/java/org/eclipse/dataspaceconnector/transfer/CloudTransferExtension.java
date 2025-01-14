package org.eclipse.dataspaceconnector.transfer;

import net.jodah.failsafe.RetryPolicy;
import org.eclipse.dataspaceconnector.azure.blob.core.api.BlobStoreApi;
import org.eclipse.dataspaceconnector.spi.asset.DataAddressResolver;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.transfer.flow.DataFlowManager;
import org.eclipse.dataspaceconnector.spi.transfer.inline.DataOperatorRegistry;

public class CloudTransferExtension implements ServiceExtension {


    @Inject
    private DataFlowManager dataFlowMgr;
    @Inject
    private DataAddressResolver dataAddressResolver;
    @Inject
    private DataOperatorRegistry registry;
    @Inject
    private RetryPolicy<Object> retryPolicy;
    @Inject
    private BlobStoreApi blobStoreApi;

    @Override
    public void initialize(ServiceExtensionContext context) {
//        registry.registerWriter(new S3BucketWriter(context.getMonitor(), context.getTypeManager(), retryPolicy));
//        registry.registerWriter(new BlobStoreWriter(context.getMonitor(), context.getTypeManager()));
//        registry.registerReader(new BlobStoreReader(blobStoreApi));
//        registry.registerReader(new S3BucketReader());
//        var flowController = new InlineDataFlowController(context.getService(Vault.class), context.getMonitor(), registry, dataAddressResolver);
//        dataFlowMgr.register(flowController);

        context.getMonitor().info("Initialized transfer extension");
    }
}
