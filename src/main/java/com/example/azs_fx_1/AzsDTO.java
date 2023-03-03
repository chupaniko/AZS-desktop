package com.example.azs_fx_1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class AzsDTO {
    public String name;
    public int width;
    public int height;
    public TopologyDTO.FuelTank[] tanks;
    public TopologyDTO.TemplateAZS[] azsField;

    public AzsDTO(String name, int width, int height, TopologyDTO.FuelTank[] tanks, TopologyDTO.TemplateAZS[] azsField) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tanks = tanks;
        this.azsField = azsField;
    }
    public AzsDTO() {
        name = "";
        width = 0;
        height = 0;
        tanks = new TopologyDTO.FuelTank[0];
        azsField = new TopologyDTO.TemplateAZS[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TopologyDTO.FuelTank[] getTanks() {
        return tanks;
    }

    public void setTanks(TopologyDTO.FuelTank[] tanks) {
        this.tanks = tanks;
    }

    public TopologyDTO.TemplateAZS[] getAzsField() {
        return azsField;
    }

    public void setAzsField(TopologyDTO.TemplateAZS[] azsField) {
        this.azsField = azsField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AzsDTO azsDTO = (AzsDTO) o;
        return width == azsDTO.width && height == azsDTO.height && Objects.equals(name, azsDTO.name) && Arrays.equals(tanks, azsDTO.tanks) && Arrays.equals(azsField, azsDTO.azsField);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, width, height);
        result = 31 * result + Arrays.hashCode(tanks);
        result = 31 * result + Arrays.hashCode(azsField);
        return result;
    }

    @Override
    public String toString() {
        return "AzsDTO{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", tanks=" + Arrays.toString(tanks) +
                ", azsField=" + Arrays.toString(azsField) +
                '}';
    }
}
