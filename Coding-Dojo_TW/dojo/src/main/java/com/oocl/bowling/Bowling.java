package com.oocl.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Bowling {

    String frames;
    List<Integer> scores = new ArrayList<>();


    Bowling(String frames) {
        this.frames = frames;
    }

    int getResult() {
        convertFrameToFrameInteger();
        computeFrameScores();
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    private void computeFrameScores() {
        List<Integer> frameScore = new ArrayList<>();
        int frameSize = scores.size() - 2;
        for (int i = 0; i < frameSize; i++) {
            int score = scores.get(i);
            if (score == 10) {
                score += scores.get(i + 1) + scores.get(i + 2);
            }
            frameScore.add(score);
        }
        scores.clear();
        scores.addAll(frameScore);
    }


    private void convertFrameToFrameInteger() {
        List<Character> frame = frames.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        frame.forEach(f -> {
            if (f.toString().equalsIgnoreCase("X")) {
                scores.add(10);
            } else if (f.toString().equalsIgnoreCase("-")) {
                scores.add(0);
            } else if (f.toString().equalsIgnoreCase("/")) {
                scores.add(10 - scores);
            } else {
                scores.add(Integer.parseInt(f.toString()));
            }
        });
    }

}
