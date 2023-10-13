package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class DefineCurrentBranchTask extends RootTask{

    @TaskAction
    def defineCurrentBrunch() {
        def currentBranch = gitService.getCurrentBranch()

        logger.log(LogLevel.LIFECYCLE, "Current branch - ${currentBranch}")
    }
}
