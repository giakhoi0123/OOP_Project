package model;

public class PenaltyRule {
    private String ruleId;
    private String description;
    private double amountPerDay;
    public PenaltyRule() {
    }
    public PenaltyRule(String ruleId, String description, double amountPerDay) {
        this.ruleId = ruleId;
        this.description = description;
        this.amountPerDay = amountPerDay;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã quy định: ");
        ruleId = scanner.nextLine();
        System.out.print("Nhập mô tả quy định: ");
        description = scanner.nextLine();
        System.out.print("Nhập số tiền phạt mỗi ngày: ");
        amountPerDay = scanner.nextDouble();
    }
    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmountPerDay() {
        return amountPerDay;
    }
    public void setAmountPerDay(double amountPerDay) {
        this.amountPerDay = amountPerDay;
    }
    @Override
    public String toString() {
        return "PenaltyRule{ruleId='" + ruleId + "', description='" + description + "', amountPerDay='" + amountPerDay + "'}";
    }
}
