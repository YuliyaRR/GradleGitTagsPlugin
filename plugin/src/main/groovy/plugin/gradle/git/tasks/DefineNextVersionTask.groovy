package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction
import plugin.gradle.git.core.PluginConstants

class DefineNextVersionTask extends RootTask {

    @TaskAction
    String defineNextVersion() {
        def lastVersion = gitService.lastVersion
        def currentBranch = gitService.currentBranch

        if (lastVersion.isEmpty()) {
            lastVersion = PluginConstants.DEFAULT_START_VERSION
        }

        def nextVersion = gitService.getNextVersion(lastVersion, currentBranch)

        logger.log(LogLevel.LIFECYCLE, "Next version is - ${nextVersion}")

        return nextVersion
    }


}
