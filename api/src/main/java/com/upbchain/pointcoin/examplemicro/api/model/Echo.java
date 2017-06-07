package com.upbchain.pointcoin.examplemicro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class Echo {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("who")
    private String who = null;

    @JsonProperty("whom")
    private String whom = null;

    @JsonProperty("when")
    private ZonedDateTime when = null;

    @JsonProperty("what")
    private String what = null;

    @JsonProperty("acknowledge")
    private Status status = new Status();

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdAt")
    private ZonedDateTime createdAt;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Echo uuid(String uuid) {
        this.uuid = uuid;

        return this;
    }

    public Echo from(String who) {
        this.who = who;
        return this;
    }

    public Echo to(String whom) {
        this.whom = whom;
        return this;
    }

    public Echo what(String what) {
        this.what = what;
        return this;
    }

    public Echo when(ZonedDateTime when) {
        this.when = when;

        return this;
    }

    public Echo createdBy(String createdBy) {
        this.createdBy = createdBy;

        return this;
    }

    public Echo createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public static class Status {
        @JsonProperty("acked")
        private Boolean acked = false;

        @JsonProperty("ackedAt")
        private ZonedDateTime ackedAt;

        public Boolean getAcked() {
            return acked;
        }

        public void setAcked(Boolean acked) {
            this.acked = acked;
        }

        public ZonedDateTime getAckedAt() {
            return ackedAt;
        }

        public void setAckedAt(ZonedDateTime acketAt) {
            this.ackedAt = acketAt;
        }

        public Status acked(Boolean acked) {
            this.acked = acked;

            return this;
        }

        public Status ackedAt(ZonedDateTime ackedAt) {
            this.ackedAt = ackedAt;

            return this;
        }
    }
}
