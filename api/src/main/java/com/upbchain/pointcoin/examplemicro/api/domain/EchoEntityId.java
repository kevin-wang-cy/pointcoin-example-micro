package com.upbchain.pointcoin.examplemicro.api.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
public class EchoEntityId implements Serializable {
    private static final long serialVersionUID = -3573228151635269178L;

    @Column(updatable = false, nullable = false)
    private String who;

    @Column(updatable = false, nullable = false)
    private String whom;

    @Column(updatable = false, nullable = false)
    private ZonedDateTime when;

    private EchoEntityId() {
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhom() {
        return whom;
    }

    public void setWhom(String whom) {
        this.whom = whom;
    }

    public ZonedDateTime getWhen() {
        return when;
    }

    public void setWhen(ZonedDateTime when) {
        this.when = when;
    }

    public static EchoEntityIdBuilder builder() {
        return new EchoEntityIdBuilder();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof EchoEntityId)) {
            return false;
        }
        EchoEntityId it = (EchoEntityId) o;

        return Objects.equals(this.getWho(), it.getWho()) && Objects.equals(this.getWhom(), it.getWhom()) && Objects.equals(this.getWhen(), it.getWhen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getWho(), this.getWhom(), this.getWhen());
    }


    public static class EchoEntityIdBuilder {
        private String who;
        private String whom;
        private ZonedDateTime when;

        private EchoEntityIdBuilder() {
        }

        public EchoEntityIdBuilder from(String who) {
            this.who = who;
            return this;
        }

        public EchoEntityIdBuilder to(String whom) {
            this.whom = whom;
            return this;
        }

        public EchoEntityIdBuilder at(ZonedDateTime when) {
            this.when = when;

            return this;
        }

        public EchoEntityId build() {
            assert who != null && who.trim().length() > 0;
            assert whom != null && whom.trim().length() > 0;
            assert when != null;

            EchoEntityId id = new EchoEntityId();

            id.setWho(this.who);
            id.setWhom(this.whom);
            id.setWhen(this.when);

            return id;
        }
    }
}