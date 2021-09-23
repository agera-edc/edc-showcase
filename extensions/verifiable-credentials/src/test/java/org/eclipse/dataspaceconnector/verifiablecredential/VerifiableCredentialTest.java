package org.eclipse.dataspaceconnector.verifiablecredential;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.ECKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.dataspaceconnector.verifiablecredential.TestHelper.readFile;

class VerifiableCredentialTest {


    private ECKey privateKey;

    @BeforeEach
    void setup() throws JOSEException {
        String contents = readFile("private_p256.pem");

        privateKey = (ECKey) ECKey.parseFromPEMEncodedObjects(contents);
    }


    @Test
    void createVerifiableCredential() throws ParseException {
        var vc = VerifiableCredential.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");

        assertThat(vc).isNotNull();
        assertThat(vc.getJWTClaimsSet().getClaim("did-url")).isEqualTo("someUrl");
        assertThat(vc.getJWTClaimsSet().getClaim("iss")).isEqualTo("test-connector");
        assertThat(vc.getJWTClaimsSet().getClaim("sub")).isEqualTo("verifiable-credential");
        assertThat(vc.getJWTClaimsSet().getExpirationTime()).isNotNull()
                .isAfter(Instant.now())
                .isBefore(Instant.now().plus(11, ChronoUnit.MINUTES));
    }

    @Test
    void ensureSerialization() throws ParseException {
        var vc = VerifiableCredential.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");

        assertThat(vc).isNotNull();
        String jwtString = vc.serialize();

        //deserialize
        var deserialized = VerifiableCredential.parse(jwtString);

        assertThat(deserialized.getJWTClaimsSet()).isEqualTo(vc.getJWTClaimsSet());
        assertThat(deserialized.getHeader().getAlgorithm()).isEqualTo(vc.getHeader().getAlgorithm());
        assertThat(deserialized.getPayload().toString()).isEqualTo(vc.getPayload().toString());
    }

    @Test
    void verifyJwt() throws JOSEException {
        var vc = VerifiableCredential.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");
        String jwtString = vc.serialize();

        //deserialize
        var jwt = VerifiableCredential.parse(jwtString);
        var pubKey = readFile("public_p256.pem");

        assertThat(VerifiableCredential.verify(jwt, (ECKey) ECKey.parseFromPEMEncodedObjects(pubKey))).isTrue();

    }
}