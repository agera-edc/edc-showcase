package org.eclipse.dataspaceconnector.verifiablecredential;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This is not an actual unit test, it is merely a utility to read pem files and get the parameters
 */
public class PemKeyReaderUtility {

    @Test
    @Disabled
    void readPemFile() {
        var jwk1 = parsePemAsJWK("/home/paul/dev/ion-demo/keys2/public.pem");
//        var jwk2 = parsePemAsJWK("/home/paul/dev/ion-demo/keys2/provider-pub.pem");
//        var jwk3 = parsePemAsJWK("/home/paul/dev/ion-demo/keys2/verifier-pub.pem");
    }

    private JWK parsePemAsJWK(String resourceName) {

        try {
            var pemContents = Files.readString(Path.of(resourceName));
            return ECKey.parseFromPEMEncodedObjects(pemContents);

        } catch (JOSEException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}