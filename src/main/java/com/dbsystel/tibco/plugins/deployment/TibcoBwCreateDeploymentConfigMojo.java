package com.dbsystel.tibco.plugins.deployment;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import com.dbsystel.tibco.plugins.common.AbstractTibcoBwDeploymentMojo;

@Mojo(name="create-deploy-config", threadSafe=true)
@Execute(phase = LifecyclePhase.PREPARE_PACKAGE)
public class TibcoBwCreateDeploymentConfigMojo extends AbstractTibcoBwDeploymentMojo {
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (isSkip()) {
            getLog().debug(String.format("Skipping Creating DeploymentConfig of %s:%s", project.getGroupId(), project.getArtifactId()));
            return;
        }
	}

}
