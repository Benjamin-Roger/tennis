package com.tennis.back.domain.useCase.GetPlayersMedianHeightUseCase;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianCalculator {

    private Queue<Integer> minHeap, maxHeap;

    MedianCalculator() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    void add(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    double getMedian() {
        int median;
        if (minHeap.size() > maxHeap.size()) {
            median = minHeap.peek();
        } else {
            median = (minHeap.peek() + maxHeap.peek()) / 2;
        }
        return median;
    }
}
