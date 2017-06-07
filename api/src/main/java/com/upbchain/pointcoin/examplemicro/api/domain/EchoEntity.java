package com.upbchain.pointcoin.examplemicro.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author kevin.wang.cy@gmail.com
 */
@Entity
public class EchoEntity extends AbstractAuditableEntity {
    @EmbeddedId
    private EchoEntityId id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(updatable = false)
    private String message;

    @Column(updatable = false, precision = 18, scale = 8)
    private BigDecimal messageSizeGB = BigDecimal.ZERO;

    @Embedded
    private EchoEntityStatus ackStatus;

    @PrePersist
    public void prePersistEvent()
    {
        if ( getAckStatus() == null )
        {
            setAckStatus(EchoEntityStatus.newInstance(null, false));
        }

        if (this.getUuid() == null || this.getUuid().trim().length() == 0) {
            this.setUuid(UUID.randomUUID().toString());
        }
    }

    private EchoEntity(){
    }

    public static EchoEntity newInstance(@NotNull EchoEntityId id) {
        EchoEntity inst = new EchoEntity();
        inst.setId(id);

        return inst;
    }

    public EchoEntityId getId() {
        return id;
    }

    private void setId(EchoEntityId id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getMessageSizeGB() {
        return messageSizeGB;
    }

    public void setMessageSizeGB(BigDecimal messageSizeGB) {
        this.messageSizeGB = messageSizeGB;
    }

    public EchoEntityStatus getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(EchoEntityStatus ackStatus) {
        this.ackStatus = ackStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EchoEntity)) return false;

        EchoEntity that = (EchoEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
