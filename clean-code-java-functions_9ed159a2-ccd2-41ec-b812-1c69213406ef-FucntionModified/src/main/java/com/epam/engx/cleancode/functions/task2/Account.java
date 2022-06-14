package com.epam.engx.cleancode.functions.task2;


import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActivUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;

import java.util.TreeMap;

public abstract class Account implements User {

    private TreeMap<Integer, Level> thresholdsToLevels = new TreeMap<>();

    public Level getActivityLevel() {
        validateAccountForLevel();
        int reviewAnswers = calculateReviewAnswers();
        return getLevelByReviews(reviewAnswers);

    }

    int calculateReviewAnswers() {
        int reviewAnswers = 0;
        for (Review r : getAllReviews())
            reviewAnswers += r.getAnswers().size();
        return reviewAnswers;
    }



    private void validateAccountForLevel() {
        if (isAccountInactive())
            throw new NotActivUserException();
    }

    private boolean isAccountInactive() {
        return getVisitNumber() <= 0 || hasNotRegistered();
    }

    private boolean hasNotRegistered() {
        return !isRegistered();
    }


    private Level getLevelByReviews(int reviewAnswers) {
        for (Integer threshold : thresholdsToLevels.keySet()) {
            if (reviewAnswers >= threshold)
                return thresholdsToLevels.get(threshold);
        }

        return Level.defaultLevel();
    }

    public void setThresholdsToLevels (TreeMap<Integer, Level> thresholdsToLevels) {
        this.thresholdsToLevels = thresholdsToLevels;
    }
}
