package org.jboss.pnc.repositorydriver.cli;

import java.util.Collections;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.api.enums.BuildType;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCreateRequest;
import org.jboss.pnc.repositorydriver.cli.clients.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;

@CommandLine.Command(name = "create", mixinStandardHelpOptions = true)
public class Create implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Create.class);

    @RestClient
    DriverService driverService;

    @CommandLine.Option(names = { "--buildContentId" }, description = "buildID", required = true)
    String id;

    @CommandLine.Option(names = { "--type" }, description = "build type [ MVN, NPM, GRADLE, SBT, MVN_RPM ]", required = true)
    BuildType buildType;

    @CommandLine.Option(names = { "--tempBuild" }, description = "tempBuild")
    boolean tempBuild;

    @Override
    public void run() {
        RepositoryCreateRequest repositoryCreateRequest = new RepositoryCreateRequest(
                id,
                buildType,
                tempBuild,
                false,
                Collections.emptyList());
        var response = driverService.create(repositoryCreateRequest);
        log.info(
                "After create got dependency url {} and deploy url {}",
                response.getRepositoryDependencyUrl(),
                response.getRepositoryDeployUrl());
    }
}
