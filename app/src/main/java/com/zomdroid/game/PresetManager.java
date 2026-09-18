package com.zomdroid.game;

import com.zomdroid.C;

import java.util.ArrayList;

public class PresetManager {
    private static final ArrayList<InstallationPreset> presets = new ArrayList<>();

    static {
        presets.add(new InstallationPreset.Builder()
                .setName("Build 42")
                .setClassPathArray(new String[]{
                        ".",
                        "commons-compress-1.27.1.jar",
                        "commons-io-2.18.0.jar",
                        "istack-commons-runtime.jar",
                        "jassimp.jar",
                        "guava-23.0.jar",
                        "javacord-3.8.0-shaded.jar",
                        "javax.activation-api.jar",
                        "jaxb-api.jar",
                        "jaxb-runtime.jar",
                        "lwjgl.jar",
                        "lwjgl-glfw.jar",
                        "lwjgl-jemalloc.jar",
                        "lwjgl-opengl.jar",
                        "lwjgl_util.jar",
                        "sqlite-jdbc-3.48.0.0.jar",
                        "trove-3.0.3.jar",
                        "uncommons-maths-1.2.3.jar",
                        "imgui-binding-1.86.11-8-g3e33dde.jar",
                        "commons-codec-1.10.jar",
                        "javase-3.2.1.jar",
                        "totp-1.0.jar",
                        "core-3.2.1.jar"
                })
                .setExtraJars(new String[0])
                .setLibraryPathArray(new String[]{
                        C.deps.LIBS_ANDROID_ARM64_v8a,
                        C.deps.LIBS_LWJGL_336
                })
                .setLibraryPathForEmulationArray(new String[]{
                        C.deps.LIBS_LINUX_X86_64
                })
                .setFmodLibraryPath(C.deps.LIBS_FMOD_20224)
                .setExtraJvmArgs(new String[0])
                .setArgs(new String[]{
                        "-novoip"
                })
                .setMainClassName("zombie/gameStates/MainScreenState")
                .setJavaAgentPath(C.deps.JARS_ZOMDROID_AGENT)
                //.setJavaAgentArgs("build=42")
                .setVerificationFile("libPZBullet64.so")
                .build()
        );

        presets.add(new InstallationPreset.Builder()
                .setName("Build 41")
                .setClassPathArray(new String[]{
                        ".",
                        "commons-compress-1.18.jar",
                        "istack-commons-runtime.jar",
                        "jassimp.jar",
                        "javacord-2.0.17-shaded.jar",
                        "javax.activation-api.jar",
                        "jaxb-api.jar",
                        "jaxb-runtime.jar",
                        "lwjgl.jar",
                        "lwjgl-glfw.jar",
                        "lwjgl-jemalloc.jar",
                        "lwjgl-opengl.jar",
                        "lwjgl_util.jar",
                        "trove-3.0.3.jar",
                        "uncommons-maths-1.2.3.jar"
                })
                .setExtraJars(new String[]{
                        C.deps.JARS_SQLITE_JDBC_34800
                })
                .setLibraryPathArray(new String[]{
                        C.deps.LIBS_ANDROID_ARM64_v8a,
                        C.deps.LIBS_LWJGL_323
                })
                .setLibraryPathForEmulationArray(new String[]{
                        C.deps.LIBS_LINUX_X86_64
                })
                .setFmodLibraryPath(C.deps.LIBS_FMOD_20206)
                .setExtraJvmArgs(new String[0])
                .setArgs(new String[]{
                        "-novoip"
                })
                .setMainClassName("zombie/gameStates/MainScreenState")
                .setJavaAgentPath(C.deps.JARS_ZOMDROID_AGENT)
                //.setJavaAgentArgs("build=41")
                .setVerificationFile("libPZBullet64.so")
                .build()
        );

        // --- Necesse ---
        // Verified directly against the real Necesse.jar manifest (2026-08 build):
        //   Main-Class: StartSteamClient
        //   Class-Path: lib/steamworks4j-1.10.2-FAIR.jar, LWJGL 3.4.1 (glfw/openal/opengl/
        //               stb/core, each with per-OS natives jars), oshi-core, slf4j, byte-buddy,
        //               jna - i.e. Necesse.jar is a THIN jar, not fat: it needs its lib/ folder
        //               sitting next to it, unlike PZ's loose-.class-files layout.
        // So the instance's game/ folder must contain, exactly as they sit in a real Necesse
        // Linux install: Necesse.jar, the lib/ folder (Linux natives jars are enough - macos/
        // windows natives jars can be left out), and a steam_appid.txt file containing just
        // "1169040" (confirmed by Necesse's own devs as the trick to launch without Steam
        // actually running - StartSteamClient still expects to find this file).
        //
        // LWJGL is 3.4.1 here, newer than what zomdroid bundles for PZ (3.3.6) - so this
        // preset intentionally does NOT point at zomdroid's shared LWJGL native folder.
        // Necesse's own lib/*-natives-linux.jar files carry the matching-version natives,
        // and LWJGL extracts/loads those itself at runtime, independent of java.library.path.
        //
        // Still a GUESS: no java agent is set. StartSteamClient pulls in steamworks4j, and
        // we don't yet know whether it needs any PZ-style bytecode patching to behave under
        // Box64/GL4ES - only a real launch attempt (and its crash log, if any) will tell.
        presets.add(new InstallationPreset.Builder()
                .setName("Necesse")
                .setClassPathArray(new String[]{
                        "Necesse.jar",
                        "lib/steamworks4j-1.10.2-FAIR.jar",
                        "lib/lwjgl-glfw-3.4.1.jar",
                        "lib/lwjgl-glfw-3.4.1-natives-linux.jar",
                        "lib/lwjgl-openal-3.4.1.jar",
                        "lib/lwjgl-openal-3.4.1-natives-linux.jar",
                        "lib/lwjgl-opengl-3.4.1.jar",
                        "lib/lwjgl-opengl-3.4.1-natives-linux.jar",
                        "lib/lwjgl-stb-3.4.1.jar",
                        "lib/lwjgl-stb-3.4.1-natives-linux.jar",
                        "lib/lwjgl-3.4.1.jar",
                        "lib/lwjgl-3.4.1-natives-linux.jar",
                        "lib/oshi-core-5.8.5.jar",
                        "lib/slf4j-nop-1.7.30.jar",
                        "lib/byte-buddy-1.12.8.jar",
                        "lib/byte-buddy-agent-1.12.8.jar",
                        "lib/jna-platform-5.10.0.jar",
                        "lib/jna-5.10.0.jar",
                        "lib/slf4j-api-1.7.32.jar"
                })
                .setExtraJars(new String[0])
                .setLibraryPathArray(new String[]{
                        C.deps.LIBS_ANDROID_ARM64_v8a
                })
                .setLibraryPathForEmulationArray(new String[]{
                        C.deps.LIBS_LINUX_X86_64
                })
                .setFmodLibraryPath("") // no FMOD in the manifest - audio goes through lwjgl-openal
                .setExtraJvmArgs(new String[0])
                .setArgs(new String[0])
                .setMainClassName("StartSteamClient") // verified from MANIFEST.MF, no package prefix
                .setJavaAgentPath("") // intentionally no agent for the first test run - see note above
                .setGameFilesCheckFile("Necesse.jar")
                .setVerificationFile("lib/lwjgl-3.4.1-natives-linux.jar")
                .build()
        );
    }

    public static ArrayList<InstallationPreset> getPresets() {
        return presets;
    }
}
