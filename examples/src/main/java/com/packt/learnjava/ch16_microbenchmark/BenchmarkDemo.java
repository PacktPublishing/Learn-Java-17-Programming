package com.packt.learnjava.ch16_microbenchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class BenchmarkDemo {
    public static void main(String... args) throws Exception{
        org.openjdk.jmh.Main.main(args);
    }

    @State(Scope.Benchmark)
    public static class TestState1 {
        @Param({"100", "1000", "10000"})
        public int m;
        public int s = 250_000;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testTheMethod6(TestState1 state, Blackhole blackhole) {
        SomeClass someClass = new SomeClass();
        blackhole.consume(someClass.someMethod(state.m, state.s));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testTheMethod5(TestState state, Blackhole blackhole) {
        SomeClass someClass = new SomeClass();
        blackhole.consume(someClass.oneMethod(state.m, state.s));
    }

    @State(Scope.Thread)
    public static class TestState {
        public int m = 1000;
        public int s = 250_000;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testTheMethod4(TestState state, Blackhole blackhole) {
        SomeClass someClass = new SomeClass();
        blackhole.consume(someClass.someMethod(state.m, state.s));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int testTheMethod3(TestState state) {
        SomeClass someClass = new SomeClass();
        return someClass.someMethod(state.m, state.s);
    }

    @Benchmark
    public int testTheMethod2() {
        SomeClass someClass = new SomeClass();
        int i = 1000;
        int s = 250_000;
        return someClass.someMethod(i, s);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testTheMethod1() {
        SomeClass someClass = new SomeClass();
        int i = 1000;
        int s = 250_000;
        someClass.someMethod(i, s);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testTheMethod0() {
        int res = 0;
        for(int i = 0; i < 1000; i++){
            int n = i * i;
            if (n != 0 && n % 250_000 == 0) {
                res += n;
            }
        }
    }

    private static class SomeClass{
        public int someMethod(int m, int s) {
            int res = 0;
            for(int i = 0; i < m; i++){
                int n = i * i;
                if (n != 0 && n % s == 0) {
                    res += n;
                }
            }
            return res;
        }

        public int oneMethod(int m, int s) {
            int res = 0;
            for(int i = 0; i < m; i++){
                int n = i * i;
                if (n != 0 && n % s == 0) {
                    res = anotherMethod(res, n);
                }
            }
            return res;
        }

        @CompilerControl(CompilerControl.Mode.EXCLUDE)
        private int anotherMethod(int res, int n){
            return res +=n;
        }

    }
}
