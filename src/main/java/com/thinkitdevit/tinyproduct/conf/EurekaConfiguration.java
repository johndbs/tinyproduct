package com.thinkitdevit.tinyproduct.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.Jersey3DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;
import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;

/**
 * EurekaConfiguration
 * Fix Eureka DiscoveryClient Implement workaround for Eureka DiscoveryClient based on https://github.com/spring-cloud/spring-cloud-netflix/issues/4177#issuecomment-1952926041
 */
@Configuration
public class EurekaConfiguration {

    @Bean
    @ConditionalOnMissingBean(AbstractDiscoveryClientOptionalArgs.class)
    public Jersey3DiscoveryClientOptionalArgs jersey3DiscoveryClientOptionalArgs() {
        return new Jersey3DiscoveryClientOptionalArgs();
    }

    @Bean
    @ConditionalOnMissingBean(TransportClientFactories.class)
    public Jersey3TransportClientFactories jersey3TransportClientFactories() {
        return Jersey3TransportClientFactories.getInstance();
    }

}
