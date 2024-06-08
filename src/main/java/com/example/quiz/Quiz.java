package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    String title;
    List<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
