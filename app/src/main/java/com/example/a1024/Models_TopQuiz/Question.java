package com.example.a1024.Models_TopQuiz;

import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }


    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;

        if (answerIndex < 0 || answerIndex >= mChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }

        mAnswerIndex = answerIndex;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;

        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        mChoiceList = choiceList;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
