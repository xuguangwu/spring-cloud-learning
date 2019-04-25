package com.clear.config;

import com.netflix.loadbalancer.*;

public abstract class MyRule extends AbstractLoadBalancerRule {

    private LoadBalancerStats loadBalancerStats;

    @Override
    public Server choose(Object key) {
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
        if (lb instanceof AbstractLoadBalancer) {
            loadBalancerStats = ((AbstractLoadBalancer) lb).getLoadBalancerStats();
        }
    }

}
