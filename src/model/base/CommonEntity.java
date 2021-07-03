package model.base;

import model.user.Users;
import utility.MyDate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class CommonEntity extends BaseEntity {
    abstract public String toString2();//Bütün detaylar
    abstract public String toString3();//Notify İçin Düzenlenmiş
    @Transient
    private static final long serialVersionUID = -2138335289342472583L;
    //CREATE
    @Column(nullable = false)
    private long createTime;
    @ManyToOne
    private Users whoCreate;
    //DELETE
    private long delTime;
    @ManyToOne
    private Users whoDel;

    //aşağıdaki liste FETC=LAZY modunda çalıştırılacak sonra hallet
    //UPDATE
    @ElementCollection          //(fetch = FetchType.LAZY)
    private List<UpdateEntity> up = new ArrayList<>();
    //private Collection<Address> addresses = new ArrayList<Address>();

    public CommonEntity() {
    }

    public CommonEntity(long createTime, Users whoCreate, long delTime, Users whoDel, List<UpdateEntity> up) {
        this.createTime = createTime;
        this.whoCreate = whoCreate;
        this.delTime = delTime;
        this.whoDel = whoDel;
        this.up = up;
    }

    public String getKayitTarihi() {
         return new MyDate(this.createTime).getMyDateAsString("dd/MM/yyyy");
    }

    public String getKayitSaati() {
        return new MyDate(this.createTime).getMyDateAsString("mm:HH:ss");
    }

    public Users getWhoCreate() {
        return whoCreate;// = whoCreate == null ? new Users() : whoCreate;
    }

    public Users getWhoDel() {
        return whoDel;// = whoDel == null ? new Users() : whoDel;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public List<UpdateEntity> getUp() {
        return up;
    }

    public void setUp(List<UpdateEntity> up) {
        this.up = up;
    }

    public void addUp(Users whoUp, long upTime) {
        this.up.add(new UpdateEntity(whoUp, upTime));
    }

    public void addUp(UpdateEntity upEntity) {
        //if (this.upTime == null) this.upTime = new ArrayList<>();
        this.up.add(upEntity);
    }

    public long getDelTime() {
        return delTime;
    }

    public void setDelTime(long delTime) {
        this.delTime = delTime;
    }

    public void setWhoCreate(Users whoCreate) {
        this.whoCreate = whoCreate;
    }

    public void setWhoDel(Users whoDel) {
        this.whoDel = whoDel;
    }
}
