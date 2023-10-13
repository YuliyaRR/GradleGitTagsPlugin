package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction


class PublishNewVersionTask extends RootTask{

    @TaskAction
    def publishNewVersion() {
        String newVersion = gitService.lastVersion
        def res = gitService.publishNewVersion(newVersion)

        if(res) {
            logger.log(LogLevel.LIFECYCLE, "New version ${newVersion} is published in remote repo")
        } else {
            logger.log(LogLevel.ERROR, "Something went wrong")
        }
    }




}
