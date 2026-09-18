package com.zomdroid.game;

import androidx.annotation.NonNull;

public class InstallationPreset {
    public final String name;
    public final String[] classPathArray;
    public final String[] extraJars;
    public final String[] libraryPathArray;
    public final String[] libraryPathForEmulationArray;
    public final String fmodLibraryPath;
    public final String[] extraJvmArgs;
    public final String[] args;
    public final String mainClassName;
    public final String javaAgentPath;
    public final String javaAgentArgs;
    public final String verificationFile;
    public final String gameFilesCheckFile;

    private InstallationPreset(Builder builder) {
        this.name = builder.name;
        this.classPathArray = builder.classPathArray;
        this.extraJars = builder.extraJars;
        this.libraryPathArray = builder.libraryPathArray;
        this.libraryPathForEmulationArray = builder.libraryPathForEmulationArray;
        this.fmodLibraryPath = builder.fmodLibraryPath;
        this.extraJvmArgs = builder.extraJvmArgs;
        this.args = builder.args;
        this.mainClassName = builder.mainClassName;
        this.javaAgentPath = builder.javaAgentPath;
        this.javaAgentArgs = builder.javaAgentArgs;
        this.verificationFile = builder.verificationFile;
        this.gameFilesCheckFile = builder.gameFilesCheckFile;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public static class Builder {
        private String name = "";
        private String[] classPathArray = new String[0];
        private String[] extraJars = new String[0];
        private String[] libraryPathArray = new String[0];
        private String[] libraryPathForEmulationArray = new String[0];
        private String fmodLibraryPath = "";
        private String[] extraJvmArgs = new String[0];
        private String[] args = new String[0];
        private String mainClassName = "";
        private String javaAgentPath = "";
        private String javaAgentArgs = "";
        private String verificationFile = "";
        private String gameFilesCheckFile = "";

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setClassPathArray(String[] classPathArray) {
            this.classPathArray = classPathArray;
            return this;
        }

        public Builder setExtraJars(String[] extraJars) {
            this.extraJars = extraJars;
            return this;
        }

        public Builder setLibraryPathArray(String[] libraryPathArray) {
            this.libraryPathArray = libraryPathArray;
            return this;
        }

        public Builder setLibraryPathForEmulationArray(String[] libraryPathForEmulationArray) {
            this.libraryPathForEmulationArray = libraryPathForEmulationArray;
            return this;
        }

        public Builder setFmodLibraryPath(String fmodLibraryPath) {
            this.fmodLibraryPath = fmodLibraryPath;
            return this;
        }

        public Builder setExtraJvmArgs(String[] extraJvmArgs) {
            this.extraJvmArgs = extraJvmArgs;
            return this;
        }

        public Builder setArgs(String[] args) {
            this.args = args;
            return this;
        }

        public Builder setMainClassName(String mainClassName) {
            this.mainClassName = mainClassName;
            return this;
        }

        public Builder setJavaAgentPath(String javaAgentPath) {
            this.javaAgentPath = javaAgentPath;
            return this;
        }

        public Builder setJavaAgentArgs(String javaAgentArgs) {
            this.javaAgentArgs = javaAgentArgs;
            return this;
        }

        /**
         * Relative path (inside the instance's game/ dir) whose presence confirms the
         * correct game files were installed for this preset - e.g. a native library that
         * only exists in the Linux build. Used by GameInstance.hasFilesForLinux() to show
         * a friendly "wrong platform" dialog instead of a silent crash on launch.
         */
        public Builder setVerificationFile(String verificationFile) {
            this.verificationFile = verificationFile;
            return this;
        }

        /**
         * Relative path (inside the instance's game/ dir) whose presence confirms game
         * files were installed at all. Defaults to "" (meaning: fall back to
         * mainClassName + ".class", the old hardcoded behaviour) - only needed when the
         * game isn't distributed as loose unpacked .class files, e.g. Necesse ships as an
         * actual .jar, so its check file is the jar itself, not a class file.
         */
        public Builder setGameFilesCheckFile(String gameFilesCheckFile) {
            this.gameFilesCheckFile = gameFilesCheckFile;
            return this;
        }

        public InstallationPreset build() {
            return new InstallationPreset(this);
        }
    }
}
