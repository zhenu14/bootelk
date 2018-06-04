package com.logalert.core.repository;

import com.logalert.core.entity.EmailConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Repository
@Table(name="emailconfig")
@Qualifier("emailReceiverRepository")
public interface EmailConfigRepository extends JpaRepository<EmailConfig, Long > {
    EmailConfig findOne(Long id);

    EmailConfig save(EmailConfig e);

    //    @Modifying
    @Query("select t from EmailConfig t where t.appname in ( :appname )")
    public List<EmailConfig> findEmailReceiverByAppname(@Param("appname") Set<String> appname);
}
