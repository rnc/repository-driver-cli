package org.jboss.pnc.repositorydriver.cli.clients;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.pnc.api.dto.ComponentVersion;
import org.jboss.pnc.api.repositorydriver.dto.ArchiveRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCollectRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCreateRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCreateResponse;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryPromoteRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryPromoteResult;

@ApplicationScoped
@RegisterRestClient(configKey = "repository-driver-service")
@Path("/")
public interface DriverService {

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/create")
    @ClientHeaderParam(name = "log-process-context", value = "cli-context")
    RepositoryCreateResponse create(
            RepositoryCreateRequest repositoryCreateRequest,
            @HeaderParam("log-tmp") String logTmp,
            @HeaderParam("log-exp") String logExp,
            @HeaderParam("log-user-id") String userId);

    @PUT
    @Path("/seal")
    void seal(String buildContentId);

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/promote")
    @ClientHeaderParam(name = "log-process-context", value = "cli-context")
    void promote(
            RepositoryPromoteRequest promoteRequest,
            @HeaderParam("log-tmp") String logTmp,
            @HeaderParam("log-exp") String logExp,
            @HeaderParam("log-user-id") String userId);

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/archive")
    void archive(ArchiveRequest archiveRequest);

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}/repository-manager-result")
    RepositoryPromoteResult collectRepoManagerResult(
            @PathParam("id") String buildContentId,
            RepositoryCollectRequest collectRequest);

    @GET
    @Path("/version")
    ComponentVersion getVersion();

}
