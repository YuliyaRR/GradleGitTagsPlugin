package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class AssignAndPublishNewVersionTask extends RootTask{
    @TaskAction
    def assignAndPublishNewVersion() {
        def doesRemoteRepoExist = gitService.doesRemoteRepoExist()

        if (!doesRemoteRepoExist) {
            logger.log(LogLevel.WARN, "You don't have a remote repo to push new version")
        } else {
            String newVersion = gitService.lastVersion
            def res = gitService.publishNewVersion(newVersion)
            if (res) {
                logger.log(LogLevel.LIFECYCLE, "New version ${newVersion} is published in remote repo")
            } else {
                logger.log(LogLevel.ERROR, "Something went wrong and version wasn't published in remote repo")
            }
        }

    }
}
