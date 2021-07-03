package model.event;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.*;


@Entity
//Named queryi iptal ettim sonra sil
@NamedQueries({
        @NamedQuery(name = "Name", query = "SELECT a FROM EventTableDesc a where 'İd'=:id")
       // @NamedQuery(name = "isHaveGetCount", query = "SELECT count(e) FROM EventTableDesc e WHERE UPPER(e.tableName)=UPPER(:DATA)")
})


public class EventTableDesc extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = -1674989521945801023L;
    @Column(unique = true, nullable = false)
    private String tableName;

    public EventTableDesc() {
    }

    public EventTableDesc(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return  tableName ;
    }
}