package org.jboss.jandex;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class IndexerBenchmark {
    @Param("/tmp/quarkus-tribe-krd-1.0.0-SNAPSHOT.jar")
    private String jarFile;
    @Param("1")
    private int count;
    private File[] jars;

    @Setup
    public void setup() {
        if (jarFile == null) {
            System.exit(1);
        }
        jars = new File[10];
        Arrays.fill(jars, new File(jarFile));
    }

    @Benchmark
    public Index indexJars() throws IOException {
        return Index.of(jars);
    }

}
