package groovy

enum BlackBoxMode {

    SEQUENTIAL("SEQUENTIAL"),
    EMERGENCY("EMERGENCY");

    private final String blackBoxMode

    BlackBoxMode(String iBlackBoxLevel) {
        blackBoxMode = iBlackBoxLevel
    }

    String value() {
        return blackBoxMode
    }

}