package com.ritik.quiz.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ScoreService {

    private final AtomicInteger score = new AtomicInteger();

    /**
     * Atomically increases the shared in-memory score by one.
     *
     * @return the score after it is incremented
     */
    public int incrementScore(){
        return score.incrementAndGet();
    }

    /**
     * Reads the current shared in-memory score without changing it.
     *
     * @return the current score
     */
   public int getScore(){
        return score.get();
    }

    /**
     * Resets the shared in-memory score to zero.
     *
     * @return the reset score, always zero
     */
    public int resetScore(){
        score.set(0);
        return 0;
    }


}
