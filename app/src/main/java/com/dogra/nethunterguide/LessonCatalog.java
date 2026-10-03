package com.dogra.nethunterguide;

public final class LessonCatalog {

    public static final String[] TITLES = {
            "Command Lessons",
            "Installation Guide",
            "Kali Tools Notes",
            "Linux Basics",
            "Network Basics",
            "Web Basics",
            "Forensics Basics",
            "Learning Roadmap",
            "Tool Categories",
            "Command Reference",
            "Troubleshooting",
            "Practice Labs",
            "About & Safety"
    };

    public static final String[] ASSETS = {
            "commands.txt",
            "install.txt",
            "kali-tools.txt",
            "linux-basics.txt",
            "network-basics.txt",
            "web-basics.txt",
            "forensics-basics.txt",
            "learning-roadmap.txt",
            "tool-categories.txt",
            "command-reference.txt",
            "troubleshooting.txt",
            "practice-labs.txt",
            "about-safety.txt"
    };

    private LessonCatalog() { }

    public static int indexOfAsset(String asset) {
        if (asset == null) {
            return -1;
        }

        for (int i = 0; i < ASSETS.length; i++) {
            if (asset.equals(ASSETS[i])) {
                return i;
            }
        }
        return -1;
    }
}
