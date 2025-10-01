package service;

import model.Supplier;

public class SupplierService {
    private Supplier[] suppliers = new Supplier[50];
    private static int supplierCount = 0;
    public void addSupplier() {
        Supplier supplier = new Supplier();
        suppliers[supplierCount++] = supplier;
        supplier.nhap();

    }
    public Supplier getSupplierById(String supplierId) {
        for (int i = 0; i < supplierCount; i++) {
            if (suppliers[i].getSupplierId().equals(supplierId)) {
                return suppliers[i];
            }
        }
        return null;
    }    
}
