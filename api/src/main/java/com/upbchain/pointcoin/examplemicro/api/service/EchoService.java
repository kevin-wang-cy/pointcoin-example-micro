package com.upbchain.pointcoin.examplemicro.api.service;

import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntity;
import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntityId;
import com.upbchain.pointcoin.examplemicro.api.model.Echo;
import com.upbchain.pointcoin.examplemicro.api.repository.EchoResposity;
import com.upbchain.pointcoin.examplemicro.api.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Service
@Transactional
public class EchoService {
    @Autowired
    private EchoResposity echoResposity;

    public Echo findByUUID(String uuid) {
        EchoEntity entity = echoResposity.findByUuid(uuid);

        Echo echo = ObjectConverter.convertFrom(entity);

        return echo;
    }
    public Echo createEcho(Echo echo) {

        EchoEntity entity = ObjectConverter.convertFrom(echo);

        entity = echoResposity.saveAndFlush(entity);

        echo = ObjectConverter.convertFrom(entity);

        return echo;
    }

    public List<Echo> retrieveAllEchoes() {
        List<EchoEntity> entities = echoResposity.findAll();

        // @formatter:off
        List<Echo> echoes = entities.stream().map(ObjectConverter::convertFrom).collect(Collectors.toList());
        // @formatter:on

        return echoes;
    }

    public List<Echo> retrieveAllBySender(String from) {
        List<EchoEntity> entities = echoResposity.findByIdWho(from);

        // @formatter:off
        List<Echo> echoes = entities.stream().map(ObjectConverter::convertFrom).collect(Collectors.toList());
        // @formatter:on

        return echoes;
    }

    public List<Echo> retrieveAllFromAtoB(String from, String to) {
        List<EchoEntity> entities = echoResposity.findByIdWhoAndIdWhom(from, to);

        // @formatter:off
        List<Echo> echoes = entities.stream().map(ObjectConverter::convertFrom).collect(Collectors.toList());
        // @formatter:on

        return echoes;
    }

}
