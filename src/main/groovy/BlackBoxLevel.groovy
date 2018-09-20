package groovy

enum BlackBoxLevel {

    EXPRESSION(4),
    STATEMENT(3),
    METHOD(2),
    ERROR(1),
    NONE(1);

    private final int blackBoxLevel

    BlackBoxLevel(int iBlackBoxLevel) {
        blackBoxLevel = iBlackBoxLevel
    }

    int value() {
        return blackBoxLevel
    }

}