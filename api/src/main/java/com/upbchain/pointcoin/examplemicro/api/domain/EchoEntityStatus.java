package com.upbchain.pointcoin.examplemicro.api.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
public class EchoEntityStatus implements Serializable {
    private static final long serialVersionUID = -3573228151635269178L;

    @Column
    private ZonedDateTime ackedAt;
    @Column
    private Boolean acked;

    private EchoEntityStatus() {
    }

    public static EchoEntityStatus newInstance(ZonedDateTime ackedAt, @NotNull Boolean acked) {
        EchoEntityStatus ret = new EchoEntityStatus();

        ret.setAcked(acked);
        ret.setAckedAt(ackedAt);

        return ret;
    }

    public ZonedDateTime getAckedAt() {
        return ackedAt;
    }

    public void setAckedAt(ZonedDateTime ackedAt) {
        this.ackedAt = ackedAt;
    }

    public Boolean getAcked() {
        return acked;
    }

    public void setAcked(Boolean acked) {
        this.acked = acked;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof EchoEntityStatus)) {
            return false;
        }
        EchoEntityStatus it = (EchoEntityStatus) o;

        return Objects.equals(this.getAcked(), it.getAcked()) && Objects.equals(this.getAckedAt(), it.getAckedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getAcked(), this.getAckedAt());
    }

}