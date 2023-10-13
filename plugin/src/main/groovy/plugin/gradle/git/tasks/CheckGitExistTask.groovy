package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class CheckGitExistTask extends RootTask{

    @TaskAction
    def checkGitIsInstalled() {
        def installed = gitService.isGitInstalled()
        this.addResult(installed)

        if (!installed) {
            logger.log(LogLevel.WARN, "Git isn't installed in your system. You have to install it to continue working")
        }
    }


}
