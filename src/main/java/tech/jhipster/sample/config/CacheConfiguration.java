package tech.jhipster.sample.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, tech.jhipster.sample.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, tech.jhipster.sample.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, tech.jhipster.sample.domain.User.class.getName());
            createCache(cm, tech.jhipster.sample.domain.Authority.class.getName());
            createCache(cm, tech.jhipster.sample.domain.User.class.getName() + ".authorities");
            createCache(cm, tech.jhipster.sample.domain.EntityIntegerId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomSequence.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidId.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidId.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdRelationship.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdRelationship.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdRelationship.class.getName() + ".manyToManyMapsIds");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTO.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTO.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTO.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTOMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTOMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTOMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTORel.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTORel.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.EntityUuidIdDTORel.class.getName() + ".manyToManyMapsIds");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomId.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomId.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRelationship.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRelationship.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRelationship.class.getName() + ".manyToManyMapsIds");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTO.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTO.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTO.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTOMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTOMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTOMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTORel.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTORel.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdDTORel.class.getName() + ".manyToManyMapsIds");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFiltering.class.getName());
            createCache(cm, tech.jhipster.sample.domain.UuidIdFiltering.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFiltering.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringRelationship.class.getName());
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringRelationship.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.UuidIdFilteringRelationship.class.getName() + ".manyToManyMapsIds");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTO.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTO.class.getName() + ".manyToOneBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTO.class.getName() + ".manyToManyBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId.class.getName() + ".manyToOneMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId.class.getName() + ".manyToManyMapsIdBacks");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel.class.getName());
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel.class.getName() + ".manyToManies");
            createCache(cm, tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel.class.getName() + ".manyToManyMapsIds");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
