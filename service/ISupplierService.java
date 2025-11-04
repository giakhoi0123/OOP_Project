package service;

import model.Supplier;

/**
 * Interface định nghĩa các phương thức chuẩn cho SupplierService
 */
public interface ISupplierService {
    void addSupplier();
    void updateSupplier();
    void deleteSupplier();
    void findAndShowSupplierById();
    Supplier getSupplierById(String supplierId);
    void showAllSuppliers();
    int getSupplierCount();
    void saveSuppliersToFile();
    void loadSuppliersFromFile();
}
