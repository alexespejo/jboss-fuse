package com.redhat.training;

import com.redhat.training.HelloWorldSvc;

public class Client {
	HelloWorldSvc helloWorldSvc;

	// Bean properties
	public HelloWorldSvc getHelloWorldSvc() {
		return helloWorldSvc;
	}

	public void setHelloWorldSvc(HelloWorldSvc helloWorldSvc) {
		this.helloWorldSvc = helloWorldSvc;
	}

	public void init() {
		System.out.println("OSGi client started.");
		if (helloWorldSvc != null) {
			System.out.println("Calling sayHello()");
			helloWorldSvc.sayHello(); // Invoke the OSGi service!
		}
	}

}
