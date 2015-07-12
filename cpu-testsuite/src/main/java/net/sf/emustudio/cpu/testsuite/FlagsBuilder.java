package net.sf.emustudio.cpu.testsuite;

import java.util.ArrayList;
import java.util.List;

public abstract class FlagsBuilder<T extends Number, SpecificFlagsBuilder extends FlagsBuilder> {
    protected final List<FlagsEval> evaluators = new ArrayList<>();

    protected int expectedFlags = 0;
    protected int expectedNotFlags = 0;

    @FunctionalInterface
    protected interface FlagsEval<T> {
        void eval(T first, T second, int result);
    }

    public SpecificFlagsBuilder reset() {
        expectedFlags = 0;
        expectedNotFlags = 0;
        return (SpecificFlagsBuilder)this;
    }

    public SpecificFlagsBuilder or(int flags) {
        expectedFlags |= flags;
        return (SpecificFlagsBuilder)this;
    }

    public int getExpectedFlags() {
        return expectedFlags;
    }

    public int getNotExpectedFlags() {
        return expectedNotFlags;
    }

    public void eval(T first, T second, int result) {
        for (FlagsEval evaluator : evaluators) {
            evaluator.eval(first, second, result);
        }
    }

}
