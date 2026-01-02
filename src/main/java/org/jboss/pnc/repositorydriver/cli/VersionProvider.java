package org.jboss.pnc.repositorydriver.cli;

import org.jboss.pnc.mavenmanipulator.common.util.ManifestUtils;

import picocli.CommandLine;

public class VersionProvider implements CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() {
        return new String[] {
                "Repository-Driver-CLI ",
                ManifestUtils.getManifestInformation(VersionProvider.class)
        };
    }
}
