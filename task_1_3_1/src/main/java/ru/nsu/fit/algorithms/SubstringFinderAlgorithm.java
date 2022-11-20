package ru.nsu.fit.algorithms;

import java.util.List;

public interface SubstringFinderAlgorithm {
    void processChar(char c);

    List<Integer> getSubstringIndices();
}
