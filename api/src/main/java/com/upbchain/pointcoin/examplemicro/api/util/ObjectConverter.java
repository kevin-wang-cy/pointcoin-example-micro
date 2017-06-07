package com.upbchain.pointcoin.examplemicro.api.util;

import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntity;
import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntityId;
import com.upbchain.pointcoin.examplemicro.api.model.Echo;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class ObjectConverter {
    public static Echo convertFrom(EchoEntity entity) {
        Echo echo = new Echo()
                .uuid(entity.getUuid())
                .from(entity.getId().getWho())
                .to(entity.getId().getWhom())
                .when(entity.getId().getWhen())
                .what(entity.getMessage())
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedDate());
        echo.getStatus().acked(entity.getAckStatus().getAcked()).ackedAt(entity.getAckStatus().getAckedAt());

        return echo;
    }

    public static EchoEntity convertFrom(Echo echo) {
        // @formatter:off
        EchoEntity entity = EchoEntity.newInstance(
                EchoEntityId.builder()
                        .from(echo.getWho())
                        .to(echo.getWhom())
                        .at(ZonedDateTime.now())
                        .build());
        // @formatter:on

        entity.setMessage(echo.getWhat());
        entity.setMessageSizeGB(BigDecimal.valueOf(echo.getWhat().getBytes().length).divide(BigDecimal.valueOf(1024 * 1024 * 1024), 8, BigDecimal.ROUND_DOWN));

        return entity;
    }
}
