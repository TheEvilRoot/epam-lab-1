package com.theevilroot.epam.lab1.model;

class ModelImpl implements Model {

    public double calculateArea(float length, float height) {
        return length * height;
    }

    public double calculatePerimeter(float length, float height) {
        return length * 2 + height * 2;
    }

}
