/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.beryx.jar

import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.provider.Property

@CompileStatic
class ModuleConfigExtension {
    private final Project project
    final Property<String> moduleInfoPath
    final Property<Boolean> multiRelease
    final Property<String> version
    final Property<Integer> moduleInfoCompatibility
    final Property<Boolean> neverCompileModuleInfo

    static class ModuleData {
        final String moduleInfoPath
        final boolean multiRelease
        final String version
        final int moduleInfoCompatibility
        final boolean neverCompileModuleInfo

        ModuleData(String moduleInfoPath, boolean multiRelease,
                   String version, int moduleInfoCompatibility, boolean neverCompileModuleInfo) {
            this.moduleInfoPath = moduleInfoPath
            this.multiRelease = multiRelease
            this.version = version
            this.moduleInfoCompatibility = moduleInfoCompatibility
            this.neverCompileModuleInfo = neverCompileModuleInfo
        }
    }

    ModuleConfigExtension(Project project) {
        this.project = project

        this.moduleInfoPath = project.objects.property(String)
        this.multiRelease = project.objects.property(Boolean)
        this.version = project.objects.property(String)
        this.moduleInfoCompatibility = project.objects.property(Integer)
        this.neverCompileModuleInfo = project.objects.property(Boolean)
    }

    ModuleData getData() {
        new ModuleData(
                moduleInfoPath.getOrElse(''),
                multiRelease.getOrElse(true),
                version.getOrElse(''),
                moduleInfoCompatibility.getOrElse(9),
                neverCompileModuleInfo.getOrElse(false)
        )
    }
}
