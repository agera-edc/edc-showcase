plugins {
    `java-library`
}

val edcversion: String by project
val group = "org.eclipse.dataspaceconnector"

dependencies {
    api("${group}:iam.identity-did-spi:${edcversion}")

    testImplementation(testFixtures(project(":identity-common-test")))

}