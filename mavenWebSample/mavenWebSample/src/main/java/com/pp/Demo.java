package com.pp;

import org.togglz.core.context.StaticFeatureManagerProvider;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonFeatureManagerProvider pr = new SingletonFeatureManagerProvider();
		StaticFeatureManagerProvider.setFeatureManager(pr.getFeatureManager());
		
		System.out.println("******FEATURE_ONE*******"+ MyFeatures.FEATURE_ONE.isActive());
		System.out.println("******FEATURE_TWO*******"+ MyFeatures.FEATURE_TWO.isActive());
	}

}
