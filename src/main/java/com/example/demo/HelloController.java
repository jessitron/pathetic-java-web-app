package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.trace.Span;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
    Span span = Span.current();
    span.setAttribute("jess.was.here",true);
    addGlobalThreadCountToSpan();
		return "Greetings from Spring Boot!";
	}

  private void addGlobalThreadCountToSpan() {
    Span span = Span.current();
    // Get the current thread's thread group
    ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
    span.setAttribute("thread.group.name", rootGroup.getName());
    ThreadGroup parentGroup;
    while ((parentGroup = rootGroup.getParent()) != null) {
        rootGroup = parentGroup;
    }

    // Active count of threads in the thread group
    int activeThreads = rootGroup.activeCount();
    span.setAttribute("thread.root.active.count", activeThreads);

    System.out.println("Number of active threads: " + activeThreads);

  }

}
