package groovy

enum BlackBoxLevel {

    EXPRESSION(6),
    STATEMENT(5),
    STATEMENT_ERROR(4),
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