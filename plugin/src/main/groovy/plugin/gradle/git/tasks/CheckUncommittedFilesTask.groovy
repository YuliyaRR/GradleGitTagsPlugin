package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction
import plugin.gradle.git.core.PluginConstants

class CheckUncommittedFilesTask extends RootTask{

    @TaskAction
    def checkUncommittedFiles() {
        def uncFiles = gitService.checkUncommittedFiles()

        if (uncFiles != 0) {
            def lastVersion = gitService.lastVersion

            if(lastVersion.isEmpty()) {
                logger.log(LogLevel.WARN, String.format(PluginConstants.LOG_MESSAGE_FOR_UNCOMMITTED_FILES_WITHOUT_VERSION, uncFiles))
            } else {
                logger.log(LogLevel.WARN, String.format(PluginConstants.LOG_MESSAGE_FOR_UNCOMMITTED_FILES, lastVersion, uncFiles))
            }

        } else {
            logger.log(LogLevel.LIFECYCLE, "You have no uncommitted changes")
        }

        this.addResult(uncFiles == 0)
    }
}
