package com.theevilroot.epam.lab1.model;

public interface Model {

    double calculateArea(float length, float height);

    double calculatePerimeter(float length, float height);

    Model defaultModel = new ModelImpl();

}
