/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.controller;
import GymManagementSystem.table.MemberTable;
import GymManagementSystem.view.members;


/**
 *
 * @author ACER
 */
public class MemberController {

    private members view;
    private MemberTable memberTable;

    public MemberController(members view, MemberTable memberTable) {
        this.view = view;
        this.memberTable = memberTable;
    }

    public void addMember(String name, String contact, String plan) {
        memberTable.addMember(name, contact, plan);
        view.updateTotalMembers(memberTable.getTotalMembers());
    }

    public int getTotalMembers() {
        return memberTable.getTotalMembers();
    }
}
