package groovy

class TestCasesNormal {

    public static void main(String[] args) {
        new TestCasesNormal().run()
    }

    void run() {
        BlackBoxEngine.getInstance().initRootAstNode() //just removing compilation data from log
        misc()
        visitBlockStatementNoneLevel()
        visitBlockStatementMethodErrorLevel()
        visitBlockStatementMethodLevel()
        visitBlockStatementStatementLevel()
        visitBlockStatementExpressionLevel()
        visitForLoopNoneLevel()
        visitForLoopMethodErrorLevel()
        visitForLoopMethodLevel()
        visitForLoopStatementLevel()
        visitForLoopExpressionLevel()
        visitWhileLoopNoneLevel()
        visitWhileLoopMethodErrorLevel()
        visitWhileLoopMethodLevel()
        visitWhileLoopStatementLevel()
        visitWhileLoopExpressionLevel()
        visitDoWhileLoopNoneLevel()
        visitDoWhileLoopMethodErrorLevel()
        visitDoWhileLoopMethodLevel()
        visitDoWhileLoopStatementLevel()
        visitDoWhileLoopExpressionLevel()
        visitIfElseNoneLevel()
        visitIfElseMethodErrorLevel()
        visitIfElseMethodLevel()
        visitIfElseStatementLevel()
        visitIfElseExpressionLevel()
        visitExpressionStatementNoneLevel()
        visitExpressionStatementMethodErrorLevel()
        visitExpressionStatementMethodLevel()
        visitExpressionStatementStatementLevel()
        visitExpressionStatementExpressionLevel()
        visitReturnStatementNoneLevel()
        visitReturnStatementMethodErrorLevel()
        visitReturnStatementMethodLevel()
        visitReturnStatementStatementLevel()
        visitReturnStatementExpressionLevel()
        visitAssertStatementNoneLevel()
        visitAssertStatementMethodErrorLevel()
        visitAssertStatementMethodLevel()
        visitAssertStatementStatementLevel()
        visitAssertStatementExpressionLevel()
        visitTryCatchFinallyNoneLevel()
        visitTryCatchFinallyMethodErrorLevel()
        visitTryCatchFinallyMethodLevel()
        visitTryCatchFinallyStatementLevel()
        visitTryCatchFinallyExpressionLevel()
        visitEmptyStatementNoneLevel()
        visitEmptyStatementMethodErrorLevel()
        visitEmptyStatementMethodLevel()
        visitEmptyStatementStatementLevel()
        visitEmptyStatementExpressionLevel()
        visitSwitchNoneLevel()
        visitSwitchMethodErrorLevel()
        visitSwitchMethodLevel()
        visitSwitchStatementLevel()
        visitSwitchExpressionLevel()
        visitCaseStatementNoneLevel()
        visitCaseStatementMethodErrorLevel()
        visitCaseStatementMethodLevel()
        visitCaseStatementStatementLevel()
        visitCaseStatementExpressionLevel()
        visitBreakStatementNoneLevel()
        visitBreakStatementMethodErrorLevel()
        visitBreakStatementMethodLevel()
        visitBreakStatementStatementLevel()
        visitBreakStatementExpressionLevel()
        visitContinueStatementNoneLevel()
        visitContinueStatementMethodErrorLevel()
        visitContinueStatementMethodLevel()
        visitContinueStatementStatementLevel()
        visitContinueStatementExpressionLevel()
        visitSynchronizedStatementNoneLevel()
        visitSynchronizedStatementMethodErrorLevel()
        visitSynchronizedStatementMethodLevel()
        visitSynchronizedStatementStatementLevel()
        visitSynchronizedStatementExpressionLevel()
        visitThrowStatementNoneLevel()
        visitThrowStatementMethodErrorLevel()
        visitThrowStatementMethodLevel()
        visitThrowStatementStatementLevel()
        visitThrowStatementExpressionLevel()
        visitMethodCallExpressionNoneLevel()
        visitMethodCallExpressionMethodErrorLevel()
        visitMethodCallExpressionMethodLevel()
        visitMethodCallExpressionStatementLevel()
        visitMethodCallExpressionExpressionLevel()
        visitStaticMethodCallExpressionNoneLevel()
        visitStaticMethodCallExpressionMethodErrorLevel()
        visitStaticMethodCallExpressionMethodLevel()
        visitStaticMethodCallExpressionStatementLevel()
        visitStaticMethodCallExpressionExpressionLevel()
        visitConstructorCallExpressionNoneLevel()
        visitConstructorCallExpressionMethodErrorLevel()
        visitConstructorCallExpressionMethodLevel()
        visitConstructorCallExpressionStatementLevel()
        visitConstructorCallExpressionExpressionLevel()
        visitBinaryExpressionNoneLevel()
        visitBinaryExpressionMethodErrorLevel()
        visitBinaryExpressionMethodLevel()
        visitBinaryExpressionStatementLevel()
        visitBinaryExpressionExpressionLevel()
        visitTernaryExpressionNoneLevel()
        visitTernaryExpressionMethodErrorLevel()
        visitTernaryExpressionMethodLevel()
        visitTernaryExpressionStatementLevel()
        visitTernaryExpressionExpressionLevel()
        visitShortTernaryExpressionNoneLevel()
        visitShortTernaryExpressionMethodErrorLevel()
        visitShortTernaryExpressionMethodLevel()
        visitShortTernaryExpressionStatementLevel()
        visitShortTernaryExpressionExpressionLevel()
        visitPostfixExpressionNoneLevel()
        visitPostfixExpressionMethodErrorLevel()
        visitPostfixExpressionMethodLevel()
        visitPostfixExpressionStatementLevel()
        visitPostfixExpressionExpressionLevel()
        visitPrefixExpressionNoneLevel()
        visitPrefixExpressionMethodErrorLevel()
        visitPrefixExpressionMethodLevel()
        visitPrefixExpressionStatementLevel()
        visitPrefixExpressionExpressionLevel()
        visitBooleanExpressionNoneLevel()
        visitBooleanExpressionMethodErrorLevel()
        visitBooleanExpressionMethodLevel()
        visitBooleanExpressionStatementLevel()
        visitBooleanExpressionExpressionLevel()
        visitNotExpressionNoneLevel()
        visitNotExpressionMethodErrorLevel()
        visitNotExpressionMethodLevel()
        visitNotExpressionStatementLevel()
        visitNotExpressionExpressionLevel()
        visitClosureExpressionNoneLevel()
        visitClosureExpressionMethodErrorLevel()
        visitClosureExpressionMethodLevel()
        visitClosureExpressionStatementLevel()
        visitClosureExpressionExpressionLevel()
        visitTupleExpressionNoneLevel()
        visitTupleExpressionMethodErrorLevel()
        visitTupleExpressionMethodLevel()
        visitTupleExpressionStatementLevel()
        visitTupleExpressionExpressionLevel()
        visitListExpressionNoneLevel()
        visitListExpressionMethodErrorLevel()
        visitListExpressionMethodLevel()
        visitListExpressionStatementLevel()
        visitListExpressionExpressionLevel()
        visitArrayExpressionNoneLevel()
        visitArrayExpressionMethodErrorLevel()
        visitArrayExpressionMethodLevel()
        visitArrayExpressionStatementLevel()
        visitArrayExpressionExpressionLevel()
        visitMapExpressionNoneLevel()
        visitMapExpressionMethodErrorLevel()
        visitMapExpressionMethodLevel()
        visitMapExpressionStatementLevel()
        visitMapExpressionExpressionLevel()
        visitMapEntryExpressionNoneLevel()
        visitMapEntryExpressionMethodErrorLevel()
        visitMapEntryExpressionMethodLevel()
        visitMapEntryExpressionStatementLevel()
        visitMapEntryExpressionExpressionLevel()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitBlockStatementNoneLevel() {
        System.out.println("Test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitBlockStatementMethodErrorLevel() {
        System.out.println("Test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitBlockStatementMethodLevel() {
        System.out.println("Test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitBlockStatementStatementLevel() {
        System.out.println("Test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBlockStatementExpressionLevel() {
        System.out.println("Test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitForLoopNoneLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitForLoopMethodErrorLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitForLoopMethodLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitForLoopStatementLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitForLoopExpressionLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitWhileLoopNoneLevel() {
        int z = 0
        while (z < 3) {
            System.out.println("Test")
            z++
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitWhileLoopMethodErrorLevel() {
        int z = 0
        while (z < 3) {
            System.out.println("Test")
            z++
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitWhileLoopMethodLevel() {
        int z = 0
        while (z < 3) {
            System.out.println("Test")
            z++
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitWhileLoopStatementLevel() {
        int z = 0
        while (z < 3) {
            System.out.println("Test")
            z++
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitWhileLoopExpressionLevel() {
        int z = 0
        while (z < 3) {
            System.out.println("Test")
            z++
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitDoWhileLoopNoneLevel() {
        //unsupported by Groovy now
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitDoWhileLoopMethodErrorLevel() {
        //unsupported by Groovy now
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitDoWhileLoopMethodLevel() {
        //unsupported by Groovy now
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitDoWhileLoopStatementLevel() {
        //unsupported by Groovy now
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitDoWhileLoopExpressionLevel() {
        //unsupported by Groovy now
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitIfElseNoneLevel() {
        if (true) {
            if (false) {
                System.out.println("Test")
            } else {
                System.out.println("Test")
            }
        } else {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitIfElseMethodErrorLevel() {
        if (true) {
            if (false) {
                System.out.println("Test")
            } else {
                System.out.println("Test")
            }
        } else {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitIfElseMethodLevel() {
        if (true) {
            if (false) {
                System.out.println("Test")
            } else {
                System.out.println("Test")
            }
        } else {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitIfElseStatementLevel() {
        if (true) {
            if (false) {
                System.out.println("Test")
            } else {
                System.out.println("Test")
            }
        } else {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitIfElseExpressionLevel() {
        if (true) {
            if (false) {
                System.out.println("Test")
            } else {
                System.out.println("Test")
            }
        } else {
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitExpressionStatementNoneLevel() {
        int z
        z = z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitExpressionStatementMethodErrorLevel() {
        int z
        z = z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitExpressionStatementMethodLevel() {
        int z
        z = z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitExpressionStatementStatementLevel() {
        int z
        z = z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitExpressionStatementExpressionLevel() {
        int z
        z = z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    String visitReturnStatementNoneLevel() {
        if (true) {
            if (false) {
                return "Test"
            } else {
                return "Test"
            }
        } else {
            return "Test"
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    String visitReturnStatementMethodErrorLevel() {
        if (true) {
            if (false) {
                return "Test"
            } else {
                return "Test"
            }
        } else {
            return "Test"
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    String visitReturnStatementMethodLevel() {
        if (true) {
            if (false) {
                return "Test"
            } else {
                return "Test"
            }
        } else {
            return "Test"
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    String visitReturnStatementStatementLevel() {
        if (true) {
            if (false) {
                return "Test"
            } else {
                return "Test"
            }
        } else {
            return "Test"
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    String visitReturnStatementExpressionLevel() {
        if (true) {
            if (false) {
                return "Test"
            } else {
                return "Test"
            }
        } else {
            return "Test"
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitAssertStatementNoneLevel() {
        assert 1==1
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitAssertStatementMethodErrorLevel() {
        assert 1==1
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitAssertStatementMethodLevel() {
        assert 1==1
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitAssertStatementStatementLevel() {
        assert 1==1
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitAssertStatementExpressionLevel() {
        assert 1==1
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitTryCatchFinallyNoneLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitTryCatchFinallyMethodErrorLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitTryCatchFinallyMethodLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitTryCatchFinallyStatementLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitTryCatchFinallyExpressionLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitEmptyStatementNoneLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitEmptyStatementMethodErrorLevel() {
        //skipped
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitEmptyStatementMethodLevel() {
        //skipped
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitEmptyStatementStatementLevel() {
        //skipped
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitEmptyStatementExpressionLevel() {
        //skipped
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitSwitchNoneLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitSwitchMethodErrorLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitSwitchMethodLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitSwitchStatementLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitSwitchExpressionLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitCaseStatementNoneLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitCaseStatementMethodErrorLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitCaseStatementMethodLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitCaseStatementStatementLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitCaseStatementExpressionLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitBreakStatementNoneLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitBreakStatementMethodErrorLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitBreakStatementMethodLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitBreakStatementStatementLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBreakStatementExpressionLevel() {
        switch (1) {
            case 1:
                System.out.println("test")
                System.out.println("test")
                break
            case 2:
                System.out.println("test")
                System.out.println("test")
                break
            default:
                System.out.println("test")
                System.out.println("test")
                break
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitContinueStatementNoneLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
            if (true) {
                continue
            }
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitContinueStatementMethodErrorLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
            if (true) {
                continue
            }
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitContinueStatementMethodLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
            if (true) {
                continue
            }
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitContinueStatementStatementLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
            if (true) {
                continue
            }
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitContinueStatementExpressionLevel() {
        for (z in [1,2,3,4]) {
            System.out.println("Test")
            if (true) {
                continue
            }
            System.out.println("Test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitSynchronizedStatementNoneLevel() {
        Object object = new Object()
        synchronized (object) {
            object = new Object()
            System.out.println(object)
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitSynchronizedStatementMethodErrorLevel() {
        Object object = new Object()
        synchronized (object) {
            object = new Object()
            System.out.println(object)
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitSynchronizedStatementMethodLevel() {
        Object object = new Object()
        synchronized (object) {
            object = new Object()
            System.out.println(object)
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitSynchronizedStatementStatementLevel() {
        Object object = new Object()
        synchronized (object) {
            object = new Object()
            System.out.println(object)
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitSynchronizedStatementExpressionLevel() {
        Object object = new Object()
        synchronized (object) {
            object = new Object()
            System.out.println(object)
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitThrowStatementNoneLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
            throw new Exception("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitThrowStatementMethodErrorLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
            throw new Exception("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitThrowStatementMethodLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
            throw new Exception("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitThrowStatementStatementLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
            throw new Exception("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitThrowStatementExpressionLevel() {
        try {
            System.out.println("test")
            System.out.println("test")
            throw new Exception("test")
        } catch (Throwable throwable) {
            System.out.println("test")
            System.out.println("test")
        } finally {
            System.out.println("test")
            System.out.println("test")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitMethodCallExpressionNoneLevel() {
        System.out.println("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitMethodCallExpressionMethodErrorLevel() {
        System.out.println("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitMethodCallExpressionMethodLevel() {
        System.out.println("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitMethodCallExpressionStatementLevel() {
        System.out.println("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitMethodCallExpressionExpressionLevel() {
        System.out.println("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitStaticMethodCallExpressionNoneLevel() {
        System.getProperty("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitStaticMethodCallExpressionMethodErrorLevel() {
        System.getProperty("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitStaticMethodCallExpressionMethodLevel() {
        System.getProperty("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitStaticMethodCallExpressionStatementLevel() {
        System.getProperty("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitStaticMethodCallExpressionExpressionLevel() {
        System.getProperty("test")
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitConstructorCallExpressionNoneLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitConstructorCallExpressionMethodErrorLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitConstructorCallExpressionMethodLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitConstructorCallExpressionStatementLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitConstructorCallExpressionExpressionLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitBinaryExpressionNoneLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitBinaryExpressionMethodErrorLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitBinaryExpressionMethodLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitBinaryExpressionStatementLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBinaryExpressionExpressionLevel() {
        Object object = new Object()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitTernaryExpressionNoneLevel() {
        true?true:false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitTernaryExpressionMethodErrorLevel() {
        true?true:false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitTernaryExpressionMethodLevel() {
        true?true:false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitTernaryExpressionStatementLevel() {
        true?true:false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitTernaryExpressionExpressionLevel() {
        true?true:false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitShortTernaryExpressionNoneLevel() {
        def location = true ?: "z"
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitShortTernaryExpressionMethodErrorLevel() {
        def location = true ?: "z"
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitShortTernaryExpressionMethodLevel() {
        def location = true ?: "z"
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitShortTernaryExpressionStatementLevel() {
        def location = true ?: "z"
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitShortTernaryExpressionExpressionLevel() {
        def location = true ?: "z"
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitPostfixExpressionNoneLevel() {
        int z = 1
        z++
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitPostfixExpressionMethodErrorLevel() {
        int z = 1
        z++
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitPostfixExpressionMethodLevel() {
        int z = 1
        z++
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitPostfixExpressionStatementLevel() {
        int z = 1
        z++
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitPostfixExpressionExpressionLevel() {
        int z = 1
        z++
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitPrefixExpressionNoneLevel() {
        int z = 1
        ++z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitPrefixExpressionMethodErrorLevel() {
        int z = 1
        ++z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitPrefixExpressionMethodLevel() {
        int z = 1
        ++z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitPrefixExpressionStatementLevel() {
        int z = 1
        ++z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitPrefixExpressionExpressionLevel() {
        int z = 1
        ++z
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitBooleanExpressionNoneLevel() {
        if (true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitBooleanExpressionMethodErrorLevel() {
        if (true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitBooleanExpressionMethodLevel() {
        if (true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitBooleanExpressionStatementLevel() {
        if (true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBooleanExpressionExpressionLevel() {
        if (true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitNotExpressionNoneLevel() {
        if (!true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitNotExpressionMethodErrorLevel() {
        if (!true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitNotExpressionMethodLevel() {
        if (!true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitNotExpressionStatementLevel() {
        if (!true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitNotExpressionExpressionLevel() {
        if (!true) false
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitClosureExpressionNoneLevel() {
        Closure c = {
            System.out.println("z")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitClosureExpressionMethodErrorLevel() {
        Closure c = {
            System.out.println("z")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitClosureExpressionMethodLevel() {
        Closure c = {
            System.out.println("z")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitClosureExpressionStatementLevel() {
        Closure c = {
            System.out.println("z")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitClosureExpressionExpressionLevel() {
        Closure c = {
            System.out.println("z")
        }
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitTupleExpressionNoneLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitTupleExpressionMethodErrorLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitTupleExpressionMethodLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitTupleExpressionStatementLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitTupleExpressionExpressionLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitListExpressionNoneLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitListExpressionMethodErrorLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitListExpressionMethodLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitListExpressionStatementLevel() {
        def (int a, int b) = [1,2]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitListExpressionExpressionLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitArrayExpressionNoneLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitArrayExpressionMethodErrorLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitArrayExpressionMethodLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitArrayExpressionStatementLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitArrayExpressionExpressionLevel() {
        def strArray = new String[3]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitMapExpressionNoneLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitMapExpressionMethodErrorLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitMapExpressionMethodLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitMapExpressionStatementLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitMapExpressionExpressionLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
    void visitMapEntryExpressionNoneLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
    void visitMapEntryExpressionMethodErrorLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void visitMapEntryExpressionMethodLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void visitMapEntryExpressionStatementLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitMapEntryExpressionExpressionLevel() {
        def map = ["abcd": 1234, "tdgf": 55436]
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void misc() {
        int wwww = 0
        while (wwww < 3) {
            try {
                System.out.println("try")
                System.out.println("zzzzzzzzzz " + wwww)
                wwww++
            } catch (Throwable z) {
                System.out.println("Catched " + z)
                System.out.println("Catch")
            } finally {
                System.out.println("Finally1 " + wwww)
                System.out.println("Finally2 " + wwww)
            }
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }


/*    @Override
    void run() {

    }*/
}

