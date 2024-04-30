package com.lim.sarisarisaya;

public class Instruction {
    private String instructionTitle;
    private String description;

    // Constructor to initialize an Instruction object with provided title and description
    public Instruction(String instructionTitle, String description) {
        this.instructionTitle = instructionTitle;
        this.description = description;
    }

    // Getter method to retrieve the instruction title
    public String getInstructionTitle() {
        return instructionTitle;
    }

    // Setter method to set the instruction title
    public void setInstructionTitle(String instructionTitle) {
        this.instructionTitle = instructionTitle;
    }

    // Getter method to retrieve the description
    public String getDescription() {
        return description;
    }

    // Setter method to set the description
    public void setDescription(String description) {
        this.description = description;
    }
}
