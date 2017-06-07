package com.upbchain.pointcoin.examplemicro.api.repository;

import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntity;
import com.upbchain.pointcoin.examplemicro.api.domain.EchoEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EchoResposity extends JpaRepository<EchoEntity, EchoEntityId> {
    EchoEntity findByUuid(@NotNull String uuid);

    List<EchoEntity> findByIdWho(@NotNull String who);

    List<EchoEntity> findByIdWhoAndIdWhom(@NotNull String who, @NotNull String whom);

    List<EchoEntity> findByIdWhomAndAckStatusAckedFalse(@NotNull String whom);
}
