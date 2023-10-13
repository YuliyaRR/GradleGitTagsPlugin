package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class DefineNextVersionTask extends RootTask {

    @TaskAction
    String defineNextVersion() {
        def lastVersion = gitService.lastVersion
        def currentBranch = gitService.currentBranch

        def nextVersion = gitService.getNextVersion(lastVersion, currentBranch)

        logger.log(LogLevel.LIFECYCLE, "Next version is - ${nextVersion}")

        return nextVersion
    }


}
