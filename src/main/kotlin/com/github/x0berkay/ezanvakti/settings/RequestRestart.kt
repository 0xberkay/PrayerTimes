package com.github.x0berkay.ezanvakti.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages

fun requestRestart(project: Project) {
    val confirmation = Messages.showYesNoDialog(
        project,
        "Do you want to restart the IDE?",
        "Restart IDE",
        Messages.getQuestionIcon()
    )

    if (confirmation == Messages.YES) {
        ApplicationManager.getApplication().invokeLater {
            ProjectManager.getInstance().reloadProject(project)
        }
    }
}