package groovy

enum BlackBoxLevel {

    EXPRESSION(5),
    STATEMENT(4),
    METHOD(3),
    METHOD_ERROR(2),
    NONE(1);

    private final int blackBoxLevel

    BlackBoxLevel(int iBlackBoxLevel) {
        blackBoxLevel = iBlackBoxLevel
    }

    int value() {
        return blackBoxLevel
    }

}