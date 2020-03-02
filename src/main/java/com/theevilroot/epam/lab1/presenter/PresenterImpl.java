package com.theevilroot.epam.lab1.presenter;

import com.theevilroot.epam.lab1.model.Model;
import com.theevilroot.epam.lab1.view.MainView;

public class PresenterImpl implements Presenter{

    private final MainView view;
    private final Model model;

    public PresenterImpl(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void requestArea(String lengthStr, String heightStr) {
        try {
            float length = Float.parseFloat(lengthStr);
            float height = Float.parseFloat(heightStr);

            double area = model.calculateArea(length, height);
            view.showAreaResult(String.valueOf(area));
            view.clearError();
        } catch (NumberFormatException e) {
            view.showError("Invalid input!");
        }
    }

    public void requestPerimeter(String lengthStr, String heightStr) {
        try {
            float length = Float.parseFloat(lengthStr);
            float height = Float.parseFloat(heightStr);

            double per = model.calculatePerimeter(length, height);
            view.showPerimeterResult(String.valueOf(per));
            view.clearError();
        } catch (NumberFormatException e) {
            view.showError("Invalid input!");
        }
    }

}
