package json;

/**
 * 指定内容的区域坐标对象
 * @date 2024/9/18
 */

public class RegionPosition {
    //租赁物名称单元格区域  x,y,width,height
    private String leaseNameCoord;

    //租赁物型号单元格区域  x,y,width,height
    private String leaseSpecCoord;

    //权属编号单元格区域  x,y,width,height
    private String ownerNoCoord;

    //唯一识别码单元格区域  x,y,width,height
    private String leaseUuidCoord;

    //供应商单元格区域  x,y,width,height
    private String supplierCoord;

    //地址单元格区域  x,y,width,height
    private String addressCoord;

    public String getLeaseNameCoord() {
        return leaseNameCoord;
    }

    public void setLeaseNameCoord(String leaseNameCoord) {
        this.leaseNameCoord = leaseNameCoord;
    }

    public String getLeaseSpecCoord() {
        return leaseSpecCoord;
    }

    public void setLeaseSpecCoord(String leaseSpecCoord) {
        this.leaseSpecCoord = leaseSpecCoord;
    }

    public String getOwnerNoCoord() {
        return ownerNoCoord;
    }

    public void setOwnerNoCoord(String ownerNoCoord) {
        this.ownerNoCoord = ownerNoCoord;
    }

    public String getLeaseUuidCoord() {
        return leaseUuidCoord;
    }

    public void setLeaseUuidCoord(String leaseUuidCoord) {
        this.leaseUuidCoord = leaseUuidCoord;
    }

    public String getSupplierCoord() {
        return supplierCoord;
    }

    public void setSupplierCoord(String supplierCoord) {
        this.supplierCoord = supplierCoord;
    }

    public String getAddressCoord() {
        return addressCoord;
    }

    public void setAddressCoord(String addressCoord) {
        this.addressCoord = addressCoord;
    }
}
