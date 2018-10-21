package io.infinite.blackbox

enum BlackBoxMode {

    SEQUENTIAL("SEQUENTIAL"),
    EMERGENCY("EMERGENCY"),
    HIERARCHICAL("HIERARCHICAL");

    private final String blackBoxMode

    BlackBoxMode(String iBlackBoxLevel) {
        blackBoxMode = iBlackBoxLevel
    }

    String value() {
        return blackBoxMode
    }

}