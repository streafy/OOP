package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public class SubstringFinderTest {
    private static final String pattern = "aba";

    private static Stream<SubstringFinder> substringFinderStream() {
        return Stream.of(new SubstringFinder("AC", pattern),
                new SubstringFinder("KMP", pattern));
    }

    @ParameterizedTest
    @MethodSource("substringFinderStream")
    void testFromFile(SubstringFinder finder) throws IOException {
        InputStream file = new FileInputStream("src/test/java/ru/nsu/fit/test.txt");
        List<Integer> indices = finder.search(file);

        Assertions.assertEquals(2, indices.size());
        Assertions.assertEquals(1, indices.get(0));
    }

    @ParameterizedTest
    @MethodSource("substringFinderStream")
    void testFromString(SubstringFinder finder) {
        InputStream is = new ByteArrayInputStream("cabacaba".getBytes());
        List<Integer> indices = finder.search(is);

        Assertions.assertEquals(2, indices.size());
        Assertions.assertEquals(1, indices.get(0));
    }
}
