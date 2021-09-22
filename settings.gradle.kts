rootProject.name = "ion-demo"

include(":launchers:consumer")
include(":launchers:hub")
include(":launchers:junit")

include(":extensions:identity-hub-verifier")
include(":identity-common-test")

include(":extensions:ion-client-mock")
include(":extensions:verifiable-credentials")
include(":extensions:distributed-identity-service")

include(":extensions:dataseeding:hub")
include(":extensions:dataseeding:catalog")
