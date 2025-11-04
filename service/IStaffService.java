package service;

import model.Staff;

/**
 * Interface định nghĩa các phương thức chuẩn cho StaffService
 */
public interface IStaffService {
    void addStaff();
    void updateStaff();
    void deleteStaff();
    void findAndShowStaffById();
    Staff getStaffById(String staffId);
    void showAllStaff();
    void saveStaffsToFile();
    void loadStaffsFromFile();
}
