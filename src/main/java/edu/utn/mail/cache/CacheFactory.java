package edu.utn.mail.cache;

import edu.utn.mail.domain.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

public class CacheFactory {


    private static CacheManager cacheManager;


    static Cache<Integer, User> userCache;

    private static CacheManager getCacheManager() {
        if (cacheManager == null) {
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .build(true);
        }
        return cacheManager;
    }


    public static Cache<Integer, User> getUserCache() {
        if (userCache == null) {
            userCache = getCacheManager().createCache("userCache",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, User.class,
                            ResourcePoolsBuilder.heap(100)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(100))
                    ).build());

        }
        return userCache;
    }
}
