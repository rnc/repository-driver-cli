package org.jboss.pnc.repositorydriver.cli;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.pnc.repositorydriver.cli.clients.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * The entrypoint of the RPM cli.
 */
@TopCommand
@CommandLine.Command(
        name = "repository-driver-cli",
        description = "",
        mixinStandardHelpOptions = true,
        usageHelpWidth = 160,
        versionProvider = VersionProvider.class,
        subcommands = { Create.class }

)
public class App implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    @RestClient
    DriverService driverService;

    @Option(names = { "-v", "--verbose" }, description = "Verbose output")
    boolean verbose;

    @Option(names = { "--driver-version" }, description = "Return driver version")
    boolean version;

    //    @Option(names = "--profile", description = "PNC Configuration profile")
    //    private String profile = "default";
    //
    //    @Option(names = { "-p", "--configPath" }, description = "Path to PNC configuration folder")
    //    private String configPath = null;


    @Override
    public void run() {
        if (verbose) {
            java.util.logging.Logger.getLogger("org.jboss.pnc.repositorydriver").setLevel(Level.FINE);
            log.debug("Log level set to DEBUG");
        }

        if (version) {
            log.info(
                    "Driver {} version {}",
                    driverService.getVersion().getName(),
                    driverService.getVersion().getVersion());
        }
    }
}
