package plugin.gradle.git.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import plugin.gradle.git.core.PluginConstants
import plugin.gradle.git.service.api.IGitService
import plugin.gradle.git.service.factory.GitServiceSingleton

abstract class RootTask extends DefaultTask{
    protected IGitService gitService

    RootTask() {
        this.gitService = GitServiceSingleton.instance
        this.setGroup(PluginConstants.TASK_GROUP)
    }

    @Internal
    Object getResult() {
        this.getExtensions().getByName(PluginConstants.RESULT_TASK)
    }

    void addResult(Object obj) {
        this.extensions.add(PluginConstants.RESULT_TASK, obj)
    }
}
