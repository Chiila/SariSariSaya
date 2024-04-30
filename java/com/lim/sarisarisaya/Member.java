package com.lim.sarisarisaya;

public class Member {
    private int memberImg; // Image resource ID for the member
    private String memberName;
    private String memberRole;

    // Constructor to initialize member data
    public Member(int memberImg, String memberName, String memberRole) {
        this.memberImg = memberImg;
        this.memberName = memberName;
        this.memberRole = memberRole;
    }

    // Getter method for retrieving the member image resource ID
    public int getMemberImg() {
        return memberImg;
    }

    // Setter method for updating the member image resource ID
    public void setMemberImg(int memberImg) {
        this.memberImg = memberImg;
    }

    // Getter method for retrieving the member name
    public String getMemberName() {
        return memberName;
    }

    // Setter method for updating the member name
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    // Getter method for retrieving the member role
    public String getMemberRole() {
        return memberRole;
    }

    // Setter method for updating the member role
    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }
}
